package com.base.common.http.call;
import com.base.common.http.request.GetDownLoadRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class DownLoadSynRequestCall extends BaseSynRequestCall {
    protected Call<ResponseBody> call;
    protected GetDownLoadRequest getDownLoadRequest;

    public DownLoadSynRequestCall(Call<ResponseBody> call, GetDownLoadRequest getDownLoadRequest) {
        this.call = call;
        this.getDownLoadRequest = getDownLoadRequest;
    }

    public boolean executeForDownLoad() throws Exception {
        Response<ResponseBody> execute = call.execute();
        ResponseBody body = execute.body();
        if (body == null) {
            throw new Exception("url is empty");
        }

        InputStream is = null;
        FileOutputStream fos = null;
        long total = body.contentLength();
        File file = new File(getDownLoadRequest.getPath());
        if (!file.exists()) {
            file.mkdirs();
        }
        int lenth = 0;
        long readed = 0;
        byte[] bytes = new byte[8 * 1024];
        is = body.byteStream();
        fos = new FileOutputStream(new File(getDownLoadRequest.getPath() + File.separator + getDownLoadRequest.getFileName()));

        while ((lenth = is.read(bytes)) != -1) {
            fos.write(bytes, 0, lenth);
            readed = readed + lenth;
            if (total == readed) {
                fos.close();
                is.close();
                return true;
            }
        }

        return false;


    }
}
