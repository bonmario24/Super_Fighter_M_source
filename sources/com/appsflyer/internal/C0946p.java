package com.appsflyer.internal;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appsflyer.AndroidUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.OkGo;

/* renamed from: com.appsflyer.internal.p */
public final class C0946p {

    /* renamed from: com.appsflyer.internal.p$a */
    public static final class C0947a {

        /* renamed from: Ι */
        public static final C0946p f621 = new C0946p();
    }

    C0946p() {
    }

    /* renamed from: ǃ */
    private static boolean m385(@NonNull Context context, @NonNull String[] strArr) {
        for (String isPermissionAvailable : strArr) {
            if (AndroidUtils.isPermissionAvailable(context, isPermissionAvailable)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    /* renamed from: ı */
    public static Location m384(@NonNull Context context) {
        Location location;
        Location location2;
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            if (m385(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})) {
                location = locationManager.getLastKnownLocation("network");
            } else {
                location = null;
            }
            if (m385(context, new String[]{"android.permission.ACCESS_FINE_LOCATION"})) {
                location2 = locationManager.getLastKnownLocation("gps");
            } else {
                location2 = null;
            }
            if (location2 == null && location == null) {
                location2 = null;
            } else if (location2 == null && location != null) {
                location2 = location;
            } else if ((location != null || location2 == null) && OkGo.DEFAULT_MILLISECONDS < location.getTime() - location2.getTime()) {
                location2 = location;
            }
            if (location2 != null) {
                return location2;
            }
        } catch (Throwable th) {
        }
        return null;
    }
}
