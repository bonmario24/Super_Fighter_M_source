package com.star.libtrack.observer;

import android.widget.EditText;
import com.star.libtrack.event.Event;

public abstract class KeyboardObserver extends BaseObserver {
    public abstract void onKeyboardDown(EditText editText);

    public abstract void onKeyboardUp(EditText editText);

    public void handEvent(Event event) {
        switch (event.getCode()) {
            case 0:
                onKeyboardUp((EditText) null);
                return;
            case 1:
                onKeyboardDown((EditText) null);
                return;
            default:
                return;
        }
    }
}
