package com.base.wedget;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.common.act.BaseActivity;

/**
 * Created by weikailiang on 2020/5/21.
 */
@Route(path = "/wedget/selectimg")
public class SelectImgActivity extends BaseActivity{
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_select_img;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initTitleBar();
        setBackNavigation();
        setBarTitle("选择图片");
    }

    @Override
    protected void initPresenter() {

    }
}
