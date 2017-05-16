package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import bike.circle.app.R;

public class MyActionActivity extends BaseActivity implements View.OnClickListener{

    private FrameLayout frameLayout;

    public static Intent getIntent(Context context){
        return new Intent(context,MyActionActivity.class);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_action;
    }

    @Override
    protected void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.test);
    }

    @Override
    protected void bindEvent() {
        frameLayout.setOnClickListener(this);
    }

    @Override
    protected void service() {

    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test:actionDialog();break;
        }
    }

    private void actionDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActionActivity.this);
        builder.setView(R.layout.activity_my_action_dialog).create().show();
    }
}
