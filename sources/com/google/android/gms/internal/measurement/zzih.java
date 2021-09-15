package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
abstract class zzih {
    zzih() {
    }

    /* access modifiers changed from: package-private */
    public abstract int zza(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract int zza(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract String zza(byte[] bArr, int i, int i2) throws zzfm;

    /* access modifiers changed from: package-private */
    public final boolean zzb(byte[] bArr, int i, int i2) {
        return zza(0, bArr, i, i2) == 0;
    }
}
