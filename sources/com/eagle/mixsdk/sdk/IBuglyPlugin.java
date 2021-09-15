package com.eagle.mixsdk.sdk;

import android.content.Context;
import java.util.Map;
import java.util.Set;

public interface IBuglyPlugin {
    public static final int CRASHTYPE_ANR = 4;
    public static final int CRASHTYPE_BLOCK = 7;
    public static final int CRASHTYPE_COCOS2DX_JS = 5;
    public static final int CRASHTYPE_COCOS2DX_LUA = 6;
    public static final int CRASHTYPE_JAVA_CATCH = 1;
    public static final int CRASHTYPE_JAVA_CRASH = 0;
    public static final int CRASHTYPE_NATIVE = 2;
    public static final int CRASHTYPE_U3D = 3;

    public interface IBuglyLoger {
        /* renamed from: d */
        void mo15348d(String str, String str2);

        /* renamed from: e */
        void mo15349e(String str, String str2);

        /* renamed from: i */
        void mo15350i(String str, String str2);

        /* renamed from: v */
        void mo15351v(String str, String str2);
    }

    public interface ICrashHandler {
        Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3);

        byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3);
    }

    boolean containsUserDataKey(Context context, String str);

    void enableBugly(boolean z);

    Set<String> getAllUserDataKeys(Context context);

    IBuglyLoger getBuglyLogger();

    int getUserDatasSize(Context context);

    String getUserId();

    int getUserSceneTagId(Context context);

    void postCatchedException(Throwable th);

    void putUserData(Context context, String str, String str2);

    String removeUserData(Context context, String str);

    void setCrashHandler(ICrashHandler iCrashHandler);

    void setIsDevelopmentDevice(Context context, boolean z);

    void setUserId(String str);

    void setUserSceneTag(Context context, int i);
}
