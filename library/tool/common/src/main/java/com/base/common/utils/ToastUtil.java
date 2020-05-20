package com.base.common.utils;


import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by codeest on 2016/8/4.
 */
public class ToastUtil {

    private static ToastUtil mInstance;
    private static Context mContent;
    private ToastUtil(Context context){
        this.mContent = context;
    }
    public static void getInstance(Context context){
        if (mInstance == null){
            mInstance = new ToastUtil(context);
        }
    }

    public static void show(int resId){
        Toast toast = Toast.makeText(mContent, mContent.getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public static void showLong(int resId){
        Toast toast = Toast.makeText(mContent, mContent.getString(resId), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void show(String msg){
        Toast toast = Toast.makeText(mContent, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public static void showLong(String msg){
        Toast toast = Toast.makeText(mContent, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
