package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import bike.circle.app.R;

public class RankActivity extends BaseActivity {


    public static Intent getIntent(Context context){
        return new Intent(context,RankActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_rank;
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
