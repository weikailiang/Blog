package com.base.common.act;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.common.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by weikailiang on 2020/5/11.
 */

public abstract class SimpleFragment extends SupportFragment{
    protected Activity mActivity;
    protected Context mContext;
    private ImageView mImg;
    private Toolbar mToolbar;
    private TextView mTitle;


    protected ViewDataBinding mRootBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        mActivity = getActivity();
        mRootBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), null, false);
        return mRootBinding.getRoot();
    }

    protected abstract int getLayoutResource();
    protected abstract void initEventAndData();
    protected abstract void init();

    protected void initTitleBar(){
        mTitle = mRootBinding.getRoot().findViewById(R.id.title);
        mToolbar = mRootBinding.getRoot().findViewById(R.id.bar);
        mImg = mRootBinding.getRoot().findViewById(R.id.img_right);
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }
    protected void setBarTitle(String tittle)
    {
        mTitle.setText(tittle);

    }
    protected void setRigImg(int mResource, View.OnClickListener v){
        if (mImg != null){
            mImg.setBackgroundResource(mResource);
            mImg.setVisibility(View.VISIBLE);
            mImg.setOnClickListener(v);
        }
    }

    protected void setBackNavigation()
    {
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRootBinding!=null)
            mRootBinding.unbind();
    }
}
