package com.lunzn.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lunzn.demo.LessonService;
import com.lunzn.demo.R;
import com.lunzn.demo.bean.Article;
import com.lunzn.demo.util.LogUtil;

/**
 * Desc: TODO
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo
 * ProjectName: MyApplication
 * Date: 2019/1/7 16:38
 */
public class LoadTaskListView extends LinearLayout implements View.OnClickListener {

    private final static String TAG = "loadfile";

    private Context mContext = null;

    private LayoutInflater mInflater = null;

    private LessonService.MyBinder mBinder = null;


    public LoadTaskListView(Context context) {
        super(context);
        initData(context);
    }

    public LoadTaskListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
        initView();
    }

    public LoadTaskListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
        initView();
    }

    private void initData(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
    }

    public void setBinder(LessonService.MyBinder binder) {
        mBinder = binder;
    }

    /**
     * 添加单个任务布局
     *
     * @param mArticle 任务对象
     */
    public void addTaskView(Article mArticle) {
        View itemView = mInflater.inflate(R.layout.lv_download_file_item_layout, null);
        LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 112);
        lp.bottomMargin = 8;
        addView(itemView, lp);
        itemView.setTag("taskview_" + mArticle.getId());
        TextView tvTitle = itemView.findViewById(R.id.tv_load_title);
        tvTitle.setText(mArticle.getTitle());
        Button btnState = (Button) itemView.findViewById(R.id.btn_load_state);
        btnState.setOnClickListener(this);
        btnState.setTag(mArticle);
        showState(mArticle.getState(), btnState);
        showProgress(mArticle.getProgress(), (TextView) itemView.findViewById(R.id.tv_load_progress));
    }

    /**
     * 更新下载状态
     * @param id 任务id
     * @param state 下载状态
     */
    public void updateState(int id, int state){
        View itemView = findViewWithTag("taskview_" + id);
        Button btnState = itemView.findViewById(R.id.btn_load_state);
        Article mArticle = (Article) btnState.getTag();
        mArticle.setState(state);
        showState(state, btnState);
    }

    /**
     * 更新进度
     * @param id 任务Id
     * @param progress 下载进度
     */
    public void updateProgress(int id, int progress){
        View itemView = findViewWithTag("taskview_" + id);
        Button btnState = itemView.findViewById(R.id.btn_load_state);
        Article mArticle = (Article) btnState.getTag();
        mArticle.setProgress(progress);
        showProgress(progress, (TextView) itemView.findViewById(R.id.tv_load_progress));
    }

    private void showProgress(int progress, TextView tvProgress) {
        LogUtil.i(TAG, "MyAdapter updateProgress,progress:  " + progress);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) tvProgress.getLayoutParams();
        lp.width = (int) (900 * (progress * 1.0 / 100));
        tvProgress.setLayoutParams(lp);
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

            case 4:
                btnState.setText("下载暂停");
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_state:
                Object tag = v.getTag();
                if (tag != null && tag instanceof Article) {
                    Article mArtile = (Article) tag;
                    if (mArtile.getState() != 1) {
                        mBinder.startLoad(mArtile.getId(), mArtile);
                    } else {
                        mBinder.stopLoad(mArtile.getId());
                    }
                }
                break;
        }
    }
}
