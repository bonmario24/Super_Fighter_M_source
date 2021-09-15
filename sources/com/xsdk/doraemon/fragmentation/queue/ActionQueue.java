package com.xsdk.doraemon.fragmentation.queue;

import android.os.Handler;
import android.os.Looper;
import com.xsdk.doraemon.fragmentation.ISupportFragment;
import com.xsdk.doraemon.fragmentation.SupportHelper;
import java.util.LinkedList;
import java.util.Queue;

public class ActionQueue {
    private Handler mMainHandler;
    /* access modifiers changed from: private */
    public Queue<Action> mQueue = new LinkedList();

    public ActionQueue(Handler mainHandler) {
        this.mMainHandler = mainHandler;
    }

    public void enqueue(final Action action) {
        if (!isThrottleBACK(action)) {
            if (action.action == 4 && this.mQueue.isEmpty() && Thread.currentThread() == Looper.getMainLooper().getThread()) {
                action.run();
            } else {
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        ActionQueue.this.enqueueAction(action);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void enqueueAction(Action action) {
        this.mQueue.add(action);
        if (this.mQueue.size() == 1) {
            handleAction();
        }
    }

    /* access modifiers changed from: private */
    public void handleAction() {
        if (!this.mQueue.isEmpty()) {
            Action action = this.mQueue.peek();
            action.run();
            executeNextAction(action);
        }
    }

    private void executeNextAction(Action action) {
        if (action.action == 1) {
            ISupportFragment top = SupportHelper.getBackStackTopFragment(action.fragmentManager);
            action.duration = top == null ? 300 : top.getSupportDelegate().getExitAnimDuration();
        }
        this.mMainHandler.postDelayed(new Runnable() {
            public void run() {
                ActionQueue.this.mQueue.poll();
                ActionQueue.this.handleAction();
            }
        }, action.duration);
    }

    private boolean isThrottleBACK(Action action) {
        Action head;
        if (action.action == 3 && (head = this.mQueue.peek()) != null && head.action == 1) {
            return true;
        }
        return false;
    }
}
