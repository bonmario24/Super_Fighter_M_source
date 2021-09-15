package com.eagle.mixsdk.sdk.log;

import android.util.Log;

public class ULocalLog implements ILog {
    /* renamed from: d */
    public void mo15758d(String tag, String msg) {
        Log.d(tag, msg);
    }

    /* renamed from: i */
    public void mo15762i(String tag, String msg) {
        Log.i(tag, msg);
    }

    /* renamed from: w */
    public void mo15763w(String tag, String msg) {
        Log.w(tag, msg);
    }

    /* renamed from: e */
    public void mo15760e(String tag, String msg) {
        Log.e(tag, msg);
    }

    /* renamed from: w */
    public void mo15764w(String tag, String msg, Throwable e) {
        Log.w(tag, msg, e);
    }

    /* renamed from: e */
    public void mo15761e(String tag, String msg, Throwable e) {
        Log.e(tag, msg, e);
    }

    public void destory() {
    }
}
