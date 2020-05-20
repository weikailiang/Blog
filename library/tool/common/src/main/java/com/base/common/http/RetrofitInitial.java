package com.base.common.http;



import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitInitial {


    private static class HolderClass {
        private static final Retrofit sInstance = get();
    }

    private static Retrofit get() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://www.costomize.com/");//规避retrofit必需要设置一个baseurl的限制
        builder.client(OkHttpInitial.getInstance());
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }

    public static Retrofit getInstance() {
        return HolderClass.sInstance;
    }
}
