package com.xsdk.doraemon.widget;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

public class TInputConnection extends InputConnectionWrapper {
    private BackspaceListener mBackspaceListener;

    public interface BackspaceListener {
        boolean onBackspace();
    }

    public TInputConnection(InputConnection target, boolean mutable) {
        super(target, mutable);
    }

    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        if (this.mBackspaceListener == null || !this.mBackspaceListener.onBackspace()) {
            return super.deleteSurroundingText(beforeLength, afterLength);
        }
        return true;
    }

    public void setBackspaceListener(BackspaceListener backspaceListener) {
        this.mBackspaceListener = backspaceListener;
    }

    public boolean sendKeyEvent(KeyEvent event) {
        if (event.getKeyCode() != 67 || event.getAction() != 0 || this.mBackspaceListener == null || !this.mBackspaceListener.onBackspace()) {
            return super.sendKeyEvent(event);
        }
        return true;
    }
}
