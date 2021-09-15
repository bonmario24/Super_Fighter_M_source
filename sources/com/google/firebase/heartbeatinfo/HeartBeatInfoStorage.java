package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;

/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
class HeartBeatInfoStorage {
    private static final String GLOBAL = "fire-global";
    private static HeartBeatInfoStorage instance = null;
    private static final String preferencesName = "FirebaseAppHeartBeat";
    private final SharedPreferences sharedPreferences;

    private HeartBeatInfoStorage(Context applicationContext) {
        this.sharedPreferences = applicationContext.getSharedPreferences(preferencesName, 0);
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.TESTS})
    HeartBeatInfoStorage(SharedPreferences preferences) {
        this.sharedPreferences = preferences;
    }

    static synchronized HeartBeatInfoStorage getInstance(Context applicationContext) {
        HeartBeatInfoStorage heartBeatInfoStorage;
        synchronized (HeartBeatInfoStorage.class) {
            if (instance == null) {
                instance = new HeartBeatInfoStorage(applicationContext);
            }
            heartBeatInfoStorage = instance;
        }
        return heartBeatInfoStorage;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean shouldSendSdkHeartBeat(String heartBeatTag, long millis) {
        boolean z = true;
        synchronized (this) {
            if (!this.sharedPreferences.contains(heartBeatTag)) {
                this.sharedPreferences.edit().putLong(heartBeatTag, millis).apply();
            } else if (millis - this.sharedPreferences.getLong(heartBeatTag, -1) >= 86400000) {
                this.sharedPreferences.edit().putLong(heartBeatTag, millis).apply();
            } else {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean shouldSendGlobalHeartBeat(long millis) {
        return shouldSendSdkHeartBeat(GLOBAL, millis);
    }
}
