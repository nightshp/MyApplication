package lunzn.com.transmit.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import lunzn.com.transmit.R;

/**
 * Desc: VideoView播放视频
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/21 13:59
 */
public class VideoViewActivity extends Activity implements View.OnClickListener {

    private Button btnPlay;

    private Button btnPause;

    private Button btnStop;

    private VideoView vvShow;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_view_layout);
        initView();
    }

    private void initView() {
        btnPlay = findViewById(R.id.btn_play_1);
        btnPause = findViewById(R.id.btn_pause_1);
        btnStop = findViewById(R.id.btn_stop_1);
        vvShow = findViewById(R.id.vv_play);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        verifyStoragePermissions(this);
        // 根据文件路劲播放
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            vvShow.setVideoPath(Environment.getExternalStorageDirectory()+"/Download/test.mp4");
        }

        // 读取放在raw目录下的文件
//        vvShow.setVideoURI(Uri.parse("android.resource://lunzn.com.transmit/" + R.raw.test_video));
        vvShow.setMediaController(new MediaController(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_1:
                vvShow.start();
                break;
            case R.id.btn_pause_1:
                vvShow.pause();
                break;
            case R.id.btn_stop_1:
                vvShow.stopPlayback();
                break;
            default:

                break;
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
}
