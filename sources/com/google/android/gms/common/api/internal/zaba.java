package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zaba implements ResultCallback<Status> {
    private final /* synthetic */ zaaw zagv;
    private final /* synthetic */ StatusPendingResult zahl;
    private final /* synthetic */ boolean zahn;
    private final /* synthetic */ GoogleApiClient zaho;

    zaba(zaaw zaaw, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.zagv = zaaw;
        this.zahl = statusPendingResult;
        this.zahn = z;
        this.zaho = googleApiClient;
    }

    public final /* synthetic */ void onResult(@NonNull Result result) {
        Status status = (Status) result;
        Storage.getInstance(this.zagv.mContext).zaf();
        if (status.isSuccess() && this.zagv.isConnected()) {
            this.zagv.reconnect();
        }
        this.zahl.setResult(status);
        if (this.zahn) {
            this.zaho.disconnect();
        }
    }
}
