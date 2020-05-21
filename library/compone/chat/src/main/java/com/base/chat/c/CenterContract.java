package com.base.chat.c;

import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;

/**
 * Created by weikailiang on 2020/5/21.
 */

public interface CenterContract {
    interface Model extends BaseModel {

    }
    interface View extends BaseView {

    }
    abstract class Presenter extends BasePresenter<View,Model> {

    }
}
