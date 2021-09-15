package com.bun.miitmdid.p014c;

import android.support.annotation.Keep;

@Keep
/* renamed from: com.bun.miitmdid.c.a */
public enum C0989a {
    UNSUPPORT(-1, "unsupport"),
    HUA_WEI(0, "HUAWEI"),
    XIAOMI(1, "Xiaomi"),
    VIVO(2, "vivo"),
    OPPO(3, "oppo"),
    MOTO(4, "motorola"),
    LENOVO(5, "lenovo"),
    ASUS(6, "asus"),
    SAMSUNG(7, "samsung"),
    MEIZU(8, "meizu"),
    NUBIA(10, "nubia"),
    ZTE(11, "ZTE"),
    ONEPLUS(12, "OnePlus"),
    BLACKSHARK(13, "blackshark"),
    FREEMEOS(30, "freemeos"),
    SSUIOS(31, "ssui");
    
    @Keep

    /* renamed from: a */
    private String f731a;

    @Keep
    private C0989a(int i, String str) {
        this.f731a = str;
    }

    @Keep
    /* renamed from: a */
    public static native C0989a m481a(String str);
}
