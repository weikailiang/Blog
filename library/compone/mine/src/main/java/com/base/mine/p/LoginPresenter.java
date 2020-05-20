package com.base.mine.p;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.comm_model.BaseModel;
import com.base.comm_model.entity.User;
import com.base.common.db.DBDataHelper;
import com.base.common.db.DbField;
import com.base.common.http.HttpUtils;
import com.base.common.http.back.CallBack;
import com.base.common.sp.SpUtil;
import com.base.common.utils.ToastUtil;
import com.base.mine.c.LoginContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class LoginPresenter extends LoginContract.Presenter{


    @Override
    public void onStart() {

    }

    @Override
    public void login(String account, final String pwd) {
        if (TextUtils.isEmpty(account)){
            ToastUtil.show("请输入帐号");
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            ToastUtil.show("请输入密码");
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("loginName",account);
        map.put("password",pwd);
        map.put("platform",1);
        mView.showLoading();
        Disposable enqueue = HttpUtils.postContent().method("api-auth/auth/login").content(map)
                .build().enqueue(User.class, new CallBack<User>() {
                    @Override
                    public void onResponse(User user,String msg) throws Exception {
                        mView.stopLoading();
                        SpUtil.setToken(user.getToken());
                        SpUtil.setAccount(user.getPhone());
                        SpUtil.setPwd(pwd);
                        ArrayList<BaseModel> list = DBDataHelper.getInstance().select(DbField.USER_TABLE_NAME,DbField.userId+"=?",new String[]{user.getUserId()},"",User.class);
                        if (list.size()>1){
                            DBDataHelper.getInstance().delete(DbField.USER_TABLE_NAME,DbField.userId+"=?",new String[]{user.getUserId()});
                            DBDataHelper.getInstance().insert(DbField.USER_TABLE_NAME, user);
                        }else {
                            if (list.size()>0){
                                DBDataHelper.getInstance().update(DbField.USER_TABLE_NAME,DbField.userId+"= ?",new String[]{user.getUserId()},user);
                            }else {
                                DBDataHelper.getInstance().insert(DbField.USER_TABLE_NAME, user);
                            }
                        }
                        ARouter.getInstance().build("/blog/main").navigation(mContext, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                mActivity.finish();
                            }
                        });


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
