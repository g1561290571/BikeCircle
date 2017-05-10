package bike.circle.request;

import com.google.gson.Gson;

import org.json.JSONException;

import bike.circle.constant.HttpConnectURL;
import bike.circle.dto.ResString;

/**
 * Created by MrH on 2017/5/9.
 */

public class CheckLoginNameRequest extends BaseRequest{
    private String loginName;

    public CheckLoginNameRequest(String loginName) {
        this.loginName = loginName;
    }

    @Override
    protected String para() {
        Gson gson = new Gson();
        ResString resString = new ResString(loginName);
        return gson.toJson(resString);
    }

    @Override
    protected String url() {
        return HttpConnectURL.CHECKLOGINANMPEPEAT;
    }
}
