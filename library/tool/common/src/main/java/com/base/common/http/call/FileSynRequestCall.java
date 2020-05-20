package com.base.common.http.call;

import com.base.common.http.response.BaseResponse;
import com.base.common.http.response.NetWorkInitial;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Date：2019/3/29 下午5:30
 * Creator：arvin
 * Des：
 */

public class FileSynRequestCall extends BaseSynRequestCall {

    protected Call<String> call;

    public FileSynRequestCall(Call<String> call) {
        this.call = call;
    }

    public BaseResponse executeForUpFiles() throws Exception {
        Response<String> execute = call.execute();
        String body = NetWorkInitial.customDecode(execute);
        return new Gson().fromJson(body,BaseResponse.class);
    }
}
