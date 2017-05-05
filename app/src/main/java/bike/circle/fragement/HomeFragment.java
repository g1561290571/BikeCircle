package bike.circle.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bike.circle.app.R;
import bike.circle.comment.TravelNoteAdapter;
import bike.circle.comment.TravelNoteViewHolder;
import bike.circle.dto.TravelNoteZoom;
import bike.circle.pool.NetResPool;
import bike.circle.pool.StaticPool;

public class HomeFragment extends Fragment{

    private View view;
    private ViewPager mBanner;
    private RecyclerView mHotTravel;
    private NetResPool netResPool;
    private List<TravelNoteZoom> mHotTravelNotes;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        if(getActivity()!=null) {
            init();
            initView();
        }
        return view;
    }

    private void init() {
        netResPool = new StaticPool();
        mHotTravelNotes = netResPool.getHotTravelNote();
    }

    private void initView() {
//        mBanner = (ViewPager) view.findViewById(R.id.banner);
        mHotTravel = (RecyclerView) view.findViewById(R.id.hot_travel_note);
        mHotTravel.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHotTravel.setAdapter(new TravelNoteAdapter( mHotTravelNotes , LayoutInflater.from(getActivity())));

    }


}
