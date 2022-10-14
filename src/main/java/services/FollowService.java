package services;

import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.User;

public class FollowService extends ServiceBase{

    /**
     * 二つのユーザー情報を条件に既にデータが存在するかを確認する
     */
    public UserView validateFollow(UserView follower, UserView followee) {

        User u = null;

        u = (User) em.createQuery("SELECT a FROM follows WHERE a.follower = ?fer AND a.followee = ?fee")
                .setParameter(JpaConst.JPQL_PARM_FOLLOWER, UserConverter.toModel(follower))
                .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, UserConverter.toModel(followee))
                .getSingleResult();

        return UserConverter.toView(u);

    }

}