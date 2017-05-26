package com.example.mysurfaceview.orderapp.util;

import android.content.Context;

import com.example.mysurfaceview.orderapp.Wrapper.MenuWrapped;
import com.example.mysurfaceview.orderapp.mode.MenuMOode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析的封装类
 */
  public  class JSONPrase implements OnStringGetedOver{

    Context mContext;
    public JSONPrase(Context context) {
        mContext = context;
    }

    String string;
    @Override
    public void getUpdateString(String s) {
        string = s;
        insertDB();
    }

    public void insertDB(){

        try {
            JSONObject jsonObject = new JSONObject(string);

            MenuWrapped menuWrapped =new MenuWrapped(mContext);
            JSONArray jsonArray= jsonObject.getJSONArray("list");

            for (int i = 0;i<jsonArray.length();i++){
                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                MenuMOode menuMOode = new MenuMOode();

                menuMOode.setCategory(jsonArray1.getString(1));
                menuMOode.setMenuname(jsonArray1.getString(2));
                menuMOode.setPic(jsonArray1.getString(3));
                String price=jsonArray1.getString(4);
                menuMOode.setPrice(Integer.parseInt(price));
                menuMOode.setRemark(jsonArray1.getString(5));
                String unit=jsonArray1.getString(6);
                menuMOode.setUnits(Integer.parseInt(unit));

                menuWrapped.insert(menuMOode);
            }
        } catch (JSONException e) {

        }
    }
}
