package com.base.common.http.service;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by weikailiang on 2020/4/30.
 */

public interface PostService_Content {
    @POST
    Call<String> synUpContent(@Url String baseUrl, @Body Object content);

    @POST
    Flowable<Response<String>> upContent(@Url String baseUrl, @Body Object content);

    @POST
    Call<String> synUpContent(@Url String baseUrl, @Body Object content, @HeaderMap Map<String, String> headers);

    @POST
    Flowable<Response<String>> upContent(@Url String baseUrl, @Body Object content, @HeaderMap Map<String, String> headers);

}
