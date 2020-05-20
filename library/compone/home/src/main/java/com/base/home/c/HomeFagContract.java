package com.base.home.c;

import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;

/**
 * Created by weikailiang on 2020/5/11.
 */

public interface HomeFagContract {
    interface Model extends BaseModel {

    }
    interface View extends BaseView{

    }
    abstract static class Presenter extends BasePresenter<View, Model> {

    }
}
