package com.android.cloud.http.uploadimghelp;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.cloud.api.responsebean.BaseResponseBean;
import com.android.cloud.help.LogHelp;
import com.android.cloud.http.exception.ApiException;
import com.android.cloud.http.exception.ExceptionHelp;
import com.android.cloud.http.gsonhelp.GsonHelp;
import com.android.cloud.http.retrofit.RetrofitHelp;
import com.android.cloud.libraryinit.BaseLibraryInitHelp;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import top.zibin.luban.Luban;

/**
 * Created by radio on 2017/9/2.
 */

public class UpLoadImgRunnable implements Runnable{
private Handler handler;
    private String path;
    private Activity context;
    private String upLoadImgUrl;
    public UpLoadImgRunnable(Handler handler,Activity context, String path,String upLoadImgUrl){
        this.handler=handler;
        this.context=context;
        this.path=path;
        this.upLoadImgUrl=upLoadImgUrl;
    }
    @Override
    public void run() {
        if (!context.isFinishing()){
        try {
            File file=Luban.with(context).load(new File(path)).get(path);
            if (file!=null){
                LogHelp.showLog("uploadimgFile==",file.getAbsolutePath());
                UpLoadImgBean uploadImgBean=postImageTongBuList(file);
                sendMessage(uploadImgBean);
            }else{
                UpLoadImgBean uploadImgBean=postImageTongBuList(new File(path));
                sendMessage(uploadImgBean);
            }
        } catch (IOException e) {
            UpLoadImgBean uploadImgBean=new UpLoadImgBean();
            uploadImgBean.setCode(0);
            uploadImgBean.setMessage(e.getMessage());
            sendMessage(uploadImgBean);
            e.printStackTrace();
        }
        }
    }

    private void sendMessage(UpLoadImgBean uploadImgBean) {
        if (!context.isFinishing()){
        Message message=Message.obtain();
        message.what=uploadImgBean.getCode();
        message.obj=uploadImgBean.getMessage();
        handler.sendMessage(message);
        }
    }

    public UpLoadImgBean postImageTongBuList(File file) {
        UpLoadImgBean uploadImgBean=new UpLoadImgBean();
        try {
            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))
                    .build();
            Response response= BaseLibraryInitHelp.getInstance().getUpLoadImgService().UploadImages(upLoadImgUrl,requestBody).execute();
            if (response.isSuccessful()){
                Type jsonType = new TypeToken<BaseResponseBean<String>>() {}.getType();
                BaseResponseBean<String> uploadPicResponse = GsonHelp.getGson().fromJson(response.body().toString(), jsonType);
                if (uploadPicResponse.isSuccess()){
                    uploadImgBean.setCode(1);
                    LogHelp.showLog("uploadimgName=", uploadPicResponse.getValues());
                    uploadImgBean.setMessage(uploadPicResponse.getValues());
                }else{
                    uploadImgBean.setCode(0);
                    uploadImgBean.setMessage("服务器异常");
                }
            }else{
                uploadImgBean.setCode(0);
                uploadImgBean.setMessage(ExceptionHelp.getErrorString(response.code()));
            }
        } catch (Exception e) {
            ApiException apiException = ExceptionHelp.getException(e);
            uploadImgBean.setCode(0);
            uploadImgBean.setMessage(apiException.getDisPlayMessage());
            e.printStackTrace();
        }
        return uploadImgBean;
    }
}
