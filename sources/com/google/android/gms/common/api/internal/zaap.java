package com.google.android.gms.common.api.internal;

import androidx.annotation.BinderThread;
import com.google.android.gms.signin.internal.zad;
import com.google.android.gms.signin.internal.zak;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zaap extends zad {
    private final WeakReference<zaak> zago;

    zaap(zaak zaak) {
        this.zago = new WeakReference<>(zaak);
    }

    @BinderThread
    public final void zab(zak zak) {
        zaak zaak = (zaak) this.zago.get();
        if (zaak != null) {
            zaak.zafv.zaa(new zaas(this, zaak, zaak, zak));
        }
    }
}
