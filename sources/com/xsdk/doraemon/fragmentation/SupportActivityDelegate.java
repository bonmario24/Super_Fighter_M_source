package com.xsdk.doraemon.fragmentation;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.ActivityCompat;
import android.view.MotionEvent;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentationMagician;
import com.xsdk.doraemon.fragmentation.ExtraTransaction;
import com.xsdk.doraemon.fragmentation.anim.DefaultVerticalAnimator;
import com.xsdk.doraemon.fragmentation.anim.FragmentAnimator;
import com.xsdk.doraemon.fragmentation.debug.DebugStackDelegate;
import com.xsdk.doraemon.fragmentation.queue.Action;

public class SupportActivityDelegate {
    private FragmentActivity mActivity;
    private DebugStackDelegate mDebugStackDelegate;
    private int mDefaultFragmentBackground = 0;
    private FragmentAnimator mFragmentAnimator;
    boolean mFragmentClickable = true;
    boolean mPopMultipleNoAnim = false;
    /* access modifiers changed from: private */
    public ISupportActivity mSupport;
    /* access modifiers changed from: private */
    public TransactionDelegate mTransactionDelegate;

    public SupportActivityDelegate(ISupportActivity support) {
        if (!(support instanceof FragmentActivity)) {
            throw new RuntimeException("Must extends FragmentActivity/AppCompatActivity");
        }
        this.mSupport = support;
        this.mActivity = (FragmentActivity) support;
        this.mDebugStackDelegate = new DebugStackDelegate(this.mActivity);
    }

    public ExtraTransaction extraTransaction() {
        return new ExtraTransaction.ExtraTransactionImpl((FragmentActivity) this.mSupport, getTopFragment(), getTransactionDelegate(), true);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.mTransactionDelegate = getTransactionDelegate();
        this.mFragmentAnimator = this.mSupport.onCreateFragmentAnimator();
        this.mDebugStackDelegate.onCreate(Fragmentation.getDefault().getMode());
    }

    public TransactionDelegate getTransactionDelegate() {
        if (this.mTransactionDelegate == null) {
            this.mTransactionDelegate = new TransactionDelegate(this.mSupport);
        }
        return this.mTransactionDelegate;
    }

    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        this.mDebugStackDelegate.onPostCreate(Fragmentation.getDefault().getMode());
    }

    public FragmentAnimator getFragmentAnimator() {
        return this.mFragmentAnimator.copy();
    }

    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        this.mFragmentAnimator = fragmentAnimator;
        for (Fragment fragment : FragmentationMagician.getActiveFragments(getSupportFragmentManager())) {
            if (fragment instanceof ISupportFragment) {
                SupportFragmentDelegate delegate = ((ISupportFragment) fragment).getSupportDelegate();
                if (delegate.mAnimByActivity) {
                    delegate.mFragmentAnimator = fragmentAnimator.copy();
                    if (delegate.mAnimHelper != null) {
                        delegate.mAnimHelper.notifyChanged(delegate.mFragmentAnimator);
                    }
                }
            }
        }
    }

    public FragmentAnimator onCreateFragmentAnimator(Context context) {
        return new DefaultVerticalAnimator();
    }

    public void setDefaultFragmentBackground(@DrawableRes int backgroundRes) {
        this.mDefaultFragmentBackground = backgroundRes;
    }

    public int getDefaultFragmentBackground() {
        return this.mDefaultFragmentBackground;
    }

    public void showFragmentStackHierarchyView() {
        this.mDebugStackDelegate.showFragmentStackHierarchyView();
    }

    public void logFragmentStackHierarchy(String TAG) {
        this.mDebugStackDelegate.logFragmentRecords(TAG);
    }

    public void post(Runnable runnable) {
        this.mTransactionDelegate.post(runnable);
    }

    public void onBackPressed() {
        this.mTransactionDelegate.mActionQueue.enqueue(new Action(3) {
            public void run() {
                if (!SupportActivityDelegate.this.mFragmentClickable) {
                    SupportActivityDelegate.this.mFragmentClickable = true;
                }
                if (!SupportActivityDelegate.this.mTransactionDelegate.dispatchBackPressedEvent(SupportHelper.getActiveFragment(SupportActivityDelegate.this.getSupportFragmentManager()))) {
                    SupportActivityDelegate.this.mSupport.onBackPressedSupport();
                }
            }
        });
    }

    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this.mActivity);
        }
    }

    public void onDestroy() {
        this.mDebugStackDelegate.onDestroy();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return !this.mFragmentClickable;
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
        loadRootFragment(containerId, toFragment, true, false);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnimation) {
        this.mTransactionDelegate.loadRootTransaction(getSupportFragmentManager(), containerId, toFragment, addToBackStack, allowAnimation);
    }

    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        this.mTransactionDelegate.loadMultipleRootTransaction(getSupportFragmentManager(), containerId, showPosition, toFragments);
    }

    public void showHideFragment(ISupportFragment showFragment) {
        showHideFragment(showFragment, (ISupportFragment) null);
    }

    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        this.mTransactionDelegate.showHideFragment(getSupportFragmentManager(), showFragment, hideFragment);
    }

    public void start(ISupportFragment toFragment) {
        start(toFragment, 0);
    }

    public void start(ISupportFragment toFragment, int launchMode) {
        this.mTransactionDelegate.dispatchStartTransaction(getSupportFragmentManager(), getTopFragment(), toFragment, 0, launchMode, 0);
    }

    public void startForResult(ISupportFragment toFragment, int requestCode) {
        this.mTransactionDelegate.dispatchStartTransaction(getSupportFragmentManager(), getTopFragment(), toFragment, requestCode, 0, 1);
    }

    public void startWithPop(ISupportFragment toFragment) {
        this.mTransactionDelegate.startWithPop(getSupportFragmentManager(), getTopFragment(), toFragment);
    }

    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        this.mTransactionDelegate.startWithPopTo(getSupportFragmentManager(), getTopFragment(), toFragment, targetFragmentClass.getName(), includeTargetFragment);
    }

    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
        this.mTransactionDelegate.dispatchStartTransaction(getSupportFragmentManager(), getTopFragment(), toFragment, 0, 0, addToBackStack ? 10 : 11);
    }

    public void pop() {
        this.mTransactionDelegate.pop(getSupportFragmentManager());
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popTo(targetFragmentClass, includeTargetFragment, (Runnable) null);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, Integer.MAX_VALUE);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        this.mTransactionDelegate.popTo(targetFragmentClass.getName(), includeTargetFragment, afterPopTransactionRunnable, getSupportFragmentManager(), popAnim);
    }

    /* access modifiers changed from: private */
    public FragmentManager getSupportFragmentManager() {
        return this.mActivity.getSupportFragmentManager();
    }

    private ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }
}
