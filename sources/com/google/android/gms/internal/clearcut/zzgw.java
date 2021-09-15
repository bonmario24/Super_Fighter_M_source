package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.util.List;

public final class zzgw {

    public static final class zza extends zzcg<zza, C1790zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        /* access modifiers changed from: private */
        public static final zza zzbir = new zza();
        private zzcn<zzb> zzbiq = zzbb();

        /* renamed from: com.google.android.gms.internal.clearcut.zzgw$zza$zza  reason: collision with other inner class name */
        public static final class C1790zza extends zzcg.zza<zza, C1790zza> implements zzdq {
            private C1790zza() {
                super(zza.zzbir);
            }

            /* synthetic */ C1790zza(zzgx zzgx) {
                this();
            }
        }

        public static final class zzb extends zzcg<zzb, C1791zza> implements zzdq {
            private static volatile zzdz<zzb> zzbg;
            /* access modifiers changed from: private */
            public static final zzb zzbiv = new zzb();
            private int zzbb;
            private String zzbis = "";
            private long zzbit;
            private long zzbiu;
            private int zzya;

            /* renamed from: com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza  reason: collision with other inner class name */
            public static final class C1791zza extends zzcg.zza<zzb, C1791zza> implements zzdq {
                private C1791zza() {
                    super(zzb.zzbiv);
                }

                /* synthetic */ C1791zza(zzgx zzgx) {
                    this();
                }

                public final C1791zza zzn(String str) {
                    zzbf();
                    ((zzb) this.zzjt).zzm(str);
                    return this;
                }

                public final C1791zza zzr(long j) {
                    zzbf();
                    ((zzb) this.zzjt).zzp(j);
                    return this;
                }

                public final C1791zza zzs(long j) {
                    zzbf();
                    ((zzb) this.zzjt).zzq(j);
                    return this;
                }
            }

            static {
                zzcg.zza(zzb.class, zzbiv);
            }

            private zzb() {
            }

            public static C1791zza zzfz() {
                return (C1791zza) ((zzcg.zza) zzbiv.zza(zzcg.zzg.zzkh, (Object) null, (Object) null));
            }

            /* access modifiers changed from: private */
            public final void zzm(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.zzbb |= 2;
                this.zzbis = str;
            }

            /* access modifiers changed from: private */
            public final void zzp(long j) {
                this.zzbb |= 4;
                this.zzbit = j;
            }

            /* access modifiers changed from: private */
            public final void zzq(long j) {
                this.zzbb |= 8;
                this.zzbiu = j;
            }

            public final int getEventCode() {
                return this.zzya;
            }

            /* JADX WARNING: type inference failed for: r0v8, types: [com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb>, com.google.android.gms.internal.clearcut.zzcg$zzb] */
            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                zzdz<zzb> zzdz;
                switch (zzgx.zzba[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new C1791zza((zzgx) null);
                    case 3:
                        return zza((zzdo) zzbiv, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0002\u0003", new Object[]{"zzbb", "zzya", "zzbis", "zzbit", "zzbiu"});
                    case 4:
                        return zzbiv;
                    case 5:
                        zzdz<zzb> zzdz2 = zzbg;
                        if (zzdz2 != null) {
                            return zzdz2;
                        }
                        synchronized (zzb.class) {
                            zzdz<zzb> zzdz3 = zzbg;
                            zzdz = zzdz3;
                            if (zzdz3 == null) {
                                ? zzb = new zzcg.zzb(zzbiv);
                                zzbg = zzb;
                                zzdz = zzb;
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

            public final boolean zzfv() {
                return (this.zzbb & 1) == 1;
            }

            public final String zzfw() {
                return this.zzbis;
            }

            public final long zzfx() {
                return this.zzbit;
            }

            public final long zzfy() {
                return this.zzbiu;
            }
        }

        static {
            zzcg.zza(zza.class, zzbir);
        }

        private zza() {
        }

        public static zza zzft() {
            return zzbir;
        }

        public static zza zzi(byte[] bArr) throws zzco {
            return (zza) zzcg.zzb(zzbir, bArr);
        }

        /* JADX WARNING: type inference failed for: r0v8, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzdz<zza> zzdz;
            switch (zzgx.zzba[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C1790zza((zzgx) null);
                case 3:
                    return zza((zzdo) zzbir, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzbiq", zzb.class});
                case 4:
                    return zzbir;
                case 5:
                    zzdz<zza> zzdz2 = zzbg;
                    if (zzdz2 != null) {
                        return zzdz2;
                    }
                    synchronized (zza.class) {
                        zzdz<zza> zzdz3 = zzbg;
                        zzdz = zzdz3;
                        if (zzdz3 == null) {
                            ? zzb2 = new zzcg.zzb(zzbir);
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

        public final List<zzb> zzfs() {
            return this.zzbiq;
        }
    }
}
