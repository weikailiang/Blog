package com.base.common.act;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.base.common.BaseApp;
import com.base.common.R;
import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.daynightmodeutils.ChangeModeController;
import com.base.common.loding.LoadingIndicatorView;
import com.base.common.utils.ScreenUtil;
import com.base.common.utils.TUtil;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by weikailiang on 2020/5/6.
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends SimpleActivity {
    protected Context mContext;
    protected Activity mActivity;
    protected ViewDataBinding mRootBinding;

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;


    protected LoadingIndicatorView ivLoading;
    private View viewLoading;

    private ViewGroup viewMain;
    private ViewGroup mParent;

    private int currentState = STATE_MAIN;

    public T mPresenter;
    public E mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApp.addActivity(this);
        ScreenUtil.adaptScreen4VerticalSlide(this, 360);
        ScreenUtil.setLightStatusBar(this, false);
        ARouter.getInstance().inject(this);
        setOnOrientation();
        initTheme();
        mContext = this;
        mActivity = this;
        mPresenter = TUtil.getT(this, 0);
        mModel=TUtil.getT(this,1);

        if(mPresenter!=null){
            mPresenter.mContext = this;
            mPresenter.mActivity = this;
        }
        try {
            mRootBinding = DataBindingUtil.setContentView(this, getLayoutResource());
        }catch (Exception e)
        {

        }
        this.initPresenter();
        initEventAndData();
    }

    @Override
    protected void initEventAndData() {
        viewMain = (ViewGroup) findViewById(R.id.contrain);
        if (viewMain == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'contrain'.");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup.");
        }
        mParent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_progress, mParent);
        viewLoading = mParent.findViewById(R.id.view_loading);
        ivLoading = viewLoading.findViewById(R.id.avi);
        ivLoading.setIndicatorColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }


    public void setOnOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    protected abstract int getLayoutResource();
    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    protected abstract void initPresenter();


    /*******内嵌加载*******/
    protected void showLoading(){
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
        ivLoading.show();
    }
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }
    protected void stopLoading(){
        if (ivLoading!=null&&ivLoading.isShown()){
            ivLoading.hide();
        }
        stateMain();
    }
    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                ivLoading.show();
                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
//                if (viewError != null) {
//                    viewError.setVisibility(View.GONE);
//                }
                break;
        }
    }




    /**
     * 设置主题
     */
    private void initTheme() {
        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApp.removeActivity(this);
        if (mRootBinding!=null)
            mRootBinding.unbind();
    }
}
