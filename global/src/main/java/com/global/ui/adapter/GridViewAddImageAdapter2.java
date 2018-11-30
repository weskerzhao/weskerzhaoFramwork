package com.global.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.global.R;
import com.global.listener.ImageShow;
import com.waterbase.utile.GlideUtile;

import java.util.ArrayList;
import java.util.List;

/**
 * com.bm.falvzixun.adapter.GridViewAddImgAdpter
 *
 * @author yuandl on 2015/12/24. edit by water on 2018/5/15
 * 添加上传图片适配器
 */
public class GridViewAddImageAdapter2<T extends ImageShow> extends BaseAdapter {
    private List<T> datas;
    private Context context;
    private LayoutInflater inflater;
    /**
     * 可以动态设置最多上传几张，之后就不显示+号了，用户也无法上传了
     * 默认9张
     */
    private int maxImages = 9;

    public GridViewAddImageAdapter2(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public List<T> getDatas() {
        return datas;
    }

    /**
     * 获取最大上传张数
     *
     * @return
     */
    public int getMaxImages() {
        return maxImages;
    }

    /**
     * 设置最大上传张数
     *
     * @param maxImages
     */
    public void setMaxImages(int maxImages) {
        this.maxImages = maxImages;
    }

    /**
     * 让GridView中的数据数目加1最后一个显示+号
     *
     * @return 返回GridView中的数量
     */
    @Override
    public int getCount() {
        int count = datas == null ? 1 : datas.size() + 1;
        if (count >= maxImages) {
            return datas.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setData(List<T> datas) {
        if (this.datas != null) {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        } else {
            this.datas = new ArrayList<>();
            if (datas != null) {
                this.datas.addAll(datas);
                notifyDataSetChanged();
            }
        }
    }

    public void addData(T t) {
        if (this.datas == null)
            datas = new ArrayList<>();
        this.datas.add(t);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (datas != null && position < datas.size()) {
            String url = datas.get(position).getShowImageUrl();
            GlideUtile.loadImage(viewHolder.ivimage, url, R.mipmap.ic_camera, R.mipmap.ic_camera);
            viewHolder.btdel.setVisibility(View.VISIBLE);
            viewHolder.btdel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else {
            Glide.with(context)
                    .load(R.mipmap.image_add)
                    .priority(Priority.HIGH)
                    .centerCrop()
                    .into(viewHolder.ivimage);
            viewHolder.ivimage.setScaleType(ImageView.ScaleType.FIT_XY);
            viewHolder.btdel.setVisibility(View.GONE);
        }
        return convertView;

    }

    public class ViewHolder {
        public final ImageView ivimage;
        public final Button btdel;
        public final View root;

        public ViewHolder(View root) {
            ivimage = (ImageView) root.findViewById(R.id.iv_image);
            btdel = (Button) root.findViewById(R.id.bt_del);
            this.root = root;
        }
    }
}
