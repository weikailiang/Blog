package com.base.common.http.call;
import com.base.comm_model.entity.FileBean;
import com.base.common.http.RetrofitInitial;
import com.base.common.http.request.PostPicsRequest;
import com.base.common.http.response.BaseResponse;
import com.base.common.http.response.NetWorkInitial;
import com.base.common.http.service.PostService_Multi;
import com.base.common.utils.ScaleUtil;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class PicsSynRequestCall extends BaseSynRequestCall {
    protected PostPicsRequest postPicsRequest;

    public PicsSynRequestCall(PostPicsRequest postPicsRequest) {
        this.postPicsRequest = postPicsRequest;
    }

    public BaseResponse executeForUpFiles() throws Exception {
        final List<File> temFiles = new ArrayList<>();
        final Gson gson = new Gson();
        PostService_Multi service = (PostService_Multi) RetrofitInitial.getInstance().create(postPicsRequest.getService());
        Call<String> call = null;


        HashMap<String, RequestBody> bodyMap = new HashMap<>();
        if (postPicsRequest.getFilePaths() != null && !postPicsRequest.getFilePaths().isEmpty()) {

            for (FileBean item : postPicsRequest.getFilePaths()) {
                File file = new File(item.getValue());
                File tempFile = ScaleUtil.ScaleImage(file, postPicsRequest.getTempPath(), postPicsRequest.getMark());
                if (tempFile == null) {
                    throw new Exception("file scale error");
                }
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                bodyMap.put(item.getKey() + "\"; filename=\"" + file.getName(), requestBody);

                temFiles.add(tempFile);
            }


        }
        Iterator<Map.Entry<String, Object>> iterator = postPicsRequest.getParams().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), gson.toJson(next.getValue()));
            bodyMap.put(next.getKey(), requestBody);
        }

        if (postPicsRequest.getHeaders() == null || postPicsRequest.getHeaders().isEmpty()) {
            call = service.synUpMulti(postPicsRequest.getBaseUrl() + postPicsRequest.getMethod(), bodyMap);

        } else {
            call = service.synUpMulti(postPicsRequest.getBaseUrl() + postPicsRequest.getMethod(), bodyMap, postPicsRequest.getHeaders());
        }

        Response<String> execute = call.execute();
        if (temFiles.size() > 0) {
            for (File item : temFiles) {
                if (item.exists()) {
                    item.delete();
                }
            }
        }

        String body = NetWorkInitial.customDecode(execute);
        return gson.fromJson(body, BaseResponse.class);

    }
}
