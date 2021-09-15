package com.xhuyu.component.core.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface TrackEventKey {
    public static final int ON_BILLING_CONSUME_PRODUCT = 8200;
    public static final int ON_BILLING_INITIALIZE = 8198;
    public static final int ON_BILLING_PAY_RESULT = 8201;
    public static final int ON_BILLING_QUERY_INVENTORY = 8199;
    public static final int ON_BILLING_QUERY_PRODUCT_DETAIL = 8208;
    public static final int ON_DUPLICATE_RESULT = 8215;
    public static final int ON_ONE_CLICK_REGISTRATION = 8216;
    public static final int ON_ORDERS_RESULT = 8209;
    public static final int ON_RECEIVER_SERVER_PAY_RESULT = 8214;
    public static final int ON_SDK_INITIALIZE = 8192;
    public static final int ON_STARTUP_APP = 8193;
    public static final int ON_START_LOGIN = 8194;
    public static final int ON_START_ORDERS = 8195;
    public static final int ON_START_PAYMENT = 8196;
    public static final int ON_TRACK_GAME_INFO = 8197;
    public static final int UNDEFINED = -1;
}
