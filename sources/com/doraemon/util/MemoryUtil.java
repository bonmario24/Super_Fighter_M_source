package com.doraemon.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MemoryUtil {
    public static long getTotalMemory() {
        try {
            String result = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/proc/meminfo")))).readLine();
            StringBuffer sb = new StringBuffer();
            char[] charArray = result.toCharArray();
            int length = charArray.length;
            for (int i = 0; i < length; i++) {
                char c = charArray[i];
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                }
            }
            double resultMem = (double) Long.valueOf(sb.toString()).longValue();
            if (resultMem > 900.0d) {
                resultMem /= (double) 1024;
            }
            return Math.round(resultMem);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getAvailMemory(Context context) {
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(outInfo);
        double result = (double) outInfo.availMem;
        if (result > 900.0d) {
            result /= 1024.0d;
        }
        if (result > 900.0d) {
            result /= 1024.0d;
        }
        return Math.round(result);
    }

    public static long getTotalInternalMemorySize() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        return Math.round(((double) (((long) stat.getBlockCount()) * ((long) stat.getBlockSize()))) / 1048576.0d);
    }

    public static long getAvailableInternalMemorySize() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        return Math.round(((double) (((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize()))) / 1048576.0d);
    }
}
