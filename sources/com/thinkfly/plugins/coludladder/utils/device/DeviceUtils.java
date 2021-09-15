package com.thinkfly.plugins.coludladder.utils.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.doraemon.p027eg.CommonUtil;

public class DeviceUtils {
    private static DeviceUtils mDeviceUtils = null;

    private DeviceUtils() {
    }

    public static DeviceUtils getInstance(Context context) {
        return getInstance();
    }

    public static DeviceUtils getInstance() {
        if (mDeviceUtils != null) {
            return mDeviceUtils;
        }
        DeviceUtils deviceUtils = new DeviceUtils();
        mDeviceUtils = deviceUtils;
        return deviceUtils;
    }

    public static boolean checkPermissions(Context context, String permission) {
        PackageManager localPackageManager;
        if (context == null || (localPackageManager = context.getPackageManager()) == null || localPackageManager.checkPermission(permission, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public String getImei(Context context) {
        return CommonUtil.getIMEI(context);
    }

    @SuppressLint({"SimpleDateFormat"})
    public String getTimeZone() {
        return CommonUtil.getTimeZone();
    }

    public String getLanguage() {
        return CommonUtil.getLanguage();
    }

    public String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public String getResolution() {
        return CommonUtil.getResolution();
    }

    public int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public String getBrand() {
        String brand = Build.BRAND;
        if (brand == null) {
            return "";
        }
        return brand;
    }

    public String getAndroidId() {
        return CommonUtil.getAndroidId();
    }

    @SuppressLint({"NewApi"})
    public String getMacAddress(Context context) {
        return CommonUtil.getMacAddress();
    }

    public int getNetworkType(Context context) {
        return CommonUtil.getNetworkType();
    }

    public String getModel() {
        return CommonUtil.getModel();
    }

    public int getOS() {
        return 1;
    }

    public String getOSVersion() {
        return CommonUtil.getOSVersion();
    }

    public String getManufacturer() {
        return CommonUtil.getManufacturer();
    }

    public String getOperators() {
        return CommonUtil.getSimOperatorByMnc();
    }

    public String getWifiName() {
        return CommonUtil.getWifiName();
    }

    public String getLocalIPAddress() {
        return CommonUtil.getLocalIPAddress();
    }

    private static String intIP2StringIP(int ip) {
        return (ip & 255) + "." + ((ip >> 8) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 24) & 255);
    }

    public static boolean isNetworkAvailable() {
        return CommonUtil.isNetAvailable();
    }
}
