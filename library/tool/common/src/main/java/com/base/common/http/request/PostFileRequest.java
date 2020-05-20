package com.base.common.http.request;
import com.base.comm_model.entity.FileBean;
import com.base.common.http.RetrofitInitial;
import com.base.common.http.call.FileRequestCall;
import com.base.common.http.call.FileSynRequestCall;
import com.base.common.http.service.PostService_Multi;
import com.google.gson.Gson;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class PostFileRequest extends BaseRequest<PostService_Multi,FileRequestCall,FileSynRequestCall> {
    protected List<FileBean> filePaths;
    protected Map<String, Object> params;

    public PostFileRequest(String method, Class<PostService_Multi> service, List<FileBean> filePaths, Map<String, Object> params, Map<String, String> headers, String baseUrl) {
        super(method, service, headers, baseUrl);
        this.filePaths = filePaths;
        this.params = params;
    }



    protected Map<String, RequestBody> generalBody() {
        LinkedHashMap<String, RequestBody> maps = new LinkedHashMap<>();
        Gson gson = new Gson();
        if (filePaths != null && !filePaths.isEmpty()) {

            for(FileBean item:filePaths)
            {
                File file = new File(item.getValue());
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                maps.put(item.getKey()+"\"; filename=\""+file.getName(), requestBody);
            }
        }
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), gson.toJson(next.getValue()));
            maps.put(next.getKey(), requestBody);
        }
        return maps;
    }

    @Override
    public FileRequestCall build() {
        PostService_Multi serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new FileRequestCall(serviceIml.upMulti(baseUrl+method,generalBody()));
        }else
        {
            return new FileRequestCall(serviceIml.upMulti(baseUrl+method,generalBody(),headers));
        }
    }

    @Override
    public FileSynRequestCall synBuild() {
        PostService_Multi serviceIml = RetrofitInitial.getInstance().create(service);
        if(headers==null||headers.isEmpty())
        {
            return new FileSynRequestCall(serviceIml.synUpMulti(baseUrl+method,generalBody()));
        }else
        {
            return new FileSynRequestCall(serviceIml.synUpMulti(baseUrl+method,generalBody(),headers));
        }
    }
}
