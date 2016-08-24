package cn.xiaocool.hongyunschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import cn.xiaocool.hongyunschool.R;
import cn.xiaocool.hongyunschool.utils.LogUtils;


/**
 * Created by Administrator on 2016/5/27.
 */
public class ImgGridAdapter extends BaseAdapter {

    private int i;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mWorkImgs;
    private Context mContext;
    private DisplayImageOptions options;


    public ImgGridAdapter(ArrayList<String> workImgs, Context context) {
        this.mContext = context;
        this.mWorkImgs = workImgs;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.i = mWorkImgs.size();
    }

    @Override
    public int getCount() {

        return i > 9 ? 9 : i;
    }

    @Override
    public Object getItem(int position) {
        return i;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGridViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MyGridViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.user_img_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_user_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyGridViewHolder) convertView.getTag();
        }
        LogUtils.d("weixiaotong-GridAdaper", mWorkImgs.get(position));
//        imageLoader.displayImage(NetBaseConstant.NET_CIRCLEPIC_HOST+ mWorkImgs.get(position), viewHolder.imageView,options);
        return convertView;
    }

    private static class MyGridViewHolder {
        ImageView imageView;
    }
}
