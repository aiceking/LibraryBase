package com.android.cloud.http.retrofit;

import android.annotation.SuppressLint;

import com.android.cloud.help.LogHelp;
import com.android.cloud.http.gsonhelp.GsonHelp;
import com.android.cloud.api.urlhelp.UrlHelp;
import com.android.cloud.libraryinit.BaseLibraryInitHelp;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

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
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        if (BaseLibraryInitHelp.getInstance().isHasCer()){
          //  设置https证书
        X509TrustManager trustManager = null;
        SSLSocketFactory sslSocketFactory = null;
        try {
            String cer = BaseLibraryInitHelp.getInstance().isDebug() ? BaseLibraryInitHelp.getInstance().getCeshiCerName() : BaseLibraryInitHelp.getInstance().getShengchanCerName();
            InputStream inputStream = BaseLibraryInitHelp.getInstance().getContext().getAssets().open(cer); // 得到证书的输入流
            trustManager = trustManagerForCertificates(inputStream);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            sslSocketFactory = sslContext.getSocketFactory();
            //设置证书
            mBuilder.sslSocketFactory(sslSocketFactory, trustManager);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            e.printStackTrace();

        }
        }else{
        mBuilder.sslSocketFactory(createSSLSocketFactory());
        mBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
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
    public void reSet(){
        retrofitHelp=null;
        retrofit=null;
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
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    public  void  changeBaseUrl(String tag,String url){
        RetrofitUrlManager.getInstance().putDomain(tag, url);
    }
    private X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        // Put the certificates a key store.
        char[] password = "wxy".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }
        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
