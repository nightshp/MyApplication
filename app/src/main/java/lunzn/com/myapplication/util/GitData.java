package lunzn.com.myapplication.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: lunzn.com.myapplication.util
 * ProjectName: MyApplication4
 * Date: 2018/12/23 21:27
 */
public class GitData {
    /**
     * 定义一个获取网络图片数据的方法:
     * @param path 图片路径
     * @return 图片
     * @throws Exception 异常
     */
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置连接超时为5秒
        conn.setConnectTimeout(5000);
        // 设置请求类型为Get类型
        conn.setRequestMethod("GET");
        // 判断请求Url是否成功
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inStream = conn.getInputStream();
        byte[] bt = StreamTool.read(inStream);
        inStream.close();
        return bt;
    }
}
