package com.xsdk.doraemon.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.doraemon.util.Utils;

public class NetWorkUtils {
    public static boolean isNetAvailable(Context context) {
        ConnectivityManager cm;
        NetworkCapabilities nc;
        Application app = Utils.getApp();
        if (!(app == null || (cm = (ConnectivityManager) app.getSystemService("connectivity")) == null)) {
            if (Build.VERSION.SDK_INT < 23) {
                NetworkInfo mWiFiNetworkInfo = cm.getActiveNetworkInfo();
                if (mWiFiNetworkInfo != null && (mWiFiNetworkInfo.getType() == 1 || mWiFiNetworkInfo.getType() == 0 || mWiFiNetworkInfo.getType() == 9 || mWiFiNetworkInfo.getType() == 17 || mWiFiNetworkInfo.getType() == 7)) {
                    return true;
                }
            } else {
                Network network = cm.getActiveNetwork();
                if (!(network == null || (nc = cm.getNetworkCapabilities(network)) == null || (!nc.hasTransport(0) && !nc.hasTransport(1) && !nc.hasTransport(2) && !nc.hasTransport(3) && !nc.hasTransport(4)))) {
                    return true;
                }
            }
        }
        return false;
    }
}
