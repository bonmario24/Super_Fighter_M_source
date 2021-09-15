package com.google.android.gms.internal.p037authapiphone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzl */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.1.0 */
final /* synthetic */ class zzl implements RemoteCall {
    private final zzi zza;
    private final String zzb;

    zzl(zzi zzi, String str) {
        this.zza = zzi;
        this.zzb = str;
    }

    public final void accept(Object obj, Object obj2) {
        zzi zzi = this.zza;
        ((zzf) ((zzj) obj).getService()).zza(this.zzb, new zzm(zzi, (TaskCompletionSource) obj2));
    }
}
