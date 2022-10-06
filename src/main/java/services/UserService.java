package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.User;
import models.validators.UserValidator;
import utils.EncryptUtil;

public class UserService extends ServiceBase{

    /**
     * 画面から入力された従業員の登録内容を元にデータを1件作成し、従業員テーブルに登録する
     * @param ev 画面から入力された従業員の登録内容
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
     * ユーザーデータを1件登録する
     * @param ev ユーザーデータ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void create(UserView ev) {

        em.getTransaction().begin();
        em.persist(UserConverter.toModel(ev));
        em.getTransaction().commit();

    }

    public List<UserView> getUserAll() {
        List<User> users = em.createNamedQuery(JpaConst.Q_USE_GET_ALL, User.class)
                                    .getResultList();
        return UserConverter.toViewList(users);
    }


}
