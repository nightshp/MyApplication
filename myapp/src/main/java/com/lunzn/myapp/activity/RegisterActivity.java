package com.lunzn.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lunzn.myapp.R;
import com.lunzn.myapp.util.LitePalUtil;
import com.lunzn.myapp.util.MD5Util;

/**
 * Desc: 用户注册
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/22 11:09
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    // 注册按钮
    private Button btnRegister;
    // 用户名
    private EditText etUserName;
    // 密码
    private EditText etPasswd;
    // 确认密码
    private EditText etConfPasswd;

    private final static String TAG = "register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_myapp_layout);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        etUserName = findViewById(R.id.et_userName_re);
        etPasswd = findViewById(R.id.et_password_re);
        etConfPasswd = findViewById(R.id.et_password_re1);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v 控件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                register();
                break;
            default:

                break;
        }
    }

    /**
     * 注册操作
     */
    private void register() {
        String name = etUserName.getText().toString();
        String passwd = etPasswd.getText().toString();
        Log.i(TAG,"加密前的密码:"+passwd);
        String confPasswd = etConfPasswd.getText().toString();
        // 名字和密码不为空
        if (name != null && name.length() != 0 && passwd != null && passwd.length() != 0){
            // 判断两次密码是否一致
            if (passwd.equals(confPasswd)) {
                // 密码加密
                String passwdMD5 = MD5Util.getMD5(passwd);
                Log.i(TAG,"加密后的密码:"+passwdMD5);
                LitePalUtil.registUser(name, passwdMD5);
                Log.i(TAG, "register user:" + name);
                Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "注册失败 两次密码不一致", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
        }

    }

}
