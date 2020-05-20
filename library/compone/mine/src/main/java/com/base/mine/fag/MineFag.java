package com.base.mine.fag;

import com.base.comm_model.event.MainTitleEvent;
import com.base.common.act.BaseFragment;
import com.base.common.rxbus.RxBus2;
import com.base.mine.R;
import com.base.mine.c.MineContract;
import com.base.mine.databinding.FagMineBinding;
import com.base.mine.m.MineModel;
import com.base.mine.p.MinePresenter;

/**
 * Created by weikailiang on 2020/5/11.
 */

public class MineFag extends BaseFragment<MinePresenter,MineModel> implements MineContract.View{

    private FagMineBinding mBinding;
    @Override
    protected void initPresenter() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fag_mine;
    }

    @Override
    protected void init() {
        mBinding = (FagMineBinding) mRootBinding;
        RxBus2.getInstance().post(new MainTitleEvent(mContext.getString(R.string.mine_title)));
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            RxBus2.getInstance().post(new MainTitleEvent(mContext.getString(R.string.mine_title)));
        }
    }

    @Override
    protected void lazyLoad() {

    }
}
