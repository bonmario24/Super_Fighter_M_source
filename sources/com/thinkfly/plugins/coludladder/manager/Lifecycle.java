package com.thinkfly.plugins.coludladder.manager;

import android.support.annotation.NonNull;

public interface Lifecycle {
    void addListener(@NonNull LifecycleListener lifecycleListener);

    void removeListener(@NonNull LifecycleListener lifecycleListener);
}
