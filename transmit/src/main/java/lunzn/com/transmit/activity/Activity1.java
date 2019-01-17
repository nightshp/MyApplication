package lunzn.com.transmit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
public  class Activity1 extends Activity implements View.OnClickListener {

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_layout);
        initView();
    }

    private void initView() {
        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                transmitData();
                break;
            default:

                break;
        }
    }

    private void transmitData() {
        Intent intent = new Intent(this,Activity2.class);
        Bundle bundle = new Bundle();
        bundle.putString("result","我是一只小小鸟");
        intent.putExtras(bundle);
        // 第二个参数为请求码 用来标识activity
        startActivityForResult(intent,1);
    }

    /**
     * 接收activity2传过来的参数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1){
            String userName = data.getStringExtra("userName");
            String passwd = data.getStringExtra("passwd");
            Toast.makeText(this,"用户名："+userName+"密码："+passwd,Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
