package com.base.mine;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.common.act.BaseActivity;
import com.base.mine.databinding.ActSettingBinding;

/**
 * Created by weikailiang on 2020/5/11.
 */
@Route(path = "/mine/setting")
public class SettingActivity extends BaseActivity{
    private ActSettingBinding mBinding;
    @Override
    protected int getLayoutResource() {
        return R.layout.act_setting;
    }

    @Override
    protected void initEventAndData() {
        mBinding = (ActSettingBinding) mRootBinding;
        initTitleBar();
        setBackNavigation();
        setBarTitle("设置");
    }

    @Override
    protected void initPresenter() {

    }
}
