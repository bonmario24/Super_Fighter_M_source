package com.eagle.mixsdk.sdk.did.config;

import java.util.regex.Pattern;

public class DIDConfig {
    @Deprecated
    public static String CACHEDIR = "com/platform/android/uuid";
    public static final String INVALID_DID = "00000000000000000000000000000000";
    public static String NEW_CACHEDIR = ".eagle/sdk/com/platform/android/uuid";
    public static String SALT = "c9#*G@y$";
    public static String SP_DID_TAG = "DID";
    public static String SP_TAG_DID = "DID";
    public static String UUID_FILE_TAG = ".uuid";
    public static Pattern didPattern = Pattern.compile("^[12345678]?[0-9A-Z]{32}$");
    @Deprecated
    public static String uuidFile = "uuid_value.txt";

    public static int didStorageLevel() {
        return 2;
    }
}
