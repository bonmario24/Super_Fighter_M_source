package com.star.libtrack.core;

import java.util.Map;

public interface ICloudLadder {
    void init(boolean z, String str, String str2, String str3);

    boolean submitCustomEvent(String str, String str2, Map<String, Object> map);

    void submitStartupEvent();
}
