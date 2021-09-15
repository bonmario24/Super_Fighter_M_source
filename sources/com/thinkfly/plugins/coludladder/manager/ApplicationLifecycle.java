package com.thinkfly.plugins.coludladder.manager;

import android.support.annotation.NonNull;

public class ApplicationLifecycle implements Lifecycle {
    public void addListener(@NonNull LifecycleListener listener) {
        listener.onStart();
    }

    public void removeListener(@NonNull LifecycleListener listener) {
    }
}
