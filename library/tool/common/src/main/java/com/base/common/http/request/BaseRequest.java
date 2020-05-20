package com.base.common.http.request;

import java.util.Map;

/**
 * Created by weikailiang on 2020/5/6.
 */

public abstract class BaseRequest<H,T,G> {
    protected String method;
    protected Class<H> service;
    protected Map<String, String> headers;
    protected String baseUrl;



    public BaseRequest(String method, Class<H> service, Map<String, String> headers, String baseUrl) {
        this.method = method;
        this.service = service;
        this.headers = headers;
        this.baseUrl = baseUrl;
    }

    public abstract T build();
    public abstract G synBuild();
}
