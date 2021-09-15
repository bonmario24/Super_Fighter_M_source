package com.xhuyu.component.core.api;

import com.thinkfly.star.CloudLadderApplication;

public class SDKApplication extends CloudLadderApplication {
    public void onCreate() {
        super.onCreate();
        HuYuApi.getInstance().initToApplication(this);
    }
}
