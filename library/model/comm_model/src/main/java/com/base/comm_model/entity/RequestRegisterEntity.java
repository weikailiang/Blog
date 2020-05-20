package com.base.comm_model.entity;

/**
 * Created by weikailiang on 2020/5/9.
 */

public class RequestRegisterEntity {
    //用户ID
    private String id;
    //用户状态
    private String status;
    //登录密码
    private String passWord;
    //登录平台  平台 0：PC 1：手机
    private String platform;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
