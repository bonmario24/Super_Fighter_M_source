package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

public final class zzgc extends zzcg.zzd<zzgc, zza> implements zzdq {
    private static volatile zzdz<zzgc> zzbg;
    /* access modifiers changed from: private */
    public static final zzgc zzsg = new zzgc();
    private byte zzsf = 2;

    public static final class zza extends zzcg.zzc<zzgc, zza> implements zzdq {
        private zza() {
            super(zzgc.zzsg);
        }

        /* synthetic */ zza(zzgd zzgd) {
            this();
        }
    }

    static {
        zzcg.zza(zzgc.class, zzsg);
    }

    private zzgc() {
    }

    public static zzgc zzer() {
        return zzsg;
    }

    /* JADX WARNING: type inference failed for: r0v13, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgc>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzdz<zzgc> zzdz;
        switch (zzgd.zzba[i - 1]) {
            case 1:
                return new zzgc();
            case 2:
                return new zza((zzgd) null);
            case 3:
                return zza((zzdo) zzsg, "\u0003\u0000", (Object[]) null);
            case 4:
                return zzsg;
            case 5:
                zzdz<zzgc> zzdz2 = zzbg;
                if (zzdz2 != null) {
                    return zzdz2;
                }
                synchronized (zzgc.class) {
                    zzdz<zzgc> zzdz3 = zzbg;
                    zzdz = zzdz3;
                    if (zzdz3 == null) {
                        ? zzb = new zzcg.zzb(zzsg);
                        zzbg = zzb;
                        zzdz = zzb;
                    }
                }
                return zzdz;
            case 6:
                return Byte.valueOf(this.zzsf);
            case 7:
                this.zzsf = (byte) (obj == null ? 0 : 1);
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
