package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzmi implements zzmj {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.experiment.enable_experiment_reporting", true);

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}
