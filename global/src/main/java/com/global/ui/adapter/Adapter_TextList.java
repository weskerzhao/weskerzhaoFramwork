package com.global.ui.adapter;

import com.global.R;
import com.global.listener.ItemClickListener;
import com.global.listener.TextShow;
import com.waterbase.widget.recycleview.BaseAdapter;
import com.waterbase.widget.recycleview.BaseViewHolder;

/**
 * 文字列表选择器
 * Created by Water on 2018/5/10.
 */

public class Adapter_TextList<T extends TextShow> extends BaseAdapter<T> {

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getLayoutRes(int index) {
        return R.layout.item_text;
    }

    @Override
    public void convert(BaseViewHolder holder, T data, int index) {
        holder.setText(R.id.tv_content, data.getContent());
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.itemClick(v, data, index);
        });
    }

    @Override
    public void bind(BaseViewHolder holder, int layoutRes) {

    }

}
