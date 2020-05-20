package com.base.common.http.call;

import com.base.common.http.ResponseState;
import com.base.common.http.back.CallBack;
import com.base.common.http.back.ParameterizedTypeImpl;
import com.base.common.http.response.BaseResponse;
import com.base.common.http.response.NetWorkInitial;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class BaseRequestCall extends RequestCall{
    protected Flowable<Response<String>> flowable;


    public BaseRequestCall(){}
    public BaseRequestCall(Flowable<Response<String>> flowable) {
        this.flowable = flowable;
    }

    public <T> Disposable enqueue(final Class<T> clazz , final CallBack callBack) {

        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(1)
                .subscribe(new Consumer<Response<String>>() {
                    @Override
                    public void accept(Response<String> response) throws Exception {
                        ParameterizedTypeImpl type = new ParameterizedTypeImpl(BaseResponse.class, new Class[]{clazz});
                        String body = NetWorkInitial.customDecode(response);
                        Gson gson = new Gson();
                        BaseResponse<T> mBaseResponse = gson.fromJson(body, type);
                        if (mBaseResponse.getCode() == ResponseState.SUCCESS_CODE) {
                            callBack.onResponse(mBaseResponse.getData(),mBaseResponse.getMsg());
                        } else{
                            callBack.onError(mBaseResponse.getCode(),mBaseResponse.getMsg(),mBaseResponse.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(-1,throwable.toString(),null);
                    }
                });


    }

    public <T> Disposable enqueueForArray(final Class<T> clazz, final CallBack<List<T>> callBack) {

        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(1)
                .subscribe(new Consumer<Response<String>>() {
                    @Override
                    public void accept(Response<String> response) throws Exception {
                        String body = NetWorkInitial.customDecode(response);
                        ParameterizedTypeImpl type_list = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
                        ParameterizedTypeImpl type = new ParameterizedTypeImpl(BaseResponse.class, new Type[]{type_list});
                        Gson gson = new Gson();
                        BaseResponse<List<T>> mBaseResponse = gson.fromJson(body, type);
                        if (mBaseResponse.getCode() == ResponseState.SUCCESS_CODE){
                            callBack.onResponse(mBaseResponse.getData(),mBaseResponse.getMsg());
                        }else {
                            callBack.onError(mBaseResponse.getCode(),mBaseResponse.getMsg(),mBaseResponse.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(-1,throwable.toString(),null);
                    }
                });
    }
}
