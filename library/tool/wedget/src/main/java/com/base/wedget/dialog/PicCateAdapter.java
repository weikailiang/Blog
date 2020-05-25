package com.base.wedget.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.wedget.R;
import com.base.wedget.entity.ImageLoadEntity;
import com.base.wedget.imgpic.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by weikailiang on 2020/5/25.
 */

public class PicCateAdapter extends BaseAdapter<PicCateViewHolder>{
    private List<ImageLoadEntity> list;
    private OnItemChooseListener onItemChooseListener;
    @Override
    protected int getLayoutResource() {
        return R.layout.item_cate_view;
    }

    @Override
    public PicCateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PicCateViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutResource(),null));
    }

    @Override
    public void onBindViewHolder(PicCateViewHolder holder, final int position) {
        holder.mBinding.name.setText(list.get(position).getDir());
        holder.mBinding.num.setText(list.get(position).getCount()+"");

        holder.mBinding.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemChooseListener!=null){
                    onItemChooseListener.onItemChoose(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public List<ImageLoadEntity> getList() {
        return list;
    }

    public void setList(List<ImageLoadEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public OnItemChooseListener getOnItemChooseListener() {
        return onItemChooseListener;
    }

    public void setOnItemChooseListener(OnItemChooseListener onItemChooseListener) {
        this.onItemChooseListener = onItemChooseListener;
    }

    public interface OnItemChooseListener
    {
        void onItemChoose(ImageLoadEntity entity);
    }
}
