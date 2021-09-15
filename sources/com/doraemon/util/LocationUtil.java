package com.doraemon.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.p000v4.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;

public class LocationUtil {
    public static Location getCurrentNetLocation(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            if (locationManager == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 && locationManager.isProviderEnabled("network")) {
                    return locationManager.getLastKnownLocation("network");
                }
                return null;
            } else if (locationManager.isProviderEnabled("network")) {
                return locationManager.getLastKnownLocation("network");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLocationInfo(Context context) {
        try {
            Location location = getCurrentNetLocation(context);
            if (location == null) {
                return getDefaultLocationStr();
            }
            return getLocationNormalStr(location);
        } catch (Exception e) {
            e.printStackTrace();
            return getDefaultLocationStr();
        }
    }

    private static String getDefaultLocationStr() {
        return "";
    }

    private static String getLocationNormalStr(Location location) {
        return "lng=" + location.getLongitude() + "`lat=" + location.getLatitude();
    }
}
