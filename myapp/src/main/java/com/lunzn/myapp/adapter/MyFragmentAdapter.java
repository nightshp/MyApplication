package com.lunzn.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.adapter
 * ProjectName: MyApplication4
 * Date: 2019/1/22 14:46
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
        super(fm);
        setData(mFragmentList);
    }

    private void setData(List<Fragment> mFragmentList) {
        if (mFragmentList == null) {
            mFragmentList = new ArrayList<Fragment>();
        }
        this.mFragmentList = mFragmentList;
    }


    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    // instantiateItem会调用getItem方法
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container,position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       super.destroyItem(container,position,object);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


}
