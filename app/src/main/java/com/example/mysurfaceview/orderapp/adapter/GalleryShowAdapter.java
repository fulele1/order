package com.example.mysurfaceview.orderapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mysurfaceview.orderapp.R;

/**
 *
 */
public class GalleryShowAdapter extends BaseAdapter {
    Context mContext;

    Integer[] mPic = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};


    public GalleryShowAdapter(Context context){
        mContext = context;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (null==convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_giy_show,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_item_show);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        Bitmap bitmap= BitmapFactory.decodeResource(mContext.getResources(),
//                mPic[position % mPic.length]);
//        Bitmap.createScaledBitmap(bitmap,,,true);

        viewHolder.imageView.setImageResource(mPic[position% mPic.length]);
       // viewHolder.imageView.setLayoutParams(new ViewGroup.LayoutParams());
        viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return convertView;
    }



    class ViewHolder{
        ImageView imageView;
    }
}
