package lunzn.com.transmit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

import lunzn.com.transmit.R;
import lunzn.com.transmit.bean.Book;

/**
 * Desc: LitePal数据库框架操作
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/16 17:15
 */
public class LitePalActivity extends Activity implements View.OnClickListener {
    // 创建数据库
    private Button btnCreate;
    // 新增数据按钮
    private Button btnSave;
    // 删除数据按钮
    private Button btnDel;
    // 修改数据按钮
    private Button btnUpdate;
    // 查询数据按钮
    private Button btnFind;
    // 显示数据
    private TextView txtShow;

    private final static String TAG = "LitePal";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btnCreate = findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(this);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        btnDel = findViewById(R.id.btn_del);
        btnDel.setOnClickListener(this);
        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        btnFind = findViewById(R.id.btn_find);
        btnFind.setOnClickListener(this);
        txtShow = findViewById(R.id.tv_show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                Connector.getDatabase();
                Log.i(TAG,"database create success");
                break;
            case R.id.btn_save:
                addData();
                Log.i(TAG,"database addData success");
                break;
            case R.id.btn_del:
                delData();
                Log.i(TAG,"database delData success");
                break;
            case R.id.btn_update:
                updateData();
                Log.i(TAG,"database updateData success");
                break;
            case R.id.btn_find:
                findData();
                Log.i(TAG,"database findData success");
                break;
            default:

                break;
        }
    }

    /**
     * 新增数据
     */
    private void addData() {
        Book book = new Book();
        book.setbId(1001);
        book.setAuthor("王羲之");
        book.setbName("兰亭集序");
        book.save();
    }

    /**
     * 删除数据
     */
    private void delData() {
        // 删除Book表的所有数据
        LitePal.deleteAll(Book.class);
        // 删除id为1001的数据
        //LitePal.delete(Book.class,1001);
        // 删除id大于1001的数据
        //LitePal.deleteAll(Book.class,"bId > ?","1001");
    }

    /**
     * 修改数据
     */
    private void updateData() {
        Book book = new Book();
        book.setAuthor("张三");
        book.updateAll("author = ?","王羲之");
    }

    /**
     * 查询数据
     */
    private void findData() {
        List<Book> books = LitePal.findAll(Book.class);
        txtShow.setText(books.toString());
    }

}
