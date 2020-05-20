package com.base.common.http.back;

/**
 * Date：2019/3/25 下午1:30
 * Creator：arvin
 * Des：
 */

public interface CallBackFroPark<T> {
    void onResponse(T t)throws Exception;
    void onError(int code, String msg, String errMsg);
    void onNext(T t);
}
