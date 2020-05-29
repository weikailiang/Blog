package com.base.wedget.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

import com.base.wedget.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * requestLayout
 *View重新调用一次layout过程。
 *invalidate
 *View重新调用一次draw过程
 *forceLayout
 *标识View在下一次重绘，需要重新调用layout过程。
 * Created by weikailiang on 2020/5/14.
 */

public class BannerChartLayoutImpl extends BannerChartLayout implements AutoInterface{

    private List<BannerEntity> mData;
    private int backHight = 200;//背景高度

    //默认轮播时间为500毫秒
    private int time = 500;
    private static int START_AUTO_BANNER = 1;
    private OnBannerImgSelectInterface onBannerImgSelectInterface;

    protected Scroller mCroller;//用于滚动效果当工具类

    protected int index = 0;
    /**
     * 要想实现图片的单机事件的获取
     * 我们采用的方法 就是利用一个单击变量开关进行判断 ， 再用户离开屏幕的一瞬间我们去判断变量开关来判断用户的操作是点击 还 是移动
     */
    private boolean isClick; //true 点击事件  false 移动



    private boolean isAuto = true;
    /**
     * 默认为不自动轮播
     */
    private boolean auto;




    public BannerChartLayoutImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public BannerChartLayoutImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    RequestOptions options;
    private void init(Context context,AttributeSet attrs){
        options = new RequestOptions();
        options.centerCrop();
        options.error(R.drawable.ic_error);
        options.placeholder(R.drawable.ic_error);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.bannerstyle);
        auto = typedArray.getBoolean(R.styleable.bannerstyle_auto,false);
        time = typedArray.getInteger(R.styleable.bannerstyle_autoTime,5000);
        backHight = typedArray.getInteger(R.styleable.bannerstyle_backHight,400);
        typedArray.recycle();
        //设置是否自动轮播，轮播时间
        setAuto(auto,time);
        mCroller = new Scroller(getContext());

    }

    public List<BannerEntity> getmData() {
        return mData;
    }

    public void setmData(List<BannerEntity> mData) {
        this.mData = mData;
        if (mData.size()==0)return;
        ImageView img = null;
        for (BannerEntity entity : this.mData){
            img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, backHight);
            img.setLayoutParams(linearLayout);
            addView(img);
            if (!TextUtils.isEmpty(entity.getUrl())){
                Glide.with(getContext()).applyDefaultRequestOptions(options).load(entity.getUrl()).into(img);
            }else if (!TextUtils.isEmpty(entity.getResource())){
                img.setImageResource(img.getContext().getResources().getIdentifier(entity.getResource(),"mipmap",img.getContext().getApplicationInfo().packageName));
            }else {
                Glide.with(getContext()).applyDefaultRequestOptions(options).load(entity.getUrl()).into(img);
            }

        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mCroller.computeScrollOffset())//当前对象是否已经滑动完毕
        {
            //mCroller.getCurrX() 获取滑动当当前位置用于滑动
            scrollTo(mCroller.getCurrX(),0);
            //重写drow绘制
            invalidate();
        }

    }

    /**
     * 拦截事件否则会交给子视图去处理
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    private int x = 0;
    private int lastDistance = 0;//记录上次滑动距离
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://当手指按下时
                //停止定时操作
                if (auto) {
                    stopAuto();
                }
                if (!mCroller.isFinished()){
                    mCroller.abortAnimation();//结束计算滑动位置
                }

                isClick = true;
                x = (int) event.getX();
                Log.i(TAG,"当前距离:"+x);
                break;
            case MotionEvent.ACTION_MOVE:
                //移动当当前位置
                int moveX = (int) event.getX();
                //移动当距离
                int distance = moveX - x;

                Log.i(TAG,"移动距离:"+distance);
                //屏幕当初始坐标为屏幕当左上角 很容易理解为什么是负的了
                if (lastDistance != distance){
                    scrollBy(-distance, 0);
                }
                if (distance==0){
                    isClick = true;
                }else {
                    isClick = false;
                }
                lastDistance = distance;
                break;

            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();//当前viewGroup 滑动位置
                index = (scrollX + childSizeWide/2)/childSizeWide;
                Log.i(TAG,"当前viewGroup 滑动位置:"+scrollX);
                if (index<0)//滑动到最左边一张图片
                {
                    index = 0;
                }else if (index>childSize-1)//滑动到最右边一张图
                {
                    index = childSize-1;
                }

                if (isClick)
                {
                    if (onBannerImgSelectInterface!=null) {
                        Toast.makeText(getContext(),"index:"+index,Toast.LENGTH_SHORT).show();
                        onBannerImgSelectInterface.onItemClick((null != mData && mData.size() != 0) ? mData.get(index) : null, index);
                    }
                }else //滑动到当前位置
                {
                    //当前滑动到距离
                    int dx = index * childSizeWide - scrollX;
                    mCroller.startScroll(scrollX,0,dx,0);
                    postInvalidate();//比较简单直接刷新界面
                    if (onBannerImgSelectInterface!=null) {
                        onBannerImgSelectInterface.selectImage(index);
                    }
                }
                if (auto) {
                    startAuto();
                }
                break;
                default:
                    break;
        }
        return true;
    }


    @Override
    public void startAuto() {
        isAuto = true;
    }

    @Override
    public void stopAuto() {
        isAuto = false;
    }

    public OnBannerImgSelectInterface getOnBannerImgSelectInterface() {
        return onBannerImgSelectInterface;
    }

    public void setOnBannerImgSelectInterface(OnBannerImgSelectInterface onBannerImgSelectInterface) {
        this.onBannerImgSelectInterface = onBannerImgSelectInterface;
    }

    @Override
    public boolean isAuto() {
        return auto;
    }

    @Override
    public void setAuto(boolean auto) {
        setAuto(auto,500);
    }
    @Override
    public void setAuto(boolean auto,int time){
        this.auto = auto;
        this.time = time;
        if (auto) {
            task = new TimerTask() {
                @Override
                public void run() {
                    if (isAuto) {
                        mHandler.sendEmptyMessage(START_AUTO_BANNER);
                    }
                }
            };
            timer.schedule(task,0,time);//执行开始时间 每隔多长时间执行一次
        }
    }
    private Timer timer = new Timer();
    private TimerTask task;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if (++index>=childSize)//说明我们 此时如果是最后一张图片的话，那么我们将会从第一章图片开始重新滑动
                    {
                        index = 0;
                    }
                    scrollTo(childSizeWide*index,0);
                    if (null != onBannerImgSelectInterface) {
                        onBannerImgSelectInterface.selectImage(index);
                    }
                    break;
            }
        }
    };

}
