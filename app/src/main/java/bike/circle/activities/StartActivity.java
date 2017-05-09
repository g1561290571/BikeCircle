package bike.circle.activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;

import bike.circle.app.R;
import bike.circle.constant.HttpConnectURL;
import bike.circle.request.HttpFileNetUtil;
import bike.circle.util.ToastUtil;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

public class StartActivity extends BaseActivity {

    private ImageView mBackground;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        mBackground = (ImageView) findViewById(R.id.backgroud);

        int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindow().getWindowManager().getDefaultDisplay().getHeight();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.start_image);

        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();

        BigDecimal ratio = new BigDecimal(width).divide(new BigDecimal(height),10,ROUND_HALF_DOWN);
        BigDecimal bRatio = new BigDecimal(bWidth).divide(new BigDecimal(bHeight),10,ROUND_HALF_DOWN);

        if(ratio.compareTo(bRatio) < 0){
            int cWidth = ratio.multiply(new BigDecimal(bHeight)).intValue();
            int hWidth = (bWidth-cWidth)/2;
            bitmap = Bitmap.createBitmap(bitmap, hWidth , 0 , cWidth -hWidth , bHeight, null,true);
            mBackground.setImageBitmap(bitmap);
        }else if(ratio.compareTo(bRatio) > 0){
            int cHeight = new BigDecimal(bWidth).divide(ratio , 10 ,ROUND_HALF_DOWN).intValue();
            int hHeight = (bHeight-cHeight)/2;
            bitmap = Bitmap.createBitmap(bitmap, 0 ,hHeight , bWidth , cHeight-hHeight, null,true);
            mBackground.setImageBitmap(bitmap);
        }


    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void service() {
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    startActivity(LoginActivity.getIntent(StartActivity.this));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void init() {

    }
}
