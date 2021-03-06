package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzig;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzz extends zzig<zzz, zza> implements zzjt {
    /* access modifiers changed from: private */
    public static final zzz zzac;
    private static volatile zzkb<zzz> zzad;
    private zzio<zzr> zzaa = zzad();
    private zzkw zzab;
    private int zzc;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";
    private zzio<String> zzg = zzig.zzad();
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private zzgv zzl = zzgv.zza;
    private zzgv zzm = zzgv.zza;
    private int zzn;
    private boolean zzo;
    private long zzp;
    private zzio<zzu> zzq = zzad();
    private long zzr;
    private boolean zzs;
    private long zzt;
    private long zzu;
    private String zzv = "";
    private boolean zzw;
    private String zzx = "";
    private String zzy = "";
    private String zzz = "";

    private zzz() {
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    public static final class zza extends zzig.zzb<zzz, zza> implements zzjt {
        private zza() {
            super(zzz.zzac);
        }

        /* synthetic */ zza(zzy zzy) {
            this();
        }
    }

    public final String zza() {
        return this.zzd;
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzi;
    }

    public final boolean zze() {
        return this.zzo;
    }

    public final List<zzu> zzf() {
        return this.zzq;
    }

    public final long zzg() {
        return this.zzt;
    }

    public final long zzh() {
        return this.zzu;
    }

    public final String zzi() {
        return this.zzx;
    }

    public final String zzj() {
        return this.zzy;
    }

    public final List<zzr> zzk() {
        return this.zzaa;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzkb zzkb;
        switch (zzy.zza[i - 1]) {
            case 1:
                return new zzz();
            case 2:
                return new zza((zzy) null);
            case 3:
                return zza((zzjr) zzac, "\u0001\u0019\u0000\u0001\u0001\u001c\u0019\u0000\u0003\u0000\u0001???\u0000\u0002???\u0001\u0003???\u0002\u0004\u001a\u0005???\u0003\u0006???\u0004\u0007???\u0005\b???\u0006\t???\u0007\n???\b\u000b???\t\f???\n\r???\u000b\u000e\u001b\u000f???\f\u0010???\r\u0011???\u000e\u0012???\u000f\u0013???\u0010\u0014???\u0011\u0015???\u0012\u0016???\u0013\u0019???\u0014\u001a\u001b\u001c???\u0015", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zzu.class, "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", zzr.class, "zzab"});
            case 4:
                return zzac;
            case 5:
                zzkb<zzz> zzkb2 = zzad;
                if (zzkb2 != null) {
                    return zzkb2;
                }
                synchronized (zzz.class) {
                    zzkb = zzad;
                    if (zzkb == null) {
                        zzkb = new zzig.zza(zzac);
                        zzad = zzkb;
                    }
                }
                return zzkb;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        zzz zzz2 = new zzz();
        zzac = zzz2;
        zzig.zza(zzz.class, zzz2);
    }
}
