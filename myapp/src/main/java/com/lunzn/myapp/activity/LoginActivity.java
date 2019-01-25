package com.lunzn.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lunzn.myapp.R;
import com.lunzn.myapp.bean.User;
import com.lunzn.myapp.util.LitePalUtil;
import com.lunzn.myapp.util.MD5Util;

import java.util.List;

/**
 * Desc: 登录activity
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/22 9:32
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    // 输入用户名
    private EditText etUserName;
    // 输入密码
    private EditText etPassword;
    // 登录按钮
    private Button btnLogin;
    // 注册按钮
    private Button btnRegister;

    private static final String TAG = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_myapp_layout);
        // 创建数据库
        LitePalUtil.createDataBase();

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        etUserName = findViewById(R.id.et_userName);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register_1);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    /**
     * 点击事件
     * @param v 控件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginOpt();
                break;
            case R.id.btn_register_1:
                jumpTo();
                break;
            default:

                break;
        }
    }

    /**
     * 跳转到其他activity
     */
    private void jumpTo() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 登录操作
     */
    public void loginOpt(){
        String name = etUserName.getText().toString();
        String passwd = etPassword.getText().toString();
        loginJudge(name,passwd);
    }
    /**
     * 登录判断
     * @param userName 用户名
     * @param passWord 密码
     */
    public void loginJudge(String userName,String passWord){
        passWord = MD5Util.getMD5(passWord);
        Log.i(TAG,"passWord: " + passWord);
        List<User> users = LitePalUtil.queryData(userName,passWord);
        Log.i(TAG,"login user: "+users);
        if (users != null && users.size()>0){
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,MyFragmentActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
        }
    }


}
