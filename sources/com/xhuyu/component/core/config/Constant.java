package com.xhuyu.component.core.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constant {
    public static final String LOG_TAG = "HuYu_SDK";
    public static final String PAK_FACEBOOK = "com.facebook.katana";
    public static final String PAK_GOOGLE_PLAY = "com.android.vending";
    public static final String PAK_GOOGLE_SERVICE = "com.google.android.gms";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActivityResultType {
        public static final int FACEBOOK = 1;
        public static final int FACEBOOK_SHARE = 3;
        public static final int GOOGLE = 2;
        public static final int POP_NOTICE = 4;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FacebookLoginType {
        public static final String DEFAULT_TYPE = "default";
        public static final String WEB_TYPE = "web";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HuYuCacheKey {
        public static final String KEY_DIALOG_HEIGHT = "DialogHeight";
        public static final String KEY_GAME_ID = "GameID";
        public static final String KEY_GOOGLE_ADVERTISING_ID = "GoogleAdvertisingId";
        public static final String KEY_GUID = "GUID";
        public static final String KEY_IP = "IP_Address";
        public static final String KEY_LANDSCAPE = "Landscape";
        public static final String KEY_LAST_LOGIN = "LAST_LOGIN";
        public static final String KEY_SDK_NAME = "SdkName";
        public static final String KEY_UNIQUE_ID = "Unique_ID";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HuYuConfigKey {
        public static final String ANALYZE_FACEBOOK_APPID = "Analyze_Facebook_AppId";
        public static final String APPSFLYER_DEV_KEY = "AppsFlyer_Dev_Key";
        public static final String CHANNEL_KEY = "Channel";
        public static final String CHANNEL_NAME_KEY = "Channel_Name";
        public static final String CURRENT_LANGUAGE_KEY = "HuYu_Current_Lan";
        public static final String DEBUG_MODE_KEY = "HuYu_Debug_Mode";
        public static final String GAME_ID_KEY = "HuYu_GameID";
        public static final String HUYU_FB_LOGIN_APPID = "HuYu_FB_Login_AppId";
        public static final String HUYU_FB_LOGIN_TYPE = "HuYu_FB_Login_Type";
        public static final String HUYU_GAME_KEY = "HuYu_Game_Key";
        public static final String HUYU_HAS_FACEBOOK_LOGIN = "HuYu_Has_Facebook_Login";
        public static final String HUYU_HAS_GOOGLE_LOGIN = "HuYu_Has_Google_Login";
        public static final String HUYU_HAS_VISITOR_LOGIN = "HuYu_Has_Visitor_Login";
        public static final String HUYU_NAME_KEY = "HuYu_Name";
        public static final String HUYU_PAYMENT = "HuYu_Payment";
        public static final String IP_KEY = "HuYu_IP";
        public static final String ORIENTATION_KEY = "HuYu_Orientation";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LadderConfigKey {
        public static final String Ladder_Host_KEY = "HuYu_Ladder_Host";
        public static final String Ladder_Salt_KEY = "HuYu_Ladder_Salt";
        public static final String Ladder_Who_KEY = "HuYu_Ladder_Who";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginType {
        public static final int LOGIN_ACCOUNT_PWD_TYPE = 5;
        public static final int LOGIN_AUTO_TYPE = 10;
        public static final int LOGIN_FACEBOOK_TYPE = 11;
        public static final int LOGIN_GOOGLE_TYPE = 12;
        public static final int LOGIN_NORMAL_TYPE = 13;
        public static final int LOGIN_PHONE_PWD_TYPE = 4;
        public static final int LOGIN_PHONE_VERIFICATION_CODE_TYPE = 6;
        public static final int LOGIN_QQ_TYPE = 9;
        public static final int LOGIN_QUICK_REGISTER_TYPE = 14;
        public static final int LOGIN_VISITOR_TYPE = 7;
        public static final int LOGIN_WECHAT_TYPE = 8;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PayType {
        public static final int GOOGLE_PAY_TYPE = 1009;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResponseCode {
        public static final int CANCEL_RESULT = 2;
        public static final int CONNECTION_NET_FAILED = -4;
        public static final int CONNECTION_SERVICE_FAILED = -1;
        public static final int CONNECTION_SERVICE_TIMEOUT = -3;
        public static final int DATA_PARSE_ERROR = -2;
        public static final int FAILED_RESULT = 0;
        public static final int OTHER_RESULT = 3;
        public static final int SUCCESS_RESULT = 1;
    }
}
