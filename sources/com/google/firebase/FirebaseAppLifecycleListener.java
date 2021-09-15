package com.google.firebase;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
public interface FirebaseAppLifecycleListener {
    void onDeleted(String str, FirebaseOptions firebaseOptions);
}