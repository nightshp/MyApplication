package lunzn.com.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.util.Md5Util;
import lunzn.com.myapplication.util.SharedHelper;

/**
 * Desc: SharedPreference activity
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/8 17:28
 */
public class SharedActivity extends Activity implements View.OnClickListener {
    // 登录按钮
    private Button btnLogin;

    // 用户名
    private EditText txtName;

    // 密码
    private EditText txtPasswd;

    // SharedPreference工具类
    private SharedHelper mSharedHelper;

    // 上下文
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_shared_preferences);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String> data = mSharedHelper.read();
        txtName.setText(data.get("userName"));
        txtPasswd.setText(data.get("passwd"));
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mContext = getApplicationContext();
        mSharedHelper = new SharedHelper(mContext);
        btnLogin = findViewById(R.id.btn_login);
        txtName = findViewById(R.id.txt_username);
        txtPasswd = findViewById(R.id.txt_password);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String userName =  txtName.getText().toString();
                String passwd = txtPasswd.getText().toString();
                Log.i("MD5",Md5Util.getMD5(passwd));
                mSharedHelper.save(userName,passwd);
                break;
            default:

                break;
        }
    }
}
