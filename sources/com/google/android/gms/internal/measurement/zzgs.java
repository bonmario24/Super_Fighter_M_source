package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzgs<T> implements zzhf<T> {
    private final zzgm zza;
    private final zzhx<?, ?> zzb;
    private final boolean zzc;
    private final zzet<?> zzd;

    private zzgs(zzhx<?, ?> zzhx, zzet<?> zzet, zzgm zzgm) {
        this.zzb = zzhx;
        this.zzc = zzet.zza(zzgm);
        this.zzd = zzet;
        this.zza = zzgm;
    }

    static <T> zzgs<T> zza(zzhx<?, ?> zzhx, zzet<?> zzet, zzgm zzgm) {
        return new zzgs<>(zzhx, zzet, zzgm);
    }

    public final T zza() {
        return this.zza.zzbr().zzu();
    }

    public final boolean zza(T t, T t2) {
        if (!this.zzb.zzb(t).equals(this.zzb.zzb(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza((Object) t).equals(this.zzd.zza((Object) t2));
        }
        return true;
    }

    public final int zza(T t) {
        int hashCode = this.zzb.zzb(t).hashCode();
        if (this.zzc) {
            return (hashCode * 53) + this.zzd.zza((Object) t).hashCode();
        }
        return hashCode;
    }

    public final void zzb(T t, T t2) {
        zzhh.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzhh.zza(this.zzd, t, t2);
        }
    }

    public final void zza(T t, zziq zziq) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd2 = this.zzd.zza((Object) t).zzd();
        while (zzd2.hasNext()) {
            Map.Entry next = zzd2.next();
            zzew zzew = (zzew) next.getKey();
            if (zzew.zzc() != zzir.MESSAGE || zzew.zzd() || zzew.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzft) {
                zziq.zza(zzew.zza(), (Object) ((zzft) next).zza().zzc());
            } else {
                zziq.zza(zzew.zza(), next.getValue());
            }
        }
        zzhx<?, ?> zzhx = this.zzb;
        zzhx.zzb(zzhx.zzb(t), zziq);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: com.google.android.gms.internal.measurement.zzfe$zzd} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.measurement.zzdr r15) throws java.io.IOException {
        /*
            r10 = this;
            r7 = 0
            r9 = 2
            r0 = r11
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0
            com.google.android.gms.internal.measurement.zzhw r4 = r0.zzb
            com.google.android.gms.internal.measurement.zzhw r0 = com.google.android.gms.internal.measurement.zzhw.zza()
            if (r4 != r0) goto L_0x0016
            com.google.android.gms.internal.measurement.zzhw r4 = com.google.android.gms.internal.measurement.zzhw.zzb()
            r0 = r11
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0
            r0.zzb = r4
        L_0x0016:
            com.google.android.gms.internal.measurement.zzfe$zzb r11 = (com.google.android.gms.internal.measurement.zzfe.zzb) r11
            r11.zza()
            r6 = r7
        L_0x001c:
            if (r13 >= r14) goto L_0x00aa
            int r2 = com.google.android.gms.internal.measurement.zzds.zza(r12, r13, r15)
            int r0 = r15.zza
            r1 = 11
            if (r0 == r1) goto L_0x0053
            r1 = r0 & 7
            if (r1 != r9) goto L_0x004e
            com.google.android.gms.internal.measurement.zzet<?> r1 = r10.zzd
            com.google.android.gms.internal.measurement.zzer r3 = r15.zzd
            com.google.android.gms.internal.measurement.zzgm r5 = r10.zza
            int r6 = r0 >>> 3
            java.lang.Object r1 = r1.zza(r3, r5, r6)
            r6 = r1
            com.google.android.gms.internal.measurement.zzfe$zzd r6 = (com.google.android.gms.internal.measurement.zzfe.zzd) r6
            if (r6 == 0) goto L_0x0046
            com.google.android.gms.internal.measurement.zzhb.zza()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x0046:
            r1 = r12
            r3 = r14
            r5 = r15
            int r13 = com.google.android.gms.internal.measurement.zzds.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.measurement.zzhw) r4, (com.google.android.gms.internal.measurement.zzdr) r5)
            goto L_0x001c
        L_0x004e:
            int r13 = com.google.android.gms.internal.measurement.zzds.zza((int) r0, (byte[]) r12, (int) r2, (int) r14, (com.google.android.gms.internal.measurement.zzdr) r15)
            goto L_0x001c
        L_0x0053:
            r0 = 0
            r3 = r7
            r5 = r0
            r1 = r2
        L_0x0057:
            if (r1 >= r14) goto L_0x009d
            int r0 = com.google.android.gms.internal.measurement.zzds.zza(r12, r1, r15)
            int r1 = r15.zza
            int r2 = r1 >>> 3
            r8 = r1 & 7
            switch(r2) {
                case 2: goto L_0x006f;
                case 3: goto L_0x0086;
                default: goto L_0x0066;
            }
        L_0x0066:
            r2 = 12
            if (r1 == r2) goto L_0x009e
            int r1 = com.google.android.gms.internal.measurement.zzds.zza((int) r1, (byte[]) r12, (int) r0, (int) r14, (com.google.android.gms.internal.measurement.zzdr) r15)
            goto L_0x0057
        L_0x006f:
            if (r8 != 0) goto L_0x0066
            int r1 = com.google.android.gms.internal.measurement.zzds.zza(r12, r0, r15)
            int r2 = r15.zza
            com.google.android.gms.internal.measurement.zzet<?> r0 = r10.zzd
            com.google.android.gms.internal.measurement.zzer r5 = r15.zzd
            com.google.android.gms.internal.measurement.zzgm r6 = r10.zza
            java.lang.Object r0 = r0.zza(r5, r6, r2)
            com.google.android.gms.internal.measurement.zzfe$zzd r0 = (com.google.android.gms.internal.measurement.zzfe.zzd) r0
            r5 = r2
            r6 = r0
            goto L_0x0057
        L_0x0086:
            if (r6 == 0) goto L_0x0091
            com.google.android.gms.internal.measurement.zzhb.zza()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x0091:
            if (r8 != r9) goto L_0x0066
            int r1 = com.google.android.gms.internal.measurement.zzds.zze(r12, r0, r15)
            java.lang.Object r0 = r15.zzc
            com.google.android.gms.internal.measurement.zzdw r0 = (com.google.android.gms.internal.measurement.zzdw) r0
            r3 = r0
            goto L_0x0057
        L_0x009d:
            r0 = r1
        L_0x009e:
            if (r3 == 0) goto L_0x00a7
            int r1 = r5 << 3
            r1 = r1 | 2
            r4.zza((int) r1, (java.lang.Object) r3)
        L_0x00a7:
            r13 = r0
            goto L_0x001c
        L_0x00aa:
            if (r13 == r14) goto L_0x00b1
            com.google.android.gms.internal.measurement.zzfm r0 = com.google.android.gms.internal.measurement.zzfm.zzg()
            throw r0
        L_0x00b1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgs.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzdr):void");
    }

    public final void zza(T t, zzhc zzhc, zzer zzer) throws IOException {
        boolean z;
        zzhx<?, ?> zzhx = this.zzb;
        zzet<?> zzet = this.zzd;
        Object zzc2 = zzhx.zzc(t);
        zzeu<?> zzb2 = zzet.zzb(t);
        do {
            try {
                if (zzhc.zza() == Integer.MAX_VALUE) {
                    zzhx.zzb((Object) t, zzc2);
                    return;
                }
                int zzb3 = zzhc.zzb();
                if (zzb3 == 11) {
                    int i = 0;
                    Object obj = null;
                    zzdw zzdw = null;
                    while (zzhc.zza() != Integer.MAX_VALUE) {
                        int zzb4 = zzhc.zzb();
                        if (zzb4 == 16) {
                            i = zzhc.zzo();
                            obj = zzet.zza(zzer, this.zza, i);
                        } else if (zzb4 == 26) {
                            if (obj != null) {
                                zzet.zza(zzhc, obj, zzer, zzb2);
                            } else {
                                zzdw = zzhc.zzn();
                            }
                        } else if (!zzhc.zzc()) {
                            break;
                        }
                    }
                    if (zzhc.zzb() != 12) {
                        throw zzfm.zze();
                    } else if (zzdw != null) {
                        if (obj != null) {
                            zzet.zza(zzdw, obj, zzer, zzb2);
                        } else {
                            zzhx.zza(zzc2, i, zzdw);
                        }
                    }
                } else if ((zzb3 & 7) == 2) {
                    Object zza2 = zzet.zza(zzer, this.zza, zzb3 >>> 3);
                    if (zza2 != null) {
                        zzet.zza(zzhc, zza2, zzer, zzb2);
                    } else {
                        z = zzhx.zza(zzc2, zzhc);
                        continue;
                    }
                } else {
                    z = zzhc.zzc();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzhx.zzb((Object) t, zzc2);
            }
        } while (z);
    }

    public final void zzc(T t) {
        this.zzb.zzd(t);
        this.zzd.zzc(t);
    }

    public final boolean zzd(T t) {
        return this.zzd.zza((Object) t).zzf();
    }

    public final int zzb(T t) {
        zzhx<?, ?> zzhx = this.zzb;
        int zze = zzhx.zze(zzhx.zzb(t)) + 0;
        if (this.zzc) {
            return zze + this.zzd.zza((Object) t).zzg();
        }
        return zze;
    }
}
