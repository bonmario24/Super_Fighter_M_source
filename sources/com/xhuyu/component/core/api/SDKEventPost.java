package com.xhuyu.component.core.api;

import com.xsdk.doraemon.event.SDKEventBus;

public class SDKEventPost {
    public static void post(int eventKey, Object... params) {
        SDKEventBus.getDefault().post(eventKey, params);
    }

    public static void postTrack(int trackEventKey, Object... params) {
        SDKEventBus.getDefault().post(trackEventKey, params);
    }
}
