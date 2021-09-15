package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;

/* renamed from: com.appsflyer.internal.ah */
public final class C0927ah {

    /* renamed from: Ι */
    private static String f523;

    /* renamed from: ι */
    private static String f524;

    C0927ah() {
    }

    /* renamed from: ǃ */
    public static void m335(String str) {
        f523 = str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || i == str.length() - 1) {
                sb.append(str.charAt(i));
            } else {
                sb.append("*");
            }
        }
        f524 = sb.toString();
    }

    /* renamed from: Ι */
    public static void m336(String str) {
        if (f523 == null) {
            m335(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY));
        }
        if (f523 != null && str.contains(f523)) {
            AFLogger.afInfoLog(str.replace(f523, f524));
        }
    }
}
