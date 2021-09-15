package com.thinkfly.plugins.coludladder.task;

import android.support.annotation.NonNull;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.coludladder.proxy.PeriodTaskProxy;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerTask {
    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> mTimerFuture;

    public TimerTask() {
        this(2, "");
    }

    public TimerTask(String threadMark) {
        this(2, threadMark);
    }

    public TimerTask(int corePoolSize, String threadMark) {
        this.mTimerFuture = null;
        this.executorService = new ScheduledThreadPoolExecutor(corePoolSize, new DaemonThreadFactory(threadMark), new ThreadPoolExecutor.DiscardPolicy());
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period) {
        if (this.executorService.isShutdown()) {
            return null;
        }
        ScheduledFuture<?> scheduleAtFixedRate = this.executorService.scheduleAtFixedRate(new PeriodTaskProxy(command), initialDelay, period, TimeUnit.MILLISECONDS);
        this.mTimerFuture = scheduleAtFixedRate;
        return scheduleAtFixedRate;
    }

    public ScheduledFuture<?> schedule(Runnable command, long initialDelay) {
        if (this.executorService.isShutdown()) {
            return null;
        }
        return this.executorService.schedule(command, initialDelay, TimeUnit.MILLISECONDS);
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long initialDelay) {
        if (this.executorService.isShutdown()) {
            return null;
        }
        return this.executorService.schedule(callable, initialDelay, TimeUnit.MILLISECONDS);
    }

    private void cancel() {
        if (this.mTimerFuture != null) {
            this.mTimerFuture.cancel(true);
            this.mTimerFuture = null;
        }
    }

    public void shutdown() {
        try {
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.m662d(Log.TAG, "TimerTask shutdown");
    }

    private static final class DaemonThreadFactory implements ThreadFactory {
        private AtomicInteger atoInteger = new AtomicInteger(0);
        private final String threadMark;

        public DaemonThreadFactory(String threadMark2) {
            this.threadMark = threadMark2;
        }

        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(this.threadMark + " schedule-pool-Thread-" + this.atoInteger.getAndIncrement());
            Log.m662d(Log.TAG, "DaemonThreadFactory newThread:" + thread.getName());
            thread.setDaemon(true);
            return thread;
        }
    }
}
