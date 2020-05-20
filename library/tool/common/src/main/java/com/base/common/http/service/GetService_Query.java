package com.base.common.http.service;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by weikailiang on 2020/5/6.
 */

public interface GetService_Query {
    @GET
    Call<String> synQuery(@Url String baseUrl, @QueryMap Map<String, Object> filters);

    @GET
    Flowable<Response<String>> query(@Url String baseUrl, @QueryMap Map<String, Object> filters);

    @GET
    Call<String> synQuery(@Url String baseUrl, @QueryMap Map<String, Object> filters, @HeaderMap Map<String, String> headers);

    @GET
    Flowable<Response<String>> query(@Url String baseUrl, @QueryMap Map<String, Object> filters, @HeaderMap Map<String, String> headers);
}
