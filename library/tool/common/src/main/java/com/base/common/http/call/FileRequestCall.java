package com.base.common.http.call;

import com.base.common.http.ResponseState;
import com.base.common.http.back.CallBackFroPark;
import com.base.common.http.response.BaseResponse;
import com.base.common.http.response.NetWorkInitial;
import com.google.gson.Gson;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
public class FileRequestCall extends BaseRequestCall {

    protected Flowable<Response<String>> flowable;

    public FileRequestCall(Flowable<Response<String>> flowable) {
        this.flowable = flowable;
    }

    public Disposable enqueueForUpFiles(final CallBackFroPark<BaseResponse> callBack) {
        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<String>>() {
                    @Override
                    public void accept(Response<String> response) throws Exception {
                        String body = NetWorkInitial.customDecode(response);
                        BaseResponse responseT = new Gson().fromJson(body, BaseResponse.class);
                        if(responseT.getCode()== ResponseState.SUCCESS_CODE)
                        {
                            callBack.onResponse(responseT);
                        }else
                        {
                            callBack.onError(responseT.getCode(),responseT.getMsg(),responseT.getMsg());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(-1,null,null);
                    }
                });
    }
}
