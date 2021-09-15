package com.xhuyu.component.core.config;

public class SuperTool {
    private static SuperTool mInstance;
    private int activityResultType;

    private SuperTool() {
    }

    public static SuperTool getInstance() {
        if (mInstance == null) {
            synchronized (SuperTool.class) {
                if (mInstance == null) {
                    mInstance = new SuperTool();
                }
            }
        }
        return mInstance;
    }

    public int getActivityResultType() {
        return this.activityResultType;
    }

    public void setActivityResultType(int activityResultType2) {
        this.activityResultType = activityResultType2;
    }
}
