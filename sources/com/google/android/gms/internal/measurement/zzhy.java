package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public final class zzhy extends AbstractList<String> implements zzfx, RandomAccess {
    /* access modifiers changed from: private */
    public final zzfx zza;

    public zzhy(zzfx zzfx) {
        this.zza = zzfx;
    }

    public final Object zzb(int i) {
        return this.zza.zzb(i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final void zza(zzdw zzdw) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzib(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzia(this);
    }

    public final List<?> zzd() {
        return this.zza.zzd();
    }

    public final zzfx zze() {
        return this;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zza.get(i);
    }
}
