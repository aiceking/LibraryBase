package com.android.cloud.http.function;

import com.android.cloud.help.LogUtil;
import com.android.cloud.http.exception.ServerException;
import com.android.cloud.http.gsonhelp.GsonHelp;
import com.android.cloud.response.BaseModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by radio on 2017/9/20.
 */

public class ServerReponseFunction implements Function<BaseModel,Object>{

    @Override
    public Object apply(@NonNull BaseModel baseModel) throws Exception {
        if (!baseModel.isSuccess()){
            throw new ServerException(baseModel.getStatus(), baseModel.getMessage().toString());
        }
        LogUtil.showJsonLog("Json：",GsonHelp.getGson().toJson(baseModel));
        return baseModel;
    }
}
