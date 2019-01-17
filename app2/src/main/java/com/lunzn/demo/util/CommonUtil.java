package com.lunzn.demo.util;

import java.io.Closeable;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.util
 * ProjectName: MyApplication
 * Date: 2019/1/4 17:33
 */
public class CommonUtil {

    /**
     * 关闭流
     */
    public static void closeStream(Closeable mStream){
        if (mStream != null){
            try{
                mStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
