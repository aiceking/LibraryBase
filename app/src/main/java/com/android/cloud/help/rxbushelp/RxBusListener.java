package com.android.cloud.help.rxbushelp;

/**
 * Created by static on 2017/11/20/020.
 */

public interface RxBusListener<T> {
    void onSuccess(RxBusModel<T> rxBusModel);
    void onFailed(Exception e);
}
