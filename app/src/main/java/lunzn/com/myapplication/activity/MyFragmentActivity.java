package lunzn.com.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.fragment.MyFragment;

/**
 * Desc: fragment实例
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/8 11:07
 */
public class MyFragmentActivity extends FragmentActivity implements View.OnClickListener {
    // 布局UI
    private FrameLayout lyContent;

    private TextView tvChannel;

    private TextView tvMy;

    private TextView tvSetting;

    private TextView tvMessage;

    // fragment
    private FragmentManager mFragmentManager;

    private MyFragment fg1, fg2, fg3, fg4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_menu_layout);
        mFragmentManager = getSupportFragmentManager();
        initView();
        // 进入界面选择第一项
        tvChannel.performLongClick();
    }

    /**
     * 初始化view
     */
    private void initView() {
        tvChannel = findViewById(R.id.txt_channel);
        tvMessage = findViewById(R.id.txt_message);
        tvMy = findViewById(R.id.txt_my);
        tvSetting = findViewById(R.id.txt_setting);
        lyContent = findViewById(R.id.ly_content);

        tvChannel.setOnClickListener(this);
        tvMessage.setOnClickListener(this);
        tvMy.setOnClickListener(this);
        tvSetting.setOnClickListener(this);

    }

    /**
     * 重置所有文本选中状态
     */
    public void setSelect() {
        tvChannel.setSelected(false);
        tvMessage.setSelected(false);
        tvMy.setSelected(false);
        tvSetting.setSelected(false);
    }

    /**
     * 隐藏所有fragment
     */
    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) { fragmentTransaction.hide(fg1); }
        if (fg2 != null) { fragmentTransaction.hide(fg2); }
        if (fg3 != null) { fragmentTransaction.hide(fg3); }
        if (fg4 != null) { fragmentTransaction.hide(fg4); }
    }

    /**
     * 点击事件
     *
     * @param v 控件
     */
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (v.getId()) {
            case R.id.txt_channel:
                setSelect();
                tvChannel.setSelected(true);
                if (fg1 == null) {
                    fg1 = new MyFragment("第一个Fragment");
                    fragmentTransaction.add(R.id.ly_content, fg1);
                } else {
                    fragmentTransaction.show(fg1);
                }
                break;
            case R.id.txt_message:
                setSelect();
                tvMessage.setSelected(true);
                if (fg2 == null) {
                    fg2 = new MyFragment("第二个Fragment");
                    fragmentTransaction.add(R.id.ly_content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            case R.id.txt_my:
                setSelect();
                tvMy.setSelected(true);
                if (fg3 == null) {
                    fg3 = new MyFragment("第三个Fragment");
                    fragmentTransaction.add(R.id.ly_content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                break;
            case R.id.txt_setting:
                setSelect();
                tvSetting.setSelected(true);
                if (fg4 == null) {
                    fg4 = new MyFragment("第四个Fragment");
                    fragmentTransaction.add(R.id.ly_content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
