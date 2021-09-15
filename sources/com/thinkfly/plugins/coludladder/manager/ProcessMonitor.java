package com.thinkfly.plugins.coludladder.manager;

import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.coludladder.proxy.LifeCycleTaskProxy;

public class ProcessMonitor extends LifeCycleTaskProxy {
    private static final long INTERVAL_TIME = 1000;
    private long beginPoint = 0;
    private ProcessLifeCycleListener mLifeCycleListener;

    public interface ProcessLifeCycleListener {
        void onProcessChanged(boolean z);
    }

    public ProcessMonitor() {
        super((Runnable) null);
    }

    public void onStart() {
        super.onStart();
        if (this.beginPoint != 0 && !isTooFast()) {
            Log.m662d(Log.TAG, "start foreground task");
            if (this.mLifeCycleListener != null) {
                this.mLifeCycleListener.onProcessChanged(false);
            }
        }
    }

    public void onStop() {
        super.onStop();
        if (!isTooFast()) {
            Log.m662d(Log.TAG, "start background task");
            if (this.mLifeCycleListener != null) {
                this.mLifeCycleListener.onProcessChanged(true);
            }
        }
    }

    private boolean isTooFast() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean isTooFast = currentTimeMillis - this.beginPoint <= INTERVAL_TIME;
        this.beginPoint = currentTimeMillis;
        return isTooFast;
    }

    public void register(ProcessLifeCycleListener lifeCycleListener) {
        this.mLifeCycleListener = lifeCycleListener;
    }
}
