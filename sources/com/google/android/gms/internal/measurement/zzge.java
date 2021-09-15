package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public final class zzge<K, V> {
    static <K, V> void zza(zzel zzel, zzgh<K, V> zzgh, K k, V v) throws IOException {
        zzeu.zza(zzel, zzgh.zza, 1, k);
        zzeu.zza(zzel, zzgh.zzc, 2, v);
    }

    static <K, V> int zza(zzgh<K, V> zzgh, K k, V v) {
        return zzeu.zza(zzgh.zza, 1, k) + zzeu.zza(zzgh.zzc, 2, v);
    }
}
