package lunzn.com.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lunzn.com.myapplication.R;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.fragment
 * ProjectName: MyApplication4
 * Date: 2019/1/10 16:05
 */
public class TxtContentFragment extends Fragment {
    public TxtContentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgt_tv,null);
        TextView mView = view.findViewById(R.id.tv_fgt_show);
        mView.setText(getArguments().getString("content"));
        return view;
    }
}
