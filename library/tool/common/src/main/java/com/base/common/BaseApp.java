package com.base.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.DisplayMetrics;

import com.base.common.utils.ErrorLogUtil;
import com.base.common.utils.ToastUtil;

import java.util.LinkedList;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class BaseApp extends Application implements Thread.UncaughtExceptionHandler{
    protected static LinkedList<Activity> sActivities = new LinkedList<>();
    public static Context sAppContext;


    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;


    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;


    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
        initScreenSize();
        //7.0文件路径权限适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        ToastUtil.getInstance(this);


    }
    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    public static void addActivity(Activity activity) {
        if (!sActivities.contains(activity)) {
            sActivities.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        if (sActivities.contains(activity)) {
            sActivities.remove(activity);
        }
    }

    public static void clearActivity() {
        for (Activity activity : sActivities) {
            activity.finish();
        }
        sActivities.clear();
    }
    public static int getActivitySize()
    {
        return sActivities.size();
    }

    public static void exit() {
        clearActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        clearActivity();
        try {
            ErrorLogUtil.saveError(getApplicationContext(), throwable);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
