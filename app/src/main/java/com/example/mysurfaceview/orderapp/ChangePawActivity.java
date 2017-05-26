package com.example.mysurfaceview.orderapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysurfaceview.orderapp.util.ChangeAsyncTast;
import com.example.mysurfaceview.orderapp.util.Constant;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 修改密码
 */
public class ChangePawActivity extends BaseActivity {
    private SharedPreferences sharedPreferences;
    private Button mBtnDo;
    private Context mContext;
    private TextView edtName;
    private EditText edtopaw;
    private EditText edtnpaw;
    private EditText edtnOnceMorepaw;
    private String nameIntent;
    private URL url;
    private String opaw;
    private String npaw;
    private String nOnceMorepaw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =this.getIntent();
        nameIntent = intent.getStringExtra("name");
        int code = intent.getIntExtra("code", -1);
        Log.e("0090",""+code);
        sharedPreferences = this.getSharedPreferences("user.xml", MODE_PRIVATE);
        String time = sharedPreferences.getString("userName", "null");
        if(nameIntent .equals(time)){
            Intent intent1 = new Intent(this,ShowActivity.class);
            this.startActivity(intent1);
            this.finish();
        }else{
            if (code==200){
                setContentView(R.layout.activity_change_paw);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("userName", nameIntent);
                editor.commit();
                mBtnDo = (Button) this.findViewById(R.id.btn_do_change);
                mBtnDo.setOnClickListener(new DoBtnClick());
                init();
            }
        }
    }



    public void init(){
        edtName = (TextView) this.findViewById(R.id.txt_name_change);
        edtName.setText(nameIntent);
        edtopaw = (EditText) this.findViewById(R.id.edt_opaw_change);
        edtnpaw = (EditText) this.findViewById(R.id.edit_npaw_change);
        edtnOnceMorepaw = (EditText) this.findViewById(R.id.edit_npawonesmore_change);
    }
    class DoBtnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChangePawActivity.this);
            builder.setMessage("是否修改");
            builder.setPositiveButton("确定",new SwitchOnClick() );
            builder.setNegativeButton("取消",new SwitchOnClick());
            builder.show();
        }
    }

    public void getEditText(){
        try {
            opaw = edtopaw.getText().toString();
            opaw = new String(opaw.getBytes("ISO8859-1"),"UTF-8");
            npaw = edtnpaw.getText().toString();
            npaw = new String(npaw.getBytes("ISO8859-1"),"UTF-8");
            nOnceMorepaw = edtnOnceMorepaw.getText().toString();
            nOnceMorepaw = new String(nOnceMorepaw.getBytes("ISO8859-1"),"UTF-8");
//            if(npaw .equals(nOnceMorepaw)){
//            }else{
//                edtnOnceMorepaw.setText("");
//                Toast.makeText(ChangePawActivity.this,"两次密码不相同，请重新输入",Toast.LENGTH_SHORT).show();
//            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void path(){
        try {

            if(npaw .equals(nOnceMorepaw)){
                url = new URL(Constant.CHANGEPAW_PATH+"?name="+nameIntent+"&opaw="+opaw+"&npaw="+npaw);
            }else{
                edtnOnceMorepaw.setText("");
                url = new URL(Constant.CHANGEPAW_PATH+"?name="+nameIntent+"&opaw=null"+"&npaw=null");
                Toast.makeText(ChangePawActivity.this,"两次密码不相同，请重新输入",Toast.LENGTH_SHORT).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    class SwitchOnClick implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case AlertDialog.BUTTON_POSITIVE:
                    //修改密码
                    getEditText();
                    path();
                    ChangeAsyncTast ChangeAsyncTast = new ChangeAsyncTast(ChangePawActivity.this);
                    ChangeAsyncTast.execute(url);
                    Toast.makeText(ChangePawActivity.this, "密码修改，请重新登录", Toast.LENGTH_SHORT).show();

                    break;
                case AlertDialog.BUTTON_NEGATIVE:
                    Intent intent1 = new Intent(ChangePawActivity.this,ShowActivity.class);
                    ChangePawActivity.this.startActivity(intent1);
                    ChangePawActivity.this.finish();
                    break;
            }
        }
    }
}
