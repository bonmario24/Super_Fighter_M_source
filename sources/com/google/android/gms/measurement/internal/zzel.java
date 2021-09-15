package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzel<V> {
    private static final Object zzf = new Object();
    private final String zza;
    private final zzej<V> zzb;
    private final V zzc;
    private final V zzd;
    private final Object zze;
    @GuardedBy("overrideLock")
    private volatile V zzg;
    @GuardedBy("cachingLock")
    private volatile V zzh;

    private zzel(@NonNull String str, @NonNull V v, @NonNull V v2, @Nullable zzej<V> zzej) {
        this.zze = new Object();
        this.zzg = null;
        this.zzh = null;
        this.zza = str;
        this.zzc = v;
        this.zzd = v2;
        this.zzb = zzej;
    }

    public final String zza() {
        return this.zza;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r3 = com.google.android.gms.measurement.internal.zzaq.zzcr.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003a, code lost:
        if (r3.hasNext() == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x003c, code lost:
        r0 = (com.google.android.gms.measurement.internal.zzel) r3.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0046, code lost:
        if (com.google.android.gms.measurement.internal.zzx.zza() == false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x004f, code lost:
        throw new java.lang.IllegalStateException("Refreshing flag cache must be done on a worker thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005c, code lost:
        if (r0.zzb == null) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x005e, code lost:
        r1 = r0.zzb.zza();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x006e, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0071, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        return r6;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V zza(@androidx.annotation.Nullable V r6) {
        /*
            r5 = this;
            r2 = 0
            java.lang.Object r1 = r5.zze
            monitor-enter(r1)
            V r0 = r5.zzg     // Catch:{ all -> 0x000a }
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            if (r6 == 0) goto L_0x000d
        L_0x0009:
            return r6
        L_0x000a:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            throw r0
        L_0x000d:
            com.google.android.gms.measurement.internal.zzx r0 = com.google.android.gms.measurement.internal.zzei.zza
            if (r0 != 0) goto L_0x0014
            V r6 = r5.zzc
            goto L_0x0009
        L_0x0014:
            com.google.android.gms.measurement.internal.zzx r0 = com.google.android.gms.measurement.internal.zzei.zza
            java.lang.Object r1 = zzf
            monitor-enter(r1)
            boolean r0 = com.google.android.gms.measurement.internal.zzx.zza()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002d
            V r0 = r5.zzh     // Catch:{ all -> 0x0027 }
            if (r0 != 0) goto L_0x002a
            V r6 = r5.zzc     // Catch:{ all -> 0x0027 }
        L_0x0025:
            monitor-exit(r1)     // Catch:{ all -> 0x0027 }
            goto L_0x0009
        L_0x0027:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            V r6 = r5.zzh     // Catch:{ all -> 0x0027 }
            goto L_0x0025
        L_0x002d:
            monitor-exit(r1)     // Catch:{ all -> 0x0027 }
            java.util.List r0 = com.google.android.gms.measurement.internal.zzaq.zzcr     // Catch:{ SecurityException -> 0x0050 }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ SecurityException -> 0x0050 }
        L_0x0036:
            boolean r0 = r3.hasNext()     // Catch:{ SecurityException -> 0x0050 }
            if (r0 == 0) goto L_0x0051
            java.lang.Object r0 = r3.next()     // Catch:{ SecurityException -> 0x0050 }
            com.google.android.gms.measurement.internal.zzel r0 = (com.google.android.gms.measurement.internal.zzel) r0     // Catch:{ SecurityException -> 0x0050 }
            boolean r1 = com.google.android.gms.measurement.internal.zzx.zza()     // Catch:{ SecurityException -> 0x0050 }
            if (r1 == 0) goto L_0x005a
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ SecurityException -> 0x0050 }
            java.lang.String r1 = "Refreshing flag cache must be done on a worker thread."
            r0.<init>(r1)     // Catch:{ SecurityException -> 0x0050 }
            throw r0     // Catch:{ SecurityException -> 0x0050 }
        L_0x0050:
            r0 = move-exception
        L_0x0051:
            com.google.android.gms.measurement.internal.zzej<V> r0 = r5.zzb
            if (r0 != 0) goto L_0x0073
            com.google.android.gms.measurement.internal.zzx r0 = com.google.android.gms.measurement.internal.zzei.zza
            V r6 = r5.zzc
            goto L_0x0009
        L_0x005a:
            com.google.android.gms.measurement.internal.zzej<V> r1 = r0.zzb     // Catch:{ IllegalStateException -> 0x0070 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzej<V> r1 = r0.zzb     // Catch:{ IllegalStateException -> 0x0070 }
            java.lang.Object r1 = r1.zza()     // Catch:{ IllegalStateException -> 0x0070 }
        L_0x0064:
            java.lang.Object r4 = zzf     // Catch:{ SecurityException -> 0x0050 }
            monitor-enter(r4)     // Catch:{ SecurityException -> 0x0050 }
            r0.zzh = r1     // Catch:{ all -> 0x006b }
            monitor-exit(r4)     // Catch:{ all -> 0x006b }
            goto L_0x0036
        L_0x006b:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x006b }
            throw r0     // Catch:{ SecurityException -> 0x0050 }
        L_0x006e:
            r1 = r2
            goto L_0x0064
        L_0x0070:
            r1 = move-exception
            r1 = r2
            goto L_0x0064
        L_0x0073:
            com.google.android.gms.measurement.internal.zzej<V> r0 = r5.zzb     // Catch:{ SecurityException -> 0x007a, IllegalStateException -> 0x0080 }
            java.lang.Object r6 = r0.zza()     // Catch:{ SecurityException -> 0x007a, IllegalStateException -> 0x0080 }
            goto L_0x0009
        L_0x007a:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzx r0 = com.google.android.gms.measurement.internal.zzei.zza
            V r6 = r5.zzc
            goto L_0x0009
        L_0x0080:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzx r0 = com.google.android.gms.measurement.internal.zzei.zza
            V r6 = r5.zzc
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzel.zza(java.lang.Object):java.lang.Object");
    }
}
