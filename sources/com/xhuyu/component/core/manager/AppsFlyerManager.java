package com.xhuyu.component.core.manager;

import android.content.Context;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.mvp.model.LoginResult;
import com.xhuyu.component.utils.appsflyer.AFEventType;
import com.xhuyu.component.utils.appsflyer.AppsFlyerTrackUtil;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.Map;

public class AppsFlyerManager {
    private static AppsFlyerManager instance = null;
    private AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
        public void onConversionDataSuccess(Map<String, Object> conversionData) {
            for (String attrName : conversionData.keySet()) {
                SDKLoggerUtil.getLogger().mo19501d("attribute: " + attrName + " = " + conversionData.get(attrName), new Object[0]);
            }
        }

        public void onConversionDataFail(String errorMessage) {
            SDKLoggerUtil.getLogger().mo19501d("error getting conversion data: " + errorMessage, new Object[0]);
        }

        public void onAppOpenAttribution(Map<String, String> conversionData) {
            for (String attrName : conversionData.keySet()) {
                SDKLoggerUtil.getLogger().mo19501d("attribute: " + attrName + " = " + conversionData.get(attrName), new Object[0]);
            }
        }

        public void onAttributionFailure(String errorMessage) {
            SDKLoggerUtil.getLogger().mo19501d("error onAttributionFailure : " + errorMessage, new Object[0]);
        }
    };
    private Context mContext;

    private AppsFlyerManager() {
        SDKEventBus.getDefault().register(this);
    }

    public static AppsFlyerManager getInstance() {
        if (instance == null) {
            synchronized (AppsFlyerManager.class) {
                if (instance == null) {
                    instance = new AppsFlyerManager();
                }
            }
        }
        return instance;
    }

    public void init(Context context, String afDevKey) {
        this.mContext = context;
        AppsFlyerLib.getInstance().init(afDevKey, this.conversionListener, context);
        AppsFlyerLib.getInstance().startTracking(context);
        SDKLoggerUtil.getLogger().mo19504i("******* AppsFlyerLib init is OK *******", new Object[0]);
        AppsFlyerLib.getInstance().setDebugLog(SDKConfig.getInstance().isDebugEnvironment());
    }

    @Subscribe(event = {3})
    public void onLoginResult(LoginResult result) {
        if (result.getResultCode() == 1) {
            String loginType = null;
            switch (result.getLoginType()) {
                case 7:
                    loginType = AFEventType.VISITOR_LOGIN_RESULT;
                    break;
                case 10:
                    loginType = AFEventType.AUTO_LOGIN_RESULT;
                    break;
                case 11:
                    loginType = AFEventType.FB_LOGIN_RESULT;
                    break;
                case 12:
                    loginType = AFEventType.GOOGLE_LOGIN_RESULT;
                    break;
            }
            if (!CheckUtils.isNullOrEmpty(loginType)) {
                AppsFlyerTrackUtil.trackLoginResult(this.mContext, loginType);
            }
        }
    }
}
