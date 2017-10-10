package com.android.cloud.http.uploadimghelp;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by radio on 2017/10/10.
 */

public interface UpLoadImgService {
    /**上传图片*/
    @POST
    Call<JsonObject> UploadImages(@Url String upLoadImgUrl, @Body RequestBody Body);
}
