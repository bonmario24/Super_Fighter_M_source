package com.xsdk.doraemon.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.xsdk.doraemon.fragmentation.ExtraTransaction;
import com.xsdk.doraemon.fragmentation.ISupportFragment;
import com.xsdk.doraemon.fragmentation.SupportFragmentDelegate;
import com.xsdk.doraemon.fragmentation.SupportHelper;
import com.xsdk.doraemon.fragmentation.anim.FragmentAnimator;

public class SupportFragment extends Fragment implements ISupportFragment {
    /* access modifiers changed from: protected */
    public SupportActivity _mActivity;
    final SupportFragmentDelegate mDelegate = new SupportFragmentDelegate(this);

    public SupportFragmentDelegate getSupportDelegate() {
        return this.mDelegate;
    }

    public ExtraTransaction extraTransaction() {
        return this.mDelegate.extraTransaction();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mDelegate.onAttach(activity);
        this._mActivity = (SupportActivity) this.mDelegate.getActivity();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDelegate.onCreate(savedInstanceState);
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return this.mDelegate.onCreateAnimation(transit, enter, nextAnim);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mDelegate.onActivityCreated(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mDelegate.onSaveInstanceState(outState);
    }

    public void onResume() {
        super.onResume();
        this.mDelegate.onResume();
    }

    public void onPause() {
        super.onPause();
        this.mDelegate.onPause();
    }

    public void onDestroyView() {
        this.mDelegate.onDestroyView();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.mDelegate.onDestroy();
        super.onDestroy();
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.mDelegate.onHiddenChanged(hidden);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mDelegate.setUserVisibleHint(isVisibleToUser);
    }

    @Deprecated
    public void enqueueAction(Runnable runnable) {
        this.mDelegate.enqueueAction(runnable);
    }

    public void post(Runnable runnable) {
        this.mDelegate.post(runnable);
    }

    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        this.mDelegate.onEnterAnimationEnd(savedInstanceState);
    }

    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        this.mDelegate.onLazyInitView(savedInstanceState);
    }

    public void onSupportVisible() {
        this.mDelegate.onSupportVisible();
    }

    public void onSupportInvisible() {
        this.mDelegate.onSupportInvisible();
    }

    public final boolean isSupportVisible() {
        return this.mDelegate.isSupportVisible();
    }

    public FragmentAnimator onCreateFragmentAnimator() {
        return this.mDelegate.onCreateFragmentAnimator();
    }

    public FragmentAnimator getFragmentAnimator() {
        return this.mDelegate.getFragmentAnimator();
    }

    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        this.mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    public boolean onBackPressedSupport() {
        return this.mDelegate.onBackPressedSupport();
    }

    public void setFragmentResult(int resultCode, Bundle bundle) {
        this.mDelegate.setFragmentResult(resultCode, bundle);
    }

    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        this.mDelegate.onFragmentResult(requestCode, resultCode, data);
    }

    public void onNewBundle(Bundle args) {
        this.mDelegate.onNewBundle(args);
    }

    public void putNewBundle(Bundle newBundle) {
        this.mDelegate.putNewBundle(newBundle);
    }

    /* access modifiers changed from: protected */
    public void hideSoftInput() {
        this.mDelegate.hideSoftInput();
    }

    /* access modifiers changed from: protected */
    public void showSoftInput(View view) {
        this.mDelegate.showSoftInput(view);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
        this.mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnim) {
        this.mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim);
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

    public void popChild() {
        this.mDelegate.popChild();
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

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        this.mDelegate.popToChild(targetFragmentClass, includeTargetFragment);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        this.mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        this.mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getFragmentManager());
    }

    public ISupportFragment getTopChildFragment() {
        return SupportHelper.getTopFragment(getChildFragmentManager());
    }

    public ISupportFragment getPreFragment() {
        return SupportHelper.getPreFragment(this);
    }

    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getFragmentManager(), fragmentClass);
    }

    public <T extends ISupportFragment> T findChildFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getChildFragmentManager(), fragmentClass);
    }
}
