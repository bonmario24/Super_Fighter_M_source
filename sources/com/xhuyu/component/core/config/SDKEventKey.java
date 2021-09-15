package com.xhuyu.component.core.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface SDKEventKey {
    public static final int ON_EXIT_APP = 7;
    public static final int ON_RECEIVER_COUNTRY_DATA = 16;
    public static final int ON_SDK_LOGIN = 3;
    public static final int ON_SDK_LOGOUT = 6;
    public static final int ON_SDK_PAYMENT = 5;
    public static final int ON_SDK_SHARE = 8;
    public static final int ON_START_EXIT_APP = 9;
}
