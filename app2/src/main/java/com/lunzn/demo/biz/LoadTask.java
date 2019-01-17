package com.lunzn.demo.biz;

import android.os.AsyncTask;
import android.util.Log;

import com.lunzn.demo.bean.Article;
import com.lunzn.demo.interfaces.UpdateProgressCallback;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.biz
 * ProjectName: MyApplication
 * Date: 2019/1/4 17:43
 */
public class LoadTask extends AsyncTask<Article, Integer, Integer> {

    private final static String TAG = "loadfile";

    private UpdateProgressCallback mCallback = null;

    // 当先任务对应的列表索引位置
    private int pos = -1;

    private LoadSingleFile mLoadSingleFile = null;

    private ProgressUpdated mProgressUpdated = new ProgressUpdated() {
        @Override
        public void onProgressUpdate(int i) {
            publishProgress(i);
        }
    };

    public interface ProgressUpdated {
        void onProgressUpdate(int i);
    }

    public void stopLoad() {
        mLoadSingleFile.setNeedLoad(false);
    }

    public LoadTask(UpdateProgressCallback mCallback, int pos) {
        this.mCallback = mCallback;
        this.pos = pos;
        this.mLoadSingleFile = new LoadSingleFile(mProgressUpdated);
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        mCallback.onStateUpdate(pos, result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mCallback.onProgressUpdate(pos, values[0]);
        mCallback.onStateUpdate(pos, 1);
    }

    @Override
    protected void onCancelled(Integer aBoolean) {
        super.onCancelled(aBoolean);
        mCallback.onStateUpdate(pos, 3);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mCallback.onStateUpdate(pos, 3);
    }

    @Override
    protected Integer doInBackground(Article... articles) {
        Log.i(TAG, "LoadTask doInBackground:  " + articles[0].toString());
        return mLoadSingleFile.loadFileToLocal(articles[0].getUrl(), "files", articles[0].getTitle());
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCallback.onProgressUpdate(pos, 1);
    }
}
