package com.base.common.http;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class Config {
    private static final String BASE_URL = "http://192.168.0.119:8989/";
    private static final String DEBUG_URL="http://192.168.0.120:8989/";
    private static boolean isDebug = false;

    public static String getBaseUrl(){
        if (isDebug){
            return DEBUG_URL;
        }else {
            return BASE_URL;
        }
    }
}
