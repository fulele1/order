package com.example.mysurfaceview.orderapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mysurfaceview.orderapp.util.Constant;
import com.example.mysurfaceview.orderapp.util.JSONPrase;
import com.example.mysurfaceview.orderapp.util.MyAsyncTast;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 更多
 */
public class MoreActivity extends BaseActivity implements View.OnClickListener{


    ImageButton mBtnUpMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        init();
        setBtnClick();
    }


    public void init(){

        mBtnUpMenu = (ImageButton) this.findViewById(R.id.imb_order_more);
    }

    public void setBtnClick(){
        mBtnUpMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imb_order_more:
                updatemenu();
                break;
        }
    }


    //访问更新菜单服务
    public void updatemenu(){

        JSONPrase jsonPrase =new JSONPrase(this);
        MyAsyncTast myAsyncTast = new MyAsyncTast(this,jsonPrase);

        try {
            URL url = new URL(Constant.UPDATE_PATH+"?num=1&update=menu");
            myAsyncTast.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
