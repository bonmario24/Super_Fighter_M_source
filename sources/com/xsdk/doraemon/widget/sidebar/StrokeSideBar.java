package com.xsdk.doraemon.widget.sidebar;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.appevents.AppEventsConstants;

public class StrokeSideBar extends BaseSideBar {
    public StrokeSideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public StrokeSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeSideBar(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public String[] getBars() {
        return new String[]{AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "12", "14", "15", "16", "17", "18", "19", "."};
    }
}
