package lunzn.com.myapplication.util;

import android.util.Log;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Desc: 多线程下载工具类
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.util
 * ProjectName: MyApplication4
 * Date: 2019/1/9 11:07
 */
public class MultithreadDownloadUtil {
    // 文件下载路径
    private  String fileUrl;

    // 文件保存名
    private String fileSaveName;

    // 线程数
    private  final int THREAD_NUM = 3;

    public MultithreadDownloadUtil() {
    }

    public MultithreadDownloadUtil(String fileUrl, String fileSaveName) {
        this.fileUrl = fileUrl;
        this.fileSaveName = fileSaveName;
    }

    /**
     * 多线程下载文件
     */
    public  void download(){
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 获取文件长度
            int fileLength = connection.getContentLength();
            Log.i("file","RandomAccessFile fileLength"+fileLength);
            // 生成大小相同的本地文件
            RandomAccessFile file = new RandomAccessFile(fileSaveName,"rwd");
            Log.i("file","RandomAccessFile path"+file);
            file.setLength(fileLength);
            file.close();
            connection.disconnect();

            // 设置多少条线程下载
            int threadNum = THREAD_NUM;
            // 计算每个线程的下载量
            int threadLength = fileLength % threadNum == 0 ? fileLength/threadNum : fileLength/threadNum + 1 ;
            for (int i = 0;i < threadNum; i++){
                // 设置每条线程从哪个位置开始下载
                int startPosition = threadLength * i;
                // 从文件的什么位置写入
                RandomAccessFile threadfile = new RandomAccessFile(fileSaveName,"rwd");
                threadfile.seek(startPosition);
                //启动三条线程分别从startposition位置开始下载文件
                new DownLoadThread(i,startPosition,threadfile,threadLength,fileUrl).start();
            }
            int quit = System.in.read();
            while('q' != quit)
            {
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DownLoadThread extends Thread {
        private int threadid;
        private int startposition;
        private RandomAccessFile threadfile;
        private int threadlength;
        private String path;
        public DownLoadThread(int threadid, int startposition,
                              RandomAccessFile threadfile, int threadlength, String path) {
            this.threadid = threadid;
            this.startposition = startposition;
            this.threadfile = threadfile;
            this.threadlength = threadlength;
            this.path = path;
        }
        public DownLoadThread() {}
        @Override
        public void run() {
            try
            {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                //指定从什么位置开始下载
                conn.setRequestProperty("Range", "bytes="+startposition+"-");
                //System.out.println(conn.getResponseCode());
                if(conn.getResponseCode() == 206)
                {
                    InputStream is = conn.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    int length = 0;
                    while(length < threadlength && (len = is.read(buffer)) != -1)
                    {
                        threadfile.write(buffer,0,len);
                        //计算累计下载的长度
                        length += len;
                    }
                    threadfile.close();
                    is.close();
                    System.out.println("线程"+(threadid+1) + "已下载完成");
                }
            }catch(Exception ex){System.out.println("线程"+(threadid+1) + "下载出错"+ ex);}
        }

    }
}
