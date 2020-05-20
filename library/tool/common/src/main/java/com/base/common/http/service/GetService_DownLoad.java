package com.base.common.http.service;


import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
public interface GetService_DownLoad {

    @GET
    @Streaming
    Call<ResponseBody> synDownload(@Url String url);

    @GET
    @Streaming
    Flowable<Response<ResponseBody>> download(@Url String url);


    @GET
    @Streaming
    Call<ResponseBody> synDownload(@Url String url, @HeaderMap Map<String, String> headers);

    @GET
    @Streaming
    Flowable<Response<ResponseBody>> download(@Url String url, @HeaderMap Map<String, String> headers);
}
