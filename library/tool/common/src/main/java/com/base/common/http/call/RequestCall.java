package com.base.common.http.call;

import android.util.Log;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class RequestCall {
    //用于处理特殊异常
    static {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i("http", "setErrorHandler(undeliverable):" + throwable.toString());
            }
        });
    }
}
