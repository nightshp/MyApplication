package com.lunzn.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lunzn.demo.constasts.ActionConstanst;
import com.lunzn.demo.util.LogUtil;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.broadcast
 * ProjectName: MyApplication
 * Date: 2019/1/2 17:12
 */
public class LessonReceiver extends BroadcastReceiver {

    private final static String TAG = "LessonReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {

            case ActionConstanst
                    .LESSON_ACTION_1:
                LogUtil.i(TAG, "receiver action：  " + action);
                break;

            case ActionConstanst
                    .LESSON_ACTION_2:
                LogUtil.i(TAG, "receiver action：  " + action);
                break;

        }
    }
}
