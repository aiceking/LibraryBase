package com.android.cloud.http.gsonhelp;

import android.util.Log;

import com.android.cloud.help.LogUtil;
import com.android.cloud.http.exception.ServerException;
import com.android.cloud.response.BaseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by static on 2018/3/28/028.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(ResponseBody value) throws IOException{
        String response = value.string();
        try {
            LogUtil.showLog("response",response);
            //ResultResponse 只解析result字段
            BaseModel resultResponse = gson.fromJson(response, BaseModel.class);
            if (resultResponse.isSuccess() ){
                //result==0表示成功返回，继续用本来的Model类解析
                return gson.fromJson(response, type);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                Type jsonType = new TypeToken<BaseModel<String>>() {}.getType();
                BaseModel<String> errResponse  = gson.fromJson(response, jsonType);
                throw new ServerException(errResponse.getStatus(), errResponse.getMessage());
            }
        } finally {
            value.close();
        }
    }
}
