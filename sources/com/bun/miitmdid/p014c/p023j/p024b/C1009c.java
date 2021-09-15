package com.bun.miitmdid.p014c.p023j.p024b;

import android.database.ContentObserver;
import android.os.Handler;
import android.support.annotation.Keep;

@Keep
/* renamed from: com.bun.miitmdid.c.j.b.c */
public class C1009c extends ContentObserver {
    @Keep

    /* renamed from: a */
    private String f783a;
    @Keep

    /* renamed from: b */
    private int f784b;
    @Keep

    /* renamed from: c */
    private C1007b f785c;

    @Keep
    public C1009c(C1007b bVar, int i, String str) {
        super((Handler) null);
        this.f785c = bVar;
        this.f784b = i;
        this.f783a = str;
    }

    @Keep
    public native void onChange(boolean z);
}
