package com.base.common.http.request;

import com.base.common.http.RetrofitInitial;
import com.base.common.http.call.DataRequestCall;
import com.base.common.http.call.DataSynRequestCall;
import com.base.common.http.service.PostService_Form;

import java.util.Map;

public class PostFormRequest extends BaseRequest<PostService_Form,DataRequestCall,DataSynRequestCall> {

    private Map<String,String> params;

    public PostFormRequest(String method, Class<PostService_Form> service, Map<String,String> params, Map<String, String> headers, String baseUrl) {
        super(method, service, headers, baseUrl);
        this.params=params;
    }



    @Override
    public DataRequestCall build() {
        PostService_Form serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new DataRequestCall(serviceIml.upForm(baseUrl+method,params));
        }else
        {
            return new DataRequestCall(serviceIml.upForm(baseUrl+method,params,headers));
        }

    }

    @Override
    public DataSynRequestCall synBuild() {
        PostService_Form serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new DataSynRequestCall(serviceIml.synUpForm(baseUrl+method,params));
        }else
        {
            return new DataSynRequestCall(serviceIml.synUpForm(baseUrl+method,params,headers));
        }
    }
}
