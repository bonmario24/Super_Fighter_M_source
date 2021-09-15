package com.appsflyer.internal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.appsflyer.internal.r */
public final class C0949r implements SensorEventListener {
    @NonNull

    /* renamed from: ı */
    private final String f622;

    /* renamed from: Ɩ */
    private long f623;

    /* renamed from: ǃ */
    private final int f624;

    /* renamed from: ɩ */
    private final float[][] f625 = new float[2][];

    /* renamed from: Ι */
    private final long[] f626 = new long[2];
    @NonNull

    /* renamed from: ι */
    private final String f627;

    /* renamed from: І */
    private final int f628;

    /* renamed from: і */
    private double f629;

    public C0949r(int i, @Nullable String str, @Nullable String str2) {
        this.f624 = i;
        this.f622 = str == null ? "" : str;
        this.f627 = str2 == null ? "" : str2;
        this.f628 = ((this.f622.hashCode() + ((i + 31) * 31)) * 31) + this.f627.hashCode();
    }

    /* renamed from: ı */
    private static double m387(@NonNull float[] fArr, @NonNull float[] fArr2) {
        int min = Math.min(fArr.length, fArr2.length);
        double d = 0.0d;
        for (int i = 0; i < min; i++) {
            d += StrictMath.pow((double) (fArr[i] - fArr2[i]), 2.0d);
        }
        return Math.sqrt(d);
    }

    /* renamed from: Ι */
    private static boolean m391(Sensor sensor) {
        return (sensor == null || sensor.getName() == null || sensor.getVendor() == null) ? false : true;
    }

    @NonNull
    /* renamed from: ɩ */
    private static List<Float> m389(@NonNull float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float valueOf : fArr) {
            arrayList.add(Float.valueOf(valueOf));
        }
        return arrayList;
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null && sensorEvent.values != null && m391(sensorEvent.sensor)) {
            int type = sensorEvent.sensor.getType();
            String name = sensorEvent.sensor.getName();
            String vendor = sensorEvent.sensor.getVendor();
            long j = sensorEvent.timestamp;
            float[] fArr = sensorEvent.values;
            if (m390(type, name, vendor)) {
                long currentTimeMillis = System.currentTimeMillis();
                float[] fArr2 = this.f625[0];
                if (fArr2 == null) {
                    this.f625[0] = Arrays.copyOf(fArr, fArr.length);
                    this.f626[0] = currentTimeMillis;
                    return;
                }
                float[] fArr3 = this.f625[1];
                if (fArr3 == null) {
                    float[] copyOf = Arrays.copyOf(fArr, fArr.length);
                    this.f625[1] = copyOf;
                    this.f626[1] = currentTimeMillis;
                    this.f629 = m387(fArr2, copyOf);
                } else if (50000000 <= j - this.f623) {
                    this.f623 = j;
                    if (Arrays.equals(fArr3, fArr)) {
                        this.f626[1] = currentTimeMillis;
                        return;
                    }
                    double r4 = m387(fArr2, fArr);
                    if (r4 > this.f629) {
                        this.f625[1] = Arrays.copyOf(fArr, fArr.length);
                        this.f626[1] = currentTimeMillis;
                        this.f629 = r4;
                    }
                }
            }
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* renamed from: ı */
    public final void mo14725(@NonNull Map<C0949r, Map<String, Object>> map, boolean z) {
        if (m388()) {
            map.put(this, m392());
            if (z) {
                int length = this.f625.length;
                for (int i = 0; i < length; i++) {
                    this.f625[i] = null;
                }
                int length2 = this.f626.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    this.f626[i2] = 0;
                }
                this.f629 = 0.0d;
                this.f623 = 0;
            }
        } else if (!map.containsKey(this)) {
            map.put(this, m392());
        }
    }

    /* renamed from: Ι */
    private boolean m390(int i, @NonNull String str, @NonNull String str2) {
        return this.f624 == i && this.f622.equals(str) && this.f627.equals(str2);
    }

    @NonNull
    /* renamed from: ι */
    private Map<String, Object> m392() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(7);
        concurrentHashMap.put("sT", Integer.valueOf(this.f624));
        concurrentHashMap.put("sN", this.f622);
        concurrentHashMap.put("sV", this.f627);
        float[] fArr = this.f625[0];
        if (fArr != null) {
            concurrentHashMap.put("sVS", m389(fArr));
        }
        float[] fArr2 = this.f625[1];
        if (fArr2 != null) {
            concurrentHashMap.put("sVE", m389(fArr2));
        }
        return concurrentHashMap;
    }

    /* renamed from: ǃ */
    private boolean m388() {
        return this.f625[0] != null;
    }

    public final int hashCode() {
        return this.f628;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0949r)) {
            return false;
        }
        C0949r rVar = (C0949r) obj;
        return m390(rVar.f624, rVar.f622, rVar.f627);
    }
}
