package com.star.libtrack.obserable;

import android.app.Activity;
import android.util.Log;
import android.view.ViewTreeObserver;
import com.star.libtrack.event.Event;
import java.lang.ref.WeakReference;

public class KeyboardObserable extends BaseObserable {
    public static final int CODE_KEYBOARD_DOWN = 1;
    public static final int CODE_KEYBOARD_UP = 0;
    /* access modifiers changed from: private */
    public WeakReference<Activity> activity;

    public KeyboardObserable(Activity activity2) {
        this.activity = new WeakReference<>(activity2);
        observer();
    }

    private class KeyboardLayoutObserver implements ViewTreeObserver.OnGlobalLayoutListener {
        int lastHeight;

        private KeyboardLayoutObserver() {
            this.lastHeight = -1;
        }

        public void onGlobalLayout() {
            if (!KeyboardObserable.this.isDetach()) {
                int height = ((Activity) KeyboardObserable.this.activity.get()).getWindow().getDecorView().findViewById(16908290).getHeight();
                Log.d("shen", "布局变化" + this.lastHeight + "--" + height);
                if (this.lastHeight == -1) {
                    this.lastHeight = height;
                } else if (this.lastHeight > height) {
                    if (this.lastHeight - height > this.lastHeight / 3) {
                        KeyboardObserable.this.dispatch(new Event(0, (Object) null, (String) null));
                    }
                } else if (this.lastHeight < height && height - this.lastHeight > height / 3) {
                    KeyboardObserable.this.dispatch(new Event(1, (Object) null, (String) null));
                }
                this.lastHeight = height;
            }
        }
    }

    private void observer() {
        if (!isDetach()) {
            ((Activity) this.activity.get()).getWindow().getDecorView().findViewById(16908290).getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardLayoutObserver());
        }
    }

    public void detach() {
        this.activity = null;
    }

    public boolean isDetach() {
        return this.activity == null || this.activity.get() == null;
    }
}
