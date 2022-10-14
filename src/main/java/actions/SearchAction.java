package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.PostView;
import actions.views.UserView;
import constants.ForwardConst;
import services.PostService;
import services.UserService;

public class SearchAction extends ActionBase{

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
     * 検索ページの表示
     * @throws ServletException
     * @throws IOException
     */
    public void top() throws ServletException, IOException {

        forward(ForwardConst.FW_SCH_TOP);

    }

    public void searchUsers() throws ServletException, IOException {

        //String su = getRequestParam(AttributeConst.SCH_USE);
        String su = request.getParameter("search_users");

        //putRequestScope(AttributeConst.SCH_USE, su);
        request.setAttribute("search_users", su);

        List<UserView> users = service.getSearchUsers(su);

        request.setAttribute("users", users);

        //検索一覧を表示
        forward(ForwardConst.FW_SCH_USERS);

    }


    public void searchPosts() throws ServletException, IOException {

        PostService ps = new PostService();

        //String su = getRequestParam(AttributeConst.SCH_USE);
        String sp = request.getParameter("search_posts");

        //putRequestScope(AttributeConst.SCH_USE, su);
        request.setAttribute("search_posts", sp);

        List<PostView> posts = ps.getSearchPosts(sp);

        request.setAttribute("posts", posts);

        //検索一覧を表示
        forward(ForwardConst.FW_SCH_POSTS);

    }
}
