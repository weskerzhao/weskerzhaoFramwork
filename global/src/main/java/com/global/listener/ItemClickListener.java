package com.global.listener;

import android.view.View;

/**
 * Created by Water on 2018/5/7.
 */

public interface ItemClickListener<T> {
    /**
     * 点击
     *
     * @param v
     * @param t
     * @param index
     */
    void itemClick(View v, T t, int index);

}
