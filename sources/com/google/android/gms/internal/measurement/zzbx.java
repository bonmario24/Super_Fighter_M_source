package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzbx extends ContentObserver {
    zzbx(Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzbv.zze.set(true);
    }
}
