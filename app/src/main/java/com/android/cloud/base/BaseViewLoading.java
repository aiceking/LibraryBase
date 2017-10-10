package com.android.cloud.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by radio on 2017/9/19.
 * 显示dialog的BaseView
 */

public interface BaseViewLoading {
    void showLoading(String msg);
    void closeLoading();
    void showToast(String msg);
    LifecycleTransformer bindLifecycle();
}
