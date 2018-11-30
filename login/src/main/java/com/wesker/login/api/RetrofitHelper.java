package com.wesker.login.api;


import com.waterbase.http.common.IdeaApi;
import com.waterbase.http.common.RxRetrofitApp;

public class RetrofitHelper {
    private static LoginApiService mWorkingApiService;

    public static LoginApiService getApiService(){
        return mWorkingApiService;
    }

    static {
        mWorkingApiService= IdeaApi.getApiService(LoginApiService.class, RxRetrofitApp.getApiServerUrl());
    }
}
