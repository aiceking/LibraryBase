package com.android.cloud.response;

/**
 * Created by static on 2018/3/28/028.
 */

public class BaseStatusModel {
    private int status;
    private String message;
    public boolean isSuccess() {
        return status==0?true:false;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
