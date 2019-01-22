package com.lunzn.myapp.bean;

import org.litepal.crud.DataSupport;

/**
 * Desc: 用户实体
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.bean
 * ProjectName: MyApplication4
 * Date: 2019/1/21 18:43
 */
public class User extends DataSupport {

    // 用户ID
    private long uId;
    // 用户名
    private String userName;
    // 密码
    private String passWord;

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

}
