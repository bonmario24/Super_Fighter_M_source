package com.appsflyer.internal;

/* renamed from: com.appsflyer.internal.w */
public final class C0963w {

    /* renamed from: Ι */
    private boolean f644;

    /* renamed from: ι */
    public String f645;

    C0963w(String str, boolean z) {
        this.f645 = str;
        this.f644 = z;
    }

    /* renamed from: ɩ */
    public final boolean mo14735() {
        return this.f644;
    }

    public final String toString() {
        return String.format("%s,%s", new Object[]{this.f645, Boolean.valueOf(this.f644)});
    }

    /* renamed from: com.appsflyer.internal.w$e */
    enum C0964e {
        GOOGLE(0),
        AMAZON(1);
        

        /* renamed from: Ι */
        private int f649;

        private C0964e(int i) {
            this.f649 = i;
        }

        public final String toString() {
            return String.valueOf(this.f649);
        }
    }
}
