/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.waterbase.http.converter;

import android.content.Intent;

import com.google.gson.TypeAdapter;
import com.waterbase.http.common.BasicResponse;
import com.waterbase.http.exception.ServerResponseException;
import com.waterbase.ui.BaseApplication;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        try {
            BasicResponse response = (BasicResponse) adapter.fromJson(value.charStream());

//            LogUtil.e("TAG", "laughing------response.getMessage()response.getMessage()response.getMessage()response.getMessage()---------------->   " + response.getMessage());


            if (response.getCode() == 0) {      //请求成功
                if (response.getData() == null)
                    return "";
                else
                    return response.getData();
            } else {
                // 特定 API 的错误，在相应的 DefaultObserver 的 onError 的方法中进行处理
                throw new ServerResponseException(response.getCode(), response.getMsg());
            }
        } finally {
            value.close();
        }
    }

    private void jump() {
        Intent intent = null;
        try {
            intent = new Intent(BaseApplication.getAppContext(), Class.forName("com.wxkj.login.ui.activity.A_Login"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.getAppContext().startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//                intent.putExtra("data", bean);
    }
}
