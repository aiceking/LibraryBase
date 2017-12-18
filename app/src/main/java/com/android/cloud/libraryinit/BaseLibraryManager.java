package com.android.cloud.libraryinit;

import android.content.Context;
import com.android.cloud.http.retrofit.RetrofitHelp;
import com.android.cloud.http.uploadimghelp.UpLoadImgService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
/**
 * Created by radio on 2017/9/13.
 */

public class BaseLibraryManager<T> {
private static BaseLibraryManager oncreateDoingHelp;
    private Context context;
    private boolean isDebug;
    private boolean isLog;
    private String ceshiUrl;
    private String shengchanUrl;
    public String[] getCerNames() {
        return cerNames;
    }
    public void setCerNames(String[] cerNames) {
        this.cerNames = cerNames;
    }
    private String []cerNames;
    private T netService;
    private boolean isHasCer;
    public UpLoadImgService getUpLoadImgService() {
        return upLoadImgService;
    }
    public void setUpLoadImgService() {
        upLoadImgService=RetrofitHelp.getInstance().getRetrofit().create(UpLoadImgService.class);
    }
    private UpLoadImgService upLoadImgService;
    private BaseLibraryManager(){
    }
    public static BaseLibraryManager getInstance(){
        if (oncreateDoingHelp==null){
            synchronized (BaseLibraryManager.class){
                if (oncreateDoingHelp==null){
                    oncreateDoingHelp=new BaseLibraryManager();
                }
            }
        }
        return oncreateDoingHelp;
    }
    public String getCeshiUrl() {
        return ceshiUrl;
    }
    public String getShengchanUrl() {
        return shengchanUrl;
    }
    public T getNetService() {
        return netService;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public Context getContext() {
        return context;
    }
    public boolean isDebug() {
        return isDebug;
    }
    public void setDebug(boolean debug) {
        isDebug = debug;
    }
    public void changeDebug(boolean debug,final Class<T> service){
        isDebug = debug;
        RetrofitHelp.getInstance().initRetrofitAndNetApi();
        netService=  RetrofitHelp.getInstance().getRetrofit().create(service);
        setUpLoadImgService();
    }
    public void setLogger(boolean isLog){
        this.isLog=isLog;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
    public boolean isLog() {
        return isLog;
    }
    public void setApiService(final Class<T> service){
        netService=  RetrofitHelp.getInstance().getRetrofit().create(service);
    }
    public void setBaseUrl(String ceshiUrl,String shengchanUrl){
        this.ceshiUrl=ceshiUrl;
        this.shengchanUrl=shengchanUrl;
    }
    public void setHttpsCer(boolean isHasCer){
        this.isHasCer=isHasCer;
    }
    public boolean isHasCer() {
        return isHasCer;
    }
}
