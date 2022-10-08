package actions.views;

import java.util.ArrayList;
import java.util.List;

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
                uv.getUser(),
                uv.getContent(),
                uv.getCreatedAt(),
                uv.getUpdatedAt()
                );

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
                u.getUser(),
                u.getContent(),
                u.getCreatedAt(),
                u.getUpdatedAt()
                );
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
        u.setUser(uv.getUser());
        u.setContent(uv.getContent());
        u.setCreatedAt(uv.getCreatedAt());
        u.setUpdatedAt(uv.getUpdatedAt());

    }

}

