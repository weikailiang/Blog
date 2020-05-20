package com.base.mine.c;

import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;

/**
 * Created by weikailiang on 2020/5/6.
 */

public interface LoginContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseView {
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        //登录接口
        public abstract void login(String account,String pwd);
    }
}
