package com.appsflyer.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.appsflyer.AndroidUtils;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class EventDataCollector {

    /* renamed from: ǃ */
    private final Context f490;

    public EventDataCollector(Context context) {
        this.f490 = context;
    }

    public String signature() throws CertificateException, NoSuchAlgorithmException, PackageManager.NameNotFoundException {
        return AndroidUtils.signature(this.f490.getPackageManager(), this.f490.getPackageName());
    }

    public long bootTime() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public String disk() {
        long availableBlocks;
        long blockCount;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= 18) {
            long blockSizeLong = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong() * blockSizeLong;
            blockCount = statFs.getBlockCountLong() * blockSizeLong;
        } else {
            int blockSize = statFs.getBlockSize();
            availableBlocks = (long) (statFs.getAvailableBlocks() * blockSize);
            blockCount = (long) (statFs.getBlockCount() * blockSize);
        }
        double pow = Math.pow(2.0d, 20.0d);
        return new StringBuilder().append((long) (((double) availableBlocks) / pow)).append("/").append((long) (((double) blockCount) / pow)).toString();
    }
}
