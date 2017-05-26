package com.example.mysurfaceview.orderapp.util;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mysurfaceview.orderapp.ChangePawActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络连接的异步任务
 */
public class LogInAsyncTast extends AsyncTask<URL,Integer,String> {
    HttpURLConnection httpURLConnection;
    Context mContext;
    public LogInAsyncTast(Context context){
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
        publishProgress(10);
        return null;

    }

    @Override
    protected void onPostExecute(String string) {


        super.onPostExecute(string);

        try {
            JSONObject jsonObject = new JSONObject(string);
            int code = jsonObject.getInt("rt");
            String name = jsonObject.getString("rtmsg");
            if (code==200){
                Toast.makeText(mContext,"登陆成功",Toast.LENGTH_SHORT).show();

                Toast.makeText(mContext,"登入成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ChangePawActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("code",code);
                mContext.startActivity(intent);
            }if (code==21){
                Toast.makeText(mContext,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
