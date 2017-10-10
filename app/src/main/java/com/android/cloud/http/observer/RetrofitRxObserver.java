package com.android.cloud.http.observer;

import android.text.TextUtils;

import com.android.cloud.http.exception.ApiException;
import com.android.cloud.http.httprequestlife.HttpRequestListener;
import com.android.cloud.http.httprequestlife.HttpRequestManagerImpl;
import com.android.cloud.api.responsebean.BaseResponseBean;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by radio on 2017/9/20.
 */

public abstract class RetrofitRxObserver<T> implements Observer<BaseResponseBean<T>>,HttpRequestListener{
    private String Request_Tag;//请求标识
    public RetrofitRxObserver(){

    }
    public RetrofitRxObserver(String Request_Tag){
        this.Request_Tag=Request_Tag;
    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(Request_Tag)) {
            HttpRequestManagerImpl.getInstance().add(Request_Tag,d);
        }
        onStart(d);
    }

    @Override
    public void onNext(@NonNull BaseResponseBean<T> response) {
        if (!TextUtils.isEmpty(Request_Tag)) {
            HttpRequestManagerImpl.getInstance().remove(Request_Tag);
        }
        onSuccess(response);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        HttpRequestManagerImpl.getInstance().remove(Request_Tag);
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, 123));
        }
    }
    @Override
    public void cancle() {
        if (!TextUtils.isEmpty(Request_Tag)) {
            HttpRequestManagerImpl.getInstance().cancle(Request_Tag);
        }
    }
    /*** 是否已经取消请求*/
    public boolean isDisposed() {
        if (TextUtils.isEmpty(Request_Tag)) {
            return true;
        }
        return HttpRequestManagerImpl.getInstance().isDisposed(Request_Tag);
    }
    @Override
    public void onComplete() {

    }
    protected abstract void onStart(Disposable d);

    /**
     * 错误/异常回调
     *
     * @author ZhongDaFeng
     */
    protected abstract void onError(ApiException e);

    /**
     * 成功回调
     *
     * @author ZhongDaFeng
     */
    protected abstract void onSuccess(BaseResponseBean<T> response);
}
