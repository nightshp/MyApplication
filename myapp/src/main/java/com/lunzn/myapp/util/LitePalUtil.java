package com.lunzn.myapp.util;

import com.lunzn.myapp.bean.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * Desc: litepal数据库操作工具类
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.util
 * ProjectName: MyApplication4
 * Date: 2019/1/21 18:51
 */
public class LitePalUtil {

    /**
     * 创建数据库
     */
    public static void createDataBase(){
        Connector.getDatabase();
    }

    /**
     * 注册用户
     * @param name 用户名
     * @param passwd 密码
     */
    public static void registUser(String name,String passwd){
        User user = new User();
        user.setUserName(name);
        user.setPassWord(passwd);
        user.save();
    }
    /**
     * 根据用户名、密码查找数据
     * @param userName 用户名
     * @param passWord 密码
     * @return 用户数据
     */
    public static List<User> queryData(String userName,String passWord){
        List<User> users = LitePal.where("userName = ? and passWord = ?",userName,passWord).find(User.class);
        return users;
    }
}
