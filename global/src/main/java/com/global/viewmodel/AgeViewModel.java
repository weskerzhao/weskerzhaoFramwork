package com.global.viewmodel;

import com.waterbase.widget.pickerview.model.IPickerViewData;

/**
 * 提供选择的年龄->人像查询页面
 * 作者：Laughing on 2018/4/15 12:47
 * 邮箱：719240226@qq.com
 */

public class AgeViewModel implements IPickerViewData {
    @Override
    public String getPickerViewText() {
        return age;
    }

    private String age;//年龄

    public AgeViewModel(String age) {

        this.age = age;
    }

    public String getAge() {
        return age;
    }
}
