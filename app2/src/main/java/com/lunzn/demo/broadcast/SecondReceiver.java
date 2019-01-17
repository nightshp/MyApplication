package com.lunzn.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lunzn.demo.util.LogUtil;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.broadcast
 * ProjectName: MyApplication
 * Date: 2019/1/2 17:41
 */
public class SecondReceiver extends BroadcastReceiver {

    private final static String TAG = "LessonService";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.i(TAG, "SecondReceiver receiver action：  " + intent.getAction());
        abortBroadcast();
    }
}
