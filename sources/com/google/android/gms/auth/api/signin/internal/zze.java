package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zze extends AsyncTaskLoader<Void> implements SignInConnectionListener {
    private Semaphore zzbg = new Semaphore(0);
    private Set<GoogleApiClient> zzbh;

    public zze(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzbh = set;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final Void loadInBackground() {
        int i = 0;
        Iterator<GoogleApiClient> it = this.zzbh.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                try {
                    this.zzbg.tryAcquire(i2, 5, TimeUnit.SECONDS);
                    return null;
                } catch (InterruptedException e) {
                    Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
                    Thread.currentThread().interrupt();
                    return null;
                }
            } else if (it.next().maybeSignIn(this)) {
                i = i2 + 1;
            } else {
                i = i2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onStartLoading() {
        this.zzbg.drainPermits();
        forceLoad();
    }

    public final void onComplete() {
        this.zzbg.release();
    }
}
