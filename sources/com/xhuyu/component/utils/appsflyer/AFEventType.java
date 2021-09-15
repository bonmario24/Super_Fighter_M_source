package com.xhuyu.component.utils.appsflyer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface AFEventType {
    public static final String AUTO_LOGIN = "hy_auto_login";
    public static final String AUTO_LOGIN_RESULT = "hy_auto_login_result";
    public static final String COMPLETE_PURCHASE = "hy_complete_purchase";
    public static final String FACEBOOK_LOGIN = "hy_click_fb_login";
    public static final String FB_LOGIN_RESULT = "hy_fb_login_result";
    public static final String GOOGLE_LOGIN = "hy_click_google_login";
    public static final String GOOGLE_LOGIN_RESULT = "hy_google_login_result";
    public static final String ORDER_PAYMENT = "hy_order_payment";
    public static final String START_PAYMENT = "hy_start_payment";
    public static final String VISITOR_LOGIN = "hy_click_visitor_login";
    public static final String VISITOR_LOGIN_RESULT = "hy_visitor_login_result";
}
