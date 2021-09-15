package com.example.testrun;

import com.zero.base.BaseConfig;

public class GameConfig implements BaseConfig {
    public static final String Hosts_accountServer = "login.funallstar.com";
    public static final String Hosts_backGroundServer = "login.funallstar.com";
    public static final String Hosts_updateServer = "login.funallstar.com:8085";
    public static final String Hosts_userActionServer = "login.funallstar.com";
    public static final String appId = "";
    public static final String appKey = "";
    public static final String bg_name = "";
    public static final String channel = "xc_xm_an_en";
    public static final String logo_name = "Empty";
    public static final String showPrivacyPolicy = "true";
    public static final String splash_name = "";

    public String getAccountServer() {
        return "login.funallstar.com";
    }

    public String getAppid() {
        return "";
    }

    public String getAppkey() {
        return "";
    }

    public String getBackGroundServer() {
        return "login.funallstar.com";
    }

    public String getChannel() {
        return channel;
    }

    public String getUpdateServer() {
        return Hosts_updateServer;
    }

    public String getUserActionServer() {
        return "login.funallstar.com";
    }
}
