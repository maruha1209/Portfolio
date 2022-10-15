package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.PostConverter;
import actions.views.PostView;
import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.Post;
import models.validators.PostValidator;

/**
 * 日報テーブルの操作に関わる処理を行うクラス
 */
public class PostService extends ServiceBase {

    /**指定したユーザーの投稿を全件ID順に取得
     * @param user ユーザー
     * @return 一覧画面に表示するデータのリスト
     */
    public List<PostView> getAllPost(UserView user) {

        List<Post> reports = em.createNamedQuery(JpaConst.Q_POS_GET_ALL_MINE, Post.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .getResultList();
        return PostConverter.toViewList(reports);
    }

    /**
     * 指定した従業員が作成した日報データを、指定されたページ数の一覧画面に表示する分取得しPostViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<PostView> getMinePerPage(UserView employee, int page) {

        List<Post> reports = em.createNamedQuery(JpaConst.Q_POS_GET_ALL_MINE, Post.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return PostConverter.toViewList(reports);
    }

    /**
     * 入力されたキーワードと部分一致の投稿を返却
     */
    public List<PostView> getSearchPosts(String sp) {

        List<Post> posts = em.createQuery("SELECT a FROM Post AS a WHERE a.content LIKE :search_posts AND a.deleteFlag = 0")
                .setParameter("search_posts", "%" + sp + "%")
                .getResultList();

        return PostConverter.toViewList(posts);
    }

    /**
     * 指定した従業員が作成した日報データの件数を取得し、返却する
     * @param employee
     * @return 日報データの件数
     */
    public long countAllMine(UserView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_POS_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示する日報データを取得し、PostViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<PostView> getAllPerPage(int page) {

        List<Post> reports = em.createNamedQuery(JpaConst.Q_POS_GET_ALL, Post.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return PostConverter.toViewList(reports);
    }

    /**
     * 日報テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long reports_count = (long) em.createNamedQuery(JpaConst.Q_POS_COUNT, Long.class)
                .getSingleResult();
        return reports_count;
    }

    /**
     * idを条件に取得したデータをPostViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public PostView findOne(int id) {
        return PostConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(PostView rv) {
        List<String> errors = PostValidator.validate(rv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            rv.setCreatedAt(ldt);
            rv.setUpdatedAt(ldt);
            createInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された日報の登録内容を元に、日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(PostView rv) {

        //バリデーションを行う
        List<String> errors = PostValidator.validate(rv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            rv.setUpdatedAt(ldt);

            updateInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Post findOneInternal(int id) {
        return em.find(Post.class, id);
    }

    /**
     * 日報データを1件登録する
     * @param rv 日報データ
     */
    private void createInternal(PostView rv) {

        em.getTransaction().begin();
        em.persist(PostConverter.toModel(rv));
        em.getTransaction().commit();

    }

    /**
     * 日報データを更新する
     * @param rv 日報データ
     */
    private void updateInternal(PostView rv) {

        em.getTransaction().begin();
        Post r = findOneInternal(rv.getId());
        PostConverter.copyViewToModel(r, rv);
        em.getTransaction().commit();

    }

    /**
     * idを条件に投稿データを論理削除する
     * @param id
     */
    public void destroy(int id) {

        //idを条件に登録済みの投稿情報を取得する
        PostView savedUse = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedUse.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedUse.setDeleteFlag(JpaConst.USE_DEL_TRUE);

        //更新処理を行う
        update(savedUse);

    }


}