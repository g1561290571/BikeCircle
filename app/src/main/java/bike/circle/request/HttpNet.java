package bike.circle.request;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by MrH on 2017/5/5.
 */

public class HttpNet {

    private Handler handler = new Handler(Looper.getMainLooper());



    public interface HttpNetCallback{
        void success(String res);
        void fail(int failType);
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkOK = false;
        try {
            ConnectivityManager conn = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == conn || null == conn.getActiveNetworkInfo()) {
                isNetworkOK = false;
            } else {
                isNetworkOK = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetworkOK;
    }

    public void conn(final HttpNetCallback httpNetCallback , String para , String url){
        connect(new HttpNetCallback() {
            @Override
            public void success(final String res) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpNetCallback.success(res);
                    }
                });
            }

            @Override
            public void fail(final int failType) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpNetCallback.fail(failType);
                    }
                });
            }
        },para , url);

    }

    private void connect(final HttpNetCallback httpNetCallback , final String para , final String url){
        final String res = "";

        new Thread() {
            public void run() {
                if (httpNetCallback != null) {

                    String result = "";
                    try {
                        result = postData(url, para);
                    } catch (MalformedURLException e) {
                        httpNetCallback.fail(1);
                        e.printStackTrace();
                    } catch (IOException e) {
                        httpNetCallback.fail(1);
                        e.printStackTrace();
                    }
                    httpNetCallback.success(result);
                }
            };
        }.start();
    }


    public String postData(String urlPath, String para) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        String res = "";
        setConnectParam(httpURLConnection);
        sendData(httpURLConnection , para);
        res = getData(httpURLConnection);
        return res;
    }

    private void setConnectParam(HttpURLConnection mConnection) throws ProtocolException {
        mConnection.setRequestMethod("POST");
        mConnection.setRequestProperty("connection", "Keep-Alive");
        mConnection.setRequestProperty("Content-Type", "application/json");
        mConnection.setDoInput(true);
        mConnection.setDoOutput(true);
        mConnection.setReadTimeout(3*1000);
        mConnection.setConnectTimeout(3*1000);
    }

    private void sendData(HttpURLConnection mConnection , String param) throws IOException {
        OutputStream out = null;
        PrintWriter pri = null;
        try{
            out = mConnection.getOutputStream();
            pri = new PrintWriter(out);
            pri.write(param);
            pri.flush();
        }finally {
            if(out != null)
                out.close();
            if(pri !=null)
                pri.close();
        }
    }

    private String getData(HttpURLConnection mConnection) throws IOException {
        InputStream in = null;
        InputStreamReader ir = null;
        BufferedReader br = null;
        String res ="";
        try{
            in = mConnection.getInputStream();
            ir = new InputStreamReader(in);
            br = new BufferedReader(ir);
            String line;
            while ((line = br.readLine()) != null)
                res +=line;
        }finally {
            if(in != null)
                in.close();
            if(ir != null)
                ir.close();
            if(br != null)
                br.close();
        }
        return res;
    }






}
