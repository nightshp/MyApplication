package lunzn.com.transmit.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import lunzn.com.transmit.R;
import lunzn.com.transmit.bean.FileCheckBean;

/**
 * Desc: 利用Volley框架请求数据
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.util
 * ProjectName: MyApplication4
 * Date: 2019/1/16 10:53
 */
public class VolleyRequestUtil {

    private static final String TAG = "VolleyRequestUtil";

    private static VolleyRequestUtil sVolleyRequestUtil;

    // 单例模式
    public static VolleyRequestUtil getInstance() {
        if(sVolleyRequestUtil == null ) {
            synchronized (VolleyRequestUtil.class) {
                if(sVolleyRequestUtil == null ) {
                    sVolleyRequestUtil = new VolleyRequestUtil();
                }
            }
        }
        return sVolleyRequestUtil;
    }

    private VolleyRequestUtil() {

    }
    /**
     * doGet请求
     * @param mContext 上下文
     * @param url 请求地址
     */
    public void doGet(Context mContext,String url){
        // 创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        // 创建一个StringRequest 三个参数分别为 请求地址、成功监听、失败监听
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i(TAG,"doGet onResponse: "+s);
                FileCheckBean data = JSON.parseObject(s,FileCheckBean.class);
                Log.i(TAG,"doGet data: "+data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"doGet onErrorResponse: "+volleyError);
            }
        });
        requestQueue.add(stringRequest);
    }

    /**
     * doPost请求
     * @param mContext 上下文
     * @param url 请求地址
     * @param params 请求参数
     */
    public void doPost(Context mContext, String url, Map<String,String> params){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i(TAG,"doPost onResponse: "+s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"doPost onErrorResponse: "+volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }

    /**
     * 图片请求
     * @param mContext 上下文
     * @param url 请求地址
     * @param maxWidth 最大宽度
     * @param maxHeight 最大高度
     * @param scaleType 如何显示图片
     * @param decodeConfig 图片颜色
     */
    public void getImage(final ImageView imageView, Context mContext, String url, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, Bitmap.Config decodeConfig){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Log.i(TAG,"getIamge onResponse: "+bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }, maxWidth, maxHeight, scaleType, decodeConfig, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"getImage onErrorResponse: "+volleyError);
                imageView.setImageResource(R.drawable.ic_img3);
            }
        });
        requestQueue.add(imageRequest);
    }
}
