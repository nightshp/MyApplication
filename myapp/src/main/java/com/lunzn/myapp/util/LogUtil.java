package com.lunzn.myapp.util;

import android.util.Log;

/**
 * Desc: 打印日志工具类
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.util
 * ProjectName: MyApplication4
 * Date: 2019/1/25 14:41
 */
public class LogUtil {

    private final static String TAG = "fragment";

    public static void i(String content){
        Log.i(TAG,content);
    }
}
