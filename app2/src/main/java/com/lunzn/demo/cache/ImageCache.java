package com.lunzn.demo.cache;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc: 图片缓存
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.cache
 * ProjectName: MyApplication
 * Date: 2018/12/28 14:58
 */
public class ImageCache {

    // 图片缓存  key -- url 的 hashcode
    private static Map<String, Bitmap> imgCache = null;

    /**
     * 从缓存中获取图片
     *
     * @param url 图片下载地址
     * @return 内存中的图片
     */
    public static Bitmap getImageFromCache(String url) {
        Bitmap result = null;
        if (imgCache != null) {
            result = imgCache.get(url.hashCode() + "");
        }
        return result;
    }

    /**
     * 将图片添加至缓存
     *
     * @param url 图片下载地址
     * @param bm  图片实体
     */
    public static void setImageToCache(String url, Bitmap bm) {
        if (imgCache == null) {
            imgCache = new HashMap<String, Bitmap>();
        }
        imgCache.put(url.hashCode() + "", bm);
    }
}
