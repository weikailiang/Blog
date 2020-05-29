package com.base.blog.adapter;
import android.text.TextUtils;
import android.view.View;

import com.base.blog.R;
import com.base.blog.databinding.ItemMenuHomeLeftBinding;
import com.base.refresh.recy.BasicRecyViewHolder;
import com.base.refresh.recy.HFSingleTypeRecyAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.base.bolg_model.entity.MenuHomeLeftEntity;

import androidx.databinding.DataBindingUtil;

/**
 * Created by Administrator on 2019/10/21.
 */

public class ItemMenuHomeLeftAdapter extends HFSingleTypeRecyAdapter<MenuHomeLeftEntity,ItemMenuHomeLeftAdapter.ViewHolder> {

    RequestOptions options = null;
    public ItemMenuHomeLeftAdapter(int resId) {
        super(resId);
        options = new RequestOptions()
                .placeholder(R.mipmap.ic_logo)
                .error(R.mipmap.ic_error)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @Override
    public ViewHolder buildViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }


    @Override
    public void bindDataToHolder(ViewHolder holder, MenuHomeLeftEntity item, int position) {
        holder.binding.setEntity(item);
        holder.binding.name.setText(item.getmName());
        if (!TextUtils.isEmpty(item.getmIcon())) {
            holder.binding.img.setImageResource(holder.binding.img.getContext().getResources().getIdentifier(item.getmIcon(), "mipmap", holder.binding.img.getContext().getApplicationInfo().packageName));
        }else if (!TextUtils.isEmpty(item.getmWebIcon())){
            if (options==null) {
                options = new RequestOptions()
                        .placeholder(R.mipmap.ic_logo)
                        .error(R.mipmap.ic_error)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);

            }
            Glide.with(holder.binding.img.getContext()).load(item.getmWebIcon()).apply(options).into(holder.binding.img);
        }else {
            holder.binding.img.setImageResource(R.mipmap.ic_error);
        }
    }

    public static class ViewHolder extends BasicRecyViewHolder {
        ItemMenuHomeLeftBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }


    }
}
