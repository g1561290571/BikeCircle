package bike.circle.fragement.tab;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import bike.circle.activities.ChatActivity;
import bike.circle.app.R;

/**
 * Created by MrH on 2017/5/15.
 */

public class MessageTab extends BaseTab {

    private FrameLayout frameLayout;

    public MessageTab(Context context) {
        super(context);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_riding_friend_message;
    }

    @Override
    protected void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.s);
    }

    @Override
    protected void bindEvent() {
        frameLayout.setOnClickListener(this);
    }

    @Override
    protected void service() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.s:startActivity(ChatActivity.getIntent(getContext()));break;
        }
    }
}
