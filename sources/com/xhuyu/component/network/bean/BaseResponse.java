package com.xhuyu.component.network.bean;

import com.alibaba.fastjson.JSONObject;

public class BaseResponse {
    private JSONObject data;
    private String message = "";
    private int status;

    public boolean isSuccess() {
        return this.status == 1;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public JSONObject getData() {
        return this.data;
    }

    public void setData(JSONObject data2) {
        this.data = data2;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status2) {
        this.status = status2;
    }
}
