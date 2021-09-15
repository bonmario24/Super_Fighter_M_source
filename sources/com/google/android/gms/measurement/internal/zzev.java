package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzev implements Runnable {
    private final /* synthetic */ int zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ Object zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ zzes zzf;

    zzev(zzes zzes, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzes;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    public final void run() {
        zzfe zzc2 = this.zzf.zzz.zzc();
        if (zzc2.zzz()) {
            if (this.zzf.zza == 0) {
                if (this.zzf.zzt().zzg()) {
                    zzes zzes = this.zzf;
                    this.zzf.zzu();
                    char unused = zzes.zza = 'C';
                } else {
                    zzes zzes2 = this.zzf;
                    this.zzf.zzu();
                    char unused2 = zzes2.zza = 'c';
                }
            }
            if (this.zzf.zzb < 0) {
                long unused3 = this.zzf.zzb = this.zzf.zzt().zzf();
            }
            char charAt = "01VDIWEA?".charAt(this.zza);
            char zza2 = this.zzf.zza;
            long zzb2 = this.zzf.zzb;
            String zza3 = zzes.zza(true, this.zzb, this.zzc, this.zzd, this.zze);
            String sb = new StringBuilder(String.valueOf(zza3).length() + 24).append("2").append(charAt).append(zza2).append(zzb2).append(":").append(zza3).toString();
            if (sb.length() > 1024) {
                sb = this.zzb.substring(0, 1024);
            }
            zzc2.zzb.zza(sb, 1);
            return;
        }
        this.zzf.zza(6, "Persisted config not initialized. Not logging error/warn");
    }
}
