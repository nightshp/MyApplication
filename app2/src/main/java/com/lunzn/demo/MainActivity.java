package com.lunzn.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.lunzn.demo.adapter.MyAdapter;
import com.lunzn.demo.bean.Article;
import com.lunzn.demo.util.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {


    public static final int WHAT_1 = 1;
    public static final int WHAT_2 = 2;
    public static final int WHAT_3 = 3;
    public static final int WHAT_4 = 4;

    private ImageView mImage;
    private TextView mHtml;

    /*
            1. Handler 用法
            2. implements Runnable, extends Thread, Executors.
            3. 图片下载
                HttpClient(6.0)
                HttpUrlConnection
             */
    private TextView tvHeader = null;

//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            return false;
//        }
//    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHtml = findViewById(R.id.tv_main_html);
        View button = findViewById(R.id.btn_main_refresh);
        findViewById(R.id.btn_main_download).setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("onClick " + Thread.currentThread().getName());
                new MyThread(mHandler).start();

            }
        });
        LogUtil.i("onCreate " + Thread.currentThread().getName()); // main 主线程，ui线程

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 耗时操作
                try {
                    Thread.sleep(5000); //anr
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Message msg = new Message();
                Message msg = Message.obtain();
                msg.what = WHAT_1;
                msg.obj = "耗时操作完成";
//                mHandler.sendMessageDelayed(msg, 1000);
                mHandler.removeMessages(WHAT_2);
                mHandler.sendEmptyMessage(WHAT_2);
//                mHandler.sendMessage(msg);
            }
        }).start();
        LogUtil.i("onCreate end " + Thread.currentThread().getName());


        mImage = findViewById(R.id.iv_main_img);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mImage.setImageResource(R.drawable.dog);
//            }
//        }).start();


        try {
            io();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        init();
    }

    @SuppressWarnings("handlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            Object obj = msg.obj;
            LogUtil.i("what " + what + ", obj " + obj + ", thread name :" + Thread.currentThread().getName());

            switch (msg.what) {
                case WHAT_1:

                    break;
                case WHAT_2:

//                    mImage.setImageResource(R.drawable.dog);
                    break;
                case WHAT_3:
                    if (msg.obj instanceof String) {
                        String obj1 = (String) msg.obj;
                        mHtml.setText(obj1);
                    }
                    break;
                case WHAT_4:

                    String picPath = (String) msg.obj;

                    Bitmap bitmap = BitmapFactory.decodeFile(picPath);
                    LogUtil.i("bitmap " + bitmap.getWidth() + ", " + bitmap.getHeight());
                    compress(picPath);

                    break;
            }
        }
    };

    private void compress(String picPath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(picPath, opt);
        int outWidth = opt.outWidth;
        int outHeight = opt.outHeight;

        LogUtil.i("bitmap " + bitmap + ", outWidth " + outWidth + ", outHeight " + outHeight);

        float i = outWidth * 1.0f / 100;
        float i1 = outHeight * 1.0f / 100;
        float scale = Math.min(i, i1);
        LogUtil.i("i " + i + ", i1 " + i1 + ", scale " + scale);

        opt.inSampleSize = (int) scale;
        opt.inJustDecodeBounds = false;
        Bitmap bitmap1 = BitmapFactory.decodeFile(picPath, opt);
        LogUtil.i("bitmap1 " + bitmap1.getWidth() + ", " + bitmap1.getHeight());
        mImage.setImageBitmap(bitmap1);
    }


    public void io() throws IOException {
        LogUtil.i("读取内容 io");
        File dir = getFilesDir();// data/data/com.lunzn.demo/files/test.txt

        // 读外存权限
        File extDir = Environment.getExternalStorageDirectory();// /sdcard/
        FileInputStream fis = new FileInputStream(new File(extDir, "test.txt"));
        byte[] buff = new byte[1024];
        int len;
        Reader reader;
        Writer writer;

        FileReader fr;
        FileWriter fw;

        BufferedReader br;
        BufferedWriter bw;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = fis.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }

        byte[] bytes = baos.toByteArray();
        String s = new String(bytes);
        LogUtil.i("读取内容" + s);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_download:
                // 点击下载
                String url = "http://www.runoob.com/wp-content/uploads/2015/07/88843756.jpg";
                String path = "/sdcard/download/88843756.jpg";
                PicDownload download = new PicDownload(url, path, mHandler);
                new Thread(download).start();

                int width = mImage.getWidth();
                int height = mImage.getHeight();

                Toast.makeText(this, "width " + width + ", height " + height, Toast.LENGTH_LONG).show();

                break;
        }
    }


    static class MyThread extends Thread {
        private Handler mThreadHandler;

        public MyThread(Handler handler) {
            mThreadHandler = handler;
        }

        @Override
        public void run() {
            InputStream is = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                URL url = new URL("http://www.baidu.com");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(3000);
                conn.setRequestMethod("GET");
                is = conn.getInputStream();

                // io 操作
                byte[] buff = new byte[1024];
                int len;
                while ((len = is.read(buff)) != -1) {
                    baos.write(buff, 0, len);
                }

                String html = new String(baos.toByteArray());
                LogUtil.i("百度网页 " + html);

                Message msg = Message.obtain();
                msg.what = MainActivity.WHAT_3;
                msg.obj = html;
                mThreadHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void init() {
        final ListView articleListView = findViewById(R.id.lv_maim_article);
        final MyAdapter myAdapter = new MyAdapter(this, articleListView);
        articleListView.setAdapter(myAdapter);
        articleListView.addHeaderView(initHeadView());
        articleListView.addFooterView(initHeadView());

        View view = null;
        articleListView.setEmptyView(view);
        articleListView.setOnItemClickListener(this);

        List<Article> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            Article article = new Article();
            article.setTitle("android 编程 " + i);
         //   article.setId(R.drawable.dog);
            // article.setChecked(i % 3 == 0);

            data.add(article);
        }
        myAdapter.setData(data);
        // ViewFlipper ViewPager ViewSwitcher
        findViewById(R.id.btn_main_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Article item = myAdapter.getItem(0);
                //   item.setTitle("aaaaaaaaaaaa");
                //   myAdapter.notifyDataSetChanged();
                View childAt = articleListView.getChildAt(1);
                TextView text = childAt.findViewById(R.id.tv_item_title);
                text.setText("bbbbbbbbb");
            }
        });
        findViewById(R.id.btn_main_refresh).setVisibility(View.GONE);
    }

    private RelativeLayout initHeadView() {
        RelativeLayout reLayout = new RelativeLayout(this);
        tvHeader = new MarqueeTextView(this);
        tvHeader.setTextColor(Color.parseColor("#000000"));
        tvHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        tvHeader.setSingleLine();
        tvHeader.setFocusable(true);
        tvHeader.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvHeader.setMarqueeRepeatLimit(-1);
        tvHeader.setText("测试标题的说法是打发打发时间的发动机的就罚款的访客卡都是看到了健康的");
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        reLayout.addView(tvHeader, lp);
        return reLayout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mHandler.removeMessages(1);
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtil.i("position:  " + position);
    }
}
