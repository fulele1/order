package com.example.mysurfaceview.orderapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mysurfaceview.orderapp.Wrapper.MenuWrapped;
import com.example.mysurfaceview.orderapp.adapter.ListPullAdapter;
import com.example.mysurfaceview.orderapp.entity.Dishes;

import java.util.List;

/**
 * 订单
 */
public class PullOrderActivity extends BaseActivity implements View.OnClickListener{


    private List<Dishes> dishesList;
    private ListView listView;
    private ListPullAdapter listPullAdapter;
    private Button mBtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pull_order);
        init();
        getDate();
        event();
    }

    public void init(){
        listView = (ListView) this.findViewById(R.id.list_pull);
        mBtnBack = (Button) this.findViewById(R.id.btn_back_pull);
    }


    public void getDate(){
        MenuWrapped menuWrapped = new MenuWrapped(this);
        dishesList = menuWrapped.queryFromTemprary();

        Log.e("0000089", "" + dishesList.get(0).getName());
        Log.e("0000089",""+dishesList.get(1).getName());
    }

    public void event(){
        listPullAdapter = new ListPullAdapter(this,dishesList);
        listView.setAdapter(listPullAdapter);
        mBtnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_pull:
                MenuWrapped menuWrapped = new MenuWrapped(this);
                menuWrapped.deleteFromTemprary();
                break;
        }
    }
}
