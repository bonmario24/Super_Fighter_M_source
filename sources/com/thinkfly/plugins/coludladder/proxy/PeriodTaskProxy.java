package com.thinkfly.plugins.coludladder.proxy;

public class PeriodTaskProxy extends LifeCycleTaskProxy {
    public PeriodTaskProxy(Runnable runnable) {
        super(runnable);
    }

    public void proxyRun() {
        if (isForeground()) {
            super.proxyRun();
        }
    }
}
