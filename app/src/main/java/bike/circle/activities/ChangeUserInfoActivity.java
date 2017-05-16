package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bike.circle.app.R;

public class ChangeUserInfoActivity extends BaseActivity implements View.OnClickListener{


    public static Intent getIntent(Context context){
        return new Intent(context,ChangeUserInfoActivity.class);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_user_info;
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

    @Override
    public void onClick(View v) {

    }
}
