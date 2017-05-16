package bike.circle.util;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bike.circle.activities.RegisterActivity;

/**
 * Created by G on 2017/5/16.
 */

public class RegularUtil {

    public static boolean checkName(RegisterActivity context,EditText mUserLoginName) {
        if (TextUtils.isEmpty((CharSequence) mUserLoginName) || mUserLoginName.length() < 3 || mUserLoginName.length() > 16 || !nameFormat(mUserLoginName)) {
            return false;
        }
        return true;
    }

    public static boolean nameFormat(EditText mUserLoginName) {
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5A-Za-z0-9_]{3,16}$");
        Matcher mc = pattern.matcher((CharSequence) mUserLoginName);
        return mc.matches();
    }
    public static boolean checkPassword(RegisterActivity context, String password) {
        if (!passwordFormat(password)) {
            Toast.makeText(context, "密码格式是6-15位英文字符、数字", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private static boolean passwordFormat(String password) {
        Pattern pattern = Pattern.compile("^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,15}$");
        Matcher mc = pattern.matcher(password);
        return mc.matches();
    }
    public static boolean checkNumber(RegisterActivity context,String number){
        if(!numberFormat(number)){
            Toast.makeText(context, "电话号码有误！", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public static boolean numberFormat(String number) {
        Pattern pattern = Pattern.compile("^[1][3,5,8][0-9]{9}$");
        Matcher mc = pattern.matcher(number);
        return mc.matches();
    }


}
