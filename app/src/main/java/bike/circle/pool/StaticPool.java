package bike.circle.pool;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import bike.circle.request.BaseRequest;
import bike.circle.app.R;
import bike.circle.dto.TravelNoteZoom;

/**
 * Created by MrH on 2017/5/5.
 */

public class StaticPool implements NetResPool {
    private List<TravelNoteZoom> travelNoteZooms ;
    private List<ImageView> benners;
    private Context context;

    public StaticPool(Context context) {
        this.context = context;
    }


    @Override
    public List<TravelNoteZoom> getHotTravelNote(PoolCallBack poolCallBack) {
        travelNoteZooms = new ArrayList<>();
        travelNoteZooms.add(new TravelNoteZoom("tefdst","aas"+"ssssssssssss",
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssss" +
                        "sssssssssssss" +
                        "ssssssssssssssssss" +
                        "sssssssssssssssssss",1L,1040,10600,new String[]{"",""}));
        travelNoteZooms.add(new TravelNoteZoom("tesdfst","aas"+"ssssssssssss",
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssss" +
                        "sssssssssssss" +
                        "ssssssssssssssssss" +
                        "sssssssssssssssssss",1L,1000,10000,new String[]{"",""}));
        travelNoteZooms.add(new TravelNoteZoom("tesdfst","aas"+"ssssssssssss",
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssss" +
                        "sssssssssssss" +
                        "ssssssssssssssssss" +
                        "sssssssssssssssssss",1L,12000,100,new String[]{"",""}));
        travelNoteZooms.add(new TravelNoteZoom("tesfst","aas"+"ssssssssssss",
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssss" +
                        "sssssssssssss" +
                        "ssssssssssssssssss" +
                        "sssssssssssssssssss",1L,1030,10000,new String[]{"",""}));
        travelNoteZooms.add(new TravelNoteZoom("tesdst","aas"+"ssssssssssss",
                "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssss" +
                        "sssssssssssss" +
                        "ssssssssssssssssss" +
                        "sssssssssssssssssss",1L,1700,10070,new String[]{"",""}));
//        requestCallback.success(travelNoteZooms);
        poolCallBack.after(travelNoteZooms);
        return travelNoteZooms;
    }

    @Override
    public List<ImageView> getHomeBanner(PoolCallBack poolCallBack) {
        benners = new ArrayList<>();
        ImageView imageView = new ImageView(context);
        ImageView imageViewTwo = new ImageView(context);
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.main_home_banner));
        imageViewTwo.setImageDrawable(context.getResources().getDrawable(R.drawable.main_home_banner));
        benners.add(imageView);
        benners.add(imageViewTwo);
//        requestCallback.success(benners);
        poolCallBack.after(benners);
        return benners;
    }
}
