package com.eagle.mixsdk.sdk.did.bean;

public class IdentifierBean {
    private String aaid = "";
    private String oaid = "";
    private String vaid = "";

    public IdentifierBean() {
    }

    public IdentifierBean(String str, String str2, String str3) {
        this.oaid = str;
        this.vaid = str2;
        this.aaid = str3;
    }

    public String getAAID() {
        return this.aaid;
    }

    public String getOAID() {
        return this.oaid;
    }

    public String getVAID() {
        return this.vaid;
    }

    public void setAAID(String str) {
        this.aaid = str;
    }

    public void setOAID(String str) {
        this.oaid = str;
    }

    public void setVAID(String str) {
        this.vaid = str;
    }
}
