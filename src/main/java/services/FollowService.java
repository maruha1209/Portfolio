package services;

import java.time.LocalDateTime;

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
    public Boolean validateFollow(UserView follower, UserView followee) {

        Object q = em.createQuery("SELECT a FROM Follow AS a WHERE a.follower = :fer AND a.followee = :fee")
                .setParameter("fer", UserConverter.toModel(follower))
                .setParameter("fee", UserConverter.toModel(followee))
                .getSingleResult();

        if (q != null) {

            return true;

        } else {

            return false;

        }

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