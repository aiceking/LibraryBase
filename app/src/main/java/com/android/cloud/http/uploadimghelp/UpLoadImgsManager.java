package com.android.cloud.http.uploadimghelp;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by radio on 2017/9/2.
 */

public class UpLoadImgsManager {
    private List<String> list_success;
    private List<String> list_upload;
    private ExecutorService imgUploadThreadPool;
    private UploadImagesListener uploadImagesListener;
    private Handler handler;
    private int errorNum;
    private String upLoadImgUrl;
    private Activity upLoadActivity;
    public UpLoadImgsManager(Activity activity,String upLoadImgUrl){
        this.upLoadImgUrl=upLoadImgUrl;
        upLoadActivity=activity;
        imgUploadThreadPool= Executors.newFixedThreadPool(4);
        list_success=new ArrayList<>();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (upLoadActivity.isFinishing())return;
                super.handleMessage(msg);
                String message=(String) msg.obj;
                if (msg.what==0){
                    if (message!=null){
                        if (uploadImagesListener!=null){
                            uploadImagesListener.onError(message);
                        }
                    }
                }else {
                    list_success.add(message);
                    if (list_success.size()==list_upload.size()-errorNum){
                        if (uploadImagesListener!=null){
                            uploadImagesListener.onSuccess(list_success);
                        }
                    }
                }
            }
        };
    }
    public  void uploadImages( List<String> list_upload, UploadImagesListener uploadImagesListener){
        this.uploadImagesListener=uploadImagesListener;
        this.list_upload=list_upload;
        if (this.uploadImagesListener!=null){
        this.uploadImagesListener.onStart();
        }
        for (String path:list_upload){
            if (TextUtils.isEmpty(path)){
                errorNum++;
                continue;
            }
            upLoadImage(upLoadActivity,path);
        }
    }
public void upLoadImage(Activity context,String path){
    imgUploadThreadPool.execute(new UpLoadImgRunnable(handler,context,path,upLoadImgUrl));
}
public interface UploadImagesListener{
    void onStart();
    void onSuccess(List<String> list);
    void onError(String message);
}
}
