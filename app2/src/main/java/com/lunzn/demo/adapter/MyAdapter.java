package com.lunzn.demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lunzn.demo.LessonService;
import com.lunzn.demo.R;
import com.lunzn.demo.bean.Article;
import com.lunzn.demo.util.DisplayImageUtil;
import com.lunzn.demo.util.LogUtil;

import java.util.List;

/**
 * Desc: 演示适配器
 * <p>
 * Author: Administrator
 * PackageName: com.lunzn.demo.adapter
 * ProjectName: MyApplication
 * Date: 2018/12/20 16:39
 */
public class MyAdapter extends BaseAdapter implements View.OnClickListener {

    private final static String TAG = "loadfile";

    // 演示数据
    private List<Article> mData = null;

    // 上下文
    private Context mContext = null;

    // 显示列表
    private ListView mListView = null;

    // 标识是否需要加载图片
    private boolean needLoadImage = true;

    // 图片加载工具
    private DisplayImageUtil mImageUtil = null;

    private LessonService.MyBinder mBinder = null;

    public MyAdapter(Context context, ListView mListView) {
        this.mContext = context;
        this.mListView = mListView;
        this.mImageUtil = new DisplayImageUtil();
    }

    public void setBinder(LessonService.MyBinder binder) {
        mBinder = binder;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Article getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final VH vh;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.lv_download_file_item_layout, null);
            vh = new VH(convertView);
            convertView.setTag(vh);
        } else {
            vh = (VH) convertView.getTag();
        }

        final Article item = getItem(position);

        vh.tvTitle.setText(item.getTitle());
        vh.btnState.setTag(position);
        vh.btnState.setOnClickListener(this);
        showState(item.getState(), vh.btnState);
        updateProgress(item.getProgress(), vh.tvProgress);
        return convertView;
    }

    public void setData(List<Article> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }

    public void setNeedLoadImage(boolean needLoadImage) {
        this.needLoadImage = needLoadImage;
    }

    private void updateProgress(int progress, TextView tvProgress) {
        LogUtil.i(TAG, "MyAdapter updateProgress,progress:  " + progress);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) tvProgress.getLayoutParams();
        lp.width = (int) (900 * (progress * 1.0 / 100));
        tvProgress.setLayoutParams(lp);
        notifyDataSetChanged();
    }

    private void showState(int state, Button btnState) {
        LogUtil.i(TAG, "MyAdapter showState  state:  " + state);
        switch (state) {
            case 0:
                btnState.setText("开始下载");
                break;

            case 1:
                btnState.setText("下载中");
                break;

            case 2:
                btnState.setText("下载完成");
                break;

            case 3:
                btnState.setText("下载失败");
                break;

        }

    }

    class VH {

        public TextView tvTitle = null;
        private TextView tvProgress = null;
        private Button btnState = null;

        public VH(View view) {
            tvTitle = view.findViewById(R.id.tv_load_title);
            tvProgress = view.findViewById(R.id.tv_load_progress);
            btnState = view.findViewById(R.id.btn_load_state);
        }
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        Article article = mData.get(pos);
        if (v.getId() == R.id.btn_load_state) {
            if (article.getState() == 0 || article.getState() == 3) {
                mBinder.startLoad(pos, article);
            } else if (article.getState() == 1) {
                mBinder.stopLoad(pos);
            }
        }
    }
}
