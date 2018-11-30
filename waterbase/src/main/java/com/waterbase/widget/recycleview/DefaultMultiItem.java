package com.waterbase.widget.recycleview;

import android.support.annotation.LayoutRes;

/**
 * Created by Water on 2017/4/20.
 * 一个默认的实现{@link IMultiItem}大部分方法的抽象类，一般继承实现此抽象类即可。
 */

public abstract class DefaultMultiItem<T> implements IMultiItem {
    @LayoutRes
    private final int mLayoutRes;

    private int mSpanSize;

    protected T mData;

    public DefaultMultiItem(@LayoutRes int layoutRes) {
        this(layoutRes,null);
    }

    public DefaultMultiItem(@LayoutRes int layoutRes, T data) {
        this(layoutRes, data, 1);
    }

    public DefaultMultiItem(@LayoutRes int layoutRes, T data, int spanSize) {
        this.mLayoutRes = layoutRes;
        this.mData = data;
        this.mSpanSize = spanSize;
    }

    public T getData() {
        return mData;
    }

    public void setData(T mData) {
        this.mData = mData;
    }

    @Override
    public int getLayoutRes() {
        return mLayoutRes;
    }

    @Override
    public int getSpanSize() {
        return mSpanSize;
    }
}
