package com.xhuyu.component.network;

public @interface Urls {
    public static final String AUTO_LOGIN = "/api/user/login/auto";
    public static final String DUPLICATE_URL = "/api/game/preinit";
    public static final String FACEBOOK_LOGIN = "/api/user/login/facebook";
    public static final String FACEBOOK_SHARE_URL = "/api/game/share/facebook";
    public static final String FEEDBACK_URL = "/api/user/feedback";
    public static final String GET_PAYMENT_LIST = "/api/game/paytype/list";
    public static final String GOOGLE_LOGIN = "/api/user/login/google";
    public static final String GOOGLE_PAY = "/api/pay/notify/googlepay";
    public static final String NOTICE_URL = "/api/game/notice";
    public static final String UNIFORM_ORDER = "/api/pay/order/create";
    public static final String URL_BIND_PHONE = "/api/user/phone/bind";
    public static final String URL_EMAIL_BIND = "/api/user/email/bind";
    public static final String URL_EMAIL_CHANGE = "/api/user/email/change";
    public static final String URL_GET_EMAIL_VERIFY_CODE = "/api/user/email/sendEmailCode";
    public static final String URL_GET_EMAIL_VERIFY_CODE_WITH_LOGIN = "/api/user/email/getEmailCode";
    public static final String URL_GET_RAND_ACCOUNT = "/api/user/rand";
    public static final String URL_GET_SMS_VERIFY_CODE = "/api/user/phone/sendSmsCode";
    public static final String URL_GET_USER_INFO = "/api/user/info/get";
    public static final String URL_MODIFY_PASSWORD = "/api/user/password/changeByPassword";
    public static final String URL_PHONE_CODE_WITH_LOGIN = "/api/user/phone/getSmsCode";
    public static final String URL_QUICK_LOGIN = "/api/user/login/celerity";
    public static final String URL_SET_PASSWORD = "/api/user/password/set";
    public static final String URL_VERIFY_CHANGE_PHONE = "/api/user/phone/change";
    public static final String USER_AGREEMENT = "/api/user/register_agreement";
    public static final String VISITOR_LOGIN = "/api/user/login/visitor";
}
