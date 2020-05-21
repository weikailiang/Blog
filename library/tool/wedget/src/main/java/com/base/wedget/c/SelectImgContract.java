package com.base.wedget.c;

import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;

import java.io.FilenameFilter;

/**
 * Created by weikailiang on 2020/5/21.
 */

public interface SelectImgContract {
    interface Model extends BaseModel {
        FilenameFilter getFileterImage();
    }
    interface View extends BaseView {

    }
    abstract class Presenter extends BasePresenter<View,Model> {
        protected abstract void checkLocalImg();
    }
}
