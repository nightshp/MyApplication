package lunzn.com.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import lunzn.com.myapplication.R;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/14 11:09
 */
public class ActivityLifeCycle1 extends Activity implements View.OnClickListener {

    private static final String TAG = "ActivityLifeCycle";

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"ActivityLifeCycle1 onCreate 被调用");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_layout1);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        Log.i(TAG,"ActivityLifeCycle1 onStart 被调用");
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG,"ActivityLifeCycle1 onNewIntent 被调用");
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"ActivityLifeCycle1 onRestart 被调用");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"ActivityLifeCycle1 onResume 被调用");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"ActivityLifeCycle1 onPause 被调用");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"ActivityLifeCycle1 onStop 被调用");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"ActivityLifeCycle1 onDestroy 被调用");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Intent intent = new Intent(this,ActivityLifeCycle.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }
}
