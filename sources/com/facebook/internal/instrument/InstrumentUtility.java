package com.facebook.internal.instrument;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.internal.Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class InstrumentUtility {
    public static final String CRASH_REPORT_PREFIX = "crash_log_";
    public static final String ERROR_REPORT_PREFIX = "error_log_";
    private static final String FBSDK_PREFIX = "com.facebook";
    private static final String INSTRUMENT_DIR = "instrument";

    @Nullable
    public static String getCause(Throwable e) {
        if (e == null) {
            return null;
        }
        if (e.getCause() == null) {
            return e.toString();
        }
        return e.getCause().toString();
    }

    @Nullable
    public static String getStackTrace(Throwable e) {
        if (e == null) {
            return null;
        }
        JSONArray array = new JSONArray();
        Throwable previous = null;
        Throwable t = e;
        while (t != null && t != previous) {
            for (StackTraceElement element : t.getStackTrace()) {
                array.put(element.toString());
            }
            previous = t;
            t = t.getCause();
        }
        return array.toString();
    }

    public static boolean isSDKRelatedException(@Nullable Throwable e) {
        if (e == null) {
            return false;
        }
        Throwable previous = null;
        Throwable t = e;
        while (t != null && t != previous) {
            for (StackTraceElement element : t.getStackTrace()) {
                if (element.getClassName().startsWith(FBSDK_PREFIX)) {
                    return true;
                }
            }
            previous = t;
            t = t.getCause();
        }
        return false;
    }

    public static File[] listCrashReportFiles() {
        File reportDir = getInstrumentReportDir();
        if (reportDir == null) {
            return new File[0];
        }
        return reportDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(String.format("^%s[0-9]+.json$", new Object[]{InstrumentUtility.CRASH_REPORT_PREFIX}));
            }
        });
    }

    @Nullable
    public static JSONObject readFile(@Nullable String filename, boolean deleteOnException) {
        File reportDir = getInstrumentReportDir();
        if (reportDir == null || filename == null) {
            return null;
        }
        try {
            return new JSONObject(Utility.readStreamToString(new FileInputStream(new File(reportDir, filename))));
        } catch (Exception e) {
            if (deleteOnException) {
                deleteFile(filename);
            }
            return null;
        }
    }

    public static void writeFile(@Nullable String filename, @Nullable String content) {
        File reportDir = getInstrumentReportDir();
        if (reportDir != null && filename != null && content != null) {
            try {
                FileOutputStream outputStream = new FileOutputStream(new File(reportDir, filename));
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public static boolean deleteFile(@Nullable String filename) {
        File reportDir = getInstrumentReportDir();
        if (reportDir == null || filename == null) {
            return false;
        }
        return new File(reportDir, filename).delete();
    }

    public static void sendReports(String key, JSONArray reports, GraphRequest.Callback callback) {
        if (reports.length() != 0) {
            JSONObject params = new JSONObject();
            try {
                params.put(key, reports.toString());
                GraphRequest.newPostRequest((AccessToken) null, String.format("%s/instruments", new Object[]{FacebookSdk.getApplicationId()}), params, callback).executeAsync();
            } catch (JSONException e) {
            }
        }
    }

    @Nullable
    public static File getInstrumentReportDir() {
        File dir = new File(FacebookSdk.getApplicationContext().getCacheDir(), INSTRUMENT_DIR);
        if (dir.exists() || dir.mkdirs()) {
            return dir;
        }
        return null;
    }
}
