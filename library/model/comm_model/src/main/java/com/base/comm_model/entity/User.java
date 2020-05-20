package com.base.comm_model.entity;

import com.base.comm_model.Model;

/**
 * Created by weikailiang on 2020/5/9.
 */

public class User extends Model{
    /**
     * token
     */
    public String token;

    /**
     * 设备类型(0:PC 1:手机)
     */
    public Integer deviceType;

    /**
     * 用户ID
     */
    public String userId;

    /**
     * 用户名
     */
    public String userName;

    /**
     * 账户ID
     */
    public String accountId;

    /**
     * 真实姓名
     */
    public String realName;

    /**
     * 手机号
     */
    public String phone;

    /**
     * 地址
     */
    public String address;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
