package com.base.common.http.request;
import com.base.comm_model.entity.FileBean;
import com.base.common.http.call.PicsRequestCall;
import com.base.common.http.call.PicsSynRequestCall;
import com.base.common.http.service.PostService_Multi;

import java.util.List;
import java.util.Map;

/**
 * Date：2019/3/30 下午12:20
 * Creator：arvin
 * Des：
 */

public class PostPicsRequest extends BaseRequest<PostService_Multi,PicsRequestCall,PicsSynRequestCall> {

    protected String tempPath;
    protected String mark;
    protected List<FileBean> filePaths;
    protected Map<String, Object> params;


    public PostPicsRequest(String method, Class<PostService_Multi> service, List<FileBean> filePaths, String tempPath, String mark, Map<String, Object> params, Map<String, String> headers, String baseUrl) {
        super(method, service, headers, baseUrl);
        this.filePaths = filePaths;
        this.params = params;
        this.tempPath = tempPath;
        this.mark = mark;
    }



    public String getTempPath() {
        return tempPath;
    }

    public String getMark() {
        return mark;
    }


    public List<FileBean> getFilePaths() {
        return filePaths;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getMethod() {
        return method;
    }

    public Class getService() {
        return service;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
    public String getBaseUrl() {
        return baseUrl;
    }




    @Override
    public PicsRequestCall build() {
        return new PicsRequestCall(this);
    }

    @Override
    public PicsSynRequestCall synBuild() {
        return new PicsSynRequestCall(this);
    }
}
