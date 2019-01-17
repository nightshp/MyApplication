package lunzn.com.transmit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lunzn.com.transmit.R;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/15 10:44
 */
public class Activity2 extends Activity implements View.OnClickListener {

    private Button btn2;

    private EditText etName;

    private EditText etPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_layout);
        initView();
        getData();
    }

    private void initView() {
        btn2 = findViewById(R.id.btn_2);
        etName = findViewById(R.id.et_userName);
        etPasswd = findViewById(R.id.et_password);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_2:
                Intent intent = new Intent();
                String userName = etName.getText().toString();
                String passwd = etPasswd.getText().toString();
                intent.putExtra("userName",userName);
                intent.putExtra("passwd",passwd);
                // 设置结果返回activity1
                setResult(1,intent);
                // 关闭单前界面
                this.finish();
                break;
            default:

                break;
        }

    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String test = bundle.getString("result");
        Toast.makeText(this,test,Toast.LENGTH_LONG).show();
    }
}
