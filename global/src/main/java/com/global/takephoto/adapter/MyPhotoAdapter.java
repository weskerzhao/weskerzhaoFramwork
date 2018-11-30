package com.global.takephoto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.global.R;
import com.global.takephoto.uilts.Image;

import java.util.List;


/**
 * 图片的适配器
 * Created by Administrator on 2017/8/25.
 */

public class MyPhotoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Image> mData;

    public MyPhotoAdapter(Context mContext, List<Image> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_cell_layout, null);
            holder.mPic = view.findViewById(R.id.item_image);
            holder.mShade = view.findViewById(R.id.item_shade);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (position < 1) {
            //第一个Item 特殊待遇下
            holder.mShade.setVisibility(View.GONE);
            Glide.with(mContext).load(R.mipmap.ic_camera)
                    .into(holder.mPic);
        } else {
            Glide.with(mContext)
                    .load(mData.get(position).getPath())//加载路径
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//不适用缓存
                    .centerCrop()//居中剪切
                    .placeholder(R.color.colorPhoto)//默认颜色
                    .into(holder.mPic);
        }

        return view;
    }

    static class ViewHolder {
        private ImageView mPic;
        private View mShade;
    }
}
