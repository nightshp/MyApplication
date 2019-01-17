package lunzn.com.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import lunzn.com.myapplication.R;

/**
 * Desc: activity生命周期
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/14 10:55
 */
public class ActivityLifeCycle extends Activity implements View.OnClickListener {

    private Button btnLife;

    private static final String TAG = "ActivityLifeCycle";

    public ActivityLifeCycle() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"ActivityLifeCycle onCreate 被调用");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_layout);
        initView();
    }

    private void initView() {
        btnLife = findViewById(R.id.btn_life);
        btnLife.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        Log.i(TAG,"ActivityLifeCycle onStart 被调用");
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG,"ActivityLifeCycle onNewIntent 被调用");
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"ActivityLifeCycle onRestart 被调用");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"ActivityLifeCycle onResume 被调用");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"ActivityLifeCycle onPause 被调用");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"ActivityLifeCycle onStop 被调用");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"ActivityLifeCycle onDestroy 被调用");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_life:
                Intent intent = new Intent(this,ActivityLifeCycle1.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }
}
