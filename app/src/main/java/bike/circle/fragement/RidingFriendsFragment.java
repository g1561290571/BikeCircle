package bike.circle.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bike.circle.app.R;


public class RidingFriendsFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_riding_friends, container, false);
    }

}
