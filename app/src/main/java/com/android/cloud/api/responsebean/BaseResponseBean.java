package com.android.cloud.api.responsebean;

import com.google.gson.annotations.SerializedName;


/**
 * Created by radio on 2017/9/20.
 */

public class BaseResponseBean<T> {
    @SerializedName("status")
    private int status;
    private String message;
    private T values;
    public boolean isSuccess() {
        return status==0?true:false;
    }
    public T getValues() {
        return values;
    }
    public void setValues(T values) {
        this.values = values;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
