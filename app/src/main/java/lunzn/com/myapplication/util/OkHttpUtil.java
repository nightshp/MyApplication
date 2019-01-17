package lunzn.com.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Desc: OkHttpClient下载工具类
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.util
 * ProjectName: MyApplication4
 * Date: 2019/1/14 15:00
 */
public class OkHttpUtil {

    /**
     * 文件下载
     * @param url 文件下载地址
     */
    public static Bitmap download(String url){
        final Bitmap[] bitmap = {null};
        // 获取OKhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                                     .get()
                                     .url(url)
                                     .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 将响应数据转化为输入流数据
                InputStream inputStream = response.body().byteStream();
                // 将输入流转化为bitmap
                bitmap[0] = BitmapFactory.decodeStream(inputStream);
                File file = new File("/mnt/sdcard/Pictures/picture.jpg");
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                bitmap[0].compress(Bitmap.CompressFormat.JPEG,100,fos);
                fos.close();
                fos.flush();
            }
        });
        return bitmap[0];
    }
}
