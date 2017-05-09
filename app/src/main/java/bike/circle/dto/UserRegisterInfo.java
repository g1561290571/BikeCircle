package bike.circle.dto;

/**
 * Created by MrH on 2017/5/3.
 */

public class UserRegisterInfo {
    private String loginName;
    private String nikeName;
    private String sex;
    private String password;
    private String imagePath;
    private String telephone;

    public UserRegisterInfo() {
    }

    public UserRegisterInfo(String loginName, String nikeName, String sex, String password, String imagePath, String telephone) {
        this.loginName = loginName;
        this.nikeName = nikeName;
        this.sex = sex;
        this.password = password;
        this.imagePath = imagePath;
        this.telephone = telephone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
