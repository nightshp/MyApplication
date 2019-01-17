package com.lunzn.demo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lunzn.demo.cache.ImageCache;

/**
 * Desc: 图片处理工具类
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.util
 * ProjectName: MyApplication
 * Date: 2018/12/28 14:45
 */
public class BitmapUtil {

    /**
     * 加载图片
     *
     * @param url       图片下载地址
     * @param imgWdith  图片宽度
     * @param imgHeight 图片高度
     * @return 指定大小的图片
     */
    public Bitmap loadImage(String url, int imgWdith, int imgHeight) {
        // 从缓存中获取图片
        Bitmap result = ImageCache.getImageFromCache(url);
        LogUtil.i("loadImage", "load from cache image:  " + result);
        if (result == null) {
            // 读取本地图片
            result = FileSaveUtil.getImage(url);
            LogUtil.i("loadImage", "load from disk image:  " + result);
        }
        if (result == null) {
            // 从网络下载
            result = downloadImageFromNet(url, imgWdith, imgHeight);
            LogUtil.i("loadImage", "load from net image:  " + result);
        }
        return result;
    }

    /**
     * 下载图片
     *
     * @param url       图片下载地址
     * @param imgWdith  图片宽度
     * @param imgHeight 图片高度
     * @return 指定大小的图片
     */
    private Bitmap downloadImageFromNet(String url, int imgWdith, int imgHeight) {
        Bitmap result = null;
        byte[] buf = HttpUtil.loadFile(url);
        LogUtil.i("downloadImageFromNet buf:  " + (buf == null ? 0 : buf.length));
        if (buf != null && buf.length > 0) {
            try {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                Bitmap oriBitmap = BitmapFactory.decodeByteArray(buf, 0, buf.length, options);
//                float rateW = imgWdith / oriBitmap.getWidth();
//                float rateH = imgHeight / oriBitmap.getHeight();
//                LogUtil.i("downloadImageFromNet rateW:  " + rateW + "; rateH: " + rateH);
//                options.inJustDecodeBounds = false;
//                options.inSampleSize = (int) (rateW > rateH ? rateW : rateH);
//                result = BitmapFactory.decodeByteArray(buf, 0, buf.length, options);
                result = BitmapFactory.decodeByteArray(buf, 0, buf.length);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.i("downloadImageFromNet exception:  " + e.getMessage());
            }
        }
        return result;
    }

}
