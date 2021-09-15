package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzy extends zzz.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzz zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzy(zzz zzz, String str, String str2, Context context, Bundle bundle) {
        super(zzz);
        this.zzg = zzz;
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
    }

    public final void zza() {
        String str;
        String str2;
        String str3;
        int i;
        boolean z;
        boolean z2;
        int i2;
        try {
            List unused = this.zzg.zzf = new ArrayList();
            if (zzz.zzc(this.zzc, this.zzd)) {
                str3 = this.zzd;
                str2 = this.zzc;
                str = this.zzg.zzc;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            zzz.zzi(this.zze);
            boolean z3 = zzz.zzi.booleanValue() || str2 != null;
            zzk unused2 = this.zzg.zzr = this.zzg.zza(this.zze, z3);
            if (this.zzg.zzr == null) {
                Log.w(this.zzg.zzc, "Failed to connect to measurement client.");
                return;
            }
            int zzd2 = zzz.zzh(this.zze);
            int zze2 = zzz.zzg(this.zze);
            if (z3) {
                int max = Math.max(zzd2, zze2);
                z2 = zze2 < zzd2;
                i2 = max;
            } else {
                if (zzd2 > 0) {
                    i = zzd2;
                } else {
                    i = zze2;
                }
                if (zzd2 > 0) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = z;
                i2 = i;
            }
            this.zzg.zzr.initialize(ObjectWrapper.wrap(this.zze), new zzx(26001, (long) i2, z2, str, str2, str3, this.zzf), this.zza);
        } catch (Exception e) {
            this.zzg.zza(e, true, false);
        }
    }
}
