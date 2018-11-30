package com.global.ustewardBase;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 通用 PagerAdapter
 * <p>
 * Author: Guangkuo
 * Email: dingguangkuo@163.com
 * Date: 2018-2-2  10:29
 */
public abstract class BaseViewPagerAdapter<T> extends PagerAdapter {
    private List<T> mData;
    private int mLayoutId;

    public BaseViewPagerAdapter(@LayoutRes int layoutId, List<T> data) {
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(mLayoutId, container, false);
        convert(inflate, mData.get(position));
        container.addView(inflate);
        return inflate;
    }

    /**
     * 抽象方法：因为各个代码不同，所以写成抽象让各自去实现
     *
     * @param view
     * @param t    当前Item的数据集
     */
    public abstract void convert(View view, T t);

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(((View) object));
    }
}