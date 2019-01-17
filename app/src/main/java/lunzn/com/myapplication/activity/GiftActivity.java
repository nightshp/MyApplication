package lunzn.com.myapplication.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import lunzn.com.myapplication.R;

/**
 * Desc: 帧动画
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/14 17:00
 */
public class GiftActivity extends Activity implements View.OnClickListener {

    private Button btnStart;

    private Button btnStop;

    private ImageView mImageView;

    private AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.girl_gift_layout);
        initView();
        anim = (AnimationDrawable) mImageView.getBackground();
    }

    private void initView() {
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        mImageView = findViewById(R.id.iv_gift);
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                anim.start();
                break;
            case R.id.btn_stop:
                anim.stop();
                break;
            default:

                break;
        }
    }
}
