package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Post;

/**
 * 投稿データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class PostConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param uv PostViewのインスタンス
     * @return Postのインスタンス
     */
    public static Post toModel(PostView uv) {

        return new Post(
                uv.getId(),
                UserConverter.toModel(uv.getUser()),
                uv.getContent(),
                uv.getCreatedAt(),
                uv.getUpdatedAt(),
                uv.getDeleteFlag() == null
                ? null
                : uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.USE_DEL_TRUE
                        : JpaConst.USE_DEL_FALSE);

    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param u Postのインスタンス
     * @return PostViewのインスタンス
     */
    public static PostView toView(Post u) {

        if(u == null) {
            return null;
        }

        return new PostView(
                u.getId(),
                UserConverter.toView(u.getUser()),
                u.getContent(),
                u.getCreatedAt(),
                u.getUpdatedAt(),
                u.getDeleteFlag() == null
                ? null
                : u.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.USE_DEL_TRUE
                        : JpaConst.USE_DEL_FALSE);
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<PostView> toViewList(List<Post> list) {
        List<PostView> uvs = new ArrayList<>();

        for (Post u : list) {
            uvs.add(toView(u));
        }

        return uvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param uv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Post u, PostView uv) {
        u.setId(uv.getId());
        u.setUser(UserConverter.toModel(uv.getUser()));
        u.setContent(uv.getContent());
        u.setCreatedAt(uv.getCreatedAt());
        u.setUpdatedAt(uv.getUpdatedAt());

    }

}

