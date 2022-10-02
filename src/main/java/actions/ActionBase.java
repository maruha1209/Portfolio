package actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.AttributeConst;
import constants.ForwardConst;

/**
 * 各Actionクラスの親クラス。共通処理を行う。
 *
 */
public abstract class ActionBase {

    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 初期化処理
     * サーブレットコンテキスト、リクエスト、レスポンスをクラスフィールドに設定
     * @param servletContext
     * @param servletRequest
     * @param servletResponse
     */
    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    /**
     * フロントコントローラから呼び出されるメソッド
     * @throws ServletException
     * @throws IOException
     */
    public abstract void process() throws ServletException, IOException;

    /**
     * 指定されたjspの呼び出しを行う
     * @param target 遷移先jsp画面のファイル名(拡張子を含まない)
     * @throws ServletException
     * @throws IOException
     */
    protected void forward(ForwardConst target) throws ServletException, IOException {

        //jspファイルの相対パスを作成
        String forward = String.format("/WEB-INF/views/%s.jsp", target.getValue());
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);

        //jspファイルの呼び出し
        dispatcher.forward(request, response);

    }

    /**
     * リクエストスコープにパラメータを設定する
     * @param key パラメータ名
     * @param value パラメータの値
     */
    protected <V> void putRequestScope(AttributeConst key, V value) {
        request.setAttribute(key.getValue(), value);
    }

    /**
     * セッションスコープから指定されたパラメータの値を取得し、返却する
     * @param key パラメータ名
     * @return パラメータの値
     */
    @SuppressWarnings("unchecked")
    protected <R> R getSessionScope(AttributeConst key) {
        return (R) request.getSession().getAttribute(key.getValue());
    }

    /**
     * セッションスコープにパラメータを設定する
     * @param key パラメータ名
     * @param value パラメータの値
     */
    protected <V> void putSessionScope(AttributeConst key, V value) {
        request.getSession().setAttribute(key.getValue(), value);
    }

    /**
     * セッションスコープから指定された名前のパラメータを除去する
     * @param key パラメータ名
     */
    protected void removeSessionScope(AttributeConst key) {
        request.getSession().removeAttribute(key.getValue());
    }

}