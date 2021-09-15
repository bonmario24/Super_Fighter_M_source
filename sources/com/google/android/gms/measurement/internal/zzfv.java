package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzfv implements Thread.UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzft zzb;

    public zzfv(zzft zzft, String str) {
        this.zzb = zzft;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzr().zzf().zza(this.zza, th);
    }
}
