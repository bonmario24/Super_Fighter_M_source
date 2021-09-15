package com.facebook.internal.instrument.errorreport;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ErrorReportData {
    private static final String PARAM_TIMESTAMP = "timestamp";
    private static final String PRARAM_ERROR_MESSAGE = "error_message";
    @Nullable
    private String errorMessage;
    private String filename;
    @Nullable
    private Long timestamp;

    public ErrorReportData(String message) {
        this.timestamp = Long.valueOf(System.currentTimeMillis() / 1000);
        this.errorMessage = message;
        this.filename = new StringBuffer().append(InstrumentUtility.ERROR_REPORT_PREFIX).append(this.timestamp).append(".json").toString();
    }

    public ErrorReportData(File file) {
        this.filename = file.getName();
        JSONObject object = InstrumentUtility.readFile(this.filename, true);
        if (object != null) {
            this.timestamp = Long.valueOf(object.optLong("timestamp", 0));
            this.errorMessage = object.optString("error_message", (String) null);
        }
    }

    public int compareTo(ErrorReportData data) {
        if (this.timestamp == null) {
            return -1;
        }
        if (data.timestamp == null) {
            return 1;
        }
        return data.timestamp.compareTo(this.timestamp);
    }

    public boolean isValid() {
        return (this.errorMessage == null || this.timestamp == null) ? false : true;
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
            if (this.timestamp != null) {
                object.put("timestamp", this.timestamp);
            }
            object.put("error_message", this.errorMessage);
            return object;
        } catch (JSONException e) {
            return null;
        }
    }
}
