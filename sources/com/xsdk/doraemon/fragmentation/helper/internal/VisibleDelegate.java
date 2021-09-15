package com.xsdk.doraemon.fragmentation.helper.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentationMagician;
import com.xsdk.doraemon.fragmentation.ISupportFragment;
import java.util.List;

public class VisibleDelegate {
    private static final String FRAGMENTATION_STATE_SAVE_COMPAT_REPLACE = "fragmentation_compat_replace";
    private static final String FRAGMENTATION_STATE_SAVE_IS_INVISIBLE_WHEN_LEAVE = "fragmentation_invisible_when_leave";
    private boolean mFirstCreateViewCompatReplace = true;
    private Fragment mFragment;
    private Handler mHandler;
    private boolean mInvisibleWhenLeave;
    private boolean mIsFirstVisible = true;
    private boolean mIsSupportVisible;
    private boolean mNeedDispatch = true;
    private Bundle mSaveInstanceState;
    private ISupportFragment mSupportF;

    public VisibleDelegate(ISupportFragment fragment) {
        this.mSupportF = fragment;
        this.mFragment = (Fragment) fragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mSaveInstanceState = savedInstanceState;
            this.mInvisibleWhenLeave = savedInstanceState.getBoolean(FRAGMENTATION_STATE_SAVE_IS_INVISIBLE_WHEN_LEAVE);
            this.mFirstCreateViewCompatReplace = savedInstanceState.getBoolean(FRAGMENTATION_STATE_SAVE_COMPAT_REPLACE);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(FRAGMENTATION_STATE_SAVE_IS_INVISIBLE_WHEN_LEAVE, this.mInvisibleWhenLeave);
        outState.putBoolean(FRAGMENTATION_STATE_SAVE_COMPAT_REPLACE, this.mFirstCreateViewCompatReplace);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (this.mFirstCreateViewCompatReplace || this.mFragment.getTag() == null || !this.mFragment.getTag().startsWith("android:switcher:")) {
            if (this.mFirstCreateViewCompatReplace) {
                this.mFirstCreateViewCompatReplace = false;
            }
            if (!this.mInvisibleWhenLeave && !this.mFragment.isHidden() && this.mFragment.getUserVisibleHint()) {
                if ((this.mFragment.getParentFragment() != null && isFragmentVisible(this.mFragment.getParentFragment())) || this.mFragment.getParentFragment() == null) {
                    this.mNeedDispatch = false;
                    safeDispatchUserVisibleHint(true);
                }
            }
        }
    }

    public void onResume() {
        if (!this.mIsFirstVisible && !this.mIsSupportVisible && !this.mInvisibleWhenLeave && isFragmentVisible(this.mFragment)) {
            this.mNeedDispatch = false;
            dispatchSupportVisible(true);
        }
    }

    public void onPause() {
        if (!this.mIsSupportVisible || !isFragmentVisible(this.mFragment)) {
            this.mInvisibleWhenLeave = true;
            return;
        }
        this.mNeedDispatch = false;
        this.mInvisibleWhenLeave = false;
        dispatchSupportVisible(false);
    }

    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !this.mFragment.isResumed()) {
            this.mInvisibleWhenLeave = false;
        } else if (hidden) {
            safeDispatchUserVisibleHint(false);
        } else {
            enqueueDispatchVisible();
        }
    }

    public void onDestroyView() {
        this.mIsFirstVisible = true;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!this.mFragment.isResumed() && (this.mFragment.isAdded() || !isVisibleToUser)) {
            return;
        }
        if (!this.mIsSupportVisible && isVisibleToUser) {
            safeDispatchUserVisibleHint(true);
        } else if (this.mIsSupportVisible && !isVisibleToUser) {
            dispatchSupportVisible(false);
        }
    }

    private void safeDispatchUserVisibleHint(boolean visible) {
        if (!this.mIsFirstVisible) {
            dispatchSupportVisible(visible);
        } else if (visible) {
            enqueueDispatchVisible();
        }
    }

    private void enqueueDispatchVisible() {
        getHandler().post(new Runnable() {
            public void run() {
                VisibleDelegate.this.dispatchSupportVisible(true);
            }
        });
    }

    /* access modifiers changed from: private */
    public void dispatchSupportVisible(boolean visible) {
        if (visible && isParentInvisible()) {
            return;
        }
        if (this.mIsSupportVisible == visible) {
            this.mNeedDispatch = true;
            return;
        }
        this.mIsSupportVisible = visible;
        if (!visible) {
            dispatchChild(false);
            this.mSupportF.onSupportInvisible();
        } else if (!checkAddState()) {
            this.mSupportF.onSupportVisible();
            if (this.mIsFirstVisible) {
                this.mIsFirstVisible = false;
                this.mSupportF.onLazyInitView(this.mSaveInstanceState);
            }
            dispatchChild(true);
        }
    }

    private void dispatchChild(boolean visible) {
        List<Fragment> childFragments;
        if (!this.mNeedDispatch) {
            this.mNeedDispatch = true;
        } else if (!checkAddState() && (childFragments = FragmentationMagician.getActiveFragments(this.mFragment.getChildFragmentManager())) != null) {
            for (Fragment child : childFragments) {
                if ((child instanceof ISupportFragment) && !child.isHidden() && child.getUserVisibleHint()) {
                    ((ISupportFragment) child).getSupportDelegate().getVisibleDelegate().dispatchSupportVisible(visible);
                }
            }
        }
    }

    private boolean isParentInvisible() {
        Fragment parentFragment = this.mFragment.getParentFragment();
        if (parentFragment instanceof ISupportFragment) {
            if (!((ISupportFragment) parentFragment).isSupportVisible()) {
                return true;
            }
            return false;
        } else if (parentFragment == null || parentFragment.isVisible()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkAddState() {
        boolean z = false;
        if (this.mFragment.isAdded()) {
            return false;
        }
        if (!this.mIsSupportVisible) {
            z = true;
        }
        this.mIsSupportVisible = z;
        return true;
    }

    private boolean isFragmentVisible(Fragment fragment) {
        return !fragment.isHidden() && fragment.getUserVisibleHint();
    }

    public boolean isSupportVisible() {
        return this.mIsSupportVisible;
    }

    private Handler getHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
        return this.mHandler;
    }
}
