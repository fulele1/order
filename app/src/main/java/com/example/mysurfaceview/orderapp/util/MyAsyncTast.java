package com.example.mysurfaceview.orderapp.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 异步任务
 */
public class MyAsyncTast extends AsyncTask<URL,Integer,String> {


    Context mContext;
    HttpURLConnection httpURLConnection;
    OnStringGetedOver mOnStringGetedOver;
    public MyAsyncTast(Context context,OnStringGetedOver onStringGetedOver) {
        mContext = context;
        mOnStringGetedOver = onStringGetedOver;
    }

    @Override
    protected String doInBackground(URL... params) {


        try {
            httpURLConnection = (HttpURLConnection) params[0].openConnection();
            if(httpURLConnection.getResponseCode()==200){
                int length;
                byte [] bb = new byte[1024];
                InputStream input =httpURLConnection.getInputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(input,"utf-8"));
                String line="";
                StringBuffer sBuf=new StringBuffer();
                while((line=buf.readLine())!=null){
                    sBuf.append(line);
                    Log.e("TAG","-------------------------------------"+line);
                }
                return  sBuf.toString();
//                while ((length = input.read(bb))!=-1){
//                    String con = new String(bb, 0, length);
//                    return con;
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (null!=httpURLConnection){
                httpURLConnection.disconnect();
            }
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.e("999999", "999999" + s);
        mOnStringGetedOver.getUpdateString(s);
    }

}
