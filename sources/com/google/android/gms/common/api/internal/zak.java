package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zar;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class zak extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected final GoogleApiAvailability zace;
    protected volatile boolean zadh;
    protected final AtomicReference<zam> zadi;
    private final Handler zadj;

    protected zak(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    /* access modifiers changed from: protected */
    public abstract void zaa(ConnectionResult connectionResult, int i);

    /* access modifiers changed from: protected */
    public abstract void zam();

    @VisibleForTesting
    private zak(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zadi = new AtomicReference<>((Object) null);
        this.zadj = new zar(Looper.getMainLooper());
        this.zace = googleApiAvailability;
    }

    public void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, (PendingIntent) null), zaa(this.zadi.get()));
        zao();
    }

    public void onCreate(Bundle bundle) {
        zam zam;
        super.onCreate(bundle);
        if (bundle != null) {
            AtomicReference<zam> atomicReference = this.zadi;
            if (bundle.getBoolean("resolving_error", false)) {
                zam = new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
            } else {
                zam = null;
            }
            atomicReference.set(zam);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zam = this.zadi.get();
        if (zam != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zam.zap());
            bundle.putInt("failed_status", zam.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zam.getConnectionResult().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.zadh = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r8, int r9, android.content.Intent r10) {
        /*
            r7 = this;
            r5 = 18
            r1 = 13
            r2 = 1
            r3 = 0
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r0 = r7.zadi
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zam r0 = (com.google.android.gms.common.api.internal.zam) r0
            switch(r8) {
                case 1: goto L_0x0034;
                case 2: goto L_0x0018;
                default: goto L_0x0011;
            }
        L_0x0011:
            r1 = r3
        L_0x0012:
            if (r1 == 0) goto L_0x0062
            r7.zao()
        L_0x0017:
            return
        L_0x0018:
            com.google.android.gms.common.GoogleApiAvailability r1 = r7.zace
            android.app.Activity r4 = r7.getActivity()
            int r4 = r1.isGooglePlayServicesAvailable(r4)
            if (r4 != 0) goto L_0x0070
            r1 = r2
        L_0x0025:
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.common.ConnectionResult r2 = r0.getConnectionResult()
            int r2 = r2.getErrorCode()
            if (r2 != r5) goto L_0x0012
            if (r4 != r5) goto L_0x0012
            goto L_0x0017
        L_0x0034:
            r4 = -1
            if (r9 != r4) goto L_0x0039
            r1 = r2
            goto L_0x0012
        L_0x0039:
            if (r9 != 0) goto L_0x0011
            if (r10 == 0) goto L_0x0043
            java.lang.String r2 = "<<ResolutionFailureErrorDetail>>"
            int r1 = r10.getIntExtra(r2, r1)
        L_0x0043:
            com.google.android.gms.common.api.internal.zam r2 = new com.google.android.gms.common.api.internal.zam
            com.google.android.gms.common.ConnectionResult r4 = new com.google.android.gms.common.ConnectionResult
            r5 = 0
            com.google.android.gms.common.ConnectionResult r6 = r0.getConnectionResult()
            java.lang.String r6 = r6.toString()
            r4.<init>(r1, r5, r6)
            int r0 = zaa(r0)
            r2.<init>(r4, r0)
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r0 = r7.zadi
            r0.set(r2)
            r0 = r2
            r1 = r3
            goto L_0x0012
        L_0x0062:
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.common.ConnectionResult r1 = r0.getConnectionResult()
            int r0 = r0.zap()
            r7.zaa(r1, r0)
            goto L_0x0017
        L_0x0070:
            r1 = r3
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zak.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onStop() {
        super.onStop();
        this.zadh = false;
    }

    /* access modifiers changed from: protected */
    public final void zao() {
        this.zadi.set((Object) null);
        zam();
    }

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zam = new zam(connectionResult, i);
        if (this.zadi.compareAndSet((Object) null, zam)) {
            this.zadj.post(new zal(this, zam));
        }
    }

    private static int zaa(@Nullable zam zam) {
        if (zam == null) {
            return -1;
        }
        return zam.zap();
    }
}
