package com.thinkfly.star;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.thinkfly.star.event.ITaskLife;
import java.util.ArrayList;
import java.util.List;

public class CloudLadderApplication extends Application {
    private static CloudLadderApplication instance;
    public static boolean isForgroundSate = false;
    private Application application;
    private List<ITaskLife> events = new ArrayList();
    private boolean isInit = false;
    /* access modifiers changed from: private */
    public int mFinalCount;

    static /* synthetic */ int access$008(CloudLadderApplication x0) {
        int i = x0.mFinalCount;
        x0.mFinalCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$010(CloudLadderApplication x0) {
        int i = x0.mFinalCount;
        x0.mFinalCount = i - 1;
        return i;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        CloudLadderProxyApplication.init(this);
    }

    public static CloudLadderApplication getInstance() {
        if (instance == null) {
            synchronized (CloudLadderApplication.class) {
                if (instance == null) {
                    instance = new CloudLadderApplication();
                }
            }
        }
        return instance;
    }

    public Application getApplication() {
        return this.application;
    }

    public void registerTaskLife(ITaskLife iTaskLife) {
        if (iTaskLife != null && !this.events.contains(iTaskLife)) {
            this.events.add(iTaskLife);
        }
    }

    /* access modifiers changed from: private */
    public void notifyForeground() {
        if (this.events != null && !this.events.isEmpty()) {
            for (ITaskLife life : this.events) {
                life.onStart();
            }
        }
    }

    /* access modifiers changed from: private */
    public void notifyBackground() {
        if (this.events != null && !this.events.isEmpty()) {
            for (ITaskLife life : this.events) {
                life.onStop();
            }
        }
    }

    public void init(Application application2) {
        if (!this.isInit) {
            this.isInit = true;
            this.application = application2;
            application2.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                public void onActivityStopped(Activity arg0) {
                }

                public void onActivityStarted(Activity arg0) {
                }

                public void onActivitySaveInstanceState(Activity arg0, Bundle arg1) {
                }

                public void onActivityResumed(Activity arg0) {
                    CloudLadderApplication.access$008(CloudLadderApplication.this);
                    if (CloudLadderApplication.this.mFinalCount == 1) {
                        CloudLadderApplication.isForgroundSate = true;
                        CloudLadderApplication.this.notifyForeground();
                    }
                }

                public void onActivityPaused(Activity arg0) {
                    CloudLadderApplication.access$010(CloudLadderApplication.this);
                    if (CloudLadderApplication.this.mFinalCount == 0) {
                        CloudLadderApplication.isForgroundSate = false;
                        CloudLadderApplication.this.notifyBackground();
                    }
                }

                public void onActivityDestroyed(Activity arg0) {
                }

                public void onActivityCreated(Activity arg0, Bundle arg1) {
                }
            });
        }
    }
}
