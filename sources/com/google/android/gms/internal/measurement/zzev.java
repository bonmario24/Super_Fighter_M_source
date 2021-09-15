package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzev {
    private static final zzet<?> zza = new zzes();
    private static final zzet<?> zzb = zzc();

    private static zzet<?> zzc() {
        try {
            return (zzet) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    static zzet<?> zza() {
        return zza;
    }

    static zzet<?> zzb() {
        if (zzb != null) {
            return zzb;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
