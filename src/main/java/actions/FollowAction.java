package actions;

import java.io.IOException;

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
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        UserService us = new UserService();


            //セッションからログイン中のユーザー情報を取得
            UserView ru = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

            //パラメータからユーザー情報を取得
            UserView fu = us.findOne(getRequestParam(AttributeConst.USE_ID));

            Long f = service.followCount(ru, fu);

            if (f != 0 || f != null || ru.getId().equals(fu.getId())) {

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
            forward(ForwardConst.FW_SCH_USERS);

    }

}

