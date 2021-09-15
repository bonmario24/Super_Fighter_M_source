package com.google.android.gms.internal.p037authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzm */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.1.0 */
final class zzm extends zzg {
    private final /* synthetic */ TaskCompletionSource zza;

    zzm(zzi zzi, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zza(Status status) {
        TaskUtil.setResultOrApiException(status, this.zza);
    }
}
