package com.base.chat.act;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.chat.R;
import com.base.chat.c.CenterContract;
import com.base.chat.databinding.ActCenterBinding;
import com.base.chat.m.CenterModel;
import com.base.chat.p.CenterPresenter;
import com.base.common.act.BaseActivity;

/**
 * 个人中心
 * Created by weikailiang on 2020/5/21.
 */
@Route(path = "/chat/center")
public class CenterActivity extends BaseActivity<CenterPresenter,CenterModel> implements CenterContract.View{
    private ActCenterBinding mBinding;
    @Override
    protected int getLayoutResource() {
        return R.layout.act_center;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mBinding = (ActCenterBinding) mRootBinding;
        initTitleBar();
        setBackNavigation();
        setBarTitle("个人中心");

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
