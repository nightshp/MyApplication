package com.lunzn.demo.interfaces;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.interfaces
 * ProjectName: MyApplication
 * Date: 2019/1/4 17:45
 */
public interface UpdateProgressCallback {

    public void onProgressUpdate(int pos, int i);

    public void onStateUpdate(int pos, int state);
}
