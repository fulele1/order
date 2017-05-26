package com.example.mysurfaceview.orderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageButton;

import com.example.mysurfaceview.orderapp.adapter.GalleryShowAdapter;

/**
 * 显示界面
 */
public class ShowActivity extends BaseActivity implements View.OnClickListener{


    Gallery mGly;
    ImageButton mImgOrder;
    ImageButton mImgMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);
        init();
        mGly.setAdapter(new GalleryShowAdapter(this));
        mGly.setSelection(Integer.MAX_VALUE / 2);
        setBtnClick();
    }


    public void init(){
        mGly = (Gallery) this.findViewById(R.id.gly_show);
        mImgOrder = (ImageButton) this.findViewById(R.id.img_order_show);
        mImgMore = (ImageButton) this.findViewById(R.id.img_more_show);
    }

    public void setBtnClick(){
        mImgOrder.setOnClickListener(this);
        mImgMore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_order_show:
                Intent intent1 = new Intent(this,DishesActivity.class);
                this.startActivity(intent1);
                break;
            case R.id.img_more_show:
                Intent intent = new Intent(this,MoreActivity.class);
                this.startActivity(intent);
                break;
        }
    }
}
