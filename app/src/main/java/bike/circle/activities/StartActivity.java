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

import java.io.File;
import java.io.InputStream;

import bike.circle.app.R;
import bike.circle.constant.HttpConnectURL;
import bike.circle.request.HttpFileNetUtil;
import bike.circle.util.ToastUtil;

public class StartActivity extends BaseActivity {


    @Override
    protected int getContentViewId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {

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
