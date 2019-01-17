package lunzn.com.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.bean.Text;
import lunzn.com.myapplication.fragment.TxtListFragment;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/10 15:31
 */
public class TxtActivity extends FragmentActivity {

    private TextView mView;

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 对象集合
     */
    private List<Text> mData = null;

    private FragmentManager mManager;

    private FrameLayout mFrameLayout;

    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgt_txt_main);
        mManager = getSupportFragmentManager();
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        mContext = TxtActivity.this;
        mFrameLayout = findViewById(R.id.fl_content);
        mView = findViewById(R.id.txt_title);
        mData = new ArrayList<Text>();
        mData.add(new Text("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2540594264,2400204506&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2255384485,690736213&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=929115251,2075750408&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2483636676,3367003520&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2540594264,2400204506&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2255384485,690736213&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=929115251,2075750408&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2483636676,3367003520&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2540594264,2400204506&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2255384485,690736213&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=929115251,2075750408&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2483636676,3367003520&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2540594264,2400204506&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2255384485,690736213&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=929115251,2075750408&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2483636676,3367003520&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2540594264,2400204506&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2255384485,690736213&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=929115251,2075750408&fm=26&gp=0.jpg"));
        mData.add(new Text("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2483636676,3367003520&fm=26&gp=0.jpg"));
        TxtListFragment txtListFragment = new TxtListFragment(mManager,mData);
        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content,txtListFragment);
        fragmentTransaction.commit();
    }


    /**
     * 点击回退键处理
     */
    @Override
    public void onBackPressed() {
        // Fragment栈中是否有fragment
        if (mManager.getBackStackEntryCount() ==  0){
            if ((System.currentTimeMillis() - exitTime) >2000){
                Toast.makeText(getApplicationContext(),"再按一次退出",Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            }else{
                super.onBackPressed();
            }
        }else{
            mManager.popBackStack();
            mView.setText("列表");
        }
    }
}
