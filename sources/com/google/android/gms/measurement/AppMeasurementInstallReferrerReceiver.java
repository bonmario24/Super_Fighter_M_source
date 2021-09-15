package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.measurement.zzz;

@KeepForSdk
@Deprecated
/* compiled from: com.google.android.gms:play-services-measurement-api@@17.4.0 */
public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver {
    @MainThread
    public final void onReceive(@NonNull Context context, @Nullable Intent intent) {
        if (zzz.zzb(context)) {
            zzz.zza(context).zza(5, "Install Referrer Broadcast deprecated", (Object) null, (Object) null, (Object) null);
        } else {
            Log.w("FA", "Install Referrer Broadcast deprecated");
        }
    }

    public final void doStartService(@Nullable Context context, @Nullable Intent intent) {
    }

    @Nullable
    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }
}
