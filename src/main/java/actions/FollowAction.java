package actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.FollowView;
import actions.views.UserConverter;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.FollowService;
import services.UserService;

public class FollowAction extends ActionBase{

    private FollowService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new FollowService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * フォロー中のユーザー一覧を表示
     */
    public void followerIndex() throws ServletException, IOException {

        //idを条件にユーザーデータを取得する
        UserService userService = new UserService();

        UserView fu = userService.findOne(getRequestParam(AttributeConst.USE_ID));

        if (fu == null || fu.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
        }

        //指定のユーザーのフォローデータを取得
        List<FollowView> fl = service.allFollowers(fu);

        //フォローデータのユーザー一覧を取得
        List <UserView> users = new ArrayList<UserView>();

        for(FollowView fv : fl) {

            users.add(UserConverter.toView(fv.getFollowee()));

        }

        putRequestScope(AttributeConst.USERS, users);

        putSessionScope(AttributeConst.USER, fu);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_FOL_FOLLOWERINDEX);

    }

    /**
     * フォロワー一覧を表示
     */
    public void followeeIndex() throws ServletException, IOException {

        //idを条件にユーザーデータを取得する
        UserService userService = new UserService();

        UserView fu = userService.findOne(getRequestParam(AttributeConst.USE_ID));

        if (fu == null || fu.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
        }

        //指定のユーザーのフォローデータを取得
        List<FollowView> fl = service.allFollowees(fu);

        //フォローデータのユーザー一覧を取得
        List <UserView> users = new ArrayList<UserView>();

        for(FollowView fv : fl) {

            users.add(UserConverter.toView(fv.getFollower()));

        }

        putRequestScope(AttributeConst.USERS, users);

        putSessionScope(AttributeConst.USER, fu);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_FOL_FOLLOWEEINDEX);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

            //セッションからログイン中のユーザー情報を取得
            UserView ru = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

          //idを条件にユーザーデータを取得する
            UserService userService = new UserService();

            UserView fu = userService.findOne(getRequestParam(AttributeConst.USE_ID));

            if (fu == null || fu.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

                //データが取得できなかった、または論理削除されている場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
            }

            Long f = service.followCount(ru, fu);

            if (f >= 1 || f != null || ru.getId().equals(fu.getId())) {

                //セッションにフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, "フォロー済みのユーザーです");


                //すでにフォロー済みの場合
                forward(ForwardConst.FW_ERR_UNKNOWN);

            }

            //パラメータの値をもとに投稿情報のインスタンスを作成する
            FollowView fv = new FollowView(
                    null,
                    UserConverter.toModel(ru), //ログインしているユーザーを、投稿作成者として登録する
                    UserConverter.toModel(fu), //フォローするユーザー
                    null,
                    null);


            //投稿情報登録
            service.create(fv);

            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

            //一覧画面にリダイレクト
            //redirect(ForwardConst.ACT_POS, ForwardConst.CMD_INDEX, getRequestParam(AttributeConst.USE_ID));


    }

    /**
     * データベースから削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //セッションからログイン中のユーザー情報を取得
        UserView ru = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

      //idを条件にユーザーデータを取得する
        UserService userService = new UserService();

        UserView fu = userService.findOne(getRequestParam(AttributeConst.USE_ID));

        if (fu == null || fu.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
        }

        //指定したフォロー情報を削除
        service.delete(ru, fu);

        //セッションに削除完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_POS, ForwardConst.CMD_INDEX, getRequestParam(AttributeConst.USE_ID));

    }


}

