package com.android.cloud.http.function;

import com.android.cloud.help.LogUtil;
import com.android.cloud.http.exception.ExceptionHelp;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by radio on 2017/9/20.
 */

public class HttpResponseFunction<T> implements Function<Throwable,Observable<T>>{
    private Observable baseObservable;
    public HttpResponseFunction(Observable baseObservable){
        this.baseObservable=baseObservable;
    }
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        LogUtil.showLog("Exception：",throwable.getMessage());
        if (ExceptionHelp.getException(throwable).getDisPlayMessage().equals("数据格式异常")){
            return baseObservable;
        }else{
        return Observable.error(ExceptionHelp.getException(throwable));
        }
    }
}
