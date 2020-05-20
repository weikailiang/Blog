package com.base.common.http.response;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class BaseResponse<H> {
    private int status;

    private int code;
    private String msg;
    private H data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public H getData() {
        return data;
    }

    public void setData(H data) {
        this.data = data;
    }
}
