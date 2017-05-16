package bike.circle.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import bike.circle.activities.MyActionActivity;
import bike.circle.activities.MyTravelNoteActivity;
import bike.circle.app.R;
import bike.circle.fragement.tab.CircleTab;
import bike.circle.fragement.tab.FriendTab;
import bike.circle.fragement.tab.MessageTab;


public class RidingFriendsFragment extends Fragment implements View.OnClickListener{

    private Toolbar toolbar;
    private View view ;

    private FrameLayout mContainer;

    private Button mMessage;
    private Button mCircle;
    private Button mFriend;


    private View mCircleView;
    private View mMessageView;
    private View mFriendView;

    public RidingFriendsFragment() {

    }

    public static RidingFriendsFragment newInstance() {
        RidingFriendsFragment fragment = new RidingFriendsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_riding_friends, container, false);
        if(getActivity()!=null){
            initView();
            bindEvent();
        }
        return view;
    }

    private void initView() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setToolBar();

        mMessage = (Button) view.findViewById(R.id.message);
        mFriend = (Button) view.findViewById(R.id.friend);
        mCircle = (Button) view.findViewById(R.id.circle);
        mContainer = (FrameLayout) view.findViewById(R.id.container);
        changeToMessage();
    }

    private void bindEvent() {

        mMessage.setOnClickListener(this);
        mFriend.setOnClickListener(this);
        mCircle.setOnClickListener(this);
    }

    private void setToolBar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu_friend));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message:changeToMessage();break;
            case R.id.friend:changeToFriend();break;
            case R.id.circle:changeToCircle();break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCircleView = null;
        mMessageView = null;
        mFriendView = null;
    }

    private void changeToMessage(){
        mContainer.removeAllViews();
        if(mMessageView == null)
            mMessageView = initChangeToMessage();
        mContainer.addView(mMessageView);
        ChangeButtonBackground(mMessage.getId());

    };


    private View initChangeToMessage(){
        View view = new MessageTab(getActivity()).getView();
        return view;
    }

    private void changeToCircle(){
        mContainer.removeAllViews();
        if(mCircleView == null)
            mCircleView = initChangeToCircle();
        mContainer.addView(mCircleView);
        ChangeButtonBackground(mCircle.getId());
    };

    private View initChangeToCircle(){
        View view = new CircleTab(getActivity()).getView();
        return view;
    }

    private void changeToFriend(){
        mContainer.removeAllViews();
        if(mFriendView == null)
            mFriendView = initChangeToFriend();
        mContainer.addView(mFriendView);
        ChangeButtonBackground(mFriend.getId());

    }

    private View initChangeToFriend(){
        View view = new FriendTab(getActivity()).getView();
        return view;
    }

    private void ChangeButtonBackground(int id){
        if(id == R.id.message){
            mMessage.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            mMessage.setTextColor(getResources().getColor(R.color.colorPrimary));
            mCircle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mCircle.setTextColor(getResources().getColor(R.color.colorWhite));
            mFriend.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mFriend.setTextColor(getResources().getColor(R.color.colorWhite));
        }else if(id == R.id.circle){
            mCircle.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            mCircle.setTextColor(getResources().getColor(R.color.colorPrimary));
            mMessage.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mMessage.setTextColor(getResources().getColor(R.color.colorWhite));
            mFriend.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mFriend.setTextColor(getResources().getColor(R.color.colorWhite));

        }else if(id == R.id.friend){
            mFriend.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            mFriend.setTextColor(getResources().getColor(R.color.colorPrimary));
            mCircle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mCircle.setTextColor(getResources().getColor(R.color.colorWhite));
            mMessage.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mMessage.setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
}
