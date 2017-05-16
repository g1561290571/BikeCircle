package bike.circle.activities;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import bike.circle.app.R;

public class StartActionActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mBack;

    private Button mCreate;

    private Button mJoin;

    public static Intent getIntent(Context context){
        return new Intent(context,StartActionActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_start_action;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mCreate = (Button) findViewById(R.id.establish);
        mJoin = (Button) findViewById(R.id.join);
    }

    @Override
    protected void bindEvent() {

        mBack.setOnClickListener(this);
        mCreate.setOnClickListener(this);
        mJoin.setOnClickListener(this);
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
            case R.id.back:finish();break;
            case R.id.establish:startActivity(CreateActionActivity.getIntent(this));break;
            case R.id.join:startActivity(SearchActivity.getIntent(this));break;
        }
    }
}
