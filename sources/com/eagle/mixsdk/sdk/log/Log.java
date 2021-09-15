package com.eagle.mixsdk.sdk.log;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.util.ArrayList;
import java.util.List;

public class Log {
    public static final String L_DEBUG = "DEBUG";
    public static final String L_ERROR = "ERROR";
    public static final String L_INFO = "INFO";
    public static final String L_WARN = "WARNING";
    private static Log instance = new Log();
    private boolean enable = false;
    private boolean isInited = false;
    private String level = "DEBUG";
    private boolean local = true;
    private List<ILog> logPrinters = new ArrayList();

    private Log() {
    }

    /* renamed from: d */
    public static void m598d(String tag, String msg) {
        try {
            if ("DEBUG".equalsIgnoreCase(instance.level)) {
                for (ILog printer : instance.logPrinters) {
                    printer.mo15758d(tag, msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public static void m601i(String tag, String msg) {
        try {
            if ("DEBUG".equalsIgnoreCase(instance.level) || "INFO".equalsIgnoreCase(instance.level)) {
                for (ILog printer : instance.logPrinters) {
                    printer.mo15762i(tag, msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: w */
    public static void m602w(String tag, String msg) {
        try {
            if (!"ERROR".equalsIgnoreCase(instance.level)) {
                for (ILog printer : instance.logPrinters) {
                    printer.mo15763w(tag, msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: w */
    public static void m603w(String tag, String msg, Throwable e) {
        try {
            if (!"ERROR".equalsIgnoreCase(instance.level)) {
                for (ILog printer : instance.logPrinters) {
                    printer.mo15764w(tag, msg, e);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: e */
    public static void m599e(String tag, String msg) {
        try {
            for (ILog printer : instance.logPrinters) {
                printer.mo15760e(tag, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public static void m600e(String tag, String msg, Throwable e) {
        try {
            for (ILog printer : instance.logPrinters) {
                printer.mo15761e(tag, msg, e);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void superError(String tag, String msg) {
        try {
            m599e(tag, "┏==============================================================================================┓");
            m599e(tag, "  ==============================================================================================");
            m599e(tag, "  ====================================来自遥远的温馨提示START===================================");
            m599e(tag, msg);
            m599e(tag, msg);
            m599e(tag, msg);
            m599e(tag, "  ====================================来自遥远的温馨提示END=====================================");
            m599e(tag, "  ==============================================================================================");
            m599e(tag, "┗==============================================================================================┛");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void init(Context context) {
        try {
            if (!instance.isInited) {
                instance.parseConfig(context);
                instance.logPrinters.clear();
                if (!instance.enable) {
                    android.util.Log.d("ULOG", "the log is not enabled.");
                    return;
                }
                if (instance.local) {
                    instance.logPrinters.add(new ULocalLog());
                }
                instance.isInited = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void destory() {
        try {
            if (instance.logPrinters != null) {
                for (ILog printer : instance.logPrinters) {
                    printer.destory();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseConfig(Context ctx) {
        try {
            ApplicationInfo appInfo = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128);
            if (appInfo != null && appInfo.metaData != null) {
                if (appInfo.metaData.containsKey("ulog.enable")) {
                    this.enable = appInfo.metaData.getBoolean("ulog.enable");
                }
                if (appInfo.metaData.containsKey("ulog.level")) {
                    this.level = appInfo.metaData.getString("ulog.level");
                }
                if (appInfo.metaData.containsKey("ulog.local")) {
                    this.local = appInfo.metaData.getBoolean("ulog.local");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
