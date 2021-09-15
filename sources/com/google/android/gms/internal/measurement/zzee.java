package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzee {
    private final zzel zza;
    private final byte[] zzb;

    private zzee(int i) {
        this.zzb = new byte[i];
        this.zza = zzel.zza(this.zzb);
    }

    public final zzdw zza() {
        this.zza.zzb();
        return new zzeg(this.zzb);
    }

    public final zzel zzb() {
        return this.zza;
    }

    /* synthetic */ zzee(int i, zzdv zzdv) {
        this(i);
    }
}
