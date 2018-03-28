package com.android.cloud.http.exception;

/**
 * Created by radio on 2017/9/20.
 */

public class ServerException extends RuntimeException{
    private int code;
    private String message;
    public ServerException(int code,String message){
        this.code=code;
        this.message=message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
