package com.base.common.http.service;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * Created by weikailiang on 2020/5/6.
 */

public interface PostService_Multi {
    @Multipart
    @POST
    Call<String> synUpMulti(@Url String baseUrl, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST
    Flowable<Response<String>> upMulti(@Url String baseUrl, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST
    Call<String> synUpMulti(@Url String baseUrl, @PartMap Map<String, RequestBody> map, @HeaderMap Map<String, String> headers);

    @Multipart
    @POST
    Flowable<Response<String>> upMulti(@Url String baseUrl, @PartMap Map<String, RequestBody> map, @HeaderMap Map<String, String> headers);
}
