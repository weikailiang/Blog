package com.base.wedget.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.base.wedget.databinding.ItemSelectImgBinding;
import com.base.wedget.imgpic.adapter.BaseViewHolder;

/**
 * Created by weikailiang on 2020/5/25.
 */

public class SelectImgViewHolder extends BaseViewHolder{

    public ItemSelectImgBinding mBinding;
    public SelectImgViewHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }
}
