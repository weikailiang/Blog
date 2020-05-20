package com.base.common.http.request;

import com.base.common.http.RetrofitInitial;
import com.base.common.http.call.DataRequestCall;
import com.base.common.http.call.DataSynRequestCall;
import com.base.common.http.service.GetService_Query;

import java.util.Map;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class GetQueryRequest extends BaseRequest<GetService_Query,DataRequestCall,DataSynRequestCall>{
    protected Map<String, Object> params;

    public GetQueryRequest(String method, Class<GetService_Query> service, Map<String, Object> params, Map<String, String> headers, String baseUrl) {
        super(method, service, headers, baseUrl);
        this.params = params;
    }



    @Override
    public DataRequestCall build() {
        GetService_Query serviceIml = RetrofitInitial.getInstance().create(service);
        if (headers == null || headers.isEmpty()) {
            return new DataRequestCall(serviceIml.query(baseUrl+method, params));

        } else {
            return new DataRequestCall(serviceIml.query(baseUrl+method, params, headers));
        }


    }

    @Override
    public DataSynRequestCall synBuild() {
        GetService_Query serviceIml = RetrofitInitial.getInstance().create(service);
        if (headers == null || headers.isEmpty()) {
            return new DataSynRequestCall(serviceIml.synQuery(baseUrl+method, params));

        } else {
            return new DataSynRequestCall(serviceIml.synQuery(baseUrl+method, params, headers));
        }
    }
}
