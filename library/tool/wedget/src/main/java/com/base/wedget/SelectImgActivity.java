package com.base.wedget;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.common.act.BaseActivity;
import com.base.wedget.c.SelectImgContract;
import com.base.wedget.databinding.ActivitySelectImgBinding;
import com.base.wedget.entity.ImageLoadEntity;
import com.base.wedget.m.SelectImgModel;
import com.base.wedget.p.SelectImgPresenter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

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
        setBarTitle(getString(R.string.select_img));
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter.mHandler!=null){
            mPresenter.mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void showImgCate(final List<ImageLoadEntity> list) {
        ImageLoadEntity loadEntity = new ImageLoadEntity();
        int size = 0;
        for (ImageLoadEntity entity : list){
           size += entity.getCount();
        }
        loadEntity.setCount(size);
        loadEntity.setDir(getString(R.string.all_img));
        loadEntity.setName(getString(R.string.all_img));
        list.add(0,loadEntity);
        mBinding.tvBootmCate.setText(list.get(0).getName());
        mBinding.tvBootmCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showImageCate(list);
            }
        });
    }

    @Override
    public RecyclerView getRecycler() {
        return mBinding.recycler;
    }

    @Override
    public TextView getBottomTextView() {
        return mBinding.tvBootmCate;
    }


}
