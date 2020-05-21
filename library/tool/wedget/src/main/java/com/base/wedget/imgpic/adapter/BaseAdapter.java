package com.base.wedget.imgpic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by weikailiang on 2020/5/21.
 */

public abstract class BaseAdapter<T extends BaseViewHolder> extends RecyclerView.Adapter<T>{

    protected abstract int getLayoutResource();
}
