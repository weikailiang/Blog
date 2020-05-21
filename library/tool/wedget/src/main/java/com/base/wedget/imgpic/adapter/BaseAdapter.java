package com.base.wedget.imgpic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by weikailiang on 2020/5/21.
 */

public abstract class BaseAdapter<VH extends BaseViewHolder> extends RecyclerView.Adapter<VH>{
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return (VH) new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutResource(),null));
    }
    protected abstract int getLayoutResource();
}
