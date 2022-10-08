package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Follow;

/**
 * フォローデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class FollowConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param uv FollowViewのインスタンス
     * @return Followのインスタンス
     */
    public static Follow toModel(FollowView uv) {

        return new Follow(
                uv.getId(),
                uv.getFollower(),
                uv.getFollowee(),
                uv.getCreatedAt(),
                uv.getUpdatedAt()
                );

    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param u Followのインスタンス
     * @return FollowViewのインスタンス
     */
    public static FollowView toView(Follow u) {

        if(u == null) {
            return null;
        }

        return new FollowView(
                u.getId(),
                u.getFollower(),
                u.getFollowee(),
                u.getCreatedAt(),
                u.getUpdatedAt()
                );
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<FollowView> toViewList(List<Follow> list) {
        List<FollowView> uvs = new ArrayList<>();

        for (Follow u : list) {
            uvs.add(toView(u));
        }

        return uvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param uv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Follow u, FollowView uv) {
        u.setId(uv.getId());
        u.setFollower(uv.getFollower());
        u.setFollowee(uv.getFollowee());
        u.setCreatedAt(uv.getCreatedAt());
        u.setUpdatedAt(uv.getUpdatedAt());

    }

}

