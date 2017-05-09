package bike.circle.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

import bike.circle.dto.UserLogin;

/**
 * Created by MrH on 2017/5/7.
 */

public class UserLoginPreferences {
    private SharedPreferences sharedPreferences;
    private Context context;


    public UserLoginPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("user_login",Context.MODE_PRIVATE);
    }

    public UserLogin getLogin(){
        UserLogin userLogin = new UserLogin();
        String uuid = UUID.randomUUID().toString();
        String userName = sharedPreferences.getString("userName",uuid);
        String password = sharedPreferences.getString("password",uuid);
        boolean isOut = sharedPreferences.getBoolean("isOut",true);
        userLogin.setOut(isOut);
        if(uuid.equals(userName)) {
            userLogin.setLoginName(new String());
            userLogin.setPassword(new String());
            return userLogin;
        }
        userLogin.setLoginName(userName);
        userLogin.setPassword(password);
        return userLogin;
    }

    public void addUserLogin(UserLogin userLogin){
        sharedPreferences.edit().
                putString("userName",userLogin.getLoginName()).
                putString("password",userLogin.getPassword()).
                putBoolean("isOut",false).
                commit();
    }

    public void outLogin(){
        sharedPreferences.edit().putBoolean("isOut",true);
    }
}
