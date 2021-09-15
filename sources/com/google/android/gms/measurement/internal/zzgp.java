package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzgp implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzgb zze;

    zzgp(zzgb zzgb, String str, String str2, String str3, long j) {
        this.zze = zzgb;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
    }

    public final void run() {
        if (this.zza == null) {
            this.zze.zza.zzs().zzv().zza(this.zzb, (zzih) null);
            return;
        }
        this.zze.zza.zzs().zzv().zza(this.zzb, new zzih(this.zzc, this.zza, this.zzd));
    }
}
