package com.doraemon.util;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.p000v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Utils {
    private static final ActivityLifecycleImpl ACTIVITY_LIFECYCLE = new ActivityLifecycleImpl();
    private static final String PERMISSION_ACTIVITY_CLASS_NAME = "com.blankj.utilcode.util.PermissionUtils$PermissionActivity";
    /* access modifiers changed from: private */
    public static final Handler UTIL_HANDLER = new Handler(Looper.getMainLooper());
    private static final ExecutorService UTIL_POOL = Executors.newFixedThreadPool(3);
    @SuppressLint({"StaticFieldLeak"})
    private static Application sApplication;

    public interface Callback<T> {
        void onCall(T t);
    }

    public interface OnActivityDestroyedListener {
        void onActivityDestroyed(Activity activity);
    }

    public interface OnAppStatusChangedListener {
        void onBackground();

        void onForeground();
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Context context) {
        if (context == null) {
            init(getApplicationByReflect());
        } else {
            init((Application) context.getApplicationContext());
        }
    }

    public static void init(Application app) {
        if (sApplication == null) {
            if (app == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = app;
            }
            sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
        } else if (app != null && app.getClass() != sApplication.getClass()) {
            sApplication.unregisterActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
            ACTIVITY_LIFECYCLE.mActivityList.clear();
            sApplication = app;
            sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
        }
    }

    public static Application getApp() {
        if (sApplication != null) {
            return sApplication;
        }
        Application app = getApplicationByReflect();
        init(app);
        return app;
    }

    static ActivityLifecycleImpl getActivityLifecycle() {
        return ACTIVITY_LIFECYCLE;
    }

    static LinkedList<Activity> getActivityList() {
        return ACTIVITY_LIFECYCLE.mActivityList;
    }

    static Context getTopActivityOrApp() {
        if (!isAppForeground()) {
            return getApp();
        }
        Activity topActivity = ACTIVITY_LIFECYCLE.getTopActivity();
        if (topActivity == null) {
            return getApp();
        }
        return topActivity;
    }

    static boolean isAppForeground() {
        List<ActivityManager.RunningAppProcessInfo> info;
        ActivityManager am = (ActivityManager) getApp().getSystemService("activity");
        if (am == null || (info = am.getRunningAppProcesses()) == null || info.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == 100 && aInfo.processName.equals(getApp().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    static <T> Task<T> doAsync(Task<T> task) {
        UTIL_POOL.execute(task);
        return task;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            UTIL_HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long delayMillis) {
        UTIL_HANDLER.postDelayed(runnable, delayMillis);
    }

    static String getCurrentProcessName() {
        String name = getCurrentProcessNameByFile();
        if (!TextUtils.isEmpty(name)) {
            return name;
        }
        String name2 = getCurrentProcessNameByAms();
        if (TextUtils.isEmpty(name2)) {
            return getCurrentProcessNameByReflect();
        }
        return name2;
    }

    static void fixSoftInputLeaks(Window window) {
        InputMethodManager imm = (InputMethodManager) getApp().getSystemService("input_method");
        if (imm != null) {
            for (String leakView : new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"}) {
                try {
                    Field leakViewField = InputMethodManager.class.getDeclaredField(leakView);
                    if (leakViewField != null) {
                        if (!leakViewField.isAccessible()) {
                            leakViewField.setAccessible(true);
                        }
                        Object obj = leakViewField.get(imm);
                        if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                            leakViewField.set(imm, (Object) null);
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    static SPUtils getSpUtils4Utils() {
        return SPUtils.getInstance("Utils");
    }

    private static String getCurrentProcessNameByFile() {
        try {
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getCurrentProcessNameByAms() {
        List<ActivityManager.RunningAppProcessInfo> info;
        ActivityManager am = (ActivityManager) getApp().getSystemService("activity");
        if (am == null || (info = am.getRunningAppProcesses()) == null || info.size() == 0) {
            return "";
        }
        int pid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.pid == pid && aInfo.processName != null) {
                return aInfo.processName;
            }
        }
        return "";
    }

    private static String getCurrentProcessNameByReflect() {
        try {
            Application app = getApp();
            Field loadedApkField = app.getClass().getField("mLoadedApk");
            loadedApkField.setAccessible(true);
            Object loadedApk = loadedApkField.get(app);
            Field activityThreadField = loadedApk.getClass().getDeclaredField("mActivityThread");
            activityThreadField.setAccessible(true);
            Object activityThread = activityThreadField.get(loadedApk);
            return (String) activityThread.getClass().getDeclaredMethod("getProcessName", new Class[0]).invoke(activityThread, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Application getApplicationByReflect() {
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object app = activityThread.getMethod("getApplication", new Class[0]).invoke(activityThread.getMethod("currentActivityThread", new Class[0]).invoke((Object) null, new Object[0]), new Object[0]);
            if (app != null) {
                return (Application) app;
            }
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    /* access modifiers changed from: private */
    public static void setAnimatorsEnabled() {
        if (Build.VERSION.SDK_INT < 26 || !ValueAnimator.areAnimatorsEnabled()) {
            try {
                Field sDurationScaleField = ValueAnimator.class.getDeclaredField("sDurationScale");
                sDurationScaleField.setAccessible(true);
                if (((Float) sDurationScaleField.get((Object) null)).floatValue() == 0.0f) {
                    sDurationScaleField.set((Object) null, Float.valueOf(1.0f));
                    Log.i("Utils", "setAnimatorsEnabled: Animators are enabled now!");
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
    }

    static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
        final LinkedList<Activity> mActivityList = new LinkedList<>();
        private int mConfigCount = 0;
        final Map<Activity, Set<OnActivityDestroyedListener>> mDestroyedListenerMap = new HashMap();
        private int mForegroundCount = 0;
        private boolean mIsBackground = false;
        final Map<Object, OnAppStatusChangedListener> mStatusListenerMap = new HashMap();

        ActivityLifecycleImpl() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            LanguageUtils.applyLanguage(activity);
            Utils.setAnimatorsEnabled();
            setTopActivity(activity);
        }

        public void onActivityStarted(Activity activity) {
            if (!this.mIsBackground) {
                setTopActivity(activity);
            }
            if (this.mConfigCount < 0) {
                this.mConfigCount++;
            } else {
                this.mForegroundCount++;
            }
        }

        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
            if (this.mIsBackground) {
                this.mIsBackground = false;
                postStatus(true);
            }
            processHideSoftInputOnActivityDestroy(activity, false);
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.mConfigCount--;
            } else {
                this.mForegroundCount--;
                if (this.mForegroundCount <= 0) {
                    this.mIsBackground = true;
                    postStatus(false);
                }
            }
            processHideSoftInputOnActivityDestroy(activity, true);
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
            this.mActivityList.remove(activity);
            consumeOnActivityDestroyedListener(activity);
            Utils.fixSoftInputLeaks(activity.getWindow());
        }

        /* access modifiers changed from: package-private */
        public Activity getTopActivity() {
            if (!this.mActivityList.isEmpty()) {
                for (int i = this.mActivityList.size() - 1; i >= 0; i--) {
                    Activity activity = this.mActivityList.get(i);
                    if (activity != null && !activity.isFinishing() && (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed())) {
                        return activity;
                    }
                }
            }
            Activity topActivityByReflect = getTopActivityByReflect();
            if (topActivityByReflect != null) {
                setTopActivity(topActivityByReflect);
            }
            return topActivityByReflect;
        }

        /* access modifiers changed from: package-private */
        public void addOnAppStatusChangedListener(Object object, OnAppStatusChangedListener listener) {
            this.mStatusListenerMap.put(object, listener);
        }

        /* access modifiers changed from: package-private */
        public void removeOnAppStatusChangedListener(Object object) {
            this.mStatusListenerMap.remove(object);
        }

        /* access modifiers changed from: package-private */
        public void removeOnActivityDestroyedListener(Activity activity) {
            if (activity != null) {
                this.mDestroyedListenerMap.remove(activity);
            }
        }

        /* access modifiers changed from: package-private */
        public void addOnActivityDestroyedListener(Activity activity, OnActivityDestroyedListener listener) {
            Set<OnActivityDestroyedListener> listeners;
            if (activity != null && listener != null) {
                if (!this.mDestroyedListenerMap.containsKey(activity)) {
                    listeners = new HashSet<>();
                    this.mDestroyedListenerMap.put(activity, listeners);
                } else {
                    listeners = this.mDestroyedListenerMap.get(activity);
                    if (listeners.contains(listener)) {
                        return;
                    }
                }
                listeners.add(listener);
            }
        }

        private void processHideSoftInputOnActivityDestroy(final Activity activity, boolean isSave) {
            if (isSave) {
                activity.getWindow().getDecorView().setTag(-123, Integer.valueOf(activity.getWindow().getAttributes().softInputMode));
                activity.getWindow().setSoftInputMode(3);
                return;
            }
            final Object tag = activity.getWindow().getDecorView().getTag(-123);
            if (tag instanceof Integer) {
                Utils.runOnUiThreadDelayed(new Runnable() {
                    public void run() {
                        activity.getWindow().setSoftInputMode(((Integer) tag).intValue());
                    }
                }, 100);
            }
        }

        private void postStatus(boolean isForeground) {
            OnAppStatusChangedListener onAppStatusChangedListener;
            if (!this.mStatusListenerMap.isEmpty()) {
                Iterator<OnAppStatusChangedListener> it = this.mStatusListenerMap.values().iterator();
                while (it.hasNext() && (onAppStatusChangedListener = it.next()) != null) {
                    if (isForeground) {
                        onAppStatusChangedListener.onForeground();
                    } else {
                        onAppStatusChangedListener.onBackground();
                    }
                }
            }
        }

        private void setTopActivity(Activity activity) {
            if (!Utils.PERMISSION_ACTIVITY_CLASS_NAME.equals(activity.getClass().getName())) {
                if (!this.mActivityList.contains(activity)) {
                    this.mActivityList.addLast(activity);
                } else if (!this.mActivityList.getLast().equals(activity)) {
                    this.mActivityList.remove(activity);
                    this.mActivityList.addLast(activity);
                }
            }
        }

        private void consumeOnActivityDestroyedListener(Activity activity) {
            Iterator<Map.Entry<Activity, Set<OnActivityDestroyedListener>>> iterator = this.mDestroyedListenerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Activity, Set<OnActivityDestroyedListener>> entry = iterator.next();
                if (entry.getKey() == activity) {
                    for (OnActivityDestroyedListener listener : entry.getValue()) {
                        listener.onActivityDestroyed(activity);
                    }
                    iterator.remove();
                }
            }
        }

        private Activity getTopActivityByReflect() {
            try {
                Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                Object currentActivityThreadMethod = activityThreadClass.getMethod("currentActivityThread", new Class[0]).invoke((Object) null, new Object[0]);
                Field mActivityListField = activityThreadClass.getDeclaredField("mActivityList");
                mActivityListField.setAccessible(true);
                Map activities = (Map) mActivityListField.get(currentActivityThreadMethod);
                if (activities == null) {
                    return null;
                }
                for (Object activityRecord : activities.values()) {
                    Class activityRecordClass = activityRecord.getClass();
                    Field pausedField = activityRecordClass.getDeclaredField("paused");
                    pausedField.setAccessible(true);
                    if (!pausedField.getBoolean(activityRecord)) {
                        Field activityField = activityRecordClass.getDeclaredField("activity");
                        activityField.setAccessible(true);
                        return (Activity) activityField.get(activityRecord);
                    }
                }
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (NoSuchFieldException e5) {
                e5.printStackTrace();
            }
        }
    }

    public static final class FileProvider4UtilCode extends FileProvider {
        public boolean onCreate() {
            Utils.init(getContext());
            return true;
        }
    }

    public static abstract class Task<Result> implements Runnable {
        private static final int CANCELLED = 2;
        private static final int COMPLETING = 1;
        private static final int EXCEPTIONAL = 3;
        private static final int NEW = 0;
        /* access modifiers changed from: private */
        public Callback<Result> mCallback;
        private volatile int state = 0;

        /* access modifiers changed from: package-private */
        public abstract Result doInBackground();

        public Task(Callback<Result> callback) {
            this.mCallback = callback;
        }

        public void run() {
            try {
                final Result t = doInBackground();
                if (this.state == 0) {
                    this.state = 1;
                    Utils.UTIL_HANDLER.post(new Runnable() {
                        public void run() {
                            Task.this.mCallback.onCall(t);
                        }
                    });
                }
            } catch (Throwable th) {
                if (this.state == 0) {
                    this.state = 3;
                }
            }
        }

        public void cancel() {
            this.state = 2;
        }

        public boolean isDone() {
            return this.state != 0;
        }

        public boolean isCanceled() {
            return this.state == 2;
        }
    }
}
