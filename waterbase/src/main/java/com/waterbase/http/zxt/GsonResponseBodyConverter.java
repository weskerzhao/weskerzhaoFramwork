package com.waterbase.http.zxt;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.waterbase.http.common.BasicResponse;
import com.waterbase.http.exception.ServerResponseException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        //▲ value 服务器回传给我们的body
        String response = value.string();
//        LogUtil.e("TAG", "-----Laughing-----GsonResponseBodyConverter---------value.string()-------->   " + value.string());
        //构建泛型的type  BaseBean<type>
        Type baseBeanType = $Gson$Types.newParameterizedTypeWithOwner(null, BasicResponse.class, type);

        BasicResponse baseBean = gson.fromJson(response, baseBeanType);
        // TODO: 2018/11/5 判断请求是否成功
        if (baseBean.getCode() != 0) {
            throw new ServerResponseException(baseBean.getCode(), baseBean.getMsg());
        } else {
            //成功返回 继续用原来的 Type类 解析

            return (T) baseBean.getData();
        }

    }
}