package com.base.common.act;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.base.common.R;
import com.base.common.act.base.BaseModel;
import com.base.common.act.base.BasePresenter;
import com.base.common.loding.LoadingIndicatorView;
import com.base.common.utils.TUtil;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Created by weikailiang on 2020/5/11.
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends SimpleFragment{

    //是否可见
    protected Boolean isFragmentVisible = false;
    //activity是否初始化完成
    protected boolean isActivityPrepared = false;
    //是否是第一次加载
    protected boolean isDataFirstInit = true;



    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;


    private LoadingIndicatorView ivLoading;
    private View viewLoading;

    private ViewGroup viewMain;
    private ViewGroup mParent;

    private int currentState = STATE_MAIN;

    public T mPresenter;
    public E mModel;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel=TUtil.getT(this,1);

        if(mPresenter!=null){
            mPresenter.mContext = mContext;
            mPresenter.mActivity = mActivity;
        }
        this.initPresenter();
        init();


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityPrepared = true;
        loadData();
        loadDataForPager();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {//当可见的时候执行操作
            isFragmentVisible = true;
            loadDataForPager();
        } else {//不可见时执行相应的操作
            isFragmentVisible = false;
            onInvisible();
        }
    }


    protected abstract void initPresenter();


    @Override
    protected void initEventAndData() {
        viewMain = (ViewGroup) mRootBinding.getRoot().findViewById(R.id.contrain);
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


    /*******内嵌加载*******/
    protected void showLoading(){
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
        ivLoading.show();
    }
    protected void stateMain() {
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
    protected void loadDataForPager() {
        //当fragment可见、activity初始化完成、第一次加载时执行
        if (isActivityPrepared && isFragmentVisible && isDataFirstInit) {
            isDataFirstInit = false;
            lazyLoadForPager();
        }
    }

    protected void loadData(){
        if (isActivityPrepared && isDataFirstInit) {
            isDataFirstInit = false;
            lazyLoad();
        }
    }
    protected void lazyLoad(){
        initEventAndData();
    }


    protected void onInvisible() {
    }

    protected void lazyLoadForPager(){
        initEventAndData();
    }//子类实现

}
