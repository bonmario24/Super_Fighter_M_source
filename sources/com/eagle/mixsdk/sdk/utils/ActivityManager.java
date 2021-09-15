package com.eagle.mixsdk.sdk.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Stack;

public class ActivityManager {
    private static ActivityManager instance;
    private Stack<Activity> activityStack = new Stack<>();
    private WeakReference<Activity> sCurrentActivityWeakRef;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void registerActivityLifecycleCallbacks(Application application) {
        if (application != null) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    ActivityManager.this.pushActivity(activity);
                }

                public void onActivityStarted(Activity activity) {
                }

                public void onActivityResumed(Activity activity) {
                    ActivityManager.this.setCurrentActivity(activity);
                }

                public void onActivityPaused(Activity activity) {
                }

                public void onActivityStopped(Activity activity) {
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                }

                public void onActivityDestroyed(Activity activity) {
                    ActivityManager.this.popActivity(activity);
                }
            });
        }
    }

    public void popActivity(Activity activity) {
        if (this.activityStack != null) {
            this.activityStack.remove(activity);
        }
    }

    public Activity currentActivity() {
        if (this.sCurrentActivityWeakRef != null) {
            return (Activity) this.sCurrentActivityWeakRef.get();
        }
        return null;
    }

    public void setCurrentActivity(Activity activity) {
        this.sCurrentActivityWeakRef = new WeakReference<>(activity);
    }

    public void pushActivity(Activity activity) {
        if (this.activityStack == null) {
            this.activityStack = new Stack<>();
        }
        this.activityStack.add(activity);
    }

    public void popAllActivity() {
        if (this.activityStack != null && !this.activityStack.isEmpty()) {
            Iterator it = this.activityStack.iterator();
            while (it.hasNext()) {
                ((Activity) it.next()).finish();
            }
            this.activityStack.clear();
        }
    }

    public void exitGame() {
        try {
            Activity activity = currentActivity();
            if (activity != null) {
                Intent startMain = new Intent("android.intent.action.MAIN");
                startMain.addCategory("android.intent.category.HOME");
                startMain.setFlags(268435456);
                activity.startActivity(startMain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popAllActivity();
        System.exit(0);
        Process.killProcess(Process.myPid());
    }
}
