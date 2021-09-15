package com.thinkfly.plugins.coludladder.proxy;

import com.thinkfly.star.CloudLadderApplication;
import com.thinkfly.star.event.ITaskLife;

public class LifeCycleTaskProxy extends BaseTaskProxy implements ITaskLife {
    private boolean isForeground = CloudLadderApplication.isForgroundSate;

    public LifeCycleTaskProxy(Runnable runnable) {
        super(runnable);
        CloudLadderApplication.getInstance().registerTaskLife(this);
    }

    public boolean isForeground() {
        return this.isForeground;
    }

    public void onStart() {
        this.isForeground = true;
    }

    public void onStop() {
        this.isForeground = false;
    }
}
