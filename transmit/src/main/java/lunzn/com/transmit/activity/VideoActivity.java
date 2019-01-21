package lunzn.com.transmit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import lunzn.com.transmit.R;

/**
 * Desc: 视屏播放VideoView
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/17 17:53
 */
public class VideoActivity extends Activity {

    private VideoView mVideoView;

    private MediaController mMediaController;

    private final static String TAG = "videoView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_controller_layout);
        initView();
        videoPlay("test.mp4");
    }

    private void initView() {
        mVideoView = findViewById(R.id.vv_play);
    }

    private void videoPlay(String videoName) {
        mMediaController = new MediaController(this);
        File file = new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"Download/"+videoName);
        Log.i(TAG,"filePath: "+file);
        if (file.exists()){
            mVideoView.setVideoPath(file.getAbsolutePath());
            mMediaController.setMediaPlayer(mVideoView);
            mVideoView.setMediaController(mMediaController);
            mVideoView.start();
            mVideoView.requestFocus();
        }else {
            Toast.makeText(this,"找不到文件",Toast.LENGTH_LONG).show();
        }

    }
}
