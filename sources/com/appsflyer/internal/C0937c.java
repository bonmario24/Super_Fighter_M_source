package com.appsflyer.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appsflyer.AFLogger;
import com.facebook.appevents.AppEventsConstants;
import com.xsdk.doraemon.utils.permission.PermissionsList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.appsflyer.internal.c */
public final class C0937c {

    /* renamed from: ı */
    private static char[] f566 = {61163, 22514, 40130, 50618, 2749, 29581, 47210, 57662, 9806, 28457, 54330, 7453, 17394, 35068, 61905, 13998, 32659, 42194, 60754, 21069, 39774, 49185, 2334, 19992, 46325, 64962, 8911, 27541, 53379, 6554, 24191, 34631, 52271, 13614, 52353, 60956, 4935, 43601, 24939, 14341, 63307, 36381, 17911, 7305, 56298, 37532, 10685, 57476, 48766, 30029, 3175, 51975, 33325, 22802, 21602, 60791, 9806, 32551, 45113, 51467, 692, 23471, 40073, 54690, 28393, 42945, 63849, 12924, 19213, 35961, 50516, 7696, 22456, 59591, 8588, 31403, 46032, 62666, 3700, 18207, 38925, 53556, 27226, 41806, 58533, 15771, 47429, '\\', 52076, 37396, 23827, 9251, 61380, 46736, 29179, 14481, 33750, 19092, 5197, 57166, 42596, 24843, 10294, 62251, 47845, 1511, 52466, 38795, 24231, 6587, 58182, 30898, 49579, 2715, 21475, 40164, 58836, 11827, 30567, 45067, 63860, 17021, 35653, 54700, 7852, 26517, 41212, 59805, 13046, 31546, 50207, 3352, 22130, 40773, 42262, 7183, 55103, 36423, 16704, 14448, 62359, 43715, 28072, 9410, 40837, 22212, 2060, 49936, 47661, 32094, 13379, 61280, 42632, 6590, 'a', 47480, 29256, 11056, 58423, 40199, 22240, 4020, 51422, 33187, 15016, 62428, 44381, 26220, 8029, 49716, 45998, 2762, 64061, 17184, 34829, 53620, 7713, 26448, 44220, 62947, 12947, 31740, 49403, 2497, 22326, 39995, '/', 47477, 29261, 11041, 58416, 40203, 13489, 36259, 18076, 8065, 53428, 43483, 25139, 15147, 64517, 46448, 'C', 47486, 29257, 11041, 58419, 40221, 22257, 4087, 51445, 33214, 15039, 62359, 44408, 26218, 8029, 55333, 37134, 50529, 31824, 46946, 60937, 8474, 22573, 37763, 51913, 3576, 17601, 65436, 14000, 26715, 41753, 55920, 7436, 21540, 36665, 50894, 31141, 45295, 60296, 8887, 26037, 40727, 54902, 2418, 16385, 64295, 12921, 30166, 44277, 59268, 7828, 20923, 35665, 49750, 1382, 48141, 63303, 11895, 17313, 64181, 12702, 26829, 42992, 57051, 5430, 19517, 35610, 49772, 31103, 45136, 61071, 9640, 23682, 39904, 53967, 2515, 16427, 65296, 13847, 28007, 42060, 58191, 48803, 1950, 52393, 38337, 23251, 9213, 59409, 45335, 30210, 16195, 33882, 19838, 5005, 55453, 41376, 26351, 12280, 62709, 48393, 562, 52012, 36935, 22891, 7796, 48359, 1494, 52964, 38799, 22684, 8619, 59909, 45906, 29823, 15633, 34322, 20280, 4556, 55967, 41959, 25742, 11687, 63163, 48968, '`', 51565, 37386, 23329, 7291, 59100, 45026, 28905, 14747, 33446, 19387, 3093, 54652, 40456, 26371, 10277, 62083, 48092, 31991, 50566, 36510, 22433, 6227, 57684, 43644, 29447, 13381, 64885};

    /* renamed from: ǃ */
    private static int f567 = 0;

    /* renamed from: Ι */
    private static long f568 = 5583085843407419670L;

    /* renamed from: ι */
    private static int f569 = 1;

    C0937c() {
    }

