package com.xsdk.doraemon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import com.xsdk.doraemon.widget.TInputConnection;

public class DelEditTextView extends EditText {
    private TInputConnection inputConnection;

    public DelEditTextView(Context context) {
        super(context);
        init();
    }

    public DelEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DelEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.inputConnection = new TInputConnection((InputConnection) null, true);
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        this.inputConnection.setTarget(super.onCreateInputConnection(outAttrs));
        return this.inputConnection;
    }

    public void setBackSpaceListener(TInputConnection.BackspaceListener backSpaceLisetener) {
        this.inputConnection.setBackspaceListener(backSpaceLisetener);
    }
}
