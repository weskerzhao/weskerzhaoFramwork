package com.global.listener;

import android.view.View;

/**
 * 长按事件
 * Created by Water on 2018/5/7.
 */

public interface ItemLongClickListener<T> {

    /**
     * 长按
     *
     * @param v
     * @param t
     * @param index
     */
    void itemLongClick(View v, T t, int index);

}
