package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzdv extends zzdx {
    private int zza = 0;
    private final int zzb = this.zzc.zza();
    private final /* synthetic */ zzdw zzc;

    zzdv(zzdw zzdw) {
        this.zzc = zzdw;
    }

    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    public final byte zza() {
        int i = this.zza;
        if (i >= this.zzb) {
            throw new NoSuchElementException();
        }
        this.zza = i + 1;
        return this.zzc.zzb(i);
    }
}
