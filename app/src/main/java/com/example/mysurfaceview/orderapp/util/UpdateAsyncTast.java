package com.example.mysurfaceview.orderapp.util;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mysurfaceview.orderapp.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/11 0011.
 */
public class UpdateAsyncTast extends AsyncTask<URL,Integer,String> {


    HttpURLConnection httpURLConnection;

    Context mContext;
    public UpdateAsyncTast(Context context){
        mContext = context;
    }
    @Override
    protected String doInBackground(URL... params) {
        try {
            httpURLConnection = (HttpURLConnection) params[0].openConnection();
            if(httpURLConnection.getResponseCode()==200){
                int length;
                byte [] bb = new byte[1024];
                InputStream input =httpURLConnection.getInputStream();
                while ((length = input.read(bb))!=-1){
                    String con = new String(bb, 0, length);
                    return con;
                }
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

        try {
            JSONObject jsonObject = new JSONObject(s);
            int code = jsonObject.getInt("rt");
            if (code==200){

                Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent2);
            }if (code==21){
                Toast.makeText(mContext,"密码输入错误，请重新输入",Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
