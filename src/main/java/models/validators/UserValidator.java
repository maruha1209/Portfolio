/*package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.UserView;
import services.UserService;

public class UserValidator {

    /**
     * ユーザーインスタンスの各項目についてバリデーションを行う
     * @param service 呼び出し元Serviceクラスのインスタンス
     * @param ev UserViewのインスタンス
     * @param codeDuplicateCheckFlag ユーザーIDの重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーのリスト
     */
/*    public static List<String> validate(
            UserService service, UserView ev, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        //ユーザーIDのチェック
        String codeError = validateCode(service, ev.getID(), codeDuplicateCheckFlag);
        if (!codeError.equals("")) {
            errors.add(codeError);
        }

        //氏名のチェック
        String nameError = validateName(ev.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        //パスワードのチェック
        String passError = validatePassword(ev.getPassword(), passwordCheckFlag);
        if (!passError.equals("")) {
            errors.add(passError);
        }

        return errors;
    }


}
*/