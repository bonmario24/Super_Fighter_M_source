package com.star.libtrack.event;

public class Event {
    private int code;
    private Object ext;
    private String msg;

    public Event(int code2, Object ext2, String msg2) {
        this.code = code2;
        this.ext = ext2;
        this.msg = msg2;
    }

    public Event() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code2) {
        this.code = code2;
    }

    public Object getExt() {
        return this.ext;
    }

    public void setExt(Object ext2) {
        this.ext = ext2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }
}
