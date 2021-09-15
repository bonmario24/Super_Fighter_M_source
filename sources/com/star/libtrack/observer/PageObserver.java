package com.star.libtrack.observer;

import android.app.Activity;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.star.libtrack.event.Event;

public abstract class PageObserver extends BaseObserver {
    public void handEvent(Event event) {
        switch (event.getCode()) {
            case 0:
                onActivityCreate((Activity) event.getExt());
                return;
            case 1:
                onActivityDestroy((Activity) event.getExt());
                return;
            case 2:
                onViewAdded((View) event.getExt());
                return;
            case 3:
                onViewRemoved((View) event.getExt());
                return;
            case 4:
                onFragmentAttached((Fragment) event.getExt());
                return;
            case 5:
                onFragmentDetached((Fragment) event.getExt());
                return;
            default:
                return;
        }
    }

    public void onActivityCreate(Activity activity) {
    }

    public void onActivityDestroy(Activity activity) {
    }

    public void onViewAdded(View view) {
    }

    public void onViewRemoved(View view) {
    }

    public void onFragmentAttached(Fragment fragment) {
    }

    public void onFragmentDetached(Fragment fragment) {
    }
}
