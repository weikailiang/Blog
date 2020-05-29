package com.base.wedget.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 自定义轮播图绘制
 * Created by weikailiang on 2020/5/14.
 */

public class BannerChartLayout extends FrameLayout{

    protected static String TAG = "BannerChartLayout";
    //子视图的个数
    protected int childSize = 0;
    //第一个子视图的宽度
    protected int childSizeWide = 0;

    public BannerChartLayout(@NonNull Context context) {
        super(context);
    }

    public BannerChartLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerChartLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 1.由于需要测量ViewGroup的宽度和高度,就必须测量子视图的高度和宽度
     * 2.求出子视图的个数
     * 3.子视图宽度总和 为ViewGroup 的宽度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG,"容器开始测量");

        childSize = getChildCount();//获取子视图的个数

        if (0 == childSize){
            //储存测量出来的宽高
            //重写onMeasure必须调用这个方法不然会抛出异常
            setMeasuredDimension(0,0);
        }else {
            //先测量子视图
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            //获取第一个视图
            View view = getChildAt(0);

            childSizeWide = view.getMeasuredWidth();


            //容器第宽度
            int width = childSizeWide * childSize;

            Log.i(TAG,"子View宽度:"+childSizeWide+"====子View高度:"+view.getMeasuredHeight());
            Log.i(TAG,"容器宽度:"+width);
            //容器第高度为第一个子视图第高度为准
            int hight = view.getMeasuredHeight();

            setMeasuredDimension(width,hight);

            //测量完成
        }
    }


    /**
     * 这个方法一定不能调用父类
     * 视图布局第调整
     * @param changed  视图改变时调用
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG,"容器开始布局");
        if (changed){
            int leftMargen = 0;
            //布局子视图
            for (int i=0;i<childSize;i++){
                View view = getChildAt(i);
                view.layout(leftMargen,0,leftMargen + childSizeWide,view.getMeasuredHeight());
                leftMargen += childSizeWide;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

