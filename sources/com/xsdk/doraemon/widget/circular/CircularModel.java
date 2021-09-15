package com.xsdk.doraemon.widget.circular;

import android.graphics.drawable.Drawable;

public class CircularModel {
    private Drawable drawable;
    private int resID;
    private float scale = 0.6f;

    public CircularModel(int resID2, Drawable drawable2, float scale2) {
        this.resID = resID2;
        this.drawable = drawable2;
        this.scale = scale2;
    }

    public int getResID() {
        return this.resID;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public float getScale() {
        return this.scale;
    }
}
