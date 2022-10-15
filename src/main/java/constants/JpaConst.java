package constants;

public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "Portfolio";

  //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数


    // ユーザーテーブル
    String TABLE_USE = "users"; //テーブル名
    // ユーザーテーブルカラム
    String USE_COL_ID = "id"; //ユーザーID
    String USE_COL_NAME = "name"; //氏名
    String USE_COL_PASS = "password"; //パスワード
    String USE_COL_CREATED_AT = "created_at"; //登録日時
    String USE_COL_UPDATED_AT = "updated_at"; //更新日時
    String USE_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int USE_DEL_TRUE = 1; //削除フラグON(削除済み)
    int USE_DEL_FALSE = 0; //削除フラグOFF(現役)

    //投稿テーブル
    String TABLE_POS = "posts"; //テーブル名
    //投稿テーブルカラム
    String POS_COL_ID = "id"; //id
    String POS_COL_USE = "user"; //投稿を作成したユーザーのid
    String POS_COL_CONTENT = "content"; //投稿の内容
    String POS_COL_CREATED_AT = "created_at"; //登録日時
    String POS_COL_UPDATED_AT = "updated_at"; //更新日時

    //フォローテーブル
    String TABLE_FOL = "follows"; //テーブル名
    //フォローテーブルカラム
    String FOL_COL_ID = "id"; //id
    String FOL_COL_FOLLOWER_USE = "follower"; //フォローしたユーザー
    String FOL_COL_FOLLOWEE_USE = "followee"; //フォローされたユーザー
    String FOL_COL_CREATED_AT = "created_at"; //登録日時
    String FOL_COL_UPDATED_AT = "updated_at"; //更新日時

    //Entity名
    String ENTITY_USE = "user"; //ユーザー
    String ENTITY_POS = "post";
    String ENTITY_FOL = "follow";

    //JPQL内パラメータ
    String JPQL_PARM_ID = "id"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_USER = "user"; //従業員
    String JPQL_PARM_FOLLOWER = "follower";
    String JPQL_PARM_FOLLOWEE = "followee";
    String JPQL_PARM_SEARCH_USERS = "search_users";

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_USE_GET_ALL = ENTITY_USE + ".getAll"; //name
    String Q_USE_GET_ALL_DEF = "SELECT e FROM User AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_USE_COUNT = ENTITY_USE + ".count";
    String Q_USE_COUNT_DEF = "SELECT COUNT(e) FROM User AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_USE_GET_BY_ID_AND_PASS = ENTITY_USE + ".getByCodeAndPass";
    String Q_USE_GET_BY_ID_AND_PASS_DEF = "SELECT e FROM User AS e WHERE e.deleteFlag = 0 AND e.id = :" + JPQL_PARM_ID + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_USE_COUNT_REGISTERED_BY_ID = ENTITY_USE + ".countRegisteredByCode";
    String Q_USE_COUNT_REGISTERED_BY_ID_DEF = "SELECT COUNT(e) FROM User AS e WHERE e.id = :" + JPQL_PARM_ID;;
    //全ての日報をidの降順に取得する
    String Q_POS_GET_ALL = ENTITY_POS + ".getAll";
    String Q_POS_GET_ALL_DEF = "SELECT r FROM Post AS r ORDER BY r.id DESC";
    //全ての日報の件数を取得する
    String Q_POS_COUNT = ENTITY_POS + ".count";
    String Q_POS_COUNT_DEF = "SELECT COUNT(r) FROM Post AS r";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_POS_GET_ALL_MINE = ENTITY_POS + ".getAllMine";
    String Q_POS_GET_ALL_MINE_DEF = "SELECT r FROM Post AS r WHERE r.deleteFlag = 0 AND r.user = :" + JPQL_PARM_USER + " ORDER BY r.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_POS_COUNT_ALL_MINE = ENTITY_POS + ".countAllMine";
    String Q_POS_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Post AS r WHERE r.user = :" + JPQL_PARM_USER;
    //全てのフォローデータをidの降順に取得する
    String Q_FOL_GET_ALL = ENTITY_FOL + ".getAll";
    //指定した従業員のフォローデータを全件idの降順で取得する
    //String Q_FOL_GET_ALL_MINE = ENTITY_FOL + ".getAllMine";
    //String Q_FOL_GET_ALL_MINE_DEF = "SELECT r FROM Follow AS r WHERE r.follow = :" + JPQL_PARM_FOLLOW + " ORDER BY r.id DESC";
    //指定したキーワードを含むユーザーを全件取得する
    String Q_USE_GET_ALL_SEARCH = ENTITY_USE + ".getAllSearchUsers";
    String Q_USE_GET_ALL_SEARCH＿DEF = "SELECT a FROM User AS a WHERE a.name LIKE :" + JPQL_PARM_SEARCH_USERS;

}
