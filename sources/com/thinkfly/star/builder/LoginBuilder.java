package com.thinkfly.star.builder;

import com.thinkfly.star.GlobalParams;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public final class LoginBuilder extends Builder {
    public static int NewUser = 1;
    public static int OldUser = 0;

    /* renamed from: QQ */
    public static int f881QQ = 2;
    public static int System = 0;
    public static int Wechat = 1;
    public static int Weibo = 3;
    private int _isNew;
    /* access modifiers changed from: private */
    public int _lway;
    /* access modifiers changed from: private */
    public Date _rtime;

    public LoginBuilder() {
    }

    public LoginBuilder(Configure configure) {
        this._rtime = configure.getRtime();
        this._lway = configure.getLway();
        this.f875c = configure.getC();
        this.f876sc = configure.getSc();
        this.appv = configure.getAppv();
        this.extc = configure.getExtc();
        this.sdkv = configure.getSdkv();
        this.uid = configure.getUid();
    }

    public JSONObject makeData(JSONObject jsonObject, GlobalParams globalParams) throws JSONException {
        super.makeData(jsonObject, globalParams);
        if (getRtime() == null) {
            jsonObject.put("rtime", 0);
        } else {
            jsonObject.put("rtime", getRtime().getTime());
        }
        jsonObject.put("new", getIsNew());
        jsonObject.put("lway", getLway());
        return jsonObject;
    }

    public static class Configure {
        private String _appv = "";

        /* renamed from: _c */
        private String f882_c = "";
        private String _extc = "";
        private int _lway;
        private Date _rtime;
        private String _sc = "";
        private String _sdkv = "";
        private String _uid = "";

        public Configure() {
        }

        public Configure(LoginBuilder builder) {
            this._rtime = builder._rtime;
            this._lway = builder._lway;
        }

        public Date getRtime() {
            return this._rtime;
        }

        public Configure setRtime(Date _rtime2) {
            this._rtime = _rtime2;
            return this;
        }

        public int getLway() {
            return this._lway;
        }

        public Configure setLway(int _lway2) {
            this._lway = _lway2;
            return this;
        }

        public String getC() {
            return this.f882_c;
        }

        public Configure setC(String _c) {
            this.f882_c = _c;
            return this;
        }

        public String getSc() {
            return this._sc;
        }

        public Configure setSc(String _sc2) {
            this._sc = _sc2;
            return this;
        }

        public String getUid() {
            return this._uid;
        }

        public Configure setUid(String _uid2) {
            this._uid = _uid2;
            return this;
        }

        public String getSdkv() {
            return this._sdkv;
        }

        public Configure setSdkv(String _sdkv2) {
            this._sdkv = _sdkv2;
            return this;
        }

        public String getAppv() {
            return this._appv;
        }

        public Configure setAppv(String _appv2) {
            this._appv = _appv2;
            return this;
        }

        public String getExtc() {
            return this._extc;
        }

        public Configure setExtc(String _extc2) {
            this._extc = _extc2;
            return this;
        }

        public LoginBuilder build() {
            return new LoginBuilder(this);
        }
    }

    public Date getRtime() {
        return this._rtime;
    }

    public void setRtime(Date date) {
        this._rtime = date;
    }

    public int getIsNew() {
        return this._isNew;
    }

    public int getLway() {
        return this._lway;
    }

    public void setIsNew(int isNew) {
        this._isNew = isNew;
    }
}
