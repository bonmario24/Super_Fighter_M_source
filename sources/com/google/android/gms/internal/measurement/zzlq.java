package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzlq implements zzlr {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.sdk.screen.manual_screen_view_logging", true);
        zzb = zzct.zza("measurement.sdk.screen.disabling_automatic_reporting", true);
    }
}
