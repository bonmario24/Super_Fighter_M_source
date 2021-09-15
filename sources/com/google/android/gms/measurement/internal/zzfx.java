package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzfx extends Thread {
    private final Object zza;
    private final BlockingQueue<zzfu<?>> zzb;
    @GuardedBy("threadLifeCycleLock")
    private boolean zzc = false;
    private final /* synthetic */ zzft zzd;

    public zzfx(zzft zzft, String str, BlockingQueue<zzfu<?>> blockingQueue) {
        this.zzd = zzft;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zza = new Object();
        this.zzb = blockingQueue;
        setName(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0077, code lost:
        zzb();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            if (r1 != 0) goto L_0x0015
            com.google.android.gms.measurement.internal.zzft r0 = r6.zzd     // Catch:{ InterruptedException -> 0x0010 }
            java.util.concurrent.Semaphore r0 = r0.zzh     // Catch:{ InterruptedException -> 0x0010 }
            r0.acquire()     // Catch:{ InterruptedException -> 0x0010 }
            r0 = 1
            r1 = r0
            goto L_0x0002
        L_0x0010:
            r0 = move-exception
            r6.zza(r0)
            goto L_0x0002
        L_0x0015:
            int r0 = android.os.Process.myTid()     // Catch:{ all -> 0x0033 }
            int r2 = android.os.Process.getThreadPriority(r0)     // Catch:{ all -> 0x0033 }
        L_0x001d:
            java.util.concurrent.BlockingQueue<com.google.android.gms.measurement.internal.zzfu<?>> r0 = r6.zzb     // Catch:{ all -> 0x0033 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0033 }
            com.google.android.gms.measurement.internal.zzfu r0 = (com.google.android.gms.measurement.internal.zzfu) r0     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x003b
            boolean r1 = r0.zza     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x0038
            r1 = r2
        L_0x002c:
            android.os.Process.setThreadPriority(r1)     // Catch:{ all -> 0x0033 }
            r0.run()     // Catch:{ all -> 0x0033 }
            goto L_0x001d
        L_0x0033:
            r0 = move-exception
            r6.zzb()
            throw r0
        L_0x0038:
            r1 = 10
            goto L_0x002c
        L_0x003b:
            java.lang.Object r1 = r6.zza     // Catch:{ all -> 0x0033 }
            monitor-enter(r1)     // Catch:{ all -> 0x0033 }
            java.util.concurrent.BlockingQueue<com.google.android.gms.measurement.internal.zzfu<?>> r0 = r6.zzb     // Catch:{ all -> 0x0080 }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x0080 }
            if (r0 != 0) goto L_0x0055
            com.google.android.gms.measurement.internal.zzft r0 = r6.zzd     // Catch:{ all -> 0x0080 }
            boolean r0 = r0.zzi     // Catch:{ all -> 0x0080 }
            if (r0 != 0) goto L_0x0055
            java.lang.Object r0 = r6.zza     // Catch:{ InterruptedException -> 0x007b }
            r4 = 30000(0x7530, double:1.4822E-319)
            r0.wait(r4)     // Catch:{ InterruptedException -> 0x007b }
        L_0x0055:
            monitor-exit(r1)     // Catch:{ all -> 0x0080 }
            com.google.android.gms.measurement.internal.zzft r0 = r6.zzd     // Catch:{ all -> 0x0033 }
            java.lang.Object r1 = r0.zzg     // Catch:{ all -> 0x0033 }
            monitor-enter(r1)     // Catch:{ all -> 0x0033 }
            java.util.concurrent.BlockingQueue<com.google.android.gms.measurement.internal.zzfu<?>> r0 = r6.zzb     // Catch:{ all -> 0x0085 }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x0085 }
            if (r0 != 0) goto L_0x0083
            com.google.android.gms.measurement.internal.zzft r0 = r6.zzd     // Catch:{ all -> 0x0085 }
            com.google.android.gms.measurement.internal.zzy r0 = r0.zzt()     // Catch:{ all -> 0x0085 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzaq.zzby     // Catch:{ all -> 0x0085 }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r2)     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0076
            r6.zzb()     // Catch:{ all -> 0x0085 }
        L_0x0076:
            monitor-exit(r1)     // Catch:{ all -> 0x0085 }
            r6.zzb()
            return
        L_0x007b:
            r0 = move-exception
            r6.zza(r0)     // Catch:{ all -> 0x0080 }
            goto L_0x0055
        L_0x0080:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0080 }
            throw r0     // Catch:{ all -> 0x0033 }
        L_0x0083:
            monitor-exit(r1)     // Catch:{ all -> 0x0085 }
            goto L_0x001d
        L_0x0085:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0085 }
            throw r0     // Catch:{ all -> 0x0033 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfx.run():void");
    }

    private final void zzb() {
        synchronized (this.zzd.zzg) {
            if (!this.zzc) {
                this.zzd.zzh.release();
                this.zzd.zzg.notifyAll();
                if (this == this.zzd.zza) {
                    zzfx unused = this.zzd.zza = null;
                } else if (this == this.zzd.zzb) {
                    zzfx unused2 = this.zzd.zzb = null;
                } else {
                    this.zzd.zzr().zzf().zza("Current scheduler thread is neither worker nor network");
                }
                this.zzc = true;
            }
        }
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.notifyAll();
        }
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzd.zzr().zzi().zza(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }
}
