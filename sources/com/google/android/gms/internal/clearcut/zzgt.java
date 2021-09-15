package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

public final class zzgt {

    public static final class zza extends zzcg<zza, C1789zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        /* access modifiers changed from: private */
        public static final zza zzbil = new zza();

        /* renamed from: com.google.android.gms.internal.clearcut.zzgt$zza$zza  reason: collision with other inner class name */
        public static final class C1789zza extends zzcg.zza<zza, C1789zza> implements zzdq {
            private C1789zza() {
                super(zza.zzbil);
            }

            /* synthetic */ C1789zza(zzgu zzgu) {
                this();
            }
        }

        public enum zzb implements zzcj {
            NO_RESTRICTION(0),
            SIDEWINDER_DEVICE(1),
            LATCHSKY_DEVICE(2);
            
            private static final zzck<zzb> zzbq = null;
            private final int value;

            static {
                zzbq = new zzgv();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzbe(int i) {
                switch (i) {
                    case 0:
                        return NO_RESTRICTION;
                    case 1:
                        return SIDEWINDER_DEVICE;
                    case 2:
                        return LATCHSKY_DEVICE;
                    default:
                        return null;
                }
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            public final int zzc() {
                return this.value;
            }
        }

        static {
            zzcg.zza(zza.class, zzbil);
        }

        private zza() {
        }

        /* JADX WARNING: type inference failed for: r0v9, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zza> zzdz;
            switch (zzgu.zzba[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C1789zza((zzgu) null);
                case 3:
                    return zza((zzdo) zzbil, "\u0001\u0000", (Object[]) null);
                case 4:
                    return zzbil;
                case 5:
                    zzdz<zza> zzdz2 = zzbg;
                    if (zzdz2 != null) {
                        return zzdz2;
                    }
                    synchronized (zza.class) {
                        zzdz<zza> zzdz3 = zzbg;
                        zzdz = zzdz3;
                        if (zzdz3 == null) {
                            ? zzb2 = new zzcg.zzb(zzbil);
                            zzbg = zzb2;
                            zzdz = zzb2;
                        }
                    }
                    return zzdz;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
