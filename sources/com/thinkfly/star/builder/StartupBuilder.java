package com.thinkfly.star.builder;

public class StartupBuilder extends Builder {
    public StartupBuilder() {
    }

    public StartupBuilder(Configure configure) {
        this.f875c = configure.getC();
        this.f876sc = configure.getSc();
        this.appv = configure.getAppv();
        this.extc = configure.getExtc();
        this.sdkv = configure.getSdkv();
        this.uid = configure.getUid();
    }

    public static class Configure {
        private String _appv = "";

        /* renamed from: _c */
        private String f886_c = "";
        private String _extc = "";
        private String _sc = "";
        private String _sdkv = "";
        private String _uid = "";

        public Configure() {
        }

        public Configure(StartupBuilder builder) {
        }

        public String getC() {
            return this.f886_c;
        }

        public Configure setC(String _c) {
            this.f886_c = _c;
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

        public StartupBuilder build() {
            return new StartupBuilder(this);
        }
    }
}
