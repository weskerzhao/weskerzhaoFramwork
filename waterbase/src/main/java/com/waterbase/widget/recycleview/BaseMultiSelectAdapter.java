package com.waterbase.widget.recycleview;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Water on 2017/4/20.
 */

public abstract class BaseMultiSelectAdapter extends BaseAdapter<IMultiSelectItem>
        implements MultiSelect {
    private OnItemCheckedChangeListener mOnItemCheckedChangeListener;

    @Override
    public int getLayoutRes(int index) {
        final IMultiItem data = mData.get(index);
        return data.getLayoutRes();
    }

    @Override
    public void convert(BaseViewHolder holder, IMultiSelectItem data, int index) {
        holder.setChecked(data.getCheckableViewId(),data.isChecked());
        data.convert(holder);
    }

    @Override
    public void clearSelectAll() {
        for (IMultiSelectItem selectorItem : mData) {
            selectorItem.setChecked(false);
        }
        notifyDataSetChanged();
    }

    @Override
    public void selectAll() {
        for (IMultiSelectItem selectorItem : mData) {
            selectorItem.setChecked(true);
        }
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemCheckedChangeListener(OnItemCheckedChangeListener onCheckedChangeListener) {
        mOnItemCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public List<IMultiSelectItem> getSelectedItems() {
        List<IMultiSelectItem> selectedItems = new ArrayList<>();
        for (IMultiSelectItem selectorItem : mData) {
            if (selectorItem.isChecked()){
                selectedItems.add(selectorItem);
            }
        }
        return selectedItems;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, int layoutRes) {
        baseViewHolder.setOnItemCheckedChangeListener(new OnItemCheckedChangeListener() {
            @Override
            public void onItemCheck(@NonNull View view, boolean isChecked, int adapterPosition) {
                final int id = view.getId();
                final IMultiSelectItem item = getData(adapterPosition);
                if (item != null && id == item.getCheckableViewId()){
                    item.setChecked(isChecked);
                    notifyItemChanged(adapterPosition);
                }
                if (mOnItemCheckedChangeListener != null){
                    mOnItemCheckedChangeListener.onItemCheck(view, isChecked, adapterPosition);
                }
            }
        });
        super.bindData(baseViewHolder, layoutRes);
    }
}
