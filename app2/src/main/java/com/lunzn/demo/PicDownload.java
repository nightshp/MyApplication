package com.lunzn.demo;

import android.os.Handler;
import android.os.Message;

import com.lunzn.demo.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Desc: 图片下载
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo
 * ProjectName: MyApplication
 * Date: 2018/12/26 15:44
 */
public class PicDownload implements Runnable{

    private String mUrl;
    private String mPath;
    private Handler mHandler;

    public PicDownload(String url, String path, Handler handler) {
        mUrl = url;
        mPath = path;
        mHandler = handler;
    }

    @Override
    public void run() {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            LogUtil.i("下载路径 " + mPath);
            fos = new FileOutputStream(new File(mPath));
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
            // io 操作
            byte[] buff = new byte[1024];
            int len;
            while ((len = is.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
            LogUtil.i("下载完成 ");
            Message msg = Message.obtain();
            msg.obj = mPath;
            msg.what = MainActivity.WHAT_4;
            mHandler.sendMessage(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
