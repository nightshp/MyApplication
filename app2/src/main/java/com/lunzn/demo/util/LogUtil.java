package com.lunzn.demo.util;

import android.util.Log;

/**
 * Desc: 日志打印
 * <p>
 * Author: Administrator
 * PackageName: com.lunzn.demo.util
 * ProjectName: MyApplication
 * Date: 2018/12/20 16:53
 */
public class LogUtil {
    private static final String TAG = "LogUtil_";

    public static void i(String msg){
        Log.i(TAG, msg);
    }

    public static void i(String tag, String msg){
        Log.i(tag, msg);
    }


}
