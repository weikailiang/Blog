package com.base.wedget.p;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;

import com.base.common.utils.ToastUtil;
import com.base.wedget.R;
import com.base.wedget.adapter.SelectImgAdapter;
import com.base.wedget.adapter.SelectImgDivider;
import com.base.wedget.c.SelectImgContract;
import com.base.wedget.dialog.PicCateAdapter;
import com.base.wedget.dialog.PicCateDailog;
import com.base.wedget.entity.ImageItem;
import com.base.wedget.entity.ImageLoadEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by weikailiang on 2020/5/21.
 */

public class SelectImgPresenter extends SelectImgContract.Presenter{
    //父路径集合
    private List<String> mDirPaths = new ArrayList<>();
    public List<ImageItem> mAllImgList = new ArrayList<>();
    private List<ImageLoadEntity> mImageFloders = new ArrayList<>();

    private int mPicsSize;
    private File mImgDir;//当前目录文件夹
    @Override
    public void checkLocalImg() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            ToastUtil.show("检测到没有内存卡");
            return;
        }
        mView.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = mActivity.getContentResolver();

                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "+
                                MediaStore.Images.Media.MIME_TYPE + "=? or "+
                                MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png","image/jpg"},
                        MediaStore.Images.Media.DATE_TAKEN +" DESC");//获取图片的cursor，按照时间倒序（发现没卵用)
                while (mCursor.moveToNext()) {
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 1.获取图片的路径
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;//不获取sd卡根目录下的图片
                    String parentPath = parentFile.getAbsolutePath();//2.获取图片的文件夹信息
                    String parentName = parentFile.getName();
                    ImageLoadEntity imageFloder ;//自定义一个model，来保存图片的信息

                    //这个操作，可以提高查询的效率，将查询的每一个图片的文件夹的路径保存到集合中，
                    //如果存在，就直接查询下一个，避免对每一个文件夹进行查询操作
                    if (mDirPaths.contains(parentPath)) {
                        continue;
                    } else {
                        mDirPaths.add(parentPath);//将父路径添加到集合中
                        imageFloder = new ImageLoadEntity();
                        imageFloder.setFirstImagePath(path);
                        imageFloder.setDir(parentPath);
                        imageFloder.setName(parentName);
                    }
                    List<String> strings = null;
                    try {
                        strings =  Arrays.asList(parentFile.list(mModel.getFileterImage()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int  picSize = strings.size();//获取每个文件夹下的图片个数
                    imageFloder.setCount(picSize);//传入每个相册的图片个数
                    mImageFloders.add(imageFloder);//添加每一个相册
                    //获取图片最多的文件夹信息（父目录对象和个数，使得刚开始显示的是最多图片的相册
                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();
                mDirPaths = null;
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    private PicCateDailog mPicCateDialog;
    @Override
    public void showImageCate(List<ImageLoadEntity> list) {
        if (mPicCateDialog!=null){
            mPicCateDialog.show();
            return;
        }
        mPicCateDialog = new PicCateDailog(mContext, mContext.getString(R.string.select_img_cate_pic), new PicCateAdapter.OnItemChooseListener() {
            @Override
            public void onItemChoose(ImageLoadEntity entity) {
                if (entity.getName().equals(mContext.getString(R.string.all_img))){
                    checkAllLocalImgByPaths();
                }else {
                    checkLocalImgByPath(entity.getDir());
                }
                mView.getBottomTextView().setText(entity.getDir());
                mPicCateDialog.dismiss();
            }
        });
        mPicCateDialog.show();
        mPicCateDialog.setData(list);
    }

    private SelectImgAdapter mAdapter;
    @Override
    public void showImg(List<ImageItem> list) {

        if (mAdapter == null){
            mAdapter = new SelectImgAdapter();
            GridLayoutManager manager = new GridLayoutManager(mContext,3);
            mView.getRecycler().setLayoutManager(manager);
            mView.getRecycler().setAdapter(mAdapter);
            mView.getRecycler().addItemDecoration(new SelectImgDivider());
        }
        mAdapter.setmData(list);

    }

    @Override
    public void checkLocalImgByPath(String path) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            ToastUtil.show("检测到没有内存卡");
            return;
        }
        mAllImgList.clear();
        mView.showLoading();
        new CheckLocalImg(path,mAllImgList,mHandler).start();
    }

    @Override
    public void checkAllLocalImgByPaths() {
        List<String> paths = new ArrayList<>();
        for (ImageLoadEntity entity : mImageFloders){
            paths.add(entity.getDir());
        }
        mView.showLoading();
        mAllImgList.clear();
        new CheckLocalAllImg(paths,mAllImgList,mHandler).start();
    }

    private static class CheckLocalImg extends Thread{
        private String path;
        private List<ImageItem> list;
        private Handler mHandler;
        public CheckLocalImg(String checkPath,List<ImageItem> data,Handler handler){
            this.path = checkPath;
            this.list = data;
            this.mHandler = handler;
        }
        @Override
        public void run() {
            super.run();
            File file = new File(path);
            File[] files = file.listFiles();
            for (int j = 0; j < files.length; j++) {
                String name = files[j].getName();
                if (files[j].isFile() & name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg")) {
                    list.add(new ImageItem(name,files[j].getPath()));
                }
            }
            mHandler.sendEmptyMessage(2);
        }
    }


    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //获取成功通知
            switch (msg.what){
                case 1:
                    mView.stopLoading();
                    if (mImageFloders.size()>0) {
                        mView.showImgCate(mImageFloders);
                    }else {
                        ToastUtil.show(mContext.getString(R.string.no_search_pic));
                    }

                    checkAllLocalImgByPaths();

                    break;
                case 2:
                    mView.stopLoading();
                    showImg(mAllImgList);
                    break;
                case 3:
                    mView.stopLoading();
                    showImg(mAllImgList);
                    break;
            }
        }
    };

    private static class CheckLocalAllImg extends Thread{
        private List<String> paths;
        private List<ImageItem> list;
        private Handler mHandler;
        public CheckLocalAllImg(List<String> checkPath,List<ImageItem> data,Handler handler){
            this.paths = checkPath;
            this.list = data;
            this.mHandler = handler;
        }
        @Override
        public void run() {
            super.run();
            for (String path : paths) {
                File file = new File(path);
                File[] files = file.listFiles();
                if (null != files) {
                    for (int j = 0; j < files.length; j++) {
                        String name = files[j].getName();
                        if (files[j].isFile() & name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg")) {
                            list.add(new ImageItem(name, files[j].getPath()));
                        }
                    }
                }
            }
            mHandler.sendEmptyMessage(3);
        }
    }






}
