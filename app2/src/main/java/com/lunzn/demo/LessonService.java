package com.lunzn.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.lunzn.demo.bean.Article;
import com.lunzn.demo.biz.LoadFileBiz;
import com.lunzn.demo.interfaces.IDoadloadFile;
import com.lunzn.demo.interfaces.UpdateProgressCallback;
import com.lunzn.demo.util.LogUtil;

import java.io.Serializable;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo
 * ProjectName: MyApplication
 * Date: 2018/12/29 17:49
 */
public class LessonService extends Service implements Serializable {

    private final static String TAG = "LessonService";

    private MyBinder mBinder = new MyBinder();

    private LoadFileBiz mLoadFileBiz = null;

    private UpdateProgressCallback mUpdateProgressCallback = null;

    public class MyBinder extends Binder implements IDoadloadFile {

        @Override
        public void startLoad(int position, Article mArticle) {
            mLoadFileBiz.loadFile(mArticle, position, mUpdateProgressCallback);
        }

        @Override
        public void stopLoad(int position) {
            mLoadFileBiz.stop(position);
        }

        @Override
        public void delete(int position) {

        }
        public void setUpdateProgressCallback(UpdateProgressCallback mCallback){
            mUpdateProgressCallback = mCallback;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.i(TAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        LogUtil.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onCreate() {
        LogUtil.i(TAG, "onCreate");
        super.onCreate();
        mLoadFileBiz = new LoadFileBiz();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i(TAG, "onStartCommand");
        if (intent != null) {
            String data = intent.getDataString();
            String command = intent.getStringExtra("command");
            LogUtil.i(TAG, "data:  " + data + ";  command: " + command);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.i(TAG, "onDestroy");
        super.onDestroy();
    }

    public int add(int num1, int num2){
        return num1 + num2;
    }
}
