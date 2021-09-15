package com.thinkfly.plugins.coludladder.config;

import com.lzy.okgo.OkGo;

public class Config {
    public static long CRITICAL_POINT_TIME = 600000;
    public static long FIRST_TIME = 1000;
    public static long HEARTBEAT_TIME = OkGo.DEFAULT_MILLISECONDS;
    public static String INTERVAL_TIME = "interval";
    public static String TAG = "CloudLadder";
    public static String UNKNOWN = "unknown";
    public static String URL = "http://applog.dc.hoho666.com/app";

    public static class DBConfig {
        public static String DATA_NAME = "";
        public static final String DB_NAME = (DATA_NAME + ".db");
        public static final String DB_TABLE_NAME = (DATA_NAME + "_table");
        public static final String SP_NAME = (DATA_NAME + "_sp");
    }

    public static class NetWorkType {
        public static int Flow_Type = 1;
        public static int UnKonw_Type = 0;
        public static int WIFI_Type = 2;
    }

    public static class RepeatConfig {
        public static int MAXNUMBER = 20;
        public static long MAX_SLEEP_TIME = OkGo.DEFAULT_MILLISECONDS;
    }

    public static class Salt {
        public static String SALT = "{pu$rv)+r";
    }
}
