package com.base.common.http;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class ResponseState {

    public static int SERVICEUNAVAILABLE_CODE = 99;//服务不可达

    public static int SUCCESS_CODE = 100;//请求成功

    public static int TOKENNOTEXIST_CODE = 101;// token not exist"

    public static int TOKENEXPIRED_CODE = 102; //token is expired

    public static int TOKENERROR_CODE = 103;//token error

    public static int PARAMNOTEXIST_CODE = 104;//参数不存在

    public static int PARAMERROR_CODE = 105; //参数错误/必传参数为空

    public static int NOPERMISSION_CODE = 106;//无权限

    public static int QRCODEEXPIRED_CODE = 107;//二维码过期

    public static int VERIFYCODEERROR_CODE = 108; //验证码错误

    public static int VERIFYCODENOTEXIST_CODE = 109;//验证码过期或不存在

    public static int NODATALIST_CODE = 110; //暂无数据

    public static int NODATA_CODE = 115;//数据不存在或已删除

    public static int INSERTFAIL_CODE = 111; //数据保存失败

    public static int UPDATEFAIL_CODE = 112;//数据更新失败

    public static int DELETEFAIL_CODE = 113; //数据删除失败

    public static int FAIL_CODE = 114; //操作失败

    public static int FEIGNFALL_CODE = 115;//feign调用失败

    public static int FILEUPLOADFAIL_CODE = 116; //文件保存失败


}
