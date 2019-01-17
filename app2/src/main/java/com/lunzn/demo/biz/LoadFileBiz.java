package com.lunzn.demo.biz;

import android.util.Log;

import com.lunzn.demo.bean.Article;
import com.lunzn.demo.interfaces.UpdateProgressCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.biz
 * ProjectName: MyApplication
 * Date: 2019/1/4 17:42
 */
public class LoadFileBiz {

    private Map<Integer, LoadTask> taskMap = null;

    private Executor mExecutor = null;

    private final static String TAG = "loadfile";

    public LoadFileBiz() {
        taskMap = new HashMap<Integer, LoadTask>();
        mExecutor = Executors.newFixedThreadPool(2);
    }

    /**
     * 下载文件
     *
     * @param mArticle  文件信息
     * @param pos       任务在列表中的索引
     * @param mCallback 下载状态回调
     */
    public void loadFile(Article mArticle, int pos, UpdateProgressCallback mCallback) {
        Log.i(TAG,"loadFile pos "+pos);
        LoadTask mTask = new LoadTask(mCallback, pos);
        taskMap.put(pos, mTask);
        mTask.executeOnExecutor(mExecutor, mArticle);
    }

    /**
     * 停止下载
     *
     * @param pos 列表位置索引
     */
    public void stop(int pos) {
        LoadTask mTask = taskMap.get(pos);
        if (mTask != null) {
            mTask.stopLoad();
        }
    }

    public void delete(int pos) {

    }


}
