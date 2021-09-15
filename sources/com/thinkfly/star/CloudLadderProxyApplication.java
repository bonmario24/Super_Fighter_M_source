package com.thinkfly.star;

import android.app.Application;

public class CloudLadderProxyApplication {
    private static int mFinalCount;

    public static void init(Application application) {
        if (application == null) {
            System.out.println("CloudLadderProxyApplication: the  Application is null !!!!!!!!!!!!!!!");
        } else {
            CloudLadderApplication.getInstance().init(application);
        }
    }
}
