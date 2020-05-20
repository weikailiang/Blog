package com.base.common.http.builder;


import com.base.common.http.call.DownLoadRequestCall;
import com.base.common.http.call.DownLoadSynRequestCall;
import com.base.common.http.request.GetDownLoadRequest;
import com.base.common.http.service.GetService_DownLoad;

public class GetDownLoadBuilder extends BaseRequestBuilder<GetDownLoadBuilder, DownLoadRequestCall, DownLoadSynRequestCall> {


    protected String path;

    protected String fileName;

    public GetDownLoadBuilder() {
        service=GetService_DownLoad.class;
    }


    public GetDownLoadBuilder path(String path)
    {
        this.path=path;
        return this;
    }

    public GetDownLoadBuilder fileName(String fileName)
    {
        this.fileName=fileName;
        return this;
    }



    @Override
    public DownLoadRequestCall build() {
        return new GetDownLoadRequest(method,service,path,fileName,headers,baseUrl).build();
    }

    @Override
    public DownLoadSynRequestCall SynBuild() {
        return new GetDownLoadRequest(method,service,path,fileName,headers,baseUrl).synBuild();
    }
}
