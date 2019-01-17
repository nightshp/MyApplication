package com.lunzn.demo.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.lunzn.demo.LessonService;
import com.lunzn.demo.R;
import com.lunzn.demo.bean.Article;
import com.lunzn.demo.broadcast.LessonReceiver;
import com.lunzn.demo.broadcast.SecondReceiver;
import com.lunzn.demo.constasts.ActionConstanst;
import com.lunzn.demo.interfaces.UpdateProgressCallback;
import com.lunzn.demo.util.FileSaveUtil;
import com.lunzn.demo.util.LogUtil;
import com.lunzn.demo.view.LoadTaskListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: listview 演示activity
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.activity
 * ProjectName: MyApplication
 * Date: 2018/12/28 16:02
 */
public class ListViewActivity extends Activity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private final static String TAG = "loadfile";

    // 显示listview
//    private ListView mListView = null;

    // 适配器
//    private MyAdapter mAdapter = null;

    // 测试数据
    private List<Article> arList = null;

    // service实例
    private LessonService.MyBinder mBinder = null;

    private LessonReceiver mReceiver = null;

    private SecondReceiver mSecondReceiver = null;

    private LoadTaskListView mTaskListView = null;


    private UpdateProgressCallback mUpdateProgressCallback = new UpdateProgressCallback() {
        @Override
        public void onProgressUpdate(int pos, int i) {
            LogUtil.i(TAG, "ListViewActivity onProgressUpdate pos:  " + pos + "; progress:  " + i);
//            Article article = mAdapter.getItem(pos);
//            article.setProgress(i);
//            mAdapter.notifyDataSetChanged();
            mTaskListView.updateProgress(pos, i);
        }

        @Override
        public void onStateUpdate(int pos, int state) {
            LogUtil.i(TAG, "ListViewActivity onStateUpdate pos:  " + pos + "; state:  " + state);
//            Article article = mAdapter.getItem(pos);
//            article.setState(state);
//            mAdapter.notifyDataSetChanged();
            mTaskListView.updateState(pos, state);
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.i(TAG, "onServiceConnected service:  " + service);
            mBinder = (LessonService.MyBinder) service;
            mBinder.setUpdateProgressCallback(mUpdateProgressCallback);
            //mAdapter.setBinder(mBinder);
            mTaskListView.setBinder(mBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.i(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_layout);
        FileSaveUtil.init(this);
        initView();
        initTestData();
        bindService();
        LogUtil.i(TAG, "ListViewActivity onCreate");
        //    registerLessonReceiver();
    }

    private void initView() {
//        mListView = findViewById(R.id.lv_lesson_1);
//        mAdapter = new MyAdapter(this, mListView);
//        mListView.setOnScrollListener(this);
//        mListView.setOnItemClickListener(this);
      //  mListView.setAdapter(mAdapter);
        mTaskListView = findViewById(R.id.lv_load_task);
    }

    private void startService() {
        Intent intent = new Intent();
        intent.setAction("com.example.lesson");
        intent.setPackage(getPackageName());
        intent.putExtra("command", "download");
        intent.setData(Uri.parse("http:111111"));
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.example.lesson");
        intent.setPackage(getPackageName());
        intent.setData(Uri.parse("http:111111"));
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private void registerLessonReceiver() {
        try {
            mReceiver = new LessonReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.example.lesson.11");
            filter.addAction("com.example.lesson.22");
            registerReceiver(mReceiver, filter);
            LogUtil.i(TAG, "register second receiver");
            mSecondReceiver = new SecondReceiver();
            IntentFilter secondFilter = new IntentFilter();
            secondFilter.addAction("com.example.lesson.11");
            secondFilter.addAction("com.example.lesson.22");
            registerReceiver(mSecondReceiver, secondFilter);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i(TAG, "register exception");
        }
    }

    /**
     * 初始化测试数据
     */
    private void initTestData() {
        arList = new ArrayList<Article>();
        String[] fileArr = new String[]{
                "http://www.runoob.com/wp-content/uploads/2015/09/MulThreadContinuableDemo2-music.zip",
                "http://www.winrar.com.cn/download/winrar-x64-561scp.exe",
                "http://static.runoob.com/download/MediaPlayerDemo.zip",
                "http://browser.cqttech.com/file/ChromeCore_1215_1.0.2.16.exe"
        };
        Article article;
        for (int i = 0; i < fileArr.length; i++) {
            article = new Article();
            article.setTitle(fileArr[i].substring(fileArr[i].lastIndexOf("/") + 1));
            article.setUrl(fileArr[i % fileArr.length]);
            article.setId(i + 1);
            arList.add(article);
        }
        // 更新数据
      //  mAdapter.setData(arList);
        for (Article mArticle : arList){
            mTaskListView.addTaskView(mArticle);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            LogUtil.i("onscroll", "listview idle state");
//            mAdapter.setNeedLoadImage(true);
//            mAdapter.update();
        } else if (scrollState == SCROLL_STATE_FLING) {
            LogUtil.i("onscroll", "listview fling state");
//            mAdapter.setNeedLoadImage(false);
        } else if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            LogUtil.i("onscroll", "listview scroll state");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        LogUtil.i("listview onScroll ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG, "connection:  " + mConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  getApplicationContext().unbindService(mConnection);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            sendLessonBroadCast(ActionConstanst.LESSON_ACTION_1);
        } else if (position == 1) {
            sendLessonBroadCast(ActionConstanst.LESSON_ACTION_2);
        }
    }

    private void sendLessonBroadCast(String action) {
        Intent intent = new Intent(action);
        sendBroadcast(intent);
    }
}
