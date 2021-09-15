package com.google.android.gms.internal.firebase_auth;

import java.util.Arrays;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzbn<K, V> {
    private Object[] zza;
    private int zzb;
    private boolean zzc;

    public zzbn() {
        this(4);
    }

    private zzbn(int i) {
        this.zza = new Object[8];
        this.zzb = 0;
        this.zzc = false;
    }

    public final zzbn<K, V> zza(K k, V v) {
        int i = this.zzb + 1;
        if ((i << 1) > this.zza.length) {
            Object[] objArr = this.zza;
            int length = this.zza.length;
            int i2 = i << 1;
            if (i2 < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            int i3 = (length >> 1) + length + 1;
            if (i3 < i2) {
                i3 = Integer.highestOneBit(i2 - 1) << 1;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i3);
            this.zzc = false;
        }
        zzbe.zza(k, v);
        this.zza[this.zzb * 2] = k;
        this.zza[(this.zzb * 2) + 1] = v;
        this.zzb++;
        return this;
    }

    public final zzbk<K, V> zza() {
        this.zzc = true;
        return zzbo.zza(this.zzb, this.zza);
    }
}
