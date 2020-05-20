package com.base.common.http.builder;
import com.base.common.http.call.DataRequestCall;
import com.base.common.http.call.DataSynRequestCall;
import com.base.common.http.request.GetQueryRequest;
import com.base.common.http.service.GetService_Query;

import java.util.LinkedHashMap;
import java.util.Map;


public class GetQueryBuilder extends BaseRequestBuilder<GetQueryBuilder, DataRequestCall, DataSynRequestCall> {

    protected Map<String,Object> params;

    public GetQueryBuilder() {
        service=GetService_Query.class;
    }

    public GetQueryBuilder params(Map<String,Object> params)
    {
        this.params=params;
        return  this;
    }

    public GetQueryBuilder addParams(String key, String value)
    {
        if(this.params==null)
        {
            this.params=new LinkedHashMap<>();
        }
        this.params.put(key,value);
        return this;
    }
    @Override
    public DataRequestCall build() {
        return (new GetQueryRequest(method,service,params,headers,baseUrl)).build();
    }

    @Override
    public DataSynRequestCall SynBuild() {
        return (new GetQueryRequest(method,service,params,headers,baseUrl)).synBuild();
    }
}
