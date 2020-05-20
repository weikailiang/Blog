package com.base.common.http.builder;
import com.base.comm_model.entity.FileBean;
import com.base.common.http.call.PicsRequestCall;
import com.base.common.http.call.PicsSynRequestCall;
import com.base.common.http.request.PostPicsRequest;
import com.base.common.http.service.PostService_Multi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class PostPicsBuilder extends BaseRequestBuilder<PostPicsBuilder, PicsRequestCall, PicsSynRequestCall> {
    protected String tempPath;

    protected String mark;

    protected List<FileBean> filePaths;


    protected Map<String, Object> params;

    public PostPicsBuilder() {
        service=PostService_Multi.class;
    }

    public PostPicsBuilder tempPath(String tempPath) {
        this.tempPath = tempPath;
        return this;
    }

    public PostPicsBuilder mark(String mark)
    {
        this.mark=mark;
        return this;
    }


    public PostPicsBuilder filePaths(List<FileBean> filePaths) {
        this.filePaths = filePaths;
        return this;
    }

    public PostPicsBuilder addFilePaths(FileBean fileBean) {
        if (this.filePaths == null) {
            this.filePaths = new ArrayList<FileBean>();
        }
        filePaths.add(fileBean);
        return this;
    }


    public PostPicsBuilder params(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public PostPicsBuilder addParams(String key, Object value) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.put(key, value);
        return this;
    }


    @Override
    public PicsRequestCall build() {
        return new PostPicsRequest(method,service,filePaths,tempPath,mark,params,headers,baseUrl).build();
    }

    @Override
    public PicsSynRequestCall SynBuild() {
        return new PostPicsRequest(method,service,filePaths,tempPath,mark,params,headers,baseUrl).synBuild();
    }
}
