package com.base.common.http.call;

import com.base.comm_model.entity.FileBean;
import com.base.common.http.ResponseState;
import com.base.common.http.RetrofitInitial;
import com.base.common.http.back.CallBackFroPark;
import com.base.common.http.request.PostPicsRequest;
import com.base.common.http.response.BaseResponse;
import com.base.common.http.response.NetWorkInitial;
import com.base.common.http.service.PostService_Multi;
import com.base.common.utils.ScaleUtil;
import com.google.gson.Gson;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;


public class PicsRequestCall extends BaseRequestCall {

    protected PostPicsRequest postPicsRequest;

    public PicsRequestCall(PostPicsRequest postPicsRequest) {
        this.postPicsRequest = postPicsRequest;
    }

    public Disposable enqueueForUpFiles(final CallBackFroPark<BaseResponse> callBack) {
        final List<File> temFiles = new ArrayList<>();
        final Gson gson = new Gson();
        return Flowable.just(postPicsRequest.getFilePaths())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<FileBean>, Publisher<Response<String>>>() {
                    @Override
                    public Publisher<Response<String>> apply(List<FileBean> fileBeans) throws Exception {
                        HashMap<String, RequestBody> bodyMap = new HashMap<>();
                        if (fileBeans != null && !fileBeans.isEmpty()) {
                            for(FileBean item:fileBeans)
                            {
                                File file = new File(item.getValue());
                                if (postPicsRequest.getTempPath() != null) {
                                    File tempFile = ScaleUtil.ScaleImage(file, postPicsRequest.getTempPath(), postPicsRequest.getMark());
                                    if (tempFile == null) {
                                        throw new Exception("file scale error");
                                    }
                                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                                    bodyMap.put(item.getKey() + "\"; filename=\"" + file.getName(), requestBody);

                                    temFiles.add(tempFile);
                                }else {
                                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                    bodyMap.put(item.getKey() + "\"; filename=\"" + file.getName(), requestBody);
                                    temFiles.add(file);
                                }
                            }
                        }
                        Iterator<Map.Entry<String, Object>> iterator = postPicsRequest.getParams().entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, Object> next = iterator.next();
                            RequestBody requestBody = null;
                            if (next.getValue() instanceof String){
                                requestBody  = RequestBody.create(MediaType.parse("multipart/form-data"), next.getValue().toString());
                            }else {
                                requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), gson.toJson(next.getValue()));
                            }
                            bodyMap.put(next.getKey(), requestBody);
                        }
                        PostService_Multi service = (PostService_Multi) RetrofitInitial.getInstance().create(postPicsRequest.getService());
                        if (postPicsRequest.getHeaders() == null || postPicsRequest.getHeaders().isEmpty()) {
                            return service.upMulti(postPicsRequest.getBaseUrl()+postPicsRequest.getMethod(), bodyMap);
                        } else {
                            return service.upMulti(postPicsRequest.getBaseUrl()+postPicsRequest.getMethod(), bodyMap, postPicsRequest.getHeaders());
                        }

                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Response<String>, Publisher<BaseResponse>>() {
                    @Override
                    public Publisher<BaseResponse> apply(Response<String> stringResponse) throws Exception {

                        if (postPicsRequest.getTempPath() !=null) {
                            if (temFiles.size() > 0) {
                                for (File item : temFiles) {
                                    if (item.exists()) {
                                        item.delete();
                                    }
                                }
                            }
                        }
                        String body = NetWorkInitial.customDecode(stringResponse);
                        final BaseResponse responseT = gson.fromJson(body, BaseResponse.class);
                        return new Publisher<BaseResponse>() {
                            @Override
                            public void subscribe(Subscriber<? super BaseResponse> s) {
                                s.onNext(responseT);
                            }
                        };

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse responseT) throws Exception {
                        if(responseT.getCode()== ResponseState.SUCCESS_CODE)
                        {
                            callBack.onResponse(responseT);
                        }else {
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
