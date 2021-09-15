package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzhl extends zzhr {
    private final /* synthetic */ zzhg zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzhl(zzhg zzhg) {
        super(zzhg, (zzhj) null);
        this.zza = zzhg;
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzhi(this.zza, (zzhj) null);
    }

    /* synthetic */ zzhl(zzhg zzhg, zzhj zzhj) {
        this(zzhg);
    }
}
