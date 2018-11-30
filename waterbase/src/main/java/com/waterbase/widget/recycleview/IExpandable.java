package com.waterbase.widget.recycleview;

import java.util.List;

/**
 * Created by Water on 2017/4/20.
 * 重写支持展开Adapter
 */

public interface IExpandable {
    /**
     * 用来判断区分是否展开
     * @return true 表示是展开状态， false 表示是关闭状态
     */
    boolean isExpandable();

    /**
     * 设置展开状态
     * @param expandable true 表示是展开状态， false 表示是关闭状态
     */
    void setExpandable(boolean expandable);

    /**
     * 返回可以展开的子列表
     * @return 返回子列表
     */
    List<IMultiItem> getSubItems();
}
