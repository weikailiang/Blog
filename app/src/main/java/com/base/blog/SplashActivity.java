package com.base.blog;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.common.utils.ScreenUtil;


/**
 * Date：2019/4/17 下午6:54
 * Creator：arvin
 * Des：
 */

public class SplashActivity extends AppCompatActivity {
    //权限
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.RECORD_AUDIO,Manifest.permission.ACCESS_FINE_LOCATION};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.cancelAdaptScreen(this);
        //检查权限是否
        checkPermissions();
    }
    private void checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            boolean isFlag = true;
            for (int i = 0;i<permissions.length;i++){
                int k = checkSelfPermission(permissions[i]);
                if (k != PackageManager.PERMISSION_GRANTED){
                    isFlag = false;
                }
            }
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (isFlag) {
                //全部权限都以申请
                start();
            }else {
                //还有权限没有获取到
                startRequestPermission();
            }
        }else {
            start();
        }
    }
    // 开始提交请求权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startRequestPermission() {
        requestPermissions(permissions, 321);
    }

    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (grantResults.length > 0){
                    boolean isTrue = true;
                    //是否都获取到了权限
                    for (int j=0;j<grantResults.length;j++){
                        if (grantResults[j] != PackageManager.PERMISSION_GRANTED){
                            isTrue = false;
                        }
                    }
                    if (!isTrue){
                        // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                        boolean isFlag = true;
                        for (int i = 0;i<permissions.length;i++){
                            boolean b = shouldShowRequestPermissionRationale(permissions[i]);
                            if (!b){
                                isFlag = false;
                            }
                        }
                        // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                        if (!isFlag) {
                            // 用户还是想用我的 APP 的
                            // 提示用户去应用设置界面手动开启权限
                            showDialogTipUserGoToAppSettting();
                        }else {
                            Toast.makeText(this,"抱歉，您有权限没有获取到，无法正常使用", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }else {
                        start();
                    }
                }else {
                    start();
                }
            } else {
                start();
            }
        }
    }
    // 提示用户去应用设置界面手动开启权限
    AlertDialog dialog;
    private void showDialogTipUserGoToAppSettting() {
        dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("请在-应用设置-权限-中，允许本应用使用所有权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }
    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 123);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取

                // 检查该权限是否已经获取
                boolean isFlag = true;
                for (int i = 0;i<permissions.length;i++){
                    int k = checkSelfPermission(permissions[i]);
                    if (k != PackageManager.PERMISSION_GRANTED){
                        isFlag = false;
                    }
                }
                if (isFlag){
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    start();
                }else {
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                }
            }
        }
    }



    protected void start() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ARouter.getInstance().build("/mine/login").navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
