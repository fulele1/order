package com.example.mysurfaceview.orderapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysurfaceview.orderapp.Wrapper.MenuWrapped;
import com.example.mysurfaceview.orderapp.adapter.ExpendAdapter;
import com.example.mysurfaceview.orderapp.entity.Dishes;
import com.example.mysurfaceview.orderapp.mode.MenuMOode;
import com.example.mysurfaceview.orderapp.util.Constant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜品详情界面
 */
public class DishesActivity extends BaseActivity implements View.OnClickListener{


    private List<List<MenuMOode>> mChild;
    private List<String> mGroup;
    private ExpendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        init();
        event();
        getDate();
        adapter=new ExpendAdapter(this, mGroup, mChild);
        mExpand.setAdapter(adapter);
        mExpand.setOnChildClickListener(new MyItemClick());
    }

    private int mgroupPosition;
    private int mchildPosition;


    List<MenuMOode> chooseMenu;

    class MyItemClick implements ExpandableListView.OnChildClickListener{

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            chooseMenu = new ArrayList<>();
           mgroupPosition = groupPosition;
            mchildPosition = childPosition;

//            CheckBox checkBox = (CheckBox) v.findViewById(R.id.ckb_item_child);
//            if (checkBox.isChecked()){
//                checkBox.setChecked(false);
//                mChild.get(groupPosition).get(childPosition).setIsCherked(false);
//                if (null !=chooseMenu){
//                    chooseMenu.remove(mChild.get(groupPosition).get(childPosition));
//                }
//            }else {
//                checkBox.setChecked(true);
//                mChild.get(groupPosition).get(childPosition).setIsCherked(true);
//                chooseMenu.add(mChild.get(groupPosition).get(childPosition));
//            }

            mTxtIntrueduce.setText(mChild.get(groupPosition).get(childPosition).getRemark());
            mTxtPointName.setText(mChild.get(groupPosition).get(childPosition).getMenuname());
            mTxtPointPrice.setText("" + mChild.get(groupPosition).get(childPosition).getPrice());

            new Thread(){
                String picString = mChild.get(mgroupPosition).get(mchildPosition).getPic();

                @Override
                public void run() {
                    downLoadPic(picString);
                }
            }.start();

            return true;
        }
    }


    /**
     * 多线程下载
     */

    static final int THREAD_COUNT = 3;
    public void downLoadPic(final String picString){

        try {
            URL url = new URL(Constant.BASE_PATH+picString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int connLength = httpURLConnection.getContentLength();
            int everyLength = connLength%THREAD_COUNT==0?connLength/THREAD_COUNT:connLength/THREAD_COUNT+1;
            RandomAccessFile accessFile = new RandomAccessFile(Environment.getExternalStorageDirectory().getAbsoluteFile()+ File.separator+picString.substring(picString.lastIndexOf('/')+1),"rw");
            accessFile.setLength(connLength);
            for (int threadId = 0;threadId<THREAD_COUNT;threadId++){
                int startPosition = threadId*everyLength;
                int endPosition = (threadId+1)*everyLength;
                new MyDownloadThread(picString,startPosition,endPosition).start();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyDownloadThread extends Thread{
        String mPicString;
        int mStartPosition;
        int mEndPosition;
        public MyDownloadThread(String picString,int startPosition,int endPosition) {
            mPicString = picString;
            mStartPosition = startPosition;
            mEndPosition = endPosition;
        }

        @Override
        public void run() {

            try {
                URL url = new URL(Constant.BASE_PATH+mPicString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Range", "bytes" + mStartPosition + "-" + mEndPosition);
                RandomAccessFile file = new RandomAccessFile(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+mPicString.substring(mPicString.lastIndexOf('/')+1),"rw");
                file.seek(mStartPosition);
                if (httpURLConnection.getResponseCode()==206){
                    int len;
                    byte [] b = new byte[1024];
                    InputStream in = httpURLConnection.getInputStream();
                    while((len = in.read())!=-1){
                        file.write(b,0,len);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private Button mBtn;
    private Button mBtnDishes;
    private Button mBtnOrder;
    private ExpandableListView mExpand;
    private TextView mTxtIntrueduce;
    private TextView mTxtPointName;
    private TextView mTxtPointPrice;
    private ImageView mImgDishes;

    public void init(){
        mBtn = (Button) this.findViewById(R.id.btn_table_num_dishes);
        mBtnDishes = (Button) this.findViewById(R.id.btn_dishes_dishes);
        mExpand = (ExpandableListView) this.findViewById(R.id.expand_dishes);
        mTxtIntrueduce = (TextView) this.findViewById(R.id.txt_dishesIntroduce_dishes);
        mTxtPointName = (TextView) this.findViewById(R.id.txt_point_name_dishes);
        mTxtPointPrice = (TextView) this.findViewById(R.id.txt_point_price_dishes);
        mImgDishes = (ImageView) this.findViewById(R.id.img_dishes_dishes);
        mBtnOrder = (Button) this.findViewById(R.id.btn_order_dishes);
    }


    public void getDate(){
        mGroup = new ArrayList<>();
        mChild = new ArrayList<List<MenuMOode>>();
        MenuWrapped menuWrapped =new MenuWrapped(this);
        List<MenuMOode> list =menuWrapped.query();

        List<MenuMOode> clod = new ArrayList<>();
        List<MenuMOode> mainFood = new ArrayList<>();
        List<MenuMOode> soup = new ArrayList<>();
        List<MenuMOode> hot = new ArrayList<>();
        List<MenuMOode> tools = new ArrayList<>();
        List<MenuMOode> drink = new ArrayList<>();

        for (MenuMOode menuMOode:list){
          if (menuMOode.getCategory().equals(1201+"")){
              hot.add(menuMOode);
              Log.e("1201", "" + menuMOode.getMenuname());
          }if (menuMOode.getCategory().equals(1202+"")){
                clod.add(menuMOode);
                Log.e("1202", "" + menuMOode.getMenuname());
            }if (menuMOode.getCategory().equals(1203+"")){
                soup.add(menuMOode);
                Log.e("1203", "" + menuMOode.getMenuname());
            }if (menuMOode.getCategory().equals(1204+"")){
                drink.add(menuMOode);
                Log.e("1204", "" + menuMOode.getMenuname());
            }if (menuMOode.getCategory().equals(1205+"")){
                mainFood.add(menuMOode);
                Log.e("1205", "" + menuMOode.getMenuname());
            }if (menuMOode.getCategory().equals(1206+"")){
                tools.add(menuMOode);
                Log.e("1206", "" + menuMOode.getMenuname());
            }
        }

        mChild.add(mainFood);
        mChild.add(clod);
        mChild.add(soup);
        mChild.add(hot);
        mChild.add(tools);
        mChild.add(drink);

        mGroup.add("主食");
        mGroup.add("凉菜");
        mGroup.add("汤");
        mGroup.add("热菜");
        mGroup.add("餐具");
        mGroup.add("饮品");
    }


    public void event(){
        mBtn.setOnClickListener(this);
        mBtnDishes.setOnClickListener(this);
        mBtnOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_table_num_dishes:
                chooseTable();
                break;
            case R.id.btn_dishes_dishes:
                allDishesSort();
                break;
            case R.id.btn_order_dishes:
                getChooseMenu();
                break;
        }
    }



    public void getChooseMenu(){
      boolean [] [] isSelect=adapter.getIsSelect();
        for (int i=0;i<isSelect.length;i++){
            for (int j=0;j<isSelect[i].length;j++){
                if (isSelect[i][j]==true){
                    MenuMOode menuMOode= mChild.get(i).get(j);
                    Dishes dishe = new Dishes();
                    dishe.setName(menuMOode.getMenuname());
                    dishe.setPrice(menuMOode.getPrice() + "");
                    dishe.setMenuId(menuMOode.getId() + "");
                    dishe.setCount(1 + "");
                    dishe.setMark("");
                    dishe.setType(menuMOode.getCategory());
                    MenuWrapped men = new MenuWrapped(this);
                    men.insetToTemprary(dishe);
                }
            }
        }


        Intent intent = new Intent(this,PullOrderActivity.class);
        this.startActivity(intent);
    }



    private String [] dishesSort = {"全部 ","主食","凉菜","汤羹","热菜","餐具","饮品"};
    public void allDishesSort(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setSingleChoiceItems(dishesSort, dishesSort.length, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mBtnDishes.setText(dishesSort[which]);
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }


    private String tableNum [] = {"101","102","103","104","105","106","107","108"};

    public void chooseTable(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setSingleChoiceItems(tableNum, tableNum.length, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mBtn.setText(tableNum[which]);
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}