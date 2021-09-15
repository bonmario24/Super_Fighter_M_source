package com.doraemon.devices;

import android.text.TextUtils;

public class Identifier {
    private String aaid = "";
    private String gAid = "";
    private String oaid = "";
    private String vaid = "";

    public Identifier() {
    }

    public Identifier(String oaid2, String vaid2, String aaid2, String gAid2) {
        this.oaid = oaid2;
        this.vaid = vaid2;
        this.aaid = aaid2;
        this.gAid = gAid2;
    }

    public String getGAID() {
        return this.gAid;
    }

    public String getOAID() {
        return getNoNullString(this.oaid);
    }

    public void setOAID(String oaid2) {
        this.oaid = oaid2;
    }

    public String getVAID() {
        return getNoNullString(this.vaid);
    }

    public void setVAID(String vaid2) {
        this.vaid = vaid2;
    }

    public String getAAID() {
        return getNoNullString(this.aaid);
    }

    public void setAAID(String aaid2) {
        this.aaid = aaid2;
    }

    private String getNoNullString(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public Identifier setGAID(String id) {
        this.gAid = id;
        return this;
    }
}
