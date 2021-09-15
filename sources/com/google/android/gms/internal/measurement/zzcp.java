package com.google.android.gms.internal.measurement;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzcp extends zzcn<Long> {
    zzcp(zzct zzct, String str, Long l) {
        super(zzct, str, l, (zzcp) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final Long zza(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException e) {
            }
        }
        String zzb = super.zzb();
        String valueOf = String.valueOf(obj);
        Log.e("PhenotypeFlag", new StringBuilder(String.valueOf(zzb).length() + 25 + String.valueOf(valueOf).length()).append("Invalid long value for ").append(zzb).append(": ").append(valueOf).toString());
        return null;
    }
}
