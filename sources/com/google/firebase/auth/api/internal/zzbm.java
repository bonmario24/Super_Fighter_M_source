package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzcu;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzb;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzp;

@VisibleForTesting
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzbm extends zzff<AuthResult, zzb> {
    private final EmailAuthCredential zza;

    public zzbm(EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zza = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
        Preconditions.checkNotEmpty(emailAuthCredential.zzb(), "email cannot be null");
        Preconditions.checkNotEmpty(emailAuthCredential.zzc(), "password cannot be null");
    }

    public final String zza() {
        return "linkEmailAuthCredential";
    }

    public final TaskApiCall<zzeh, AuthResult> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{zze.zza}).run(new zzbp(this)).build();
    }

    public final void zze() {
        zzp zza2 = zzas.zza(this.zzd, this.zzl);
        ((zzb) this.zzf).zza(this.zzk, zza2);
        zzb(new zzj(zza2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzeh zzeh, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzh = new zzfm(this, taskCompletionSource);
        if (this.zzu) {
            zzeh.zza().zza(this.zza.zzb(), this.zza.zzc(), this.zze.zzf(), this.zzc);
        } else {
            zzeh.zza().zza(new zzcu(this.zza.zzb(), this.zza.zzc(), this.zze.zzf()), (zzem) this.zzc);
        }
    }
}
