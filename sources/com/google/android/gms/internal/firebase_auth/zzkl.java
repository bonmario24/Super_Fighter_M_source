package com.google.android.gms.internal.firebase_auth;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzkl extends zzki<FieldDescriptorType, Object> {
    zzkl(int i) {
        super(i, (zzkl) null);
    }

    public final void zza() {
        if (!zzb()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= zzc()) {
                    break;
                }
                Map.Entry zzb = zzb(i2);
                if (((zzib) zzb.getKey()).zzd()) {
                    zzb.setValue(Collections.unmodifiableList((List) zzb.getValue()));
                }
                i = i2 + 1;
            }
            for (Map.Entry entry : zzd()) {
                if (((zzib) entry.getKey()).zzd()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
