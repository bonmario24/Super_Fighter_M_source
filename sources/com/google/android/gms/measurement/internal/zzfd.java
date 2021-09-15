package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

@WorkerThread
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzfd implements Runnable {
    private final URL zza;
    private final byte[] zzb;
    private final zzfb zzc;
    private final String zzd;
    private final Map<String, String> zze;
    private final /* synthetic */ zzez zzf;

    public zzfd(zzez zzez, String str, URL url, byte[] bArr, Map<String, String> map, zzfb zzfb) {
        this.zzf = zzez;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfb);
        this.zza = url;
        this.zzb = bArr;
        this.zzc = zzfb;
        this.zzd = str;
        this.zze = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e3 A[SYNTHETIC, Splitter:B:36:0x00e3] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r13 = this;
            r4 = 0
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf
            r0.zzc()
            r3 = 0
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf     // Catch:{ IOException -> 0x0125, all -> 0x00dc }
            java.net.URL r1 = r13.zza     // Catch:{ IOException -> 0x0125, all -> 0x00dc }
            java.net.HttpURLConnection r2 = r0.zza((java.net.URL) r1)     // Catch:{ IOException -> 0x0125, all -> 0x00dc }
            java.util.Map<java.lang.String, java.lang.String> r0 = r13.zze     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            if (r0 == 0) goto L_0x005d
            java.util.Map<java.lang.String, java.lang.String> r0 = r13.zze     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.util.Iterator r5 = r0.iterator()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
        L_0x001d:
            boolean r0 = r5.hasNext()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            if (r0 == 0) goto L_0x005d
            java.lang.Object r0 = r5.next()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.lang.Object r1 = r0.getKey()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            r2.addRequestProperty(r1, r0)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            goto L_0x001d
        L_0x0039:
            r9 = move-exception
            r11 = r4
            r8 = r3
            r0 = r4
            r1 = r2
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ IOException -> 0x00c4 }
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.disconnect()
        L_0x0048:
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzq()
            com.google.android.gms.measurement.internal.zzfa r5 = new com.google.android.gms.measurement.internal.zzfa
            java.lang.String r6 = r13.zzd
            com.google.android.gms.measurement.internal.zzfb r7 = r13.zzc
            r10 = r4
            r12 = r4
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.zza((java.lang.Runnable) r5)
        L_0x005c:
            return
        L_0x005d:
            byte[] r0 = r13.zzb     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            if (r0 == 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            com.google.android.gms.measurement.internal.zzki r0 = r0.zzg()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            byte[] r1 = r13.zzb     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            byte[] r1 = r0.zzc(r1)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            com.google.android.gms.measurement.internal.zzes r0 = r0.zzr()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzx()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.lang.String r5 = "Uploading data. size"
            int r6 = r1.length     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            r0.zza(r5, r6)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            r0 = 1
            r2.setDoOutput(r0)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.lang.String r0 = "Content-Encoding"
            java.lang.String r5 = "gzip"
            r2.addRequestProperty(r0, r5)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            int r0 = r1.length     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            r2.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            r2.connect()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.io.OutputStream r0 = r2.getOutputStream()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            r0.write(r1)     // Catch:{ IOException -> 0x012c, all -> 0x011c }
            r0.close()     // Catch:{ IOException -> 0x012c, all -> 0x011c }
        L_0x009d:
            int r3 = r2.getResponseCode()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            java.util.Map r6 = r2.getHeaderFields()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf     // Catch:{ IOException -> 0x0132, all -> 0x0121 }
            byte[] r5 = com.google.android.gms.measurement.internal.zzez.zza((java.net.HttpURLConnection) r2)     // Catch:{ IOException -> 0x0132, all -> 0x0121 }
            if (r2 == 0) goto L_0x00b0
            r2.disconnect()
        L_0x00b0:
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzft r8 = r0.zzq()
            com.google.android.gms.measurement.internal.zzfa r0 = new com.google.android.gms.measurement.internal.zzfa
            java.lang.String r1 = r13.zzd
            com.google.android.gms.measurement.internal.zzfb r2 = r13.zzc
            r7 = r4
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r8.zza((java.lang.Runnable) r0)
            goto L_0x005c
        L_0x00c4:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzez r2 = r13.zzf
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()
            java.lang.String r3 = "Error closing HTTP compressed POST connection output stream. appId"
            java.lang.String r5 = r13.zzd
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r5)
            r2.zza(r3, r5, r0)
            goto L_0x0043
        L_0x00dc:
            r0 = move-exception
            r8 = r0
            r6 = r4
            r5 = r4
            r2 = r4
        L_0x00e1:
            if (r5 == 0) goto L_0x00e6
            r5.close()     // Catch:{ IOException -> 0x0100 }
        L_0x00e6:
            if (r2 == 0) goto L_0x00eb
            r2.disconnect()
        L_0x00eb:
            com.google.android.gms.measurement.internal.zzez r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzft r9 = r0.zzq()
            com.google.android.gms.measurement.internal.zzfa r0 = new com.google.android.gms.measurement.internal.zzfa
            java.lang.String r1 = r13.zzd
            com.google.android.gms.measurement.internal.zzfb r2 = r13.zzc
            r5 = r4
            r7 = r4
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r9.zza((java.lang.Runnable) r0)
            throw r8
        L_0x0100:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzez r1 = r13.zzf
            com.google.android.gms.measurement.internal.zzes r1 = r1.zzr()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()
            java.lang.String r5 = "Error closing HTTP compressed POST connection output stream. appId"
            java.lang.String r7 = r13.zzd
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r7)
            r1.zza(r5, r7, r0)
            goto L_0x00e6
        L_0x0117:
            r0 = move-exception
            r8 = r0
            r6 = r4
            r5 = r4
            goto L_0x00e1
        L_0x011c:
            r1 = move-exception
            r8 = r1
            r6 = r4
            r5 = r0
            goto L_0x00e1
        L_0x0121:
            r0 = move-exception
            r8 = r0
            r5 = r4
            goto L_0x00e1
        L_0x0125:
            r9 = move-exception
            r11 = r4
            r8 = r3
            r0 = r4
            r1 = r4
            goto L_0x003e
        L_0x012c:
            r9 = move-exception
            r11 = r4
            r8 = r3
            r1 = r2
            goto L_0x003e
        L_0x0132:
            r9 = move-exception
            r11 = r6
            r8 = r3
            r0 = r4
            r1 = r2
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfd.run():void");
    }
}
