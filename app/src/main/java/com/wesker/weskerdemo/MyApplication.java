package com.wesker.weskerdemo;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.waterbase.http.common.RxRetrofitApp;
import com.waterbase.ui.BaseApplication;

/**
 * 作者：Laughing on 2018/9/6 11:20
 * 邮箱：719240226@qq.com
 */
public class MyApplication extends BaseApplication {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//解决 64K 限制，方法数超过65535解决方法
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 此方法以完成http请求的初始化
     * 根据不同的服务器选择不同的地址
     */
    @Override
    protected void initRxRetrofitApp() {
        RxRetrofitApp.init(BaseApplication.HOST);

    }

}
