package com.waterbase.http.interceptor;

import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * http请求头 同意追加参数包括 token
 * Created by zhpan on 2018/3/21.
 */

public class HttpHeaderInterceptor implements Interceptor {

    private Request mRequest;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //  配置请求头
        String accessToken = "token";
        String tokenType = "tokenType";
//        String cookie = PreferencesManager.getInstance(Utils.getContext()).get("cookie");
        String token = PreferencesManager.getInstance(BaseApplication.getAppContext()).get("token");

        if (!StrUtil.isEmpty(token)) {
            LogUtil.e("HttpHeaderInterceptor", "Cookie: " + token);
            mRequest = chain.request().newBuilder()
                    .header("app_key", "appId")
                    .header("Authorization", tokenType + " " + accessToken)
                    .header("Content-Type", "application/json")
                    .addHeader("Connection", "close")
                    .addHeader("Accept-Encoding", "identity")
                    .addHeader("token", token)
                    .build();
            LogUtil.e("URL", "HttpHeaderInterceptor----------------------token---------> : " + token);
            return chain.proceed(mRequest);
        } else {
            mRequest = chain.request().newBuilder()
                    .header("app_key", "appId")
                    .header("Authorization", tokenType + " " + accessToken)
                    .header("Content-Type", "application/json")
                    .addHeader("Connection", "close")
                    .addHeader("Accept-Encoding", "identity")
                    .build();
            LogUtil.e("URL", "HttpHeaderInterceptor----------------------token---------> : " + token);
            return chain.proceed(mRequest);
        }
    }
}
