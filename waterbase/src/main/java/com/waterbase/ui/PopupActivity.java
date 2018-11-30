package com.waterbase.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.waterbase.R;

/**
 * 作者：Laughing on 2018/10/25 21:25
 * 邮箱：719240226@qq.com
 */
public class PopupActivity extends BaseActivity {


    public static void startActivity(Context context) {
            context.startActivity(new Intent(context, PopupActivity.class));
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window2);
    }
}
