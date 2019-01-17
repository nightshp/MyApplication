package com.lunzn.demo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Desc: 文件存储工具类
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.util
 * ProjectName: MyApplication
 * Date: 2018/12/28 17:59
 */
public class FileSaveUtil {

    // 可以的本地存储路径
    public static String avaiLocalPath = null;

    public static void init(Context mContext) {
        avaiLocalPath = getSavePath(mContext);
    }

    /**
     * 获取可读写执行的存储目录
     *
     * @param mContext 上下文
     * @return 本地的存储目录
     */
    private static String getSavePath(Context mContext) {
        // 检测外部存储是否可用
        String result = null;
        File exterFile = mContext.getExternalCacheDir();
        if (exterFile.exists() && exterFile.canRead() && exterFile.canWrite() && exterFile.canExecute()) {
            result = exterFile.getAbsolutePath();
        } else {
            File innerFile = mContext.getCacheDir();
            if (innerFile.exists() && innerFile.canRead() && innerFile.canExecute() && innerFile.canWrite()) {
                result = innerFile.getAbsolutePath();
            }
        }
        return result;
    }

    /**
     * 将文件根据其文件名保存在image目录下
     *
     * @param url  图片下载地址
     * @param data 图片数据
     */
    public static void saveImage(String url, byte[] data) {
        String savePath = avaiLocalPath + "/image/";
        File saveDir = new File(savePath);
        if (saveDir != null) {
            saveDir.mkdirs();
        }
        String filePath = savePath + url.hashCode();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 根据url获取本地存储的图片
     *
     * @param url 图片下载地址
     * @return 图片实体
     */
    public static Bitmap getImage(String url) {
        Bitmap result = null;
        String filePath = avaiLocalPath + "/image/" + url.hashCode();
        if (new File(filePath).exists()) {
            result = BitmapFactory.decodeFile(filePath);
        }
        return result;
    }


}
