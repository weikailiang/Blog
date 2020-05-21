package com.base.wedget.imgpic;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.base.wedget.R;
import com.base.wedget.databinding.ItemImgPicViewBinding;
import com.base.wedget.entity.ImageItem;
import com.base.wedget.imgpic.adapter.PicImgAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weikailiang on 2020/5/19.
 */

public class ImagePicView extends FrameLayout{
    private ItemImgPicViewBinding mBinding;
    private PicImgAdapter mAdapter;
    public ImagePicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ImagePicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet atrr){
        TypedArray typedArray = context.obtainStyledAttributes(atrr,R.styleable.imgPic);
        boolean isEdit = typedArray.getBoolean(R.styleable.imgPic_isEdit,true);
        int numLine = typedArray.getInteger(R.styleable.imgPic_numline,4);
        typedArray.recycle();
        mBinding = DataBindingUtil.bind(LayoutInflater.from(context).inflate(R.layout.item_img_pic_view,null));
        addView(mBinding.getRoot());
        if (mAdapter == null) {
            mAdapter = new PicImgAdapter(context);
            GridLayoutManager manager = new GridLayoutManager(context, numLine);
            mBinding.picRecy.setLayoutManager(manager);
            mAdapter.setDefaultData(isEdit);
            mBinding.picRecy.setAdapter(mAdapter);
        }
    }


    public void setImagePic(List<ImageItem> list){
        if (mAdapter != null){
            mAdapter.setmData(list);
        }
    }

    public List<ImageItem> getImagePic(){
        if (mAdapter != null) {
            return mAdapter.getmData();
        }
        return new ArrayList<ImageItem>();
    }




}
