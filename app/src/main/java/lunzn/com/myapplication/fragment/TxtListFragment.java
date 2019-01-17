package lunzn.com.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.adapter.TxtAdapter;
import lunzn.com.myapplication.bean.Text;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.fragment
 * ProjectName: MyApplication4
 * Date: 2019/1/10 15:55
 */
public class TxtListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private FragmentManager mManager;

    private ListView mListView;

    private List<Text> mData;

    public TxtListFragment() {
    }

    @SuppressLint("ValidFragment")
    public TxtListFragment(FragmentManager manager, List<Text> data) {
        mManager = manager;
        mData = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgt_tvlist,null);
        mListView = view.findViewById(R.id.list_tv);
        TxtAdapter mAdapter =  new TxtAdapter(mData,getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
        TxtContentFragment txtContentFragment = new TxtContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",mData.get(position).getTxt());
        txtContentFragment.setArguments(bundle);
//        TextView mTextView = (TextView) getActivity().findViewById(R.id.tv_fgt_show);
//        mTextView.setText(mData.get(position).getTxt());
        fragmentTransaction.replace(R.id.fl_content,txtContentFragment);
        // 将fragment加到backStack中
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }
}
