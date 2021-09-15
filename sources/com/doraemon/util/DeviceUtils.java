package com.doraemon.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

public final class DeviceUtils {
    private static final String KEY_UDID = "KEY_UDID";
    private static volatile String udid;

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isDeviceRooted() {
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"};
        int length = locations.length;
        for (int i = 0; i < length; i++) {
            if (new File(locations[i] + ShellAdbUtil.COMMAND_SU).exists()) {
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = 17)
    public static boolean isAdbEnabled() {
        if (Settings.Secure.getInt(Utils.getApp().getContentResolver(), "adb_enabled", 0) > 0) {
            return true;
        }
        return false;
    }

    public static String getSDKVersionName() {
        return Build.VERSION.RELEASE;
    }

    public static int getSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID() {
        String id = Settings.Secure.getString(Utils.getApp().getContentResolver(), "android_id");
        return (!"9774d56d682e549c".equals(id) && id != null) ? id : "";
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET", "android.permission.CHANGE_WIFI_STATE"})
    public static String getMacAddress() {
        String macAddress = getMacAddress((String[]) null);
        if (!macAddress.equals("") || getWifiEnabled()) {
            return macAddress;
        }
        setWifiEnabled(true);
        setWifiEnabled(false);
        return getMacAddress((String[]) null);
    }

    private static boolean getWifiEnabled() {
        WifiManager manager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (manager == null) {
            return false;
        }
        return manager.isWifiEnabled();
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    private static void setWifiEnabled(boolean enabled) {
        WifiManager manager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (manager != null && enabled != manager.isWifiEnabled()) {
            manager.setWifiEnabled(enabled);
        }
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static String getMacAddress(String... excepts) {
        String macAddress = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        String macAddress2 = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddress2, excepts)) {
            return macAddress2;
        }
        String macAddress3 = getMacAddressByWifiInfo();
        if (isAddressNotInExcepts(macAddress3, excepts)) {
            return macAddress3;
        }
        String macAddress4 = getMacAddressByFile();
        if (isAddressNotInExcepts(macAddress4, excepts)) {
            return macAddress4;
        }
        return "";
    }

    private static boolean isAddressNotInExcepts(String address, String... excepts) {
        if (excepts != null && excepts.length != 0) {
            for (String filter : excepts) {
                if (address.equals(filter)) {
                    return false;
                }
            }
            return true;
        } else if (TextUtils.isEmpty(address) || "02:00:00:00:00:00".equals(address)) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private static String getMacAddressByWifiInfo() {
        WifiInfo info;
        try {
            WifiManager wifi = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
            if (!(wifi == null || (info = wifi.getConnectionInfo()) == null)) {
                return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByNetworkInterface() {
        byte[] macBytes;
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni != null && ni.getName().equalsIgnoreCase("wlan0") && (macBytes = ni.getHardwareAddress()) != null && macBytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    int length = macBytes.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02x:", new Object[]{Byte.valueOf(macBytes[i])}));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByInetAddress() {
        NetworkInterface ni;
        byte[] macBytes;
        try {
            InetAddress inetAddress = getInetAddress();
            if (!(inetAddress == null || (ni = NetworkInterface.getByInetAddress(inetAddress)) == null || (macBytes = ni.getHardwareAddress()) == null || macBytes.length <= 0)) {
                StringBuilder sb = new StringBuilder();
                int length = macBytes.length;
                for (int i = 0; i < length; i++) {
                    sb.append(String.format("%02x:", new Object[]{Byte.valueOf(macBytes[i])}));
                }
                return sb.substring(0, sb.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isUp()) {
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress inetAddress = addresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(58) < 0) {
                            return inetAddress;
                        }
                    }
                    continue;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0030, code lost:
        r0 = r2.successMsg;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getMacAddressByFile() {
        /*
            r5 = 0
            java.lang.String r3 = "getprop wifi.interface"
            com.doraemon.util.ShellUtils$CommandResult r2 = com.doraemon.util.ShellUtils.execCmd((java.lang.String) r3, (boolean) r5)
            int r3 = r2.result
            if (r3 != 0) goto L_0x003b
            java.lang.String r1 = r2.successMsg
            if (r1 == 0) goto L_0x003b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "cat /sys/class/net/"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r4 = "/address"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.doraemon.util.ShellUtils$CommandResult r2 = com.doraemon.util.ShellUtils.execCmd((java.lang.String) r3, (boolean) r5)
            int r3 = r2.result
            if (r3 != 0) goto L_0x003b
            java.lang.String r0 = r2.successMsg
            if (r0 == 0) goto L_0x003b
            int r3 = r0.length()
            if (r3 <= 0) goto L_0x003b
        L_0x003a:
            return r0
        L_0x003b:
            java.lang.String r0 = "02:00:00:00:00:00"
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.DeviceUtils.getMacAddressByFile():java.lang.String");
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return TextUtils.isEmpty(Build.MODEL) ? "" : Build.MODEL;
    }

    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        return new String[]{Build.CPU_ABI};
    }

    public static boolean isTablet() {
        return (Utils.getApp().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean isEmulator() {
        boolean checkProperty;
        boolean checkDial;
        String name;
        if (Build.FINGERPRINT.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) || "google_sdk".equals(Build.PRODUCT))) {
            checkProperty = true;
        } else {
            checkProperty = false;
        }
        if (checkProperty) {
            return true;
        }
        String operatorName = "";
        TelephonyManager tm = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (!(tm == null || (name = tm.getNetworkOperatorName()) == null)) {
            operatorName = name;
        }
        if (operatorName.toLowerCase().equals("android")) {
            return true;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        if (intent.resolveActivity(Utils.getApp().getPackageManager()) == null) {
            checkDial = true;
        } else {
            checkDial = false;
        }
        if (!checkDial) {
            return false;
        }
        return true;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getUniqueDeviceId() {
        return getUniqueDeviceId("");
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getUniqueDeviceId(String prefix) {
        if (udid == null) {
            synchronized (DeviceUtils.class) {
                if (udid == null) {
                    String id = Utils.getSpUtils4Utils().getString(KEY_UDID, (String) null);
                    if (id != null) {
                        udid = id;
                        String str = udid;
                        return str;
                    }
                    try {
                        String macAddress = getMacAddress();
                        if (!macAddress.equals("")) {
                            String saveUdid = saveUdid(prefix + 1, macAddress);
                            return saveUdid;
                        }
                        String androidId = getAndroidID();
                        if (!TextUtils.isEmpty(androidId)) {
                            String saveUdid2 = saveUdid(prefix + 2, androidId);
                            return saveUdid2;
                        }
                        String saveUdid3 = saveUdid(prefix + 9, "");
                        return saveUdid3;
                    } catch (Exception e) {
                    }
                }
            }
        }
        return udid;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static boolean isSameDevice(String uniqueDeviceId) {
        if (TextUtils.isEmpty(uniqueDeviceId) && uniqueDeviceId.length() < 33) {
            return false;
        }
        if (uniqueDeviceId.equals(udid)) {
            return true;
        }
        if (uniqueDeviceId.equals(Utils.getSpUtils4Utils().getString(KEY_UDID, (String) null))) {
            return true;
        }
        int st = uniqueDeviceId.length() - 33;
        String type = uniqueDeviceId.substring(st, st + 1);
        if (type.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            String macAddress = getMacAddress();
            if (!macAddress.equals("")) {
                return uniqueDeviceId.substring(st + 1).equals(getUdid("", macAddress));
            }
            return false;
        } else if (!type.startsWith("2")) {
            return false;
        } else {
            String androidId = getAndroidID();
            if (!TextUtils.isEmpty(androidId)) {
                return uniqueDeviceId.substring(st + 1).equals(getUdid("", androidId));
            }
            return false;
        }
    }

    private static String saveUdid(String prefix, String id) {
        udid = getUdid(prefix, id);
        SPUtils.getInstance().put(KEY_UDID, udid);
        return udid;
    }

    private static String getUdid(String prefix, String id) {
        if (id.equals("")) {
            return prefix + UUID.randomUUID().toString().replace("-", "");
        }
        return prefix + UUID.nameUUIDFromBytes(id.getBytes()).toString().replace("-", "");
    }

    public static String getPhoneBrand() {
        return TextUtils.isEmpty(Build.BRAND) ? "" : Build.BRAND;
    }

    @Deprecated
    public static String getTimeZone() {
        String timeZone;
        int sourceGMTOffset = new GregorianCalendar().getTimeZone().getOffset(System.currentTimeMillis());
        if (sourceGMTOffset > 0) {
            timeZone = "+" + (sourceGMTOffset / 3600000);
        } else {
            timeZone = "" + (sourceGMTOffset / 3600000);
        }
        return TextUtils.isEmpty(timeZone) ? "" : timeZone;
    }

    @Deprecated
    public static String getSerialNumber() {
        String serial = PhoneUtils.getSerial();
        return TextUtils.isEmpty(serial) ? "" : serial;
    }

    @Deprecated
    public static String getPhoneIMSI(Context context) {
        String imsi = "";
        if (PermissionUtils.isGranted("android.permission.READ_PHONE_STATE")) {
            imsi = PhoneUtils.getIMSI();
        }
        return TextUtils.isEmpty(imsi) ? "" : imsi;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    @Deprecated
    public static String getMacAddressUpperStr() {
        String mac = getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            return mac.toUpperCase();
        }
        return "";
    }

    @Deprecated
    public static String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        return TextUtils.isEmpty(language) ? "" : language;
    }

    @Deprecated
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
}
