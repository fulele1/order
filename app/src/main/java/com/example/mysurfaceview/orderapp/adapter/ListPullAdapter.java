package com.example.mysurfaceview.orderapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mysurfaceview.orderapp.R;
import com.example.mysurfaceview.orderapp.entity.Dishes;

import java.util.List;

/**
 *
 */
public class ListPullAdapter extends BaseAdapter {

    private Context mContext;
    List<Dishes> mDishesList;

    public ListPullAdapter(Context context,List<Dishes> dishesList) {
        mContext = context;
        mDishesList = dishesList;

        Log.e("000000", "" + mDishesList.size() + mDishesList.get(0).getName()+mDishesList.get(0).getMenuId());


    }

    @Override
    public int getCount() {
        return mDishesList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHlolder;
        if (convertView ==null){
            viewHlolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_get_order,null);
            viewHlolder.id = (TextView) convertView.findViewById(R.id.txt_id_item_pull);
            viewHlolder.name = (TextView) convertView.findViewById(R.id.txt_name_item_pull);
            viewHlolder.price = (TextView) convertView.findViewById(R.id.txt_price_item_pull);
            viewHlolder.count = (TextView) convertView.findViewById(R.id.txt_price_item_pull);
            viewHlolder.totalPrice = (TextView) convertView.findViewById(R.id.txt_total_price_item_pull);
            viewHlolder.mark = (TextView) convertView.findViewById(R.id.txt_mark_item_pull);
            viewHlolder.verity = (TextView) convertView.findViewById(R.id.txt_verity_item_pull);
            viewHlolder.delete = (TextView) convertView.findViewById(R.id.delete_id_item_pull);

            convertView.setTag(viewHlolder);
        }else{
            viewHlolder = (ViewHolder) convertView.getTag();
        }



        Log.e("000000", "" + mDishesList.get(position).getMenuId());
        Log.e("000000", "" + mDishesList.get(position).getName());
        Log.e("000000", "" + mDishesList.get(position).getPrice());
            viewHlolder.id.setText("" + mDishesList.get(position).getMenuId());
            viewHlolder.name.setText(""+mDishesList.get(position).getName());
            viewHlolder.price.setText(""+mDishesList.get(position).getPrice());
//            viewHlolder.count.setText(""+mDishesList.get(position).getCount());
//            viewHlolder.totalPrice.setText(""+Integer.getInteger(mDishesList.get(position).getPrice())*Integer.getInteger(mDishesList.get(position).getCount()));
//            viewHlolder.mark.setText(""+mDishesList.get(position).getMark());
//            viewHlolder.verity.setText(""+mDishesList.get(position).getType());
        return convertView;
    }

    class ViewHolder{
        TextView id;
        TextView name;
        TextView price;
        TextView count;
        TextView totalPrice;
        TextView mark;
        TextView verity;
        TextView delete;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
