package com.base.common.sp;

import android.content.Context;
import android.content.SharedPreferences;

import com.base.common.BaseApp;

import java.util.Set;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class SpUtil {
    private  static SharedPreferences sConfig = BaseApp.sAppContext.getSharedPreferences("Config", Context.MODE_PRIVATE);
    private static final String TOKEN = "token";
    private static final String ACCOUNT = "account";
    private static final String PASSWORD = "password";
    public static void setToken(String token){
        putString(TOKEN,token);
    }
    public static String getToken(){
        return getString(TOKEN);
    }

    public static void setAccount(String account){
        putString(ACCOUNT,account);
    }
    public static String getAccount(){
        return getString(ACCOUNT);
    }

    public static void setPwd(String pwd){
        putString(PASSWORD,pwd);
    }
    public static String getPwd(){
        return getString(PASSWORD);
    }



    public static void putString(String key, String vale)
    {
        SharedPreferences.Editor editor = sConfig.edit().putString(key, vale);
        editor.commit();
    }
    public static void putStringSet(String key, Set<String> values)
    {
        SharedPreferences.Editor editor = sConfig.edit().putStringSet(key, values);
        editor.commit();
    }

    public static void putBoolean(String key, Boolean b)
    {
        SharedPreferences.Editor editor = sConfig.edit().putBoolean(key, b);
        editor.commit();
    }

    public static void putInt(String key, int i)
    {
        SharedPreferences.Editor editor = sConfig.edit().putInt(key, i);
        editor.commit();
    }
    public static int getInt(String key, int def)
    {
        return sConfig.getInt(key,def);
    }

    public static String getString(String key)
    {
        return sConfig.getString(key, "");
    }

    public static Boolean getBoolean(String key)
    {
        return sConfig.getBoolean(key,false);
    }

    public static Boolean getBoolean(String key, boolean def)
    {
        return sConfig.getBoolean(key,def);
    }
}
