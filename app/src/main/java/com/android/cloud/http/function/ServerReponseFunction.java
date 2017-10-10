package com.android.cloud.http.function;

import com.android.cloud.help.LogHelp;
import com.android.cloud.http.exception.ServerException;
import com.android.cloud.http.gsonhelp.GsonHelp;
import com.android.cloud.api.responsebean.BaseResponseBean;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by radio on 2017/9/20.
 */

public class ServerReponseFunction implements Function<BaseResponseBean,Object>{

    @Override
    public Object apply(@NonNull BaseResponseBean baseResponseBean) throws Exception {
        if (!baseResponseBean.isSuccess()){
            throw new ServerException(baseResponseBean.getStatus(), baseResponseBean.getMessage().toString());
        }
        LogHelp.showJsonLog(GsonHelp.getGson().toJson(baseResponseBean));
        return baseResponseBean;
    }
}
