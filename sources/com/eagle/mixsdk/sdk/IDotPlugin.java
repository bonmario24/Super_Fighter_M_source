package com.eagle.mixsdk.sdk;

import android.content.Context;
import com.thinkfly.star.builder.InitBuilder;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import java.util.Map;

public interface IDotPlugin extends IPlugin {
    public static final int PLUGIN_TYPE = 10;

    void init(Context context, String str, String str2, String str3, InitBuilder initBuilder, boolean z);

    void reportEvent(Context context, String str, Map<String, Object> map);

    void reportLogin(Context context, LoginBuilder loginBuilder);

    void reportLogout(Context context, LogoutBuilder logoutBuilder);

    void reportRegister(Context context, RegisterBuilder registerBuilder);

    void reportStartup(Context context, StartupBuilder startupBuilder);
}