    @Nullable
    /* renamed from: ı */
    public static String m360(Context context, long j) {
        String intern;
        String intern2;
        String intern3;
        String intern4;
        String intern5;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        if (m361(m365(0, 61066, 34).intern())) {
            int i = f567 + 73;
            f569 = i % 128;
            intern = i % 2 == 0 ? m365(113, 52400, 0).intern() : m365(34, 52400, 1).intern();
        } else {
            intern = m365(35, 60972, 1).intern();
        }
        sb2.append(intern);
        StringBuilder sb4 = new StringBuilder();
        String packageName = context.getPackageName();
        String r0 = m364(packageName);
        sb2.append(m365(34, 52400, 1).intern());
        sb4.append(r0);
        switch (m359(context) == null) {
            case true:
                sb2.append(m365(35, 60972, 1).intern());
                sb4.append(packageName);
                break;
            default:
                sb2.append(m365(34, 52400, 1).intern());
                sb4.append(packageName);
                break;
        }
        String r8 = m362(context);
        switch (r8 == null ? '^' : '5') {
            case '5':
                sb2.append(m365(34, 52400, 1).intern());
                sb4.append(r8);
                break;
            default:
                sb2.append(m365(35, 60972, 1).intern());
                sb4.append(packageName);
                break;
        }
        sb4.append(m363(context, packageName));
        sb.append(sb4.toString());
        try {
            sb.append(new SimpleDateFormat(m365(36, 4926, 18).intern(), Locale.US).format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime)));
            sb.append(j);
            switch (!m361(m365(86, 47396, 25).intern())) {
                case false:
                    intern2 = m365(34, 52400, 1).intern();
                    break;
                default:
                    intern2 = m365(35, 60972, 1).intern();
                    int i2 = f569 + 91;
                    f567 = i2 % 128;
                    if (i2 % 2 != 0) {
                    }
                    break;
            }
            sb3.append(intern2);
            switch (m361(m365(111, 30931, 23).intern()) ? 4 : '/') {
                case 4:
                    intern3 = m365(34, 52400, 1).intern();
                    break;
                default:
                    intern3 = m365(35, 60972, 1).intern();
                    break;
            }
            sb3.append(intern3);
            if (m361(m365(134, 42359, 20).intern())) {
                int i3 = f569 + 105;
                f567 = i3 % 128;
                if (i3 % 2 != 0) {
                }
                intern4 = m365(34, 52400, 1).intern();
            } else {
                intern4 = m365(35, 60972, 1).intern();
            }
            sb3.append(intern4);
            switch (m361(m365(154, 0, 15).intern())) {
                case true:
                    int i4 = f569 + 91;
                    f567 = i4 % 128;
                    switch (i4 % 2 != 0 ? '4' : 'b') {
                        case '4':
                            intern5 = m365(97, 52400, 0).intern();
                            break;
                        default:
                            intern5 = m365(34, 52400, 1).intern();
                            break;
                    }
                default:
                    intern5 = m365(35, 60972, 1).intern();
                    break;
            }
            sb3.append(intern5);
            String r02 = C0968z.m410(C0968z.m411(sb.toString()));
            String obj = sb2.toString();
            StringBuilder sb5 = new StringBuilder(r02);
            sb5.setCharAt(17, Integer.toString(Integer.parseInt(obj, 2), 16).charAt(0));
            String obj2 = sb5.toString();
            String obj3 = sb3.toString();
            StringBuilder sb6 = new StringBuilder(obj2);
            sb6.setCharAt(27, Integer.toString(Integer.parseInt(obj3, 2), 16).charAt(0));
            return m366(sb6.toString(), Long.valueOf(j));
        } catch (PackageManager.NameNotFoundException e) {
            return m365(54, 21504, 32).intern();
        }
    }

    /* renamed from: ι */
    private static String m366(String str, Long l) {
        switch (str == null) {
            case true:
                break;
            default:
                int i = f569 + 125;
                f567 = i % 128;
                if (i % 2 != 0) {
                }
                switch (l == null) {
                    case true:
                        break;
                    default:
                        int i2 = f567 + 59;
                        f569 = i2 % 128;
                        if (i2 % 2 == 0) {
                        }
                        if (str.length() == 32) {
                            StringBuilder sb = new StringBuilder(str);
                            String obj = l.toString();
                            int i3 = 0;
                            for (int i4 = 0; i4 < obj.length(); i4++) {
                                i3 += Character.getNumericValue(obj.charAt(i4));
                            }
                            String hexString = Integer.toHexString(i3);
                            sb.replace(7, hexString.length() + 7, hexString);
                            long j = 0;
                            for (int i5 = 0; i5 < sb.length(); i5++) {
                                j += (long) Character.getNumericValue(sb.charAt(i5));
                            }
                            while (true) {
                                switch (j > 100) {
                                    case true:
                                        j %= 100;
                                    default:
                                        sb.insert(23, (int) j);
                                        switch (j < 10 ? 'Q' : '`') {
                                            case '`':
                                                break;
                                            default:
                                                sb.insert(23, m365(35, 60972, 1).intern());
                                                break;
                                        }
                                        return sb.toString();
                                }
                            }
                        }
                        break;
                }
        }
        return m365(54, 21504, 32).intern();
    }

    /* renamed from: ı */
    private static boolean m361(String str) {
        int i = f567 + 89;
        f569 = i % 128;
        if (i % 2 == 0) {
        }
        try {
            Class.forName(str);
            int i2 = f569 + 49;
            f567 = i2 % 128;
            if (i2 % 2 != 0) {
            }
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: Ι */
    private static String m364(String str) {
        boolean z;
        int i = f569 + 65;
        f567 = i % 128;
        if (i % 2 != 0) {
        }
        switch (str.contains(m365(169, 49690, 1).intern())) {
            case false:
                return str;
            default:
                String[] split = str.split(m365(170, 46066, 2).intern());
                int length = split.length;
                StringBuilder sb = new StringBuilder();
                sb.append(split[length - 1]).append(m365(169, 49690, 1).intern());
                int i2 = f567 + 103;
                f569 = i2 % 128;
                if (i2 % 2 == 0) {
                }
                int i3 = 1;
                while (true) {
                    if (i3 < length - 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    switch (z) {
                        case false:
                            sb.append(split[0]);
                            return sb.toString();
                        default:
                            int i4 = f567 + 61;
                            f569 = i4 % 128;
                            if (i4 % 2 == 0) {
                            }
                            sb.append(split[i3]).append(m365(169, 49690, 1).intern());
                            i3++;
                    }
                }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0075  */
    /* renamed from: ı */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m359(android.content.Context r10) {
        /*
            r2 = 41
            r0 = 0
            r9 = 1
            r8 = 0
            java.util.Properties r1 = java.lang.System.getProperties()
            r3 = 172(0xac, float:2.41E-43)
            r4 = 64087(0xfa57, float:8.9805E-41)
            r5 = 14
            java.lang.String r3 = m365(r3, r4, r5)
            java.lang.String r3 = r3.intern()
            boolean r1 = r1.containsKey(r3)
            if (r1 == 0) goto L_0x00d3
            r1 = 46
        L_0x0020:
            switch(r1) {
                case 67: goto L_0x0069;
                default: goto L_0x0023;
            }
        L_0x0023:
            int r1 = f567
            int r1 = r1 + 51
            int r3 = r1 % 128
            f569 = r3
            int r1 = r1 % 2
            if (r1 != 0) goto L_0x002f
        L_0x002f:
            java.io.File r1 = r10.getCacheDir()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r1 = r1.getPath()     // Catch:{ Exception -> 0x0070 }
            r3 = 186(0xba, float:2.6E-43)
            r4 = 0
            r5 = 6
            java.lang.String r3 = m365(r3, r4, r5)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r3 = r3.intern()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r4 = ""
            java.lang.String r1 = r1.replace(r3, r4)     // Catch:{ Exception -> 0x0070 }
            r3 = 192(0xc0, float:2.69E-43)
            r4 = 13471(0x349f, float:1.8877E-41)
            r5 = 10
            java.lang.String r3 = m365(r3, r4, r5)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r3 = r3.intern()     // Catch:{ Exception -> 0x0070 }
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)     // Catch:{ Exception -> 0x0070 }
            java.util.regex.Matcher r3 = r3.matcher(r1)     // Catch:{ Exception -> 0x0070 }
            boolean r1 = r3.find()     // Catch:{ Exception -> 0x0070 }
            if (r1 == 0) goto L_0x00da
            r1 = r2
        L_0x0066:
            switch(r1) {
                case 41: goto L_0x00bc;
                default: goto L_0x0069;
            }     // Catch:{ Exception -> 0x0070 }
        L_0x0069:
            return r0
        L_0x006a:
            r1 = 1
            java.lang.String r0 = r3.group(r1)     // Catch:{ Exception -> 0x0070 }
            goto L_0x0069
        L_0x0070:
            r1 = move-exception
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            if (r3 != 0) goto L_0x0088
            com.appsflyer.internal.ai r3 = new com.appsflyer.internal.ai
            r3.<init>()
            com.appsflyer.internal.C0928ai.f525 = r3
            int r3 = f567
            int r3 = r3 + 87
            int r4 = r3 % 128
            f569 = r4
            int r3 = r3 % 2
            if (r3 != 0) goto L_0x0088
        L_0x0088:
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            r4 = 202(0xca, float:2.83E-43)
            r5 = 17
            java.lang.String r4 = m365(r4, r8, r5)
            java.lang.String r4 = r4.intern()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = 219(0xdb, float:3.07E-43)
            r7 = 50471(0xc527, float:7.0725E-41)
            java.lang.String r2 = m365(r6, r7, r2)
            java.lang.String r2 = r2.intern()
            java.lang.StringBuilder r2 = r5.append(r2)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            java.lang.String[] r2 = new java.lang.String[r9]
            r2[r8] = r1
            r3.mo14690(r0, r4, r2)
            goto L_0x0069
        L_0x00bc:
            int r1 = f569
            int r1 = r1 + 89
            int r4 = r1 % 128
            f567 = r4
            int r1 = r1 % 2
            if (r1 == 0) goto L_0x00d7
            r1 = 20
        L_0x00ca:
            switch(r1) {
                case 93: goto L_0x006a;
                default: goto L_0x00cd;
            }
        L_0x00cd:
            r1 = 1
            java.lang.String r0 = r3.group(r1)     // Catch:{ Exception -> 0x0070 }
            goto L_0x0069
        L_0x00d3:
            r1 = 67
            goto L_0x0020
        L_0x00d7:
            r1 = 93
            goto L_0x00ca
        L_0x00da:
            r1 = 99
            goto L_0x0066
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0937c.m359(android.content.Context):java.lang.String");
    }

    /* renamed from: ǃ */
    private static String m362(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            int i = f567 + 83;
            f569 = i % 128;
            if (i % 2 == 0) {
            }
            int i2 = f569 + 79;
            f567 = i2 % 128;
            if (i2 % 2 != 0) {
            }
            return str;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0055 A[LOOP_START, SYNTHETIC, Splitter:B:7:0x0055] */
    /* renamed from: Ι */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m363(android.content.Context r11, java.lang.String r12) {
        /*
            r10 = 284(0x11c, float:3.98E-43)
            r9 = 47
            r8 = 24
            r1 = 1
            r2 = 0
            int r0 = f569
            int r0 = r0 + 27
            int r3 = r0 % 128
            f567 = r3
            int r0 = r0 % 2
            if (r0 == 0) goto L_0x0014
        L_0x0014:
            android.content.pm.PackageManager r0 = r11.getPackageManager()
            java.lang.Class<android.content.pm.PackageManager> r3 = android.content.pm.PackageManager.class
            r4 = 260(0x104, float:3.64E-43)
            r5 = 17350(0x43c6, float:2.4313E-41)
            r6 = 24
            java.lang.String r4 = m365(r4, r5, r6)     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.lang.String r4 = r4.intern()     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            r5 = 1
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            r6 = 0
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            r5[r6] = r7     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            r5 = 0
            r6 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            r4[r5] = r6     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.lang.Object r0 = r3.invoke(r0, r4)     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            int r0 = f569
            int r0 = r0 + 1
            int r4 = r0 % 128
            f567 = r4
            int r0 = r0 % 2
            if (r0 == 0) goto L_0x0055
        L_0x0055:
            boolean r0 = r3.hasNext()     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            if (r0 == 0) goto L_0x0172
            r0 = 55
        L_0x005d:
            switch(r0) {
                case 55: goto L_0x0067;
                default: goto L_0x0060;
            }
        L_0x0060:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            java.lang.String r0 = r0.toString()
        L_0x0066:
            return r0
        L_0x0067:
            java.lang.Object r0 = r3.next()     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            android.content.pm.ApplicationInfo r0 = (android.content.pm.ApplicationInfo) r0     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.lang.String r0 = r0.packageName     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            boolean r0 = r0.equals(r12)     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            if (r0 == 0) goto L_0x016f
            r0 = r1
        L_0x0076:
            switch(r0) {
                case 0: goto L_0x0055;
                default: goto L_0x0079;
            }
        L_0x0079:
            int r0 = f567
            int r0 = r0 + 101
            int r3 = r0 % 128
            f569 = r3
            int r0 = r0 % 2
            if (r0 != 0) goto L_0x0090
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            r3 = 75
            int r1 = r3 / 0
            goto L_0x0066
        L_0x0090:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalAccessException -> 0x0097, NoSuchMethodException -> 0x00d7, InvocationTargetException -> 0x0129 }
            goto L_0x0066
        L_0x0097:
            r0 = move-exception
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            if (r3 != 0) goto L_0x00a3
            com.appsflyer.internal.ai r3 = new com.appsflyer.internal.ai
            r3.<init>()
            com.appsflyer.internal.C0928ai.f525 = r3
        L_0x00a3:
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            r4 = 48864(0xbee0, float:6.8473E-41)
            java.lang.String r4 = m365(r10, r4, r8)
            java.lang.String r4 = r4.intern()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = 308(0x134, float:4.32E-43)
            r7 = 48289(0xbca1, float:6.7667E-41)
            java.lang.String r6 = m365(r6, r7, r9)
            java.lang.String r6 = r6.intern()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r0 = r5.append(r0)
            java.lang.String r0 = r0.toString()
            r5 = 0
            java.lang.String[] r1 = new java.lang.String[r1]
            r1[r2] = r0
            r3.mo14690(r5, r4, r1)
            goto L_0x0060
        L_0x00d7:
            r0 = move-exception
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            if (r3 != 0) goto L_0x00f4
            com.appsflyer.internal.ai r3 = new com.appsflyer.internal.ai
            r3.<init>()
            com.appsflyer.internal.C0928ai.f525 = r3
            int r3 = f567
            int r3 = r3 + 25
            int r4 = r3 % 128
            f569 = r4
            int r3 = r3 % 2
            if (r3 != 0) goto L_0x016c
            r3 = 32
        L_0x00f1:
            switch(r3) {
                case 61: goto L_0x00f4;
                default: goto L_0x00f4;
            }
        L_0x00f4:
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            r4 = 48864(0xbee0, float:6.8473E-41)
            java.lang.String r4 = m365(r10, r4, r8)
            java.lang.String r4 = r4.intern()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = 308(0x134, float:4.32E-43)
            r7 = 48289(0xbca1, float:6.7667E-41)
            java.lang.String r6 = m365(r6, r7, r9)
            java.lang.String r6 = r6.intern()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r0 = r5.append(r0)
            java.lang.String r0 = r0.toString()
            r5 = 0
            java.lang.String[] r1 = new java.lang.String[r1]
            r1[r2] = r0
            r3.mo14690(r5, r4, r1)
            goto L_0x0060
        L_0x0129:
            r0 = move-exception
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            if (r3 != 0) goto L_0x0135
            com.appsflyer.internal.ai r3 = new com.appsflyer.internal.ai
            r3.<init>()
            com.appsflyer.internal.C0928ai.f525 = r3
        L_0x0135:
            com.appsflyer.internal.ai r3 = com.appsflyer.internal.C0928ai.f525
            r4 = 48864(0xbee0, float:6.8473E-41)
            java.lang.String r4 = m365(r10, r4, r8)
            java.lang.String r4 = r4.intern()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = 308(0x134, float:4.32E-43)
            r7 = 48289(0xbca1, float:6.7667E-41)
            java.lang.String r6 = m365(r6, r7, r9)
            java.lang.String r6 = r6.intern()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r0 = r5.append(r0)
            java.lang.String r0 = r0.toString()
            r5 = 0
            java.lang.String[] r1 = new java.lang.String[r1]
            r1[r2] = r0
            r3.mo14690(r5, r4, r1)
            goto L_0x0060
        L_0x016a:
            r0 = move-exception
            throw r0
        L_0x016c:
            r3 = 61
            goto L_0x00f1
        L_0x016f:
            r0 = r2
            goto L_0x0076
        L_0x0172:
            r0 = 7
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0937c.m363(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: com.appsflyer.internal.c$a */
    public static class C0938a extends HashMap<String, Object> {

        /* renamed from: ǃ */
        private static long f570 = -8771784815112425056L;

        /* renamed from: Ι */
        private static int f571 = 0;

        /* renamed from: ι */
        private static char[] f572 = {'a', 61894, 58143, 54420, 50921, 47181, 43429, 39699, 36212, 32449, 28717, 24976, 'b', 61906, 58145, 54414, 50916, 'N', 61935, 58143, 54434, 50898, 47201, 43406, 39716, 'k', 61893, 58150, 'f', 61889, 58153, 54412, 50917, 47172, 43488, 39687, 36197, 32462, 28709, 24978, 21473, 17748, 13993, 10254, 6759, 2944, 64811, 61061, 57574, 53760, 50091, 46341, 42873, 39040, 35383, 31625, 28148, 24392, 20704, 16901, 13432, 9667, 5925, 2192, 64244, 60489, 56751, 53006, 49466, 45696, 14536, 51558, 56197, 60538, 65050, 32954, 37210, 'f', 61897, 58162, 54419, 50932, 47212, 43425, 39701, 36206, 32451, 28712, 24996, 21473, 17748, 13989, '5', 61838, 58228, 54478, 50864, 24553, 44566, 48359, 35594, 39278, 14334, 50777, 54449, 58132, 61821, 36828, 40568, 44191, 47869, 18774, 18365, 22026, 25721, 29388, 305, 8086, 11775, 15384, 51891, 55581, 55166, 58776, 62510, 33433, 37108, 44877, 48573, 19544, 23151, 26833, 26412, 30096, 952, 4701, 8352, 16155, 52605, 56264, 59948, 63633, 63223, 34134, 37858, 41560, 51824, 15315, 10544, 7831, 3315, 29271, 25570, 20807, 18214, 46210, 47670, 43979, 39331, 36692, 64736, 57922, 53283, 49546, 'a', 61902, 58148, 54418, 50927, 47177, 43428, 39758, 36201, 32462, 28724, 24965, 21486, 17748, 14062, 10241, 6755, 3028, 64809, 61071, 57582, 53774, 50050, 46369, 42836, 39156, 35333, 31666, 28121, 24447, 20611, 16936, 13377, 9710, 5895, 2213, 64196, 't', 61893, 58157, 54416, 50917, 47186, 43425, 39700, 36213, 32466, 28709, 64752, 3344, 8190, 26734, 39384, 35635, 48270, 44786, 53327, 24021, 33232, 28718, '&', 61907, '&', 61904, 'f', 61840, 58161, 54480, 50929, 47121, 43440, 39761, 36208, 32402, 28786, 24963, 21480, 17748, 13985, 10253};

        /* renamed from: І */
        private static int f573 = 1;

        /* renamed from: ı */
        private final Map<String, Object> f574;

        /* renamed from: ɩ */
        private final Context f575;

        public C0938a(Map<String, Object> map, Context context) {
            this.f574 = map;
            this.f575 = context;
            put(m369(), m367());
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x005c  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x008a  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x004b A[SYNTHETIC] */
        /* renamed from: Ι */
        private static java.lang.StringBuilder m370(@android.support.annotation.NonNull java.lang.String... r11) throws java.lang.Exception {
            /*
                r5 = 0
                r10 = 3
                r2 = 0
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>()
                r1 = r2
            L_0x0009:
                if (r1 >= r10) goto L_0x008c
                r0 = 22
            L_0x000d:
                switch(r0) {
                    case 13: goto L_0x0021;
                    default: goto L_0x0010;
                }
            L_0x0010:
                r0 = r11[r1]
                int r0 = r0.length()
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                r3.add(r0)
                int r0 = r1 + 1
                r1 = r0
                goto L_0x0009
            L_0x0021:
                java.util.Collections.sort(r3)
                java.lang.Object r0 = r3.get(r2)
                java.lang.Integer r0 = (java.lang.Integer) r0
                int r7 = r0.intValue()
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r8.<init>()
                r6 = r2
            L_0x0034:
                if (r6 >= r7) goto L_0x0090
                int r0 = f571
                int r0 = r0 + 105
                int r1 = r0 % 128
                f573 = r1
                int r0 = r0 % 2
                if (r0 != 0) goto L_0x005a
                r0 = r11
            L_0x0043:
                r3 = r2
                r4 = r5
            L_0x0045:
                if (r3 >= r10) goto L_0x008a
                r1 = 1
            L_0x0048:
                switch(r1) {
                    case 1: goto L_0x005c;
                    default: goto L_0x004b;
                }
            L_0x004b:
                int r0 = r4.intValue()
                java.lang.String r0 = java.lang.Integer.toHexString(r0)
                r8.append(r0)
                int r0 = r6 + 1
                r6 = r0
                goto L_0x0034
            L_0x005a:
                r0 = r11
                goto L_0x0043
            L_0x005c:
                r1 = r0[r3]
                char r1 = r1.charAt(r6)
                if (r4 != 0) goto L_0x0084
                int r4 = f571
                int r4 = r4 + 95
                int r9 = r4 % 128
                f573 = r9
                int r4 = r4 % 2
                if (r4 != 0) goto L_0x0070
            L_0x0070:
                java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
                int r1 = r3 + 1
                int r3 = f571
                int r3 = r3 + 87
                int r9 = r3 % 128
                f573 = r9
                int r3 = r3 % 2
                if (r3 != 0) goto L_0x0082
            L_0x0082:
                r3 = r1
                goto L_0x0045
            L_0x0084:
                int r4 = r4.intValue()
                r1 = r1 ^ r4
                goto L_0x0070
            L_0x008a:
                r1 = r2
                goto L_0x0048
            L_0x008c:
                r0 = 13
                goto L_0x000d
            L_0x0090:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0937c.C0938a.m370(java.lang.String[]):java.lang.StringBuilder");
        }

        @NonNull
        /* renamed from: Ι */
        private String m369() {
            String str;
            boolean z;
            boolean z2 = true;
            int i = f573 + 49;
            f571 = i % 128;
            if (i % 2 != 0) {
            }
            try {
                String num = Integer.toString(Build.VERSION.SDK_INT);
                String obj = this.f574.get(m368(0, 0, 12).intern()).toString();
                String obj2 = this.f574.get(m368(12, 0, 5).intern()).toString();
                switch (obj2 == null) {
                    case false:
                        str = obj2;
                        break;
                    default:
                        String intern = m368(17, 0, 8).intern();
                        int i2 = f573 + 117;
                        f571 = i2 % 128;
                        switch (i2 % 2 != 0) {
                            case false:
                                str = intern;
                                break;
                            default:
                                str = intern;
                                break;
                        }
                }
                StringBuilder sb = new StringBuilder(obj);
                sb.reverse();
                StringBuilder r4 = m370(num, str, sb.toString());
                int length = r4.length();
                if (length > 4) {
                    int i3 = f573 + 73;
                    f571 = i3 % 128;
                    if (i3 % 2 != 0) {
                        z2 = false;
                    }
                    switch (z2) {
                        case true:
                            r4.delete(4, length);
                            break;
                        default:
                            r4.delete(3, length);
                            break;
                    }
                } else {
                    int i4 = length;
                    while (true) {
                        if (i4 < 4) {
                            z = false;
                        } else {
                            z = true;
                        }
                        switch (z) {
                            case false:
                                int i5 = i4 + 1;
                                r4.append('1');
                                int i6 = f573 + 67;
                                f571 = i6 % 128;
                                i4 = i6 % 2 != 0 ? i5 : i5;
                        }
                    }
                }
                r4.insert(0, m368(25, 0, 3).intern());
                return r4.toString();
            } catch (Exception e) {
                AFLogger.afRDLog(new StringBuilder().append(m368(28, 0, 42).intern()).append(e).toString());
                return m368(70, 14499, 7).intern();
            }
        }

        /* renamed from: ı */
        private String m367() {
            String obj;
            int i;
            int i2;
            int i3;
            boolean z = false;
            try {
                obj = new StringBuilder().append("").append(C0968z.m411(new StringBuilder().append(this.f574.get(m368(0, 0, 12).intern()).toString()).append(this.f574.get(m368(77, 0, 15).intern()).toString()).append(m368(92, 0, 5).intern().replaceAll(m368(97, 24516, 5).intern(), "")).toString()).substring(0, 16)).toString();
            } catch (Exception e) {
                AFLogger.afRDLog(new StringBuilder().append(m368(102, 14232, 44).intern()).append(e).toString());
                obj = new StringBuilder().append("").append(m368(146, 51730, 18).intern()).toString();
            }
            try {
                Intent registerReceiver = this.f575.registerReceiver((BroadcastReceiver) null, new IntentFilter(m368(164, 0, 37).intern()));
                if (registerReceiver != null) {
                    int i4 = f573 + 51;
                    f571 = i4 % 128;
                    i = i4 % 2 != 0 ? registerReceiver.getIntExtra(m368(15579, 0, 109).intern(), 25996) : registerReceiver.getIntExtra(m368(201, 0, 11).intern(), -2700);
                } else {
                    i = -2700;
                }
                String str = this.f575.getApplicationInfo().nativeLibraryDir;
                switch (str != null ? '0' : 'V') {
                    case '0':
                        int i5 = f573 + 15;
                        f571 = i5 % 128;
                        if (i5 % 2 != 0) {
                        }
                        if (str.contains(m368(212, 64648, 3).intern())) {
                            int i6 = f573 + 111;
                            f571 = i6 % 128;
                            switch (i6 % 2 != 0 ? '4' : 'O') {
                                case 'O':
                                    i2 = 1;
                                    break;
                                default:
                                    i2 = 0;
                                    break;
                            }
                            i3 = i2;
                            break;
                        }
                        break;
                }
                i3 = 0;
                String obj2 = new StringBuilder().append(obj).append(C0939c.m375(C0939c.m373(C0939c.m372(new StringBuilder().append(m368(221, 23991, 1).intern()).append(i).append(m368(PermissionsList.PERMISSION_CAMERA_REQUEST_CODE, 33270, 2).intern()).append(i3).append(m368(224, 0, 2).intern()).append(((SensorManager) this.f575.getSystemService(m368(215, 26653, 6).intern())).getSensorList(-1).size()).append(m368(226, 0, 2).intern()).append(this.f574.size()).toString())))).toString();
                int i7 = f573 + 13;
                f571 = i7 % 128;
                if (i7 % 2 == 0) {
                    z = true;
                }
                switch (z) {
                }
                return obj2;
            } catch (Exception e2) {
                AFLogger.afRDLog(new StringBuilder().append(m368(102, 14232, 44).intern()).append(e2).toString());
                return new StringBuilder().append(obj).append(m368(228, 0, 16).intern()).toString();
            }
        }

        /* renamed from: com.appsflyer.internal.c$a$c */
        public static class C0939c {

            /* renamed from: ı */
            private final Object f576;

            /* renamed from: ɩ */
            public String f577;

            /* renamed from: Ι */
            private long f578;

            C0939c() {
            }

            @NonNull
            /* renamed from: ɩ */
            static byte[] m372(@NonNull String str) throws Exception {
                return str.getBytes();
            }

            /* renamed from: Ι */
            static byte[] m373(@NonNull byte[] bArr) throws Exception {
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = (byte) (bArr[i] ^ ((i % 2) + 42));
                }
                return bArr;
            }

            @NonNull
            /* renamed from: ι */
            static String m375(@NonNull byte[] bArr) throws Exception {
                StringBuilder sb = new StringBuilder();
                for (byte hexString : bArr) {
                    String hexString2 = Integer.toHexString(hexString);
                    if (hexString2.length() == 1) {
                        hexString2 = AppEventsConstants.EVENT_PARAM_VALUE_NO.concat(String.valueOf(hexString2));
                    }
                    sb.append(hexString2);
                }
                return sb.toString();
            }

            public C0939c(long j, String str) {
                this.f576 = new Object();
                this.f578 = 0;
                this.f577 = "";
                this.f578 = j;
                this.f577 = str;
            }

            @NonNull
            /* renamed from: ι */
            public static C0939c m374(String str) {
                if (str == null) {
                    return new C0939c(0, "");
                }
                String[] split = str.split(",");
                return split.length < 2 ? new C0939c(0, "") : new C0939c(Long.parseLong(split[0]), split[1]);
            }

            /* renamed from: ɩ */
            public final boolean mo14710(C0939c cVar) {
                return m371(cVar.f578, cVar.f577);
            }

            /* renamed from: ı */
            private boolean m371(long j, String str) {
                boolean z;
                synchronized (this.f576) {
                    if (str != null) {
                        if (!str.equals(this.f577) && m376(j)) {
                            this.f578 = j;
                            this.f577 = str;
                            z = true;
                        }
                    }
                    z = false;
                }
                return z;
            }

            /* renamed from: ι */
            private boolean m376(long j) {
                return j - this.f578 > 2000;
            }

            public final String toString() {
                return new StringBuilder().append(this.f578).append(",").append(this.f577).toString();
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0048 A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:4:0x0013  */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x0017  */
        /* renamed from: ɩ */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static java.lang.String m368(int r12, char r13, int r14) {
            /*
                r1 = 1
                r2 = 0
                int r0 = f571
                int r0 = r0 + 19
                int r3 = r0 % 128
                f573 = r3
                int r0 = r0 % 2
                if (r0 != 0) goto L_0x000e
            L_0x000e:
                char[] r4 = new char[r14]
                r3 = r2
            L_0x0011:
                if (r3 >= r14) goto L_0x0050
                r0 = r1
            L_0x0014:
                switch(r0) {
                    case 0: goto L_0x0048;
                    default: goto L_0x0017;
                }
            L_0x0017:
                int r0 = f573
                int r0 = r0 + 95
                int r5 = r0 % 128
                f571 = r5
                int r0 = r0 % 2
                if (r0 == 0) goto L_0x0023
            L_0x0023:
                char[] r0 = f572
                int r5 = r12 + r3
                char r0 = r0[r5]
                long r6 = (long) r0
                long r8 = (long) r3
                long r10 = f570
                long r8 = r8 * r10
                long r6 = r6 ^ r8
                long r8 = (long) r13
                long r6 = r6 ^ r8
                int r0 = (int) r6
                char r0 = (char) r0
                r4[r3] = r0
                int r3 = r3 + 1
                int r0 = f571
                int r0 = r0 + 125
                int r5 = r0 % 128
                f573 = r5
                int r0 = r0 % 2
                if (r0 != 0) goto L_0x004e
                r0 = r2
            L_0x0044:
                switch(r0) {
                    case 1: goto L_0x0011;
                    default: goto L_0x0047;
                }
            L_0x0047:
                goto L_0x0011
            L_0x0048:
                java.lang.String r0 = new java.lang.String
                r0.<init>(r4)
                return r0
            L_0x004e:
                r0 = r1
                goto L_0x0044
            L_0x0050:
                r0 = r2
                goto L_0x0014
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0937c.C0938a.m368(int, char, int):java.lang.String");
        }
    }

    /* renamed from: ι */
    private static String m365(int i, char c, int i2) {
        int i3;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (true) {
            switch (i4 < i2 ? 'J' : 'Y') {
                case 'Y':
                    return new String(cArr);
                default:
                    int i5 = f569 + 85;
                    f567 = i5 % 128;
                    if (i5 % 2 != 0) {
                        cArr[i4] = (char) ((int) ((((long) f566[i + i4]) | (((long) i4) % f568)) * ((long) c)));
                        i3 = i4 + 37;
                    } else {
                        cArr[i4] = (char) ((int) ((((long) f566[i + i4]) ^ (((long) i4) * f568)) ^ ((long) c)));
                        i3 = i4 + 1;
                    }
                    int i6 = f569 + 109;
                    f567 = i6 % 128;
                    switch (i6 % 2 != 0 ? '\"' : '&') {
                        case '&':
                            i4 = i3;
                            break;
                        default:
                            i4 = i3;
                            break;
                    }
            }
        }
    }
}
