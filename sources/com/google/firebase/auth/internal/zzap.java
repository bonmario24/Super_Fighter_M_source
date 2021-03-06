package com.google.firebase.auth.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzbk;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.api.zza;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzap {
    private static final Logger zza = new Logger("GetTokenResultFactory", new String[0]);

    public static GetTokenResult zza(String str) {
        Map zza2;
        try {
            zza2 = zzas.zza(str);
        } catch (zza e) {
            zza.mo21223e("Error parsing token claims", e, new Object[0]);
            zza2 = zzbk.zza();
        }
        return new GetTokenResult(str, zza2);
    }
}
