package com.waterbase.ui;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.waterbase.utile.Utils;

/**
 * Application基类：处理全局初始化的内容
 * Created by Laughing on 2018/9/6.
 * 邮箱：719240226@qq.com
 */
public abstract class BaseApplication extends MultiDexApplication {
    public static int DEFAULT_TIMEOUT = 30000;
    public static String IP = "47.98.62.218:8080";
    //    public static String HOST = "http://192.168.1.101:8082/";//刘哥
//    public static String HOST = "http://192.168.1.132:8083/";//鲁宇
//    public static final String HOST = "https://www.yotingche.com/";// 线上
    //        public static String HOST = "http://192.168.1.68:8889/";//秦晓东
    public static String HOST = "https://www.yotingche.com/";//服务器地址
    public static String API_SERVER_URL = HOST + "police-check-service/";

    // H5页面地址
    public static final String H5_URL = BaseApplication.HOST + "h5/agreement/detail.do";//服务器地址
    protected static BaseApplication application;


    public static Context getAppContext() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//waterbase中的Utils初始化
        initRxRetrofitApp();
        application = this;

    }

    /**
     * 重写此方法以完成http请求的初始化
     */
    protected abstract void initRxRetrofitApp();

}
