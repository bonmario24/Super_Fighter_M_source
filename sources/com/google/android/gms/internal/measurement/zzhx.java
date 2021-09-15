package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
abstract class zzhx<T, B> {
    zzhx() {
    }

    /* access modifiers changed from: package-private */
    public abstract B zza();

    /* access modifiers changed from: package-private */
    public abstract T zza(B b);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzdw zzdw);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: package-private */
    public abstract void zza(T t, zziq zziq) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(Object obj, T t);

    /* access modifiers changed from: package-private */
    public abstract boolean zza(zzhc zzhc);

    /* access modifiers changed from: package-private */
    public abstract T zzb(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzb(T t, zziq zziq) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(Object obj, B b);

    /* access modifiers changed from: package-private */
    public abstract B zzc(Object obj);

    /* access modifiers changed from: package-private */
    public abstract T zzc(T t, T t2);

    /* access modifiers changed from: package-private */
    public abstract void zzd(Object obj);

    /* access modifiers changed from: package-private */
    public abstract int zze(T t);

    /* access modifiers changed from: package-private */
    public abstract int zzf(T t);

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0039 A[LOOP:0: B:9:0x0039->B:12:0x0046, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(B r7, com.google.android.gms.internal.measurement.zzhc r8) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 1
            int r1 = r8.zzb()
            int r2 = r1 >>> 3
            r1 = r1 & 7
            switch(r1) {
                case 0: goto L_0x0011;
                case 1: goto L_0x0021;
                case 2: goto L_0x0029;
                case 3: goto L_0x0031;
                case 4: goto L_0x005b;
                case 5: goto L_0x0019;
                default: goto L_0x000c;
            }
        L_0x000c:
            com.google.android.gms.internal.measurement.zzfp r0 = com.google.android.gms.internal.measurement.zzfm.zzf()
            throw r0
        L_0x0011:
            long r4 = r8.zzg()
            r6.zza(r7, (int) r2, (long) r4)
        L_0x0018:
            return r0
        L_0x0019:
            int r1 = r8.zzj()
            r6.zza(r7, (int) r2, (int) r1)
            goto L_0x0018
        L_0x0021:
            long r4 = r8.zzi()
            r6.zzb(r7, r2, r4)
            goto L_0x0018
        L_0x0029:
            com.google.android.gms.internal.measurement.zzdw r1 = r8.zzn()
            r6.zza(r7, (int) r2, (com.google.android.gms.internal.measurement.zzdw) r1)
            goto L_0x0018
        L_0x0031:
            java.lang.Object r1 = r6.zza()
            int r3 = r2 << 3
            r3 = r3 | 4
        L_0x0039:
            int r4 = r8.zza()
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x0048
            boolean r4 = r6.zza(r1, (com.google.android.gms.internal.measurement.zzhc) r8)
            if (r4 != 0) goto L_0x0039
        L_0x0048:
            int r4 = r8.zzb()
            if (r3 == r4) goto L_0x0053
            com.google.android.gms.internal.measurement.zzfm r0 = com.google.android.gms.internal.measurement.zzfm.zze()
            throw r0
        L_0x0053:
            java.lang.Object r1 = r6.zza(r1)
            r6.zza(r7, (int) r2, r1)
            goto L_0x0018
        L_0x005b:
            r0 = 0
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhx.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzhc):boolean");
    }
}
