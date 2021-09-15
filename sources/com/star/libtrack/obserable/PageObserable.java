package com.star.libtrack.obserable;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.star.libtrack.event.Event;

public class PageObserable extends BaseObserable {
    public static final int CODE_ACTIVITY_ENTER = 0;
    public static final int CODE_ACTIVITY_EXIT = 1;
    public static final int CODE_FRAGMENT_ENTER = 4;
    public static final int CODE_FRAGMENT_EXIT = 5;
    public static final int CODE_VIEW_ENTER = 2;
    public static final int CODE_VIEW_EXIT = 3;
    private static boolean isObserverActivity = false;
    private ActivityPageLifecycle activityPageLifecycle = new ActivityPageLifecycle();
    private ViewGroup.OnHierarchyChangeListener hierarchyChangeListener = new ViewGroup.OnHierarchyChangeListener() {
        public void onChildViewAdded(View view, View child) {
            PageObserable.this.dispatch(new Event(2, child, (String) null));
        }

        public void onChildViewRemoved(View view, View child) {
            PageObserable.this.dispatch(new Event(3, child, (String) null));
        }
    };

    public void observerActivity(Application application) {
        if (application != null && !isObserverActivity) {
            application.registerActivityLifecycleCallbacks(this.activityPageLifecycle);
            isObserverActivity = true;
        }
    }

    public void observerView(ViewGroup vp) {
        vp.setOnHierarchyChangeListener(this.hierarchyChangeListener);
    }

    private class ActivityPageLifecycle implements Application.ActivityLifecycleCallbacks {
        private ActivityPageLifecycle() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            PageObserable.this.dispatch(new Event(0, activity, (String) null));
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
            PageObserable.this.dispatch(new Event(1, activity, (String) null));
        }
    }
}
