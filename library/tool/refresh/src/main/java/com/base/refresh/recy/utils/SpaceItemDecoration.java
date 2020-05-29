package com.base.refresh.recy.utils;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 *          Created by admin on 2018/8/9.
 * @FileName: myn.ailisi.com.irecyclerview.utils.SpaceItemDecoration.java
 * Author weikailiang
 * @date: 2018-08-09 18:07
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDivider;

    private static final int SPACE = 25;
    public SpaceItemDecoration() {
        mDivider= new ColorDrawable(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawLine(c,parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) %2==0) {
            if (parent.getChildLayoutPosition(view) == 0){
                outRect.set(SPACE, 0, 0, SPACE);
            }else {
                outRect.set(SPACE, 0, SPACE, SPACE);
            }
        }else {
            outRect.set(SPACE, 0, 0, SPACE);
        }
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
