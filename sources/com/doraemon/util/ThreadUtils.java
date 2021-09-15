package com.doraemon.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.Thread;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class ThreadUtils {
    /* access modifiers changed from: private */
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static final Map<Task, ExecutorService> TASK_POOL_MAP = new ConcurrentHashMap();
    private static final Timer TIMER = new Timer();
    private static final byte TYPE_CACHED = -2;
    private static final byte TYPE_CPU = -8;
    private static final byte TYPE_IO = -4;
    private static final Map<Integer, Map<Integer, ExecutorService>> TYPE_PRIORITY_POOLS = new HashMap();
    private static final byte TYPE_SINGLE = -1;
    private static Executor sDeliver;

    private static class TaskInfo {
        /* access modifiers changed from: private */
        public ExecutorService mService;
        /* access modifiers changed from: private */
        public TimerTask mTimerTask;

        private TaskInfo(ExecutorService executorService) {
            this.mService = executorService;
        }
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Handler getMainHandler() {
        return HANDLER;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int size) {
        return getPoolByTypeAndPriority(size);
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int size, @IntRange(from = 1, mo13859to = 10) int priority) {
        return getPoolByTypeAndPriority(size, priority);
    }

    public static ExecutorService getSinglePool() {
        return getPoolByTypeAndPriority(-1);
    }

    public static ExecutorService getSinglePool(@IntRange(from = 1, mo13859to = 10) int priority) {
        return getPoolByTypeAndPriority(-1, priority);
    }

    public static ExecutorService getCachedPool() {
        return getPoolByTypeAndPriority(-2);
    }

    public static ExecutorService getCachedPool(@IntRange(from = 1, mo13859to = 10) int priority) {
        return getPoolByTypeAndPriority(-2, priority);
    }

    public static ExecutorService getIoPool() {
        return getPoolByTypeAndPriority(-4);
    }

    public static ExecutorService getIoPool(@IntRange(from = 1, mo13859to = 10) int priority) {
        return getPoolByTypeAndPriority(-4, priority);
    }

    public static ExecutorService getCpuPool() {
        return getPoolByTypeAndPriority(-8);
    }

    public static ExecutorService getCpuPool(@IntRange(from = 1, mo13859to = 10) int priority) {
        return getPoolByTypeAndPriority(-8, priority);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int size, Task<T> task) {
        execute(getPoolByTypeAndPriority(size), task);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int size, Task<T> task, @IntRange(from = 1, mo13859to = 10) int priority) {
        execute(getPoolByTypeAndPriority(size, priority), task);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int size, Task<T> task, long delay, TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(size), task, delay, unit);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int size, Task<T> task, long delay, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeWithDelay(getPoolByTypeAndPriority(size, priority), task, delay, unit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int size, Task<T> task, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(size), task, 0, period, unit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int size, Task<T> task, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(size, priority), task, 0, period, unit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int size, Task<T> task, long initialDelay, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(size), task, initialDelay, period, unit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int size, Task<T> task, long initialDelay, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(size, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeBySingle(Task<T> task) {
        execute(getPoolByTypeAndPriority(-1), task);
    }

    public static <T> void executeBySingle(Task<T> task, @IntRange(from = 1, mo13859to = 10) int priority) {
        execute(getPoolByTypeAndPriority(-1, priority), task);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long delay, TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-1), task, delay, unit);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long delay, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-1, priority), task, delay, unit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, 0, period, unit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, priority), task, 0, period, unit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, initialDelay, period, unit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByCached(Task<T> task) {
        execute(getPoolByTypeAndPriority(-2), task);
    }

    public static <T> void executeByCached(Task<T> task, @IntRange(from = 1, mo13859to = 10) int priority) {
        execute(getPoolByTypeAndPriority(-2, priority), task);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long delay, TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-2), task, delay, unit);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long delay, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-2, priority), task, delay, unit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, 0, period, unit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, priority), task, 0, period, unit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, initialDelay, period, unit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByIo(Task<T> task) {
        execute(getPoolByTypeAndPriority(-4), task);
    }

    public static <T> void executeByIo(Task<T> task, @IntRange(from = 1, mo13859to = 10) int priority) {
        execute(getPoolByTypeAndPriority(-4, priority), task);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long delay, TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-4), task, delay, unit);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long delay, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-4, priority), task, delay, unit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, 0, period, unit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, priority), task, 0, period, unit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, initialDelay, period, unit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByCpu(Task<T> task) {
        execute(getPoolByTypeAndPriority(-8), task);
    }

    public static <T> void executeByCpu(Task<T> task, @IntRange(from = 1, mo13859to = 10) int priority) {
        execute(getPoolByTypeAndPriority(-8, priority), task);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long delay, TimeUnit unit) {
        executeWithDelay(getPoolByTypeAndPriority(-8), task, delay, unit);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long delay, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeWithDelay(getPoolByTypeAndPriority(-8, priority), task, delay, unit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, 0, period, unit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, priority), task, 0, period, unit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, initialDelay, period, unit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long initialDelay, long period, TimeUnit unit, @IntRange(from = 1, mo13859to = 10) int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, priority), task, initialDelay, period, unit);
    }

    public static <T> void executeByCustom(ExecutorService pool, Task<T> task) {
        execute(pool, task);
    }

    public static <T> void executeByCustomWithDelay(ExecutorService pool, Task<T> task, long delay, TimeUnit unit) {
        executeWithDelay(pool, task, delay, unit);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService pool, Task<T> task, long period, TimeUnit unit) {
        executeAtFixedRate(pool, task, 0, period, unit);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService pool, Task<T> task, long initialDelay, long period, TimeUnit unit) {
        executeAtFixedRate(pool, task, initialDelay, period, unit);
    }

    public static void cancel(Task task) {
        if (task != null) {
            task.cancel();
        }
    }

    public static void cancel(Task... tasks) {
        if (tasks != null && tasks.length != 0) {
            for (Task task : tasks) {
                if (task != null) {
                    task.cancel();
                }
            }
        }
    }

    public static void cancel(List<Task> tasks) {
        if (tasks != null && tasks.size() != 0) {
            for (Task task : tasks) {
                if (task != null) {
                    task.cancel();
                }
            }
        }
    }

    public static void cancel(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor4Util) {
            for (Map.Entry<Task, ExecutorService> taskTaskInfoEntry : TASK_POOL_MAP.entrySet()) {
                if (taskTaskInfoEntry.getValue() == executorService) {
                    cancel(taskTaskInfoEntry.getKey());
                }
            }
            return;
        }
        Log.e("ThreadUtils", "The executorService is not ThreadUtils's pool.");
    }

    public static void setDeliver(Executor deliver) {
        sDeliver = deliver;
    }

    private static <T> void execute(ExecutorService pool, Task<T> task) {
        execute(pool, task, 0, 0, (TimeUnit) null);
    }

    private static <T> void executeWithDelay(ExecutorService pool, Task<T> task, long delay, TimeUnit unit) {
        execute(pool, task, delay, 0, unit);
    }

    private static <T> void executeAtFixedRate(ExecutorService pool, Task<T> task, long delay, long period, TimeUnit unit) {
        execute(pool, task, delay, period, unit);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r10 != 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (r8 != 0) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r6.execute(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        TIMER.schedule(new com.doraemon.util.ThreadUtils.C10461(), r12.toMillis(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        com.doraemon.util.ThreadUtils.Task.access$000(r7, true);
        TIMER.scheduleAtFixedRate(new com.doraemon.util.ThreadUtils.C10472(), r12.toMillis(r8), r12.toMillis(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> void execute(final java.util.concurrent.ExecutorService r6, final com.doraemon.util.ThreadUtils.Task<T> r7, long r8, long r10, java.util.concurrent.TimeUnit r12) {
        /*
            r4 = 0
            java.util.Map<com.doraemon.util.ThreadUtils$Task, java.util.concurrent.ExecutorService> r2 = TASK_POOL_MAP
            monitor-enter(r2)
            java.util.Map<com.doraemon.util.ThreadUtils$Task, java.util.concurrent.ExecutorService> r0 = TASK_POOL_MAP     // Catch:{ all -> 0x0028 }
            java.lang.Object r0 = r0.get(r7)     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0016
            java.lang.String r0 = "ThreadUtils"
            java.lang.String r3 = "Task can only be executed once."
            android.util.Log.e(r0, r3)     // Catch:{ all -> 0x0028 }
            monitor-exit(r2)     // Catch:{ all -> 0x0028 }
        L_0x0015:
            return
        L_0x0016:
            java.util.Map<com.doraemon.util.ThreadUtils$Task, java.util.concurrent.ExecutorService> r0 = TASK_POOL_MAP     // Catch:{ all -> 0x0028 }
            r0.put(r7, r6)     // Catch:{ all -> 0x0028 }
            monitor-exit(r2)     // Catch:{ all -> 0x0028 }
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x003a
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x002b
            r6.execute(r7)
            goto L_0x0015
        L_0x0028:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0028 }
            throw r0
        L_0x002b:
            com.doraemon.util.ThreadUtils$1 r1 = new com.doraemon.util.ThreadUtils$1
            r1.<init>(r6, r7)
            java.util.Timer r0 = TIMER
            long r2 = r12.toMillis(r8)
            r0.schedule(r1, r2)
            goto L_0x0015
        L_0x003a:
            r0 = 1
            r7.setSchedule(r0)
            com.doraemon.util.ThreadUtils$2 r1 = new com.doraemon.util.ThreadUtils$2
            r1.<init>(r6, r7)
            java.util.Timer r0 = TIMER
            long r2 = r12.toMillis(r8)
            long r4 = r12.toMillis(r10)
            r0.scheduleAtFixedRate(r1, r2, r4)
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.ThreadUtils.execute(java.util.concurrent.ExecutorService, com.doraemon.util.ThreadUtils$Task, long, long, java.util.concurrent.TimeUnit):void");
    }

    private static ExecutorService getPoolByTypeAndPriority(int type) {
        return getPoolByTypeAndPriority(type, 5);
    }

    private static ExecutorService getPoolByTypeAndPriority(int type, int priority) {
        ExecutorService pool;
        synchronized (TYPE_PRIORITY_POOLS) {
            Map<Integer, ExecutorService> priorityPools = TYPE_PRIORITY_POOLS.get(Integer.valueOf(type));
            if (priorityPools == null) {
                Map<Integer, ExecutorService> priorityPools2 = new ConcurrentHashMap<>();
                pool = ThreadPoolExecutor4Util.createPool(type, priority);
                priorityPools2.put(Integer.valueOf(priority), pool);
                TYPE_PRIORITY_POOLS.put(Integer.valueOf(type), priorityPools2);
            } else {
                pool = priorityPools.get(Integer.valueOf(priority));
                if (pool == null) {
                    pool = ThreadPoolExecutor4Util.createPool(type, priority);
                    priorityPools.put(Integer.valueOf(priority), pool);
                }
            }
        }
        return pool;
    }

    static final class ThreadPoolExecutor4Util extends ThreadPoolExecutor {
        private final AtomicInteger mSubmittedCount = new AtomicInteger();
        private LinkedBlockingQueue4Util mWorkQueue;

        /* access modifiers changed from: private */
        public static ExecutorService createPool(int type, int priority) {
            switch (type) {
                case -8:
                    return new ThreadPoolExecutor4Util(ThreadUtils.CPU_COUNT + 1, (ThreadUtils.CPU_COUNT * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cpu", priority));
                case -4:
                    return new ThreadPoolExecutor4Util((ThreadUtils.CPU_COUNT * 2) + 1, (ThreadUtils.CPU_COUNT * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("io", priority));
                case -2:
                    return new ThreadPoolExecutor4Util(0, 128, 60, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cached", priority));
                case -1:
                    return new ThreadPoolExecutor4Util(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("single", priority));
                default:
                    return new ThreadPoolExecutor4Util(type, type, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("fixed(" + type + ")", priority));
            }
        }

        ThreadPoolExecutor4Util(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, LinkedBlockingQueue4Util workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
            ThreadPoolExecutor4Util unused = workQueue.mPool = this;
            this.mWorkQueue = workQueue;
        }

        private int getSubmittedCount() {
            return this.mSubmittedCount.get();
        }

        /* access modifiers changed from: protected */
        public void afterExecute(Runnable r, Throwable t) {
            this.mSubmittedCount.decrementAndGet();
            super.afterExecute(r, t);
        }

        public void execute(@NonNull Runnable command) {
            if (!isShutdown()) {
                this.mSubmittedCount.incrementAndGet();
                try {
                    super.execute(command);
                } catch (RejectedExecutionException e) {
                    Log.e("ThreadUtils", "This will not happen!");
                    this.mWorkQueue.offer(command);
                } catch (Throwable th) {
                    this.mSubmittedCount.decrementAndGet();
                }
            }
        }
    }

    private static final class LinkedBlockingQueue4Util extends LinkedBlockingQueue<Runnable> {
        private int mCapacity = Integer.MAX_VALUE;
        /* access modifiers changed from: private */
        public volatile ThreadPoolExecutor4Util mPool;

        LinkedBlockingQueue4Util() {
        }

        LinkedBlockingQueue4Util(boolean isAddSubThreadFirstThenAddQueue) {
            if (isAddSubThreadFirstThenAddQueue) {
                this.mCapacity = 0;
            }
        }

        LinkedBlockingQueue4Util(int capacity) {
            this.mCapacity = capacity;
        }

        public boolean offer(@NonNull Runnable runnable) {
            if (this.mCapacity > size() || this.mPool == null || this.mPool.getPoolSize() >= this.mPool.getMaximumPoolSize()) {
                return super.offer(runnable);
            }
            return false;
        }
    }

    static final class UtilsThreadFactory extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private static final long serialVersionUID = -9209200509960368598L;
        private final boolean isDaemon;
        private final String namePrefix;
        private final int priority;

        UtilsThreadFactory(String prefix, int priority2) {
            this(prefix, priority2, false);
        }

        UtilsThreadFactory(String prefix, int priority2, boolean isDaemon2) {
            this.namePrefix = prefix + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = priority2;
            this.isDaemon = isDaemon2;
        }

        public Thread newThread(@NonNull Runnable r) {
            Thread t = new Thread(r, this.namePrefix + getAndIncrement()) {
                public void run() {
                    try {
                        super.run();
                    } catch (Throwable t) {
                        Log.e("ThreadUtils", "Request threw uncaught throwable", t);
                    }
                }
            };
            t.setDaemon(this.isDaemon);
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(e);
                }
            });
            t.setPriority(this.priority);
            return t;
        }
    }

    public static abstract class SimpleTask<T> extends Task<T> {
        public void onCancel() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        public void onFail(Throwable t) {
            Log.e("ThreadUtils", "onFail: ", t);
        }
    }

    public static abstract class Task<T> implements Runnable {
        private static final int CANCELLED = 4;
        private static final int COMPLETING = 3;
        private static final int EXCEPTIONAL = 2;
        private static final int INTERRUPTED = 5;
        private static final int NEW = 0;
        private static final int RUNNING = 1;
        private static final int TIMEOUT = 6;
        private Executor deliver;
        private volatile boolean isSchedule;
        /* access modifiers changed from: private */
        public OnTimeoutListener mTimeoutListener;
        private long mTimeoutMillis;
        private Timer mTimer;
        private volatile Thread runner;
        private final AtomicInteger state = new AtomicInteger(0);

        public interface OnTimeoutListener {
            void onTimeout();
        }

        public abstract T doInBackground() throws Throwable;

        public abstract void onCancel();

        public abstract void onFail(Throwable th);

        public abstract void onSuccess(T t);

        public void run() {
            if (this.isSchedule) {
                if (this.runner == null) {
                    if (this.state.compareAndSet(0, 1)) {
                        this.runner = Thread.currentThread();
                        if (this.mTimeoutListener != null) {
                            Log.w("ThreadUtils", "Scheduled task doesn't support timeout.");
                        }
                    } else {
                        return;
                    }
                } else if (this.state.get() != 1) {
                    return;
                }
            } else if (this.state.compareAndSet(0, 1)) {
                this.runner = Thread.currentThread();
                if (this.mTimeoutListener != null) {
                    this.mTimer = new Timer();
                    this.mTimer.schedule(new TimerTask() {
                        public void run() {
                            if (!Task.this.isDone() && Task.this.mTimeoutListener != null) {
                                Task.this.timeout();
                                Task.this.mTimeoutListener.onTimeout();
                            }
                        }
                    }, this.mTimeoutMillis);
                }
            } else {
                return;
            }
            try {
                final T result = doInBackground();
                if (this.isSchedule) {
                    if (this.state.get() == 1) {
                        getDeliver().execute(new Runnable() {
                            public void run() {
                                Task.this.onSuccess(result);
                            }
                        });
                    }
                } else if (this.state.compareAndSet(1, 3)) {
                    getDeliver().execute(new Runnable() {
                        public void run() {
                            Task.this.onSuccess(result);
                            Task.this.onDone();
                        }
                    });
                }
            } catch (InterruptedException e) {
                this.state.compareAndSet(4, 5);
            } catch (Throwable throwable) {
                if (this.state.compareAndSet(1, 2)) {
                    getDeliver().execute(new Runnable() {
                        public void run() {
                            Task.this.onFail(throwable);
                            Task.this.onDone();
                        }
                    });
                }
            }
        }

        public void cancel() {
            cancel(true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
            if (r3.runner == null) goto L_0x0020;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
            r3.runner.interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
            getDeliver().execute(new com.doraemon.util.ThreadUtils.Task.C10535(r3));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r4 == false) goto L_0x0020;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void cancel(boolean r4) {
            /*
                r3 = this;
                java.util.concurrent.atomic.AtomicInteger r1 = r3.state
                monitor-enter(r1)
                java.util.concurrent.atomic.AtomicInteger r0 = r3.state     // Catch:{ all -> 0x002d }
                int r0 = r0.get()     // Catch:{ all -> 0x002d }
                r2 = 1
                if (r0 <= r2) goto L_0x000e
                monitor-exit(r1)     // Catch:{ all -> 0x002d }
            L_0x000d:
                return
            L_0x000e:
                java.util.concurrent.atomic.AtomicInteger r0 = r3.state     // Catch:{ all -> 0x002d }
                r2 = 4
                r0.set(r2)     // Catch:{ all -> 0x002d }
                monitor-exit(r1)     // Catch:{ all -> 0x002d }
                if (r4 == 0) goto L_0x0020
                java.lang.Thread r0 = r3.runner
                if (r0 == 0) goto L_0x0020
                java.lang.Thread r0 = r3.runner
                r0.interrupt()
            L_0x0020:
                java.util.concurrent.Executor r0 = r3.getDeliver()
                com.doraemon.util.ThreadUtils$Task$5 r1 = new com.doraemon.util.ThreadUtils$Task$5
                r1.<init>()
                r0.execute(r1)
                goto L_0x000d
            L_0x002d:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x002d }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.ThreadUtils.Task.cancel(boolean):void");
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
            r3.runner.interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            onDone();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
            if (r3.runner == null) goto L_0x001e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void timeout() {
            /*
                r3 = this;
                java.util.concurrent.atomic.AtomicInteger r1 = r3.state
                monitor-enter(r1)
                java.util.concurrent.atomic.AtomicInteger r0 = r3.state     // Catch:{ all -> 0x0022 }
                int r0 = r0.get()     // Catch:{ all -> 0x0022 }
                r2 = 1
                if (r0 <= r2) goto L_0x000e
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            L_0x000d:
                return
            L_0x000e:
                java.util.concurrent.atomic.AtomicInteger r0 = r3.state     // Catch:{ all -> 0x0022 }
                r2 = 6
                r0.set(r2)     // Catch:{ all -> 0x0022 }
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                java.lang.Thread r0 = r3.runner
                if (r0 == 0) goto L_0x001e
                java.lang.Thread r0 = r3.runner
                r0.interrupt()
            L_0x001e:
                r3.onDone()
                goto L_0x000d
            L_0x0022:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.ThreadUtils.Task.timeout():void");
        }

        public boolean isCanceled() {
            return this.state.get() >= 4;
        }

        public boolean isDone() {
            return this.state.get() > 1;
        }

        public Task<T> setDeliver(Executor deliver2) {
            this.deliver = deliver2;
            return this;
        }

        public Task<T> setTimeout(long timeoutMillis, OnTimeoutListener listener) {
            this.mTimeoutMillis = timeoutMillis;
            this.mTimeoutListener = listener;
            return this;
        }

        /* access modifiers changed from: private */
        public void setSchedule(boolean isSchedule2) {
            this.isSchedule = isSchedule2;
        }

        private Executor getDeliver() {
            if (this.deliver == null) {
                return ThreadUtils.getGlobalDeliver();
            }
            return this.deliver;
        }

        /* access modifiers changed from: protected */
        @CallSuper
        public void onDone() {
            ThreadUtils.TASK_POOL_MAP.remove(this);
            if (this.mTimer != null) {
                this.mTimer.cancel();
                this.mTimer = null;
                this.mTimeoutListener = null;
            }
        }
    }

    public static class SyncValue<T> {
        private AtomicBoolean mFlag = new AtomicBoolean();
        private CountDownLatch mLatch = new CountDownLatch(1);
        private T mValue;

        public void setValue(T value) {
            if (this.mFlag.compareAndSet(false, true)) {
                this.mValue = value;
                this.mLatch.countDown();
            }
        }

        public T getValue() {
            if (!this.mFlag.get()) {
                try {
                    this.mLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.mValue;
        }
    }

    /* access modifiers changed from: private */
    public static Executor getGlobalDeliver() {
        if (sDeliver == null) {
            sDeliver = new Executor() {
                public void execute(@NonNull Runnable command) {
                    ThreadUtils.runOnUiThread(command);
                }
            };
        }
        return sDeliver;
    }
}
