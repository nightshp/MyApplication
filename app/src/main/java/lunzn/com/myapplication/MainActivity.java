package lunzn.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lunzn.com.myapplication.adapter.ImageAdapter;
import lunzn.com.myapplication.bean.Image;

public class MainActivity extends AppCompatActivity {
    /**
     * listview控件
     */
    private ListView mListView;

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 图片适配器
     */
    private ImageAdapter mImageAdapter = null;

    /**
     * 图片对象集合
     */
    private List<Image> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_main_layout);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        mContext = MainActivity.this;
        mListView = (ListView)findViewById(R.id.lv_show);
        mData = new ArrayList<Image>();
        mData.add(new Image("http://img4.imgtn.bdimg.com/it/u=3415244797,3645437497&fm=26&gp=0.jpg","哈哈哈"));
        mData.add(new Image("http://img1.imgtn.bdimg.com/it/u=3946459250,1112291727&fm=26&gp=0.jpg","哈哈哈"));
        mData.add(new Image("http://img4.imgtn.bdimg.com/it/u=2390531497,954614193&fm=26&gp=0.jpg","哈哈哈"));
        mData.add(new Image("http://img0.imgtn.bdimg.com/it/u=160554372,288864420&fm=26&gp=0.jpg","哈哈哈"));
        mImageAdapter = new ImageAdapter((List<Image>) mData,mContext);
        mListView.setAdapter(mImageAdapter);
    }

}
