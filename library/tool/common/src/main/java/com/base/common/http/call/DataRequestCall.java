package com.base.common.http.call;

import io.reactivex.Flowable;
import retrofit2.Response;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class DataRequestCall extends BaseRequestCall{

    public DataRequestCall(Flowable<Response<String>> flowable) {
        super(flowable);
    }
}
