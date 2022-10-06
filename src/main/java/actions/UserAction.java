package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.UserService;

public class UserAction extends ActionBase {

    private UserService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new UserService();

        //メソッドを実行
        invoke();

        service.close();

    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
/*    public void index() throws ServletException, IOException {

        List<UserView> users = service.getUserAll();

        putRequestScope(AttributeConst.USERS, users); //取得したユーザーデータ

      //一覧画面を表示
        forward(ForwardConst.FW_USE_INDEX);

    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.USER, new UserView()); //空のユーザーインスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_USE_NEW);
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
/*    public void create() throws ServletException, IOException {

        //パラメータの値を元に従業員情報のインスタンスを作成する
        UserView ev = new UserView(
                null,
                getRequestParam(AttributeConst.USE_NAME),
                getRequestParam(AttributeConst.USE_PASS),
                null,
                null,
                AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

        //アプリケーションスコープからpepper文字列を取得
        String pepper = getContextScope(PropertyConst.PEPPER);

        //従業員情報登録
        List<String> errors = service.create(ev, pepper);

        if (errors.size() > 0) {
            //登録中にエラーがあった場合

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.USER, ev); //入力された従業員情報
            putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

            //新規登録画面を再表示
            forward(ForwardConst.FW_USE_NEW);

        } else {
            //登録中にエラーがなかった場合

            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

            //一覧画面にリダイレクト
            //redirect(ForwardConst.ACT_USE, ForwardConst.CMD_INDEX);
        }

    }
*/
}
