package com.base.blog.c;



import android.content.Context;

import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.act.base.BaseView;

import java.util.List;

import com.base.bolg_model.entity.MenuHomeLeftEntity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by weikailiang on 2020/5/11.
 */

public interface MainContract {
    interface Model extends BaseModel{
        List<MenuHomeLeftEntity> getMenuHomeLeftDatas(Context context);
    }
    interface View extends BaseView{
        void settMainToolBar(String title);
        RecyclerView geMaintRecyclerView();
        DrawerLayout geMaintDrawerLayout();
    }
    abstract class Prestener extends BasePresenter<View,Model>{
       public abstract void initLeftMenu();
    }
}
