package com.facebook.internal.instrument.crashreport;

import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final int MAX_CRASH_REPORT_NUM = 5;
    private static final String TAG = CrashHandler.class.getCanonicalName();
    @Nullable
    private static CrashHandler instance;
    private boolean mEndApplication = false;
    @Nullable
    private final Thread.UncaughtExceptionHandler mPreviousHandler;

    private CrashHandler(@Nullable Thread.UncaughtExceptionHandler oldHandler) {
        this.mPreviousHandler = oldHandler;
    }

    public void uncaughtException(Thread t, Throwable e) {
        if (InstrumentUtility.isSDKRelatedException(e)) {
            new CrashReportData(e).save();
        }
        if (this.mPreviousHandler != null) {
            this.mPreviousHandler.uncaughtException(t, e);
        }
        if (this.mEndApplication) {
            killProcess();
        }
    }

    public static synchronized void enable() {
        synchronized (CrashHandler.class) {
            if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                sendCrashReports();
            }
            if (instance != null) {
                Log.w(TAG, "Already enabled!");
            } else {
                instance = new CrashHandler(Thread.getDefaultUncaughtExceptionHandler());
                Thread.setDefaultUncaughtExceptionHandler(instance);
            }
        }
    }

    public void endApplication() {
        this.mEndApplication = true;
    }

    private static void killProcess() {
        try {
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Throwable th) {
        }
    }

    private static void sendCrashReports() {
        File[] reports = InstrumentUtility.listCrashReportFiles();
        final ArrayList<CrashReportData> validReports = new ArrayList<>();
        for (File report : reports) {
            CrashReportData crashData = new CrashReportData(report);
            if (crashData.isValid()) {
                validReports.add(crashData);
            }
        }
        Collections.sort(validReports, new Comparator<CrashReportData>() {
            public int compare(CrashReportData o1, CrashReportData o2) {
                return o1.compareTo(o2);
            }
        });
        JSONArray crashLogs = new JSONArray();
        int i = 0;
        while (i < validReports.size() && i < 5) {
            crashLogs.put(validReports.get(i));
            i++;
        }
        InstrumentUtility.sendReports("crash_reports", crashLogs, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse response) {
                try {
                    if (response.getError() == null && response.getJSONObject().getBoolean("success")) {
                        for (int i = 0; validReports.size() > i; i++) {
                            ((CrashReportData) validReports.get(i)).clear();
                        }
                    }
                } catch (JSONException e) {
                }
            }
        });
    }
}
