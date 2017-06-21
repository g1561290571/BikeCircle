package bike.circle.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import bike.circle.app.R;
import bike.circle.constant.HttpConnectURL;
import bike.circle.dto.UserRegisterInfo;
import bike.circle.request.BaseRequest;
import bike.circle.request.CheckLoginNameRequest;
import bike.circle.request.HttpFileNetUtil;
import bike.circle.request.RegisterRequest;
import bike.circle.util.ToastUtil;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends BaseActivity implements View.OnClickListener , View.OnFocusChangeListener{

    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;


    private CircleImageView mPortrait;
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
    private Bitmap mPortraitBitmap;
    private File mPortraitFile;

    public static Intent getIntent(Context context){

        return new Intent(context,RegisterActivity.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mPortrait = (CircleImageView) findViewById(R.id.portrait);
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
        mUserLoginName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String ll = mUserLoginName.getText().toString();
                    if (ll.equals("")){
                        mUserLoginName.setError("请输入");
                        Message msg = new Message();
                        msg.obj = "f";
                        userLoginNameHandler.sendMessage(msg);
                    }
                    else if( mUserLoginName.length() < 3 || mUserLoginName.length() > 16){
                        mUserLoginName.setError("账户名不符合规范，3-16个中英文字符、数字");
                        Message msg = new Message();
                        msg.obj = "f";
                        userLoginNameHandler.sendMessage(msg);
                    }
                }
            }
        });
        mUserNikeName.setOnFocusChangeListener(this);

    }

    Handler userLoginNameHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            String msgobj=(String) msg.obj;

            switch (msgobj){
                case "t":
                    mUserLoginName.requestFocus();
                    break;
            }
            if (msgobj.equals("f")) {

            }

            super.handleMessage(msg);
        }
    };


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
            img = File.createTempFile(name,".jpeg",file);
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
                        cutPhoto(imageUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    cutPhoto(uri);
                }
                break;
            case UCrop.REQUEST_CROP:
                if(resultCode == RESULT_OK){
                    final Uri resultUri = UCrop.getOutput(data);
                    try {
                        mPortraitBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    upLoadImage(mPortraitFile);
                }
            default:
                break;
        }
    }

    private void cutPhoto(Uri uri){
        mPortraitFile = new File(getCacheDir(), "portrait.jpeg");
        Uri mDestinationUri = Uri.fromFile(mPortraitFile);
        UCrop.of(uri, mDestinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(512, 512)
                .start(RegisterActivity.this);
    }


    private void upLoadImage(File file){
        httpFileNetUtil.conn(file, HttpConnectURL.UPPORTRAIT , new HttpFileNetUtil.FileCallback() {
            @Override
            public void before() {

            }

            @Override
            public void success(Object res) {
                try {
                    JSONObject jsonObject = new JSONObject(res.toString());
                    mRegister .setImagePath(jsonObject.getString("res"));
                    mPortrait.setImageBitmap(mPortraitBitmap);
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
            case R.id.register:register();break;

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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.loginName:checkLoginName();break;
            case R.id.login_name:
                if(!hasFocus){
                    String ll = mUserNikeName.getText().toString();
                    if (ll.equals("")){
                        mUserNikeName.setError("请输入");
                        Message msg = new Message();
                        msg.obj = "f";
                        userLoginNameHandler.sendMessage(msg);
                    }
                    else if( mUserNikeName.length() < 3 || mUserNikeName.length() > 16){
                        mUserNikeName.setError("账户名不符合规范，3-16个中英文字符、数字");
                        Message msg = new Message();
                        msg.obj = "f";
                        userLoginNameHandler.sendMessage(msg);
                    }
                };break;
        }
    }

    private void checkLoginName(){
        String loginName = mUserLoginName.getText().toString();
        if(loginName.length()>0){
            new CheckLoginNameRequest(loginName).connect(new BaseRequest.RequestCallback() {
                @Override
                public void before() {

                }

                @Override
                public void success(JSONObject res) {
                    try {
                        if(!res.getBoolean("res"))
                            ToastUtil.makeLongText(RegisterActivity.this,"重名");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fail(int type) {

                }
            });
        }
    }
}
