package bike.circle.pool;

import java.util.ArrayList;
import java.util.List;

import bike.circle.dto.TravelNoteZoom;

/**
 * Created by MrH on 2017/5/5.
 */

public class StaticPool implements NetResPool {
    private List<TravelNoteZoom> travelNoteZooms ;
    @Override
    public List<TravelNoteZoom> getHotTravelNote() {
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
        return travelNoteZooms;
    }
}
