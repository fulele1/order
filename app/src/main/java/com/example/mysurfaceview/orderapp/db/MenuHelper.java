package com.example.mysurfaceview.orderapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 菜单数据库的帮助类
 */
public class MenuHelper extends SQLiteOpenHelper {

    public MenuHelper(Context context) {
        super(context, "foods.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table menutbl(_id integer primary key autoincrement,category text,menuname text,price integer,units integer,pic text,version text,remark text)");
        db.execSQL("create table tableNums(_id integer primary key autoincrement,num text)");
        db.execSQL("create table need(_id integer primary key autoincrement,menuId text,menuname text,price text,count text,mark text,type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
