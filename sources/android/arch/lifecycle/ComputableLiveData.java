package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T> {
    /* access modifiers changed from: private */
    public AtomicBoolean mComputing;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    /* access modifiers changed from: private */
    public AtomicBoolean mInvalid;
    @VisibleForTesting
    final Runnable mInvalidationRunnable;
    /* access modifiers changed from: private */
    public final LiveData<T> mLiveData;
    @VisibleForTesting
    final Runnable mRefreshRunnable;

    /* access modifiers changed from: protected */
    @WorkerThread
    public abstract T compute();

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(@NonNull Executor executor) {
        this.mInvalid = new AtomicBoolean(true);
        this.mComputing = new AtomicBoolean(false);
        this.mRefreshRunnable = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
            @android.support.annotation.WorkerThread
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    r6 = 1
                    r5 = 0
                L_0x0002:
                    r0 = 0
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r2 = r2.mComputing
                    boolean r2 = r2.compareAndSet(r5, r6)
                    if (r2 == 0) goto L_0x003a
                    r1 = 0
                L_0x0010:
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x0049 }
                    java.util.concurrent.atomic.AtomicBoolean r2 = r2.mInvalid     // Catch:{ all -> 0x0049 }
                    r3 = 1
                    r4 = 0
                    boolean r2 = r2.compareAndSet(r3, r4)     // Catch:{ all -> 0x0049 }
                    if (r2 == 0) goto L_0x0026
                    r0 = 1
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x0049 }
                    java.lang.Object r1 = r2.compute()     // Catch:{ all -> 0x0049 }
                    goto L_0x0010
                L_0x0026:
                    if (r0 == 0) goto L_0x0031
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this     // Catch:{ all -> 0x0049 }
                    android.arch.lifecycle.LiveData r2 = r2.mLiveData     // Catch:{ all -> 0x0049 }
                    r2.postValue(r1)     // Catch:{ all -> 0x0049 }
                L_0x0031:
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r2 = r2.mComputing
                    r2.set(r5)
                L_0x003a:
                    if (r0 == 0) goto L_0x0048
                    android.arch.lifecycle.ComputableLiveData r2 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r2 = r2.mInvalid
                    boolean r2 = r2.get()
                    if (r2 != 0) goto L_0x0002
                L_0x0048:
                    return
                L_0x0049:
                    r2 = move-exception
                    android.arch.lifecycle.ComputableLiveData r3 = android.arch.lifecycle.ComputableLiveData.this
                    java.util.concurrent.atomic.AtomicBoolean r3 = r3.mComputing
                    r3.set(r5)
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.ComputableLiveData.C08532.run():void");
            }
        };
        this.mInvalidationRunnable = new Runnable() {
            @MainThread
            public void run() {
                boolean isActive = ComputableLiveData.this.mLiveData.hasActiveObservers();
                if (ComputableLiveData.this.mInvalid.compareAndSet(false, true) && isActive) {
                    ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
                }
            }
        };
        this.mExecutor = executor;
        this.mLiveData = new LiveData<T>() {
            /* access modifiers changed from: protected */
            public void onActive() {
                ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
            }
        };
    }

    @NonNull
    public LiveData<T> getLiveData() {
        return this.mLiveData;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
    }
}
