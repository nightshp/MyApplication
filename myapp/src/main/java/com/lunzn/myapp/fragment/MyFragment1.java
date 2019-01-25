package com.lunzn.myapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lunzn.myapp.R;
import com.lunzn.myapp.util.LogUtil;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.fragment
 * ProjectName: MyApplication4
 * Date: 2019/1/22 14:35
 */
public class MyFragment1 extends Fragment {

    private View view;

    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i("MyFragment1 onCreate");
//        if (view == null) {
//            view = View.inflate(getActivity(), R.layout.fgt_content, null);
//        } else {
//            ViewParent viewParent = view.getParent();
//            if (viewParent instanceof ViewGroup){
//                ((ViewGroup)viewParent).removeView(view);
//            }
//        }
        view = inflater.inflate(R.layout.fgt_content,null);
        TextView textView = view.findViewById(R.id.tv_fgt);
        textView.setText("提醒");
        return textView;
    }
}
