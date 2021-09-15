package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.core.util.DebugUtils;
import androidx.core.util.LogWriter;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    final HashMap<String, Fragment> mActive = new HashMap<>();
    final ArrayList<Fragment> mAdded = new ArrayList<>();
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState = 0;
    boolean mDestroyed;
    Runnable mExecCommit = new Runnable() {
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    };
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<>();
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex = 0;
    private FragmentManagerViewModel mNonConfig;
    private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) {
        public void handleOnBackPressed() {
            FragmentManagerImpl.this.handleOnBackPressed();
        }
    };
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    Fragment mParent;
    ArrayList<OpGenerator> mPendingActions;
    ArrayList<StartEnterTransitionListener> mPostponedTransactions;
    @Nullable
    Fragment mPrimaryNav;
    SparseArray<Parcelable> mStateArray = null;
    Bundle mStateBundle = null;
    boolean mStateSaved;
    boolean mStopped;
    ArrayList<Fragment> mTmpAddedFragments;
    ArrayList<Boolean> mTmpIsPop;
    ArrayList<BackStackRecord> mTmpRecords;

    interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    FragmentManagerImpl() {
    }

    private static final class FragmentLifecycleCallbacksHolder {
        final FragmentManager.FragmentLifecycleCallbacks mCallback;
        final boolean mRecursive;

        FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks callback, boolean recursive) {
            this.mCallback = callback;
            this.mRecursive = recursive;
        }
    }

    private void throwException(RuntimeException ex) {
        Log.e(TAG, ex.getMessage());
        Log.e(TAG, "Activity state:");
        PrintWriter pw = new PrintWriter(new LogWriter(TAG));
        if (this.mHost != null) {
            try {
                this.mHost.onDump("  ", (FileDescriptor) null, pw, new String[0]);
            } catch (Exception e) {
                Log.e(TAG, "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", (FileDescriptor) null, pw, new String[0]);
            } catch (Exception e2) {
                Log.e(TAG, "Failed dumping state", e2);
            }
        }
        throw ex;
    }

    @NonNull
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public boolean executePendingTransactions() {
        boolean updates = execPendingActions();
        forcePostponedTransactions();
        return updates;
    }

    private void updateOnBackPressedCallbackEnabled() {
        boolean z = true;
        if (this.mPendingActions == null || this.mPendingActions.isEmpty()) {
            OnBackPressedCallback onBackPressedCallback = this.mOnBackPressedCallback;
            if (getBackStackEntryCount() <= 0 || !isPrimaryNavigation(this.mParent)) {
                z = false;
            }
            onBackPressedCallback.setEnabled(z);
            return;
        }
        this.mOnBackPressedCallback.setEnabled(true);
    }

    /* access modifiers changed from: package-private */
    public boolean isPrimaryNavigation(@Nullable Fragment parent) {
        if (parent == null) {
            return true;
        }
        FragmentManagerImpl parentFragmentManager = parent.mFragmentManager;
        if (parent != parentFragmentManager.getPrimaryNavigationFragment() || !isPrimaryNavigation(parentFragmentManager.mParent)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void handleOnBackPressed() {
        execPendingActions();
        if (this.mOnBackPressedCallback.isEnabled()) {
            popBackStackImmediate();
        } else {
            this.mOnBackPressedDispatcher.onBackPressed();
        }
    }

    public void popBackStack() {
        enqueueAction(new PopBackStackState((String) null, -1, 0), false);
    }

    public boolean popBackStackImmediate() {
        checkStateLoss();
        return popBackStackImmediate((String) null, -1, 0);
    }

    public void popBackStack(@Nullable String name, int flags) {
        enqueueAction(new PopBackStackState(name, -1, flags), false);
    }

    public boolean popBackStackImmediate(@Nullable String name, int flags) {
        checkStateLoss();
        return popBackStackImmediate(name, -1, flags);
    }

    public void popBackStack(int id, int flags) {
        if (id < 0) {
            throw new IllegalArgumentException("Bad id: " + id);
        }
        enqueueAction(new PopBackStackState((String) null, id, flags), false);
    }

    public boolean popBackStackImmediate(int id, int flags) {
        checkStateLoss();
        execPendingActions();
        if (id >= 0) {
            return popBackStackImmediate((String) null, id, flags);
        }
        throw new IllegalArgumentException("Bad id: " + id);
    }

    private boolean popBackStackImmediate(String name, int id, int flags) {
        execPendingActions();
        ensureExecReady(true);
        if (this.mPrimaryNav != null && id < 0 && name == null && this.mPrimaryNav.getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        boolean executePop = popBackStackState(this.mTmpRecords, this.mTmpIsPop, name, id, flags);
        if (executePop) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        burpActive();
        return executePop;
    }

    public int getBackStackEntryCount() {
        if (this.mBackStack != null) {
            return this.mBackStack.size();
        }
        return 0;
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int index) {
        return this.mBackStack.get(index);
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<>();
        }
        this.mBackStackChangeListeners.add(listener);
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener) {
        if (this.mBackStackChangeListeners != null) {
            this.mBackStackChangeListeners.remove(listener);
        }
    }

    public void putFragment(Bundle bundle, String key, Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(key, fragment.mWho);
    }

    @Nullable
    public Fragment getFragment(Bundle bundle, String key) {
        String who = bundle.getString(key);
        if (who == null) {
            return null;
        }
        Fragment f = this.mActive.get(who);
        if (f != null) {
            return f;
        }
        throwException(new IllegalStateException("Fragment no longer exists for key " + key + ": unique id " + who));
        return f;
    }

    public List<Fragment> getFragments() {
        List<Fragment> list;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            list = (List) this.mAdded.clone();
        }
        return list;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ViewModelStore getViewModelStore(@NonNull Fragment f) {
        return this.mNonConfig.getViewModelStore(f);
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public FragmentManagerViewModel getChildNonConfig(@NonNull Fragment f) {
        return this.mNonConfig.getChildNonConfig(f);
    }

    /* access modifiers changed from: package-private */
    public void addRetainedFragment(@NonNull Fragment f) {
        if (isStateSaved()) {
            if (DEBUG) {
                Log.v(TAG, "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.mNonConfig.addRetainedFragment(f) && DEBUG) {
            Log.v(TAG, "Updating retained Fragments: Added " + f);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeRetainedFragment(@NonNull Fragment f) {
        if (isStateSaved()) {
            if (DEBUG) {
                Log.v(TAG, "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.mNonConfig.removeRetainedFragment(f) && DEBUG) {
            Log.v(TAG, "Updating retained Fragments: Removed " + f);
        }
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public List<Fragment> getActiveFragments() {
        return new ArrayList(this.mActive.values());
    }

    /* access modifiers changed from: package-private */
    public int getActiveFragmentCount() {
        return this.mActive.size();
    }

    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment fragment) {
        Bundle result;
        if (fragment.mFragmentManager != this) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0 || (result = saveFragmentBasicState(fragment)) == null) {
            return null;
        }
        return new Fragment.SavedState(result);
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, sb);
        } else {
            DebugUtils.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {
        int N;
        int N2;
        int N3;
        int N4;
        String innerPrefix = prefix + "    ";
        if (!this.mActive.isEmpty()) {
            writer.print(prefix);
            writer.print("Active Fragments in ");
            writer.print(Integer.toHexString(System.identityHashCode(this)));
            writer.println(":");
            for (Fragment f : this.mActive.values()) {
                writer.print(prefix);
                writer.println(f);
                if (f != null) {
                    f.dump(innerPrefix, fd, writer, args);
                }
            }
        }
        int N5 = this.mAdded.size();
        if (N5 > 0) {
            writer.print(prefix);
            writer.println("Added Fragments:");
            for (int i = 0; i < N5; i++) {
                writer.print(prefix);
                writer.print("  #");
                writer.print(i);
                writer.print(": ");
                writer.println(this.mAdded.get(i).toString());
            }
        }
        if (this.mCreatedMenus != null && (N4 = this.mCreatedMenus.size()) > 0) {
            writer.print(prefix);
            writer.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < N4; i2++) {
                writer.print(prefix);
                writer.print("  #");
                writer.print(i2);
                writer.print(": ");
                writer.println(this.mCreatedMenus.get(i2).toString());
            }
        }
        if (this.mBackStack != null && (N3 = this.mBackStack.size()) > 0) {
            writer.print(prefix);
            writer.println("Back Stack:");
            for (int i3 = 0; i3 < N3; i3++) {
                BackStackRecord bs = this.mBackStack.get(i3);
                writer.print(prefix);
                writer.print("  #");
                writer.print(i3);
                writer.print(": ");
                writer.println(bs.toString());
                bs.dump(innerPrefix, writer);
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null && (N2 = this.mBackStackIndices.size()) > 0) {
                writer.print(prefix);
                writer.println("Back Stack Indices:");
                for (int i4 = 0; i4 < N2; i4++) {
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i4);
                    writer.print(": ");
                    writer.println(this.mBackStackIndices.get(i4));
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                writer.print(prefix);
                writer.print("mAvailBackStackIndices: ");
                writer.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        if (this.mPendingActions != null && (N = this.mPendingActions.size()) > 0) {
            writer.print(prefix);
            writer.println("Pending Actions:");
            for (int i5 = 0; i5 < N; i5++) {
                writer.print(prefix);
                writer.print("  #");
                writer.print(i5);
                writer.print(": ");
                writer.println(this.mPendingActions.get(i5));
            }
        }
        writer.print(prefix);
        writer.println("FragmentManager misc state:");
        writer.print(prefix);
        writer.print("  mHost=");
        writer.println(this.mHost);
        writer.print(prefix);
        writer.print("  mContainer=");
        writer.println(this.mContainer);
        if (this.mParent != null) {
            writer.print(prefix);
            writer.print("  mParent=");
            writer.println(this.mParent);
        }
        writer.print(prefix);
        writer.print("  mCurState=");
        writer.print(this.mCurState);
        writer.print(" mStateSaved=");
        writer.print(this.mStateSaved);
        writer.print(" mStopped=");
        writer.print(this.mStopped);
        writer.print(" mDestroyed=");
        writer.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            writer.print(prefix);
            writer.print("  mNeedMenuInvalidate=");
            writer.println(this.mNeedMenuInvalidate);
        }
    }

    static AnimationOrAnimator makeOpenCloseAnimation(float startScale, float endScale, float startAlpha, float endAlpha) {
        AnimationSet set = new AnimationSet(false);
        ScaleAnimation scale = new ScaleAnimation(startScale, endScale, startScale, endScale, 1, 0.5f, 1, 0.5f);
        scale.setInterpolator(DECELERATE_QUINT);
        scale.setDuration(220);
        set.addAnimation(scale);
        AlphaAnimation alpha = new AlphaAnimation(startAlpha, endAlpha);
        alpha.setInterpolator(DECELERATE_CUBIC);
        alpha.setDuration(220);
        set.addAnimation(alpha);
        return new AnimationOrAnimator((Animation) set);
    }

    static AnimationOrAnimator makeFadeAnimation(float start, float end) {
        AlphaAnimation anim = new AlphaAnimation(start, end);
        anim.setInterpolator(DECELERATE_CUBIC);
        anim.setDuration(220);
        return new AnimationOrAnimator((Animation) anim);
    }

    /* access modifiers changed from: package-private */
    public AnimationOrAnimator loadAnimation(Fragment fragment, int transit, boolean enter, int transitionStyle) {
        int styleIndex;
        int nextAnim = fragment.getNextAnim();
        fragment.setNextAnim(0);
        if (fragment.mContainer != null && fragment.mContainer.getLayoutTransition() != null) {
            return null;
        }
        Animation animation = fragment.onCreateAnimation(transit, enter, nextAnim);
        if (animation != null) {
            return new AnimationOrAnimator(animation);
        }
        Animator animator = fragment.onCreateAnimator(transit, enter, nextAnim);
        if (animator != null) {
            return new AnimationOrAnimator(animator);
        }
        if (nextAnim != 0) {
            boolean isAnim = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(nextAnim));
            boolean successfulLoad = false;
            if (isAnim) {
                try {
                    Animation animation2 = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                    if (animation2 != null) {
                        return new AnimationOrAnimator(animation2);
                    }
                    successfulLoad = true;
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException e2) {
                }
            }
            if (!successfulLoad) {
                try {
                    Animator animator2 = AnimatorInflater.loadAnimator(this.mHost.getContext(), nextAnim);
                    if (animator2 != null) {
                        return new AnimationOrAnimator(animator2);
                    }
                } catch (RuntimeException e3) {
                    if (isAnim) {
                        throw e3;
                    }
                    Animation animation3 = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                    if (animation3 != null) {
                        return new AnimationOrAnimator(animation3);
                    }
                }
            }
        }
        if (transit == 0 || (styleIndex = transitToStyleIndex(transit, enter)) < 0) {
            return null;
        }
        switch (styleIndex) {
            case 1:
                return makeOpenCloseAnimation(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return makeOpenCloseAnimation(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return makeOpenCloseAnimation(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return makeOpenCloseAnimation(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return makeFadeAnimation(0.0f, 1.0f);
            case 6:
                return makeFadeAnimation(1.0f, 0.0f);
            default:
                if (transitionStyle == 0 && this.mHost.onHasWindowAnimations()) {
                    transitionStyle = this.mHost.onGetWindowAnimations();
                }
                if (transitionStyle == 0) {
                    return null;
                }
                return null;
        }
    }

    public void performPendingDeferredStart(Fragment f) {
        if (!f.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        f.mDeferStart = false;
        moveToState(f, this.mCurState, 0, 0, false);
    }

    /* access modifiers changed from: package-private */
    public boolean isStateAtLeast(int state) {
        return this.mCurState >= state;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02e8, code lost:
        if (DEBUG == false) goto L_0x0304;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02ea, code lost:
        android.util.Log.v(TAG, "moveto ACTIVITY_CREATED: " + r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0308, code lost:
        if (r20.mFromLayout != false) goto L_0x0413;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x030a, code lost:
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x030f, code lost:
        if (r20.mContainerId == 0) goto L_0x039c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0316, code lost:
        if (r20.mContainerId != -1) goto L_0x033d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0318, code lost:
        throwException(new java.lang.IllegalArgumentException("Cannot create fragment " + r20 + " for a container view with no id"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x033d, code lost:
        r14 = (android.view.ViewGroup) r19.mContainer.onFindViewById(r20.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x034b, code lost:
        if (r14 != null) goto L_0x039c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0351, code lost:
        if (r20.mRestored != false) goto L_0x039c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:?, code lost:
        r16 = r20.getResources().getResourceName(r20.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x04c0, code lost:
        r16 = "unknown";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x04e2, code lost:
        if (r21 >= 1) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x04e8, code lost:
        if (r19.mDestroyed == false) goto L_0x04fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x04ee, code lost:
        if (r20.getAnimatingAway() == null) goto L_0x064c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x04f0, code lost:
        r18 = r20.getAnimatingAway();
        r20.setAnimatingAway((android.view.View) null);
        r18.clearAnimation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0501, code lost:
        if (r20.getAnimatingAway() != null) goto L_0x0509;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0507, code lost:
        if (r20.getAnimator() == null) goto L_0x0661;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0509, code lost:
        r20.setStateAfterAnimating(r21);
        r21 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0541, code lost:
        if (r21 >= 3) goto L_0x056c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0545, code lost:
        if (DEBUG == false) goto L_0x0561;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0547, code lost:
        android.util.Log.v(TAG, "movefrom STARTED: " + r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0561, code lost:
        r20.performStop();
        dispatchOnFragmentStopped(r20, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x056f, code lost:
        if (r21 >= 2) goto L_0x04df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0573, code lost:
        if (DEBUG == false) goto L_0x058f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0575, code lost:
        android.util.Log.v(TAG, "movefrom ACTIVITY_CREATED: " + r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0593, code lost:
        if (r20.mView == null) goto L_0x05aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x059f, code lost:
        if (r19.mHost.onShouldSaveFragmentState(r20) == false) goto L_0x05aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x05a5, code lost:
        if (r20.mSavedViewState != null) goto L_0x05aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x05a7, code lost:
        saveFragmentViewState(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x05aa, code lost:
        r20.performDestroyView();
        dispatchOnFragmentViewDestroyed(r20, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x05b9, code lost:
        if (r20.mView == null) goto L_0x0629;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x05bf, code lost:
        if (r20.mContainer == null) goto L_0x0629;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x05c1, code lost:
        r20.mContainer.endViewTransition(r20.mView);
        r20.mView.clearAnimation();
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x05d8, code lost:
        if (r20.getParentFragment() == null) goto L_0x05e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x05e0, code lost:
        if (r20.getParentFragment().mRemoving != false) goto L_0x0629;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x05e6, code lost:
        if (r19.mCurState <= 0) goto L_0x060e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x05ec, code lost:
        if (r19.mDestroyed != false) goto L_0x060e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x05f6, code lost:
        if (r20.mView.getVisibility() != 0) goto L_0x060e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x05ff, code lost:
        if (r20.mPostponedAlpha < 0.0f) goto L_0x060e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0601, code lost:
        r11 = loadAnimation(r20, r22, false, r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x060e, code lost:
        r20.mPostponedAlpha = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0613, code lost:
        if (r11 == null) goto L_0x061e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0615, code lost:
        animateRemoveFragment(r20, r11, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x061e, code lost:
        r20.mContainer.removeView(r20.mView);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0629, code lost:
        r20.mContainer = null;
        r20.mView = null;
        r20.mViewLifecycleOwner = null;
        r20.mViewLifecycleOwnerLiveData.setValue(null);
        r20.mInnerView = null;
        r20.mInLayout = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0650, code lost:
        if (r20.getAnimator() == null) goto L_0x04fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0652, code lost:
        r12 = r20.getAnimator();
        r20.setAnimator((android.animation.Animator) null);
        r12.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0663, code lost:
        if (DEBUG == false) goto L_0x067f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0665, code lost:
        android.util.Log.v(TAG, "movefrom CREATED: " + r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0683, code lost:
        if (r20.mRemoving == false) goto L_0x06e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0689, code lost:
        if (r20.isInBackStack() != false) goto L_0x06e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x068b, code lost:
        r13 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x068c, code lost:
        if (r13 != false) goto L_0x069a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0698, code lost:
        if (r19.mNonConfig.shouldDestroy(r20) == false) goto L_0x0709;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x06a0, code lost:
        if ((r19.mHost instanceof androidx.lifecycle.ViewModelStoreOwner) == false) goto L_0x06e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x06a2, code lost:
        r17 = r19.mNonConfig.isCleared();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x06aa, code lost:
        if (r13 != false) goto L_0x06ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x06ac, code lost:
        if (r17 == false) goto L_0x06b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x06ae, code lost:
        r19.mNonConfig.clearNonConfigState(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x06b7, code lost:
        r20.performDestroy();
        dispatchOnFragmentDestroyed(r20, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x06c2, code lost:
        r20.performDetach();
        dispatchOnFragmentDetached(r20, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x06cd, code lost:
        if (r24 != false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x06cf, code lost:
        if (r13 != false) goto L_0x06dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x06db, code lost:
        if (r19.mNonConfig.shouldDestroy(r20) == false) goto L_0x070f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x06dd, code lost:
        makeInactive(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x06e2, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x06ee, code lost:
        if ((r19.mHost.getContext() instanceof android.app.Activity) == false) goto L_0x0706;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x06fe, code lost:
        if (((android.app.Activity) r19.mHost.getContext()).isChangingConfigurations() != false) goto L_0x0703;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0700, code lost:
        r17 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x0703, code lost:
        r17 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x0706, code lost:
        r17 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0709, code lost:
        r20.mState = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x070f, code lost:
        r20.mHost = null;
        r20.mParentFragment = null;
        r20.mFragmentManager = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0722, code lost:
        if (r20.mTargetWho == null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0724, code lost:
        r5 = r19.mActive.get(r20.mTargetWho);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0732, code lost:
        if (r5 == null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0738, code lost:
        if (r5.getRetainInstance() == false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x073a, code lost:
        r20.mTarget = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02dc, code lost:
        if (r21 <= 0) goto L_0x02e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02de, code lost:
        ensureInflatedFragmentView(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02e4, code lost:
        if (r21 <= 1) goto L_0x043c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:258:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveToState(androidx.fragment.app.Fragment r20, int r21, int r22, int r23, boolean r24) {
        /*
            r19 = this;
            r0 = r20
            boolean r4 = r0.mAdded
            if (r4 == 0) goto L_0x000c
            r0 = r20
            boolean r4 = r0.mDetached
            if (r4 == 0) goto L_0x0013
        L_0x000c:
            r4 = 1
            r0 = r21
            if (r0 <= r4) goto L_0x0013
            r21 = 1
        L_0x0013:
            r0 = r20
            boolean r4 = r0.mRemoving
            if (r4 == 0) goto L_0x002f
            r0 = r20
            int r4 = r0.mState
            r0 = r21
            if (r0 <= r4) goto L_0x002f
            r0 = r20
            int r4 = r0.mState
            if (r4 != 0) goto L_0x0067
            boolean r4 = r20.isInBackStack()
            if (r4 == 0) goto L_0x0067
            r21 = 1
        L_0x002f:
            r0 = r20
            boolean r4 = r0.mDeferStart
            if (r4 == 0) goto L_0x0043
            r0 = r20
            int r4 = r0.mState
            r6 = 3
            if (r4 >= r6) goto L_0x0043
            r4 = 2
            r0 = r21
            if (r0 <= r4) goto L_0x0043
            r21 = 2
        L_0x0043:
            r0 = r20
            androidx.lifecycle.Lifecycle$State r4 = r0.mMaxState
            androidx.lifecycle.Lifecycle$State r6 = androidx.lifecycle.Lifecycle.State.CREATED
            if (r4 != r6) goto L_0x006e
            r4 = 1
            r0 = r21
            int r21 = java.lang.Math.min(r0, r4)
        L_0x0052:
            r0 = r20
            int r4 = r0.mState
            r0 = r21
            if (r4 > r0) goto L_0x04ce
            r0 = r20
            boolean r4 = r0.mFromLayout
            if (r4 == 0) goto L_0x007d
            r0 = r20
            boolean r4 = r0.mInLayout
            if (r4 != 0) goto L_0x007d
        L_0x0066:
            return
        L_0x0067:
            r0 = r20
            int r0 = r0.mState
            r21 = r0
            goto L_0x002f
        L_0x006e:
            r0 = r20
            androidx.lifecycle.Lifecycle$State r4 = r0.mMaxState
            int r4 = r4.ordinal()
            r0 = r21
            int r21 = java.lang.Math.min(r0, r4)
            goto L_0x0052
        L_0x007d:
            android.view.View r4 = r20.getAnimatingAway()
            if (r4 != 0) goto L_0x0089
            android.animation.Animator r4 = r20.getAnimator()
            if (r4 == 0) goto L_0x00a3
        L_0x0089:
            r4 = 0
            r0 = r20
            r0.setAnimatingAway(r4)
            r4 = 0
            r0 = r20
            r0.setAnimator(r4)
            int r6 = r20.getStateAfterAnimating()
            r7 = 0
            r8 = 0
            r9 = 1
            r4 = r19
            r5 = r20
            r4.moveToState(r5, r6, r7, r8, r9)
        L_0x00a3:
            r0 = r20
            int r4 = r0.mState
            switch(r4) {
                case 0: goto L_0x00ee;
                case 1: goto L_0x02dc;
                case 2: goto L_0x043c;
                case 3: goto L_0x046a;
                default: goto L_0x00aa;
            }
        L_0x00aa:
            r0 = r20
            int r4 = r0.mState
            r0 = r21
            if (r4 == r0) goto L_0x0066
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "moveToState: Fragment state for "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r7 = " not updated inline; expected state "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r21
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r7 = " found "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            int r7 = r0.mState
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.w(r4, r6)
            r0 = r21
            r1 = r20
            r1.mState = r0
            goto L_0x0066
        L_0x00ee:
            if (r21 <= 0) goto L_0x02dc
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x010e
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "moveto CREATED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x010e:
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            if (r4 == 0) goto L_0x0187
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r6 = r0.mHost
            android.content.Context r6 = r6.getContext()
            java.lang.ClassLoader r6 = r6.getClassLoader()
            r4.setClassLoader(r6)
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r6 = "android:view_state"
            android.util.SparseArray r4 = r4.getSparseParcelableArray(r6)
            r0 = r20
            r0.mSavedViewState = r4
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r6 = "android:target_state"
            r0 = r19
            androidx.fragment.app.Fragment r5 = r0.getFragment(r4, r6)
            if (r5 == 0) goto L_0x01f0
            java.lang.String r4 = r5.mWho
        L_0x0145:
            r0 = r20
            r0.mTargetWho = r4
            r0 = r20
            java.lang.String r4 = r0.mTargetWho
            if (r4 == 0) goto L_0x015e
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r6 = "android:target_req_state"
            r7 = 0
            int r4 = r4.getInt(r6, r7)
            r0 = r20
            r0.mTargetRequestCode = r4
        L_0x015e:
            r0 = r20
            java.lang.Boolean r4 = r0.mSavedUserVisibleHint
            if (r4 == 0) goto L_0x01f3
            r0 = r20
            java.lang.Boolean r4 = r0.mSavedUserVisibleHint
            boolean r4 = r4.booleanValue()
            r0 = r20
            r0.mUserVisibleHint = r4
            r4 = 0
            r0 = r20
            r0.mSavedUserVisibleHint = r4
        L_0x0175:
            r0 = r20
            boolean r4 = r0.mUserVisibleHint
            if (r4 != 0) goto L_0x0187
            r4 = 1
            r0 = r20
            r0.mDeferStart = r4
            r4 = 2
            r0 = r21
            if (r0 <= r4) goto L_0x0187
            r21 = 2
        L_0x0187:
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            r0 = r20
            r0.mHost = r4
            r0 = r19
            androidx.fragment.app.Fragment r4 = r0.mParent
            r0 = r20
            r0.mParentFragment = r4
            r0 = r19
            androidx.fragment.app.Fragment r4 = r0.mParent
            if (r4 == 0) goto L_0x0204
            r0 = r19
            androidx.fragment.app.Fragment r4 = r0.mParent
            androidx.fragment.app.FragmentManagerImpl r4 = r4.mChildFragmentManager
        L_0x01a3:
            r0 = r20
            r0.mFragmentManager = r4
            r0 = r20
            androidx.fragment.app.Fragment r4 = r0.mTarget
            if (r4 == 0) goto L_0x0230
            r0 = r19
            java.util.HashMap<java.lang.String, androidx.fragment.app.Fragment> r4 = r0.mActive
            r0 = r20
            androidx.fragment.app.Fragment r6 = r0.mTarget
            java.lang.String r6 = r6.mWho
            java.lang.Object r4 = r4.get(r6)
            r0 = r20
            androidx.fragment.app.Fragment r6 = r0.mTarget
            if (r4 == r6) goto L_0x020b
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Fragment "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r7 = " declared target fragment "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            androidx.fragment.app.Fragment r7 = r0.mTarget
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " that does not belong to this FragmentManager!"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.<init>(r6)
            throw r4
        L_0x01f0:
            r4 = 0
            goto L_0x0145
        L_0x01f3:
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r6 = "android:user_visible_hint"
            r7 = 1
            boolean r4 = r4.getBoolean(r6, r7)
            r0 = r20
            r0.mUserVisibleHint = r4
            goto L_0x0175
        L_0x0204:
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            androidx.fragment.app.FragmentManagerImpl r4 = r4.mFragmentManager
            goto L_0x01a3
        L_0x020b:
            r0 = r20
            androidx.fragment.app.Fragment r4 = r0.mTarget
            int r4 = r4.mState
            r6 = 1
            if (r4 >= r6) goto L_0x0221
            r0 = r20
            androidx.fragment.app.Fragment r5 = r0.mTarget
            r6 = 1
            r7 = 0
            r8 = 0
            r9 = 1
            r4 = r19
            r4.moveToState(r5, r6, r7, r8, r9)
        L_0x0221:
            r0 = r20
            androidx.fragment.app.Fragment r4 = r0.mTarget
            java.lang.String r4 = r4.mWho
            r0 = r20
            r0.mTargetWho = r4
            r4 = 0
            r0 = r20
            r0.mTarget = r4
        L_0x0230:
            r0 = r20
            java.lang.String r4 = r0.mTargetWho
            if (r4 == 0) goto L_0x0283
            r0 = r19
            java.util.HashMap<java.lang.String, androidx.fragment.app.Fragment> r4 = r0.mActive
            r0 = r20
            java.lang.String r6 = r0.mTargetWho
            java.lang.Object r5 = r4.get(r6)
            androidx.fragment.app.Fragment r5 = (androidx.fragment.app.Fragment) r5
            if (r5 != 0) goto L_0x0275
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Fragment "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r7 = " declared target fragment "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.String r7 = r0.mTargetWho
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " that does not belong to this FragmentManager!"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.<init>(r6)
            throw r4
        L_0x0275:
            int r4 = r5.mState
            r6 = 1
            if (r4 >= r6) goto L_0x0283
            r6 = 1
            r7 = 0
            r8 = 0
            r9 = 1
            r4 = r19
            r4.moveToState(r5, r6, r7, r8, r9)
        L_0x0283:
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            android.content.Context r4 = r4.getContext()
            r6 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentPreAttached(r1, r4, r6)
            r20.performAttach()
            r0 = r20
            androidx.fragment.app.Fragment r4 = r0.mParentFragment
            if (r4 != 0) goto L_0x04a4
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            r0 = r20
            r4.onAttachFragment(r0)
        L_0x02a5:
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            android.content.Context r4 = r4.getContext()
            r6 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentAttached(r1, r4, r6)
            r0 = r20
            boolean r4 = r0.mIsCreated
            if (r4 != 0) goto L_0x04af
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r6 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentPreCreated(r1, r4, r6)
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r20
            r0.performCreate(r4)
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r6 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentCreated(r1, r4, r6)
        L_0x02dc:
            if (r21 <= 0) goto L_0x02e1
            r19.ensureInflatedFragmentView(r20)
        L_0x02e1:
            r4 = 1
            r0 = r21
            if (r0 <= r4) goto L_0x043c
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x0304
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "moveto ACTIVITY_CREATED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x0304:
            r0 = r20
            boolean r4 = r0.mFromLayout
            if (r4 != 0) goto L_0x0413
            r14 = 0
            r0 = r20
            int r4 = r0.mContainerId
            if (r4 == 0) goto L_0x039c
            r0 = r20
            int r4 = r0.mContainerId
            r6 = -1
            if (r4 != r6) goto L_0x033d
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Cannot create fragment "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r7 = " for a container view with no id"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.<init>(r6)
            r0 = r19
            r0.throwException(r4)
        L_0x033d:
            r0 = r19
            androidx.fragment.app.FragmentContainer r4 = r0.mContainer
            r0 = r20
            int r6 = r0.mContainerId
            android.view.View r14 = r4.onFindViewById(r6)
            android.view.ViewGroup r14 = (android.view.ViewGroup) r14
            if (r14 != 0) goto L_0x039c
            r0 = r20
            boolean r4 = r0.mRestored
            if (r4 != 0) goto L_0x039c
            android.content.res.Resources r4 = r20.getResources()     // Catch:{ NotFoundException -> 0x04bf }
            r0 = r20
            int r6 = r0.mContainerId     // Catch:{ NotFoundException -> 0x04bf }
            java.lang.String r16 = r4.getResourceName(r6)     // Catch:{ NotFoundException -> 0x04bf }
        L_0x035f:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "No view found for id 0x"
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            int r7 = r0.mContainerId
            java.lang.String r7 = java.lang.Integer.toHexString(r7)
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " ("
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r16
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r7 = ") for fragment "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            r4.<init>(r6)
            r0 = r19
            r0.throwException(r4)
        L_0x039c:
            r0 = r20
            r0.mContainer = r14
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r20
            android.view.LayoutInflater r4 = r0.performGetLayoutInflater(r4)
            r0 = r20
            android.os.Bundle r6 = r0.mSavedFragmentState
            r0 = r20
            r0.performCreateView(r4, r14, r6)
            r0 = r20
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x04c7
            r0 = r20
            android.view.View r4 = r0.mView
            r0 = r20
            r0.mInnerView = r4
            r0 = r20
            android.view.View r4 = r0.mView
            r6 = 0
            r4.setSaveFromParentEnabled(r6)
            if (r14 == 0) goto L_0x03d2
            r0 = r20
            android.view.View r4 = r0.mView
            r14.addView(r4)
        L_0x03d2:
            r0 = r20
            boolean r4 = r0.mHidden
            if (r4 == 0) goto L_0x03e1
            r0 = r20
            android.view.View r4 = r0.mView
            r6 = 8
            r4.setVisibility(r6)
        L_0x03e1:
            r0 = r20
            android.view.View r4 = r0.mView
            r0 = r20
            android.os.Bundle r6 = r0.mSavedFragmentState
            r0 = r20
            r0.onViewCreated(r4, r6)
            r0 = r20
            android.view.View r4 = r0.mView
            r0 = r20
            android.os.Bundle r6 = r0.mSavedFragmentState
            r7 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentViewCreated(r1, r4, r6, r7)
            r0 = r20
            android.view.View r4 = r0.mView
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x04c4
            r0 = r20
            android.view.ViewGroup r4 = r0.mContainer
            if (r4 == 0) goto L_0x04c4
            r4 = 1
        L_0x040f:
            r0 = r20
            r0.mIsNewlyAdded = r4
        L_0x0413:
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r20
            r0.performActivityCreated(r4)
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r6 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentActivityCreated(r1, r4, r6)
            r0 = r20
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x0437
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r20
            r0.restoreViewState(r4)
        L_0x0437:
            r4 = 0
            r0 = r20
            r0.mSavedFragmentState = r4
        L_0x043c:
            r4 = 2
            r0 = r21
            if (r0 <= r4) goto L_0x046a
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x045f
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "moveto STARTED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x045f:
            r20.performStart()
            r4 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentStarted(r1, r4)
        L_0x046a:
            r4 = 3
            r0 = r21
            if (r0 <= r4) goto L_0x00aa
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x048d
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "moveto RESUMED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x048d:
            r20.performResume()
            r4 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentResumed(r1, r4)
            r4 = 0
            r0 = r20
            r0.mSavedFragmentState = r4
            r4 = 0
            r0 = r20
            r0.mSavedViewState = r4
            goto L_0x00aa
        L_0x04a4:
            r0 = r20
            androidx.fragment.app.Fragment r4 = r0.mParentFragment
            r0 = r20
            r4.onAttachFragment(r0)
            goto L_0x02a5
        L_0x04af:
            r0 = r20
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r20
            r0.restoreChildFragmentState(r4)
            r4 = 1
            r0 = r20
            r0.mState = r4
            goto L_0x02dc
        L_0x04bf:
            r15 = move-exception
            java.lang.String r16 = "unknown"
            goto L_0x035f
        L_0x04c4:
            r4 = 0
            goto L_0x040f
        L_0x04c7:
            r4 = 0
            r0 = r20
            r0.mInnerView = r4
            goto L_0x0413
        L_0x04ce:
            r0 = r20
            int r4 = r0.mState
            r0 = r21
            if (r4 <= r0) goto L_0x00aa
            r0 = r20
            int r4 = r0.mState
            switch(r4) {
                case 1: goto L_0x04df;
                case 2: goto L_0x056c;
                case 3: goto L_0x053e;
                case 4: goto L_0x0510;
                default: goto L_0x04dd;
            }
        L_0x04dd:
            goto L_0x00aa
        L_0x04df:
            r4 = 1
            r0 = r21
            if (r0 >= r4) goto L_0x00aa
            r0 = r19
            boolean r4 = r0.mDestroyed
            if (r4 == 0) goto L_0x04fd
            android.view.View r4 = r20.getAnimatingAway()
            if (r4 == 0) goto L_0x064c
            android.view.View r18 = r20.getAnimatingAway()
            r4 = 0
            r0 = r20
            r0.setAnimatingAway(r4)
            r18.clearAnimation()
        L_0x04fd:
            android.view.View r4 = r20.getAnimatingAway()
            if (r4 != 0) goto L_0x0509
            android.animation.Animator r4 = r20.getAnimator()
            if (r4 == 0) goto L_0x0661
        L_0x0509:
            r20.setStateAfterAnimating(r21)
            r21 = 1
            goto L_0x00aa
        L_0x0510:
            r4 = 4
            r0 = r21
            if (r0 >= r4) goto L_0x053e
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x0533
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "movefrom RESUMED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x0533:
            r20.performPause()
            r4 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentPaused(r1, r4)
        L_0x053e:
            r4 = 3
            r0 = r21
            if (r0 >= r4) goto L_0x056c
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x0561
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "movefrom STARTED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x0561:
            r20.performStop()
            r4 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentStopped(r1, r4)
        L_0x056c:
            r4 = 2
            r0 = r21
            if (r0 >= r4) goto L_0x04df
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x058f
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "movefrom ACTIVITY_CREATED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x058f:
            r0 = r20
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x05aa
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            r0 = r20
            boolean r4 = r4.onShouldSaveFragmentState(r0)
            if (r4 == 0) goto L_0x05aa
            r0 = r20
            android.util.SparseArray<android.os.Parcelable> r4 = r0.mSavedViewState
            if (r4 != 0) goto L_0x05aa
            r19.saveFragmentViewState(r20)
        L_0x05aa:
            r20.performDestroyView()
            r4 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentViewDestroyed(r1, r4)
            r0 = r20
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x0629
            r0 = r20
            android.view.ViewGroup r4 = r0.mContainer
            if (r4 == 0) goto L_0x0629
            r0 = r20
            android.view.ViewGroup r4 = r0.mContainer
            r0 = r20
            android.view.View r6 = r0.mView
            r4.endViewTransition(r6)
            r0 = r20
            android.view.View r4 = r0.mView
            r4.clearAnimation()
            r11 = 0
            androidx.fragment.app.Fragment r4 = r20.getParentFragment()
            if (r4 == 0) goto L_0x05e2
            androidx.fragment.app.Fragment r4 = r20.getParentFragment()
            boolean r4 = r4.mRemoving
            if (r4 != 0) goto L_0x0629
        L_0x05e2:
            r0 = r19
            int r4 = r0.mCurState
            if (r4 <= 0) goto L_0x060e
            r0 = r19
            boolean r4 = r0.mDestroyed
            if (r4 != 0) goto L_0x060e
            r0 = r20
            android.view.View r4 = r0.mView
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x060e
            r0 = r20
            float r4 = r0.mPostponedAlpha
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 < 0) goto L_0x060e
            r4 = 0
            r0 = r19
            r1 = r20
            r2 = r22
            r3 = r23
            androidx.fragment.app.FragmentManagerImpl$AnimationOrAnimator r11 = r0.loadAnimation(r1, r2, r4, r3)
        L_0x060e:
            r4 = 0
            r0 = r20
            r0.mPostponedAlpha = r4
            if (r11 == 0) goto L_0x061e
            r0 = r19
            r1 = r20
            r2 = r21
            r0.animateRemoveFragment(r1, r11, r2)
        L_0x061e:
            r0 = r20
            android.view.ViewGroup r4 = r0.mContainer
            r0 = r20
            android.view.View r6 = r0.mView
            r4.removeView(r6)
        L_0x0629:
            r4 = 0
            r0 = r20
            r0.mContainer = r4
            r4 = 0
            r0 = r20
            r0.mView = r4
            r4 = 0
            r0 = r20
            r0.mViewLifecycleOwner = r4
            r0 = r20
            androidx.lifecycle.MutableLiveData<androidx.lifecycle.LifecycleOwner> r4 = r0.mViewLifecycleOwnerLiveData
            r6 = 0
            r4.setValue(r6)
            r4 = 0
            r0 = r20
            r0.mInnerView = r4
            r4 = 0
            r0 = r20
            r0.mInLayout = r4
            goto L_0x04df
        L_0x064c:
            android.animation.Animator r4 = r20.getAnimator()
            if (r4 == 0) goto L_0x04fd
            android.animation.Animator r12 = r20.getAnimator()
            r4 = 0
            r0 = r20
            r0.setAnimator(r4)
            r12.cancel()
            goto L_0x04fd
        L_0x0661:
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x067f
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "movefrom CREATED: "
            java.lang.StringBuilder r6 = r6.append(r7)
            r0 = r20
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.v(r4, r6)
        L_0x067f:
            r0 = r20
            boolean r4 = r0.mRemoving
            if (r4 == 0) goto L_0x06e2
            boolean r4 = r20.isInBackStack()
            if (r4 != 0) goto L_0x06e2
            r13 = 1
        L_0x068c:
            if (r13 != 0) goto L_0x069a
            r0 = r19
            androidx.fragment.app.FragmentManagerViewModel r4 = r0.mNonConfig
            r0 = r20
            boolean r4 = r4.shouldDestroy(r0)
            if (r4 == 0) goto L_0x0709
        L_0x069a:
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            boolean r4 = r4 instanceof androidx.lifecycle.ViewModelStoreOwner
            if (r4 == 0) goto L_0x06e4
            r0 = r19
            androidx.fragment.app.FragmentManagerViewModel r4 = r0.mNonConfig
            boolean r17 = r4.isCleared()
        L_0x06aa:
            if (r13 != 0) goto L_0x06ae
            if (r17 == 0) goto L_0x06b7
        L_0x06ae:
            r0 = r19
            androidx.fragment.app.FragmentManagerViewModel r4 = r0.mNonConfig
            r0 = r20
            r4.clearNonConfigState(r0)
        L_0x06b7:
            r20.performDestroy()
            r4 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentDestroyed(r1, r4)
        L_0x06c2:
            r20.performDetach()
            r4 = 0
            r0 = r19
            r1 = r20
            r0.dispatchOnFragmentDetached(r1, r4)
            if (r24 != 0) goto L_0x00aa
            if (r13 != 0) goto L_0x06dd
            r0 = r19
            androidx.fragment.app.FragmentManagerViewModel r4 = r0.mNonConfig
            r0 = r20
            boolean r4 = r4.shouldDestroy(r0)
            if (r4 == 0) goto L_0x070f
        L_0x06dd:
            r19.makeInactive(r20)
            goto L_0x00aa
        L_0x06e2:
            r13 = 0
            goto L_0x068c
        L_0x06e4:
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            android.content.Context r4 = r4.getContext()
            boolean r4 = r4 instanceof android.app.Activity
            if (r4 == 0) goto L_0x0706
            r0 = r19
            androidx.fragment.app.FragmentHostCallback r4 = r0.mHost
            android.content.Context r10 = r4.getContext()
            android.app.Activity r10 = (android.app.Activity) r10
            boolean r4 = r10.isChangingConfigurations()
            if (r4 != 0) goto L_0x0703
            r17 = 1
        L_0x0702:
            goto L_0x06aa
        L_0x0703:
            r17 = 0
            goto L_0x0702
        L_0x0706:
            r17 = 1
            goto L_0x06aa
        L_0x0709:
            r4 = 0
            r0 = r20
            r0.mState = r4
            goto L_0x06c2
        L_0x070f:
            r4 = 0
            r0 = r20
            r0.mHost = r4
            r4 = 0
            r0 = r20
            r0.mParentFragment = r4
            r4 = 0
            r0 = r20
            r0.mFragmentManager = r4
            r0 = r20
            java.lang.String r4 = r0.mTargetWho
            if (r4 == 0) goto L_0x00aa
            r0 = r19
            java.util.HashMap<java.lang.String, androidx.fragment.app.Fragment> r4 = r0.mActive
            r0 = r20
            java.lang.String r6 = r0.mTargetWho
            java.lang.Object r5 = r4.get(r6)
            androidx.fragment.app.Fragment r5 = (androidx.fragment.app.Fragment) r5
            if (r5 == 0) goto L_0x00aa
            boolean r4 = r5.getRetainInstance()
            if (r4 == 0) goto L_0x00aa
            r0 = r20
            r0.mTarget = r5
            goto L_0x00aa
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManagerImpl.moveToState(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    private void animateRemoveFragment(@NonNull final Fragment fragment, @NonNull AnimationOrAnimator anim, int newState) {
        final View viewToAnimate = fragment.mView;
        final ViewGroup container = fragment.mContainer;
        container.startViewTransition(viewToAnimate);
        fragment.setStateAfterAnimating(newState);
        if (anim.animation != null) {
            Animation animation = new EndViewTransitionAnimation(anim.animation, container, viewToAnimate);
            fragment.setAnimatingAway(fragment.mView);
            animation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    container.post(new Runnable() {
                        public void run() {
                            if (fragment.getAnimatingAway() != null) {
                                fragment.setAnimatingAway((View) null);
                                FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                            }
                        }
                    });
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            fragment.mView.startAnimation(animation);
            return;
        }
        Animator animator = anim.animator;
        fragment.setAnimator(anim.animator);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                container.endViewTransition(viewToAnimate);
                Animator animator = fragment.getAnimator();
                fragment.setAnimator((Animator) null);
                if (animator != null && container.indexOfChild(viewToAnimate) < 0) {
                    FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        });
        animator.setTarget(fragment.mView);
        animator.start();
    }

    /* access modifiers changed from: package-private */
    public void moveToState(Fragment f) {
        moveToState(f, this.mCurState, 0, 0, false);
    }

    /* access modifiers changed from: package-private */
    public void ensureInflatedFragmentView(Fragment f) {
        if (f.mFromLayout && !f.mPerformedCreateView) {
            f.performCreateView(f.performGetLayoutInflater(f.mSavedFragmentState), (ViewGroup) null, f.mSavedFragmentState);
            if (f.mView != null) {
                f.mInnerView = f.mView;
                f.mView.setSaveFromParentEnabled(false);
                if (f.mHidden) {
                    f.mView.setVisibility(8);
                }
                f.onViewCreated(f.mView, f.mSavedFragmentState);
                dispatchOnFragmentViewCreated(f, f.mView, f.mSavedFragmentState, false);
                return;
            }
            f.mInnerView = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void completeShowHideFragment(final Fragment fragment) {
        int visibility;
        if (fragment.mView != null) {
            AnimationOrAnimator anim = loadAnimation(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (anim == null || anim.animator == null) {
                if (anim != null) {
                    fragment.mView.startAnimation(anim.animation);
                    anim.animation.start();
                }
                if (!fragment.mHidden || fragment.isHideReplaced()) {
                    visibility = 0;
                } else {
                    visibility = 8;
                }
                fragment.mView.setVisibility(visibility);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                anim.animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    final ViewGroup container = fragment.mContainer;
                    final View animatingView = fragment.mView;
                    container.startViewTransition(animatingView);
                    anim.animator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            container.endViewTransition(animatingView);
                            animation.removeListener(this);
                            if (fragment.mView != null && fragment.mHidden) {
                                fragment.mView.setVisibility(8);
                            }
                        }
                    });
                }
                anim.animator.start();
            }
        }
        if (fragment.mAdded && isMenuAvailable(fragment)) {
            this.mNeedMenuInvalidate = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006a, code lost:
        r10 = r8.mView;
        r7 = r15.mContainer;
        r9 = r7.indexOfChild(r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveFragmentToExpectedState(androidx.fragment.app.Fragment r15) {
        /*
            r14 = this;
            r13 = 1
            r12 = 0
            r5 = 0
            if (r15 != 0) goto L_0x0006
        L_0x0005:
            return
        L_0x0006:
            java.util.HashMap<java.lang.String, androidx.fragment.app.Fragment> r0 = r14.mActive
            java.lang.String r1 = r15.mWho
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x0043
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0005
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Ignoring moving "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r15)
            java.lang.String r3 = " to state "
            java.lang.StringBuilder r1 = r1.append(r3)
            int r3 = r14.mCurState
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r3 = "since it is not added to "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r14)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
            goto L_0x0005
        L_0x0043:
            int r2 = r14.mCurState
            boolean r0 = r15.mRemoving
            if (r0 == 0) goto L_0x0053
            boolean r0 = r15.isInBackStack()
            if (r0 == 0) goto L_0x00bd
            int r2 = java.lang.Math.min(r2, r13)
        L_0x0053:
            int r3 = r15.getNextTransition()
            int r4 = r15.getNextTransitionStyle()
            r0 = r14
            r1 = r15
            r0.moveToState(r1, r2, r3, r4, r5)
            android.view.View r0 = r15.mView
            if (r0 == 0) goto L_0x00b4
            androidx.fragment.app.Fragment r8 = r14.findFragmentUnder(r15)
            if (r8 == 0) goto L_0x0082
            android.view.View r10 = r8.mView
            android.view.ViewGroup r7 = r15.mContainer
            int r9 = r7.indexOfChild(r10)
            android.view.View r0 = r15.mView
            int r11 = r7.indexOfChild(r0)
            if (r11 >= r9) goto L_0x0082
            r7.removeViewAt(r11)
            android.view.View r0 = r15.mView
            r7.addView(r0, r9)
        L_0x0082:
            boolean r0 = r15.mIsNewlyAdded
            if (r0 == 0) goto L_0x00b4
            android.view.ViewGroup r0 = r15.mContainer
            if (r0 == 0) goto L_0x00b4
            float r0 = r15.mPostponedAlpha
            int r0 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r0 <= 0) goto L_0x0097
            android.view.View r0 = r15.mView
            float r1 = r15.mPostponedAlpha
            r0.setAlpha(r1)
        L_0x0097:
            r15.mPostponedAlpha = r12
            r15.mIsNewlyAdded = r5
            int r0 = r15.getNextTransition()
            int r1 = r15.getNextTransitionStyle()
            androidx.fragment.app.FragmentManagerImpl$AnimationOrAnimator r6 = r14.loadAnimation(r15, r0, r13, r1)
            if (r6 == 0) goto L_0x00b4
            android.view.animation.Animation r0 = r6.animation
            if (r0 == 0) goto L_0x00c2
            android.view.View r0 = r15.mView
            android.view.animation.Animation r1 = r6.animation
            r0.startAnimation(r1)
        L_0x00b4:
            boolean r0 = r15.mHiddenChanged
            if (r0 == 0) goto L_0x0005
            r14.completeShowHideFragment(r15)
            goto L_0x0005
        L_0x00bd:
            int r2 = java.lang.Math.min(r2, r5)
            goto L_0x0053
        L_0x00c2:
            android.animation.Animator r0 = r6.animator
            android.view.View r1 = r15.mView
            r0.setTarget(r1)
            android.animation.Animator r0 = r6.animator
            r0.start()
            goto L_0x00b4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManagerImpl.moveFragmentToExpectedState(androidx.fragment.app.Fragment):void");
    }

    /* access modifiers changed from: package-private */
    public void moveToState(int newState, boolean always) {
        if (this.mHost == null && newState != 0) {
            throw new IllegalStateException("No activity");
        } else if (always || newState != this.mCurState) {
            this.mCurState = newState;
            int numAdded = this.mAdded.size();
            for (int i = 0; i < numAdded; i++) {
                moveFragmentToExpectedState(this.mAdded.get(i));
            }
            for (Fragment f : this.mActive.values()) {
                if (f != null && ((f.mRemoving || f.mDetached) && !f.mIsNewlyAdded)) {
                    moveFragmentToExpectedState(f);
                }
            }
            startPendingDeferredFragments();
            if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 4) {
                this.mHost.onSupportInvalidateOptionsMenu();
                this.mNeedMenuInvalidate = false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void startPendingDeferredFragments() {
        for (Fragment f : this.mActive.values()) {
            if (f != null) {
                performPendingDeferredStart(f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeActive(Fragment f) {
        if (this.mActive.get(f.mWho) == null) {
            this.mActive.put(f.mWho, f);
            if (f.mRetainInstanceChangedWhileDetached) {
                if (f.mRetainInstance) {
                    addRetainedFragment(f);
                } else {
                    removeRetainedFragment(f);
                }
                f.mRetainInstanceChangedWhileDetached = false;
            }
            if (DEBUG) {
                Log.v(TAG, "Added fragment to active set " + f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeInactive(Fragment f) {
        if (this.mActive.get(f.mWho) != null) {
            if (DEBUG) {
                Log.v(TAG, "Removed fragment from active set " + f);
            }
            for (Fragment fragment : this.mActive.values()) {
                if (fragment != null && f.mWho.equals(fragment.mTargetWho)) {
                    fragment.mTarget = f;
                    fragment.mTargetWho = null;
                }
            }
            this.mActive.put(f.mWho, (Object) null);
            removeRetainedFragment(f);
            if (f.mTargetWho != null) {
                f.mTarget = this.mActive.get(f.mTargetWho);
            }
            f.initState();
        }
    }

    public void addFragment(Fragment fragment, boolean moveToStateNow) {
        if (DEBUG) {
            Log.v(TAG, "add: " + fragment);
        }
        makeActive(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.mAdded.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.mAdded) {
            this.mAdded.add(fragment);
        }
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (isMenuAvailable(fragment)) {
            this.mNeedMenuInvalidate = true;
        }
        if (moveToStateNow) {
            moveToState(fragment);
        }
    }

    public void removeFragment(Fragment fragment) {
        boolean inactive;
        if (DEBUG) {
            Log.v(TAG, "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        if (!fragment.isInBackStack()) {
            inactive = true;
        } else {
            inactive = false;
        }
        if (!fragment.mDetached || inactive) {
            synchronized (this.mAdded) {
                this.mAdded.remove(fragment);
            }
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void hideFragment(Fragment fragment) {
        boolean z = true;
        if (DEBUG) {
            Log.v(TAG, "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mHiddenChanged) {
                z = false;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public void showFragment(Fragment fragment) {
        boolean z = false;
        if (DEBUG) {
            Log.v(TAG, "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (!fragment.mHiddenChanged) {
                z = true;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public void detachFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (DEBUG) {
                    Log.v(TAG, "remove from detach: " + fragment);
                }
                synchronized (this.mAdded) {
                    this.mAdded.remove(fragment);
                }
                if (isMenuAvailable(fragment)) {
                    this.mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public void attachFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.mAdded.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (DEBUG) {
                Log.v(TAG, "add from attach: " + fragment);
            }
            synchronized (this.mAdded) {
                this.mAdded.add(fragment);
            }
            fragment.mAdded = true;
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
    }

    @Nullable
    public Fragment findFragmentById(int id) {
        for (int i = this.mAdded.size() - 1; i >= 0; i--) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.mFragmentId == id) {
                return f;
            }
        }
        for (Fragment f2 : this.mActive.values()) {
            if (f2 != null && f2.mFragmentId == id) {
                return f2;
            }
        }
        return null;
    }

    @Nullable
    public Fragment findFragmentByTag(@Nullable String tag) {
        if (tag != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = this.mAdded.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        if (tag != null) {
            for (Fragment f2 : this.mActive.values()) {
                if (f2 != null && tag.equals(f2.mTag)) {
                    return f2;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(@NonNull String who) {
        Fragment f;
        for (Fragment f2 : this.mActive.values()) {
            if (f2 != null && (f = f2.findFragmentByWho(who)) != null) {
                return f;
            }
        }
        return null;
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    public void enqueueAction(OpGenerator action, boolean allowStateLoss) {
        if (!allowStateLoss) {
            checkStateLoss();
        }
        synchronized (this) {
            if (!this.mDestroyed && this.mHost != null) {
                if (this.mPendingActions == null) {
                    this.mPendingActions = new ArrayList<>();
                }
                this.mPendingActions.add(action);
                scheduleCommit();
            } else if (!allowStateLoss) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleCommit() {
        boolean postponeReady;
        boolean pendingReady = true;
        synchronized (this) {
            if (this.mPostponedTransactions == null || this.mPostponedTransactions.isEmpty()) {
                postponeReady = false;
            } else {
                postponeReady = true;
            }
            if (this.mPendingActions == null || this.mPendingActions.size() != 1) {
                pendingReady = false;
            }
            if (postponeReady || pendingReady) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
                updateOnBackPressedCallbackEnabled();
            }
        }
    }

    public int allocBackStackIndex(BackStackRecord bse) {
        synchronized (this) {
            if (this.mAvailBackStackIndices == null || this.mAvailBackStackIndices.size() <= 0) {
                if (this.mBackStackIndices == null) {
                    this.mBackStackIndices = new ArrayList<>();
                }
                int index = this.mBackStackIndices.size();
                if (DEBUG) {
                    Log.v(TAG, "Setting back stack index " + index + " to " + bse);
                }
                this.mBackStackIndices.add(bse);
                int i = index;
                return index;
            }
            int index2 = this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1).intValue();
            if (DEBUG) {
                Log.v(TAG, "Adding back stack index " + index2 + " with " + bse);
            }
            this.mBackStackIndices.set(index2, bse);
            int i2 = index2;
            return index2;
        }
    }

    public void setBackStackIndex(int index, BackStackRecord bse) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int N = this.mBackStackIndices.size();
            if (index < N) {
                if (DEBUG) {
                    Log.v(TAG, "Setting back stack index " + index + " to " + bse);
                }
                this.mBackStackIndices.set(index, bse);
            } else {
                while (N < index) {
                    this.mBackStackIndices.add((Object) null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList<>();
                    }
                    if (DEBUG) {
                        Log.v(TAG, "Adding available back stack index " + N);
                    }
                    this.mAvailBackStackIndices.add(Integer.valueOf(N));
                    N++;
                }
                if (DEBUG) {
                    Log.v(TAG, "Adding back stack index " + index + " with " + bse);
                }
                this.mBackStackIndices.add(bse);
            }
        }
    }

    public void freeBackStackIndex(int index) {
        synchronized (this) {
            this.mBackStackIndices.set(index, (Object) null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList<>();
            }
            if (DEBUG) {
                Log.v(TAG, "Freeing back stack index " + index);
            }
            this.mAvailBackStackIndices.add(Integer.valueOf(index));
        }
    }

    private void ensureExecReady(boolean allowStateLoss) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.mHost == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        } else if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else {
            if (!allowStateLoss) {
                checkStateLoss();
            }
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList<>();
                this.mTmpIsPop = new ArrayList<>();
            }
            this.mExecutingActions = true;
            try {
                executePostponedTransaction((ArrayList<BackStackRecord>) null, (ArrayList<Boolean>) null);
            } finally {
                this.mExecutingActions = false;
            }
        }
    }

    public void execSingleAction(OpGenerator action, boolean allowStateLoss) {
        if (!allowStateLoss || (this.mHost != null && !this.mDestroyed)) {
            ensureExecReady(allowStateLoss);
            if (action.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                } finally {
                    cleanupExec();
                }
            }
            updateOnBackPressedCallbackEnabled();
            doPendingDeferredStart();
            burpActive();
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    /* JADX INFO: finally extract failed */
    public boolean execPendingActions() {
        ensureExecReady(true);
        boolean didSomething = false;
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
                didSomething = true;
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        burpActive();
        return didSomething;
    }

    private void executePostponedTransaction(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop) {
        int numPostponed;
        int index;
        int index2;
        if (this.mPostponedTransactions == null) {
            numPostponed = 0;
        } else {
            numPostponed = this.mPostponedTransactions.size();
        }
        int i = 0;
        while (i < numPostponed) {
            StartEnterTransitionListener listener = this.mPostponedTransactions.get(i);
            if (records != null && !listener.mIsBack && (index2 = records.indexOf(listener.mRecord)) != -1 && isRecordPop.get(index2).booleanValue()) {
                this.mPostponedTransactions.remove(i);
                i--;
                numPostponed--;
                listener.cancelTransaction();
            } else if (listener.isReady() || (records != null && listener.mRecord.interactsWith(records, 0, records.size()))) {
                this.mPostponedTransactions.remove(i);
                i--;
                numPostponed--;
                if (records == null || listener.mIsBack || (index = records.indexOf(listener.mRecord)) == -1 || !isRecordPop.get(index).booleanValue()) {
                    listener.completeTransaction();
                } else {
                    listener.cancelTransaction();
                }
            }
            i++;
        }
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop) {
        if (records != null && !records.isEmpty()) {
            if (isRecordPop == null || records.size() != isRecordPop.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            executePostponedTransaction(records, isRecordPop);
            int numRecords = records.size();
            int startIndex = 0;
            int recordNum = 0;
            while (recordNum < numRecords) {
                if (!records.get(recordNum).mReorderingAllowed) {
                    if (startIndex != recordNum) {
                        executeOpsTogether(records, isRecordPop, startIndex, recordNum);
                    }
                    int reorderingEnd = recordNum + 1;
                    if (isRecordPop.get(recordNum).booleanValue()) {
                        while (reorderingEnd < numRecords && isRecordPop.get(reorderingEnd).booleanValue() && !records.get(reorderingEnd).mReorderingAllowed) {
                            reorderingEnd++;
                        }
                    }
                    executeOpsTogether(records, isRecordPop, recordNum, reorderingEnd);
                    startIndex = reorderingEnd;
                    recordNum = reorderingEnd - 1;
                }
                recordNum++;
            }
            if (startIndex != numRecords) {
                executeOpsTogether(records, isRecordPop, startIndex, numRecords);
            }
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        boolean allowReordering = records.get(startIndex).mReorderingAllowed;
        boolean addToBackStack = false;
        if (this.mTmpAddedFragments == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            this.mTmpAddedFragments.clear();
        }
        this.mTmpAddedFragments.addAll(this.mAdded);
        Fragment oldPrimaryNav = getPrimaryNavigationFragment();
        for (int recordNum = startIndex; recordNum < endIndex; recordNum++) {
            BackStackRecord record = records.get(recordNum);
            if (!isRecordPop.get(recordNum).booleanValue()) {
                oldPrimaryNav = record.expandOps(this.mTmpAddedFragments, oldPrimaryNav);
            } else {
                oldPrimaryNav = record.trackAddedFragmentsInPop(this.mTmpAddedFragments, oldPrimaryNav);
            }
            if (addToBackStack || record.mAddToBackStack) {
                addToBackStack = true;
            } else {
                addToBackStack = false;
            }
        }
        this.mTmpAddedFragments.clear();
        if (!allowReordering) {
            FragmentTransition.startTransitions(this, records, isRecordPop, startIndex, endIndex, false);
        }
        executeOps(records, isRecordPop, startIndex, endIndex);
        int postponeIndex = endIndex;
        if (allowReordering) {
            ArraySet<Fragment> addedFragments = new ArraySet<>();
            addAddedFragments(addedFragments);
            postponeIndex = postponePostponableTransactions(records, isRecordPop, startIndex, endIndex, addedFragments);
            makeRemovedFragmentsInvisible(addedFragments);
        }
        if (postponeIndex != startIndex && allowReordering) {
            FragmentTransition.startTransitions(this, records, isRecordPop, startIndex, postponeIndex, true);
            moveToState(this.mCurState, true);
        }
        for (int recordNum2 = startIndex; recordNum2 < endIndex; recordNum2++) {
            BackStackRecord record2 = records.get(recordNum2);
            if (isRecordPop.get(recordNum2).booleanValue() && record2.mIndex >= 0) {
                freeBackStackIndex(record2.mIndex);
                record2.mIndex = -1;
            }
            record2.runOnCommitRunnables();
        }
        if (addToBackStack) {
            reportBackStackChanged();
        }
    }

    private void makeRemovedFragmentsInvisible(ArraySet<Fragment> fragments) {
        int numAdded = fragments.size();
        for (int i = 0; i < numAdded; i++) {
            Fragment fragment = fragments.valueAt(i);
            if (!fragment.mAdded) {
                View view = fragment.requireView();
                fragment.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0f);
            }
        }
    }

    private int postponePostponableTransactions(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex, ArraySet<Fragment> added) {
        boolean isPostponed;
        int postponeIndex = endIndex;
        for (int i = endIndex - 1; i >= startIndex; i--) {
            BackStackRecord record = records.get(i);
            boolean isPop = isRecordPop.get(i).booleanValue();
            if (!record.isPostponed() || record.interactsWith(records, i + 1, endIndex)) {
                isPostponed = false;
            } else {
                isPostponed = true;
            }
            if (isPostponed) {
                if (this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList<>();
                }
                StartEnterTransitionListener listener = new StartEnterTransitionListener(record, isPop);
                this.mPostponedTransactions.add(listener);
                record.setOnStartPostponedListener(listener);
                if (isPop) {
                    record.executeOps();
                } else {
                    record.executePopOps(false);
                }
                postponeIndex--;
                if (i != postponeIndex) {
                    records.remove(i);
                    records.add(postponeIndex, record);
                }
                addAddedFragments(added);
            }
        }
        return postponeIndex;
    }

    /* access modifiers changed from: package-private */
    public void completeExecute(BackStackRecord record, boolean isPop, boolean runTransitions, boolean moveToState) {
        if (isPop) {
            record.executePopOps(moveToState);
        } else {
            record.executeOps();
        }
        ArrayList<BackStackRecord> records = new ArrayList<>(1);
        ArrayList<Boolean> isRecordPop = new ArrayList<>(1);
        records.add(record);
        isRecordPop.add(Boolean.valueOf(isPop));
        if (runTransitions) {
            FragmentTransition.startTransitions(this, records, isRecordPop, 0, 1, true);
        }
        if (moveToState) {
            moveToState(this.mCurState, true);
        }
        for (Fragment fragment : this.mActive.values()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && record.interactsWith(fragment.mContainerId)) {
                if (fragment.mPostponedAlpha > 0.0f) {
                    fragment.mView.setAlpha(fragment.mPostponedAlpha);
                }
                if (moveToState) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    private Fragment findFragmentUnder(Fragment f) {
        ViewGroup container = f.mContainer;
        View view = f.mView;
        if (container == null || view == null) {
            return null;
        }
        for (int i = this.mAdded.indexOf(f) - 1; i >= 0; i--) {
            Fragment underFragment = this.mAdded.get(i);
            if (underFragment.mContainer == container && underFragment.mView != null) {
                return underFragment;
            }
        }
        return null;
    }

    private static void executeOps(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        int i = startIndex;
        while (i < endIndex) {
            BackStackRecord record = records.get(i);
            if (isRecordPop.get(i).booleanValue()) {
                record.bumpBackStackNesting(-1);
                record.executePopOps(i == endIndex + -1);
            } else {
                record.bumpBackStackNesting(1);
                record.executeOps();
            }
            i++;
        }
    }

    private void addAddedFragments(ArraySet<Fragment> added) {
        if (this.mCurState >= 1) {
            int state = Math.min(this.mCurState, 3);
            int numAdded = this.mAdded.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = this.mAdded.get(i);
                if (fragment.mState < state) {
                    moveToState(fragment, state, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                        added.add(fragment);
                    }
                }
            }
        }
    }

    private void forcePostponedTransactions() {
        if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).completeTransaction();
            }
        }
    }

    private void endAnimatingAwayFragments() {
        for (Fragment fragment : this.mActive.values()) {
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragment.setAnimatingAway((View) null);
                    moveToState(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> records, ArrayList<Boolean> isPop) {
        synchronized (this) {
            try {
                if (this.mPendingActions == null || this.mPendingActions.size() == 0) {
                    return false;
                }
                int numActions = this.mPendingActions.size();
                int i = 0;
                boolean didSomething = false;
                while (i < numActions) {
                    try {
                        i++;
                        didSomething |= this.mPendingActions.get(i).generateOps(records, isPop);
                    } catch (Throwable th) {
                        th = th;
                        boolean z = didSomething;
                        throw th;
                    }
                }
                try {
                    this.mPendingActions.clear();
                    this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                    boolean z2 = didSomething;
                    return didSomething;
                } catch (Throwable th2) {
                    th = th2;
                    boolean z3 = didSomething;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    /* access modifiers changed from: package-private */
    public void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
                this.mBackStackChangeListeners.get(i).onBackStackChanged();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addBackStackState(BackStackRecord state) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList<>();
        }
        this.mBackStack.add(state);
    }

    /* access modifiers changed from: package-private */
    public boolean popBackStackState(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, String name, int id, int flags) {
        if (this.mBackStack == null) {
            return false;
        }
        if (name == null && id < 0 && (flags & 1) == 0) {
            int last = this.mBackStack.size() - 1;
            if (last < 0) {
                return false;
            }
            records.add(this.mBackStack.remove(last));
            isRecordPop.add(true);
        } else {
            int index = -1;
            if (name != null || id >= 0) {
                int index2 = this.mBackStack.size() - 1;
                while (index >= 0) {
                    BackStackRecord bss = this.mBackStack.get(index);
                    if ((name != null && name.equals(bss.getName())) || (id >= 0 && id == bss.mIndex)) {
                        break;
                    }
                    index2 = index - 1;
                }
                if (index < 0) {
                    return false;
                }
                if ((flags & 1) != 0) {
                    index--;
                    while (index >= 0) {
                        BackStackRecord bss2 = this.mBackStack.get(index);
                        if ((name == null || !name.equals(bss2.getName())) && (id < 0 || id != bss2.mIndex)) {
                            break;
                        }
                        index--;
                    }
                }
            }
            if (index == this.mBackStack.size() - 1) {
                return false;
            }
            for (int i = this.mBackStack.size() - 1; i > index; i--) {
                records.add(this.mBackStack.remove(i));
                isRecordPop.add(true);
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public FragmentManagerNonConfig retainNonConfig() {
        if (this.mHost instanceof ViewModelStoreOwner) {
            throwException(new IllegalStateException("You cannot use retainNonConfig when your FragmentHostCallback implements ViewModelStoreOwner."));
        }
        return this.mNonConfig.getSnapshot();
    }

    /* access modifiers changed from: package-private */
    public void saveFragmentViewState(Fragment f) {
        if (f.mInnerView != null) {
            if (this.mStateArray == null) {
                this.mStateArray = new SparseArray<>();
            } else {
                this.mStateArray.clear();
            }
            f.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                f.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Bundle saveFragmentBasicState(Fragment f) {
        Bundle result = null;
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        f.performSaveInstanceState(this.mStateBundle);
        dispatchOnFragmentSaveInstanceState(f, this.mStateBundle, false);
        if (!this.mStateBundle.isEmpty()) {
            result = this.mStateBundle;
            this.mStateBundle = null;
        }
        if (f.mView != null) {
            saveFragmentViewState(f);
        }
        if (f.mSavedViewState != null) {
            if (result == null) {
                result = new Bundle();
            }
            result.putSparseParcelableArray(VIEW_STATE_TAG, f.mSavedViewState);
        }
        if (!f.mUserVisibleHint) {
            if (result == null) {
                result = new Bundle();
            }
            result.putBoolean(USER_VISIBLE_HINT_TAG, f.mUserVisibleHint);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public Parcelable saveAllState() {
        int size;
        FragmentManagerState fms = null;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        this.mStateSaved = true;
        if (!this.mActive.isEmpty()) {
            ArrayList<FragmentState> active = new ArrayList<>(this.mActive.size());
            boolean haveFragments = false;
            for (Fragment f : this.mActive.values()) {
                if (f != null) {
                    if (f.mFragmentManager != this) {
                        throwException(new IllegalStateException("Failure saving state: active " + f + " was removed from the FragmentManager"));
                    }
                    haveFragments = true;
                    FragmentState fs = new FragmentState(f);
                    active.add(fs);
                    if (f.mState <= 0 || fs.mSavedFragmentState != null) {
                        fs.mSavedFragmentState = f.mSavedFragmentState;
                    } else {
                        fs.mSavedFragmentState = saveFragmentBasicState(f);
                        if (f.mTargetWho != null) {
                            Fragment target = this.mActive.get(f.mTargetWho);
                            if (target == null) {
                                throwException(new IllegalStateException("Failure saving state: " + f + " has target not in fragment manager: " + f.mTargetWho));
                            }
                            if (fs.mSavedFragmentState == null) {
                                fs.mSavedFragmentState = new Bundle();
                            }
                            putFragment(fs.mSavedFragmentState, TARGET_STATE_TAG, target);
                            if (f.mTargetRequestCode != 0) {
                                fs.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, f.mTargetRequestCode);
                            }
                        }
                    }
                    if (DEBUG) {
                        Log.v(TAG, "Saved state of " + f + ": " + fs.mSavedFragmentState);
                    }
                }
            }
            if (haveFragments) {
                ArrayList<String> added = null;
                BackStackState[] backStack = null;
                int size2 = this.mAdded.size();
                if (size2 > 0) {
                    added = new ArrayList<>(size2);
                    Iterator<Fragment> it = this.mAdded.iterator();
                    while (it.hasNext()) {
                        Fragment f2 = it.next();
                        added.add(f2.mWho);
                        if (f2.mFragmentManager != this) {
                            throwException(new IllegalStateException("Failure saving state: active " + f2 + " was removed from the FragmentManager"));
                        }
                        if (DEBUG) {
                            Log.v(TAG, "saveAllState: adding fragment (" + f2.mWho + "): " + f2);
                        }
                    }
                }
                if (this.mBackStack != null && (size = this.mBackStack.size()) > 0) {
                    backStack = new BackStackState[size];
                    for (int i = 0; i < size; i++) {
                        backStack[i] = new BackStackState(this.mBackStack.get(i));
                        if (DEBUG) {
                            Log.v(TAG, "saveAllState: adding back stack #" + i + ": " + this.mBackStack.get(i));
                        }
                    }
                }
                fms = new FragmentManagerState();
                fms.mActive = active;
                fms.mAdded = added;
                fms.mBackStack = backStack;
                if (this.mPrimaryNav != null) {
                    fms.mPrimaryNavActiveWho = this.mPrimaryNav.mWho;
                }
                fms.mNextFragmentIndex = this.mNextFragmentIndex;
            } else if (DEBUG) {
                Log.v(TAG, "saveAllState: no fragments!");
            }
        }
        return fms;
    }

    /* access modifiers changed from: package-private */
    public void restoreAllState(Parcelable state, FragmentManagerNonConfig nonConfig) {
        if (this.mHost instanceof ViewModelStoreOwner) {
            throwException(new IllegalStateException("You must use restoreSaveState when your FragmentHostCallback implements ViewModelStoreOwner"));
        }
        this.mNonConfig.restoreFromSnapshot(nonConfig);
        restoreSaveState(state);
    }

    /* access modifiers changed from: package-private */
    public void restoreSaveState(Parcelable state) {
        if (state != null) {
            FragmentManagerState fms = (FragmentManagerState) state;
            if (fms.mActive != null) {
                for (Fragment f : this.mNonConfig.getRetainedFragments()) {
                    if (DEBUG) {
                        Log.v(TAG, "restoreSaveState: re-attaching retained " + f);
                    }
                    FragmentState fs = null;
                    Iterator<FragmentState> it = fms.mActive.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        FragmentState fragmentState = it.next();
                        if (fragmentState.mWho.equals(f.mWho)) {
                            fs = fragmentState;
                            break;
                        }
                    }
                    if (fs == null) {
                        if (DEBUG) {
                            Log.v(TAG, "Discarding retained Fragment " + f + " that was not found in the set of active Fragments " + fms.mActive);
                        }
                        moveToState(f, 1, 0, 0, false);
                        f.mRemoving = true;
                        moveToState(f, 0, 0, 0, false);
                    } else {
                        fs.mInstance = f;
                        f.mSavedViewState = null;
                        f.mBackStackNesting = 0;
                        f.mInLayout = false;
                        f.mAdded = false;
                        f.mTargetWho = f.mTarget != null ? f.mTarget.mWho : null;
                        f.mTarget = null;
                        if (fs.mSavedFragmentState != null) {
                            fs.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            f.mSavedViewState = fs.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
                            f.mSavedFragmentState = fs.mSavedFragmentState;
                        }
                    }
                }
                this.mActive.clear();
                Iterator<FragmentState> it2 = fms.mActive.iterator();
                while (it2.hasNext()) {
                    FragmentState fs2 = it2.next();
                    if (fs2 != null) {
                        Fragment f2 = fs2.instantiate(this.mHost.getContext().getClassLoader(), getFragmentFactory());
                        f2.mFragmentManager = this;
                        if (DEBUG) {
                            Log.v(TAG, "restoreSaveState: active (" + f2.mWho + "): " + f2);
                        }
                        this.mActive.put(f2.mWho, f2);
                        fs2.mInstance = null;
                    }
                }
                this.mAdded.clear();
                if (fms.mAdded != null) {
                    Iterator<String> it3 = fms.mAdded.iterator();
                    while (it3.hasNext()) {
                        String who = it3.next();
                        Fragment f3 = this.mActive.get(who);
                        if (f3 == null) {
                            throwException(new IllegalStateException("No instantiated fragment for (" + who + ")"));
                        }
                        f3.mAdded = true;
                        if (DEBUG) {
                            Log.v(TAG, "restoreSaveState: added (" + who + "): " + f3);
                        }
                        if (this.mAdded.contains(f3)) {
                            throw new IllegalStateException("Already added " + f3);
                        }
                        synchronized (this.mAdded) {
                            this.mAdded.add(f3);
                        }
                    }
                }
                if (fms.mBackStack != null) {
                    this.mBackStack = new ArrayList<>(fms.mBackStack.length);
                    for (int i = 0; i < fms.mBackStack.length; i++) {
                        BackStackRecord bse = fms.mBackStack[i].instantiate(this);
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: back stack #" + i + " (index " + bse.mIndex + "): " + bse);
                            PrintWriter pw = new PrintWriter(new LogWriter(TAG));
                            bse.dump("  ", pw, false);
                            pw.close();
                        }
                        this.mBackStack.add(bse);
                        if (bse.mIndex >= 0) {
                            setBackStackIndex(bse.mIndex, bse);
                        }
                    }
                } else {
                    this.mBackStack = null;
                }
                if (fms.mPrimaryNavActiveWho != null) {
                    this.mPrimaryNav = this.mActive.get(fms.mPrimaryNavActiveWho);
                    dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
                }
                this.mNextFragmentIndex = fms.mNextFragmentIndex;
            }
        }
    }

    private void burpActive() {
        this.mActive.values().removeAll(Collections.singleton((Object) null));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: androidx.activity.OnBackPressedDispatcherOwner} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: androidx.fragment.app.Fragment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: androidx.fragment.app.Fragment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: androidx.fragment.app.Fragment} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void attachController(@androidx.annotation.NonNull androidx.fragment.app.FragmentHostCallback r6, @androidx.annotation.NonNull androidx.fragment.app.FragmentContainer r7, @androidx.annotation.Nullable androidx.fragment.app.Fragment r8) {
        /*
            r5 = this;
            androidx.fragment.app.FragmentHostCallback r3 = r5.mHost
            if (r3 == 0) goto L_0x000c
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "Already attached"
            r3.<init>(r4)
            throw r3
        L_0x000c:
            r5.mHost = r6
            r5.mContainer = r7
            r5.mParent = r8
            androidx.fragment.app.Fragment r3 = r5.mParent
            if (r3 == 0) goto L_0x0019
            r5.updateOnBackPressedCallbackEnabled()
        L_0x0019:
            boolean r3 = r6 instanceof androidx.activity.OnBackPressedDispatcherOwner
            if (r3 == 0) goto L_0x0030
            r0 = r6
            androidx.activity.OnBackPressedDispatcherOwner r0 = (androidx.activity.OnBackPressedDispatcherOwner) r0
            androidx.activity.OnBackPressedDispatcher r3 = r0.getOnBackPressedDispatcher()
            r5.mOnBackPressedDispatcher = r3
            if (r8 == 0) goto L_0x003b
            r1 = r8
        L_0x0029:
            androidx.activity.OnBackPressedDispatcher r3 = r5.mOnBackPressedDispatcher
            androidx.activity.OnBackPressedCallback r4 = r5.mOnBackPressedCallback
            r3.addCallback(r1, r4)
        L_0x0030:
            if (r8 == 0) goto L_0x003d
            androidx.fragment.app.FragmentManagerImpl r3 = r8.mFragmentManager
            androidx.fragment.app.FragmentManagerViewModel r3 = r3.getChildNonConfig(r8)
            r5.mNonConfig = r3
        L_0x003a:
            return
        L_0x003b:
            r1 = r0
            goto L_0x0029
        L_0x003d:
            boolean r3 = r6 instanceof androidx.lifecycle.ViewModelStoreOwner
            if (r3 == 0) goto L_0x004e
            androidx.lifecycle.ViewModelStoreOwner r6 = (androidx.lifecycle.ViewModelStoreOwner) r6
            androidx.lifecycle.ViewModelStore r2 = r6.getViewModelStore()
            androidx.fragment.app.FragmentManagerViewModel r3 = androidx.fragment.app.FragmentManagerViewModel.getInstance(r2)
            r5.mNonConfig = r3
            goto L_0x003a
        L_0x004e:
            androidx.fragment.app.FragmentManagerViewModel r3 = new androidx.fragment.app.FragmentManagerViewModel
            r4 = 0
            r3.<init>(r4)
            r5.mNonConfig = r3
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManagerImpl.attachController(androidx.fragment.app.FragmentHostCallback, androidx.fragment.app.FragmentContainer, androidx.fragment.app.Fragment):void");
    }

    public void noteStateNotSaved() {
        this.mStateSaved = false;
        this.mStopped = false;
        int addedCount = this.mAdded.size();
        for (int i = 0; i < addedCount; i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(1);
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(2);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(3);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(4);
    }

    public void dispatchPause() {
        dispatchStateChange(3);
    }

    public void dispatchStop() {
        this.mStopped = true;
        dispatchStateChange(2);
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        dispatchStateChange(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher != null) {
            this.mOnBackPressedCallback.remove();
            this.mOnBackPressedDispatcher = null;
        }
    }

    /* JADX INFO: finally extract failed */
    private void dispatchStateChange(int nextState) {
        try {
            this.mExecutingActions = true;
            moveToState(nextState, false);
            this.mExecutingActions = false;
            execPendingActions();
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    public void dispatchMultiWindowModeChanged(boolean isInMultiWindowMode) {
        for (int i = this.mAdded.size() - 1; i >= 0; i--) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performMultiWindowModeChanged(isInMultiWindowMode);
            }
        }
    }

    public void dispatchPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        for (int i = this.mAdded.size() - 1; i >= 0; i--) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performPictureInPictureModeChanged(isInPictureInPictureMode);
            }
        }
    }

    public void dispatchConfigurationChanged(@NonNull Configuration newConfig) {
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performConfigurationChanged(newConfig);
            }
        }
    }

    public void dispatchLowMemory() {
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null) {
                f.performLowMemory();
            }
        }
    }

    public boolean dispatchCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if (this.mCurState < 1) {
            return false;
        }
        boolean show = false;
        ArrayList<Fragment> newMenus = null;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performCreateOptionsMenu(menu, inflater)) {
                show = true;
                if (newMenus == null) {
                    newMenus = new ArrayList<>();
                }
                newMenus.add(f);
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment f2 = this.mCreatedMenus.get(i2);
                if (newMenus == null || !newMenus.contains(f2)) {
                    f2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = newMenus;
        return show;
    }

    public boolean dispatchPrepareOptionsMenu(@NonNull Menu menu) {
        if (this.mCurState < 1) {
            return false;
        }
        boolean show = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performPrepareOptionsMenu(menu)) {
                show = true;
            }
        }
        return show;
    }

    public boolean dispatchOptionsItemSelected(@NonNull MenuItem item) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performOptionsItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean dispatchContextItemSelected(@NonNull MenuItem item) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.performContextItemSelected(item)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(@NonNull Menu menu) {
        if (this.mCurState >= 1) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = this.mAdded.get(i);
                if (f != null) {
                    f.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment f) {
        if (f == null || (this.mActive.get(f.mWho) == f && (f.mHost == null || f.getFragmentManager() == this))) {
            Fragment previousPrimaryNav = this.mPrimaryNav;
            this.mPrimaryNav = f;
            dispatchParentPrimaryNavigationFragmentChanged(previousPrimaryNav);
            dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
            return;
        }
        throw new IllegalArgumentException("Fragment " + f + " is not an active fragment of FragmentManager " + this);
    }

    private void dispatchParentPrimaryNavigationFragmentChanged(@Nullable Fragment f) {
        if (f != null && this.mActive.get(f.mWho) == f) {
            f.performPrimaryNavigationFragmentChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchPrimaryNavigationFragmentChanged() {
        updateOnBackPressedCallbackEnabled();
        dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
    }

    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public void setMaxLifecycle(Fragment f, Lifecycle.State state) {
        if (this.mActive.get(f.mWho) == f && (f.mHost == null || f.getFragmentManager() == this)) {
            f.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + f + " is not an active fragment of FragmentManager " + this);
    }

    @NonNull
    public FragmentFactory getFragmentFactory() {
        if (super.getFragmentFactory() == DEFAULT_FACTORY) {
            if (this.mParent != null) {
                return this.mParent.mFragmentManager.getFragmentFactory();
            }
            setFragmentFactory(new FragmentFactory() {
                @NonNull
                public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
                    return FragmentManagerImpl.this.mHost.instantiate(FragmentManagerImpl.this.mHost.getContext(), className, (Bundle) null);
                }
            });
        }
        return super.getFragmentFactory();
    }

    public void registerFragmentLifecycleCallbacks(@NonNull FragmentManager.FragmentLifecycleCallbacks cb, boolean recursive) {
        this.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(cb, recursive));
    }

    public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentManager.FragmentLifecycleCallbacks cb) {
        synchronized (this.mLifecycleCallbacks) {
            int i = 0;
            int N = this.mLifecycleCallbacks.size();
            while (true) {
                if (i >= N) {
                    break;
                } else if (this.mLifecycleCallbacks.get(i).mCallback == cb) {
                    this.mLifecycleCallbacks.remove(i);
                    break;
                } else {
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPreAttached(@NonNull Fragment f, @NonNull Context context, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPreAttached(f, context, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentPreAttached(this, f, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentAttached(@NonNull Fragment f, @NonNull Context context, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentAttached(f, context, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentAttached(this, f, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPreCreated(@NonNull Fragment f, @Nullable Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPreCreated(f, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentPreCreated(this, f, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentCreated(@NonNull Fragment f, @Nullable Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentCreated(f, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentCreated(this, f, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentActivityCreated(@NonNull Fragment f, @Nullable Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentActivityCreated(f, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentActivityCreated(this, f, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentViewCreated(@NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentViewCreated(f, v, savedInstanceState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentViewCreated(this, f, v, savedInstanceState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentStarted(@NonNull Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentStarted(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentStarted(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentResumed(@NonNull Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentResumed(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentResumed(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPaused(@NonNull Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPaused(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentPaused(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentStopped(@NonNull Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentStopped(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentStopped(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentSaveInstanceState(@NonNull Fragment f, @NonNull Bundle outState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentSaveInstanceState(f, outState, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentSaveInstanceState(this, f, outState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentViewDestroyed(@NonNull Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentViewDestroyed(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentViewDestroyed(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentDestroyed(@NonNull Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentDestroyed(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentDestroyed(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentDetached(@NonNull Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentDetached(f, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder holder = it.next();
            if (!onlyRecursive || holder.mRecursive) {
                holder.mCallback.onFragmentDetached(this, f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean checkForMenus() {
        boolean hasMenu = false;
        for (Fragment f : this.mActive.values()) {
            if (f != null) {
                hasMenu = isMenuAvailable(f);
                continue;
            }
            if (hasMenu) {
                return true;
            }
        }
        return false;
    }

    private boolean isMenuAvailable(Fragment f) {
        return (f.mHasMenu && f.mMenuVisible) || f.mChildFragmentManager.checkForMenus();
    }

    public static int reverseTransit(int transit) {
        switch (transit) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    public static int transitToStyleIndex(int transit, boolean enter) {
        switch (transit) {
            case 4097:
                return enter ? 1 : 2;
            case 4099:
                return enter ? 5 : 6;
            case 8194:
                return enter ? 3 : 4;
            default:
                return -1;
        }
    }

    @Nullable
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        int containerId;
        Fragment fragment;
        int i;
        if (!"fragment".equals(name)) {
            return null;
        }
        String fname = attrs.getAttributeValue((String) null, "class");
        TypedArray a = context.obtainStyledAttributes(attrs, FragmentTag.Fragment);
        if (fname == null) {
            fname = a.getString(0);
        }
        int id = a.getResourceId(1, -1);
        String tag = a.getString(2);
        a.recycle();
        if (fname == null || !FragmentFactory.isFragmentClass(context.getClassLoader(), fname)) {
            return null;
        }
        if (parent != null) {
            containerId = parent.getId();
        } else {
            containerId = 0;
        }
        if (containerId == -1 && id == -1 && tag == null) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + fname);
        }
        if (id != -1) {
            fragment = findFragmentById(id);
        } else {
            fragment = null;
        }
        if (fragment == null && tag != null) {
            fragment = findFragmentByTag(tag);
        }
        if (fragment == null && containerId != -1) {
            fragment = findFragmentById(containerId);
        }
        if (DEBUG) {
            Log.v(TAG, "onCreateView: id=0x" + Integer.toHexString(id) + " fname=" + fname + " existing=" + fragment);
        }
        if (fragment == null) {
            fragment = getFragmentFactory().instantiate(context.getClassLoader(), fname);
            fragment.mFromLayout = true;
            if (id != 0) {
                i = id;
            } else {
                i = containerId;
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = containerId;
            fragment.mTag = tag;
            fragment.mInLayout = true;
            fragment.mFragmentManager = this;
            fragment.mHost = this.mHost;
            fragment.onInflate(this.mHost.getContext(), attrs, fragment.mSavedFragmentState);
            addFragment(fragment, true);
        } else if (fragment.mInLayout) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(id) + ", tag " + tag + ", or parent id 0x" + Integer.toHexString(containerId) + " with another fragment for " + fname);
        } else {
            fragment.mInLayout = true;
            fragment.mHost = this.mHost;
            fragment.onInflate(this.mHost.getContext(), attrs, fragment.mSavedFragmentState);
        }
        if (this.mCurState >= 1 || !fragment.mFromLayout) {
            moveToState(fragment);
        } else {
            moveToState(fragment, 1, 0, 0, false);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + fname + " did not create a view.");
        }
        if (id != 0) {
            fragment.mView.setId(id);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(tag);
        }
        return fragment.mView;
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView((View) null, name, context, attrs);
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this;
    }

    static class FragmentTag {
        public static final int[] Fragment = {16842755, 16842960, 16842961};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        private FragmentTag() {
        }
    }

    private class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(String name, int id, int flags) {
            this.mName = name;
            this.mId = id;
            this.mFlags = flags;
        }

        public boolean generateOps(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop) {
            if (FragmentManagerImpl.this.mPrimaryNav != null && this.mId < 0 && this.mName == null && FragmentManagerImpl.this.mPrimaryNav.getChildFragmentManager().popBackStackImmediate()) {
                return false;
            }
            return FragmentManagerImpl.this.popBackStackState(records, isRecordPop, this.mName, this.mId, this.mFlags);
        }
    }

    static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        final boolean mIsBack;
        private int mNumPostponed;
        final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord record, boolean isBack) {
            this.mIsBack = isBack;
            this.mRecord = record;
        }

        public void onStartEnterTransition() {
            this.mNumPostponed--;
            if (this.mNumPostponed == 0) {
                this.mRecord.mManager.scheduleCommit();
            }
        }

        public void startListening() {
            this.mNumPostponed++;
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        public void completeTransaction() {
            boolean canceled;
            boolean z = false;
            if (this.mNumPostponed > 0) {
                canceled = true;
            } else {
                canceled = false;
            }
            FragmentManagerImpl manager = this.mRecord.mManager;
            int numAdded = manager.mAdded.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = manager.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener((Fragment.OnStartEnterTransitionListener) null);
                if (canceled && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
            BackStackRecord backStackRecord = this.mRecord;
            boolean z2 = this.mIsBack;
            if (!canceled) {
                z = true;
            }
            fragmentManagerImpl.completeExecute(backStackRecord, z2, z, true);
        }

        public void cancelTransaction() {
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
        }
    }

    private static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animation animation2) {
            this.animation = animation2;
            this.animator = null;
            if (animation2 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        AnimationOrAnimator(Animator animator2) {
            this.animation = null;
            this.animator = animator2;
            if (animator2 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    private static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        private boolean mAnimating = true;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimation(@NonNull Animation animation, @NonNull ViewGroup parent, @NonNull View child) {
            super(false);
            this.mParent = parent;
            this.mChild = child;
            addAnimation(animation);
            this.mParent.post(this);
        }

        public boolean getTransformation(long currentTime, Transformation t) {
            this.mAnimating = true;
            if (this.mEnded) {
                if (!this.mTransitionEnded) {
                    return true;
                }
                return false;
            } else if (super.getTransformation(currentTime, t)) {
                return true;
            } else {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
                return true;
            }
        }

        public boolean getTransformation(long currentTime, Transformation outTransformation, float scale) {
            this.mAnimating = true;
            if (this.mEnded) {
                if (!this.mTransitionEnded) {
                    return true;
                }
                return false;
            } else if (super.getTransformation(currentTime, outTransformation, scale)) {
                return true;
            } else {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
                return true;
            }
        }

        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
                return;
            }
            this.mAnimating = false;
            this.mParent.post(this);
        }
    }
}
