package com.facebook.internal.instrument.crashreport;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class CrashReportData {
    private static final String PARAM_APP_VERSION = "app_version";
    private static final String PARAM_CALLSTACK = "callstack";
    private static final String PARAM_DEVICE_MODEL = "device_model";
    private static final String PARAM_DEVICE_OS = "device_os_version";
    private static final String PARAM_REASON = "reason";
    private static final String PARAM_TIMESTAMP = "timestamp";
    @Nullable
    private String appVersion;
    @Nullable
    private String cause;
    private String filename;
    @Nullable
    private String stackTrace;
    @Nullable
    private Long timestamp;

    public CrashReportData(Throwable e) {
        this.appVersion = Utility.getAppVersion();
        this.cause = InstrumentUtility.getCause(e);
        this.stackTrace = InstrumentUtility.getStackTrace(e);
        this.timestamp = Long.valueOf(System.currentTimeMillis() / 1000);
        this.filename = new StringBuffer().append(InstrumentUtility.CRASH_REPORT_PREFIX).append(this.timestamp.toString()).append(".json").toString();
    }

    public CrashReportData(File file) {
        this.filename = file.getName();
        JSONObject object = InstrumentUtility.readFile(this.filename, true);
        if (object != null) {
            this.appVersion = object.optString(PARAM_APP_VERSION, (String) null);
            this.cause = object.optString(PARAM_REASON, (String) null);
            this.stackTrace = object.optString(PARAM_CALLSTACK, (String) null);
            this.timestamp = Long.valueOf(object.optLong("timestamp", 0));
        }
    }

    public int compareTo(CrashReportData data) {
        if (this.timestamp == null) {
            return -1;
        }
        if (data.timestamp == null) {
            return 1;
        }
        return data.timestamp.compareTo(this.timestamp);
    }

    public boolean isValid() {
        return (this.stackTrace == null || this.timestamp == null) ? false : true;
    }

    public void save() {
        if (isValid()) {
            InstrumentUtility.writeFile(this.filename, toString());
        }
    }

    public void clear() {
        InstrumentUtility.deleteFile(this.filename);
    }

    @Nullable
    public String toString() {
        JSONObject params = getParameters();
        if (params == null) {
            return null;
        }
        return params.toString();
    }

    @Nullable
    public JSONObject getParameters() {
        JSONObject object = new JSONObject();
        try {
            object.put(PARAM_DEVICE_OS, Build.VERSION.RELEASE);
            object.put(PARAM_DEVICE_MODEL, Build.MODEL);
            if (this.appVersion != null) {
                object.put(PARAM_APP_VERSION, this.appVersion);
            }
            if (this.timestamp != null) {
                object.put("timestamp", this.timestamp);
            }
            if (this.cause != null) {
                object.put(PARAM_REASON, this.cause);
            }
            if (this.stackTrace == null) {
                return object;
            }
            object.put(PARAM_CALLSTACK, this.stackTrace);
            return object;
        } catch (JSONException e) {
            return null;
        }
    }
}
