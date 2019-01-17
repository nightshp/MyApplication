package com.lunzn.demo.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.lunzn.demo.cache.ImageCache;

/**
 * Desc: 加载图片并显示
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.util
 * ProjectName: MyApplication
 * Date: 2018/12/28 15:16
 */
public class DisplayImageUtil {

    private final static String TAG = "displayimage";

    // 加载图片工具类
    private BitmapUtil mBitmapUtil = null;

    public DisplayImageUtil() {
        mBitmapUtil = new BitmapUtil();
    }


    /**
     * 加载图片并显示
     *
     * @param view   显示图片控件
     * @param url    下载地址
     * @param width  图片显示宽度
     * @param height 图片显示高度
     */
    public void displayImage(final View view, final String url, final int width, final int height) {

        AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {

            //onPreExecute用于异步处理前的操作
            @Override
            protected void onPreExecute() {
                LogUtil.i("start load image ,url:  " + url);
            }

            //onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                LogUtil.i("onPostExecute bitmap:  " + bitmap);
                if (ImageCache.getImageFromCache(url) == null) {
                    ImageCache.setImageToCache(url, bitmap);
                }
                if (view != null && bitmap != null) {
                    if (view instanceof ImageView) {
                        ((ImageView) view).setImageBitmap(bitmap);
                    } else {
                        view.setBackground(new BitmapDrawable((bitmap)));
                    }
                }
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(Bitmap bitmap) {
                super.onCancelled(bitmap);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            //在doInBackground方法中进行异步任务的处理
            @Override
            protected Bitmap doInBackground(String... strings) {
                return mBitmapUtil.loadImage(url, width, height);
            }
        };

        //通过调用execute方法开始处理异步任务.相当于线程中的start方法
        task.execute(url);
    }


}
