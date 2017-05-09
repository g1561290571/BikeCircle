package bike.circle.pool;

import android.widget.ImageView;

import java.util.List;

import bike.circle.request.BaseRequest;
import bike.circle.dto.TravelNoteZoom;

/**
 * Created by MrH on 2017/5/5.
 */

public interface NetResPool {
    interface PoolCallBack{
        void before();
        void after(Object obj);
    }
    List<TravelNoteZoom> getHotTravelNote(PoolCallBack poolCallBack);
    List<ImageView> getHomeBanner(PoolCallBack poolCallBack);
}
