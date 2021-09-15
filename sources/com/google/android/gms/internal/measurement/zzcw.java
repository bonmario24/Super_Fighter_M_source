package com.google.android.gms.internal.measurement;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public abstract class zzcw<T> implements Serializable {
    public static <T> zzcw<T> zzc() {
        return zzcu.zza;
    }

    public abstract boolean zza();

    public abstract T zzb();

    public static <T> zzcw<T> zza(T t) {
        return new zzcy(zzcz.zza(t));
    }

    zzcw() {
    }
}
