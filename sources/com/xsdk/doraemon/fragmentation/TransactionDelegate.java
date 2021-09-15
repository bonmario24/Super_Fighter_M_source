package com.xsdk.doraemon.fragmentation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentationMagician;
import com.xsdk.doraemon.fragmentation.exception.AfterSaveStateTransactionWarning;
import com.xsdk.doraemon.fragmentation.helper.internal.ResultRecord;
import com.xsdk.doraemon.fragmentation.helper.internal.TransactionRecord;
import com.xsdk.doraemon.fragmentation.queue.Action;
import com.xsdk.doraemon.fragmentation.queue.ActionQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class TransactionDelegate {
    static final int DEFAULT_POPTO_ANIM = Integer.MAX_VALUE;
    static final String FRAGMENTATION_ARG_CONTAINER = "fragmentation_arg_container";
    static final String FRAGMENTATION_ARG_CUSTOM_ENTER_ANIM = "fragmentation_arg_custom_enter_anim";
    static final String FRAGMENTATION_ARG_CUSTOM_EXIT_ANIM = "fragmentation_arg_custom_exit_anim";
    static final String FRAGMENTATION_ARG_CUSTOM_POP_EXIT_ANIM = "fragmentation_arg_custom_pop_exit_anim";
    static final String FRAGMENTATION_ARG_IS_SHARED_ELEMENT = "fragmentation_arg_is_shared_element";
    static final String FRAGMENTATION_ARG_REPLACE = "fragmentation_arg_replace";
    static final String FRAGMENTATION_ARG_RESULT_RECORD = "fragment_arg_result_record";
    static final String FRAGMENTATION_ARG_ROOT_STATUS = "fragmentation_arg_root_status";
    static final String FRAGMENTATION_STATE_SAVE_ANIMATOR = "fragmentation_state_save_animator";
    static final String FRAGMENTATION_STATE_SAVE_IS_HIDDEN = "fragmentation_state_save_status";
    private static final String FRAGMENTATION_STATE_SAVE_RESULT = "fragmentation_state_save_result";
    private static final String TAG = "Fragmentation";
    static final int TYPE_ADD = 0;
    static final int TYPE_ADD_RESULT = 1;
    static final int TYPE_ADD_RESULT_WITHOUT_HIDE = 3;
    static final int TYPE_ADD_WITHOUT_HIDE = 2;
    static final int TYPE_REPLACE = 10;
    static final int TYPE_REPLACE_DONT_BACK = 11;
    ActionQueue mActionQueue = new ActionQueue(this.mHandler);
    private FragmentActivity mActivity;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public ISupportActivity mSupport;

    TransactionDelegate(ISupportActivity support) {
        this.mSupport = support;
        this.mActivity = (FragmentActivity) support;
    }

    /* access modifiers changed from: package-private */
    public void post(final Runnable runnable) {
        this.mActionQueue.enqueue(new Action() {
            public void run() {
                runnable.run();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void loadRootTransaction(FragmentManager fm, int containerId, ISupportFragment to, boolean addToBackStack, boolean allowAnimation) {
        final int i = containerId;
        final ISupportFragment iSupportFragment = to;
        final FragmentManager fragmentManager = fm;
        final boolean z = addToBackStack;
        final boolean z2 = allowAnimation;
        enqueue(fm, new Action(4) {
            public void run() {
                TransactionDelegate.this.bindContainerId(i, iSupportFragment);
                String toFragmentTag = iSupportFragment.getClass().getName();
                TransactionRecord transactionRecord = iSupportFragment.getSupportDelegate().mTransactionRecord;
                if (!(transactionRecord == null || transactionRecord.tag == null)) {
                    toFragmentTag = transactionRecord.tag;
                }
                TransactionDelegate.this.start(fragmentManager, (ISupportFragment) null, iSupportFragment, toFragmentTag, !z, (ArrayList<TransactionRecord.SharedElement>) null, z2, 10);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void loadMultipleRootTransaction(FragmentManager fm, int containerId, int showPosition, ISupportFragment... tos) {
        final FragmentManager fragmentManager = fm;
        final ISupportFragment[] iSupportFragmentArr = tos;
        final int i = containerId;
        final int i2 = showPosition;
        enqueue(fm, new Action(4) {
            public void run() {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                for (int i = 0; i < iSupportFragmentArr.length; i++) {
                    Fragment to = (Fragment) iSupportFragmentArr[i];
                    TransactionDelegate.this.getArguments(to).putInt(TransactionDelegate.FRAGMENTATION_ARG_ROOT_STATUS, 1);
                    TransactionDelegate.this.bindContainerId(i, iSupportFragmentArr[i]);
                    ft.add(i, to, to.getClass().getName());
                    if (i != i2) {
                        ft.hide(to);
                    }
                }
                TransactionDelegate.this.supportCommit(fragmentManager, ft);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void dispatchStartTransaction(FragmentManager fm, ISupportFragment from, ISupportFragment to, int requestCode, int launchMode, int type) {
        int i = 2;
        if (launchMode != 2) {
            i = 0;
        }
        final FragmentManager fragmentManager = fm;
        final ISupportFragment iSupportFragment = from;
        final ISupportFragment iSupportFragment2 = to;
        final int i2 = requestCode;
        final int i3 = launchMode;
        final int i4 = type;
        enqueue(fm, new Action(i) {
            public void run() {
                TransactionDelegate.this.doDispatchStartTransaction(fragmentManager, iSupportFragment, iSupportFragment2, i2, i3, i4);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void showHideFragment(final FragmentManager fm, final ISupportFragment showFragment, final ISupportFragment hideFragment) {
        enqueue(fm, new Action() {
            public void run() {
                TransactionDelegate.this.doShowHideFragment(fm, showFragment, hideFragment);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void startWithPop(FragmentManager fm, ISupportFragment from, ISupportFragment to) {
        final ISupportFragment iSupportFragment = from;
        final FragmentManager fragmentManager = fm;
        final ISupportFragment iSupportFragment2 = to;
        enqueue(fm, new Action(2) {
            public void run() {
                ISupportFragment top = TransactionDelegate.this.getTopFragmentForStart(iSupportFragment, fragmentManager);
                if (top == null) {
                    throw new NullPointerException("There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment() first!");
                }
                TransactionDelegate.this.bindContainerId(top.getSupportDelegate().mContainerId, iSupportFragment2);
                TransactionDelegate.this.handleAfterSaveInStateTransactionException(fragmentManager, "popTo()");
                FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager);
                top.getSupportDelegate().mLockAnim = true;
                if (!FragmentationMagician.isStateSaved(fragmentManager)) {
                    TransactionDelegate.this.mockStartWithPopAnim(SupportHelper.getTopFragment(fragmentManager), iSupportFragment2, top.getSupportDelegate().mAnimHelper.popExitAnim);
                }
                TransactionDelegate.this.removeTopFragment(fragmentManager);
                FragmentationMagician.popBackStackAllowingStateLoss(fragmentManager);
                FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager);
            }
        });
        dispatchStartTransaction(fm, from, to, 0, 0, 0);
    }

    /* access modifiers changed from: package-private */
    public void startWithPopTo(FragmentManager fm, ISupportFragment from, ISupportFragment to, String fragmentTag, boolean includeTargetFragment) {
        final boolean z = includeTargetFragment;
        final FragmentManager fragmentManager = fm;
        final String str = fragmentTag;
        final ISupportFragment iSupportFragment = from;
        final ISupportFragment iSupportFragment2 = to;
        enqueue(fm, new Action(2) {
            public void run() {
                int flag = 0;
                if (z) {
                    flag = 1;
                }
                List<Fragment> willPopFragments = SupportHelper.getWillPopFragments(fragmentManager, str, z);
                ISupportFragment top = TransactionDelegate.this.getTopFragmentForStart(iSupportFragment, fragmentManager);
                if (top == null) {
                    throw new NullPointerException("There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment() first!");
                }
                TransactionDelegate.this.bindContainerId(top.getSupportDelegate().mContainerId, iSupportFragment2);
                if (willPopFragments.size() > 0) {
                    TransactionDelegate.this.handleAfterSaveInStateTransactionException(fragmentManager, "startWithPopTo()");
                    FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager);
                    if (!FragmentationMagician.isStateSaved(fragmentManager)) {
                        TransactionDelegate.this.mockStartWithPopAnim(SupportHelper.getTopFragment(fragmentManager), iSupportFragment2, top.getSupportDelegate().mAnimHelper.popExitAnim);
                    }
                    TransactionDelegate.this.safePopTo(str, fragmentManager, flag, willPopFragments);
                }
            }
        });
        dispatchStartTransaction(fm, from, to, 0, 0, 0);
    }

    /* access modifiers changed from: package-private */
    public void remove(FragmentManager fm, Fragment fragment, boolean showPreFragment) {
        final FragmentManager fragmentManager = fm;
        final Fragment fragment2 = fragment;
        final boolean z = showPreFragment;
        enqueue(fm, new Action(1, fm) {
            public void run() {
                FragmentTransaction ft = fragmentManager.beginTransaction().setTransition(8194).remove(fragment2);
                if (z) {
                    ISupportFragment preFragment = SupportHelper.getPreFragment(fragment2);
                    if (preFragment instanceof Fragment) {
                        ft.show((Fragment) preFragment);
                    }
                }
                TransactionDelegate.this.supportCommit(fragmentManager, ft);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void pop(final FragmentManager fm) {
        enqueue(fm, new Action(1, fm) {
            public void run() {
                TransactionDelegate.this.handleAfterSaveInStateTransactionException(fm, "pop()");
                FragmentationMagician.popBackStackAllowingStateLoss(fm);
                TransactionDelegate.this.removeTopFragment(fm);
            }
        });
    }

    /* access modifiers changed from: private */
    public void removeTopFragment(FragmentManager fm) {
        try {
            ISupportFragment top = SupportHelper.getBackStackTopFragment(fm);
            if (top != null) {
                fm.beginTransaction().setTransition(8194).remove((Fragment) top).commitAllowingStateLoss();
            }
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public void popQuiet(final FragmentManager fm, final Fragment fragment) {
        enqueue(fm, new Action(2) {
            public void run() {
                TransactionDelegate.this.mSupport.getSupportDelegate().mPopMultipleNoAnim = true;
                TransactionDelegate.this.removeTopFragment(fm);
                FragmentationMagician.popBackStackAllowingStateLoss(fm, fragment.getTag(), 0);
                FragmentationMagician.popBackStackAllowingStateLoss(fm);
                FragmentationMagician.executePendingTransactionsAllowingStateLoss(fm);
                TransactionDelegate.this.mSupport.getSupportDelegate().mPopMultipleNoAnim = false;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void popTo(String targetFragmentTag, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, FragmentManager fm, int popAnim) {
        final String str = targetFragmentTag;
        final boolean z = includeTargetFragment;
        final FragmentManager fragmentManager = fm;
        final int i = popAnim;
        final Runnable runnable = afterPopTransactionRunnable;
        enqueue(fm, new Action(2) {
            public void run() {
                TransactionDelegate.this.doPopTo(str, z, fragmentManager, i);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchBackPressedEvent(ISupportFragment activeFragment) {
        if (activeFragment == null || (!activeFragment.onBackPressedSupport() && !dispatchBackPressedEvent((ISupportFragment) ((Fragment) activeFragment).getParentFragment()))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void handleResultRecord(Fragment from) {
        ResultRecord resultRecord;
        try {
            Bundle args = from.getArguments();
            if (args != null && (resultRecord = (ResultRecord) args.getParcelable(FRAGMENTATION_ARG_RESULT_RECORD)) != null) {
                ((ISupportFragment) from.getFragmentManager().getFragment(from.getArguments(), FRAGMENTATION_STATE_SAVE_RESULT)).onFragmentResult(resultRecord.requestCode, resultRecord.resultCode, resultRecord.resultBundle);
            }
        } catch (IllegalStateException e) {
        }
    }

    private void enqueue(FragmentManager fm, Action action) {
        if (fm == null) {
            Log.w(TAG, "FragmentManager is null, skip the action!");
        } else {
            this.mActionQueue.enqueue(action);
        }
    }

    /* access modifiers changed from: private */
    public void doDispatchStartTransaction(FragmentManager fm, ISupportFragment from, ISupportFragment to, int requestCode, int launchMode, int type) {
        checkNotNull(to, "toFragment == null");
        if ((type == 1 || type == 3) && from != null) {
            if (!((Fragment) from).isAdded()) {
                Log.w(TAG, ((Fragment) from).getClass().getSimpleName() + " has not been attached yet! startForResult() converted to start()");
            } else {
                saveRequestCode(fm, (Fragment) from, (Fragment) to, requestCode);
            }
        }
        ISupportFragment from2 = getTopFragmentForStart(from, fm);
        int containerId = getArguments((Fragment) to).getInt(FRAGMENTATION_ARG_CONTAINER, 0);
        if (from2 == null && containerId == 0) {
            Log.e(TAG, "There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment()!");
            return;
        }
        if (from2 != null && containerId == 0) {
            bindContainerId(from2.getSupportDelegate().mContainerId, to);
        }
        String toFragmentTag = to.getClass().getName();
        boolean dontAddToBackStack = false;
        ArrayList<TransactionRecord.SharedElement> sharedElementList = null;
        TransactionRecord transactionRecord = to.getSupportDelegate().mTransactionRecord;
        if (transactionRecord != null) {
            if (transactionRecord.tag != null) {
                toFragmentTag = transactionRecord.tag;
            }
            dontAddToBackStack = transactionRecord.dontAddToBackStack;
            if (transactionRecord.sharedElementList != null) {
                sharedElementList = transactionRecord.sharedElementList;
            }
        }
        if (!handleLaunchMode(fm, from2, to, toFragmentTag, launchMode)) {
            start(fm, from2, to, toFragmentTag, dontAddToBackStack, sharedElementList, false, type);
        }
    }

    /* access modifiers changed from: private */
    public ISupportFragment getTopFragmentForStart(ISupportFragment from, FragmentManager fm) {
        if (from == null) {
            return SupportHelper.getTopFragment(fm);
        }
        if (from.getSupportDelegate().mContainerId == 0) {
            Fragment fromF = (Fragment) from;
            if (fromF.getTag() != null && !fromF.getTag().startsWith("android:switcher:")) {
                throw new IllegalStateException("Can't find container, please call loadRootFragment() first!");
            }
        }
        return SupportHelper.getTopFragment(fm, from.getSupportDelegate().mContainerId);
    }

    /* access modifiers changed from: private */
    public void start(FragmentManager fm, ISupportFragment from, ISupportFragment to, String toFragmentTag, boolean dontAddToBackStack, ArrayList<TransactionRecord.SharedElement> sharedElementList, boolean allowRootFragmentAnim, int type) {
        int i;
        FragmentTransaction ft = fm.beginTransaction();
        boolean addMode = type == 0 || type == 1 || type == 2 || type == 3;
        Fragment fromF = (Fragment) from;
        Fragment toF = (Fragment) to;
        Bundle args = getArguments(toF);
        args.putBoolean(FRAGMENTATION_ARG_REPLACE, !addMode);
        if (sharedElementList != null) {
            args.putBoolean(FRAGMENTATION_ARG_IS_SHARED_ELEMENT, true);
            Iterator<TransactionRecord.SharedElement> it = sharedElementList.iterator();
            while (it.hasNext()) {
                TransactionRecord.SharedElement item = it.next();
                ft.addSharedElement(item.sharedElement, item.sharedName);
            }
        } else if (addMode) {
            TransactionRecord record = to.getSupportDelegate().mTransactionRecord;
            if (record == null || record.targetFragmentEnter == Integer.MIN_VALUE) {
                ft.setTransition(4097);
            } else {
                ft.setCustomAnimations(record.targetFragmentEnter, record.currentFragmentPopExit, record.currentFragmentPopEnter, record.targetFragmentExit);
                args.putInt(FRAGMENTATION_ARG_CUSTOM_ENTER_ANIM, record.targetFragmentEnter);
                args.putInt(FRAGMENTATION_ARG_CUSTOM_EXIT_ANIM, record.targetFragmentExit);
                args.putInt(FRAGMENTATION_ARG_CUSTOM_POP_EXIT_ANIM, record.currentFragmentPopExit);
            }
        } else {
            args.putInt(FRAGMENTATION_ARG_ROOT_STATUS, 1);
        }
        if (from == null) {
            ft.replace(args.getInt(FRAGMENTATION_ARG_CONTAINER), toF, toFragmentTag);
            if (!addMode) {
                ft.setTransition(4097);
                if (allowRootFragmentAnim) {
                    i = 2;
                } else {
                    i = 1;
                }
                args.putInt(FRAGMENTATION_ARG_ROOT_STATUS, i);
            }
        } else if (addMode) {
            ft.add(from.getSupportDelegate().mContainerId, toF, toFragmentTag);
            if (!(type == 2 || type == 3)) {
                ft.hide(fromF);
            }
        } else {
            ft.replace(from.getSupportDelegate().mContainerId, toF, toFragmentTag);
        }
        if (!dontAddToBackStack && type != 11) {
            ft.addToBackStack(toFragmentTag);
        }
        supportCommit(fm, ft);
    }

    /* access modifiers changed from: private */
    public void doShowHideFragment(FragmentManager fm, ISupportFragment showFragment, ISupportFragment hideFragment) {
        if (showFragment != hideFragment) {
            FragmentTransaction ft = fm.beginTransaction().show((Fragment) showFragment);
            if (hideFragment == null) {
                List<Fragment> fragmentList = FragmentationMagician.getActiveFragments(fm);
                if (fragmentList != null) {
                    for (Fragment fragment : fragmentList) {
                        if (!(fragment == null || fragment == showFragment)) {
                            ft.hide(fragment);
                        }
                    }
                }
            } else {
                ft.hide((Fragment) hideFragment);
            }
            supportCommit(fm, ft);
        }
    }

    /* access modifiers changed from: private */
    public void bindContainerId(int containerId, ISupportFragment to) {
        getArguments((Fragment) to).putInt(FRAGMENTATION_ARG_CONTAINER, containerId);
    }

    /* access modifiers changed from: private */
    public Bundle getArguments(Fragment fragment) {
        Bundle bundle = fragment.getArguments();
        if (bundle != null) {
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        fragment.setArguments(bundle2);
        return bundle2;
    }

    /* access modifiers changed from: private */
    public void supportCommit(FragmentManager fm, FragmentTransaction transaction) {
        handleAfterSaveInStateTransactionException(fm, "commit()");
        transaction.commitAllowingStateLoss();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = com.xsdk.doraemon.fragmentation.SupportHelper.findBackStackFragment(r8.getClass(), r9, r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean handleLaunchMode(androidx.fragment.app.FragmentManager r6, com.xsdk.doraemon.fragmentation.ISupportFragment r7, final com.xsdk.doraemon.fragmentation.ISupportFragment r8, java.lang.String r9, int r10) {
        /*
            r5 = this;
            r2 = 1
            r1 = 0
            if (r7 != 0) goto L_0x0005
        L_0x0004:
            return r1
        L_0x0005:
            java.lang.Class r3 = r8.getClass()
            com.xsdk.doraemon.fragmentation.ISupportFragment r0 = com.xsdk.doraemon.fragmentation.SupportHelper.findBackStackFragment(r3, r9, r6)
            if (r0 == 0) goto L_0x0004
            if (r10 != r2) goto L_0x002e
            if (r8 == r7) goto L_0x0029
            java.lang.Class r3 = r8.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.Class r4 = r7.getClass()
            java.lang.String r4 = r4.getName()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0004
        L_0x0029:
            r5.handleNewBundle(r8, r0)
            r1 = r2
            goto L_0x0004
        L_0x002e:
            r3 = 2
            if (r10 != r3) goto L_0x0004
            r3 = 2147483647(0x7fffffff, float:NaN)
            r5.doPopTo(r9, r1, r6, r3)
            android.os.Handler r1 = r5.mHandler
            com.xsdk.doraemon.fragmentation.TransactionDelegate$12 r3 = new com.xsdk.doraemon.fragmentation.TransactionDelegate$12
            r3.<init>(r8, r0)
            r1.post(r3)
            r1 = r2
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.doraemon.fragmentation.TransactionDelegate.handleLaunchMode(androidx.fragment.app.FragmentManager, com.xsdk.doraemon.fragmentation.ISupportFragment, com.xsdk.doraemon.fragmentation.ISupportFragment, java.lang.String, int):boolean");
    }

    /* access modifiers changed from: private */
    public void handleNewBundle(ISupportFragment toFragment, ISupportFragment stackToFragment) {
        Bundle argsNewBundle = toFragment.getSupportDelegate().mNewBundle;
        Bundle args = getArguments((Fragment) toFragment);
        if (args.containsKey(FRAGMENTATION_ARG_CONTAINER)) {
            args.remove(FRAGMENTATION_ARG_CONTAINER);
        }
        if (argsNewBundle != null) {
            args.putAll(argsNewBundle);
        }
        stackToFragment.onNewBundle(args);
    }

    private void saveRequestCode(FragmentManager fm, Fragment from, Fragment to, int requestCode) {
        Bundle bundle = getArguments(to);
        ResultRecord resultRecord = new ResultRecord();
        resultRecord.requestCode = requestCode;
        bundle.putParcelable(FRAGMENTATION_ARG_RESULT_RECORD, resultRecord);
        fm.putFragment(bundle, FRAGMENTATION_STATE_SAVE_RESULT, from);
    }

    /* access modifiers changed from: private */
    public void doPopTo(String targetFragmentTag, boolean includeTargetFragment, FragmentManager fm, int popAnim) {
        handleAfterSaveInStateTransactionException(fm, "popTo()");
        if (fm.findFragmentByTag(targetFragmentTag) == null) {
            Log.e(TAG, "Pop failure! Can't find FragmentTag:" + targetFragmentTag + " in the FragmentManager's Stack.");
            return;
        }
        int flag = 0;
        if (includeTargetFragment) {
            flag = 1;
        }
        List<Fragment> willPopFragments = SupportHelper.getWillPopFragments(fm, targetFragmentTag, includeTargetFragment);
        if (willPopFragments.size() > 0) {
            mockPopToAnim(willPopFragments.get(0), targetFragmentTag, fm, flag, willPopFragments, popAnim);
        }
    }

    /* access modifiers changed from: private */
    public void safePopTo(String fragmentTag, FragmentManager fm, int flag, List<Fragment> willPopFragments) {
        this.mSupport.getSupportDelegate().mPopMultipleNoAnim = true;
        FragmentTransaction transaction = fm.beginTransaction().setTransition(8194);
        for (Fragment fragment : willPopFragments) {
            transaction.remove(fragment);
        }
        transaction.commitAllowingStateLoss();
        FragmentationMagician.popBackStackAllowingStateLoss(fm, fragmentTag, flag);
        FragmentationMagician.executePendingTransactionsAllowingStateLoss(fm);
        this.mSupport.getSupportDelegate().mPopMultipleNoAnim = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        r5 = r13.getView();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mockPopToAnim(androidx.fragment.app.Fragment r13, java.lang.String r14, androidx.fragment.app.FragmentManager r15, int r16, java.util.List<androidx.fragment.app.Fragment> r17, int r18) {
        /*
            r12 = this;
            boolean r7 = r13 instanceof com.xsdk.doraemon.fragmentation.ISupportFragment
            if (r7 != 0) goto L_0x000c
            r0 = r16
            r1 = r17
            r12.safePopTo(r14, r15, r0, r1)
        L_0x000b:
            return
        L_0x000c:
            r4 = r13
            com.xsdk.doraemon.fragmentation.ISupportFragment r4 = (com.xsdk.doraemon.fragmentation.ISupportFragment) r4
            com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r7 = r4.getSupportDelegate()
            int r7 = r7.mContainerId
            android.view.ViewGroup r3 = r12.findContainerById(r13, r7)
            if (r3 == 0) goto L_0x000b
            android.view.View r5 = r13.getView()
            if (r5 == 0) goto L_0x000b
            r3.removeViewInLayout(r5)
            android.view.ViewGroup r6 = r12.addMockView(r5, r3)
            r0 = r16
            r1 = r17
            r12.safePopTo(r14, r15, r0, r1)
            r7 = 2147483647(0x7fffffff, float:NaN)
            r0 = r18
            if (r0 != r7) goto L_0x0057
            com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r7 = r4.getSupportDelegate()
            android.view.animation.Animation r2 = r7.getExitAnim()
            if (r2 != 0) goto L_0x0045
            com.xsdk.doraemon.fragmentation.TransactionDelegate$13 r2 = new com.xsdk.doraemon.fragmentation.TransactionDelegate$13
            r2.<init>()
        L_0x0045:
            r5.startAnimation(r2)
            android.os.Handler r7 = r12.mHandler
            com.xsdk.doraemon.fragmentation.TransactionDelegate$15 r8 = new com.xsdk.doraemon.fragmentation.TransactionDelegate$15
            r8.<init>(r6, r5, r3)
            long r10 = r2.getDuration()
            r7.postDelayed(r8, r10)
            goto L_0x000b
        L_0x0057:
            if (r18 != 0) goto L_0x005f
            com.xsdk.doraemon.fragmentation.TransactionDelegate$14 r2 = new com.xsdk.doraemon.fragmentation.TransactionDelegate$14
            r2.<init>()
            goto L_0x0045
        L_0x005f:
            androidx.fragment.app.FragmentActivity r7 = r12.mActivity
            r0 = r18
            android.view.animation.Animation r2 = android.view.animation.AnimationUtils.loadAnimation(r7, r0)
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.doraemon.fragmentation.TransactionDelegate.mockPopToAnim(androidx.fragment.app.Fragment, java.lang.String, androidx.fragment.app.FragmentManager, int, java.util.List, int):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0010, code lost:
        r2 = r6.getView();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mockStartWithPopAnim(com.xsdk.doraemon.fragmentation.ISupportFragment r9, com.xsdk.doraemon.fragmentation.ISupportFragment r10, android.view.animation.Animation r11) {
        /*
            r8 = this;
            r6 = r9
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r0 = r9.getSupportDelegate()
            int r0 = r0.mContainerId
            android.view.ViewGroup r5 = r8.findContainerById(r6, r0)
            if (r5 != 0) goto L_0x0010
        L_0x000f:
            return
        L_0x0010:
            android.view.View r2 = r6.getView()
            if (r2 == 0) goto L_0x000f
            r5.removeViewInLayout(r2)
            android.view.ViewGroup r4 = r8.addMockView(r2, r5)
            com.xsdk.doraemon.fragmentation.SupportFragmentDelegate r7 = r10.getSupportDelegate()
            com.xsdk.doraemon.fragmentation.TransactionDelegate$16 r0 = new com.xsdk.doraemon.fragmentation.TransactionDelegate$16
            r1 = r8
            r3 = r11
            r0.<init>(r2, r3, r4, r5)
            r7.mEnterAnimListener = r0
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.doraemon.fragmentation.TransactionDelegate.mockStartWithPopAnim(com.xsdk.doraemon.fragmentation.ISupportFragment, com.xsdk.doraemon.fragmentation.ISupportFragment, android.view.animation.Animation):void");
    }

    @NonNull
    private ViewGroup addMockView(View fromView, ViewGroup container) {
        ViewGroup mock = new ViewGroup(this.mActivity) {
            /* access modifiers changed from: protected */
            public void onLayout(boolean changed, int l, int t, int r, int b) {
            }
        };
        mock.addView(fromView);
        container.addView(mock);
        return mock;
    }

    private ViewGroup findContainerById(Fragment fragment, int containerId) {
        View container;
        if (fragment.getView() == null) {
            return null;
        }
        Fragment parentFragment = fragment.getParentFragment();
        if (parentFragment == null) {
            container = this.mActivity.findViewById(containerId);
        } else if (parentFragment.getView() != null) {
            container = parentFragment.getView().findViewById(containerId);
        } else {
            container = findContainerById(parentFragment, containerId);
        }
        if (container instanceof ViewGroup) {
            return (ViewGroup) container;
        }
        return null;
    }

    private static <T> void checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
    }

    /* access modifiers changed from: private */
    public void handleAfterSaveInStateTransactionException(FragmentManager fm, String action) {
        if (FragmentationMagician.isStateSaved(fm)) {
            AfterSaveStateTransactionWarning e = new AfterSaveStateTransactionWarning(action);
            if (Fragmentation.getDefault().getHandler() != null) {
                Fragmentation.getDefault().getHandler().onException(e);
            }
        }
    }
}
