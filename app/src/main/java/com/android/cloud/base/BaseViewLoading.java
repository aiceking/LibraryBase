package com.android.cloud.base;


/**
 * Created by radio on 2017/9/19.
 * 显示dialog的BaseView
 */

public interface BaseViewLoading {
    void showLoading(String msg);
    void closeLoading();
    void showToast(String msg);
}
