package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzkc implements zzfb {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzka zzb;

    zzkc(zzka zzka, String str) {
        this.zzb = zzka;
        this.zza = str;
    }

    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zza(i, th, bArr, this.zza);
    }
}
