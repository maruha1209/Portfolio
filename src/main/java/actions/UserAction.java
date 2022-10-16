package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import constants.PropertyConst;
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
    public void index() throws ServletException, IOException {

            //指定されたページ数の一覧画面に表示するデータを取得
            int page = getPage();
            List<UserView> employees = service.getPerPage(page);

            //全ての従業員データの件数を取得
            long employeeCount = service.countAll();

            putRequestScope(AttributeConst.USERS, employees); //取得した従業員データ
            putRequestScope(AttributeConst.USE_COUNT, employeeCount); //全ての従業員データの件数
            putRequestScope(AttributeConst.PAGE, page); //ページ数
            putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

            //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
            String flush = getSessionScope(AttributeConst.FLUSH);
            if (flush != null) {
                putRequestScope(AttributeConst.FLUSH, flush);
                removeSessionScope(AttributeConst.FLUSH);
            }

            //一覧画面を表示
            forward(ForwardConst.FW_USE_INDEX);

    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     * http://localhost:8080/Portfolio/?action=User&command=entryNew
     */
    public void entryNew() throws ServletException, IOException {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.USER, new UserView()); //空の従業員インスタンス

            //新規登録画面を表示
            forward(ForwardConst.FW_USE_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //パラメータの値を元に従業員情報のインスタンスを作成する
        UserView ev = new UserView(
                getRequestParam(AttributeConst.USE_ID),
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

            //セッションにログインユーザーを設定
            putSessionScope(AttributeConst.LOGIN_USE, ev);

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
        }

    }


    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

           putRequestScope(AttributeConst.USER, getSessionScope(AttributeConst.LOGIN_USE));

           //詳細画面を表示
           forward(ForwardConst.FW_USE_SHOW);

    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {


            //ログインユーザーを取得
            UserView ev = getSessionScope(AttributeConst.LOGIN_USE);

            if (ev == null || ev.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

                //データが取得できなかった、または論理削除されている場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
            }

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.USER, ev); //取得した従業員情報

            //編集画面を表示する
            forward(ForwardConst.FW_USE_EDIT);

    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) { //追記
            //パラメータの値を元に従業員情報のインスタンスを作成する
            UserView ev = new UserView(
                    getRequestParam(AttributeConst.USE_ID),
                    getRequestParam(AttributeConst.USE_NAME),
                    getRequestParam(AttributeConst.USE_PASS),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            //従業員情報更新
            List<String> errors = service.update(ev, pepper);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.USER, ev); //入力された従業員情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_USE_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                UserView uv = service.findOne(getRequestParam(AttributeConst.USE_ID));

                putSessionScope(AttributeConst.LOGIN_USE, uv);

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) { //追記

            //idを条件に従業員データを論理削除する
            service.destroy(getRequestParam(AttributeConst.USE_ID));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            removeSessionScope(AttributeConst.LOGIN_USE);

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
        }
    }

}
