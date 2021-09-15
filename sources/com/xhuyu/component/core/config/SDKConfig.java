package com.xhuyu.component.core.config;

import android.app.Activity;
import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.login.LoginBehavior;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.BuildConfig;
import com.xhuyu.component.core.config.Constant;
import com.xhuyu.component.utils.CacheUtils;
import com.xsdk.doraemon.apkreflect.ResManager;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SDKConfig {
    public static final String SDK_CONFIG_FILE_NAME = "xhuyu_sdk.properties";
    private static final SDKConfig mInstance = new SDKConfig();
    private static Map<String, Object> mSdkConfigs;

    private SDKConfig() {
        mSdkConfigs = new HashMap();
    }

    public static SDKConfig getInstance() {
        return mInstance;
    }

    public void initEnvironment(Context context) {
        CacheUtils.init(context);
        getInstance().loadSdkConfigInfo(context);
        ResManager.getInstance().init(context, BuildConfig.RES_APK_NAME, BuildConfig.RES_PACKAGE_NAME);
        initIpAddress();
        initSDKName();
        initGameID();
        initScreenOrientation();
        SDKLoggerUtil.setLoggerInfo(isDebugEnvironment(), "HuYu_SDK_V2.3.0");
    }

    public void loadSdkConfigInfo(Context context) {
        Properties properties = new Properties();
        try {
            InputStream ins = context.getAssets().open(SDK_CONFIG_FILE_NAME);
            properties.load(ins);
            ins.close();
            for (Object key : properties.keySet()) {
                mSdkConfigs.put(key.toString(), properties.getProperty(key.toString()));
            }
        } catch (IOException e) {
            SDKLoggerUtil.getLogger().mo19502e(e.getLocalizedMessage(), new Object[0]);
        }
    }

    public String getConfigValue(String configKey) {
        if (CheckUtil.isEmpty(configKey) || !mSdkConfigs.containsKey(configKey)) {
            return null;
        }
        return mSdkConfigs.get(configKey).toString();
    }

    public void addConfig(String configKey, Object configValue) {
        mSdkConfigs.put(configKey, configValue);
    }

    public boolean isDevelopInfoInvalid(String key) {
        return mSdkConfigs == null || !mSdkConfigs.containsKey(key);
    }

    private void initIpAddress() {
        String ip = getConfigValue(Constant.HuYuConfigKey.IP_KEY);
        if (!CheckUtil.isEmpty(ip)) {
            CacheUtils.putCacheString(Constant.HuYuCacheKey.KEY_IP, ip);
        }
    }

    private void initGameID() {
        String gameId = getConfigValue(Constant.HuYuConfigKey.GAME_ID_KEY);
        if (CheckUtil.isInteger(gameId)) {
            CacheUtils.putCacheInteger(Constant.HuYuCacheKey.KEY_GAME_ID, Integer.parseInt(gameId));
        }
    }

    public String getPlatformCipher() {
        if (!isDevelopInfoInvalid(Constant.HuYuConfigKey.HUYU_GAME_KEY)) {
            return (String) mSdkConfigs.get(Constant.HuYuConfigKey.HUYU_GAME_KEY);
        }
        return "";
    }

    public String getPayment() {
        return getConfigValue(Constant.HuYuConfigKey.HUYU_PAYMENT);
    }

    public String getFbApplicationId() {
        String fbAppId = getConfigValue(Constant.HuYuConfigKey.HUYU_FB_LOGIN_APPID);
        if (CheckUtils.isNullOrEmpty(fbAppId) || fbAppId.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            return null;
        }
        return fbAppId;
    }

    public boolean isShowGoogleLogin() {
        String isShow = getConfigValue(Constant.HuYuConfigKey.HUYU_HAS_GOOGLE_LOGIN);
        if (CheckUtils.isNullOrEmpty(isShow) || isShow.equals("false")) {
            return false;
        }
        return true;
    }

    public boolean isShowFacebookLogin() {
        String isShow = getConfigValue(Constant.HuYuConfigKey.HUYU_HAS_FACEBOOK_LOGIN);
        if (CheckUtils.isNullOrEmpty(isShow) || isShow.equals("false")) {
            return false;
        }
        return true;
    }

    public boolean isShowVisitorLogin() {
        String isShow = getConfigValue(Constant.HuYuConfigKey.HUYU_HAS_VISITOR_LOGIN);
        if (CheckUtils.isNullOrEmpty(isShow) || isShow.equals("false")) {
            return false;
        }
        return true;
    }

    public String getAnalyzeFacebookAppId() {
        String fbAppId = getConfigValue(Constant.HuYuConfigKey.ANALYZE_FACEBOOK_APPID);
        if (CheckUtils.isNullOrEmpty(fbAppId) || fbAppId.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            return null;
        }
        return fbAppId;
    }

    public LoginBehavior getFbLoginType() {
        String fbLoginType = getConfigValue(Constant.HuYuConfigKey.HUYU_FB_LOGIN_TYPE);
        if (!CheckUtils.isNullOrEmpty(fbLoginType) && "web".equals(fbLoginType)) {
            return LoginBehavior.WEB_ONLY;
        }
        return null;
    }

    private void initSDKName() {
        String sdkName = getConfigValue(Constant.HuYuConfigKey.HUYU_NAME_KEY);
        if (CheckUtil.isEmpty(sdkName)) {
            SDKLoggerUtil.getLogger().mo19502e("xhuyu_sdk.properties file error!", new Object[0]);
        } else {
            CacheUtils.putCacheString(Constant.HuYuCacheKey.KEY_SDK_NAME, sdkName);
        }
    }

    public boolean isDebugEnvironment() {
        Boolean debug = Boolean.valueOf(Boolean.parseBoolean(getConfigValue(Constant.HuYuConfigKey.DEBUG_MODE_KEY)));
        if (debug != null) {
            return debug.booleanValue();
        }
        return false;
    }

    private void initScreenOrientation() {
        String orientation = getConfigValue(Constant.HuYuConfigKey.ORIENTATION_KEY);
        boolean paramBoolean = false;
        if (!CheckUtil.isEmpty(orientation)) {
            paramBoolean = "landscape".equalsIgnoreCase(orientation);
        }
        CacheUtils.putCacheBoolean(Constant.HuYuCacheKey.KEY_LANDSCAPE, paramBoolean);
    }

    public boolean isLandscape() {
        return CacheUtils.getCacheBoolean(Constant.HuYuCacheKey.KEY_LANDSCAPE, false);
    }

    public void putGameKey(String rsaKey) {
        addConfig(rsaKey, Constant.HuYuConfigKey.HUYU_GAME_KEY);
    }

    public void setActivityOrientation(Activity activity) {
        if (activity != null) {
            if (isLandscape()) {
                activity.setRequestedOrientation(6);
            } else {
                activity.setRequestedOrientation(7);
            }
        }
    }

    public int getDialogViewHeight() {
        return CacheUtils.getCacheInteger(Constant.HuYuCacheKey.KEY_DIALOG_HEIGHT, -1);
    }

    public void setDialogViewHeight(int dialogViewHeight) {
        CacheUtils.putCacheInteger(Constant.HuYuCacheKey.KEY_DIALOG_HEIGHT, dialogViewHeight);
    }

    public int getLastLoginType() {
        return CacheUtils.getCacheInteger(Constant.HuYuCacheKey.KEY_LAST_LOGIN, 0);
    }

    public void saveLastLoginType(int type) {
        CacheUtils.putCacheInteger(Constant.HuYuCacheKey.KEY_LAST_LOGIN, type);
    }
}
