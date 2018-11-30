package com.wesker.login.api;

import com.global.bean.AppUpdate;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Laughing on 2018/9/6.
 * 邮箱：719240226@qq.com
 */

public interface LoginApiService {
    /**
     * 密码登录 ok
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/v3/userInfo/login.do")
    Observable<Object> login(@FieldMap Map<String, Object> fields);

    /**
     * 版本更新
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/v3/appUpdate/findVersion.do")
    Observable<AppUpdate> getApkUrl(@FieldMap Map<String, Object> fields);


}
