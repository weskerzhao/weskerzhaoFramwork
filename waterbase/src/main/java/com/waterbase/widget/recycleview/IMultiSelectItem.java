package com.waterbase.widget.recycleview;


import android.support.annotation.IdRes;

/**
 * Created by Water on 2017/4/20.
 */

public interface IMultiSelectItem extends IMultiItem {

    void setChecked(boolean checked);

    boolean isChecked();

    /**
     * 用于点击更新选中状态的view id,此view 必须实现Checkable接口，
     * 否则应该是无效的
     * @return 返回一个实现了Checkable接口的View id.
     */
    @IdRes
    int getCheckableViewId();
}
