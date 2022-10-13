package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.PostView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.PostService;
import services.UserService;

/**
 * 日報に関する処理を行うActionクラス
 *
 */
public class PostAction extends ActionBase {

    private PostService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new PostService();

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

        //指定されたページ数の一覧画面に表示する日報データを取得
        //int page = getPage();

        //idを条件にユーザーデータを取得する
        UserService userService = new UserService();

        UserView ev = userService.findOne(getRequestParam(AttributeConst.USE_ID));

        if (ev == null || ev.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        //指定IDのユーザーの投稿を全件取得
        List<PostView> reports = service.getAllPost(ev);

        //全日報データの件数を取得
        //long reportsCount = service.countAll();

        putRequestScope(AttributeConst.USER, ev);
        putRequestScope(AttributeConst.POSTS, reports); //取得した日報データ
        //putRequestScope(AttributeConst.POS_COUNT, reportsCount); //全ての日報データの件数
        //putRequestScope(AttributeConst.PAGE, page); //ページ数
        //putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_POS_INDEX);
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        //日報情報の空インスタンスに、日報の日付＝今日の日付を設定する
        //PostView rv = new PostView();
        //rv.setPostDate(LocalDate.now());
        //putRequestScope(AttributeConst.POST, rv); //日付のみ設定済みの日報インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_POS_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            /*//日報の日付が入力されていなければ、今日の日付を設定
            LocalDate day = null;
            if (getRequestParam(AttributeConst.POS_DATE) == null
                    || getRequestParam(AttributeConst.POS_DATE).equals("")) {
                day = LocalDate.now();
            } else {
                day = LocalDate.parse(getRequestParam(AttributeConst.POS_DATE));
            }*/

            //セッションからログイン中の従業員情報を取得
            UserView ev = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

            //パラメータの値をもとに日報情報のインスタンスを作成する
            PostView rv = new PostView(
                    null,
                    ev, //ログインしている従業員を、日報作成者として登録する
                    getRequestParam(AttributeConst.POS_CONTENT),
                    null,
                    null);

            //日報情報登録
            List<String> errors = service.create(rv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.POST, rv);//入力された日報情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_POS_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_POS, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に日報データを取得する
        PostView rv = service.findOne(toNumber(getRequestParam(AttributeConst.POS_ID)));

        if (rv == null) {
            //該当の日報データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.POST, rv); //取得した日報データ

            //詳細画面を表示
            forward(ForwardConst.FW_POS_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に日報データを取得する
        PostView rv = service.findOne(toNumber(getRequestParam(AttributeConst.POS_ID)));

        //セッションからログイン中の従業員情報を取得
        UserView ev = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

        if (rv == null || ev.getId() != rv.getUser().getId()) {
            //該当の日報データが存在しない、または
            //ログインしている従業員が日報の作成者でない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.POST, rv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_POS_EDIT);
        }
    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
/*    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に日報データを取得する
            PostView rv = service.findOne(toNumber(getRequestParam(AttributeConst.POS_ID)));

            //入力された日報内容を設定する
            rv.setPostDate(toLocalDate(getRequestParam(AttributeConst.POS_DATE)));
            rv.setTitle(getRequestParam(AttributeConst.POS_TITLE));
            rv.setContent(getRequestParam(AttributeConst.POS_CONTENT));
            rv.setTimeIn(getRequestParam(AttributeConst.POS_TIME_IN));
            rv.setTimeOut(getRequestParam(AttributeConst.POS_TIME_OUT));

            //日報データを更新する
            List<String> errors = service.update(rv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.POST, rv); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト


                //編集画面を再表示
                forward(ForwardConst.FW_POS_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_POS, ForwardConst.CMD_INDEX);

            }
        }
    }*/
}