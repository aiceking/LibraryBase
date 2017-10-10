package com.android.cloud.http.httprequestlife;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

/**
 * Created by radio on 2017/9/20.
 */

public class HttpRequestManagerImpl implements HttpRequestManager{
    private static HttpRequestManagerImpl httpRequestManagerImpl;
    private HashMap<String ,Disposable> request_map;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private HttpRequestManagerImpl(){
        request_map=new HashMap<>();
    }
    public static HttpRequestManagerImpl getInstance(){
        if (httpRequestManagerImpl==null){
            synchronized (HttpRequestManagerImpl.class){
                if (httpRequestManagerImpl==null){
                    httpRequestManagerImpl=new HttpRequestManagerImpl();
                }
            }
        }
        return httpRequestManagerImpl;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void add(String tag, Disposable disposable) {
        request_map.put(tag,disposable);
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void remove(String tag) {
        if (!request_map.isEmpty()){
            if (request_map.containsKey(tag)){
                request_map.remove(tag);
            }
        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancle(String tag) {
        if (request_map.isEmpty()) {
            return;
        }
        if (request_map.get(tag) == null) {
            return;
        }
        if (!request_map.get(tag).isDisposed()) {
            request_map.get(tag).dispose();
        }
        request_map.remove(tag);
    }
    /**判断是否已经取消了请求*/
    public boolean isDisposed(Object tag) {
        if (request_map.isEmpty() || request_map.get(tag) == null) return true;
        return request_map.get(tag).isDisposed();
    }
}
