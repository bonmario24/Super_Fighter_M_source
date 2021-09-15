package com.eagle.mixsdk.aspectj;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Constants {
    public static final int CANCEL = 2;
    public static final int FAIL = 0;
    public static final int OTHER = 3;
    public static final int SUCCESS = 1;
}
