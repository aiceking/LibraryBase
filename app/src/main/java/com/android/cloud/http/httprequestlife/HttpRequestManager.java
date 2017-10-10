package com.android.cloud.http.httprequestlife;

import io.reactivex.disposables.Disposable;

/**
 * Created by radio on 2017/9/20.
 */

public interface HttpRequestManager {
    void add(String tag, Disposable disposable);
    void remove(String tag);
    void cancle(String tag);
}
