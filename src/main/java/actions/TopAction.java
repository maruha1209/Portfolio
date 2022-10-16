package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.PostView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.PostService;

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

    //private PostService service; //追記
    private PostService service = new PostService();

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

        //セッションからログイン中のユーザー情報を取得
        UserView loginUser = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

        //ログインユーザーがフォロー中のユーザーの全投稿を取得
        List<PostView> posts = service.getFollowPosts(loginUser);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        //http://localhost:8080/Portfolio/?action=Top&command=index

        putRequestScope(AttributeConst.POSTS, posts);

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }

}