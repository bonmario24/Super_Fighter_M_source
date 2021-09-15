package com.thinkfly.plugins.coludladder.manager;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CloudLadderConnectivityMonitor implements ConnectivityListener {
    private static volatile CloudLadderConnectivityMonitor instance;
    private ApplicationLifecycle lifecycle;
    private List<ConnectivityListener> listeners;

    private CloudLadderConnectivityMonitor() {
    }

    public static CloudLadderConnectivityMonitor with(Context context) {
        if (instance == null) {
            synchronized (CloudLadderConnectivityMonitor.class) {
                instance = new CloudLadderConnectivityMonitor();
                instance.init(context);
            }
        }
        return instance;
    }

    private void init(Context context) {
        if (this.lifecycle == null) {
            this.lifecycle = new ApplicationLifecycle();
            this.lifecycle.addListener(new ConnectivityMonitorFactory().build(context, this));
        }
        this.listeners = new ArrayList();
    }

    public void onConnectivityChanged(boolean isConnected) {
        synchronized (this) {
            for (ConnectivityListener listener : this.listeners) {
                listener.onConnectivityChanged(isConnected);
            }
        }
    }

    public void register(ConnectivityListener listener) {
        if (listener != null && this.listeners != null && !this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    public void unregister(ConnectivityListener listener) {
        Iterator<ConnectivityListener> iter = this.listeners.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(listener)) {
                iter.remove();
            }
        }
    }

    public ApplicationLifecycle getLifecycle() {
        return this.lifecycle;
    }
}
