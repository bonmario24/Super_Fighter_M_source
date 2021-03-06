package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.lang.reflect.Field;

@RequiresApi(19)
final class ImmLeaksCleaner implements LifecycleEventObserver {
    private static final int INIT_FAILED = 2;
    private static final int INIT_SUCCESS = 1;
    private static final int NOT_INITIALIAZED = 0;
    private static Field sHField;
    private static Field sNextServedViewField;
    private static int sReflectedFieldsInitialized = 0;
    private static Field sServedViewField;
    private Activity mActivity;

    ImmLeaksCleaner(Activity activity) {
        this.mActivity = activity;
    }

    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            if (sReflectedFieldsInitialized == 0) {
                initializeReflectiveFields();
            }
            if (sReflectedFieldsInitialized == 1) {
                InputMethodManager inputMethodManager = (InputMethodManager) this.mActivity.getSystemService("input_method");
                try {
                    Object lock = sHField.get(inputMethodManager);
                    if (lock != null) {
                        synchronized (lock) {
                            try {
                                View servedView = (View) sServedViewField.get(inputMethodManager);
                                if (servedView != null) {
                                    if (!servedView.isAttachedToWindow()) {
                                        try {
                                            sNextServedViewField.set(inputMethodManager, (Object) null);
                                            inputMethodManager.isActive();
                                        } catch (IllegalAccessException e) {
                                        }
                                    }
                                }
                            } catch (IllegalAccessException e2) {
                            } catch (ClassCastException e3) {
                            }
                        }
                    }
                } catch (IllegalAccessException e4) {
                }
            }
        }
    }

    @MainThread
    private static void initializeReflectiveFields() {
        try {
            sReflectedFieldsInitialized = 2;
            sServedViewField = InputMethodManager.class.getDeclaredField("mServedView");
            sServedViewField.setAccessible(true);
            sNextServedViewField = InputMethodManager.class.getDeclaredField("mNextServedView");
            sNextServedViewField.setAccessible(true);
            sHField = InputMethodManager.class.getDeclaredField("mH");
            sHField.setAccessible(true);
            sReflectedFieldsInitialized = 1;
        } catch (NoSuchFieldException e) {
        }
    }
}
