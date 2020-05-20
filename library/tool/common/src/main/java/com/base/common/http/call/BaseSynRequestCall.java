package com.base.common.http.call;

import com.base.common.http.ResponseState;
import com.base.common.http.back.ParameterizedTypeImpl;
import com.base.common.http.response.BaseResponse;
import com.base.common.http.response.NetWorkInitial;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class BaseSynRequestCall {
    protected Call<String> call;

    public BaseSynRequestCall(){}
    public BaseSynRequestCall(Call<String> call) {
        this.call = call;
    }

    public <T> T execute(Class<T> clazz) throws Exception {
        Response<String> execute = call.execute();
        String body = NetWorkInitial.customDecode(execute);
        ParameterizedTypeImpl type = new ParameterizedTypeImpl(BaseResponse.class, new Class[]{clazz});
        Gson gson = new Gson();
        BaseResponse<T> responseT = gson.fromJson(body, type);
        if (responseT.getCode()== ResponseState.SUCCESS_CODE) {
            return responseT.getData();
        } else {
            throw new Exception(responseT.getMsg());
        }

    }

    public <T> List<T> executeForArray(Class<List<T>> clazz) throws Exception {
        Response<String> execute = call.execute();
        String body = NetWorkInitial.customDecode(execute);
        ParameterizedTypeImpl type_list = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        ParameterizedTypeImpl type = new ParameterizedTypeImpl(BaseResponse.class, new Type[]{type_list});
        Gson gson = new Gson();
        BaseResponse<List<T>> responseT = gson.fromJson(body, type);
        if (responseT.getCode()== ResponseState.SUCCESS_CODE) {
            return responseT.getData();
        } else {
            throw new Exception(responseT.getMsg());
        }

    }
}
