package com.android.cloud.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by radio on 2017/9/19.
 * 不显示dialog的BaseView
 */

public interface BaseView {
    void showToast(String msg);
    LifecycleTransformer bindLifecycle();
}
