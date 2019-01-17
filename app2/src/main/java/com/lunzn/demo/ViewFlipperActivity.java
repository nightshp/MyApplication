package com.lunzn.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.lunzn.demo.util.LogUtil;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo
 * ProjectName: MyApplication
 * Date: 2018/12/21 17:48
 */
public class ViewFlipperActivity extends Activity {

    private ViewFlipper vFlipper = null;

    private GestureDetector.SimpleOnGestureListener myGestureListener = null;

    private GestureDetector mDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper_layout);
        initView();
        initGestyreLinstener();
        addViewToFlipper();
    }

    private void initView() {
        vFlipper = (ViewFlipper) findViewById(R.id.vf_1);
    }

    private void initGestyreLinstener() {
        myGestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                LogUtil.i("onLongPress x:  " + e.getX() + ";  y:  " + e.getY());
                super.onLongPress(e);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                LogUtil.i("onFling stat x:  " + e1.getX() + ";  start y:  " + e1.getY() + ";  end x:  " + e2.getX() + ";  end y:  " + e2.getY());
                // 往右翻页
                if (e2.getX() - e1.getX() > 100) {
                    vFlipper.showPrevious();
                } else if (e1.getX() - e2.getX() > 100) {
                    // 往左翻页
                    vFlipper.showNext();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                LogUtil.i("onDown x:  " + e.getX() + ";  y:  " + e.getY());
                return super.onDown(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                LogUtil.i("onScroll stat x:  " + e1.getX() + ";  start y:  " + e1.getY() + ";  end x:  " + e2.getX() + ";  end y:  " + e2.getY());
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        };
        mDetector = new GestureDetector(this, myGestureListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    /**
     * 往flipper中添加布局
     */
    private void addViewToFlipper() {
        int[] resAry = new int[]{R.mipmap.image_1, R.mipmap.image_2, R.mipmap.image_3, R.mipmap.image_4, R.mipmap.image_5, R.mipmap.image_6};
        for (int resId : resAry) {
            addImageView(resId);
        }
    }

    /**
     * 根据图片创建imageView，并添加到Flipper中
     *
     * @param resId 图片的资源id
     */
    private void addImageView(int resId) {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(resId);
        vFlipper.addView(imageView);
    }
}
