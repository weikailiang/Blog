package com.base.wedget.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.base.wedget.R;
import com.base.wedget.entity.ImageItem;
import com.base.wedget.imgpic.adapter.BaseAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by weikailiang on 2020/5/25.
 */

public class SelectImgAdapter extends BaseAdapter<SelectImgViewHolder>{
    private List<ImageItem> mData;
    @Override
    protected int getLayoutResource() {
        return R.layout.item_select_img;
    }
    RequestOptions options;
    public SelectImgAdapter(){
        options = new RequestOptions();
        options.placeholder(R.drawable.img_loading);
        options.error(R.drawable.ic_error);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.centerCrop();
    }

    @Override
    public SelectImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectImgViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutResource(),null));
    }

    @Override
    public void onBindViewHolder(SelectImgViewHolder holder, int position) {

        Glide.with(holder.mBinding.img.getContext()).applyDefaultRequestOptions(options).load(mData.get(position).getPath()).into(holder.mBinding.img);
    }

    @Override
    public int getItemCount() {
        return (null!=mData&&mData.size()>0) ? mData.size() : 0;
    }

    public List<ImageItem> getmData() {
        return mData;
    }

    public void setmData(List<ImageItem> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}
