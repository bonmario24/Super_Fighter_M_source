package com.doraemon.devices;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class TimeoutObserver implements Runnable, IdentifierObserver {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private IdentifierObserver mObserver;

    public TimeoutObserver(IdentifierObserver observer, long delay) {
        this.mObserver = observer;
        this.handler.postDelayed(this, delay);
        Log.w("TimeoutObserver", "postDelayed " + delay);
    }

    public void run() {
        Log.w("TimeoutObserver", "running");
        if (this.mObserver != null) {
            this.mObserver.onChange(new Identifier());
            this.mObserver = null;
        }
    }

    public void onChange(Identifier identifier) {
        if (this.mObserver != null) {
            this.mObserver.onChange(identifier);
        }
        this.handler.removeCallbacksAndMessages((Object) null);
        Log.w("TimeoutObserver", "removeCallbacks");
    }
}
