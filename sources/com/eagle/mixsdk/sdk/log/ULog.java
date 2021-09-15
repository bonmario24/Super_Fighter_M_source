package com.eagle.mixsdk.sdk.log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.model.Progress;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import org.json.JSONObject;

public class ULog {
    public static final String L_DEBUG = "DEBUG";
    public static final String L_ERROR = "ERROR";
    public static final String L_INFO = "INFO";
    public static final String L_WARN = "WARNING";
    private String level;
    private String msg;
    private Throwable stack;
    private String tag;
    private Date time;

    public ULog() {
    }

    public ULog(String str, String str2, String str3) {
        this.level = str;
        this.tag = str2;
        this.msg = str3;
        this.stack = null;
        this.time = new Date();
    }

    public ULog(String str, String str2, String str3, Throwable th) {
        this.level = str;
        this.tag = str2;
        this.msg = str3;
        this.stack = th;
        this.time = new Date();
    }

    private String parseStack(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        String stringWriter2 = stringWriter.toString();
        printWriter.close();
        return stringWriter2;
    }

    public String toJSON() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(FirebaseAnalytics.Param.LEVEL, this.level);
            jSONObject.put(Progress.TAG, this.tag);
            jSONObject.put("msg", this.msg);
            jSONObject.put("stack", this.stack == null ? "" : parseStack(this.stack));
            jSONObject.put("time", this.time);
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
