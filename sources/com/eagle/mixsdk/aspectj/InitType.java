package com.eagle.mixsdk.aspectj;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface InitType {
    public static final String CHANNEL = "channel_init";
    public static final String EAGLE = "eagle_init";
}
