package bike.circle.request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MrH on 2017/5/5.
 */

    public abstract class BaseRequest {

    protected HttpNet httpNet  = new HttpNet();

    public interface RequestCallback{
        void before();
        void success(JSONObject res);
        void fail(int type);
    };

    public void connect(final RequestCallback requestCallback){
        requestCallback.before();
        httpNet.conn(new HttpNet.HttpNetCallback() {
            @Override
            public void success(String res) {
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    requestCallback.success(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(int failType) {
                requestCallback.fail(failType);
            }
        } , para(),url());
    }

    protected abstract String para();
    protected abstract String url();
}
