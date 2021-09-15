package com.doraemon.util;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class DeviceEmuCheckUtil {
    public static String[] known_pipes = {"/dev/socket/qemud", "/dev/qemu_pipe"};
    public static String[] known_qemu_drivers = {"goldfish"};

    public static boolean mayOnEmulator(Context context) {
        if (isEmulatorFromQemuFeatures() || isRunningInEmualtor()) {
            return true;
        }
        int result = 0;
        if (notHasLightSensorManager(context).booleanValue()) {
            result = 0 + 1;
        }
        if (isEmulatorFromCpu()) {
            result++;
        }
        if (hasEth0Interface()) {
            result++;
        }
        if (result <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isEmulatorFromQemuFeatures() {
        return checkPipes() || checkQEmuDriverFile();
    }

    public static boolean isEmulatorFromCpu() {
        return checkIsNotRealPhone() || checkCpuInfo();
    }

    public static boolean checkPipes() {
        for (String pipes : known_pipes) {
            if (new File(pipes).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkQEmuDriverFile() {
        File driver_file = new File("/proc/tty/drivers");
        if (!driver_file.exists() || !driver_file.canRead()) {
            return false;
        }
        byte[] data = new byte[1024];
        try {
            InputStream inStream = new FileInputStream(driver_file);
            inStream.read(data);
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String driver_data = new String(data);
        for (String known_qemu_driver : known_qemu_drivers) {
            if (driver_data.contains(known_qemu_driver)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean notHasLightSensorManager(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        return Boolean.valueOf((sensorManager != null ? sensorManager.getDefaultSensor(5) : null) == null);
    }

    public static boolean isFeatures() {
        if (Build.FINGERPRINT.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) || "google_sdk".equals(Build.PRODUCT))) {
            return true;
        }
        return false;
    }

    public static String readCpuInfo() {
        try {
            Process process = new ProcessBuilder(new String[]{"/system/bin/cat", "/proc/cpuinfo"}).start();
            StringBuffer sb = new StringBuffer();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            while (true) {
                String readLine = responseReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    responseReader.close();
                    return sb.toString().toLowerCase();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static boolean checkIsNotRealPhone() {
        String cpuInfo = readCpuInfo();
        return cpuInfo.contains("intel") || cpuInfo.contains("amd");
    }

    private static boolean checkCpuInfo() {
        String cpuInfo = ShellAdbUtil.execCommand("cat /proc/cpuinfo", false).successMsg;
        if (TextUtils.isEmpty(cpuInfo) || cpuInfo.contains("intel") || cpuInfo.contains("amd")) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060 A[SYNTHETIC, Splitter:B:25:0x0060] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRunningInEmualtor() {
        /*
            r6 = 1
            r5 = 0
            r4 = 0
            r2 = 0
            java.lang.Runtime r7 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0050, all -> 0x005d }
            java.lang.String r8 = "getprop ro.kernel.qemu"
            java.lang.Process r4 = r7.exec(r8)     // Catch:{ Exception -> 0x0050, all -> 0x005d }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0050, all -> 0x005d }
            java.io.OutputStream r7 = r4.getOutputStream()     // Catch:{ Exception -> 0x0050, all -> 0x005d }
            r3.<init>(r7)     // Catch:{ Exception -> 0x0050, all -> 0x005d }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            java.io.InputStream r8 = r4.getInputStream()     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            java.lang.String r9 = "GBK"
            r7.<init>(r8, r9)     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            java.lang.String r7 = "exit\n"
            r3.writeBytes(r7)     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            r3.flush()     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            r4.waitFor()     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            java.lang.String r7 = r1.readLine()     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            int r7 = r7.intValue()     // Catch:{ Exception -> 0x006c, all -> 0x0069 }
            if (r7 != r6) goto L_0x004b
            r5 = r6
        L_0x0041:
            if (r3 == 0) goto L_0x0046
            r3.close()     // Catch:{ Exception -> 0x004d }
        L_0x0046:
            r4.destroy()     // Catch:{ Exception -> 0x004d }
            r2 = r3
        L_0x004a:
            return r5
        L_0x004b:
            r5 = 0
            goto L_0x0041
        L_0x004d:
            r6 = move-exception
            r2 = r3
            goto L_0x004a
        L_0x0050:
            r0 = move-exception
        L_0x0051:
            r5 = 0
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ Exception -> 0x005b }
        L_0x0057:
            r4.destroy()     // Catch:{ Exception -> 0x005b }
            goto L_0x004a
        L_0x005b:
            r6 = move-exception
            goto L_0x004a
        L_0x005d:
            r6 = move-exception
        L_0x005e:
            if (r2 == 0) goto L_0x0063
            r2.close()     // Catch:{ Exception -> 0x0067 }
        L_0x0063:
            r4.destroy()     // Catch:{ Exception -> 0x0067 }
        L_0x0066:
            throw r6
        L_0x0067:
            r7 = move-exception
            goto L_0x0066
        L_0x0069:
            r6 = move-exception
            r2 = r3
            goto L_0x005e
        L_0x006c:
            r0 = move-exception
            r2 = r3
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.DeviceEmuCheckUtil.isRunningInEmualtor():boolean");
    }

    public static boolean hasEth0Interface() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                if (en.nextElement().getName().equals("eth0")) {
                    return true;
                }
            }
        } catch (SocketException e) {
        }
        return false;
    }
}
