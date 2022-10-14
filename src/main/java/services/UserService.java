package services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.User;
import models.validators.UserValidator;
import utils.EncryptUtil;

/**
 * ユーザーテーブルの操作に関わる処理を行うクラス
 */
public class UserService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、UserViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<UserView> getPerPage(int page) {
        List<User> employees = em.createNamedQuery(JpaConst.Q_USE_GET_ALL, User.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return UserConverter.toViewList(employees);
    }

    /**
     * 入力されたキーワードと部分一致のユーザーを返却

    public List<UserView> getSearchUsers(String sql) {

        List<User> users = em.createNamedQuery(JpaConst.Q_USE_GET_ALL_SEARCH, User.class).
                setParameter(JpaConst.JPQL_PARM_SEARCH_USERS, "%" + sql + "%")
                .getResultList();

        return UserConverter.toViewList(users);
    }*/

    /**
     * 入力されたキーワードと部分一致のユーザーを返却
     */
    public List<UserView> getSearchUsers(String su) {

        List<User> users = em.createQuery("SELECT a FROM User AS a WHERE a.name LIKE :search_users")
                .setParameter("search_users", "%" + su + "%")
                .getResultList();

        return UserConverter.toViewList(users);
    }

    /**
     * ユーザーテーブルのデータの件数を取得し、返却する
     * @return ユーザーテーブルのデータの件数
     */
    public long countAll() {
        long empCount = (long) em.createNamedQuery(JpaConst.Q_USE_COUNT, Long.class)
                .getSingleResult();

        return empCount;
    }

    /**
     * 社員番号、パスワードを条件に取得したデータをUserViewのインスタンスで返却する
     * @param id 社員番号
     * @param plainPass パスワード文字列
     * @param pepper pepper文字列
     * @return 取得データのインスタンス 取得できない場合null
     */
    public UserView findOne(String id, String plainPass, String pepper) {
        User e = null;
        try {
            //パスワードのハッシュ化
            String pass = EncryptUtil.getPasswordEncrypt(plainPass, pepper);

            //社員番号とハッシュ化済パスワードを条件に未削除のユーザーを1件取得する
            e = em.createNamedQuery(JpaConst.Q_USE_GET_BY_ID_AND_PASS, User.class)
                    .setParameter(JpaConst.JPQL_PARM_ID, id)
                    .setParameter(JpaConst.JPQL_PARM_PASSWORD, pass)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return UserConverter.toView(e);

    }

    /**
     * idを条件に取得したデータをUserViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス

    public UserView findId(String id) {
        User u = (User) em.createQuery("SELECT r FROM User AS r WHERE r.id = :id")
                .setParameter("id", id)
                .getSingleResult();

        return UserConverter.toView(u);
    }

    /**
     * idを条件に取得したデータをUserViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public UserView findOne(String id) {
        User e = findOneInternal(id);
        return UserConverter.toView(e);
    }

    /**
     * 社員番号を条件に該当するデータの件数を取得し、返却する
     * @param id 社員番号
     * @return 該当するデータの件数
     */
    public long countById(String id) {

        //指定した社員番号を保持するユーザーの件数を取得する
        long employees_count = (long) em.createNamedQuery(JpaConst.Q_USE_COUNT_REGISTERED_BY_ID, Long.class)
                .setParameter(JpaConst.JPQL_PARM_ID, id)
                .getSingleResult();
        return employees_count;
    }

    /**
     * 画面から入力されたユーザーの登録内容を元にデータを1件作成し、ユーザーテーブルに登録する
     * @param ev 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(UserView ev, String pepper) {

        //パスワードをハッシュ化して設定
        String pass = EncryptUtil.getPasswordEncrypt(ev.getPassword(), pepper);
        ev.setPassword(pass);

        //登録日時、更新日時は現在時刻を設定する
        LocalDateTime now = LocalDateTime.now();
        ev.setCreatedAt(now);
        ev.setUpdatedAt(now);

        //登録内容のバリデーションを行う
        List<String> errors = UserValidator.validate(this, ev, true, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            create(ev);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力されたユーザーの更新内容を元にデータを1件作成し、ユーザーテーブルを更新する
     * @param ev 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(UserView ev, String pepper) {

        //idを条件に登録済みのユーザー情報を取得する
        UserView savedUse = findOne(ev.getId());

        boolean validateId = false;
        if (!savedUse.getId().equals(ev.getId())) {
            //社員番号を更新する場合

            //社員番号についてのバリデーションを行う
            validateId = true;
            //変更後の社員番号を設定する
            savedUse.setId(ev.getId());
        }

        boolean validatePass = false;
        if (ev.getPassword() != null && !ev.getPassword().equals("")) {
            //パスワードに入力がある場合

            //パスワードについてのバリデーションを行う
            validatePass = true;

            //変更後のパスワードをハッシュ化し設定する
            savedUse.setPassword(
                    EncryptUtil.getPasswordEncrypt(ev.getPassword(), pepper));
        }

        savedUse.setName(ev.getName()); //変更後の氏名を設定する

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedUse.setUpdatedAt(today);

        //更新内容についてバリデーションを行う
        List<String> errors = UserValidator.validate(this, savedUse, validateId, validatePass);

        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {
            update(savedUse);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にユーザーデータを論理削除する
     * @param id
     */
    public void destroy(String id) {

        //idを条件に登録済みのユーザー情報を取得する
        UserView savedUse = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedUse.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedUse.setDeleteFlag(JpaConst.USE_DEL_TRUE);

        //更新処理を行う
        update(savedUse);

    }

    /**
     * 社員番号とパスワードを条件に検索し、データが取得できるかどうかで認証結果を返却する
     * @param id 社員番号
     * @param plainPass パスワード
     * @param pepper pepper文字列
     * @return 認証結果を返却す(成功:true 失敗:false)
     */
    public Boolean validateLogin(String id, String plainPass, String pepper) {

        boolean isValidUser = false;
        if (id != null && !id.equals("") && plainPass != null && !plainPass.equals("")) {
            UserView ev = findOne(id, plainPass, pepper);

            if (ev != null && ev.getId() != null) {

                //データが取得できた場合、認証成功
                isValidUser = true;
            }
        }

        //認証結果を返却する
        return isValidUser;
    }

    /**
     * idを条件にデータを1件取得し、Userのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private User findOneInternal(String id) {
        User e = em.find(User.class, id);

        return e;
    }

    /**
     * ユーザーデータを1件登録する
     * @param ev ユーザーデータ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void create(UserView ev) {

        em.getTransaction().begin();
        em.persist(UserConverter.toModel(ev));
        em.getTransaction().commit();

    }

    /**
     * ユーザーデータを更新する
     * @param ev 画面から入力されたユーザーの登録内容
     */
    private void update(UserView ev) {

        em.getTransaction().begin();
        User e = findOneInternal(ev.getId());
        UserConverter.copyViewToModel(e, ev);
        em.getTransaction().commit();

    }

}