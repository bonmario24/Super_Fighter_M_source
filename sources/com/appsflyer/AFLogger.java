package com.appsflyer;

import android.support.annotation.NonNull;
import android.util.Log;
import com.appsflyer.internal.C0928ai;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AFLogger {

    /* renamed from: ι */
    private static long f378 = System.currentTimeMillis();

    public static void afInfoLog(String str, boolean z) {
        if (m222(LogLevel.INFO)) {
            Log.i(AppsFlyerLibCore.LOG_TAG, m217(str, false));
        }
        if (z) {
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14690((String) null, "I", m217(str, true));
        }
    }

    public static void resetDeltaTime() {
        f378 = System.currentTimeMillis();
    }

    @NonNull
    /* renamed from: ǃ */
    private static String m217(String str, boolean z) {
        if (z || LogLevel.VERBOSE.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            return new StringBuilder("(").append(m220(System.currentTimeMillis() - f378)).append(") [").append(Thread.currentThread().getName()).append("] ").append(str).toString();
        }
        return str;
    }

    /* renamed from: ı */
    private static void m216(String str, Throwable th, boolean z) {
        String message;
        StackTraceElement[] stackTrace;
        String[] strArr;
        if (m222(LogLevel.ERROR) && z) {
            Log.e(AppsFlyerLibCore.LOG_TAG, m217(str, false), th);
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai aiVar = C0928ai.f525;
        Throwable cause = th.getCause();
        String simpleName = th.getClass().getSimpleName();
        if (cause == null) {
            message = th.getMessage();
        } else {
            message = cause.getMessage();
        }
        if (cause == null) {
            stackTrace = th.getStackTrace();
        } else {
            stackTrace = cause.getStackTrace();
        }
        if (stackTrace == null) {
            strArr = new String[]{message};
        } else {
            String[] strArr2 = new String[(stackTrace.length + 1)];
            strArr2[0] = message;
            for (int i = 1; i < stackTrace.length; i++) {
                strArr2[i] = stackTrace[i].toString();
            }
            strArr = strArr2;
        }
        aiVar.mo14690("exception", simpleName, strArr);
    }

    /* renamed from: Ι */
    static void m221(String str) {
        if (m222(LogLevel.WARNING)) {
            Log.w(AppsFlyerLibCore.LOG_TAG, m217(str, false));
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690((String) null, "W", m217(str, true));
    }

    public static void afRDLog(String str) {
        if (m222(LogLevel.VERBOSE)) {
            Log.v(AppsFlyerLibCore.LOG_TAG, m217(str, false));
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690((String) null, "V", m217(str, true));
    }

    /* renamed from: Ι */
    private static boolean m222(LogLevel logLevel) {
        return logLevel.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel());
    }

    /* renamed from: ǃ */
    static void m218(String str) {
        if (!m219()) {
            Log.d(AppsFlyerLibCore.LOG_TAG, m217(str, false));
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690((String) null, "F", str);
    }

    /* renamed from: ɩ */
    private static boolean m219() {
        return AppsFlyerProperties.getInstance().isLogsDisabledCompletely();
    }

    public static void afDebugLog(String str) {
        if (m222(LogLevel.DEBUG)) {
            Log.d(AppsFlyerLibCore.LOG_TAG, m217(str, false));
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690((String) null, "D", m217(str, true));
    }

    public static void afInfoLog(String str) {
        afInfoLog(str, true);
    }

    public static void afErrorLog(String str, Throwable th) {
        m216(str, th, true);
    }

    public static void afErrorLog(String str, Throwable th, boolean z) {
        m216(str, th, z);
    }

    public static void afWarnLog(String str) {
        m221(str);
    }

    /* renamed from: Ι */
    private static String m220(long j) {
        long hours = TimeUnit.MILLISECONDS.toHours(j);
        long millis = j - TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long millis2 = millis - TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis2);
        long millis3 = TimeUnit.MILLISECONDS.toMillis(millis2 - TimeUnit.SECONDS.toMillis(seconds));
        return String.format(Locale.getDefault(), "%02d:%02d:%02d:%03d", new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds), Long.valueOf(millis3)});
    }

    public enum LogLevel {
        NONE(0),
        ERROR(1),
        WARNING(2),
        INFO(3),
        DEBUG(4),
        VERBOSE(5);
        

        /* renamed from: ɩ */
        private int f380;

        private LogLevel(int i) {
            this.f380 = i;
        }

        public final int getLevel() {
            return this.f380;
        }
    }
}
