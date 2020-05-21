package com.base.wedget.imgpic.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.base.wedget.databinding.ItemImgPicBinding;

/**
 * Created by weikailiang on 2020/5/21.
 */

public class PicImgViewHolder extends BaseViewHolder{


    public ItemImgPicBinding mBinding;
    public PicImgViewHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }
}
