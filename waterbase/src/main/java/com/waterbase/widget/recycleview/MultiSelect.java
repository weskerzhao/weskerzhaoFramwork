package com.waterbase.widget.recycleview;

import java.util.List;

/**
 * Created by Water on 2017/4/20.
 * item 选中状态监听
 */

public interface MultiSelect {
    /**
     * 清除所选
     */
    void clearSelectAll();

    /**
     * 全选
     */
    void selectAll();

    /**
     * 设置监听
     * @param onCheckedChangeListener 监听器，可以为空
     */
    void setOnItemCheckedChangeListener(OnItemCheckedChangeListener onCheckedChangeListener);

    /**
     * 返回全部选择
     * @return 返回全部已经勾选的子项
     */
    List<IMultiSelectItem> getSelectedItems();
}
