package com.global.viewmodel;

import com.waterbase.widget.pickerview.model.IPickerViewData;

/**
 * 派给我的-任务-(用来提供是任务优先级 0：全部  1：普通 2：重要 3：紧急)
 * <p>
 * 作者：Laughing on 2018/4/15 12:47
 * 邮箱：719240226@qq.com
 */

public class TaskPriorityViewModel implements IPickerViewData {
    @Override
    public String getPickerViewText() {
        return priority;
    }

    private String priority;//任务优先级
    private String id;//用来区分用户的操作   0：全部  1：普通 2：重要 3：紧急

    public TaskPriorityViewModel(String priority, String id) {
        this.priority = priority;
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }
}
