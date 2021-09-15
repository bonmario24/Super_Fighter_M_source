package com.thinkfly.star.builder;

import android.text.TextUtils;
import com.doraemon.p027eg.CheckUtils;
import com.facebook.internal.NativeProtocol;
import com.thinkfly.star.GlobalParams;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class EventBuilder extends Builder {
    /* access modifiers changed from: private */
    public String _action;
    /* access modifiers changed from: private */
    public Map<String, Object> extMap = null;

    public EventBuilder() {
    }

    public EventBuilder(Configure configure) {
        this._action = configure.getAction();
        this.f876sc = configure.getSc();
        this.appv = configure.getAppv();
        this.extc = configure.getExtc();
        this.sdkv = configure.getSdkv();
        this.uid = configure.getUid();
        this.extMap = configure.getExtMap();
    }

    public JSONObject makeData(JSONObject jsonObject, GlobalParams globalParams) throws JSONException {
        Map<String, Object> map = getExtMap();
        if (map != null && map.size() > 0) {
            for (String key : map.keySet()) {
                if (!CheckUtils.isNumeric(key)) {
                    jsonObject.put(key, map.get(key));
                }
            }
        }
        super.makeData(jsonObject, globalParams);
        jsonObject.put(NativeProtocol.WEB_DIALOG_ACTION, getAction());
        return jsonObject;
    }

    public static class Configure {
        private String _action;
        private String _appv = "";

        /* renamed from: _c */
        private String f877_c = "";
        private Map<String, Object> _extMap;
        private String _extc = "";
        private String _sc = "";
        private String _sdkv = "";
        private String _uid = "";

        public Configure() {
            if (this._extMap != null) {
                this._extMap.clear();
            } else {
                this._extMap = new HashMap();
            }
        }

        public Configure(EventBuilder builder) {
            this._extMap = builder.extMap;
            this._action = builder._action;
        }

        @Deprecated
        public Configure setExtmap(Map<String, Object> map) {
            this._extMap = map;
            return this;
        }

        public Configure setExtMap(Map<String, Object> map) {
            if (map != null && !map.isEmpty()) {
                clearExtMap();
                for (String key : map.keySet()) {
                    this._extMap.put(key, map.get(key));
                }
            }
            return this;
        }

        @Deprecated
        public Map<String, Object> getExtmap() {
            return getExtMap();
        }

        public Map<String, Object> getExtMap() {
            return this._extMap;
        }

        public Configure setAction(String action) {
            this._action = action;
            return this;
        }

        public String getAction() {
            return this._action;
        }

        public String getC() {
            return this.f877_c;
        }

        @Deprecated
        public Configure setC(String _c) {
            this.f877_c = _c;
            return this;
        }

        public String getSc() {
            return this._sc;
        }

        @Deprecated
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

        @Deprecated
        public Configure setSdkv(String _sdkv2) {
            this._sdkv = _sdkv2;
            return this;
        }

        public String getAppv() {
            return this._appv;
        }

        @Deprecated
        public Configure setAppv(String _appv2) {
            this._appv = _appv2;
            return this;
        }

        public String getExtc() {
            return this._extc;
        }

        @Deprecated
        public Configure setExtc(String _extc2) {
            this._extc = _extc2;
            return this;
        }

        public Configure putNumber(String key, long number) {
            return put(key, Long.valueOf(number));
        }

        public Configure putString(String key, String value) {
            return put(key, value);
        }

        public Configure put(String key, Object obj) {
            if (!(TextUtils.isEmpty(key) || obj == null || this._extMap == null)) {
                this._extMap.put(key, obj);
            }
            return this;
        }

        @Deprecated
        public Configure clearExtmap() {
            return clearExtMap();
        }

        public Configure clearExtMap() {
            if (this._extMap != null) {
                this._extMap.clear();
            }
            return this;
        }

        public EventBuilder build() {
            return new EventBuilder(this);
        }
    }

    public String getAction() {
        return this._action;
    }

    @Deprecated
    public Map<String, Object> getExtmap() {
        return this.extMap;
    }

    public Map<String, Object> getExtMap() {
        return this.extMap;
    }
}
