package com.appsflyer;

import android.net.TrafficStats;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AFExecutor {

    /* renamed from: ι */
    private static AFExecutor f365;

    /* renamed from: ı */
    final ThreadFactory f366 = new ThreadFactory() {
        public final Thread newThread(@NonNull final Runnable runnable) {
            return new Thread(new Runnable() {
                public final void run() {
                    TrafficStats.setThreadStatsTag("AppsFlyer".hashCode());
                    runnable.run();
                }
            });
        }
    };

    /* renamed from: ǃ */
    ScheduledExecutorService f367;

    /* renamed from: ɩ */
    ScheduledExecutorService f368;

    /* renamed from: Ι */
    Executor f369;

    private AFExecutor() {
    }

    public static AFExecutor getInstance() {
        if (f365 == null) {
            f365 = new AFExecutor();
        }
        return f365;
    }

    public Executor getThreadPoolExecutor() {
        if (this.f369 == null || ((this.f369 instanceof ThreadPoolExecutor) && (((ThreadPoolExecutor) this.f369).isShutdown() || ((ThreadPoolExecutor) this.f369).isTerminated() || ((ThreadPoolExecutor) this.f369).isTerminating()))) {
            this.f369 = Executors.newFixedThreadPool(2, this.f366);
        }
        return this.f369;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ι */
    public final ScheduledThreadPoolExecutor mo14505() {
        if (this.f368 == null || this.f368.isShutdown() || this.f368.isTerminated()) {
            this.f368 = Executors.newScheduledThreadPool(2, this.f366);
        }
        return (ScheduledThreadPoolExecutor) this.f368;
    }

    /* renamed from: ɩ */
    static void m206(ExecutorService executorService) {
        try {
            AFLogger.afRDLog("shut downing executor ...");
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            if (!executorService.isTerminated()) {
                AFLogger.afRDLog("killing non-finished tasks");
            }
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            AFLogger.afRDLog("InterruptedException!!!");
            if (!executorService.isTerminated()) {
                AFLogger.afRDLog("killing non-finished tasks");
            }
            executorService.shutdownNow();
        } catch (Throwable th) {
            if (!executorService.isTerminated()) {
                AFLogger.afRDLog("killing non-finished tasks");
            }
            executorService.shutdownNow();
            throw th;
        }
    }
}
