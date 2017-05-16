package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bike.circle.app.R;

public class SearchActivity extends BaseActivity {

    public static Intent getIntent(Context context){
        return new Intent(context,SearchActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_serach;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void service() {

    }

    @Override
    protected void init() {

    }

}
