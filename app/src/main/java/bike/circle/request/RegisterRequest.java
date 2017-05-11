package bike.circle.request;

import com.google.gson.Gson;

import bike.circle.dto.UserRegisterInfo;

import static bike.circle.constant.HttpConnectURL.REGISTER;

/**
 * Created by MrH on 2017/5/8.
 */

public class RegisterRequest extends BaseRequest {
    private UserRegisterInfo userRegisterInfo;

    public RegisterRequest(UserRegisterInfo userRegisterInfo) {
        this.userRegisterInfo = userRegisterInfo;
    }

    @Override
    protected String para() {
        Gson gson = new Gson();
        return gson.toJson(userRegisterInfo);
    }

    @Override
    protected String url() {
        return REGISTER;
    }
}
