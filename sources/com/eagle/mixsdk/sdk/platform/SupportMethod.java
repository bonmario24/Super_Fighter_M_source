package com.eagle.mixsdk.sdk.platform;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface SupportMethod {
    public static final String BIND_MOBILE_METHOD = "bindMobile";
    public static final String EAGLE_IDOT_METHOD = "EagleIDot";
    public static final String EXIT_GAME_METHOD = "exit";
    public static final String LOGIN_METHOD = "login";
    public static final String LOGOUT_METHOD = "logout";
    public static final String PAY_METHOD = "pay";
    public static final String PLAYER_ADULT_INFO_METHOD = "getPlayerCertificationInfo";
    public static final String SERVICE_METHOD = "customerService";
    public static final String SHARE_METHOD = "share";
    public static final String SHOW_ACCOUNT_CENTER_METHOD = "showAccountCenter";
    public static final String SUBMIT_EXTRA_DATA_METHOD = "submitExtraData";
    public static final String SWITCH_LOGIN_METHOD = "switchLogin";
}
