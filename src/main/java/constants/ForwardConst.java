package constants;

/**
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {

    //action
    ACT("action"),
    ACT_AUTH("Auth"),

    //command
    CMD("command"),
    CMD_LOGIN("login"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_LOGIN("login/login"),;

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
