package com.base.comm_model.event;

/**
 * Created by weikailiang on 2020/5/11.
 */

public class MainTitleEvent {
    public MainTitleEvent(String t){
        this.title = t;
    }
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
