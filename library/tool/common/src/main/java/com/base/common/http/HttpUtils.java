package com.base.common.http;

import com.base.common.http.builder.GetDownLoadBuilder;
import com.base.common.http.builder.GetQueryBuilder;
import com.base.common.http.builder.PostContentBuilder;
import com.base.common.http.builder.PostFileBuilder;
import com.base.common.http.builder.PostFormBuilder;
import com.base.common.http.builder.PostPicsBuilder;
import com.base.common.sp.SpUtil;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class HttpUtils {

    public static GetDownLoadBuilder downLoad() {
        return new GetDownLoadBuilder().baseUrl(Config.getBaseUrl())
                .addHeaders("token", SpUtil.getToken())
                .addHeaders("User-Agent", "ANDROID");
    }

    public static GetQueryBuilder get()
    {
        return new GetQueryBuilder().baseUrl(Config.getBaseUrl())
                .addHeaders("token",SpUtil.getToken())
                .addHeaders("User-Agent","ANDROID");
    }

    public static PostContentBuilder postContent() {
        return new PostContentBuilder().baseUrl(Config.getBaseUrl())
                .addHeaders("token",SpUtil.getToken())
                .addHeaders("User-Agent","ANDROID")
                .addHeaders("Content-Type","application/json");
    }

    public static PostFormBuilder postForm() {
        return new PostFormBuilder().baseUrl(Config.getBaseUrl())
                .addHeaders("token",SpUtil.getToken())
                .addHeaders("User-Agent","ANDROID");
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder().baseUrl(Config.getBaseUrl())
                .addHeaders("token",SpUtil.getToken())
                .addHeaders("User-Agent","ANDROID");
    }

    public static PostPicsBuilder postPics() {
        return new PostPicsBuilder().baseUrl(Config.getBaseUrl())
                .addHeaders("token",SpUtil.getToken())
                .addHeaders("User-Agent","ANDROID");
    }

}
