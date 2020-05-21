package com.base.wedget;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.common.act.BaseActivity;
import com.base.wedget.c.SelectImgContract;
import com.base.wedget.databinding.ActivitySelectImgBinding;
import com.base.wedget.m.SelectImgModel;
import com.base.wedget.p.SelectImgPresenter;

/**
 * Created by weikailiang on 2020/5/21.
 */
@Route(path = "/wedget/selectimg")
public class SelectImgActivity extends BaseActivity<SelectImgPresenter,SelectImgModel> implements SelectImgContract.View{
    private ActivitySelectImgBinding mBinding;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_select_img;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mBinding = (ActivitySelectImgBinding) mRootBinding;
        initTitleBar();
        setBackNavigation();
        setBarTitle("选择图片");
        mPresenter.checkLocalImg();

    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
    }
}
