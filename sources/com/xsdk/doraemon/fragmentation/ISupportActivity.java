package com.xsdk.doraemon.fragmentation;

import android.view.MotionEvent;
import com.xsdk.doraemon.fragmentation.anim.FragmentAnimator;

public interface ISupportActivity {
    boolean dispatchTouchEvent(MotionEvent motionEvent);

    ExtraTransaction extraTransaction();

    FragmentAnimator getFragmentAnimator();

    SupportActivityDelegate getSupportDelegate();

    void onBackPressed();

    void onBackPressedSupport();

    FragmentAnimator onCreateFragmentAnimator();

    void post(Runnable runnable);

    void setFragmentAnimator(FragmentAnimator fragmentAnimator);
}
