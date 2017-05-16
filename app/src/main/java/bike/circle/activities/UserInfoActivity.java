package bike.circle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bike.circle.app.R;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button mChange;
    private TextView mTitle;
    private AppBarLayout appBarLayout;
    private TextView mNikeName;
    private ImageView mPortraitBig;
    private NestedScrollView nestedScrollView;
    private LinearLayout mAppbarGroup;
    private LinearLayout main;
    public static Intent getIntent(Context context){
        return new Intent(context,UserInfoActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initView() {
        mChange = (Button) findViewById(R.id.three_function_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        mNikeName = (TextView) findViewById(R.id.nikename);
        mPortraitBig = (ImageView) findViewById(R.id.portrait_big);
        mTitle = (TextView) findViewById(R.id.title);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nes);
        mAppbarGroup = (LinearLayout) findViewById(R.id.appbar_group);
        main = (LinearLayout) findViewById(R.id.main);
        initActionBar();
    }

    private void initActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            private FrameLayout.LayoutParams lp;
            private int topMargin;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if(lp == null){
                    lp = (FrameLayout.LayoutParams) main.getLayoutParams();
                    topMargin = lp.topMargin;
                }
                if (getSupportActionBar().getHeight() - appBarLayout.getHeight() == verticalOffset) {
                    mTitle.setText("我的信息");
                    mChange.setVisibility(View.INVISIBLE);
                    lp.topMargin = 0;
                    main.setLayoutParams(lp);
                    mAppbarGroup.setVisibility(View.INVISIBLE);
                }else if(getSupportActionBar().getHeight() <= appBarLayout.getHeight()){
                    mTitle.setText("");
                    lp.topMargin = topMargin;
                    main.setLayoutParams(lp);
                    mAppbarGroup.setVisibility(View.VISIBLE);
                    mChange.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void bindEvent() {
        mChange.setOnClickListener(this);
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
            case R.id.three_function_button:startActivity(ChangeUserInfoActivity.getIntent(UserInfoActivity.this));
        }
    }
}
