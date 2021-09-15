package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzkg implements zzkh {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Long> zzb;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.sdk.referrer.delayed_install_referrer_api", false);
        zzb = zzct.zza("measurement.id.sdk.referrer.delayed_install_referrer_api", 0);
    }
}
