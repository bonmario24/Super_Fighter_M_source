package com.doraemon.p027eg;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.p000v4.app.ActivityCompat;
import android.text.TextUtils;
import com.doraemon.p027eg.Const;
import com.doraemon.util.AppUtils;
import com.doraemon.util.DeviceEmuCheckUtil;
import com.doraemon.util.DeviceUtils;
import com.doraemon.util.NetworkUtils;
import com.doraemon.util.PermissionUtils;
import com.doraemon.util.PhoneUtils;
import com.doraemon.util.ScreenUtils;
import com.doraemon.util.Utils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* renamed from: com.doraemon.eg.CommonUtil */
public class CommonUtil {
    private CommonUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(@NonNull Context context) {
        Utils.init((Application) context.getApplicationContext());
    }

    public static boolean isMainProcess(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null) {
            return false;
        }
        int pid = Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getSystemService("activity");
        if (manager == null || (runningAppProcesses = manager.getRunningAppProcesses()) == null || runningAppProcesses.isEmpty()) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo process : runningAppProcesses) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return context.getPackageName().equals(processName);
    }

    public static String getTimeZone() {
        int sourceGMTOffset = new GregorianCalendar().getTimeZone().getOffset(System.currentTimeMillis()) / 3600000;
        if (sourceGMTOffset >= 0) {
            StringBuilder append = new StringBuilder().append("+");
            if (sourceGMTOffset > 12) {
                sourceGMTOffset = 12;
            }
            return append.append(sourceGMTOffset).toString();
        }
        StringBuilder append2 = new StringBuilder().append("");
        if (sourceGMTOffset < -11) {
            sourceGMTOffset = -11;
        }
        return append2.append(sourceGMTOffset).toString();
    }

    public static int getNetworkType() {
        try {
            ConnectivityManager systemService = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
            if (systemService == null) {
                return Const.NetWorkType.UnKown_Type;
            }
            NetworkInfo networkInfo = systemService.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getType() == 1) {
                    return Const.NetWorkType.WIFI_Type;
                }
                if (networkInfo.getType() == 0) {
                    String subtypeName = networkInfo.getSubtypeName();
                    switch (networkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 20:
                            return Const.NetWorkType.Flow_Type;
                        default:
                            return Const.NetWorkType.UnKown_Type;
                    }
                }
            }
            return Const.NetWorkType.UnKown_Type;
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static String getModel() {
        String model = Build.MODEL;
        if (model.contains(" ")) {
            model = model.replace(" ", "_");
        }
        return TextUtils.isEmpty(model) ? "" : model;
    }

    public static String getOSVersion() {
        String OSVersion = Build.VERSION.RELEASE;
        return TextUtils.isEmpty(OSVersion) ? "" : OSVersion;
    }

    public static int getOS() {
        return Const.Android_OS;
    }

    public static String getManufacturer() {
        String manufacturer = Build.MANUFACTURER;
        return TextUtils.isEmpty(manufacturer) ? "" : manufacturer;
    }

    @SuppressLint({"NewApi"})
    public static String getSerialNo() {
        if (ActivityCompat.checkSelfPermission(Utils.getApp(), "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        String serial = PhoneUtils.getSerial();
        return serial.equals("unknown") ? "" : serial;
    }

    public static String getBrand() {
        return getPhoneBrand();
    }

    public static String getPhoneBrand() {
        return TextUtils.isEmpty(Build.BRAND) ? "" : Build.BRAND;
    }

    public static String getAndroidId() {
        return DeviceUtils.getAndroidID();
    }

    public static String getLocalIPAddress() {
        ConnectivityManager systemService = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (systemService != null) {
            try {
                NetworkInfo info = systemService.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getType() == 0) {
                        try {
                            Enumeration en = NetworkInterface.getNetworkInterfaces();
                            while (en.hasMoreElements()) {
                                Enumeration enumIpAddr = en.nextElement().getInetAddresses();
                                while (true) {
                                    if (enumIpAddr.hasMoreElements()) {
                                        InetAddress inetAddress = enumIpAddr.nextElement();
                                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                                            return inetAddress.getHostAddress();
                                        }
                                    }
                                }
                            }
                        } catch (SocketException var7) {
                            var7.printStackTrace();
                        }
                    } else if (info.getType() == 1) {
                        WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
                        if (wifiManager == null) {
                            return "";
                        }
                        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                        if (wifiInfo != null) {
                            String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                            if (TextUtils.isEmpty(ipAddress)) {
                                ipAddress = "";
                            }
                            return ipAddress;
                        }
                    }
                }
                if (TextUtils.isEmpty("")) {
                    return "";
                }
                return "";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty("")) {
            return "";
        }
        return "";
    }

    public static String intIP2StringIP(int ip) {
        return (ip & 255) + "." + ((ip >> 8) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 24) & 255);
    }

    public static String getAppPackageName() {
        return FilterUtils.filterString(AppUtils.getAppPackageName());
    }

    public static String getAppName() {
        return FilterUtils.filterString(AppUtils.getAppName());
    }

    @Deprecated
    public static String getDefaultString(String string) {
        return TextUtils.isEmpty(string) ? "" : string;
    }

    public static String getAppVersionName() {
        return FilterUtils.filterString(AppUtils.getAppVersionName());
    }

    public static String getAppVersionCode() {
        return FilterUtils.filterString("" + AppUtils.getAppVersionCode());
    }

    public static String getMacAddress() {
        String macAddress = DeviceUtils.getMacAddress();
        if (TextUtils.isEmpty(macAddress) || "00:00:00:00:00:00".equals(macAddress) || !macAddress.contains(":") || macAddress.length() < 17) {
            return "02:00:00:00:00:00";
        }
        return macAddress.toUpperCase();
    }

    public static String getWifiName() {
        String wifiName;
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (Build.VERSION.SDK_INT == 28) {
            wifiName = getSSIDByNetworkId();
        } else {
            wifiName = NetworkUtils.getWifiName();
        }
        if (wifiName.contains("\"")) {
            return wifiName.replace("\"", "");
        }
        return wifiName;
    }

    private static String getSSIDByNetworkId() {
        String sid = "";
        try {
            WifiManager mWifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
            if (mWifiManager != null) {
                WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
                Iterator<WifiConfiguration> it = mWifiManager.getConfiguredNetworks().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    WifiConfiguration network = it.next();
                    if (network.networkId == connectionInfo.getNetworkId()) {
                        sid = network.SSID;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(sid) ? "" : sid;
    }

    public static String getResolution() {
        return ScreenUtils.getScreenWidth() + "*" + ScreenUtils.getScreenHeight();
    }

    public static String getLanguage() {
        return FilterUtils.filterString(Locale.getDefault().getLanguage());
    }

    public static String getSimOperatorByMnc() {
        return FilterUtils.filterString(PhoneUtils.getSimOperatorByMnc());
    }

    public static int getDeviceRootState() {
        return DeviceUtils.isDeviceRooted() ? 1 : 0;
    }

    public static boolean isEmulator(Context context) {
        return DeviceEmuCheckUtil.mayOnEmulator(context);
    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable();
    }

    public static String getIMEI(Context context) {
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 29 && context.getApplicationContext().getApplicationInfo().targetSdkVersion >= 29) {
            return "";
        }
        if (PermissionUtils.isGranted("android.permission.READ_PHONE_STATE")) {
            try {
                String i = PhoneUtils.getIMEI();
                return TextUtils.isEmpty(i) ? "" : i;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNetAvailable() {
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
