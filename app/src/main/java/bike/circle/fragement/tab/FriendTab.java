package bike.circle.fragement.tab;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import bike.circle.activities.FriendInfoActivity;
import bike.circle.activities.NewFriendsActivity;
import bike.circle.app.R;

/**
 * Created by MrH on 2017/5/15.
 */

public class FriendTab extends BaseTab {

    private LinearLayout mFriend;
    private LinearLayout mNewFriend;


    public FriendTab(Context context) {
        super(context);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_riding_frinends_friend ;
    }

    @Override
    protected void initView() {
        mFriend = (LinearLayout) findViewById(R.id.friend);
        mNewFriend = (LinearLayout) findViewById(R.id.new_friend);
    }

    @Override
    protected void bindEvent() {
        mFriend.setOnClickListener(this);
        mNewFriend.setOnClickListener(this);
    }

    @Override
    protected void service() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.friend:startActivity(FriendInfoActivity.getIntent(getContext()));break;
            case R.id.new_friend:startActivity(NewFriendsActivity.getIntent(getContext()));break;
        }
    }
}
