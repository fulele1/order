package com.example.mysurfaceview.orderapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mysurfaceview.orderapp.R;
import com.example.mysurfaceview.orderapp.mode.MenuMOode;

import java.util.List;

/**
 *
 */
public class ExpendAdapter extends BaseExpandableListAdapter {


    Context mContext;
    List<List<MenuMOode>> mChild;
    List<String> mGroup;
    private boolean [][] isSelect;

    public boolean[][] getIsSelect() {
        return isSelect;
    }

    public ExpendAdapter(Context context, List<String> group, List<List<MenuMOode>> child) {
        mContext  = context;
        mChild = child;
        mGroup = group;
        isSelect=new boolean[mGroup.size()][];
        getData();
    }
    public void getData(){
        for (int i=0;i<isSelect.length;i++){
            isSelect[i]=new boolean[mChild.get(i).size()];
        }
    }


    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChild.get(groupPosition).size();
    }



    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (null==convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_parent_dishes,null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.txt_parent_item_dishes);
            viewHolder.textView.setTextSize(20);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mGroup.get(groupPosition));

        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null==convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child_dishes,null);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.txt_child_item_dishes);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.ckb_item_child);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView2.setText(mChild.get(groupPosition).get(childPosition).getMenuname());
        viewHolder.textView2.setTextColor(Color.YELLOW);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isSelect[groupPosition][childPosition] = true;
                } else {
                    isSelect[groupPosition][childPosition] = false;
                }
            }
        });
        viewHolder.checkBox.setChecked(isSelect[groupPosition][childPosition]);

        return convertView;

    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class ViewHolder{
        TextView textView;
        TextView textView2;
        CheckBox checkBox;
    }









    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



}
