package com.xsdk.doraemon.base;

import android.os.Bundle;
import android.view.MotionEvent;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.xsdk.doraemon.fragmentation.ExtraTransaction;
import com.xsdk.doraemon.fragmentation.ISupportActivity;
import com.xsdk.doraemon.fragmentation.ISupportFragment;
import com.xsdk.doraemon.fragmentation.SupportActivityDelegate;
import com.xsdk.doraemon.fragmentation.SupportHelper;
import com.xsdk.doraemon.fragmentation.anim.FragmentAnimator;

public class SupportActivity extends FragmentActivity implements ISupportActivity {
    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);

    public SupportActivityDelegate getSupportDelegate() {
        return this.mDelegate;
    }

    public ExtraTransaction extraTransaction() {
        return this.mDelegate.extraTransaction();
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDelegate.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mDelegate.onPostCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mDelegate.onDestroy();
        super.onDestroy();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return this.mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    public final void onBackPressed() {
        this.mDelegate.onBackPressed();
    }

    public void onBackPressedSupport() {
        this.mDelegate.onBackPressedSupport();
    }

    public FragmentAnimator getFragmentAnimator() {
        return this.mDelegate.getFragmentAnimator();
    }

    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        this.mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    public FragmentAnimator onCreateFragmentAnimator() {
        return this.mDelegate.onCreateFragmentAnimator(this);
    }

    public void post(Runnable runnable) {
        this.mDelegate.post(runnable);
    }

    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        this.mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnimation) {
        this.mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnimation);
    }

    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        this.mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    public void showHideFragment(ISupportFragment showFragment) {
        this.mDelegate.showHideFragment(showFragment);
    }

    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        this.mDelegate.showHideFragment(showFragment, hideFragment);
    }

    public void start(ISupportFragment toFragment) {
        this.mDelegate.start(toFragment);
    }

    public void start(ISupportFragment toFragment, int launchMode) {
        this.mDelegate.start(toFragment, launchMode);
    }

    public void startForResult(ISupportFragment toFragment, int requestCode) {
        this.mDelegate.startForResult(toFragment, requestCode);
    }

    public void startWithPop(ISupportFragment toFragment) {
        this.mDelegate.startWithPop(toFragment);
    }

    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        this.mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
    }

    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
        this.mDelegate.replaceFragment(toFragment, addToBackStack);
    }

    public void pop() {
        this.mDelegate.pop();
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        this.mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        this.mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        this.mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    public void setDefaultFragmentBackground(@DrawableRes int backgroundRes) {
        this.mDelegate.setDefaultFragmentBackground(backgroundRes);
    }

    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }
}
