package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.FollowConverter;
import actions.views.FollowView;
import actions.views.UserConverter;
import actions.views.UserView;

public class FollowService extends ServiceBase{

    /**
     * 二つのユーザー情報を条件に既にデータが存在するかを確認する
     * @follower フォローする側のユーザー
     * @followee フォローされる側のユーザー
     */
    public Long followCount(UserView follower, UserView followee) {

        Long f = (Long) em.createQuery("SELECT COUNT(a) FROM Follow AS a WHERE a.follower = :fer AND a.followee = :fee")
                .setParameter("fer", UserConverter.toModel(follower))
                .setParameter("fee", UserConverter.toModel(followee))
                .getSingleResult();

            return f;

    }

    /**
     * 表示するユーザーがフォロー済みかチェックする
     */
    public boolean followCheck(UserView follower, UserView followee) {

            Long f = (Long) em.createQuery("SELECT COUNT(a) FROM Follow AS a WHERE a.follower = :fer AND a.followee = :fee")
                    .setParameter("fer", UserConverter.toModel(follower))
                    .setParameter("fee", UserConverter.toModel(followee))
                    .getSingleResult();

            if (f >= 1) {

                return true;

            } else {

                return false;

            }

    }

    /**
     * 一覧に表示するユーザーがフォロー済みかチェックする
     */
    public List<Integer> followsCheck(UserView follower, List<UserView> followees) {

        List<Integer> fc = new ArrayList<Integer>();

        for(UserView followee : followees) {

            Long f = (Long) em.createQuery("SELECT COUNT(a) FROM Follow AS a WHERE a.follower = :fer AND a.followee = :fee")
                    .setParameter("fer", UserConverter.toModel(follower))
                    .setParameter("fee", UserConverter.toModel(followee))
                    .getSingleResult();

            if (f >= 1) {
                fc.add(1);
            } else if (f == 0 || f == null) {
                fc.add(0);
            }

        }

        return fc;

    }

    /**
     * 新規フォロー情報を登録する
     *
     */
    public void create(FollowView rv) {

            LocalDateTime ldt = LocalDateTime.now();
            rv.setCreatedAt(ldt);
            rv.setUpdatedAt(ldt);
            createInternal(rv);

    }

    /**
     * ユーザーデータを1件登録する
     * @param ev ユーザーデータ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void createInternal(FollowView ev) {

        em.getTransaction().begin();
        em.persist(FollowConverter.toModel(ev));
        em.getTransaction().commit();

    }

}