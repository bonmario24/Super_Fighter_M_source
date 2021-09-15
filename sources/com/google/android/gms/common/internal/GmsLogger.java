package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class GmsLogger {
    private static final int zzev = 15;
    private static final String zzew = null;
    private final String zzex;
    private final String zzey;

    public GmsLogger(String str, String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.zzex = str;
        if (str2 == null || str2.length() <= 0) {
            this.zzey = null;
        } else {
            this.zzey = str2;
        }
    }

    public GmsLogger(String str) {
        this(str, (String) null);
    }

    @KeepForSdk
    public final boolean canLog(int i) {
        return Log.isLoggable(this.zzex, i);
    }

    @KeepForSdk
    public final boolean canLogPii() {
        return false;
    }

    @KeepForSdk
    /* renamed from: d */
    public final void mo21106d(String str, String str2) {
        if (canLog(3)) {
            Log.d(str, zzh(str2));
        }
    }

    @KeepForSdk
    /* renamed from: d */
    public final void mo21107d(String str, String str2, Throwable th) {
        if (canLog(3)) {
            Log.d(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    /* renamed from: v */
    public final void mo21115v(String str, String str2) {
        if (canLog(2)) {
            Log.v(str, zzh(str2));
        }
    }

    @KeepForSdk
    /* renamed from: v */
    public final void mo21116v(String str, String str2, Throwable th) {
        if (canLog(2)) {
            Log.v(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public final void mo21111i(String str, String str2) {
        if (canLog(4)) {
            Log.i(str, zzh(str2));
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public final void mo21112i(String str, String str2, Throwable th) {
        if (canLog(4)) {
            Log.i(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    /* renamed from: w */
    public final void mo21117w(String str, String str2) {
        if (canLog(5)) {
            Log.w(str, zzh(str2));
        }
    }

    @KeepForSdk
    /* renamed from: w */
    public final void mo21118w(String str, String str2, Throwable th) {
        if (canLog(5)) {
            Log.w(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    public final void wfmt(String str, String str2, Object... objArr) {
        if (canLog(5)) {
            Log.w(this.zzex, zza(str2, objArr));
        }
    }

    @KeepForSdk
    /* renamed from: e */
    public final void mo21108e(String str, String str2) {
        if (canLog(6)) {
            Log.e(str, zzh(str2));
        }
    }

    @KeepForSdk
    /* renamed from: e */
    public final void mo21109e(String str, String str2, Throwable th) {
        if (canLog(6)) {
            Log.e(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    public final void efmt(String str, String str2, Object... objArr) {
        if (canLog(6)) {
            Log.e(str, zza(str2, objArr));
        }
    }

    @KeepForSdk
    public final void wtf(String str, String str2, Throwable th) {
        if (canLog(7)) {
            Log.e(str, zzh(str2), th);
            Log.wtf(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    public final void pii(String str, String str2) {
        if (canLogPii()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(" PII_LOG");
            Log.i(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), zzh(str2));
        }
    }

    @KeepForSdk
    public final void pii(String str, String str2, Throwable th) {
        if (canLogPii()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(" PII_LOG");
            Log.i(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), zzh(str2), th);
        }
    }

    private final String zzh(String str) {
        return this.zzey == null ? str : this.zzey.concat(str);
    }

    private final String zza(String str, Object... objArr) {
        String format = String.format(str, objArr);
        return this.zzey == null ? format : this.zzey.concat(format);
    }
}
