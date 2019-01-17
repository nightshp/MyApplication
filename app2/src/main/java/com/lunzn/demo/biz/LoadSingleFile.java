package com.lunzn.demo.biz;

import com.lunzn.demo.util.CommonUtil;
import com.lunzn.demo.util.FileSaveUtil;
import com.lunzn.demo.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.biz
 * ProjectName: MyApplication
 * Date: 2019/1/4 18:12
 */
public class LoadSingleFile {

    private final static String TAG = "loadfile";

    private LoadTask.ProgressUpdated mProgressUpdated = null;

    // 是否继续下载
    private boolean needLoad = true;

    public void setNeedLoad(boolean needLoad) {
        this.needLoad = needLoad;
    }

    public LoadSingleFile(LoadTask.ProgressUpdated mProgressUpdated) {
        this.mProgressUpdated = mProgressUpdated;
    }

    /**
     * 下载文件到本地
     * 0 - 任务空闲， 1 - 下载中， 2 - 下载成功，  3 -- 下载失败,  4 -- 暂停下载
     * @param path     文件下载地址
     * @param dirName  本地保存的文件目录
     * @param fileName 文件名称
     * @return 文件下载成功
     */

    public int loadFileToLocal(String path, String dirName, String fileName) {
        // 下载失败
        int result = 3;
        File dirFile = new File(FileSaveUtil.avaiLocalPath + File.separator + dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        InputStream in = null;
        OutputStream fos = null;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            in = connection.getInputStream();
            LogUtil.i(TAG, "loadFile in:  " + in);
            if (in != null) {
                // 下载中
                result = 1;
                double length = connection.getContentLength();
                LogUtil.i(TAG, "length:  " + length + "B");
                fos = new FileOutputStream(dirFile.getAbsoluteFile() + File.separator + fileName);
                byte[] buf = new byte[10 * 1024];
                int len = -1;
                double size = 0;
                int percent = 0;
                while ((len = in.read(buf)) > 0 && needLoad) {
                    fos.write(buf, 0, len);
                    size += len;
                    int rate = (int) ((size / length) * 100);
                    //  LogUtil.i(TAG, "rate:  " + rate);
                    if (rate > percent) {
                        percent = rate;
                        mProgressUpdated.onProgressUpdate(rate);
                    }
                }
                if (needLoad){
                    // 下载成功
                    result = 2;
                }else{ // 暂停下载

                    result = 4;
                }
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i(e.getMessage());
            result = 3;
        } finally {
            CommonUtil.closeStream(fos);
            CommonUtil.closeStream(in);
        }
        return result;

    }
}
