package bike.circle.fragement;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bike.circle.activities.RankActivity;
import bike.circle.activities.StartActionActivity;
import bike.circle.request.BaseRequest;
import bike.circle.app.R;
import bike.circle.adapter.BannerAdapter;
import bike.circle.adapter.MoreScrollListener;
import bike.circle.adapter.TravelNoteAdapter;
import bike.circle.dto.TravelNoteZoom;
import bike.circle.pool.NetResPool;
import bike.circle.pool.StaticPool;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private View view;
    private ViewPager mBanner;
    private RecyclerView mHotTravel;
    private NetResPool netResPool;
    private List<TravelNoteZoom> mHotTravelNotes;
    private BannerAdapter bannerAdapter;
    private List<ImageView> bannerImages;
    private LinearLayoutManager mLinearLayoutManager;
    private TravelNoteAdapter mTravelNoteAdapter;
    private ConstraintLayout mRank;
    private ConstraintLayout mStartActivity;
    private Toolbar toolbar;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        if(getActivity()!=null) {
            init();
            initView();
            bindEvent();
            addBanner();
            addDate();
        }
        return view;
    }

    private void bindEvent() {
        mHotTravel.addOnScrollListener(new MoreScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore() {
                netResPool.getHotTravelNote(new NetResPool.PoolCallBack() {
                    @Override
                    public void before() {

                    }

                    @Override
                    public void after(Object res) {
                        mHotTravelNotes.addAll((Collection<? extends TravelNoteZoom>) res);
                        mTravelNoteAdapter.notifyDataSetChanged();
                    }

                });
            }
        });
        mRank.setOnClickListener(this);
        mStartActivity.setOnClickListener(this);
    }

    private void init() {
        netResPool = new StaticPool(getActivity());
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mHotTravelNotes = new ArrayList<>();
        bannerImages = new ArrayList<>();
        mTravelNoteAdapter = new TravelNoteAdapter( mHotTravelNotes , LayoutInflater.from(getActivity()));

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setToolBar();
    }

    private void setToolBar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
    }

    private void addBanner(){

        bannerAdapter = new BannerAdapter(bannerImages);
        mBanner.setAdapter(bannerAdapter);
        setBannerWD();
    }

    private void setBannerWD() {
        ViewGroup.LayoutParams lp = mBanner.getLayoutParams();
        lp.width = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
        lp.height = Integer.parseInt(String.valueOf(Math.round(lp.width * 0.625)));
        mBanner.setLayoutParams(lp);
    }

    private void initView() {
        mBanner = (ViewPager) view.findViewById(R.id.banner);
        mHotTravel = (RecyclerView) view.findViewById(R.id.hot_travel_note);
        mHotTravel.setLayoutManager(mLinearLayoutManager);
        mHotTravel.setAdapter(mTravelNoteAdapter);
        mRank = (ConstraintLayout) view.findViewById(R.id.rank);
        mStartActivity = (ConstraintLayout) view.findViewById(R.id.start_activity);

    }

    private void addDate(){
         netResPool.getHotTravelNote(new NetResPool.PoolCallBack() {
            @Override
            public void before() {

            }
            @Override
            public void after(Object res) {
                mHotTravelNotes.addAll((Collection<? extends TravelNoteZoom>) res);
                mTravelNoteAdapter.notifyDataSetChanged();
            }

         });

        netResPool.getHomeBanner(new NetResPool.PoolCallBack() {
            @Override
            public void before() {

            }
            @Override
            public void after(Object res) {
                bannerImages.addAll((Collection<? extends ImageView>) res);
                bannerAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rank:startActivity(RankActivity.getIntent(getActivity()));break;
            case R.id.start_activity:startActivity(StartActionActivity.getIntent(getActivity()));break;
        }
    }



}
