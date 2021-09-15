package com.appsflyer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appsflyer.internal.C0949r;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AFSensorManager {
    @VisibleForTesting
    public static volatile AFSensorManager sInstance;

    /* renamed from: ȷ */
    private static final BitSet f381 = new BitSet(6);

    /* renamed from: ɪ */
    private static final Handler f382 = new Handler(Looper.getMainLooper());

    /* renamed from: ı */
    final Handler f383;
    /* access modifiers changed from: package-private */

    /* renamed from: Ɩ */
    public int f384 = 1;

    /* renamed from: ǃ */
    final Map<C0949r, C0949r> f385 = new HashMap(f381.size());
    /* access modifiers changed from: private */

    /* renamed from: ɨ */
    public final Runnable f386 = new Runnable() {
        public final void run() {
            synchronized (AFSensorManager.this.f391) {
                if (AFSensorManager.this.f384 == 0) {
                    int unused = AFSensorManager.this.f384 = 1;
                }
                AFSensorManager.this.f383.postDelayed(AFSensorManager.this.f393, 500 * ((long) AFSensorManager.this.f384));
            }
        }
    };

    /* renamed from: ɩ */
    final SensorManager f387;

    /* renamed from: ɹ */
    final Runnable f388 = new Runnable() {
        public final void run() {
            synchronized (AFSensorManager.this.f391) {
                AFSensorManager aFSensorManager = AFSensorManager.this;
                try {
                    for (Sensor next : aFSensorManager.f387.getSensorList(-1)) {
                        if (AFSensorManager.m227(next.getType())) {
                            C0949r rVar = new C0949r(next.getType(), next.getName(), next.getVendor());
                            if (!aFSensorManager.f385.containsKey(rVar)) {
                                aFSensorManager.f385.put(rVar, rVar);
                            }
                            aFSensorManager.f387.registerListener(aFSensorManager.f385.get(rVar), next, 0);
                        }
                    }
                } catch (Throwable th) {
                }
                aFSensorManager.f394 = true;
                AFSensorManager.this.f383.postDelayed(AFSensorManager.this.f386, 100);
                AFSensorManager.this.f390 = true;
            }
        }
    };

    /* renamed from: ɾ */
    long f389 = 0;

    /* renamed from: Ι */
    boolean f390;

    /* renamed from: ι */
    final Object f391 = new Object();

    /* renamed from: І */
    final Runnable f392 = new Runnable() {
        public final void run() {
            synchronized (AFSensorManager.this.f391) {
                if (AFSensorManager.this.f390) {
                    AFSensorManager.this.f383.removeCallbacks(AFSensorManager.this.f388);
                    AFSensorManager.this.f383.removeCallbacks(AFSensorManager.this.f393);
                    AFSensorManager.this.mo14518();
                    AFSensorManager.this.f390 = false;
                }
            }
        }
    };

    /* renamed from: і */
    final Runnable f393 = new Runnable() {
        public final void run() {
            synchronized (AFSensorManager.this.f391) {
                AFSensorManager.this.mo14518();
            }
        }
    };

    /* renamed from: Ӏ */
    boolean f394;

    /* renamed from: ӏ */
    private final Map<C0949r, Map<String, Object>> f395 = new ConcurrentHashMap(f381.size());

    static {
        f381.set(1);
        f381.set(2);
        f381.set(4);
    }

    private AFSensorManager(@NonNull SensorManager sensorManager, Handler handler) {
        this.f387 = sensorManager;
        this.f383 = handler;
    }

    /* renamed from: ɩ */
    static AFSensorManager m226(Context context) {
        if (sInstance != null) {
            return sInstance;
        }
        return m223((SensorManager) context.getApplicationContext().getSystemService("sensor"), f382);
    }

    /* renamed from: ǃ */
    private static AFSensorManager m223(SensorManager sensorManager, Handler handler) {
        if (sInstance == null) {
            synchronized (AFSensorManager.class) {
                if (sInstance == null) {
                    sInstance = new AFSensorManager(sensorManager, handler);
                }
            }
        }
        return sInstance;
    }

    /* renamed from: ɩ */
    static boolean m227(int i) {
        return i >= 0 && f381.get(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ι */
    public final void mo14518() {
        try {
            if (!this.f385.isEmpty()) {
                for (C0949r next : this.f385.values()) {
                    this.f387.unregisterListener(next);
                    next.mo14725(this.f395, true);
                }
            }
        } catch (Throwable th) {
        }
        this.f384 = 0;
        this.f394 = false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ɩ */
    public final List<Map<String, Object>> mo14517() {
        for (C0949r r0 : this.f385.values()) {
            r0.mo14725(this.f395, true);
        }
        if (this.f395 == null || this.f395.isEmpty()) {
            return new CopyOnWriteArrayList(Collections.emptyList());
        }
        return new CopyOnWriteArrayList(this.f395.values());
    }

    /* access modifiers changed from: package-private */
    @NonNull
    /* renamed from: ι */
    public final List<Map<String, Object>> mo14519() {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this.f391) {
            if (!this.f385.isEmpty() && this.f394) {
                for (C0949r r0 : this.f385.values()) {
                    r0.mo14725(this.f395, false);
                }
            }
            if (this.f395.isEmpty()) {
                copyOnWriteArrayList = new CopyOnWriteArrayList(Collections.emptyList());
            } else {
                copyOnWriteArrayList = new CopyOnWriteArrayList(this.f395.values());
            }
        }
        return copyOnWriteArrayList;
    }
}
