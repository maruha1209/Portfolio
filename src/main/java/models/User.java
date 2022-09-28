package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * ユーザーデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_USE)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    /**
     * ユーザーID
     */
    @Id
    @Column(name = JpaConst.USE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    /**
     * 氏名
     */
    @Column(name = JpaConst.USE_COL_NAME, nullable = false)
    private String name;

    /**
     * パスワード
     */
    @Column(name = JpaConst.USE_COL_PASS, length = 64, nullable = false)
    private String password;

    /**
     *登録日時
     */
    @Column(name = JpaConst.USE_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.USE_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.USE_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}
