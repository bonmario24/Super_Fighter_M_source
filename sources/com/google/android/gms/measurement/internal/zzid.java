package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.List;
import java.util.Map;

@WorkerThread
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzid implements Runnable {
    private final URL zza;
    private final byte[] zzb = null;
    private final zzia zzc;
    private final String zzd;
    private final Map<String, String> zze;
    private final /* synthetic */ zzib zzf;

    public zzid(zzib zzib, String str, URL url, byte[] bArr, Map<String, String> map, zzia zzia) {
        this.zzf = zzib;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzia);
        this.zza = url;
        this.zzc = zzia;
        this.zzd = str;
        this.zze = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            r2 = 0
            com.google.android.gms.measurement.internal.zzib r0 = r6.zzf
            r0.zzc()
            r3 = 0
            com.google.android.gms.measurement.internal.zzib r0 = r6.zzf     // Catch:{ IOException -> 0x006c, all -> 0x005b }
            java.net.URL r1 = r6.zza     // Catch:{ IOException -> 0x006c, all -> 0x005b }
            java.net.HttpURLConnection r4 = r0.zza((java.net.URL) r1)     // Catch:{ IOException -> 0x006c, all -> 0x005b }
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.zze     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            if (r0 == 0) goto L_0x0044
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.zze     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.util.Iterator r5 = r0.iterator()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
        L_0x001d:
            boolean r0 = r5.hasNext()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            if (r0 == 0) goto L_0x0044
            java.lang.Object r0 = r5.next()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.lang.Object r1 = r0.getKey()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            r4.addRequestProperty(r1, r0)     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            goto L_0x001d
        L_0x0039:
            r0 = move-exception
            r1 = r2
        L_0x003b:
            if (r4 == 0) goto L_0x0040
            r4.disconnect()
        L_0x0040:
            r6.zzb(r3, r0, r2, r1)
        L_0x0043:
            return
        L_0x0044:
            int r3 = r4.getResponseCode()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            java.util.Map r1 = r4.getHeaderFields()     // Catch:{ IOException -> 0x0039, all -> 0x0067 }
            com.google.android.gms.measurement.internal.zzib r0 = r6.zzf     // Catch:{ IOException -> 0x0070, all -> 0x006a }
            byte[] r0 = com.google.android.gms.measurement.internal.zzib.zza((java.net.HttpURLConnection) r4)     // Catch:{ IOException -> 0x0070, all -> 0x006a }
            if (r4 == 0) goto L_0x0057
            r4.disconnect()
        L_0x0057:
            r6.zzb(r3, r2, r0, r1)
            goto L_0x0043
        L_0x005b:
            r0 = move-exception
            r1 = r2
            r4 = r2
        L_0x005e:
            if (r4 == 0) goto L_0x0063
            r4.disconnect()
        L_0x0063:
            r6.zzb(r3, r2, r2, r1)
            throw r0
        L_0x0067:
            r0 = move-exception
            r1 = r2
            goto L_0x005e
        L_0x006a:
            r0 = move-exception
            goto L_0x005e
        L_0x006c:
            r0 = move-exception
            r1 = r2
            r4 = r2
            goto L_0x003b
        L_0x0070:
            r0 = move-exception
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzid.run():void");
    }

    private final void zzb(int i, Exception exc, byte[] bArr, Map<String, List<String>> map) {
        this.zzf.zzq().zza((Runnable) new zzic(this, i, exc, bArr, map));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        this.zzc.zza(this.zzd, i, exc, bArr, map);
    }
}
