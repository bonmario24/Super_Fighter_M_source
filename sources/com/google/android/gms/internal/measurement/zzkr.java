package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzkr implements zzko {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.client.sessions.check_on_reset_and_enable2", true);
        zzb = zzct.zza("measurement.client.sessions.check_on_startup", true);
        zzc = zzct.zza("measurement.client.sessions.start_session_before_view_screen", true);
    }
}
