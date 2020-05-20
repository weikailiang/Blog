package com.base.mine.p;
import com.base.comm_model.entity.RequestRegisterEntity;
import com.base.common.http.HttpUtils;
import com.base.common.http.back.CallBack;
import com.base.common.utils.ToastUtil;
import com.base.mine.c.RegisterContract;

import io.reactivex.disposables.Disposable;

/**
 * Created by weikailiang on 2020/5/9.
 */

public class RegisterPresenter extends RegisterContract.Presenter{
    @Override
    public void onStart() {

    }

    @Override
    public void register(RequestRegisterEntity user) {
        mView.showLoading();
        if (!mModel.voildUser(user)){
            mView.stopLoading();
            return;
        }
        Disposable enqueue = HttpUtils.postContent().method("api-auth/auth/register").content(user)
                .build().enqueue(String.class, new CallBack<String>() {
                    @Override
                    public void onResponse(String str,String msg) throws Exception {
                        mView.stopLoading();
                        ToastUtil.show(msg);
                        mActivity.finish();
                    }


                    @Override
                    public void onError(int code, String msg, String errMsg) {
                        mView.stopLoading();
                        ToastUtil.show(msg);
                    }

                });

        mCompositeDisposable.add(enqueue);
    }
}
