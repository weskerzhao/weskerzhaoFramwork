package com.global.viewmodel;

import com.waterbase.widget.pickerview.model.IPickerViewData;

/**
 * 派给我的-任务-(用来提供是任务状态)
 * 0：全部
 * 1：未开始
 * 2：进行中
 * 3：已超期
 * 4：待验收
 * 5：已完成
 * <p>
 * 作者：Laughing on 2018/4/15 12:47
 * 邮箱：719240226@qq.com
 */

public class TaskStateViewModel implements IPickerViewData {
    @Override
    public String getPickerViewText() {
        return state;
    }

    private String state;//任务状态 0：全部 1：未开始 2：进行中 3：已超期 4：待验收 5：已完成
    private String id;//用来区分用户的操作

    public TaskStateViewModel(String priority, String id) {
        this.state = priority;
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public String getId() {
        return id;
    }
}
