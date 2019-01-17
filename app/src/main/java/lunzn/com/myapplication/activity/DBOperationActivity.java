package lunzn.com.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.db.MyDBOpenHelper;

/**
 * Desc: 数据库操作activity
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/9 18:38
 */
public class DBOperationActivity extends Activity implements View.OnClickListener {
    // 新增数据
    private Button btnAdd;

    // 删除数据
    private Button btnDel;

    // 修改数据
    private Button btnUpdate;

    // 修改数据
    private Button btnQuery;

    // 查询总记录数
    private Button btnCount;

    // 显示数据
    private TextView tvShow;

    private MyDBOpenHelper mHelper;

    private Context mContext;

    private static final String TAG = "DBOperationActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_operation_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mContext = DBOperationActivity.this;
        btnAdd = findViewById(R.id.btn_db_add);
        btnDel = findViewById(R.id.btn_db_del);
        btnUpdate = findViewById(R.id.btn_db_update);
        btnQuery = findViewById(R.id.btn_db_query);
        btnCount = findViewById(R.id.btn_db_count);
        tvShow = findViewById(R.id.tv_db_show);

        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnCount.setOnClickListener(this);

    }

    /**
     * 点击事件
     *
     * @param v 控件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_db_add:
                addData();
                break;
            case R.id.btn_db_del:
                delData();
                break;
            case R.id.btn_db_update:
                updateData();
                break;
            case R.id.btn_db_query:
                queryData();
                break;
            case R.id.btn_db_count:
                getCount();
                break;
            default:

                break;
        }
    }

    /**
     * 新增数据
     */
    private void addData() {
        mHelper = MyDBOpenHelper.getInstance(mContext);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into teacher(tid,name) values(?,?)", new String[]{null, "zhangsan"});
        Toast.makeText(mContext,"新增成功",Toast.LENGTH_LONG).show();
        Log.i(TAG, "addData success");
        db.close();
    }

    /**
     * 删除数据
     */
    private void delData() {
        mHelper = MyDBOpenHelper.getInstance(mContext);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from teacher where tid = ?", new String[]{"2"});
        Toast.makeText(mContext,"删除成功",Toast.LENGTH_LONG).show();
        Log.i(TAG, "delData success");
        db.close();
    }

    /**
     * 修改数据
     */
    private void updateData() {
        mHelper = MyDBOpenHelper.getInstance(mContext);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("update teacher set name = ? where tid = ?", new String[]{"lisi", "3"});
        Toast.makeText(mContext,"修改成功",Toast.LENGTH_LONG).show();
        Log.i(TAG, "updateData success");
        db.close();
    }

    /**
     * 查找数据
     */
    private void queryData() {
        mHelper = MyDBOpenHelper.getInstance(mContext);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from teacher", new String[]{});
        List<String> data = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            // 存在数据
            while (cursor.moveToNext()) {
                String tid = cursor.getString(cursor.getColumnIndex("tid"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                data.add("tid : "+ tid +" "+ "name : "+ name);
                Log.i(TAG, "tid " + tid + "name " + name);
            }
            tvShow.setText(data+"");
            Log.i(TAG, "queryData success");
        } else {
            Log.i(TAG, "queryData fail :no data");
        }

        cursor.close();
    }

    /**
     * 查询总记录数
     */
    private void getCount() {
        mHelper = MyDBOpenHelper.getInstance(mContext);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select count(*) from teacher";
        Cursor cursor = db.rawQuery(sql, null);
        // 指向查询结果的第一个位置
        cursor.moveToFirst();
        long result = cursor.getLong(0);
        tvShow.setText(result + "");
        Log.i(TAG, "getCount " + result);
        cursor.close();

    }


}
