package com.base.common.http.builder;

import com.base.common.http.call.DataRequestCall;
import com.base.common.http.call.DataSynRequestCall;
import com.base.common.http.request.PostContentRequest;
import com.base.common.http.service.PostService_Content;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class PostContentBuilder extends BaseRequestBuilder<PostContentBuilder, DataRequestCall, DataSynRequestCall> {
    protected Object content;

    public PostContentBuilder() {
        service=PostService_Content.class;
    }

    public PostContentBuilder content(Object content) {
        this.content = content;
        return this;
    }

    @Override
    public DataRequestCall build() {
        return (new PostContentRequest(method, service, content, headers,baseUrl)).build();
    }

    @Override
    public DataSynRequestCall SynBuild() {
        return (new PostContentRequest(method, service, content, headers,baseUrl)).synBuild();
    }
}
