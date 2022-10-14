package constants;

/**
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {

    //action
    ACT("action"),
    ACT_USE("User"),
    ACT_POS("Post"),
    ACT_AUTH("Auth"),
    ACT_TOP("Top"),
    ACT_SCH("Search"),
    ACT_FOL("Follow"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),
    CMD_USERINDEX("userIndex"),
    CMD_TOP("top"),
    CMD_SEARCH_USERS("searchUsers"),
    CMD_SEARCH_POSTS("searchPosts"),

  //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_USE_INDEX("users/index"),
    FW_USE_SHOW("users/show"),
    FW_USE_NEW("users/new"),
    FW_USE_EDIT("users/edit"),
    FW_USE_USERINDEX("users/userIndex"),
    FW_POS_INDEX("posts/index"),
    FW_POS_SHOW("posts/show"),
    FW_POS_NEW("posts/new"),
    FW_POS_EDIT("posts/edit"),
    FW_FOL_INDEX("follows/index"),
    FW_SCH_TOP("search/top"),
    FW_SCH_USERS("search/users"),
    FW_SCH_POSTS("search/posts");

    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}
