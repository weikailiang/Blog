package com.base.blog.m;

import android.content.Context;

import com.base.blog.R;
import com.base.blog.c.MainContract;

import java.util.ArrayList;
import java.util.List;

import com.base.bolg_model.entity.MenuHomeLeftEntity;

/**
 * Created by weikailiang on 2020/5/11.
 */

public class MainModel implements MainContract.Model {

    @Override
    public List<MenuHomeLeftEntity> getMenuHomeLeftDatas(Context context) {
        List<MenuHomeLeftEntity> list = new ArrayList<>();
        MenuHomeLeftEntity menuHomeLeftEntity = null;
        menuHomeLeftEntity = new MenuHomeLeftEntity();
        menuHomeLeftEntity.setmClassName("/mine/setting");
        menuHomeLeftEntity.setmIcon("ic_setting");
        menuHomeLeftEntity.setmName(context.getString(R.string.home_setting));
        list.add(menuHomeLeftEntity);

        menuHomeLeftEntity = new MenuHomeLeftEntity();
        menuHomeLeftEntity.setmClassName("/mine/setting");
        menuHomeLeftEntity.setmIcon("ic_setting");
        menuHomeLeftEntity.setmName(context.getString(R.string.home_setting));
        list.add(menuHomeLeftEntity);

        menuHomeLeftEntity = new MenuHomeLeftEntity();
        menuHomeLeftEntity.setmClassName("/mine/setting");
        menuHomeLeftEntity.setmIcon("ic_setting");
        menuHomeLeftEntity.setmName(context.getString(R.string.home_setting));
        list.add(menuHomeLeftEntity);

        menuHomeLeftEntity = new MenuHomeLeftEntity();
        menuHomeLeftEntity.setmClassName("/mine/setting");
        menuHomeLeftEntity.setmIcon("ic_setting");
        menuHomeLeftEntity.setmName(context.getString(R.string.home_setting));
        list.add(menuHomeLeftEntity);


        menuHomeLeftEntity = new MenuHomeLeftEntity();
        menuHomeLeftEntity.setmClassName("/chat/center");
        menuHomeLeftEntity.setmIcon("ic_setting");
        menuHomeLeftEntity.setmName(context.getString(R.string.home_center));
        list.add(menuHomeLeftEntity);

        return list;
    }
}
