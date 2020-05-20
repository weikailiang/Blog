package com.base.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.base.common.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jp on 2018/8/5.
 */

public class ErrorLogUtil {

    public static void saveError(Context context, Throwable e) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String date_file = simpleDateFormat.format(date);
        String date_crash = simpleDateFormat2.format(date);
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String appName = (String) packageManager.getApplicationLabel(applicationInfo);


        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File path = new File(absolutePath + Config.PATH+ File.separator + "Error" + File.separator + appName);
        if (!path.exists()) {
            path.mkdirs();
        }
        String fileName = date_file + "_log.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path, fileName), true);
        PrintStream printStream = new PrintStream(fileOutputStream, true, "utf-8");

        printStream.print(date_crash);
        printStream.print("\r\n");
        e.printStackTrace(printStream);
        printStream.print("\r\n");
        printStream.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>分割线<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        printStream.print("\r\n");
        printStream.print("\r\n");
        printStream.close();
        fileOutputStream.close();

    }




    public static void saveLog(Context context, String log) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String date_file = simpleDateFormat.format(date);
        String date_crash = simpleDateFormat2.format(date);
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String appName = (String) packageManager.getApplicationLabel(applicationInfo);


        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File path = new File(absolutePath + Config.PATH+ File.separator + "Error" + File.separator + appName);
        if (!path.exists()) {
            path.mkdirs();
        }
        String fileName = date_file + "_log.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path, fileName), true);
        PrintStream printStream = new PrintStream(fileOutputStream, true, "utf-8");

        printStream.print(date_crash);
        printStream.print("\r\n");
        printStream.print(log);
        printStream.print("\r\n");
        printStream.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>分割线<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        printStream.print("\r\n");
        printStream.print("\r\n");
        printStream.close();
        fileOutputStream.close();

    }
}
