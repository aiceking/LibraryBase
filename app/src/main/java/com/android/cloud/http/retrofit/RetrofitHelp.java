package com.android.cloud.http.retrofit;

import com.android.cloud.help.LogHelp;
import com.android.cloud.http.HttpsCerHelp.HttpsCerHelp;
import com.android.cloud.http.gsonhelp.GsonHelp;
import com.android.cloud.api.urlhelp.UrlHelp;
import com.android.cloud.libraryinit.BaseLibraryManager;
import java.util.concurrent.TimeUnit;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by radio on 2017/9/20.
 */

public class RetrofitHelp {
    public static final int CONNECT_TIME_OUT = 30;//连接超时时长x秒
    public static final int READ_TIME_OUT = 30;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 30;//写数据接超时时长x秒
    private static RetrofitHelp retrofitHelp;
    public Retrofit getRetrofit() {
        return retrofit;
    }
    private Retrofit retrofit;
    private RetrofitHelp(){
        initRetrofitAndNetApi();
    }
    public void initRetrofitAndNetApi() {
         retrofit = new Retrofit.Builder()
                .client(initOkHttpClient())
                .baseUrl(UrlHelp.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(GsonHelp.getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder mBuilder ;
        if (BaseLibraryManager.getInstance().isHasCer()) {
            //  设置https证书
            mBuilder = HttpsCerHelp.getClientBuilderByCer(BaseLibraryManager.getInstance().getContext(), BaseLibraryManager.getInstance().getCerNames());
        }else{
            mBuilder = HttpsCerHelp.getClientBuilderByCer(BaseLibraryManager.getInstance().getContext(), new String[]{});
        }
        //开启Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogHelp.showLog("okHttp：",message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        /**RetrofitUrlManager用于随时切换BaseUrl*/
        OkHttpClient client= RetrofitUrlManager.getInstance().with(mBuilder)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
        return client;
    }
    public static RetrofitHelp getInstance(){
        if (retrofitHelp==null){
            synchronized (RetrofitHelp.class){
                if (retrofitHelp==null){
                    retrofitHelp=new RetrofitHelp();
                }
            }
        }
        return retrofitHelp;
    }
    public  void  changeBaseUrl(String tag,String url){
        RetrofitUrlManager.getInstance().putDomain(tag, url);
    }
}
