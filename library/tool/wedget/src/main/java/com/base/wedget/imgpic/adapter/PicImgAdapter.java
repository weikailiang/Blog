package com.base.wedget.imgpic.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.base.wedget.R;
import com.base.wedget.entity.ImageItem;
import com.base.wedget.imgpic.adapter.inter.OnItemClickListener;
import com.base.wedget.imgpic.adapter.inter.OnItemLongClickListener;
import com.base.wedget.imgpic.adapter.inter.OnItemOrDeleteClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weikailiang on 2020/5/21.
 */

public class PicImgAdapter extends BaseAdapter<PicImgViewHolder>{

    private OnItemLongClickListener onLongClickListener;

    private OnItemOrDeleteClickListener onItemOrDeleteClickListener;
    private OnItemClickListener onItemClickListener;


    private List<ImageItem> mData = new ArrayList<>();
    RequestOptions mRequestOptions;
    public PicImgAdapter(Context context){
        mRequestOptions = new RequestOptions();
        mRequestOptions.centerCrop()
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.img_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }
    public void setDefaultData(boolean isEdit){
        if (isEdit) {
            ImageItem item = new ImageItem();
            item.setResource("ic_pic_add");
            mData.add(0, item);
            notifyDataSetChanged();
        }
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.item_img_pic;
    }

    @Override
    public void onBindViewHolder(PicImgViewHolder holder, int position) {
        final ImageItem imageItem = mData.get(position);
        if (imageItem.getUrl()!=null) {
            Glide.with(holder.mBinding.img.getContext()).applyDefaultRequestOptions(mRequestOptions).load(imageItem.getUrl()).into(holder.mBinding.img);
        }else if (imageItem.getPath()!=null){
            Glide.with(holder.mBinding.img.getContext()).applyDefaultRequestOptions(mRequestOptions).load(imageItem.getPath()).into(holder.mBinding.img);
        }else if (imageItem.getResource()!=null){
            holder.mBinding.img.setImageResource(holder.mBinding.img.getContext().getResources().getIdentifier(imageItem.getResource(),"drawable",holder.mBinding.img.getContext().getApplicationInfo().packageName));
        }
        if (imageItem.getResource()!=null){
            holder.mBinding.imgDelete.setVisibility(View.GONE);
            holder.mBinding.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //跳转添加图片界面
                    ARouter.getInstance().build("/wedget/selectimg").navigation();
                }
            });
        }else {
            holder.mBinding.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemOrDeleteClickListener!=null){
                        onItemOrDeleteClickListener.onDelete(imageItem);
                    }
                }
            });
            holder.mBinding.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemOrDeleteClickListener!=null){
                        onItemOrDeleteClickListener.OnItem(imageItem);
                    }
                    if (onItemClickListener!=null){
                        onItemClickListener.OnItem(imageItem);
                    }
                }
            });
            holder.mBinding.img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onLongClickListener!=null){
                        onLongClickListener.OnLong(imageItem);
                    }
                    return true;
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return mData!=null?mData.size():0;
    }

    public List<ImageItem> getmData() {
        List<ImageItem> imageItemList = new ArrayList<>();
        for (ImageItem Item : mData){
            if (TextUtils.isEmpty(Item.getResource())){
                imageItemList.add(Item);
            }
        }
        return imageItemList;
    }
    public void setmData(List<ImageItem> mData) {
        this.mData.clear();
        ImageItem item = new ImageItem();
        item.setResource("ic_pic_add");
        this.mData.add(0,item);
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }
    public void setmData(List<ImageItem> mData,boolean isEdit) {
        if (isEdit){
            this.mData.clear();
            ImageItem item = new ImageItem();
            item.setResource("ic_pic_add");
            this.mData.add(0,item);
            this.mData.addAll(mData);
        }else {
            this.mData = mData;
        }
        notifyDataSetChanged();
    }

    public OnItemLongClickListener getOnLongClickListener() {
        return onLongClickListener;
    }

    public void setOnLongClickListener(OnItemLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public OnItemOrDeleteClickListener getOnItemOrDeleteClickListener() {
        return onItemOrDeleteClickListener;
    }

    public void setOnItemOrDeleteClickListener(OnItemOrDeleteClickListener onItemOrDeleteClickListener) {
        this.onItemOrDeleteClickListener = onItemOrDeleteClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
