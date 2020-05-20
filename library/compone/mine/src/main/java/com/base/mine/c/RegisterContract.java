package com.base.mine.c;

import com.base.comm_model.entity.RequestRegisterEntity;
import com.base.comm_model.entity.User;
import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;

/**
 * Created by weikailiang on 2020/5/9.
 */

public interface RegisterContract {
    interface Model extends BaseModel {
        //验证用户信息
        boolean voildUser(RequestRegisterEntity user);
    }

    interface View extends BaseView {
        //注册成功
        void returnRegisterSuccess();
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        //注册接口
        public abstract void register(RequestRegisterEntity user);
    }
}
