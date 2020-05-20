package com.base.common.http.builder;
import com.base.comm_model.entity.FileBean;
import com.base.common.http.call.FileRequestCall;
import com.base.common.http.call.FileSynRequestCall;
import com.base.common.http.request.PostFileRequest;
import com.base.common.http.service.PostService_Multi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PostFileBuilder extends BaseRequestBuilder<PostFileBuilder, FileRequestCall, FileSynRequestCall> {

    protected List<FileBean> filePaths;


    protected Map<String,Object> params;

    public PostFileBuilder() {
        service=PostService_Multi.class;
    }

    public PostFileBuilder filePaths(List<FileBean> filePaths)
    {
        this.filePaths=filePaths;
        return this;
    }


    public PostFileBuilder addFilePaths(FileBean fileBean)
    {
        if(this.filePaths==null)
        {
            this.filePaths=new ArrayList<FileBean>();
        }
        filePaths.add(fileBean);
        return this;
    }



    public PostFileBuilder params(Map<String,Object> params)
    {
        this.params=params;
        return  this;
    }

    public PostFileBuilder addParams(String key, Object value)
    {
        if(this.params==null)
        {
            this.params=new LinkedHashMap<>();
        }
        this.params.put(key,value);
        return this;
    }


    @Override
    public FileRequestCall build() {
        return new PostFileRequest(method,service,filePaths,params,headers,baseUrl).build();
    }

    @Override
    public FileSynRequestCall SynBuild() {
        return new PostFileRequest(method,service,filePaths,params,headers,baseUrl).synBuild();
    }
}
