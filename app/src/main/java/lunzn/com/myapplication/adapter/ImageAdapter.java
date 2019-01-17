package lunzn.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.bean.Image;
import lunzn.com.myapplication.util.GitData;

/**
 * Desc: TODO
 * <p>适配器
 * Author: meijie
 * PackageName: lunzn.com.myapplication.adapter
 * ProjectName: MyApplication
 * Date: 2018/12/23 14:33
 */
public class ImageAdapter extends BaseAdapter {
    /**
     * imge对象集合
     */
    private List<Image> mData;

    /**
     * 上下文
     */
    private Context mContext;


    /**
     * 构造函数
     * @param mData 对象集合
     * @param context 上下文
     */
    public ImageAdapter(List<Image> mData, Context context) {
        setData(mData);
        mContext = context;
    }

    /**
     * 对象初始化 防止出现空指针
     * @param mData 对象集合
     */
    public void setData(List<Image> mData){
        if(mData == null){
            mData = new ArrayList<Image>();
        }
        this.mData= mData;
    }

    /**
     * 获取集合中对象数量
     * @return 数量
     */
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size() ;
    }

    /**
     * 获取位置
     * @param position 位置
     * @return 位置
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * 获取对象id
     * @param position 位置
     * @return 对象id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_item_layout,parent,false);
            viewHodler = new ViewHodler();
            viewHodler.mImgShow = (ImageView) convertView.findViewById(R.id.img_icon);
            viewHodler.mTxtShow = (TextView)convertView.findViewById(R.id.txt_show);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler)convertView.getTag();
        }
        viewHodler.mTxtShow.setText(mData.get(position).getTxt());
        byte[] data = new byte[0];
        try {
            data = GitData.getImage(mData.get(position).getImgUrl());
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            viewHodler.mImgShow.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }


    class ViewHodler{
        /**
         * 图片显示控件
         */
        ImageView mImgShow;

        /**
         * 文本显示控件
         */
        TextView mTxtShow;
    }
}
