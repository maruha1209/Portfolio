package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = JpaConst.TABLE_FOL)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.FOL_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * フォローしたユーザーID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.USE_COL_ID, referencedColumnName = JpaConst.FOL_COL_FOLLOWER_USE, nullable = false)
    private User follower;

    /**
     * フォローされたユーザーID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.USE_COL_ID, referencedColumnName = JpaConst.FOL_COL_FOLLOWEE_USE, nullable = false)
    private User followee;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.FOL_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.FOL_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}
