package com.waterbase.widget.recycleview;

/**
 * Created by Water on 2017/4/20.
 */

public abstract class BaseMultiAdapter extends BaseAdapter<IMultiItem> {
    @Override
    public int getLayoutRes(int index) {
        final IMultiItem data = mData.get(index);
        return data.getLayoutRes();
    }

    @Override
    public void convert(BaseViewHolder holder, IMultiItem data, int index) {
        data.convert(holder);
    }
}
