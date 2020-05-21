package com.base.wedget.m;

import com.base.wedget.c.SelectImgContract;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by weikailiang on 2020/5/21.
 */

public class SelectImgModel implements SelectImgContract.Model{
    //图片筛选器，过滤无效图片
    @Override
    public FilenameFilter getFileterImage(){
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg")
                        || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        };
        return filenameFilter;
    }
}
