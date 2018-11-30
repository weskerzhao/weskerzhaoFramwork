package com.wesker.weskerdemo.ui.api;


import com.waterbase.http.common.IdeaApi;
import com.waterbase.http.common.RxRetrofitApp;

/**
 * Created by Laughing on 2018/9/6.
 * 邮箱：719240226@qq.com
 */
public class RetrofitHelper {
    private static AppApiService mWorkingApiService;

    public static AppApiService getApiService() {
        return mWorkingApiService;
    }

    static {
        mWorkingApiService = IdeaApi.getApiService(AppApiService.class, RxRetrofitApp.getApiServerUrl());
    }
}
