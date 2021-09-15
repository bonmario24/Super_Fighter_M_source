package com.star.libtrack.observer;

import android.view.View;
import com.star.libtrack.event.Event;

public abstract class ClickObserver extends BaseObserver {
    public abstract void onViewClicked(View view);

    public void handEvent(Event event) {
        onViewClicked((View) event.getExt());
    }
}
