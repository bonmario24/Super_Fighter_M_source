package com.thinkfly.plugins.coludladder.proxy;

import com.thinkfly.plugins.coludladder.log.Log;

public class BaseTaskProxy implements Runnable {
    protected final Runnable proxyRunnable;

    public BaseTaskProxy(Runnable runnable) {
        this.proxyRunnable = runnable;
    }

    public void run() {
        proxyRun();
    }

    public void proxyRun() {
        try {
            if (this.proxyRunnable != null) {
                this.proxyRunnable.run();
            }
        } catch (Exception e) {
            Log.m665w(Log.TAG, "TaskProxy: 执行定时任务出现异常，忽略当前任务");
            e.printStackTrace();
        }
    }

    public Runnable getProxyRunnable() {
        return this.proxyRunnable;
    }
}
