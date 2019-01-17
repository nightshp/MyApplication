package com.lunzn.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Desc: 加载图片工具类
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.util
 * ProjectName: MyApplication
 * Date: 2018/12/28 14:24
 */
public class HttpUtil {

    /**
     * 下载文件
     *
     * @param imgPath 下载路径
     * @return 文件字节数组
     */
    public static byte[] loadFile(String imgPath) {
        InputStream in = null;
        byte[] result = null;
        try {
            URL url = new URL(imgPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            in = connection.getInputStream();
            LogUtil.i("loadFile in:  " + in);
            if (in != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int len = -1;
                while ((len = in.read(buf)) > 0) {
                    bos.write(buf, 0, len);
                }
                in.close();
                result = bos.toByteArray();
                FileSaveUtil.saveImage(imgPath, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i(e.getMessage());
        }
        return  result;
    }


}
