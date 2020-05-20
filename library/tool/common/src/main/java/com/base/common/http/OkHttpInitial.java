package com.base.common.http;

import android.text.TextUtils;

import com.base.common.BaseApp;
import com.base.common.http.response.NetWorkInitial;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
class OkHttpInitial {


    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    private static final long READTIMEOUT = 20000l;
    private static final long WRITETIMEOUT = 20000l;
    private static final long CONNECTTIMEOUT = 20000l;
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;

    private static class HolderClass {
        private final static OkHttpClient sInstance = get();
    }


    private static OkHttpClient get() {
        //缓存
        File cacheFile = new File(BaseApp.sAppContext.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READTIMEOUT, UNIT);
        builder.writeTimeout(WRITETIMEOUT, UNIT);
        builder.connectTimeout(CONNECTTIMEOUT, UNIT);
        builder.retryOnConnectionFailure(false);
        builder.addInterceptor(mRewriteCacheControlInterceptor);
        builder.addNetworkInterceptor(mRewriteCacheControlInterceptor);

        builder.addInterceptor(logInterceptor);
        builder.addInterceptor(headerInterceptor);
        builder.cache(cache);

        NetWorkInitial.addCustom(builder);
        NetWorkInitial.setCertificates(builder);

        return builder.build();
    }

    static OkHttpClient getInstance() {
        return OkHttpInitial.HolderClass.sInstance;
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (!NetWorkUtils.isNetConnected(BaseApp.sAppContext)) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK:CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(BaseApp.sAppContext)) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置

                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };





}
