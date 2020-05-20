package com.base.common.http.service;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by weikailiang on 2020/5/6.
 */

public interface PostService_Form {

    @FormUrlEncoded
    @POST
    Call<String> synUpForm(@Url String baseUrl, @FieldMap Map<String, String> fields);


    @FormUrlEncoded
    @POST
    Flowable<Response<String>> upForm(@Url String baseUrl, @FieldMap Map<String, String> fields);


    @FormUrlEncoded
    @POST
    Call<String> synUpForm(@Url String baseUrl, @FieldMap Map<String, String> fields, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST
    Flowable<Response<String>> upForm(@Url String baseUrl, @FieldMap Map<String, String> fields, @HeaderMap Map<String, String> headers);
}
