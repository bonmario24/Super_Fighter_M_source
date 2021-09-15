package com.xsdk.doraemon.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

public class ContextUtil {
    private static ContextUtil instance;
    private Activity activity;
    private Application application;
    private Context applicationContext;

    private ContextUtil() {
    }

    public static ContextUtil getInstance() {
        if (instance == null) {
            synchronized (ContextUtil.class) {
                if (instance == null) {
                    instance = new ContextUtil();
                }
            }
        }
        return instance;
    }

    public void setApplicationContext(Context applicationContext2) {
        this.applicationContext = applicationContext2;
    }

    public Context getApplicationContext() {
        if (this.applicationContext != null) {
            return this.applicationContext;
        }
        throw new RuntimeException("Argument 'applicationContext' cannot be null");
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application2) {
        this.application = application2;
        this.applicationContext = application2.getApplicationContext();
    }

    public void setGameMainActivity(Activity activity2) {
        this.activity = activity2;
    }

    public int getWidthPixels() {
        if (this.activity == null || this.activity.isFinishing()) {
            return 0;
        }
        int widthPixels = this.activity.getResources().getDisplayMetrics().widthPixels;
        int i = this.activity.getResources().getDisplayMetrics().heightPixels;
        if (Build.VERSION.SDK_INT < 17) {
            return widthPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int widthPixels2 = dm.widthPixels;
        int i2 = dm.heightPixels;
        return widthPixels2;
    }

    public int getHeightPixels() {
        if (this.activity == null || this.activity.isFinishing()) {
            return 0;
        }
        int heightPixels = this.activity.getResources().getDisplayMetrics().heightPixels;
        if (Build.VERSION.SDK_INT < 17) {
            return heightPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        return dm.heightPixels;
    }

    public int getOrientation() {
        if (this.activity == null || this.activity.isFinishing()) {
            return 0;
        }
        return this.activity.getResources().getConfiguration().orientation;
    }

    public Activity getActivity() {
        return this.activity;
    }
}
