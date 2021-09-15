package com.facebook.internal.instrument.errorreport;

import android.support.annotation.RestrictTo;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ErrorReportHandler {
    private static final int MAX_ERROR_REPORT_NUM = 1000;

    public static void save(String msg) {
        try {
            new ErrorReportData(msg).save();
        } catch (Exception e) {
        }
    }

    public static void enable() {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            sendErrorReports();
        }
    }

    public static void sendErrorReports() {
        File[] reports = listErrorReportFiles();
        final ArrayList<ErrorReportData> validReports = new ArrayList<>();
        for (File report : reports) {
            ErrorReportData errorData = new ErrorReportData(report);
            if (errorData.isValid()) {
                validReports.add(errorData);
            }
        }
        Collections.sort(validReports, new Comparator<ErrorReportData>() {
            public int compare(ErrorReportData o1, ErrorReportData o2) {
                return o1.compareTo(o2);
            }
        });
        JSONArray errorLogs = new JSONArray();
        int i = 0;
        while (i < validReports.size() && i < 1000) {
            errorLogs.put(validReports.get(i));
            i++;
        }
        InstrumentUtility.sendReports("error_reports", errorLogs, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse response) {
                try {
                    if (response.getError() == null && response.getJSONObject().getBoolean("success")) {
                        for (int i = 0; validReports.size() > i; i++) {
                            ((ErrorReportData) validReports.get(i)).clear();
                        }
                    }
                } catch (JSONException e) {
                }
            }
        });
    }

    public static File[] listErrorReportFiles() {
        File reportDir = InstrumentUtility.getInstrumentReportDir();
        if (reportDir == null) {
            return new File[0];
        }
        return reportDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(String.format("^%s[0-9]+.json$", new Object[]{InstrumentUtility.ERROR_REPORT_PREFIX}));
            }
        });
    }
}
