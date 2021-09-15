package com.google.android.gms.internal.phenotype;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzf {
    private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzbe = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    private static final Pattern zzbf = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    private static final Pattern zzbg = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    /* access modifiers changed from: private */
    public static final AtomicBoolean zzbh = new AtomicBoolean();
    private static HashMap<String, String> zzbi;
    private static final HashMap<String, Boolean> zzbj = new HashMap<>();
    private static final HashMap<String, Integer> zzbk = new HashMap<>();
    private static final HashMap<String, Long> zzbl = new HashMap<>();
    private static final HashMap<String, Float> zzbm = new HashMap<>();
    private static Object zzbn;
    private static boolean zzbo;
    private static String[] zzbp = new String[0];

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T zza(java.util.HashMap<java.lang.String, T> r2, java.lang.String r3, T r4) {
        /*
            java.lang.Class<com.google.android.gms.internal.phenotype.zzf> r1 = com.google.android.gms.internal.phenotype.zzf.class
            monitor-enter(r1)
            boolean r0 = r2.containsKey(r3)     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r2.get(r3)     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x0011
        L_0x000f:
            monitor-exit(r1)     // Catch:{ all -> 0x0016 }
        L_0x0010:
            return r0
        L_0x0011:
            r0 = r4
            goto L_0x000f
        L_0x0013:
            monitor-exit(r1)     // Catch:{ all -> 0x0016 }
            r0 = 0
            goto L_0x0010
        L_0x0016:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0016 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.phenotype.zzf.zza(java.util.HashMap, java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (zzbo == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (zzbi.isEmpty() == false) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        zzbi.putAll(zza(r9, zzbp));
        zzbo = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004e, code lost:
        if (zzbi.containsKey(r10) == false) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        r0 = zzbi.get(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
        if (r0 == null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        r2 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(android.content.ContentResolver r9, java.lang.String r10, java.lang.String r11) {
        /*
            r3 = 0
            r8 = 1
            r2 = 0
            java.lang.Class<com.google.android.gms.internal.phenotype.zzf> r1 = com.google.android.gms.internal.phenotype.zzf.class
            monitor-enter(r1)
            zza(r9)     // Catch:{ all -> 0x005d }
            java.lang.Object r6 = zzbn     // Catch:{ all -> 0x005d }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbi     // Catch:{ all -> 0x005d }
            boolean r0 = r0.containsKey(r10)     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0020
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbi     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x005d }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x001e
            r2 = r0
        L_0x001e:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
        L_0x001f:
            return r2
        L_0x0020:
            java.lang.String[] r4 = zzbp     // Catch:{ all -> 0x005d }
            int r5 = r4.length     // Catch:{ all -> 0x005d }
            r0 = r3
        L_0x0024:
            if (r0 >= r5) goto L_0x0065
            r7 = r4[r0]     // Catch:{ all -> 0x005d }
            boolean r7 = r10.startsWith(r7)     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x0062
            boolean r0 = zzbo     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x003a
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbi     // Catch:{ all -> 0x005d }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0060
        L_0x003a:
            java.lang.String[] r0 = zzbp     // Catch:{ all -> 0x005d }
            java.util.HashMap<java.lang.String, java.lang.String> r3 = zzbi     // Catch:{ all -> 0x005d }
            java.util.Map r0 = zza(r9, r0)     // Catch:{ all -> 0x005d }
            r3.putAll(r0)     // Catch:{ all -> 0x005d }
            r0 = 1
            zzbo = r0     // Catch:{ all -> 0x005d }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbi     // Catch:{ all -> 0x005d }
            boolean r0 = r0.containsKey(r10)     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0060
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbi     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x005d }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x005b
            r2 = r0
        L_0x005b:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            goto L_0x001f
        L_0x005d:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            throw r0
        L_0x0060:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            goto L_0x001f
        L_0x0062:
            int r0 = r0 + 1
            goto L_0x0024
        L_0x0065:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            android.net.Uri r1 = CONTENT_URI
            java.lang.String[] r4 = new java.lang.String[r8]
            r4[r3] = r10
            r0 = r9
            r3 = r2
            r5 = r2
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)
            if (r1 == 0) goto L_0x007b
            boolean r0 = r1.moveToFirst()     // Catch:{ all -> 0x00a0 }
            if (r0 != 0) goto L_0x0085
        L_0x007b:
            r0 = 0
            zza((java.lang.Object) r6, (java.lang.String) r10, (java.lang.String) r0)     // Catch:{ all -> 0x00a0 }
            if (r1 == 0) goto L_0x001f
            r1.close()
            goto L_0x001f
        L_0x0085:
            r0 = 1
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x00a0 }
            if (r0 == 0) goto L_0x0094
            r3 = 0
            boolean r3 = r0.equals(r3)     // Catch:{ all -> 0x00a0 }
            if (r3 == 0) goto L_0x0094
            r0 = r2
        L_0x0094:
            zza((java.lang.Object) r6, (java.lang.String) r10, (java.lang.String) r0)     // Catch:{ all -> 0x00a0 }
            if (r0 == 0) goto L_0x009a
            r2 = r0
        L_0x009a:
            if (r1 == 0) goto L_0x001f
            r1.close()
            goto L_0x001f
        L_0x00a0:
            r0 = move-exception
            if (r1 == 0) goto L_0x00a6
            r1.close()
        L_0x00a6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.phenotype.zzf.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzbe, (String[]) null, (String) null, strArr, (String) null);
        TreeMap treeMap = new TreeMap();
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    treeMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzbi == null) {
            zzbh.set(false);
            zzbi = new HashMap<>();
            zzbn = new Object();
            zzbo = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new zzg((Handler) null));
        } else if (zzbh.getAndSet(false)) {
            zzbi.clear();
            zzbj.clear();
            zzbk.clear();
            zzbl.clear();
            zzbm.clear();
            zzbn = new Object();
            zzbo = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzf.class) {
            if (obj == zzbn) {
                zzbi.put(str, str2);
            }
        }
    }

    public static boolean zza(ContentResolver contentResolver, String str, boolean z) {
        Object zzb = zzb(contentResolver);
        Boolean bool = (Boolean) zza(zzbj, str, Boolean.valueOf(z));
        if (bool != null) {
            return bool.booleanValue();
        }
        String zza = zza(contentResolver, str, (String) null);
        if (zza != null && !zza.equals("")) {
            if (zzbf.matcher(zza).matches()) {
                bool = true;
                z = true;
            } else if (zzbg.matcher(zza).matches()) {
                bool = false;
                z = false;
            } else {
                Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + zza + "\") as boolean");
            }
        }
        HashMap<String, Boolean> hashMap = zzbj;
        synchronized (zzf.class) {
            if (zzb == zzbn) {
                hashMap.put(str, bool);
                zzbi.remove(str);
            }
        }
        return z;
    }

    private static Object zzb(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzf.class) {
            zza(contentResolver);
            obj = zzbn;
        }
        return obj;
    }
}
