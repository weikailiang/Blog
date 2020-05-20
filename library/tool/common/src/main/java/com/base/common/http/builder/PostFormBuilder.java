package com.base.common.http.builder;
import com.base.common.http.call.DataRequestCall;
import com.base.common.http.call.DataSynRequestCall;
import com.base.common.http.request.PostFormRequest;
import com.base.common.http.service.PostService_Form;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Date：2019/3/29 上午11:56
 * Creator：arvin
 * Des：
 */

public class PostFormBuilder extends BaseRequestBuilder<PostFormBuilder, DataRequestCall, DataSynRequestCall> {

    protected Map<String,String> params;

    public PostFormBuilder() {
        service=PostService_Form.class;
    }

    public PostFormBuilder params(Map<String,String> params)
    {
        this.params=params;
        return  this;
    }

    public PostFormBuilder addParams(String key, String value)
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
        return (new PostFormRequest(method,service,params,headers,baseUrl)).build();
    }

    @Override
    public DataSynRequestCall SynBuild() {
        return (new PostFormRequest(method,service,params,headers,baseUrl)).synBuild();
    }
}
