package com.global.listener;

import android.view.View;

/**
 * 项目-正在进行的项目及已完成的项目使用
 * 作者：Laughing on 2018/5/15 20:37
 * 邮箱：719240226@qq.com
 */

public interface ProjectItemClickListener<T> {
    /**
     * 点击 item左边图像
     *
     * @param v
     * @param t
     * @param index
     */
    void leftImageClick(View v, T t, int index);

    /**
     * 点击 item中间内容
     *
     * @param v
     * @param t
     * @param index
     */
    void centerContentClick(View v, T t, int index);

    /**
     * 点击 item右边图像
     *
     * @param v
     * @param t
     * @param index
     */
    void rightImageClick(View v, T t, int index);

}
