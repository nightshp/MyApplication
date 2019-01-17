package lunzn.com.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
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
public class SaveFileUtil {

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
        Log.i("OkHttpActivity","savepath "+savePath);
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

    /**
     * 保存文件
     * @param fileName 文件名
     * @param fileContent 文件内容
     * @param fileDir 文件目录
     */
    public static void saveFile(String fileName,String fileContent,String fileDir){
        String savePath = avaiLocalPath + File.separator + fileDir;
        File saveDir = new File(savePath);
        if (saveDir != null){
            saveDir.mkdirs();
        }
        String filePath = savePath + File.separator + fileName;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filePath));
            fos.write(fileContent.getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 读取文件中的内容
     * @param fileName 文件名
     * @return 文件内容字符串
     */
    public static String readFromFile(String fileName,String fileDir){
        String filePath = avaiLocalPath + File.separator + fileDir + File.separator + fileName;
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1024];
        try {
            fis = new FileInputStream(new File(filePath));
            int len = 0;
            while((len = fis.read(bytes))!= -1){
                sb.append(new String(bytes,0,len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



}
