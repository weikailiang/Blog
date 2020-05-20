package com.base.common.http.request;
import com.base.common.http.RetrofitInitial;
import com.base.common.http.call.DownLoadRequestCall;
import com.base.common.http.call.DownLoadSynRequestCall;
import com.base.common.http.service.GetService_DownLoad;

import java.util.Map;


public class GetDownLoadRequest extends BaseRequest<GetService_DownLoad,DownLoadRequestCall,DownLoadSynRequestCall> {


    protected String path;

    protected String fileName;




    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }


    public GetDownLoadRequest(String method, Class<GetService_DownLoad> service, String path, String fileName, Map<String, String> headers, String baseUrl) {
        super(method, service, headers, baseUrl);
        this.path=path;
        this.fileName=fileName;
    }



    @Override
    public DownLoadRequestCall build() {
        GetService_DownLoad serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new DownLoadRequestCall(serviceIml.download(baseUrl),this);
        }else {
            return new DownLoadRequestCall(serviceIml.download(baseUrl,headers),this);
        }
    }

    @Override
    public DownLoadSynRequestCall synBuild() {
        GetService_DownLoad serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new DownLoadSynRequestCall(serviceIml.synDownload(baseUrl),this);
        }else {
            return new DownLoadSynRequestCall(serviceIml.synDownload(baseUrl,headers),this);
        }
    }
}
