package com.base.mine.c;

import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;

/**
 * Created by weikailiang on 2020/5/11.
 */

public interface MineContract {
    interface Model extends BaseModel{

    }
    interface View extends BaseView{

    }

    abstract class Presenter extends BasePresenter<Model,View>{

    }
}
