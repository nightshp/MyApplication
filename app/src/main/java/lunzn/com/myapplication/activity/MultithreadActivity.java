package lunzn.com.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.util.MultithreadDownloadUtil;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/9 11:41
 */
public class MultithreadActivity extends Activity implements View.OnClickListener {
    // 文件名
    private EditText etName;

    // 文件下载路径
    private EditText etFilePath;

    // 下载按钮
    private Button btnLoad;

    private MultithreadDownloadUtil mUtil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_download_multithread_layout);
        initView();
    }

    private void initView() {
        etFilePath = findViewById(R.id.et_file_path);
        etName = findViewById(R.id.et_file_save);
        btnLoad = findViewById(R.id.btn_load);
        etFilePath.setText("http://img0.imgtn.bdimg.com/it/u=413456570,2370525928&fm=26&gp=0.jpg");
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
//                String fileName = etName.getText().toString();

                String fileName = etName.getText().toString();
                String filePeth = etFilePath.getText().toString();
                mUtil = new MultithreadDownloadUtil(filePeth,fileName);
                mUtil.download();
                break;
            default:
                break;
        }
    }
}
