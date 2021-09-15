package com.google.android.gms.internal.p037authapiphone;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzp */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.1.0 */
abstract class zzp extends TaskApiCall<zzj, Void> {
    private TaskCompletionSource<Void> zza;

    private zzp() {
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzf zzf) throws RemoteException;

    /* access modifiers changed from: protected */
    public final void zza(Status status) {
        TaskUtil.setResultOrApiException(status, this.zza);
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zza = taskCompletionSource;
        zza((zzf) ((zzj) anyClient).getService());
    }

    /* synthetic */ zzp(zzk zzk) {
        this();
    }
}
