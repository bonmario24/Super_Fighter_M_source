package com.thinkfly.star.builder;

public class InitBuilder extends Builder {
    private boolean isOpenHeartbeat = false;

    public InitBuilder() {
    }

    public InitBuilder(Configure configure) {
        this.f875c = configure.getC();
        this.f876sc = configure.getSC();
        this.uid = configure.getUid();
        this.appv = configure.getAppv();
        this.extc = configure.getExtc();
        setOpenHeartbeat(configure.getIsOpenHeartbeat());
    }

    public static class Configure {
        private String appv = "";

        /* renamed from: c */
        private String f879c = "";
        private String extc = "";
        private boolean isOpenHeartbeat = false;

        /* renamed from: sc */
        private String f880sc = "";
        private String uid = "";

        public Configure() {
        }

        public Configure(InitBuilder builder) {
        }

        public String getC() {
            return this.f879c;
        }

        public Configure setC(String c) {
            this.f879c = c;
            return this;
        }

        public String getSC() {
            return this.f880sc;
        }

        public Configure setsc(String sc) {
            this.f880sc = sc;
            return this;
        }

        public String getUid() {
            return this.uid;
        }

        public Configure setUid(String uid2) {
            this.uid = uid2;
            return this;
        }

        public Configure setAppv(String appv2) {
            this.appv = appv2;
            return this;
        }

        public String getAppv() {
            return this.appv;
        }

        public Configure setExtc(String extc2) {
            this.extc = extc2;
            return this;
        }

        public String getExtc() {
            return this.extc;
        }

        public Configure setIsOpenHeartbeat(boolean bool) {
            this.isOpenHeartbeat = bool;
            return this;
        }

        public boolean getIsOpenHeartbeat() {
            return this.isOpenHeartbeat;
        }

        public InitBuilder build() {
            return new InitBuilder(this);
        }
    }

    public String getC() {
        return this.f875c;
    }

    public String getSC() {
        return this.f876sc;
    }

    public String getUid() {
        return this.uid;
    }

    public String getAppv() {
        return this.appv;
    }

    public String getExtc() {
        return this.extc;
    }

    public boolean isOpenHeartbeat() {
        return this.isOpenHeartbeat;
    }

    public void setOpenHeartbeat(boolean isOpenHeartbeat2) {
        this.isOpenHeartbeat = isOpenHeartbeat2;
    }
}
