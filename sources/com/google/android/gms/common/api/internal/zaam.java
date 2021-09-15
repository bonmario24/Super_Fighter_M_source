package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zaam implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final Api<?> mApi;
    /* access modifiers changed from: private */
    public final boolean zaee;
    private final WeakReference<zaak> zago;

    public zaam(zaak zaak, Api<?> api, boolean z) {
        this.zago = new WeakReference<>(zaak);
        this.mApi = api;
        this.zaee = z;
    }

    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        boolean z = false;
        zaak zaak = (zaak) this.zago.get();
        if (zaak != null) {
            if (Looper.myLooper() == zaak.zafv.zaeh.getLooper()) {
                z = true;
            }
            Preconditions.checkState(z, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaak.zaer.lock();
            try {
                if (zaak.zac(0)) {
                    if (!connectionResult.isSuccess()) {
                        zaak.zab(connectionResult, this.mApi, this.zaee);
                    }
                    if (zaak.zaam()) {
                        zaak.zaan();
                    }
                    zaak.zaer.unlock();
                }
            } finally {
                zaak.zaer.unlock();
            }
        }
    }
}
