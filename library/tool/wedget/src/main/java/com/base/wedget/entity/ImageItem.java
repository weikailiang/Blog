package com.base.wedget.entity;

import java.io.Serializable;

/**
 * Created by weikailiang on 2020/5/21.
 */

public class ImageItem implements Serializable{
    private String name;
    private String url;
    private String path;
    private String resource;

    private String var1;
    private String var2;

    public ImageItem(String picUrl){
        this.url = picUrl;
    }
    public ImageItem(String picNmae,String picPath){
        this.name = picNmae;
        this.path = picPath;
    }

    public ImageItem() {
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }
}
