package lunzn.com.myapplication.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.util.SaveFileUtil;
import lunzn.com.myapplication.util.StreamTool;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/14 15:37
 */
public class OkHttpActivity extends Activity implements View.OnClickListener {
    private ImageView mImageView;

    private Button btnDownload;

    private static final String TAG = "OkHttpActivity";


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mImageView.setImageBitmap((Bitmap) msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_file_download_layout);
        mImageView = findViewById(R.id.iv_img);
        btnDownload = findViewById(R.id.btn_ok);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                download("http://img5.imgtn.bdimg.com/it/u=935292084,2640874667&fm=26&gp=0.jpg");
                break;
            default:

                break;
        }
    }
    /**
     * 文件下载
     * @param url 文件下载地址
     */
    public void download(final String url){
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
                Log.i(TAG,"download fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 将响应数据转化为输入流数据
                InputStream inputStream = response.body().byteStream();
                try {
                    SaveFileUtil.init(OkHttpActivity.this);
                    byte[] bytes = StreamTool.read(inputStream);
                    SaveFileUtil.saveImage(url,bytes);

                    inputStream.reset();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Message message = Message.obtain();
                    message.obj = bitmap;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
