package bike.circle.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Permission;
import java.util.UUID;

import bike.circle.app.R;
import bike.circle.constant.HttpConnectURL;
import bike.circle.dto.UserRegisterInfo;
import bike.circle.request.BaseRequest;
import bike.circle.request.HttpFileNetUtil;
import bike.circle.request.RegisterRequest;
import bike.circle.util.ToastUtil;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    private ImageView mPortrait;
    private ImageView mBack;
    private RadioButton mMale;
    private RadioButton mFemale;
    private Button mButton;
    private EditText mUserLoginName;
    private EditText mUserNikeName;
    private EditText mPassword;
    private EditText mPhone;

    private UserRegisterInfo mRegister;
    private HttpFileNetUtil httpFileNetUtil;


    private Uri imageUri;

    public static Intent getIntent(Context context){

        return new Intent(context,RegisterActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mPortrait = (ImageView) findViewById(R.id.portrait);
        mBack =  (ImageView) findViewById(R.id.back);
        mMale = (RadioButton) findViewById(R.id.male);
        mFemale = (RadioButton) findViewById(R.id.female);
        mUserLoginName = (EditText) findViewById(R.id.loginName);
        mUserNikeName = (EditText) findViewById(R.id.nikeName);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone);
        mButton= (Button) findViewById(R.id.register);
    }

    @Override
    protected void bindEvent() {
        mPortrait.setOnClickListener(RegisterActivity.this);
        mBack.setOnClickListener(RegisterActivity.this);
        mButton.setOnClickListener(RegisterActivity.this);
    }

    @Override
    protected void service() {

    }

    @Override
    protected void init() {
        mRegister = new UserRegisterInfo();
        mRegister.setImagePath(new String());
        httpFileNetUtil = new HttpFileNetUtil();
    }

    private File getOutSideFile(){
        String name = UUID.randomUUID().toString();
        File file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File img = null;
        try {
            img = File.createTempFile(name,".jpg",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private void takePhoto(){
        File img = getOutSideFile();
        if(img == null ) return;
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(img);
        } else {
            imageUri = FileProvider.getUriForFile(RegisterActivity.this, "bike.circle.fileprovider", img);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }


    private void choosePhoto(){
        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            openAlbum();
        }
    }


    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        mPortrait.setImageBitmap(bitmap);
                        upLoadImage(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    public File saveBitmap(Bitmap mBitmap){
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file = null;
        try {
            file = File.createTempFile(
                    UUID.randomUUID().toString(),
                    ".jpeg",
                    storageDir
            );

            FileOutputStream out=new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mPortrait.setImageBitmap(bitmap);
            upLoadImage(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void upLoadImage(Bitmap bitmap){
        File file = saveBitmap(bitmap);
        httpFileNetUtil.conn(file, HttpConnectURL.UPPORTRAIT , new HttpFileNetUtil.FileCallback() {
            @Override
            public void before() {

            }

            @Override
            public void success(Object res) {
                try {
                    JSONObject jsonObject = new JSONObject(res.toString());
                    mRegister .setImagePath(jsonObject.getString("res"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(int type) {
                ToastUtil.makeLongText(RegisterActivity.this,"无法设置头像,请检查网络链接");
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.portrait:setPortrait();break;
            case R.id.back:finish();
                    break;
            case R.id.register:register();

        }
    }

    private void register(){
        mRegister.setLoginName(mUserLoginName.getText().toString());
        mRegister.setNikeName(mUserNikeName.getText().toString());
        mRegister.setPassword(mPassword.getText().toString());
        mRegister.setSex("男");
        mRegister.setTelephone(mPhone.getText().toString());

        new RegisterRequest(mRegister).connect(new BaseRequest.RequestCallback() {
            @Override
            public void before() {

            }

            @Override
            public void success(JSONObject res) {
                try {
                    if(res.getBoolean("res"))
                        ToastUtil.makeLongText(RegisterActivity.this,"注册成功");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(int type) {

            }
        });
    }

    private void setPortrait(){
        showPopWindow();
    }

    private void showPopWindow(){

        View popView = View.inflate(this,R.layout.activity_register_popwindow,null);
        Button album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button cancel = (Button) popView.findViewById(R.id.btn_pop_cancel);

        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
                popupWindow.dismiss();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
                popupWindow.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);
    }
}
