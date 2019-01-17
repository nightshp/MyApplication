package lunzn.com.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.fragment.MyFragment;

/**
 * Desc: radioButton实现fragment切换
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/10 11:18
 */
public class MyFragmentActivity1 extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg;

    private MyFragment fg1,fg2,fg3,fg4;

    private FragmentManager mManager;

    private FrameLayout lyContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fgt_radio_menu_layout);
        mManager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        rg = findViewById(R.id.rg_tab_bar);
        lyContent = findViewById(R.id.ly_radio_content);
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (checkedId) {
            case R.id.rb_channel:
                if (fg1 == null) {
                    fg1 = new MyFragment("第一个Fragment");
                    fragmentTransaction.add(R.id.ly_radio_content, fg1);
                } else {
                    fragmentTransaction.show(fg1);
                }
                break;
            case R.id.rb_message:
                if (fg2 == null) {
                    fg2 = new MyFragment("第二个Fragment");
                    fragmentTransaction.add(R.id.ly_radio_content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            case R.id.rb_my:
                if (fg3 == null) {
                    fg3 = new MyFragment("第三个Fragment");
                    fragmentTransaction.add(R.id.ly_radio_content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                break;
            case R.id.rb_setting:
                if (fg4 == null) {
                    fg4 = new MyFragment("第四个Fragment");
                    fragmentTransaction.add(R.id.ly_radio_content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
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

}
