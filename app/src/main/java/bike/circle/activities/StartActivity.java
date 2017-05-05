package bike.circle.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bike.circle.app.R;

public class StartActivity extends BaseActivity {


    @Override
    protected int getContentViewId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void service() {
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    startActivity(LoginActivity.getIntent(StartActivity.this));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void init() {

    }
}
