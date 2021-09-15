package com.google.android.gms.internal.firebase_auth;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class zzaf {
    protected zzaf() {
    }

    public abstract boolean zza(char c);

    public int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        zzav.zza(i, length, FirebaseAnalytics.Param.INDEX);
        for (int i2 = i; i2 < length; i2++) {
            if (zza(charSequence.charAt(i2))) {
                return i2;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static String zzc(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }
}
