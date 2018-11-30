package com.global.viewmodel;

import com.waterbase.widget.pickerview.model.IPickerViewData;

/**
 * 用来提供性别
 * 作者：Laughing on 2018/4/15 12:47
 * 邮箱：719240226@qq.com
 */

public class MyCenterSexViewModel implements IPickerViewData {
    @Override
    public String getPickerViewText() {
        return sex;
    }

    private String sex;//性别
    private String sexReq;//性别

    public MyCenterSexViewModel(String sex) {
        this.sex = sex;
    }

    public MyCenterSexViewModel(String sex, String sexReq) {
        this.sex = sex;
        this.sexReq = sexReq;
    }

    public String getSex() {
        return sex;
    }

    public String getSexReq() {
        return sexReq;
    }
}
