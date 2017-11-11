package com.android.cloud.libraryinit;

import android.content.Context;

import com.android.cloud.http.retrofit.RetrofitHelp;
import com.android.cloud.http.uploadimghelp.UpLoadImgService;
import com.lzy.imagepicker.ImagePicker;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by radio on 2017/9/13.
 */

public class BaseLibraryInitHelp<T> {
private static BaseLibraryInitHelp oncreateDoingHelp;
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
    private BaseLibraryInitHelp(){
    }
    public static BaseLibraryInitHelp getInstance(){
        if (oncreateDoingHelp==null){
            synchronized (BaseLibraryInitHelp.class){
                if (oncreateDoingHelp==null){
                    oncreateDoingHelp=new BaseLibraryInitHelp();
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
    public void setImagePickerLoader(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
//        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
//        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
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
