package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.b */
class C0767b {

    /* renamed from: a */
    protected C0784m f231a = null;

    /* renamed from: b */
    protected C0770d f232b = null;

    /* renamed from: c */
    protected Context f233c = null;

    /* renamed from: d */
    protected String f234d = null;

    /* renamed from: e */
    protected String f235e = "";

    C0767b(String str, C0770d dVar) {
        this.f235e = str;
        this.f232b = dVar;
    }

    /* access modifiers changed from: protected */
    public void reportError(String str) {
        if (this.f232b != null) {
            this.f232b.reportError(this.f235e + " Error [" + this.f234d + "]", str);
        } else {
            C0771e.Log(6, this.f235e + " Error [" + this.f234d + "]: " + str);
        }
    }

    /* access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        if (this.f233c instanceof Activity) {
            ((Activity) this.f233c).runOnUiThread(runnable);
        } else {
            C0771e.Log(5, "Not running " + this.f235e + " from an Activity; Ignoring execution request...");
        }
    }

    /* access modifiers changed from: protected */
    public boolean runOnUiThreadWithSync(final Runnable runnable) {
        boolean z = false;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        final Semaphore semaphore = new Semaphore(0);
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    C0767b.this.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                } finally {
                    semaphore.release();
                }
            }
        });
        try {
            if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                reportError("Timeout waiting for vr state change!");
            } else {
                z = true;
            }
        } catch (InterruptedException e) {
            reportError("Interrupted while trying to acquire sync lock. " + e.getLocalizedMessage());
        }
        return z;
    }
}
