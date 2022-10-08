package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import constants.ForwardConst;

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

    //private PostService service; //追記

    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        //service = new PostService(); //追記

        //メソッドを実行
        invoke();

        //service.close(); //追記

    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException {

        // 以下追記

        //セッションからログイン中の従業員情報を取得
        /*UserView loginUser = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

        //ログイン中の従業員が作成した日報データを、指定されたページ数の一覧画面に表示する分取得する
        int page = getPage();
        List<PostView> reports = service.getMinePerPage(loginUser, page);

        //ログイン中の従業員が作成した日報データの件数を取得
        long myPostsCount = service.countAllMine(loginUser);

        putRequestScope(AttributeConst.POSTS, reports); //取得した日報データ
        //putRequestScope(AttributeConst.POS_COUNT, myPostsCount); //ログイン中の従業員が作成した日報の数
        //putRequestScope(AttributeConst.PAGE, page); //ページ数
        //putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //↑ここまで追記

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        http://localhost:8080/Portfolio/?action=Top&command=index
*/
        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }

}