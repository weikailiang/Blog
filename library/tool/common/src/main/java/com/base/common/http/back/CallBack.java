package com.base.common.http.back;

/**
 * Created by weikailiang on 2020/5/6.
 */

public interface CallBack<T> {
    void onResponse(T t,String msg)throws Exception;
    void onError(int code, String msg, String errMsg);
}
