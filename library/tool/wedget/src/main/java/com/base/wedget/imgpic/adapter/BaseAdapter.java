package com.base.wedget.imgpic.adapter;


import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by weikailiang on 2020/5/21.
 */

public abstract class BaseAdapter<T extends BaseViewHolder> extends RecyclerView.Adapter<T>{

    protected abstract int getLayoutResource();
}
