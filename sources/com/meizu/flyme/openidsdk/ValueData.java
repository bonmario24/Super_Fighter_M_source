package com.meizu.flyme.openidsdk;

import android.support.annotation.Keep;

@Keep
public class ValueData {
    @Keep

    /* renamed from: a */
    public String f853a;
    @Keep

    /* renamed from: b */
    public int f854b;
    @Keep

    /* renamed from: c */
    public long f855c = (System.currentTimeMillis() + 86400000);

    @Keep
    public ValueData(String str, int i) {
        this.f853a = str;
        this.f854b = i;
    }

    @Keep
    public native String toString();
}
