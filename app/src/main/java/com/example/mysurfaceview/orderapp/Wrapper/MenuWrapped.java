package com.example.mysurfaceview.orderapp.Wrapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mysurfaceview.orderapp.db.MenuHelper;
import com.example.mysurfaceview.orderapp.entity.Dishes;
import com.example.mysurfaceview.orderapp.mode.MenuMOode;

import java.util.ArrayList;
import java.util.List;

/**
 * 顾客封装类
 */
public class MenuWrapped {

    Context mContext;
    MenuHelper menuHelper;
    public MenuWrapped(Context context) {
        mContext = context;
        menuHelper = new MenuHelper(mContext);
    }

    //插入
    public void insert(MenuMOode menuMOode){
        SQLiteDatabase sqLiteDatabase = menuHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category", menuMOode.getCategory());
        values.put("menuname", menuMOode.getMenuname());
        values.put("price", menuMOode.getPrice());
        values.put("units", menuMOode.getUnits());
        values.put("pic", menuMOode.getPic());
        values.put("version", menuMOode.getVersion());
        values.put("remark", menuMOode.getRemark());

        sqLiteDatabase.insert("menutbl", null, values);
        sqLiteDatabase.close();
    }


    //查找
    public List<MenuMOode> query(){
        List<MenuMOode> menuMOodes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = menuHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("menutbl", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String categorys = cursor.getString(cursor.getColumnIndex("category"));
            String menuname = cursor.getString(cursor.getColumnIndex("menuname"));
            int price = cursor.getInt(cursor.getColumnIndex("price"));
            int units = cursor.getInt(cursor.getColumnIndex("units"));
            String pic = cursor.getString(cursor.getColumnIndex("pic"));
            String version = cursor.getString(cursor.getColumnIndex("version"));
            String remark = cursor.getString(cursor.getColumnIndex("remark"));

            MenuMOode menuMOode = new MenuMOode();
            menuMOode.setCategory(categorys);
            menuMOode.setMenuname(menuname);
            menuMOode.setPrice(price);
            menuMOode.setUnits(units);
            menuMOode.setPic(pic);
            menuMOode.setVersion(version);
            menuMOode.setRemark(remark);

            menuMOodes.add(menuMOode);

        }
        sqLiteDatabase.close();
        return menuMOodes;
    }

    public void insetToTemprary(Dishes dishes){
        SQLiteDatabase sqLiteDatabase =menuHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("menuId",dishes.getMenuId());
        contentValues.put("menuname",dishes.getName());
        contentValues.put("price",dishes.getPrice());
        contentValues.put("count",dishes.getCount());
        contentValues.put("mark",dishes.getMark());
        contentValues.put("type",dishes.getMark());
        sqLiteDatabase.insert("need", null, contentValues);
        sqLiteDatabase.close();
    }

    public List<Dishes> queryFromTemprary(){
        List<Dishes> dishesList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = menuHelper.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.query("need",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String menuId = cursor.getString(cursor.getColumnIndex("menuId"));
            String menuname = cursor.getString(cursor.getColumnIndex("menuname"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String count = cursor.getString(cursor.getColumnIndex("count"));
            String mark = cursor.getString(cursor.getColumnIndex("mark"));
            String type = cursor.getString(cursor.getColumnIndex("type"));

            Dishes dishes = new Dishes();
            dishes.setMenuId(menuId);
            dishes.setName(menuname);
            dishes.setPrice(price);
            dishes.setCount(count);
            dishes.setMark(mark);
            dishes.setType(type);

            dishesList.add(dishes);
            sqLiteDatabase.close();
        }
        return dishesList;
    }


    public void deleteFromTemprary(){
        SQLiteDatabase sqLiteDatabase = menuHelper.getReadableDatabase();
        sqLiteDatabase.delete("need",null,null);
        sqLiteDatabase.close();
    }


}
