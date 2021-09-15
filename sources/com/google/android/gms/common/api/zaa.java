package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zaa implements PendingResult.StatusListener {
    private final /* synthetic */ Batch zabb;

    zaa(Batch batch) {
        this.zabb = batch;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onComplete(com.google.android.gms.common.api.Status r6) {
        /*
            r5 = this;
            com.google.android.gms.common.api.Batch r0 = r5.zabb
            java.lang.Object r1 = r0.mLock
            monitor-enter(r1)
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            boolean r0 = r0.isCanceled()     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0011
            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
        L_0x0010:
            return
        L_0x0011:
            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x003c
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            r2 = 1
            boolean unused = r0.zabe = true     // Catch:{ all -> 0x0039 }
        L_0x001d:
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            com.google.android.gms.common.api.Batch.zab(r0)     // Catch:{ all -> 0x0039 }
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            int r0 = r0.zabc     // Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x0037
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            boolean r0 = r0.zabe     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0049
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            com.google.android.gms.common.api.zaa.super.cancel()     // Catch:{ all -> 0x0039 }
        L_0x0037:
            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
            goto L_0x0010
        L_0x0039:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
            throw r0
        L_0x003c:
            boolean r0 = r6.isSuccess()     // Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x001d
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            r2 = 1
            boolean unused = r0.zabd = true     // Catch:{ all -> 0x0039 }
            goto L_0x001d
        L_0x0049:
            com.google.android.gms.common.api.Batch r0 = r5.zabb     // Catch:{ all -> 0x0039 }
            boolean r0 = r0.zabd     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0069
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0039 }
            r2 = 13
            r0.<init>(r2)     // Catch:{ all -> 0x0039 }
        L_0x0058:
            com.google.android.gms.common.api.Batch r2 = r5.zabb     // Catch:{ all -> 0x0039 }
            com.google.android.gms.common.api.BatchResult r3 = new com.google.android.gms.common.api.BatchResult     // Catch:{ all -> 0x0039 }
            com.google.android.gms.common.api.Batch r4 = r5.zabb     // Catch:{ all -> 0x0039 }
            com.google.android.gms.common.api.PendingResult[] r4 = r4.zabf     // Catch:{ all -> 0x0039 }
            r3.<init>(r0, r4)     // Catch:{ all -> 0x0039 }
            r2.setResult(r3)     // Catch:{ all -> 0x0039 }
            goto L_0x0037
        L_0x0069:
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.RESULT_SUCCESS     // Catch:{ all -> 0x0039 }
            goto L_0x0058
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.zaa.onComplete(com.google.android.gms.common.api.Status):void");
    }
}
