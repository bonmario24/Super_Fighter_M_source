package com.xhuyu.component.callback;

import android.view.View;

public abstract class OnMultiClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public abstract void onMultiClick(View view);

    public void onClick(View view) {
        long curClickTime = System.currentTimeMillis();
        if (curClickTime - lastClickTime >= 1000) {
            lastClickTime = curClickTime;
            onMultiClick(view);
        }
    }
}
