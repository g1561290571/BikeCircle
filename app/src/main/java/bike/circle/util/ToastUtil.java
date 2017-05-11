package bike.circle.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by MrH on 2017/5/8.
 */

public class ToastUtil {
    private static Toast toast;

    public static void makeLongText(Context context , String text){
        if(toast == null)
            toast = Toast.makeText(context,text,Toast.LENGTH_LONG);
        else
            toast.setText(text);
        toast.show();
    }

    public static void makeShortText(Context context , String text){
        if(toast ==null )
            toast = Toast.makeText(context , text ,Toast.LENGTH_SHORT);
        else
            toast.setText(text);
        toast.show();
    }

    public void dd(){

    }
}
