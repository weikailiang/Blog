package com.base.refresh.recy.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jp on 2017/8/9.
 */

public class CustomDivider extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS=new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    private static final int SPACE = 25;
    public CustomDivider(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider=typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawLine(c,parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,0,SPACE);
    }

    private void drawLine(Canvas c, RecyclerView parent)
    {
        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++)
        {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left=child.getRight()+layoutParams.rightMargin;
            int right=left+1;
            int top=child.getTop()+layoutParams.topMargin;
            int bottom=child.getBottom()+layoutParams.bottomMargin+1;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
            left=child.getLeft()-layoutParams.leftMargin;
            right=child.getRight()+layoutParams.rightMargin;
            top=child.getBottom()+layoutParams.bottomMargin;
            bottom=top+1;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
}
