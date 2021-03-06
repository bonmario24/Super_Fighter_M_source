package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.internal.firebase_auth.zzea;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzb;

@VisibleForTesting
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzdi extends zzff<Void, zzb> {
    private final zzea zza;

    public zzdi(String str, String str2) {
        super(2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(str2);
        this.zza = new zzea(str, str2);
    }

    public final String zza() {
        return "unenrollMfa";
    }

    public final TaskApiCall<zzeh, Void> zzb() {
        return TaskApiCall.builder().setFeatures(zze.zzb).setAutoResolveMissingFeatures(false).run(new zzdl(this)).build();
    }

    public final void zze() {
        ((zzb) this.zzf).zza(this.zzk, zzas.zza(this.zzd, this.zzl));
        zzb(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzeh zzeh, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzh = new zzfm(this, taskCompletionSource);
        zzeh.zza().zza(this.zza, (zzem) this.zzc);
    }
}
