package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zafz;

    private zaau(zaak zaak) {
        this.zafz = zaak;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public abstract void zaal();

    @WorkerThread
    public void run() {
        this.zafz.zaer.lock();
        try {
            if (!Thread.interrupted()) {
                zaal();
                this.zafz.zaer.unlock();
            }
        } catch (RuntimeException e) {
            this.zafz.zafv.zab(e);
        } finally {
            this.zafz.zaer.unlock();
        }
    }

    /* synthetic */ zaau(zaak zaak, zaaj zaaj) {
        this(zaak);
    }
}
