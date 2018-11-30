package com.global.viewmodel;

import com.waterbase.widget.pickerview.model.IPickerViewData;

/**
 * 派给我的-任务-(用来提供是任务优先级 0：全部  1：未阅读)
 * <p>
 * 作者：Laughing on 2018/4/15 12:47
 * 邮箱：719240226@qq.com
 */

public class TaskIsReadViewModel implements IPickerViewData {
    @Override
    public String getPickerViewText() {
        return read;
    }

    private String read;//以是否阅读为任务类型  0：全部  1：未阅读
    private String id;//用来区分用户的操作

    public TaskIsReadViewModel(String priority, String id) {
        this.read = priority;
        this.id = id;
    }

    public String getRead() {
        return read;
    }

    public String getId() {
        return id;
    }
}
