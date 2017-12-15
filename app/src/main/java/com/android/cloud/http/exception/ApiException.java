package com.android.cloud.http.exception;

/**
 * Created by radio on 2017/9/20.
 */

public class ApiException extends Exception{
    private int code;
    private Throwable throwable;

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    private String disPlayMessage;
    public ApiException(Throwable throwable, int code) {
        this.throwable=throwable;
        this.code = code;
    }
    public ApiException(int code, String disPlayMessage) {
        this.code = code;
        this.disPlayMessage = disPlayMessage;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public String getDisPlayMessage() {
        return disPlayMessage;
    }
    public void setDisPlayMessage(String disPlayMessage) {
        this.disPlayMessage = disPlayMessage;
    }
}
