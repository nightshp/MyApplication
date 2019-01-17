package lunzn.com.transmit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

import lunzn.com.transmit.R;
import lunzn.com.transmit.bean.FileCheckBean;
import lunzn.com.transmit.biz.VolleyRequestBiz;
import lunzn.com.transmit.interfaces.VolleyRequestCallback;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.activity
 * ProjectName: MyApplication
 * Date: 2019/1/15 15:26
 */
public class VolleyActivity extends Activity implements View.OnClickListener {

    private final static String TAG = "VolleyRequest";

    private Button btnDoGet = null;

    private Button btnDoPost = null;

    private final static String GET_REQUEST_URL = "http://lunzn.aisee.tv/m2/sys/filecheck?sdkvsn=1.0.1&rom_sign=LZ-R1-M01-V01009&launcher_sign=LZM001&launcher_vsn=1.806&sn=36XFM1CZ&mac=e8:bb:3f:00:be:9d&gdid=null&D=null";

    private final static String POST_REQUEST_URL = "http://lunzn.aisee.tv/m2/sys/filecheck";

    private VolleyRequestBiz mRequestUtil = null;

    private VolleyRequestCallback mCallback = new VolleyRequestCallback() {
        @Override
        public <T> void onRequestSuccess(String url, T t) {
            Log.i(TAG, "onRequestSuccess url：" + url);
            Log.i(TAG, "onRequestSuccess response：" + t);
            FileCheckBean data = JSON.parseObject((String)t, FileCheckBean.class);
            Log.i(TAG, "data:  " + data);
        }

        @Override
        public <T> void onRequestFailed(String url, T t) {
            Log.i(TAG, "onRequestFailed url:  " + url);
            Log.i(TAG, "onRequestFailed t:  " + t);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_layout);
        initView();
        initData();
    }

    private void initView() {
        btnDoGet = findViewById(R.id.btn_do_get);
        btnDoPost = findViewById(R.id.btn_do_post);
        btnDoGet.setOnClickListener(this);
        btnDoPost.setOnClickListener(this);
    }

    private void initData(){
        mRequestUtil = new VolleyRequestBiz(mCallback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_do_get:
                mRequestUtil.doGet(getApplicationContext(), GET_REQUEST_URL);
                break;

            case R.id.btn_do_post:
                Map<String, String> params = new HashMap<String, String>();
                params.put("sdkvsn", "1.0.1");
                params.put("rom_sign", "LZ-R1-M01-V01009");
                params.put("launcher_sign", "LZM001");
                params.put("launcher_vsn", "1.806");
                params.put("sn", "36XFM1CZ");
                params.put("mac", "e8:bb:3f:00:be:9d");
                params.put("gdid", "null");
                params.put("D", "null");
                mRequestUtil.doPost(getApplicationContext(), POST_REQUEST_URL, params);
                break;
        }
    }
}
