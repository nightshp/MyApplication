package com.lunzn.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lunzn.demo.R;

/**
 * Desc: Looper 演示
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.activity
 * ProjectName: MyApplication
 * Date: 2018/12/29 16:51
 */
public class LooperActivity extends Activity implements View.OnClickListener {

    private LessonThread mLessonThread = null;

    private Button btnSend = null;

    private Handler mHandler = null;

    private HandlerThread mHandlerThread = null;

    class LessonThread extends Thread{

        private Looper mLooper = null;

        public Looper getLooper() {
            return mLooper;
        }

        @Override
        public void run() {
            Looper.prepare();
            mLooper = Looper.myLooper();
            Looper.loop();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_lesson_layout);
        initData();
        initView();
    }

    private void initData(){
        mHandlerThread = new HandlerThread("handler_thread");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()){

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        Log.i("looper", "thread:  " + Thread.currentThread().getName());
                        break;
                }
            }
        };
    }

    private void initView(){
        btnSend = findViewById(R.id.btn_send_message);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_message:
                mHandler.sendEmptyMessage(0);
            break;
        }
    }
}
