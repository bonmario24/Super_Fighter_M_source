package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzhj extends zzhg<FieldDescriptorType, Object> {
    zzhj(int i) {
        super(i, (zzhj) null);
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
                if (((zzew) zzb.getKey()).zzd()) {
                    zzb.setValue(Collections.unmodifiableList((List) zzb.getValue()));
                }
                i = i2 + 1;
            }
            for (Map.Entry entry : zzd()) {
                if (((zzew) entry.getKey()).zzd()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
