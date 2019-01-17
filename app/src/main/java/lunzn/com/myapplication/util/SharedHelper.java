package lunzn.com.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc: SharedPreferences 工具类
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.util
 * ProjectName: MyApplication4
 * Date: 2019/1/8 17:10
 */
public class SharedHelper {

    private Context mContext;

    public SharedHelper() {
    }

    public SharedHelper(Context context) {
        mContext = context;
    }

    /**
     * 保存数据
     * @param userName 用户名
     * @param passwd 密码
     */
    public void save(String userName,String passwd){
        // 获取SharedPreferences对象 mysp为配置文件名
        SharedPreferences sp = mContext.getSharedPreferences("mysp",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName",userName);
        editor.putString("passwd",passwd);
        editor.commit();
        Toast.makeText(mContext,"数据已写入sharedpreference",Toast.LENGTH_LONG).show();
    }

    /**
     * 读取sp文件中的数据并存入map中
     * @return 数据
     */
    public Map<String,String> read(){
        Map<String,String> data = new HashMap<String,String>();
        SharedPreferences sp = mContext.getSharedPreferences("mysp",Context.MODE_PRIVATE);

        data.put("userName",sp.getString("userName",""));
        data.put("passwd",sp.getString("passwd",""));

        return data;
    }

}
