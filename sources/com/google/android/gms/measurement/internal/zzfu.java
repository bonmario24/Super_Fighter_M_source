package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzfu<V> extends FutureTask<V> implements Comparable<zzfu<V>> {
    final boolean zza;
    private final long zzb = zzft.zzj.getAndIncrement();
    private final String zzc;
    private final /* synthetic */ zzft zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfu(zzft zzft, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzd = zzft;
        Preconditions.checkNotNull(str);
        this.zzc = str;
        this.zza = z;
        if (this.zzb == Long.MAX_VALUE) {
            zzft.zzr().zzf().zza("Tasks index overflow");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfu(zzft zzft, Runnable runnable, boolean z, String str) {
        super(runnable, (Object) null);
        this.zzd = zzft;
        Preconditions.checkNotNull(str);
        this.zzc = str;
        this.zza = false;
        if (this.zzb == Long.MAX_VALUE) {
            zzft.zzr().zzf().zza("Tasks index overflow");
        }
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zzd.zzr().zzf().zza(this.zzc, th);
        if (th instanceof zzfs) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        zzfu zzfu = (zzfu) obj;
        if (this.zza != zzfu.zza) {
            if (this.zza) {
                return -1;
            }
            return 1;
        } else if (this.zzb < zzfu.zzb) {
            return -1;
        } else {
            if (this.zzb > zzfu.zzb) {
                return 1;
            }
            this.zzd.zzr().zzg().zza("Two tasks share the same index. index", Long.valueOf(this.zzb));
            return 0;
        }
    }
}
