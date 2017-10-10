package com.android.cloud.http.function;
import android.util.Log;

import com.android.cloud.help.LogHelp;
import com.android.cloud.http.exception.ExceptionHelp;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by radio on 2017/9/20.
 */

public class HttpResponseFunction<T> implements Function<Throwable,Observable<T>>{
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        LogHelp.showLog(throwable.getMessage());
        return Observable.error(ExceptionHelp.getException(throwable));
    }
}
