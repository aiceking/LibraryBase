package com.android.cloud.http.exception;

/**
 * Created by radio on 2017/9/21.
 */

public class Error {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;
    /**
     * 证书错误
     */
    public static final int CER_ERROR= 1004;
}
