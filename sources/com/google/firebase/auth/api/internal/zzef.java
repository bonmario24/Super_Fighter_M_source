package com.google.firebase.auth.api.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.firebase.FirebaseExceptionMapper;
import java.util.Collections;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzef implements Callable<zzan<zzew>> {
    private final zzew zza;
    private final Context zzb;

    public zzef(zzew zzew, Context context) {
        this.zza = zzew;
        this.zzb = context;
    }

    @NonNull
    private final GoogleApi<zzew> zza(boolean z, Context context) {
        zzew zzew = (zzew) this.zza.clone();
        zzew.zza = z;
        return new zzao(context, zzeu.zza, zzew, new FirebaseExceptionMapper());
    }

    public final /* synthetic */ Object call() throws Exception {
        GoogleApi<zzew> googleApi;
        int remoteVersion;
        GoogleApi<zzew> googleApi2 = null;
        if (zzec.zza == -1 || zzec.zzb == -1) {
            int localVersion = DynamiteModule.getLocalVersion(this.zzb, "com.google.firebase.auth");
            if (localVersion != 0) {
                switch (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.zzb, 12451000)) {
                    case 0:
                    case 2:
                        remoteVersion = DynamiteModule.getRemoteVersion(this.zzb, "com.google.android.gms.firebase_auth");
                        break;
                    default:
                        remoteVersion = 0;
                        break;
                }
            } else {
                remoteVersion = 1;
            }
            int unused = zzec.zza = remoteVersion;
            int unused2 = zzec.zzb = localVersion;
        }
        if (zzec.zzb != 0) {
            googleApi = zza(true, this.zzb);
        } else {
            googleApi = null;
        }
        if (zzec.zza != 0) {
            googleApi2 = zza(false, this.zzb);
        }
        return new zzan(googleApi2, googleApi, new zzap(zzec.zza, zzec.zzb, Collections.emptyMap()));
    }
}
