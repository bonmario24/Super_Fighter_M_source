package com.xhuyu.component.mvp.model;

public class LoginResult {
    private String data;
    private int loginType;
    private int resultCode;

    public LoginResult() {
    }

    public LoginResult(int loginType2, int resultCode2, String data2) {
        this.loginType = loginType2;
        this.resultCode = resultCode2;
        this.data = data2;
    }

    public int getLoginType() {
        return this.loginType;
    }

    public void setLoginType(int loginType2) {
        this.loginType = loginType2;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode2) {
        this.resultCode = resultCode2;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data2) {
        this.data = data2;
    }
}
