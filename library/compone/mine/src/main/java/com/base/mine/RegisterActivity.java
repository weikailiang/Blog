package com.base.mine;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.comm_model.entity.RequestRegisterEntity;
import com.base.common.act.BaseActivity;
import com.base.mine.c.RegisterContract;
import com.base.mine.databinding.ActRegisterBinding;
import com.base.mine.m.RegisterModel;
import com.base.mine.p.RegisterPresenter;

/**
 * 用户注册
 * Created by weikailiang on 2020/5/9.
 */
@Route(path = "/mine/register")
public class RegisterActivity extends BaseActivity<RegisterPresenter,RegisterModel> implements RegisterContract.View{
    private ActRegisterBinding mBinding;
    @Override
    protected int getLayoutResource() {
        return R.layout.act_register;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        super.initTitleBar();
        setBarTitle("注册");
        setBackNavigation();
        mBinding = (ActRegisterBinding) mRootBinding;
        mBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestRegisterEntity requestRegisterEntity = new RequestRegisterEntity();
                requestRegisterEntity.setPassWord(mBinding.pwd.getText().toString());
                requestRegisterEntity.setPhone(mBinding.account.getText().toString());
                mPresenter.register(requestRegisterEntity);
            }
        });
    }



    @Override
    public void returnRegisterSuccess() {

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
