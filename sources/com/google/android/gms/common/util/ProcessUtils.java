package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class ProcessUtils {
    private static String zzhv = null;
    private static int zzhw = 0;

    private ProcessUtils() {
    }

    @KeepForSdk
    @Nullable
    public static String getMyProcessName() {
        if (zzhv == null) {
            if (zzhw == 0) {
                zzhw = Process.myPid();
            }
            zzhv = zzd(zzhw);
        }
        return zzhv;
    }

    @Nullable
    private static String zzd(int i) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2;
        String str = null;
        if (i > 0) {
            try {
                bufferedReader2 = zzk(new StringBuilder(25).append("/proc/").append(i).append("/cmdline").toString());
                try {
                    str = bufferedReader2.readLine().trim();
                    IOUtils.closeQuietly((Closeable) bufferedReader2);
                } catch (IOException e) {
                    IOUtils.closeQuietly((Closeable) bufferedReader2);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    IOUtils.closeQuietly((Closeable) bufferedReader);
                    throw th;
                }
            } catch (IOException e2) {
                bufferedReader2 = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                IOUtils.closeQuietly((Closeable) bufferedReader);
                throw th;
            }
        }
        return str;
    }

    private static BufferedReader zzk(String str) throws IOException {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
