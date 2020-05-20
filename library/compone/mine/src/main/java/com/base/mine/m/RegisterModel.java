package com.base.mine.m;

import android.text.TextUtils;

import com.base.comm_model.entity.RequestRegisterEntity;
import com.base.common.utils.ToastUtil;
import com.base.mine.c.RegisterContract;

/**
 * Created by weikailiang on 2020/5/9.
 */

public class RegisterModel implements RegisterContract.Model{
    @Override
    public boolean voildUser(RequestRegisterEntity user) {
        if (TextUtils.isEmpty(user.getPhone())){
            ToastUtil.show("请输入帐号");
            return false;
        }
        if (TextUtils.isEmpty(user.getPassWord())){
            ToastUtil.show("请输入密码");
            return false;
        }
        user.setPlatform("1");
        user.setStatus("0");
        return true;
    }
}
