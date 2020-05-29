package com.base.mine;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.common.act.BaseActivity;
import com.base.common.sp.SpUtil;
import com.base.mine.c.LoginContract;
import com.base.mine.databinding.ActLoginBinding;
import com.base.mine.m.LoginModel;
import com.base.mine.p.LoginPresenter;

import androidx.core.content.ContextCompat;

/**
 * Created by weikailiang on 2020/5/6.
 */
@Route(path = "/mine/login")
public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginContract.View{
    private ActLoginBinding mBinding;
    @Override
    protected int getLayoutResource() {
        return R.layout.act_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initTitleBar();
        setBarTitle("登录");
        mBinding = (ActLoginBinding) mRootBinding;

        mBinding.toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/mine/register").navigation();
            }
        });
        mBinding.account.setText(SpUtil.getAccount());
        mBinding.pwd.setText(SpUtil.getPwd());
        mBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login(mBinding.account.getText().toString(),mBinding.pwd.getText().toString());
            }
        });


    }

    @Override
    public void showLoading() {
        ivLoading.setIndicatorColor(ContextCompat.getColor(mContext, com.base.common.R.color.color_white));
        super.showLoading();
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
    }
}
