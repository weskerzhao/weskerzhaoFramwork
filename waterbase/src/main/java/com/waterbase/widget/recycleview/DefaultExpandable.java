package com.waterbase.widget.recycleview;

import android.support.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Water on 2017/4/20.
 */

public abstract class DefaultExpandable<T> implements IExpandable{

    protected final List<IMultiItem> mSubData;

    private int mSpanSize;

    private boolean mExpandable;

    @LayoutRes
    private final int mLayoutRes;

    protected T mData;

    public DefaultExpandable(@LayoutRes int layoutRes) {
        this(layoutRes, null);
    }

    public DefaultExpandable(@LayoutRes int layoutRes, T data){
        this(layoutRes, data, 1);
    }

    public DefaultExpandable(@LayoutRes int layoutRes, T data, int spanSize){
        mLayoutRes = layoutRes;
        mData = data;
        mSpanSize = spanSize;
        mSubData = new ArrayList<>();
        mExpandable = false;
    }

    public T getData() {
        return mData;
    }

    public void setData(T mData) {
        this.mData = mData;
    }

    public void setSubData(List<IMultiItem> subData){
        mSubData.clear();
        if (subData != null){
            mSubData.addAll(subData);
        }
    }

    public void addSubData(List<IMultiItem> subData){
        if (subData != null){
            mSubData.addAll(subData);
        }
    }

    public void addSubData(IMultiItem subData){
        if (subData != null){
            mSubData.add(subData);
        }
    }

    @Override
    public boolean isExpandable() {
        return mExpandable;
    }

    @Override
    public void setExpandable(boolean expandable) {
        mExpandable = expandable;
    }

    @Override
    public List<IMultiItem> getSubItems() {
        return mSubData;
    }

    public int getLayoutRes() {
        return mLayoutRes;
    }

    public int getSpanSize() {
        return mSpanSize;
    }
}
