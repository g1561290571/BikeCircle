package bike.circle.request;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;

import java.net.Socket;
import java.net.URL;
import java.util.UUID;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.path;

/**
 * Created by MrH on 2017/5/8.
 */

public class HttpFileNetUtil {

    private static final String BOUNDARY = "----WebKitFormBoundary3fMHCtmchjtDtVlg";
    private Handler handler = new Handler(Looper.getMainLooper());

    public interface FileCallback{
        void before();
        void success(Object res);
        void failed(int type);
    }
//
//    public void con(File file , String url , final FileCallback fileCallback){
//        conn(file, url, new FileCallback() {
//            @Override
//            public void before() {
//                fileCallback.before();
//            }
//
//            @Override
//            public void success(final Object res) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        fileCallback.success(res);
//                    }
//                });
//            }
//
//            @Override
//            public void failed(final int type) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        fileCallback.failed(type);
//                    }
//                });
//            }
//        });
//    }
//
//
//    private void conn(final File file , final String url , final FileCallback fileCallback){
//        fileCallback.before();
//        new Thread(){
//            public void run(){
//                try{
//                    HttpURLConnection httpURLConnection =initHttpURLConnection(url);
//                    upLoadFile( httpURLConnection , file );
//                    String res = getResponse(httpURLConnection);
//                    fileCallback.success(res);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//
//    private HttpURLConnection initHttpURLConnection(String utl) throws IOException {
//        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(utl).openConnection();
//        httpURLConnection.setRequestMethod("POST");
//        httpURLConnection.setDoOutput(true);
//        httpURLConnection.setDoInput(true);
//        httpURLConnection.setConnectTimeout(10*1000);
//        httpURLConnection.setReadTimeout(10*1000);
//        httpURLConnection.setUseCaches(false);
//        httpURLConnection.setRequestProperty("Connection", "keep-alive");
//        httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);  ;
//        return httpURLConnection;
//    }
//
//    private void upLoadFile(HttpURLConnection mConnection , File file) throws IOException {
//        OutputStream outputStream = null;
//        InputStream inputStream = null;
//        try{
//            outputStream = mConnection.getOutputStream();
//            StringBuffer sb = new StringBuffer();
//            sb.append("--" + BOUNDARY + "\r\n");
//            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"file\"" + "\r\n");
//            sb.append("Content-Type: image/jpeg" + "\r\n");
//            sb.append("\r\n");
//            outputStream.write(sb.toString().getBytes());
//            byte[] headerInfo = sb.toString().getBytes("UTF-8");
//            byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
//            outputStream.write(headerInfo);
//            inputStream = new FileInputStream(file);
//            byte[] bytes = new byte[1024];
//            int len = 0;
//            while((len=inputStream.read(bytes))!=-1)
//            {
//                outputStream.write(bytes, 0, len);
//            }
//            outputStream.write(endInfo);
//            outputStream.flush();
//        }finally {
//            if(outputStream !=null)
//                outputStream.close();
//            if(inputStream != null)
//                inputStream.close();
//        }
//    }
//
//
    private String getResponse(HttpURLConnection mConnection) throws IOException {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader =null;
        String res = "";
        try{
            inputStream = mConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line ;
            while((line = bufferedReader.readLine()) != null)
                res += line;
        }finally {
            if(inputStream != null)
                inputStream.close();
            if(inputStreamReader != null)
                inputStreamReader.close();
            if(bufferedReader != null)
                bufferedReader.close();
        }
        return res;
    }

    public void conn(final File file ,final String path ,final FileCallback fileCallback){
        fileCallback.before();
        new Thread(){
            @Override
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("--" + BOUNDARY + "\r\n");
                    sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"file\"" + "\r\n");
                    sb.append("Content-Type: image/jpeg" + "\r\n");
                    sb.append("\r\n");
                    byte[] headerInfo = sb.toString().getBytes("UTF-8");
                    byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
                    System.out.println(sb.toString());
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type",
                            "multipart/form-data; boundary=" + BOUNDARY);
                    conn.setRequestProperty("Content-Length", String
                            .valueOf(headerInfo.length + file.length()
                                    + endInfo.length));
                    conn.setDoOutput(true);
                    OutputStream out = conn.getOutputStream();
                    InputStream in = new FileInputStream(file);
                    out.write(headerInfo);

                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) != -1)
                        out.write(buf, 0, len);
                    out.write(endInfo);
                    in.close();
                    out.close();
                    if (conn.getResponseCode() == 200) {
                        final String res = getResponse(conn);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                fileCallback.success(res);
                            }
                        });
                    }
                }catch (Exception e){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            fileCallback.failed(1);
                        }
                    });
                }
            }
            }.start();
    }




}
