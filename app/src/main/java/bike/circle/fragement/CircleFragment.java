package bike.circle.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bike.circle.activities.MainActivity;
import bike.circle.adapter.TravelNoteAdapter;
import bike.circle.app.R;
import bike.circle.dto.TravelNoteZoom;
import bike.circle.pool.NetResPool;
import bike.circle.pool.StaticPool;

public class CircleFragment extends Fragment {
    private View view;
    private Toolbar toolbar;
    private RecyclerView mFriendTravel;
    private LinearLayoutManager mLinearLayoutManager;
    private List<TravelNoteZoom> mHotTravelNotes;
    private TravelNoteAdapter mTravelNoteAdapter;

    public CircleFragment() {
    }

    public static CircleFragment newInstance() {
        CircleFragment fragment = new CircleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_circle, container, false);
        if(getActivity()!=null){
            initView();
        }
        return view;
    }

    private void initView() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mFriendTravel = (RecyclerView) view.findViewById(R.id.firend_travel);
        mHotTravelNotes = new ArrayList<>();
        mTravelNoteAdapter = new TravelNoteAdapter( mHotTravelNotes , LayoutInflater.from(getActivity()));
        mFriendTravel.setAdapter(mTravelNoteAdapter);
        mFriendTravel.setLayoutManager(mLinearLayoutManager);
        new StaticPool(getActivity()).getHotTravelNote(new NetResPool.PoolCallBack() {
            @Override
            public void before() {

            }
            @Override
            public void after(Object res) {
                mHotTravelNotes.addAll((Collection<? extends TravelNoteZoom>) res);
                mTravelNoteAdapter.notifyDataSetChanged();
            }

        });
        setToolBar();
    }

    private void setToolBar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu_friend));
    }


}
