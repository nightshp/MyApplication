package lunzn.com.myapplication.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Desc: TODO
 * <p>将流转化为二进制数组的工具类
 * Author: meijie
 * PackageName: lunzn.com.myapplication.util
 * ProjectName: MyApplication4
 * Date: 2018/12/23 21:14
 */
public class StreamTool {
    //从流中读取数据
    public static byte[] read(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer,0,len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
