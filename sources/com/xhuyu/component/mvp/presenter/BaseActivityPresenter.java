package com.xhuyu.component.mvp.presenter;

import android.os.Bundle;
import android.view.MotionEvent;
import androidx.annotation.Nullable;

public interface BaseActivityPresenter {
    boolean dispatchTouchEvent(MotionEvent motionEvent);

    void onCreate(@Nullable Bundle bundle);

    void onDestroy();
}
