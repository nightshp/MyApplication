package lunzn.com.transmit.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import lunzn.com.transmit.R;
import lunzn.com.transmit.util.VolleyRequestUtil;

/**
 * Desc: TODO
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.transmit.activity
 * ProjectName: MyApplication4
 * Date: 2019/1/16 11:19
 */
public class VolleyActivity1 extends Activity implements View.OnClickListener {

    private Button btnGet;

    private Button btnPost;

    private Button btnImage;

    // 显示图片
    private ImageView mImageView;

    // 图片索引
    private int index;

    // get请求
    private final static String GET_REQUEST_URL = "http://lunzn.aisee.tv/m2/sys/filecheck?sdkvsn=1.0.1&rom_sign=LZ-R1-M01-V01009&launcher_sign=LZM001&launcher_vsn=1.806&sn=36XFM1CZ&mac=e8:bb:3f:00:be:9d&gdid=null&D=null";

    // post请求
    private final static String POST_REQUEST_URL = "http://lunzn.aisee.tv/m2/sys/filecheck";

    // 图片请求地址数组
    private String[] images = new String[]{"http://img4.imgtn.bdimg.com/it/u=967395617,3601302195&fm=26&gp=0.jpg",
                                           "http://img5.imgtn.bdimg.com/it/u=3365018759,2226705862&fm=26&gp=0.jpg",
                                           "http://img5.imgtn.bdimg.com/it/u=49764040,3750999451&fm=26&gp=0.jpg",
                                           "http://img5.imgtn.bdimg.com/it/u=935292084,2640874667&fm=26&gp=0.jpg",
                                           "http://img1.imgtn.bdimg.com/it/u=2735633715,2749454924&fm=26&gp=0.jpg",
                                           "http://img1.imgtn.bdimg.com/it/u=2725262009,4290107754&fm=26&gp=0.jpg",
                                           "http://img4.imgtn.bdimg.com/it/u=3865754955,2482734833&fm=26&gp=0.jpg",
                                           "http://img0.imgtn.bdimg.com/it/u=3626439953,3976502933&fm=26&gp=0.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_layout);
        initView();
    }

    private void initView() {
        btnGet = findViewById(R.id.btn_get);
        btnGet.setOnClickListener(this);
        btnPost = findViewById(R.id.btn_POST);
        btnPost.setOnClickListener(this);
        btnImage = findViewById(R.id.btn_img_request);
        btnImage.setOnClickListener(this);
        mImageView = findViewById(R.id.iv_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                VolleyRequestUtil volleyRequestUtil = VolleyRequestUtil.getInstance();
                volleyRequestUtil.doGet(this, GET_REQUEST_URL);
                break;
            case R.id.btn_POST:
                VolleyRequestUtil volleyRequestUtil1 = VolleyRequestUtil.getInstance();
                Map<String, String> map = new HashMap<String, String>();
                map.put("sdkvsn", "1.0.1");
                map.put("rom_sign", "LZ-R1-M01-V01009");
                map.put("launcher_sign", "LZM001");
                map.put("launcher_vsn", "1.806");
                map.put("sn", "36XFM1CZ");
                map.put("mac", "e8:bb:3f:00:be:9d");
                map.put("gdid", "null");
                map.put("D", "null");
                volleyRequestUtil1.doPost(this, POST_REQUEST_URL, map);
                break;
            case R.id.btn_img_request:
                VolleyRequestUtil volleyRequestUtil2 = VolleyRequestUtil.getInstance();
                // 图片索引大于7重置为0实现循环
                if (index > 7) {
                    index = 0;
                }
                // ScaleType.CENTER 居中显示 Config.ARGB_8888 显示最好的颜色属性
                volleyRequestUtil2.getImage(mImageView, this, images[index], 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888);
                index++;
                break;
            default:
                break;
        }
    }
}
