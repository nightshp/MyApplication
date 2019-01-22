package com.lunzn.myapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Desc: MD5加密
 * <p>
 * Author: suhongpeng
 * PackageName: com.lunzn.myapp.util
 * ProjectName: MyApplication4
 * Date: 2019/1/22 11:03
 */
public class MD5Util {
    /**
     * 获取MD5加密后的数据
     * @param content 要加密的内容
     * @return 加密后的数据
     */
    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }
}
