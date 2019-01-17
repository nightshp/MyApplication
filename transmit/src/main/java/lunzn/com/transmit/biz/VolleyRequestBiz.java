package lunzn.com.transmit.biz;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import lunzn.com.transmit.interfaces.VolleyRequestCallback;

/**
 * Desc: 利用Volley框架请求数据
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.util
 * ProjectName: MyApplication
 * Date: 2019/1/15 14:50
 */
public class VolleyRequestBiz {

    private final static String TAG = "VolleyRequest";

    private VolleyRequestCallback mCallback = null;

    public VolleyRequestBiz(VolleyRequestCallback mCallback){
        this.mCallback = mCallback;
    }

    private class LessonListener implements Response.Listener {
        String url = null;

        LessonListener(String requestUrl){
            this.url = requestUrl;
        }

        @Override
        public void onResponse(Object o) {
            if (mCallback != null) {
                mCallback.onRequestSuccess(url, o);
            }
        }
    }

    private class LessonErroLis implements Response.ErrorListener{
        String url = null;
        LessonErroLis(String requstUrl){
            this.url = requstUrl;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            if (mCallback != null){
                mCallback.onRequestFailed(url, volleyError);
            }
        }
    }

    /**
     * get请求数据
     *
     * @param mContext   上下文
     * @param requestUrl 请求地址
     */
    public void doGet(Context mContext, String requestUrl) {
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        StringRequest mRequest = new StringRequest(requestUrl, new LessonListener(requestUrl), new LessonErroLis(requestUrl));
        mQueue.add(mRequest);
    }

    /**
     * post请求数据
     *
     * @param mContext   上下文
     * @param requestUrl 请求地址
     * @param params     请求参数
     */
    public void doPost(Context mContext, String requestUrl, final Map<String, String> params) {
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        StringRequest mQuest = new StringRequest(Request.Method.POST, requestUrl, new LessonListener(requestUrl), new LessonErroLis(requestUrl)) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        mQueue.add(mQuest);
    }

}
