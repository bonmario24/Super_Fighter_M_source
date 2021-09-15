package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
abstract class zzae<T> implements Iterator<T> {
    private int zza = zzag.zzb;
    @NullableDecl
    private T zzb;

    protected zzae() {
    }

    /* access modifiers changed from: protected */
    public abstract T zza();

    /* access modifiers changed from: protected */
    @NullableDecl
    public final T zzb() {
        this.zza = zzag.zzc;
        return null;
    }

    public final boolean hasNext() {
        boolean z;
        if (this.zza != zzag.zzd) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            throw new IllegalStateException();
        }
        switch (zzad.zza[this.zza - 1]) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                this.zza = zzag.zzd;
                this.zzb = zza();
                if (this.zza == zzag.zzc) {
                    return false;
                }
                this.zza = zzag.zza;
                return true;
        }
    }

    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.zza = zzag.zzb;
        T t = this.zzb;
        this.zzb = null;
        return t;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
