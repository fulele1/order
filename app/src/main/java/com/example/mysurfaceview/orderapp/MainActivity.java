package com.example.mysurfaceview.orderapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mysurfaceview.orderapp.util.Constant;
import com.example.mysurfaceview.orderapp.util.LogInAsyncTast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *登录界面
 */
public class MainActivity extends BaseActivity {
    ImageButton btnLogIn;
    URL url;
    EditText edtName;
    EditText edtPassWord;
    String name;
    String paw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        init();

    }

    public void init(){
        btnLogIn = (ImageButton) this.findViewById(R.id.btn_login_main);
        edtName = (EditText) this.findViewById(R.id.edit_name_main);
        edtPassWord = (EditText) this.findViewById(R.id.edit_password_main);
    }


    public void getEditText(){
        try {
            name = edtName.getText().toString();
            name = new String(name.getBytes("ISO8859-1"),"UTF-8");
            paw = edtPassWord.getText().toString();
            paw = new String(paw.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void path(){
        try {
            url = new URL(Constant.LOGIN_PATH+"&name="+name+"&paw="+paw);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void myClick(View view){
        getEditText();
        path();
        LogInAsyncTast logInAsyncTast = new LogInAsyncTast(this);
        logInAsyncTast.execute(url);

    }
}
