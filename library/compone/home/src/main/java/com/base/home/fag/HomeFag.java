package com.base.home.fag;

import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;

import com.base.comm_model.event.MainTitleEvent;
import com.base.common.act.BaseFragment;
import com.base.common.rxbus.RxBus2;
import com.base.home.R;
import com.base.home.c.HomeFagContract;
import com.base.home.databinding.FagHomeBinding;
import com.base.home.m.HomeFagModel;
import com.base.home.p.HomeFagPresenter;

/**
 * Created by weikailiang on 2020/5/11.
 */

public class HomeFag extends BaseFragment<HomeFagPresenter,HomeFagModel> implements HomeFagContract.View {
    private FagHomeBinding mBinding;
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


    @Override
    protected int getLayoutResource() {
        return R.layout.fag_home;
    }

    @Override
    protected void init() {
        mBinding = (FagHomeBinding) mRootBinding;
        RxBus2.getInstance().post(new MainTitleEvent(mContext.getString(R.string.home_title)));
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            RxBus2.getInstance().post(new MainTitleEvent(mContext.getString(R.string.home_title)));
        }
    }





    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        showLoading();
    }

}
