package com.base.common.http.call;
import com.base.common.http.back.CallBack;
import com.base.common.http.request.GetDownLoadRequest;

import org.reactivestreams.Publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Date：2019/3/30 下午3:45
 * Creator：arvin
 * Des：
 */

public class DownLoadRequestCall extends BaseRequestCall {

    protected Flowable<Response<ResponseBody>> flowable;
    protected GetDownLoadRequest getDownLoadRequest;

    public DownLoadRequestCall(Flowable<Response<ResponseBody>> flowable, GetDownLoadRequest getDownLoadRequest) {
        this.flowable = flowable;
        this.getDownLoadRequest = getDownLoadRequest;
    }

    public Disposable enqueueForDownLoad(final CallBack<Integer> callBack) {
        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<Response<ResponseBody>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(final Response<ResponseBody> response) throws Exception {
                        if (response.body() == null) {
                            throw new Exception("url 所指向的内容为空!");
                        } else {
                            return Flowable.create(new FlowableOnSubscribe<Integer>() {
                                @Override
                                public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                                    InputStream is = null;
                                    FileOutputStream fos = null;
                                    long total = response.body().contentLength();
                                    File file = new File(getDownLoadRequest.getPath());
                                    if (!file.exists()) {
                                        file.mkdirs();
                                    }
                                    int lenth = 0;
                                    long readed = 0;
                                    byte[] bytes = new byte[8 * 1024];
                                    is = response.body().byteStream();
                                    fos = new FileOutputStream(new File(getDownLoadRequest.getPath() + File.separator + getDownLoadRequest.getFileName()));

                                    while ((lenth = is.read(bytes)) != -1) {
                                        fos.write(bytes, 0, lenth);
                                        readed = readed + lenth;
                                        float f = (float) readed / (float) total;
                                        emitter.onNext((int) (f * 100));
                                        if (total == readed) {
                                            emitter.onComplete();
                                            break;
                                        }
                                    }
                                    fos.close();
                                    is.close();


                                }
                            }, BackpressureStrategy.LATEST);

                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        callBack.onResponse(integer,"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(-1,null,null);
                    }
                });
    }
}
