package com.base.refresh.recy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by weikailiang on 2020/2/26.
 */

public class HorizonRecyclerView extends RecyclerView {
    public HorizonRecyclerView(Context context) {
        super(context);
    }

    public HorizonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        //返回false，则把事件交给子控件的onInterceptTouchEvent()处理
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //返回true,则后续事件可以继续传递给该View的onTouchEvent()处理
        return true;
    }
}
