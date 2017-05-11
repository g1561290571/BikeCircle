package bike.circle.dto;

/**
 * Created by MrH on 2017/5/7.
 */

public class UserLogin {
    private String loginName;
    private String password;
    private boolean isOut;

    public UserLogin(String loginName, String password, boolean isOut) {
        this.loginName = loginName;
        this.password = password;
        this.isOut = isOut;
    }

    public UserLogin() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }
}
