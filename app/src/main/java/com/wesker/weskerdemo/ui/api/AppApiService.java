package com.wesker.weskerdemo.ui.api;


import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Laughing on 2018/9/6.
 * 邮箱：719240226@qq.com
 */

public interface AppApiService {

    /**
     * 查询整个城市的合约车场
     * @param fields
     * @return
     */
    @FormUrlEncoded
    @POST("api/v3/parkingLot/queryParkingLotByCityName.do")
    Observable<Object> queryParkingLotByCityName(@FieldMap HashMap<String,Object> fields);
}


