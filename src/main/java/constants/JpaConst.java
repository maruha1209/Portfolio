package constants;

public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "Portfolio";

    // ユーザーテーブル
    String TABLE_USE = "users"; //テーブル名
    // ユーザーテーブルカラム
    String USE_COL_ID = "user_id"; //ユーザーID
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
    String FOL_COL_FOLLOWER_USE = "follower"; //フォローしたユーザーのid
    String FOL_COL_FOLLOWEE_USE = "followee"; //フォローされたユーザーのid
    String FOL_COL_CREATED_AT = "created_at"; //登録日時
    String FOL_COL_UPDATED_AT = "updated_at"; //更新日時
}
