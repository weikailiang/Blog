package com.base.comm_model;

/**
 * Created by weikailiang on 2020/5/9.
 */

public abstract class Model implements BaseModel{

    public String _id;
    public String time;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
