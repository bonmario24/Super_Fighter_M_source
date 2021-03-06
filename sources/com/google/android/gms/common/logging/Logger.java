package com.google.android.gms.common.logging;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class Logger {
    private final String mTag;
    private final String zzey;
    private final GmsLogger zzfk;
    private final int zzfl;

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Logger(java.lang.String r7, java.lang.String... r8) {
        /*
            r6 = this;
            int r0 = r8.length
            if (r0 != 0) goto L_0x0009
            java.lang.String r0 = ""
        L_0x0005:
            r6.<init>((java.lang.String) r7, (java.lang.String) r0)
            return
        L_0x0009:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r0 = 91
            r1.append(r0)
            int r2 = r8.length
            r0 = 0
        L_0x0015:
            if (r0 >= r2) goto L_0x002b
            r3 = r8[r0]
            int r4 = r1.length()
            r5 = 1
            if (r4 <= r5) goto L_0x0025
            java.lang.String r4 = ","
            r1.append(r4)
        L_0x0025:
            r1.append(r3)
            int r0 = r0 + 1
            goto L_0x0015
        L_0x002b:
            r0 = 93
            java.lang.StringBuilder r0 = r1.append(r0)
            r2 = 32
            r0.append(r2)
            java.lang.String r0 = r1.toString()
            goto L_0x0005
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.logging.Logger.<init>(java.lang.String, java.lang.String[]):void");
    }

    private Logger(String str, String str2) {
        this.zzey = str2;
        this.mTag = str;
        this.zzfk = new GmsLogger(str);
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        this.zzfl = i;
    }

    @KeepForSdk
    public String getTag() {
        return this.mTag;
    }

    @KeepForSdk
    public boolean isLoggable(int i) {
        return this.zzfl <= i;
    }

    @KeepForSdk
    /* renamed from: v */
    public void mo21230v(String str, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(str, objArr));
        }
    }

    @KeepForSdk
    /* renamed from: v */
    public void mo21229v(String str, Throwable th, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(str, objArr), th);
        }
    }

    @KeepForSdk
    /* renamed from: d */
    public void mo21222d(String str, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr));
        }
    }

    @KeepForSdk
    /* renamed from: d */
    public void mo21221d(String str, Throwable th, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr), th);
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public void mo21227i(String str, @Nullable Object... objArr) {
        Log.i(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    /* renamed from: w */
    public void mo21231w(String str, @Nullable Object... objArr) {
        Log.w(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    /* renamed from: e */
    public void mo21224e(String str, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    /* renamed from: e */
    public void mo21223e(String str, Throwable th, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void wtf(String str, Throwable th, @Nullable Object... objArr) {
        Log.wtf(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void wtf(Throwable th) {
        Log.wtf(this.mTag, th);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String format(String str, @Nullable Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.zzey.concat(str);
    }
}
