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

import bike.circle.app.R;


public class RidingFriendsFragment extends Fragment {

    private Toolbar toolbar;
    private View view ;

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
        }
        return view;
    }

    private void initView() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setToolBar();
    }

    private void setToolBar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu_friend));
    }

}
