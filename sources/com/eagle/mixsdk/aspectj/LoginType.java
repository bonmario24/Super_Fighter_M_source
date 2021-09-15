package com.eagle.mixsdk.aspectj;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface LoginType {
    public static final String CHANNEL = "channel_login";
    public static final String EAGLE = "eagle_login";
}
