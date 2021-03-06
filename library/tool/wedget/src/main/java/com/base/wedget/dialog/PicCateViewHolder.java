package com.base.wedget.dialog;

import android.view.View;

import com.base.wedget.databinding.ItemCateViewBinding;
import com.base.wedget.imgpic.adapter.BaseViewHolder;

import androidx.databinding.DataBindingUtil;

/**
 * Created by weikailiang on 2020/5/25.
 */

public class PicCateViewHolder extends BaseViewHolder {

    ItemCateViewBinding mBinding;
    public PicCateViewHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }
}
