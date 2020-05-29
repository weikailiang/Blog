package com.base.wedget.adapter;

import android.view.View;

import com.base.wedget.databinding.ItemSelectImgBinding;
import com.base.wedget.imgpic.adapter.BaseViewHolder;

import androidx.databinding.DataBindingUtil;

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
