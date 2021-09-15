package com.doraemon.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isPhone() {
        return getTelephonyManager().getPhoneType() != 0;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getDeviceId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        TelephonyManager tm = getTelephonyManager();
        String deviceId = tm.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return "";
        }
        String imei = tm.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = tm.getMeid();
        if (TextUtils.isEmpty(meid)) {
            meid = "";
        }
        return meid;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getSerial() {
        return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getIMEI() {
        return getImeiOrMeid(true);
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getMEID() {
        return getImeiOrMeid(false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00fc A[RETURN, SYNTHETIC] */
    @android.annotation.SuppressLint({"HardwareIds"})
    @android.support.annotation.RequiresPermission("android.permission.READ_PHONE_STATE")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getImeiOrMeid(boolean r14) {
        /*
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 29
            if (r9 < r10) goto L_0x001b
            android.app.Application r9 = com.doraemon.util.Utils.getApp()
            android.content.Context r9 = r9.getApplicationContext()
            android.content.pm.ApplicationInfo r9 = r9.getApplicationInfo()
            int r9 = r9.targetSdkVersion
            r10 = 29
            if (r9 < r10) goto L_0x001b
            java.lang.String r1 = ""
        L_0x001a:
            return r1
        L_0x001b:
            android.telephony.TelephonyManager r8 = getTelephonyManager()
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 26
            if (r9 < r10) goto L_0x0045
            if (r14 == 0) goto L_0x0036
            r9 = 0
            java.lang.String r9 = r8.getImei(r9)
            r10 = 1
            java.lang.String r10 = r8.getImei(r10)
            java.lang.String r1 = getMinOne(r9, r10)
            goto L_0x001a
        L_0x0036:
            r9 = 0
            java.lang.String r9 = r8.getMeid(r9)
            r10 = 1
            java.lang.String r10 = r8.getMeid(r10)
            java.lang.String r1 = getMinOne(r9, r10)
            goto L_0x001a
        L_0x0045:
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 21
            if (r9 < r10) goto L_0x00ec
            if (r14 == 0) goto L_0x006e
            java.lang.String r9 = "ril.gsm.imei"
        L_0x004f:
            java.lang.String r6 = getSystemPropertyByReflect(r9)
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 != 0) goto L_0x0075
            java.lang.String r9 = ","
            java.lang.String[] r5 = r6.split(r9)
            int r9 = r5.length
            r10 = 2
            if (r9 != r10) goto L_0x0071
            r9 = 0
            r9 = r5[r9]
            r10 = 1
            r10 = r5[r10]
            java.lang.String r1 = getMinOne(r9, r10)
            goto L_0x001a
        L_0x006e:
            java.lang.String r9 = "ril.cdma.meid"
            goto L_0x004f
        L_0x0071:
            r9 = 0
            r1 = r5[r9]
            goto L_0x001a
        L_0x0075:
            java.lang.String r3 = r8.getDeviceId()
            java.lang.String r4 = ""
            java.lang.Class r9 = r8.getClass()     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            java.lang.String r10 = "getDeviceId"
            r11 = 1
            java.lang.Class[] r11 = new java.lang.Class[r11]     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            r12 = 0
            java.lang.Class r13 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            r11[r12] = r13     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            java.lang.reflect.Method r7 = r9.getMethod(r10, r11)     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            r9 = 1
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            r11 = 0
            if (r14 == 0) goto L_0x00c2
            r9 = 1
        L_0x0094:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            r10[r11] = r9     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            java.lang.Object r9 = r7.invoke(r8, r10)     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            r0 = r9
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NoSuchMethodException -> 0x00c4, IllegalAccessException -> 0x00c9, InvocationTargetException -> 0x00ce }
            r4 = r0
        L_0x00a2:
            if (r14 == 0) goto L_0x00d3
            if (r3 == 0) goto L_0x00b0
            int r9 = r3.length()
            r10 = 15
            if (r9 >= r10) goto L_0x00b0
            java.lang.String r3 = ""
        L_0x00b0:
            if (r4 == 0) goto L_0x00bc
            int r9 = r4.length()
            r10 = 15
            if (r9 >= r10) goto L_0x00bc
            java.lang.String r4 = ""
        L_0x00bc:
            java.lang.String r1 = getMinOne(r3, r4)
            goto L_0x001a
        L_0x00c2:
            r9 = 2
            goto L_0x0094
        L_0x00c4:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00a2
        L_0x00c9:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00a2
        L_0x00ce:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00a2
        L_0x00d3:
            if (r3 == 0) goto L_0x00df
            int r9 = r3.length()
            r10 = 14
            if (r9 != r10) goto L_0x00df
            java.lang.String r3 = ""
        L_0x00df:
            if (r4 == 0) goto L_0x00bc
            int r9 = r4.length()
            r10 = 14
            if (r9 != r10) goto L_0x00bc
            java.lang.String r4 = ""
            goto L_0x00bc
        L_0x00ec:
            java.lang.String r1 = r8.getDeviceId()
            if (r14 == 0) goto L_0x0100
            if (r1 == 0) goto L_0x00fc
            int r9 = r1.length()
            r10 = 15
            if (r9 >= r10) goto L_0x001a
        L_0x00fc:
            java.lang.String r1 = ""
            goto L_0x001a
        L_0x0100:
            if (r1 == 0) goto L_0x00fc
            int r9 = r1.length()
            r10 = 14
            if (r9 != r10) goto L_0x00fc
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.PhoneUtils.getImeiOrMeid(boolean):java.lang.String");
    }

    private static String getMinOne(String s0, String s1) {
        boolean empty0 = TextUtils.isEmpty(s0);
        boolean empty1 = TextUtils.isEmpty(s1);
        if (empty0 && empty1) {
            return "";
        }
        if (empty0 || empty1) {
            if (empty0) {
                return s1;
            }
            return s0;
        } else if (s0.compareTo(s1) > 0) {
            return s1;
        } else {
            return s0;
        }
    }

    private static String getSystemPropertyByReflect(String key) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            return (String) clz.getMethod("get", new Class[]{String.class, String.class}).invoke(clz, new Object[]{key, ""});
        } catch (Exception e) {
            return "";
        }
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getIMSI() {
        return getTelephonyManager().getSubscriberId();
    }

    public static int getPhoneType() {
        return getTelephonyManager().getPhoneType();
    }

    public static boolean isSimCardReady() {
        return getTelephonyManager().getSimState() == 5;
    }

    public static String getSimOperatorName() {
        return getTelephonyManager().getSimOperatorName();
    }

    public static String getSimOperatorByMnc() {
        String operator = getTelephonyManager().getSimOperator();
        if (operator == null) {
            return "";
        }
        char c = 65535;
        switch (operator.hashCode()) {
            case 49679470:
                if (operator.equals("46000")) {
                    c = 0;
                    break;
                }
                break;
            case 49679471:
                if (operator.equals("46001")) {
                    c = 4;
                    break;
                }
                break;
            case 49679472:
                if (operator.equals("46002")) {
                    c = 1;
                    break;
                }
                break;
            case 49679473:
                if (operator.equals("46003")) {
                    c = 7;
                    break;
                }
                break;
            case 49679475:
                if (operator.equals("46005")) {
                    c = 8;
                    break;
                }
                break;
            case 49679476:
                if (operator.equals("46006")) {
                    c = 5;
                    break;
                }
                break;
            case 49679477:
                if (operator.equals("46007")) {
                    c = 2;
                    break;
                }
                break;
            case 49679479:
                if (operator.equals("46009")) {
                    c = 6;
                    break;
                }
                break;
            case 49679502:
                if (operator.equals("46011")) {
                    c = 9;
                    break;
                }
                break;
            case 49679532:
                if (operator.equals("46020")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "中国移动";
            case 4:
            case 5:
            case 6:
                return "中国联通";
            case 7:
            case 8:
            case 9:
                return "中国电信";
            default:
                return operator;
        }
    }

    public static boolean dial(String phoneNumber) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNumber));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static boolean call(String phoneNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    public static boolean sendSms(String phoneNumber, String content) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + phoneNumber));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        intent.putExtra("sms_body", content);
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) Utils.getApp().getSystemService("phone");
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
