package com.base.wedget.banner;

/**
 *
 * 定时操作
 * Created by weikailiang on 2020/5/14.
 */

public interface AutoInterface
{
    /**
     * 开始定时
     */
    void startAuto();

    /**
     * 停止定时
     */
    void stopAuto();

    /**
     *  是否自动轮播
     */
    boolean isAuto();

    /**
     * 设置自动轮播值
     * @param auto
     */
    void setAuto(boolean auto);

    /**
     * 设置自动轮播值
     * @param auto
     * @param time
     */
    void setAuto(boolean auto,int time);

}
