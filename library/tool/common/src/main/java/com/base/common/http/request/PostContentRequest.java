package com.base.common.http.request;

import com.base.common.http.RetrofitInitial;
import com.base.common.http.call.DataRequestCall;
import com.base.common.http.call.DataSynRequestCall;
import com.base.common.http.service.PostService_Content;

import java.util.Map;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class PostContentRequest extends BaseRequest<PostService_Content,DataRequestCall,DataSynRequestCall>{
    protected Object content;

    public PostContentRequest(String method, Class<PostService_Content> service, Object content, Map<String, String> headers, String baseUrl) {
        super(method, service, headers, baseUrl);
        this.content=content;
    }




    @Override
    public DataRequestCall build() {
        PostService_Content serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new DataRequestCall(serviceIml.upContent(baseUrl+method,content));
        }else
        {
            return new DataRequestCall(serviceIml.upContent(baseUrl+method,content,headers));
        }
    }

    @Override
    public DataSynRequestCall synBuild() {
        PostService_Content serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new DataSynRequestCall(serviceIml.synUpContent(baseUrl+method,content));
        }else
        {
            return new DataSynRequestCall(serviceIml.synUpContent(baseUrl+method,content,headers));
        }
    }
}
