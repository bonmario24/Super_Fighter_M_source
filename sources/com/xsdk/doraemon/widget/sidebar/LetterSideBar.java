package com.xsdk.doraemon.widget.sidebar;

import android.content.Context;
import android.util.AttributeSet;

public class LetterSideBar extends BaseSideBar {
    public LetterSideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LetterSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LetterSideBar(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public String[] getBars() {
        return new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    }
}
