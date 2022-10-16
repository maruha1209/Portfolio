package constants;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 */
public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    PAGE("page"),
    MAX_ROW("maxRow"),

    //ログイン中のユーザー
    LOGIN_USE("login_user"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //ユーザー管理
    USER("user"),
    USERS("users"),
    USE_COUNT("users_count"),
    USE_ID("id"),
    USE_PASS("password"),
    USE_NAME("name"),

    //検索機能
    SCH_USE("search_users"),
    SCH_POS("search_posts"),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //投稿管理
    POST("post"),
    POSTS("posts"),
    POS_COUNT("posts_count"),
    POS_ID("id"),
    POS_DATE("post_date"),
    POS_TITLE("title"),
    POS_CONTENT("content"),
    POS_TIME_IN("timeIn"),
    POS_TIME_OUT("timeOut");


    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}