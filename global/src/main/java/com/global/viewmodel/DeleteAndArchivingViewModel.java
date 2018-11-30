package com.global.viewmodel;

import com.waterbase.widget.pickerview.model.IPickerViewData;

/**
 * 正在执行的项目-(用来提供是:1,删除；2,归档)
 * <p>
 * 作者：Laughing on 2018/4/15 12:47
 * 邮箱：719240226@qq.com
 */

public class DeleteAndArchivingViewModel implements IPickerViewData {
    @Override
    public String getPickerViewText() {
        return operating;
    }

    private String operating;//操作：1,删除；2,归档
    private String id;//用来区分用户的操作

    public DeleteAndArchivingViewModel(String operating, String id) {
        this.operating = operating;
        this.id = id;
    }

    public String getOperating() {
        return operating;
    }

    public String getId() {
        return id;
    }
}
