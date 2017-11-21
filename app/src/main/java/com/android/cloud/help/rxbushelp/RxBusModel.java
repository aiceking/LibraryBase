package com.android.cloud.help.rxbushelp;

/**
 * Created by static on 2017/11/20/020.
 */

public class RxBusModel<T> {
    private int code;

    public RxBusModel(int code, T values) {
        this.code = code;
        this.values = values;
    }

    private T values;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getValues() {
        return values;
    }

    public void setValues(T values) {
        this.values = values;
    }
}
