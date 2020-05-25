package com.base.wedget.c;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;
import com.base.wedget.entity.ImageItem;
import com.base.wedget.entity.ImageLoadEntity;

import java.io.FilenameFilter;
import java.util.List;

/**
 * Created by weikailiang on 2020/5/21.
 */

public interface SelectImgContract {
    interface Model extends BaseModel {
        FilenameFilter getFileterImage();
    }
    interface View extends BaseView {
        void showImgCate(List<ImageLoadEntity> list);
        RecyclerView getRecycler();
        TextView getBottomTextView();

    }
    abstract class Presenter extends BasePresenter<View,Model> {
        protected abstract void checkLocalImg();
        protected abstract void showImageCate(List<ImageLoadEntity> list);

        public abstract void showImg(List<ImageItem> list);

        public abstract void checkLocalImgByPath(String path);

        public abstract void checkAllLocalImgByPaths();
    }
}
