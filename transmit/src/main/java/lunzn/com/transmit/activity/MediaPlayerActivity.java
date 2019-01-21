package lunzn.com.transmit.activity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import lunzn.com.transmit.R;

import static lunzn.com.transmit.R.id;
import static lunzn.com.transmit.R.layout;

/**
 * Desc: MediaPlayer播放视频
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/21 11:39
 */
public class MediaPlayerActivity extends Activity implements View.OnClickListener,SurfaceHolder.Callback {

    // 播放
    private Button btnPlay;
    // 暂停
    private Button btnPause;
    // 停止
    private Button btnStop;

    private MediaPlayer mPlayer;

    private SurfaceView sfvShow;

    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.media_player_layout);
        initView();
    }

    private void initView() {
        btnPlay = findViewById(id.btn_play);
        btnPause = findViewById(id.btn_pause);
        btnStop = findViewById(id.btn_stop);
        sfvShow = findViewById(id.sfv_show);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);

        // 初始化surfaceHolder类
        mSurfaceHolder = sfvShow.getHolder();
        mSurfaceHolder.addCallback(this);
        // 设置分辨率
        mSurfaceHolder.setFixedSize(500,400);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case id.btn_play:
                mPlayer.start();
                break;
            case id.btn_pause:
                mPlayer.pause();
                break;
            case id.btn_stop:
                mPlayer.stop();
                break;
            default:

                break;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPlayer = MediaPlayer.create(MediaPlayerActivity.this,R.raw.test_video);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置视频显示在SurfaceView上
        mPlayer.setDisplay(mSurfaceHolder);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()){
            mPlayer.stop();
        }
        mPlayer.release();
    }
}
