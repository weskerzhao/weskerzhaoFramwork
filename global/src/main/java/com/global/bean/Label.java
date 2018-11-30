package com.global.bean;

import com.waterbase.widget.pickerview.model.IPickerViewData;

/**
 * 标签类
 * Created by Water on 2018/5/14.
 */

public class Label implements IPickerViewData {

    private int id;
    private String content;

    public Label(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getPickerViewText() {
        return content;
    }
}
