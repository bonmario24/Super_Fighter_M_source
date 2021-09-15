package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzda {
    public static <T> zzdb<T> zza(zzdb<T> zzdb) {
        if ((zzdb instanceof zzdc) || (zzdb instanceof zzdd)) {
            return zzdb;
        }
        if (zzdb instanceof Serializable) {
            return new zzdd(zzdb);
        }
        return new zzdc(zzdb);
    }

    public static <T> zzdb<T> zza(@NullableDecl T t) {
        return new zzdf(t);
    }
}
