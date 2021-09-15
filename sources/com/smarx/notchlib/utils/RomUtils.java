package com.smarx.notchlib.utils;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public final class RomUtils {
    private static final String HUAWEI = "huawei";
    private static final String OPPO = "oppo";
    private static final String UNKNOWN = "unknown";
    private static final String VERSION_PROPERTY_HUAWEI = "ro.build.version.emui";
    private static final String VERSION_PROPERTY_OPPO = "ro.build.version.opporom";
    private static final String VERSION_PROPERTY_VIVO = "ro.vivo.os.build.display.id";
    private static final String VERSION_PROPERTY_XIAOMI = "ro.build.version.incremental";
    private static final String VIVO = "vivo";
    private static final String XIAOMI = "xiaomi";
    private static RomInfo bean = null;

    public static class RomInfo {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public String version;

        public String getName() {
            return this.name;
        }

        public String getVersion() {
            return this.version;
        }

        public String toString() {
            return "RomInfo{name=" + this.name + ", version=" + this.version + "}";
        }
    }

    private RomUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static String getBrand() {
        try {
            String str = Build.BRAND;
            if (!TextUtils.isEmpty(str)) {
                return str.toLowerCase();
            }
        } catch (Throwable th) {
        }
        return "unknown";
    }

    private static String getManufacturer() {
        try {
            String str = Build.MANUFACTURER;
            if (!TextUtils.isEmpty(str)) {
                return str.toLowerCase();
            }
        } catch (Throwable th) {
        }
        return "unknown";
    }

    public static RomInfo getRomInfo() {
        if (bean != null) {
            return bean;
        }
        bean = new RomInfo();
        String brand = getBrand();
        String manufacturer = getManufacturer();
        if (isRightRom(brand, manufacturer, "huawei")) {
            String unused = bean.name = "huawei";
            String romVersion = getRomVersion(VERSION_PROPERTY_HUAWEI);
            String[] split = romVersion.split("_");
            if (split.length > 1) {
                String unused2 = bean.version = split[1];
            } else {
                String unused3 = bean.version = romVersion;
            }
            return bean;
        }
        if (isRightRom(brand, manufacturer, VIVO)) {
            String unused4 = bean.name = VIVO;
            String unused5 = bean.version = getRomVersion(VERSION_PROPERTY_VIVO);
            return bean;
        }
        if (isRightRom(brand, manufacturer, XIAOMI)) {
            String unused6 = bean.name = XIAOMI;
            String unused7 = bean.version = getRomVersion(VERSION_PROPERTY_XIAOMI);
            return bean;
        }
        if (isRightRom(brand, manufacturer, OPPO)) {
            String unused8 = bean.name = OPPO;
            String unused9 = bean.version = getRomVersion(VERSION_PROPERTY_OPPO);
            return bean;
        }
        String unused10 = bean.name = manufacturer;
        String unused11 = bean.version = getRomVersion("");
        return bean;
    }

    private static String getRomVersion(String str) {
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            str2 = getSystemProperty(str);
        }
        if (TextUtils.isEmpty(str2) || str2.equals("unknown")) {
            try {
                String str3 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str3)) {
                    str2 = str3.toLowerCase();
                }
            } catch (Throwable th) {
            }
        }
        return TextUtils.isEmpty(str2) ? "unknown" : str2;
    }

    private static String getSystemProperty(String str) {
        String systemPropertyByShell = getSystemPropertyByShell(str);
        if (!TextUtils.isEmpty(systemPropertyByShell)) {
            return systemPropertyByShell;
        }
        String systemPropertyByStream = getSystemPropertyByStream(str);
        return !TextUtils.isEmpty(systemPropertyByStream) ? systemPropertyByStream : Build.VERSION.SDK_INT < 28 ? getSystemPropertyByReflect(str) : systemPropertyByStream;
    }

    private static String getSystemPropertyByReflect(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, ""});
        } catch (Exception e) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051 A[SYNTHETIC, Splitter:B:24:0x0051] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getSystemPropertyByShell(java.lang.String r7) {
        /*
            r0 = 0
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            r5.<init>()     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.lang.String r6 = "getprop "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.lang.Process r2 = r4.exec(r5)     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.io.InputStream r5 = r2.getInputStream()     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            r5 = 1024(0x400, float:1.435E-42)
            r1.<init>(r4, r5)     // Catch:{ IOException -> 0x0045, all -> 0x004e }
            java.lang.String r3 = r1.readLine()     // Catch:{ IOException -> 0x005c, all -> 0x0059 }
            if (r3 == 0) goto L_0x0039
            if (r1 == 0) goto L_0x0037
            r1.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0037:
            r0 = r1
        L_0x0038:
            return r3
        L_0x0039:
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ IOException -> 0x0042 }
            r0 = r1
        L_0x003f:
            java.lang.String r3 = ""
            goto L_0x0038
        L_0x0042:
            r4 = move-exception
            r0 = r1
            goto L_0x003f
        L_0x0045:
            r4 = move-exception
        L_0x0046:
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x003f
        L_0x004c:
            r4 = move-exception
            goto L_0x003f
        L_0x004e:
            r4 = move-exception
        L_0x004f:
            if (r0 == 0) goto L_0x0054
            r0.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0054:
            throw r4
        L_0x0055:
            r4 = move-exception
            goto L_0x0037
        L_0x0057:
            r5 = move-exception
            goto L_0x0054
        L_0x0059:
            r4 = move-exception
            r0 = r1
            goto L_0x004f
        L_0x005c:
            r4 = move-exception
            r0 = r1
            goto L_0x0046
        L_0x005f:
            r0 = r1
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smarx.notchlib.utils.RomUtils.getSystemPropertyByShell(java.lang.String):java.lang.String");
    }

    private static String getSystemPropertyByStream(String str) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isHuawei() {
        return "huawei".equals(getRomInfo().name);
    }

    public static boolean isOppo() {
        return OPPO.equals(getRomInfo().name);
    }

    private static boolean isRightRom(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVivo() {
        return VIVO.equals(getRomInfo().name);
    }

    public static boolean isXiaomi() {
        return XIAOMI.equals(getRomInfo().name);
    }
}
