package com.lunzn.myapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lunzn.myapp.R;
import com.lunzn.myapp.adapter.MyFragmentAdapter;
import com.lunzn.myapp.fragment.MyFragment1;
import com.lunzn.myapp.fragment.MyFragment2;
import com.lunzn.myapp.fragment.MyFragment3;
import com.lunzn.myapp.fragment.MyFragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 主界面
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/22 14:53
 */
public class MyFragmentActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener {

    // 代表界面的常量
    public static final int PAGER_ONE = 0;

    public static final int PAGER_TWO = 1;

    public static final int PAGER_THREE = 2;

    public static final int PAGER_FOUR = 3;

    private RadioGroup rgTab;
    // 提醒
    private RadioButton rbChannel;
    // 信息
    private RadioButton rbMessage;
    // 我的
    private RadioButton rbMy;
    // 设置
    private RadioButton rbSetting;

    private ViewPager mViewPager;

    private MyFragmentAdapter mAdapter;

    private List<Fragment> mFragmentList = null;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgt_radio_menu_layout);
        initView();
    }

    private void initView() {
        rgTab = findViewById(R.id.rg_tab_bar);
        rbChannel = findViewById(R.id.rb_channel);
        rbMessage = findViewById(R.id.rb_message);
        rbMy = findViewById(R.id.rb_my);
        rbSetting = findViewById(R.id.rb_setting);
        rgTab.setOnCheckedChangeListener(this);

        mViewPager = findViewById(R.id.vp_radio_content);
        initData();
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initData() {
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new MyFragment1());
        mFragmentList.add(new MyFragment2());
        mFragmentList.add(new MyFragment3());
        mFragmentList.add(new MyFragment4());
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager(),mFragmentList);
        // 预加载左右两边各三个
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    /**
     * 滑动操作
     * @param state 0表示什么都没干，1表示正在滑动，2表示滑动结束
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2){
            switch (mViewPager.getCurrentItem()) {
                case PAGER_ONE:
                    rbChannel.setChecked(true);
                    break;
                case PAGER_TWO:
                    rbMessage.setChecked(true);
                    break;
                case PAGER_THREE:
                    rbMy.setChecked(true);
                    break;
                case PAGER_FOUR:
                    rbSetting.setChecked(true);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_channel:
                mViewPager.setCurrentItem(PAGER_ONE);
                break;
            case R.id.rb_message:
                mViewPager.setCurrentItem(PAGER_TWO);
                break;
            case R.id.rb_my:
                mViewPager.setCurrentItem(PAGER_THREE);
                break;
            case R.id.rb_setting:
                mViewPager.setCurrentItem(PAGER_FOUR);
                break;
            default:
        
                break;
        }

    }
}
