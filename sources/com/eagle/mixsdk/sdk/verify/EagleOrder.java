package com.eagle.mixsdk.sdk.verify;

public class EagleOrder {
    private String extension;
    private String mNotifyUrl;
    private String msg;
    private String order;
    private int state;

    public EagleOrder(int state2, String order2, String ext, String notifyUrl, String msg2) {
        this.state = state2;
        this.order = order2;
        this.extension = ext;
        this.mNotifyUrl = notifyUrl;
        this.msg = msg2;
    }

    public EagleOrder(int state2, String msg2) {
        this.state = state2;
        this.msg = msg2;
    }

    public String getNotifyUrl() {
        return this.mNotifyUrl;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order2) {
        this.order = order2;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension2) {
        this.extension = extension2;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state2) {
        this.state = state2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }
}
