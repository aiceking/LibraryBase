package com.android.cloud.http.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * Created by radio on 2017/9/20.
 */

public class ExceptionHelp {
    //对应HTTP的状态码
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException getException(Throwable e){
        ApiException ex;
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, Error.HTTP_ERROR);
            switch(httpException.code()){
                case NOT_FOUND:
                    ex.setDisPlayMessage("接口不存在");
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.setDisPlayMessage("参数异常或服务器异常");
                    break;
                case UNAUTHORIZED:
                case FORBIDDEN:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisPlayMessage("服务器异常");  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException){    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.getCode());
            ex.setDisPlayMessage(resultException.getMessage());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, Error.PARSE_ERROR);
            ex.setDisPlayMessage("数据格式异常");            //均视为解析错误
            return ex;
        }else if (e instanceof UnknownHostException){
            ex = new ApiException(e, Error.NETWORD_ERROR);
            ex.setDisPlayMessage("请检查网络连接");  //均视为网络错误
            return ex;
        }else if(e instanceof ConnectException){
            ex = new ApiException(e, Error.NETWORD_ERROR);
            ex.setDisPlayMessage("连接失败");  //均视为网络错误
            return ex;
        }else if (e instanceof SSLHandshakeException){
            ex = new ApiException(e, Error.CER_ERROR);
            ex.setDisPlayMessage("SSL证书无效");  //均视为网络错误
            return ex;
        }else {
            ex = new ApiException(e, Error.UNKNOWN);
            ex.setDisPlayMessage("未知错误");          //未知错误
            return ex;
        }
    }
public static String getErrorString(int code){
    String s="";
    switch (code){
        case NOT_FOUND:
            s="接口不存在";
            break;
        case INTERNAL_SERVER_ERROR:
            s="参数异常或服务器异常";
            break;
        case UNAUTHORIZED:
        case FORBIDDEN:
        case REQUEST_TIMEOUT:
        case GATEWAY_TIMEOUT:
        case BAD_GATEWAY:
        case SERVICE_UNAVAILABLE:
        default:
            s="网络错误";  //均视为网络错误
            break;
    }
    return s;
}
}
