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
import services.FollowService;

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

        //セッションからログイン中のユーザー情報を取得
        UserView loginUser = (UserView) getSessionScope(AttributeConst.LOGIN_USE);

        //指定のユーザーのフォローデータを取得
        FollowService fs = new FollowService();
        List<FollowView> fl = fs.allFollowers(loginUser);

        //フォローデータのユーザー一覧を取得
        List <UserView> users = new ArrayList<UserView>();
        for(FollowView fv : fl) {

            users.add(UserConverter.toView(fv.getFollowee()));

        }
        //ログインユーザーのデータも追加
        users.add(loginUser);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        //http://localhost:8080/Portfolio/?action=Top&command=index

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }

}