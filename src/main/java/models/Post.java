package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 投稿データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_POS)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_POS_GET_ALL,
            query = JpaConst.Q_POS_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_POS_COUNT,
            query = JpaConst.Q_POS_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_POS_GET_ALL_MINE,
            query = JpaConst.Q_POS_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_POS_COUNT_ALL_MINE,
            query = JpaConst.Q_POS_COUNT_ALL_MINE_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.POS_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 投稿したユーザー
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.POS_COL_USE, nullable = false)
    private User user;

    /**
     * 投稿の内容
     */
    @Lob
    @Column(name = JpaConst.POS_COL_CONTENT, nullable = false)
    private String content;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.POS_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.POS_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.USE_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}
