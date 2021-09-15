package com.xsdk.doraemon.fragmentation;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.xsdk.doraemon.fragmentation.ExtraTransaction;
import com.xsdk.doraemon.fragmentation.anim.FragmentAnimator;
import com.xsdk.doraemon.fragmentation.helper.internal.AnimatorHelper;
import com.xsdk.doraemon.fragmentation.helper.internal.ResultRecord;
import com.xsdk.doraemon.fragmentation.helper.internal.TransactionRecord;
import com.xsdk.doraemon.fragmentation.helper.internal.VisibleDelegate;

public class SupportFragmentDelegate {
    private static final long NOT_FOUND_ANIM_TIME = 300;
    static final int STATUS_ROOT_ANIM_DISABLE = 1;
    static final int STATUS_ROOT_ANIM_ENABLE = 2;
    static final int STATUS_UN_ROOT = 0;
    protected FragmentActivity _mActivity;
    boolean mAnimByActivity = true;
    AnimatorHelper mAnimHelper;
    int mContainerId;
    private int mCustomEnterAnim = Integer.MIN_VALUE;
    private int mCustomExitAnim = Integer.MIN_VALUE;
    private int mCustomPopExitAnim = Integer.MIN_VALUE;
    EnterAnimListener mEnterAnimListener;
    private boolean mFirstCreateView = true;
    /* access modifiers changed from: private */
    public Fragment mFragment;
    FragmentAnimator mFragmentAnimator;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private boolean mIsHidden = true;
    private boolean mIsSharedElement;
    boolean mLockAnim;
    Bundle mNewBundle;
    private Runnable mNotifyEnterAnimEndRunnable = new Runnable() {
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0020, code lost:
            r3 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.access$200(r10.this$0).getView();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r10 = this;
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                androidx.fragment.app.Fragment r6 = r6.mFragment
                if (r6 != 0) goto L_0x0009
            L_0x0008:
                return
            L_0x0009:
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                com.xsdk.doraemon.fragmentation.ISupportFragment r6 = r6.mSupportF
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r7 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                android.os.Bundle r7 = r7.mSaveInstanceState
                r6.onEnterAnimationEnd(r7)
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                boolean r6 = r6.mRootViewClickable
                if (r6 != 0) goto L_0x0008
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                androidx.fragment.app.Fragment r6 = r6.mFragment
                android.view.View r3 = r6.getView()
                if (r3 == 0) goto L_0x0008
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                androidx.fragment.app.Fragment r6 = r6.mFragment
                com.xsdk.doraemon.fragmentation.ISupportFragment r2 = com.xsdk.doraemon.fragmentation.SupportHelper.getPreFragment(r6)
                if (r2 == 0) goto L_0x0008
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = r2.getSupportDelegate()
                long r4 = r6.getPopExitAnimDuration()
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                long r0 = r6.getEnterAnimDuration()
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r6 = com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.this
                android.os.Handler r6 = r6.mHandler
                com.xsdk.doraemon.fragmentation.SupportFragmentDelegate$3$1 r7 = new com.xsdk.doraemon.fragmentation.SupportFragmentDelegate$3$1
                r7.<init>(r3)
                long r8 = r4 - r0
                r6.postDelayed(r7, r8)
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xsdk.doraemon.fragmentation.SupportFragmentDelegate.C16983.run():void");
        }
    };
    private boolean mReplaceMode;
    private int mRootStatus = 0;
    /* access modifiers changed from: private */
    public boolean mRootViewClickable;
    /* access modifiers changed from: private */
    public Bundle mSaveInstanceState;
    /* access modifiers changed from: private */
    public ISupportActivity mSupport;
    /* access modifiers changed from: private */
    public ISupportFragment mSupportF;
    private TransactionDelegate mTransactionDelegate;
    TransactionRecord mTransactionRecord;
    private VisibleDelegate mVisibleDelegate;

    interface EnterAnimListener {
        void onEnterAnimStart();
    }

    public SupportFragmentDelegate(ISupportFragment support) {
        if (!(support instanceof Fragment)) {
            throw new RuntimeException("Must extends Fragment");
        }
        this.mSupportF = support;
        this.mFragment = (Fragment) support;
    }

    public ExtraTransaction extraTransaction() {
        if (this.mTransactionDelegate != null) {
            return new ExtraTransaction.ExtraTransactionImpl((FragmentActivity) this.mSupport, this.mSupportF, this.mTransactionDelegate, false);
        }
        throw new RuntimeException(this.mFragment.getClass().getSimpleName() + " not attach!");
    }

    public void onAttach(Activity activity) {
        if (activity instanceof ISupportActivity) {
            this.mSupport = (ISupportActivity) activity;
            this._mActivity = (FragmentActivity) activity;
            this.mTransactionDelegate = this.mSupport.getSupportDelegate().getTransactionDelegate();
            return;
        }
        throw new RuntimeException(activity.getClass().getSimpleName() + " must impl ISupportActivity!");
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        getVisibleDelegate().onCreate(savedInstanceState);
        Bundle bundle = this.mFragment.getArguments();
        if (bundle != null) {
            this.mRootStatus = bundle.getInt("fragmentation_arg_root_status", 0);
            this.mIsSharedElement = bundle.getBoolean("fragmentation_arg_is_shared_element", false);
            this.mContainerId = bundle.getInt("fragmentation_arg_container");
            this.mReplaceMode = bundle.getBoolean("fragmentation_arg_replace", false);
            this.mCustomEnterAnim = bundle.getInt("fragmentation_arg_custom_enter_anim", Integer.MIN_VALUE);
            this.mCustomExitAnim = bundle.getInt("fragmentation_arg_custom_exit_anim", Integer.MIN_VALUE);
            this.mCustomPopExitAnim = bundle.getInt("fragmentation_arg_custom_pop_exit_anim", Integer.MIN_VALUE);
        }
        if (savedInstanceState == null) {
            getFragmentAnimator();
        } else {
            savedInstanceState.setClassLoader(getClass().getClassLoader());
            this.mSaveInstanceState = savedInstanceState;
            this.mFragmentAnimator = (FragmentAnimator) savedInstanceState.getParcelable("fragmentation_state_save_animator");
            this.mIsHidden = savedInstanceState.getBoolean("fragmentation_state_save_status");
            this.mContainerId = savedInstanceState.getInt("fragmentation_arg_container");
        }
        this.mAnimHelper = new AnimatorHelper(this._mActivity.getApplicationContext(), this.mFragmentAnimator);
        final Animation enter = getEnterAnim();
        if (enter != null) {
            getEnterAnim().setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    SupportFragmentDelegate.this.mSupport.getSupportDelegate().mFragmentClickable = false;
                    SupportFragmentDelegate.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            SupportFragmentDelegate.this.mSupport.getSupportDelegate().mFragmentClickable = true;
                        }
                    }, enter.getDuration());
                }

                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (this.mSupport.getSupportDelegate().mPopMultipleNoAnim || this.mLockAnim) {
            if (transit != 8194 || !enter) {
                return this.mAnimHelper.getNoneAnim();
            }
            return this.mAnimHelper.getNoneAnimFixed();
        } else if (transit == 4097) {
            if (!enter) {
                return this.mAnimHelper.popExitAnim;
            }
            if (this.mRootStatus == 1) {
                return this.mAnimHelper.getNoneAnim();
            }
            Animation enterAnim = this.mAnimHelper.enterAnim;
            fixAnimationListener(enterAnim);
            return enterAnim;
        } else if (transit == 8194) {
            return enter ? this.mAnimHelper.popEnterAnim : this.mAnimHelper.exitAnim;
        } else {
            if (this.mIsSharedElement && enter) {
                compatSharedElements();
            }
            if (!enter) {
                return this.mAnimHelper.compatChildFragmentExitAnim(this.mFragment);
            }
            return null;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        getVisibleDelegate().onSaveInstanceState(outState);
        outState.putParcelable("fragmentation_state_save_animator", this.mFragmentAnimator);
        outState.putBoolean("fragmentation_state_save_status", this.mFragment.isHidden());
        outState.putInt("fragmentation_arg_container", this.mContainerId);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getVisibleDelegate().onActivityCreated(savedInstanceState);
        View view = this.mFragment.getView();
        if (view != null) {
            this.mRootViewClickable = view.isClickable();
            view.setClickable(true);
            setBackground(view);
        }
        if (savedInstanceState != null || this.mRootStatus == 1 || ((this.mFragment.getTag() != null && this.mFragment.getTag().startsWith("android:switcher:")) || (this.mReplaceMode && !this.mFirstCreateView))) {
            notifyEnterAnimEnd();
        } else if (this.mCustomEnterAnim != Integer.MIN_VALUE) {
            fixAnimationListener(this.mCustomEnterAnim == 0 ? this.mAnimHelper.getNoneAnim() : AnimationUtils.loadAnimation(this._mActivity, this.mCustomEnterAnim));
        }
        if (this.mFirstCreateView) {
            this.mFirstCreateView = false;
        }
    }

    public void onResume() {
        getVisibleDelegate().onResume();
    }

    public void onPause() {
        getVisibleDelegate().onPause();
    }

    public void onDestroyView() {
        this.mSupport.getSupportDelegate().mFragmentClickable = true;
        getVisibleDelegate().onDestroyView();
        getHandler().removeCallbacks(this.mNotifyEnterAnimEndRunnable);
    }

    public void onDestroy() {
        this.mTransactionDelegate.handleResultRecord(this.mFragment);
    }

    public void onHiddenChanged(boolean hidden) {
        getVisibleDelegate().onHiddenChanged(hidden);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        getVisibleDelegate().setUserVisibleHint(isVisibleToUser);
    }

    @Deprecated
    public void enqueueAction(Runnable runnable) {
        post(runnable);
    }

    public void post(Runnable runnable) {
        this.mTransactionDelegate.post(runnable);
    }

    public void onEnterAnimationEnd(Bundle savedInstanceState) {
    }

    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
    }

    public void onSupportVisible() {
    }

    public void onSupportInvisible() {
    }

    public final boolean isSupportVisible() {
        return getVisibleDelegate().isSupportVisible();
    }

    public FragmentAnimator onCreateFragmentAnimator() {
        return this.mSupport.getFragmentAnimator();
    }

    public FragmentAnimator getFragmentAnimator() {
        if (this.mSupport == null) {
            throw new RuntimeException("Fragment has not been attached to Activity!");
        }
        if (this.mFragmentAnimator == null) {
            this.mFragmentAnimator = this.mSupportF.onCreateFragmentAnimator();
            if (this.mFragmentAnimator == null) {
                this.mFragmentAnimator = this.mSupport.getFragmentAnimator();
            }
        }
        return this.mFragmentAnimator;
    }

    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        this.mFragmentAnimator = fragmentAnimator;
        if (this.mAnimHelper != null) {
            this.mAnimHelper.notifyChanged(fragmentAnimator);
        }
        this.mAnimByActivity = false;
    }

    public void setFragmentResult(int resultCode, Bundle bundle) {
        ResultRecord resultRecord;
        Bundle args = this.mFragment.getArguments();
        if (args != null && args.containsKey("fragment_arg_result_record") && (resultRecord = (ResultRecord) args.getParcelable("fragment_arg_result_record")) != null) {
            resultRecord.resultCode = resultCode;
            resultRecord.resultBundle = bundle;
        }
    }

    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
    }

    public void onNewBundle(Bundle args) {
    }

    public void putNewBundle(Bundle newBundle) {
        this.mNewBundle = newBundle;
    }

    public boolean onBackPressedSupport() {
        return false;
    }

    public void hideSoftInput() {
        Activity activity = this.mFragment.getActivity();
        if (activity != null) {
            SupportHelper.hideSoftInput(activity.getWindow().getDecorView());
        }
    }

    public void showSoftInput(View view) {
        SupportHelper.showSoftInput(view);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
        loadRootFragment(containerId, toFragment, true, false);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnim) {
        this.mTransactionDelegate.loadRootTransaction(getChildFragmentManager(), containerId, toFragment, addToBackStack, allowAnim);
    }

    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        this.mTransactionDelegate.loadMultipleRootTransaction(getChildFragmentManager(), containerId, showPosition, toFragments);
    }

    public void showHideFragment(ISupportFragment showFragment) {
        showHideFragment(showFragment, (ISupportFragment) null);
    }

    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        this.mTransactionDelegate.showHideFragment(getChildFragmentManager(), showFragment, hideFragment);
    }

    public void start(ISupportFragment toFragment) {
        start(toFragment, 0);
    }

    public void start(ISupportFragment toFragment, int launchMode) {
        this.mTransactionDelegate.dispatchStartTransaction(this.mFragment.getFragmentManager(), this.mSupportF, toFragment, 0, launchMode, 0);
    }

    public void startForResult(ISupportFragment toFragment, int requestCode) {
        this.mTransactionDelegate.dispatchStartTransaction(this.mFragment.getFragmentManager(), this.mSupportF, toFragment, requestCode, 0, 1);
    }

    public void startWithPop(ISupportFragment toFragment) {
        this.mTransactionDelegate.startWithPop(this.mFragment.getFragmentManager(), this.mSupportF, toFragment);
    }

    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        this.mTransactionDelegate.startWithPopTo(this.mFragment.getFragmentManager(), this.mSupportF, toFragment, targetFragmentClass.getName(), includeTargetFragment);
    }

    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
        this.mTransactionDelegate.dispatchStartTransaction(this.mFragment.getFragmentManager(), this.mSupportF, toFragment, 0, 0, addToBackStack ? 10 : 11);
    }

    public void startChild(ISupportFragment toFragment) {
        startChild(toFragment, 0);
    }

    public void startChild(ISupportFragment toFragment, int launchMode) {
        this.mTransactionDelegate.dispatchStartTransaction(getChildFragmentManager(), getTopFragment(), toFragment, 0, launchMode, 0);
    }

    public void startChildForResult(ISupportFragment toFragment, int requestCode) {
        this.mTransactionDelegate.dispatchStartTransaction(getChildFragmentManager(), getTopFragment(), toFragment, requestCode, 0, 1);
    }

    public void startChildWithPop(ISupportFragment toFragment) {
        this.mTransactionDelegate.startWithPop(getChildFragmentManager(), getTopFragment(), toFragment);
    }

    public void replaceChildFragment(ISupportFragment toFragment, boolean addToBackStack) {
        this.mTransactionDelegate.dispatchStartTransaction(getChildFragmentManager(), getTopFragment(), toFragment, 0, 0, addToBackStack ? 10 : 11);
    }

    public void pop() {
        this.mTransactionDelegate.pop(this.mFragment.getFragmentManager());
    }

    public void popChild() {
        this.mTransactionDelegate.pop(getChildFragmentManager());
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popTo(targetFragmentClass, includeTargetFragment, (Runnable) null);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, Integer.MAX_VALUE);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        this.mTransactionDelegate.popTo(targetFragmentClass.getName(), includeTargetFragment, afterPopTransactionRunnable, this.mFragment.getFragmentManager(), popAnim);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popToChild(targetFragmentClass, includeTargetFragment, (Runnable) null);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, Integer.MAX_VALUE);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        this.mTransactionDelegate.popTo(targetFragmentClass.getName(), includeTargetFragment, afterPopTransactionRunnable, getChildFragmentManager(), popAnim);
    }

    public void popQuiet() {
        this.mTransactionDelegate.popQuiet(this.mFragment.getFragmentManager(), this.mFragment);
    }

    private FragmentManager getChildFragmentManager() {
        return this.mFragment.getChildFragmentManager();
    }

    private ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getChildFragmentManager());
    }

    private void fixAnimationListener(Animation enterAnim) {
        getHandler().postDelayed(this.mNotifyEnterAnimEndRunnable, enterAnim.getDuration());
        this.mSupport.getSupportDelegate().mFragmentClickable = true;
        if (this.mEnterAnimListener != null) {
            getHandler().post(new Runnable() {
                public void run() {
                    SupportFragmentDelegate.this.mEnterAnimListener.onEnterAnimStart();
                    SupportFragmentDelegate.this.mEnterAnimListener = null;
                }
            });
        }
    }

    private void compatSharedElements() {
        notifyEnterAnimEnd();
    }

    public void setBackground(View view) {
        if ((this.mFragment.getTag() == null || !this.mFragment.getTag().startsWith("android:switcher:")) && this.mRootStatus == 0 && view.getBackground() == null) {
            int defaultBg = this.mSupport.getSupportDelegate().getDefaultFragmentBackground();
            if (defaultBg == 0) {
                view.setBackgroundResource(getWindowBackground());
            } else {
                view.setBackgroundResource(defaultBg);
            }
        }
    }

    private int getWindowBackground() {
        TypedArray a = this._mActivity.getTheme().obtainStyledAttributes(new int[]{16842836});
        int background = a.getResourceId(0, 0);
        a.recycle();
        return background;
    }

    private void notifyEnterAnimEnd() {
        getHandler().post(this.mNotifyEnterAnimEndRunnable);
        this.mSupport.getSupportDelegate().mFragmentClickable = true;
    }

    private Handler getHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
        return this.mHandler;
    }

    public VisibleDelegate getVisibleDelegate() {
        if (this.mVisibleDelegate == null) {
            this.mVisibleDelegate = new VisibleDelegate(this.mSupportF);
        }
        return this.mVisibleDelegate;
    }

    public FragmentActivity getActivity() {
        return this._mActivity;
    }

    private Animation getEnterAnim() {
        if (this.mCustomEnterAnim != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this._mActivity, this.mCustomEnterAnim);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!(this.mAnimHelper == null || this.mAnimHelper.enterAnim == null)) {
            return this.mAnimHelper.enterAnim;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public long getEnterAnimDuration() {
        Animation enter = getEnterAnim();
        if (enter != null) {
            return enter.getDuration();
        }
        return 300;
    }

    public long getExitAnimDuration() {
        if (this.mCustomExitAnim != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this._mActivity, this.mCustomExitAnim).getDuration();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!(this.mAnimHelper == null || this.mAnimHelper.exitAnim == null)) {
            return this.mAnimHelper.exitAnim.getDuration();
        }
        return 300;
    }

    /* access modifiers changed from: private */
    public long getPopExitAnimDuration() {
        if (this.mCustomPopExitAnim != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this._mActivity, this.mCustomPopExitAnim).getDuration();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!(this.mAnimHelper == null || this.mAnimHelper.popExitAnim == null)) {
            return this.mAnimHelper.popExitAnim.getDuration();
        }
        return 300;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Animation getExitAnim() {
        if (this.mCustomExitAnim != Integer.MIN_VALUE) {
            try {
                return AnimationUtils.loadAnimation(this._mActivity, this.mCustomExitAnim);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!(this.mAnimHelper == null || this.mAnimHelper.exitAnim == null)) {
            return this.mAnimHelper.exitAnim;
        }
        return null;
    }
}
