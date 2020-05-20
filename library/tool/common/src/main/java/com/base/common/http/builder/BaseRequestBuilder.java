package com.base.common.http.builder;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseRequestBuilder<T,H ,G > {
    protected String method;
    protected Class service;
    protected Map<String,String> headers;
    protected String baseUrl;


    public T method(String method)
    {
        this.method=method;
        return (T) this;
    }

    public T baseUrl(String baseUrl)
    {
        this.baseUrl=baseUrl;
        return (T)this;
    }


    public T headers(Map<String,String> headers)
    {
        this.headers=headers;
        return (T) this;
    }

    public T addHeaders(String key, String value)
    {
        if(this.headers==null)
        {
            this.headers=new LinkedHashMap<>();
        }
        this.headers.put(key,value);
        return (T) this;
    }




    public abstract H build();

    public abstract G SynBuild();




}
