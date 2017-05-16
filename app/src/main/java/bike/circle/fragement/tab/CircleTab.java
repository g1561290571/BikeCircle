package bike.circle.fragement.tab;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import bike.circle.activities.FriendCircleActivity;
import bike.circle.activities.SearchActivity;
import bike.circle.app.R;

/**
 * Created by MrH on 2017/5/16.
 */

public class CircleTab extends BaseTab {

    private LinearLayout mJoinCircle;
    private LinearLayout mCircle;

    public CircleTab(Context context) {
        super(context);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_riding_friends_circle;
    }

    @Override
    protected void initView() {
        mJoinCircle = (LinearLayout) findViewById(R.id.join);
        mCircle = (LinearLayout) findViewById(R.id.mcirle);
    }

    @Override
    protected void bindEvent() {
        mJoinCircle.setOnClickListener(this);
        mCircle.setOnClickListener(this);
    }

    @Override
    protected void service() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.join:startActivity(SearchActivity.getIntent(getContext()));break;
            case R.id.mcirle:startActivity(FriendCircleActivity.getIntent(getContext()));break;
        }
    }
}
