package lunzn.com.myapplication.fragment;

import android.annotation.SuppressLint;
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
 * Desc: 自定义fragment
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.fragment
 * ProjectName: MyApplication4
 * Date: 2019/1/8 11:35
 */
public class MyFragment extends Fragment {
    private String content;

    public MyFragment() {
    }

    @SuppressLint("ValidFragment")
    public MyFragment(String content) {
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = view.findViewById(R.id.txt_content);
        txt_content.setText(content);
        return view;
    }
}
