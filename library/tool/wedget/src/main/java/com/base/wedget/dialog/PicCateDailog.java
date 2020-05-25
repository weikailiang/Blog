package com.base.wedget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.base.wedget.R;
import com.base.wedget.entity.ImageLoadEntity;

import java.util.List;

/**
 * Created by weikailiang on 2020/5/22.
 */

public class PicCateDailog extends BottomSheetDialog{
    private String mTitle;
    private RecyclerView mRv;
    private Context mContext;
    private PicCateAdapter.OnItemChooseListener mListener;
    private PicCateAdapter mAdapter;

    public PicCateDailog(@NonNull Context context, String title, PicCateAdapter.OnItemChooseListener listener) {
        super(context);
        mTitle = title;
        mContext = context;
        mListener = listener;
    }

    public void setData(List<ImageLoadEntity> data)
    {
        if (mAdapter!=null) {
            mAdapter.setList(data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.item_pic_cat_dialog);
        TextView tv_title = findViewById(R.id.tv_title);
        mRv = findViewById(R.id.rv);
        tv_title.setText(mTitle);
        initRv();
        super.onCreate(savedInstanceState);
    }

    private void initRv() {
        if (mAdapter == null) {
            mAdapter = new PicCateAdapter();
        }
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRv.setAdapter(mAdapter);
        if (mListener!=null) {
            mAdapter.setOnItemChooseListener(mListener);
        }
    }

    public PicCateAdapter.OnItemChooseListener getmListener() {
        return mListener;
    }

    public void setmListener(PicCateAdapter.OnItemChooseListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void show() {
        super.show();
    }


}
