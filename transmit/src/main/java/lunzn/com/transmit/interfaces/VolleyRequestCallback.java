package lunzn.com.transmit.interfaces;

/**
 * Desc: 请求结果回调
 * <p>
 * Author: shirencong
 * PackageName: com.lunzn.demo.interfaces
 * ProjectName: MyApplication
 * Date: 2019/1/15 16:18
 */
public interface VolleyRequestCallback {

    /**
     * 请求成功回调
     * @param url 请求地址
     * @param t 请求结果
     */
    public <T> void onRequestSuccess(String url, T t);

    /**
     * 请求失败回调
     * @param url 请求地址
     * @param t 失败参数
     */
    public <T> void onRequestFailed(String url, T t);

}
