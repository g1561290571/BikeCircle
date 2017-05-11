package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bike.circle.app.R;

/**
 * Created by MrH on 2017/5/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getContentViewId();
    protected abstract void initView();
    protected abstract void bindEvent();
    protected abstract void service();
    protected abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView();
        init();
        bindEvent();
        service();
    }
}
