package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzgf implements zzgn {
    private zzgn[] zza;

    zzgf(zzgn... zzgnArr) {
        this.zza = zzgnArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzgn zza2 : this.zza) {
            if (zza2.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzgk zzb(Class<?> cls) {
        for (zzgn zzgn : this.zza) {
            if (zzgn.zza(cls)) {
                return zzgn.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
