package com.base.common.act.base;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.base.comm_model.*;
import com.base.comm_model.entity.User;
import com.base.common.db.DBDataHelper;
import com.base.common.db.DbField;
import com.base.common.sp.SpUtil;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by weikailiang on 2020/5/6.
 */

public abstract class BasePresenter<T,E> {
    protected CompositeDisposable mCompositeDisposable=new CompositeDisposable();
    public Context mContext;
    public Activity mActivity;
    public E mModel;
    public T mView;


    public User mUser;
    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
        //数据库查询当前用户
        try {
            ArrayList<com.base.comm_model.BaseModel> list = DBDataHelper.getInstance().select(DbField.USER_TABLE_NAME,DbField.phone+"=?",new String[]{SpUtil.getAccount()},"",User.class);
            mUser = (User) list.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void onDestroy() {
        mCompositeDisposable.dispose();
    }
}
