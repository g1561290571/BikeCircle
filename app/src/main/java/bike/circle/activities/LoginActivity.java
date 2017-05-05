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

import com.bumptech.glide.Glide;

import bike.circle.app.R;

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

    private Onclick onclick;
//    private LineOnFocus lineOnFocus;

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
//        mLoginName.setOnFocusChangeListener(lineOnFocus);
//        mPassword.setOnFocusChangeListener(lineOnFocus);
        mLogin.setOnClickListener(onclick);
    }

    @Override
    protected void service() {

    }

    @Override
    protected void init() {
        onclick = new Onclick();
//        lineOnFocus = new LineOnFocus();
    }

    private class Onclick implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:startActivity(MainActivity.getIntent(LoginActivity.this));break;

                case R.id.forget_password:;break;

                case R.id.register:startActivity(RegisterActivity.getIntent(LoginActivity.this));break;
            }
        }
    }

//    private class LineOnFocus implements View.OnFocusChangeListener{
//
//        @Override
//        public void onFocusChange(View v, boolean hasFocus) {
//            if(hasFocus) changeToFocus(v.getId());
//            else changeToLoseFocus(v.getId());
//        }
//
//        private void changeToFocus(int viewId){
//            switch (viewId){
//                case R.id.login_name:
//                    mLoginLine.setImageResource(R.color.colorAccent);
//                    mLoginName.setHintTextColor(getResources().getColor(R.color.colorAccent));
//                    break;
//                case R.id.password:
//                    mPasswordLine.setImageResource(R.color.colorAccent);
//                    mLoginName.setHintTextColor(getResources().getColor(R.color.colorAccent));
//                    break;
//            }
//        }
//
//        private void changeToLoseFocus(int viewId){
//            switch (viewId){
//                case R.id.login_name:
//                    mLoginLine.setImageResource(R.color.colorPrimary);
//                    mLoginName.setHintTextColor(getResources().getColor(R.color.colorPrimary));
//                    break;
//                case R.id.password:
//                    mPasswordLine.setImageResource(R.color.colorPrimary);
//                    mPassword.setHintTextColor(getResources().getColor(R.color.colorPrimary));
//                    break;
//            }
//        }
//    }
}
