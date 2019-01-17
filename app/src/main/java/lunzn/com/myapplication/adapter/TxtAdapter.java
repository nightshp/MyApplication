package lunzn.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lunzn.com.myapplication.R;
import lunzn.com.myapplication.bean.Text;

/**
 * Desc: fragment 图片适配器
 * <p>
 * Author: suhongpeng
 * PackageName: lunzn.com.myapplication.adapter
 * ProjectName: MyApplication4
 * Date: 2019/1/10 15:25
 */
public class TxtAdapter extends BaseAdapter {
    /**
     * imge对象集合
     */
    private List<Text> mData;

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * bitmap
     */
    private Bitmap bitmap;

    /**
     * listview
     */
    private ListView mListView;


    /**
     * 构造函数
     * @param mData 对象集合
     * @param context 上下文
     */
    public TxtAdapter(List<Text> mData, Context context) {
        setData(mData);
        mContext = context;
    }

    /**
     * 对象初始化 防止出现空指针
     * @param mData 对象集合
     */
    public void setData(List<Text> mData){
        if(mData == null){
            mData = new ArrayList<Text>();
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
        if (mListView == null) {
            mListView = (ListView) parent;
        }
        ViewHodler viewHodler = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fgt_tvlist_item,parent,false);
            viewHodler = new ViewHodler();
            viewHodler.mTextView = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler)convertView.getTag();
        }


        viewHodler.mTextView.setText(mData.get(position).getTxt());


        return convertView;
    }


    class ViewHodler{

      TextView mTextView;
    }
}
