package com.google.android.gms.internal.measurement;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzco extends zzcn<Boolean> {
    zzco(zzct zzct, String str, Boolean bool) {
        super(zzct, str, bool, (zzcp) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzbv.zzb.matcher(str).matches()) {
                return true;
            }
            if (zzbv.zzc.matcher(str).matches()) {
                return false;
            }
        }
        String zzb = super.zzb();
        String valueOf = String.valueOf(obj);
        Log.e("PhenotypeFlag", new StringBuilder(String.valueOf(zzb).length() + 28 + String.valueOf(valueOf).length()).append("Invalid boolean value for ").append(zzb).append(": ").append(valueOf).toString());
        return null;
    }
}
