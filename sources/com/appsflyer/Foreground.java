package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Foreground {
    @VisibleForTesting
    public static long CHECK_DELAY = 500;
    public static Listener listener;

    public interface Listener {
        void onBecameBackground(Context context);

        void onBecameForeground(Activity activity);
    }

    /* renamed from: ɩ */
    static void m311(Context context, final Listener listener2) {
        listener = listener2;
        C09104 r1 = new Application.ActivityLifecycleCallbacks() {

            /* renamed from: ɩ */
            boolean f476 = true;

            /* renamed from: Ι */
            boolean f477;

            /* renamed from: ι */
            private Executor f478 = Executors.newSingleThreadExecutor();

            public final void onActivityResumed(final Activity activity) {
                this.f478.execute(new Runnable() {
                    public final void run() {
                        if (!C09104.this.f477) {
                            try {
                                listener2.onBecameForeground(activity);
                            } catch (Exception e) {
                                AFLogger.afErrorLog("Listener thrown an exception: ", e);
                            }
                        }
                        C09104.this.f476 = false;
                        C09104.this.f477 = true;
                    }
                });
            }

            public final void onActivityPaused(@NonNull final Activity activity) {
                this.f478.execute(new Runnable() {
                    public final void run() {
                        C09104.this.f476 = true;
                        final Context applicationContext = activity.getApplicationContext();
                        try {
                            new Timer().schedule(new TimerTask() {
                                public final void run() {
                                    if (C09104.this.f477 && C09104.this.f476) {
                                        C09104.this.f477 = false;
                                        try {
                                            listener2.onBecameBackground(applicationContext);
                                        } catch (Exception e) {
                                            AFLogger.afErrorLog("Listener threw exception! ", e);
                                        }
                                    }
                                }
                            }, Foreground.CHECK_DELAY);
                        } catch (Throwable th) {
                            AFLogger.afErrorLog("Background task failed with a throwable: ", th);
                        }
                    }
                });
            }

            public final void onActivityCreated(@NonNull final Activity activity, Bundle bundle) {
                this.f478.execute(new Runnable() {
                    public final void run() {
                        AFDeepLinkManager.getInstance().collectIntentsFromActivities(activity.getIntent());
                    }
                });
            }

            public final void onActivityStarted(Activity activity) {
            }

            public final void onActivityStopped(Activity activity) {
            }

            public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public final void onActivityDestroyed(Activity activity) {
            }
        };
        if (context instanceof Activity) {
            r1.onActivityResumed((Activity) context);
        }
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(r1);
    }
}
