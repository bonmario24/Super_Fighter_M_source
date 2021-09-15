package com.xhuyu.component.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.xhuyu.component.p036ui.activity.LoginActivity;
import com.xhuyu.component.p036ui.activity.MarketingActivity;
import com.xhuyu.component.p036ui.activity.NoticeActivity;
import com.xhuyu.component.p036ui.activity.PaymentActivity;
import com.xhuyu.component.p036ui.activity.SplashActivity;
import com.xhuyu.component.p036ui.activity.TallyOrderActivity;
import com.xhuyu.component.p036ui.activity.UserCenterActivity;
import com.xhuyu.component.p036ui.activity.WebBillingActivity;
import java.util.ArrayList;
import java.util.List;

public class ActivityUtil {
    /* access modifiers changed from: private */
    public static List<Activity> list = new ArrayList();

    public static void init(Activity activity) {
        Application application = activity.getApplication();
        list.add(activity);
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityUtil.list.add(activity);
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            public void onActivityDestroyed(Activity activity) {
                ActivityUtil.list.remove(activity);
            }
        });
    }

    public static Activity getTopActivity() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static int getActivitySize() {
        return list.size();
    }

    public static void finishAllActivity() {
        for (Activity activity : list) {
            if (!activity.isFinishing() && !isSDKActivity(activity)) {
                activity.finish();
            }
        }
    }

    public static void finishAllSDKActivity() {
        for (Activity activity : list) {
            if (!activity.isFinishing() && isSDKActivity(activity)) {
                activity.finish();
            }
        }
    }

    public static Activity getTopActivity2() {
        if (list.isEmpty()) {
            return null;
        }
        for (int index = list.size() - 1; index >= 0; index--) {
            Activity activity = list.get(index);
            if (!(activity instanceof LoginActivity)) {
                return activity;
            }
        }
        return null;
    }

    public static boolean isSDKActivity(Activity activity) {
        if (activity == null) {
            return false;
        }
        String target = activity.getClass().getName();
        if (LoginActivity.class.getName().equals(target) || MarketingActivity.class.getName().equals(target) || PaymentActivity.class.getName().equals(target) || NoticeActivity.class.getName().equals(target) || WebBillingActivity.class.getName().equals(target) || UserCenterActivity.class.getName().equals(target) || SplashActivity.class.getName().equals(target) || TallyOrderActivity.class.getName().equals(target)) {
            return true;
        }
        return false;
    }
}
