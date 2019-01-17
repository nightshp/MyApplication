package lunzn.com.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Desc: 数据库创建与版本管理
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.db
 * ProjectName: MyApplication4
 * Date: 2019/1/9 18:20
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {

    private final String TAG = "MyDBOpenHelper";

    private static MyDBOpenHelper sMyDBOpenHelper;

    // 创建单例
    public static MyDBOpenHelper getInstance(Context context) {
        if(sMyDBOpenHelper == null ) {
            synchronized (MyDBOpenHelper.class) {
                if(sMyDBOpenHelper == null ) {
                    sMyDBOpenHelper = new MyDBOpenHelper(context,null,null,0);
                }
            }
        }
        return sMyDBOpenHelper;
    }

    public MyDBOpenHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "my.db", null, 1);
    }

    /**
     * 数据库第一次创建时调用
     * @param db 数据库访问类
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table teacher(tid Integer primary key AUTOINCREMENT,name varchar(20))";
        db.execSQL(sql);
    }

    /**
     * 数据库版本变更时调用
     * @param db 数据库访问类
     * @param oldVersion 老版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"版本更新");
    }
}
