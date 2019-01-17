package lunzn.com.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.util.SaveFileUtil;

/**
 * Desc: 文件保存和读取
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/8 15:42
 */
public class FileActivity extends Activity implements View.OnClickListener {
    // 保存文件
    private Button btnSave;
    
    // 清空
    private Button btnClear;
    
    // 读取文件
    private Button btnRead;

    // 跳转
    private Button btnJump;
    
    // 写入文件名
    private EditText txtName;
    
    // 写入文件内容
    private EditText txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_read_write_layout);
        SaveFileUtil.init(FileActivity.this);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btnSave = findViewById(R.id.btn_file_save);
        btnRead = findViewById(R.id.btn_file_read);
        btnClear = findViewById(R.id.btn_file_clear);
        btnJump = findViewById(R.id.btn_jump);
        txtName = findViewById(R.id.txt_file_name);
        txtContent = findViewById(R.id.txt_file_content);
        
        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnJump.setOnClickListener(this);
        
    }

    /**
     * 点击事件
     * @param v 控件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file_save:
                String fileName = txtName.getText().toString();
                String fileContent = txtContent.getText().toString();
                SaveFileUtil.saveFile(fileName,fileContent,"files");
                Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_file_read:
                String fileName1 = txtName.getText().toString();
                String result = SaveFileUtil.readFromFile(fileName1,"files");
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_file_clear:
                txtName.setText("");
                txtContent.setText("");
                break;
            case R.id.btn_jump:
                Intent intent = new Intent(this,MyFragmentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
