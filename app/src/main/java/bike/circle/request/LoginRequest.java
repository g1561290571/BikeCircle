package bike.circle.request;

import org.json.JSONException;
import org.json.JSONObject;

import bike.circle.activities.BaseActivity;
import bike.circle.constant.HttpConnectURL;

/**
 * Created by MrH on 2017/5/7.
 */

public class LoginRequest extends BaseRequest{
    private JSONObject jsonObject = new JSONObject();

    public LoginRequest(String loginName ,String password) {
        try {
            jsonObject.put("loginName" ,loginName);
            jsonObject.put("password" , password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String para() {
        return jsonObject.toString();
    }

    @Override
    protected String url() {
        return HttpConnectURL.Login;
    }
}
