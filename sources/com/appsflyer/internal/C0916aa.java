package com.appsflyer.internal;

import android.content.Context;
import android.util.Log;
import com.appsflyer.AppsFlyerLibCore;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.appsflyer.internal.aa */
public final class C0916aa {

    /* renamed from: ɩ */
    private static C0916aa f504 = new C0916aa();

    private C0916aa() {
    }

    /* renamed from: ǃ */
    public static C0916aa m315() {
        return f504;
    }

    /* renamed from: ι */
    public static File m318(Context context) {
        return new File(context.getFilesDir(), "AFRequestCache");
    }

    /* renamed from: ǃ */
    public static List<C0945j> m316(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(context.getFilesDir(), "AFRequestCache");
            if (!file.exists()) {
                file.mkdir();
            } else {
                for (File file2 : file.listFiles()) {
                    Log.i(AppsFlyerLibCore.LOG_TAG, new StringBuilder("Found cached request").append(file2.getName()).toString());
                    arrayList.add(m314(file2));
                }
            }
        } catch (Exception e) {
            Log.i(AppsFlyerLibCore.LOG_TAG, "Could not cache request");
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0023 A[SYNTHETIC, Splitter:B:10:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c A[SYNTHETIC, Splitter:B:16:0x002c] */
    /* renamed from: ı */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.appsflyer.internal.C0945j m314(java.io.File r6) {
        /*
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x001f, all -> 0x0028 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x001f, all -> 0x0028 }
            long r4 = r6.length()     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            int r0 = (int) r4     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            char[] r3 = new char[r0]     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r2.read(r3)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            com.appsflyer.internal.j r0 = new com.appsflyer.internal.j     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            java.lang.String r3 = r6.getName()     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r0.f607 = r3     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r2.close()     // Catch:{ IOException -> 0x0030 }
        L_0x001e:
            return r0
        L_0x001f:
            r0 = move-exception
            r0 = r1
        L_0x0021:
            if (r0 == 0) goto L_0x0026
            r0.close()     // Catch:{ IOException -> 0x0032 }
        L_0x0026:
            r0 = r1
            goto L_0x001e
        L_0x0028:
            r0 = move-exception
            r2 = r1
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x002f:
            throw r0
        L_0x0030:
            r1 = move-exception
            goto L_0x001e
        L_0x0032:
            r0 = move-exception
            goto L_0x0026
        L_0x0034:
            r1 = move-exception
            goto L_0x002f
        L_0x0036:
            r0 = move-exception
            goto L_0x002a
        L_0x0038:
            r0 = move-exception
            r0 = r2
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0916aa.m314(java.io.File):com.appsflyer.internal.j");
    }

    /* renamed from: ǃ */
    public static void m317(String str, Context context) {
        File file = new File(new File(context.getFilesDir(), "AFRequestCache"), str);
        Log.i(AppsFlyerLibCore.LOG_TAG, new StringBuilder("Deleting ").append(str).append(" from cache").toString());
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                Log.i(AppsFlyerLibCore.LOG_TAG, new StringBuilder("Could not delete ").append(str).append(" from cache").toString(), e);
            }
        }
    }
}
