package com.android.cloud.http.HttpsCerHelp;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by radio on 2017/11/7.
 */

public class HttpsCerHelp {
    public static OkHttpClient.Builder getClientByCer(Context context, String[] fileNames){
        HttpsCerUtil.SSLParams sslParams;
        OkHttpClient.Builder okHttpClientBuilder;
        try {
            InputStream[] inputStreams=new InputStream[fileNames.length];
            for (int i=0;i<fileNames.length;i++){
                inputStreams[i]= context.getAssets().open(fileNames[i]);
            }
            sslParams = HttpsCerUtil.getSslSocketFactory(inputStreams, null, null);
            okHttpClientBuilder = new OkHttpClient.Builder()
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        } catch (Exception e) {
            sslParams = HttpsCerUtil.getSslSocketFactory(null, null, null);
            okHttpClientBuilder = new OkHttpClient.Builder()
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        }
        return okHttpClientBuilder;
    }
}
