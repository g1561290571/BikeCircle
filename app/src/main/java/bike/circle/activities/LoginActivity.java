package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import bike.circle.app.R;
import bike.circle.dto.UserLogin;
import bike.circle.request.BaseRequest;
import bike.circle.request.LoginRequest;
import bike.circle.util.ToastUtil;
import bike.circle.util.UserLoginPreferences;

public class LoginActivity extends BaseActivity {

    private EditText mLoginName;
    private EditText mPassword;
    private TextView mForgetPassword;
    private TextView mRegister;
    private Button mLogin;
    private ImageView mQQLogin;
    private ImageView mWechatLogin;
    private ImageView mWeiboLogin;
    private ImageView mLoginImage;
    private ImageView mPasswordImage;
    private ImageView mLoginLine;
    private ImageView mPasswordLine;
    private UserLogin userLogin;
    private UserLoginPreferences userLoginPreferences;

    private Onclick onclick;
    private LineOnFocus lineOnFocus;

    public static Intent getIntent(Context context){
        return new Intent(context,LoginActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mLoginName = (EditText) findViewById(R.id.login_name);
        mPassword = (EditText) findViewById(R.id.password);
        mForgetPassword = (TextView) findViewById(R.id.forget_password);
        mRegister = (TextView) findViewById(R.id.register);
        mLogin = (Button) findViewById(R.id.login);
        mQQLogin = (ImageView) findViewById(R.id.qq_login);
        mWechatLogin = (ImageView) findViewById(R.id.wechat_login);
        mWeiboLogin = (ImageView) findViewById(R.id.weibo_login);
        mLoginImage = (ImageView) findViewById(R.id.user_image);
        mPasswordImage = (ImageView) findViewById(R.id.password_image);
        mLoginLine = (ImageView) findViewById(R.id.user_line);
        mPasswordLine = (ImageView) findViewById(R.id.password_line);
    }



    @Override
    protected void bindEvent() {
        mLoginName.setOnFocusChangeListener(lineOnFocus);
        mPassword.setOnFocusChangeListener(lineOnFocus);
        mLogin.setOnClickListener(onclick);
        mRegister.setOnClickListener(onclick);
        mForgetPassword.setOnClickListener(onclick);
        userLoginPreferences = new UserLoginPreferences(LoginActivity.this);
    }

    @Override
    protected void service() {
        isLogin();
    }

    private void isLogin() {
        UserLogin userLogin = userLoginPreferences.getLogin();
        mLoginName.setText(userLogin.getLoginName());
        mPassword.setText(userLogin.getPassword());
        if(!userLogin.isOut())
            login();
    }

    private void login(){
        String loginName = mLoginName.getText().toString();
        String password = mPassword.getText().toString();
        LoginRequest loginRequest = new LoginRequest(loginName , password);
        loginRequest.connect(new BaseRequest.RequestCallback() {
            @Override
            public void before() {

            }

            @Override
            public void success(JSONObject res) {
                try {
                    if(res.has("res")) {
                        if (res.getBoolean("res"))loginSuccess();
                        else loginFailed();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(int type) {
                ToastUtil.makeLongText(LoginActivity.this , "请检查网络链接");
            }
        });
    }
    private void loginSuccess(){
        saveLogin();
        startActivity(MainActivity.getIntent(LoginActivity.this));
        finish();
    }
    private void saveLogin(){
        UserLogin userLogin = new UserLogin();
        userLogin.setLoginName(mLoginName.getText().toString());
        userLogin.setPassword(mPassword.getText().toString());
        userLoginPreferences.addUserLogin(userLogin);
    }

    private void loginFailed(){;
        ToastUtil.makeLongText(LoginActivity.this , "登陆失败请检查账户名密码是否正确");
    }
    @Override
    protected void init() {
        onclick = new Onclick();
        lineOnFocus = new LineOnFocus();
    }

    private class Onclick implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:
                    startActivity(MainActivity.getIntent(LoginActivity.this));
                    break;
                case R.id.forget_password:
                    startActivity(ForgetPasswordActivity.getIntent(LoginActivity.this));
                    break;

                case R.id.register:startActivity(RegisterActivity.getIntent(LoginActivity.this));break;
            }
        }
    }


    private class LineOnFocus implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus) changeToFocus(v.getId());
            else changeToLoseFocus(v.getId());
        }

        private void changeToFocus(int viewId){
            switch (viewId){
                case R.id.login_name:
                    mLoginLine.setImageResource(R.color.colorAccent);
                    break;
                case R.id.password:
                    mPasswordLine.setImageResource(R.color.colorAccent);
                    break;
            }
        }

        private void changeToLoseFocus(int viewId){
            switch (viewId){
                case R.id.login_name:
                    mLoginLine.setImageResource(R.color.colorPrimary);
                    break;
                case R.id.password:
                    mPasswordLine.setImageResource(R.color.colorPrimary);
                    break;
            }
        }
    }
}
