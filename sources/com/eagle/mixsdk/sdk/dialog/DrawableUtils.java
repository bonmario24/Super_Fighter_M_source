package com.eagle.mixsdk.sdk.dialog;

import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtils {
    public static PaintDrawable getBackgroundDrawable(int color, int roundRadii) {
        PaintDrawable drawable = new PaintDrawable(color);
        drawable.setCornerRadius((float) roundRadii);
        return drawable;
    }

    public static StateListDrawable getStateListDrawable(int normalColor, int pressedColor, int roundRadii) {
        PaintDrawable drawableNormal = new PaintDrawable(normalColor);
        drawableNormal.setCornerRadius((float) roundRadii);
        PaintDrawable drawablePressed = new PaintDrawable(pressedColor);
        drawablePressed.setCornerRadius((float) roundRadii);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842910, 16842919}, drawablePressed);
        stateListDrawable.addState(new int[0], drawableNormal);
        return stateListDrawable;
    }
}
