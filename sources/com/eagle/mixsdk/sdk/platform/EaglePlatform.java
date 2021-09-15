package com.eagle.mixsdk.sdk.platform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.WindowManager;
import com.doraemon.util.language.LanguageUtil;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IPlugin;
import com.eagle.mixsdk.sdk.PayParams;
import com.eagle.mixsdk.sdk.ShareParams;
import com.eagle.mixsdk.sdk.UserExtraData;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.IEagleSDKListener;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.dialog.BindMobilePageDialog;
import com.eagle.mixsdk.sdk.impl.listeners.ISDKOrderIDListener;
import com.eagle.mixsdk.sdk.impl.listeners.ISDKShareListener;
import com.eagle.mixsdk.sdk.listeners.EagleAntiAddictionListener;
import com.eagle.mixsdk.sdk.listeners.EagleBindMobileListener;
import com.eagle.mixsdk.sdk.listeners.EagleInitListener;
import com.eagle.mixsdk.sdk.listeners.EagleInvokeListener;
import com.eagle.mixsdk.sdk.listeners.EagleLoginListener;
import com.eagle.mixsdk.sdk.listeners.EaglePayListener;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.other.GameEvent;
import com.eagle.mixsdk.sdk.other.p029bm.BindMobileHelper;
import com.eagle.mixsdk.sdk.plugin.EagleExtPlugin;
import com.eagle.mixsdk.sdk.plugin.EagleIDot;
import com.eagle.mixsdk.sdk.plugin.EaglePay;
import com.eagle.mixsdk.sdk.plugin.EagleServicePlugin;
import com.eagle.mixsdk.sdk.plugin.EagleShare;
import com.eagle.mixsdk.sdk.plugin.EagleUser;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.eagle.mixsdk.track.GameTrackReport;
import java.util.Map;

public class EaglePlatform {
    private static volatile EaglePlatform instance;
    private boolean isSwitchAccount = false;
    private final GameEvent mGameEvent = new GameEvent();
    private IEagleSDKListener mSdkInitListener;

    private EaglePlatform() {
    }

    public static EaglePlatform getInstance() {
        if (instance == null) {
            synchronized (EaglePlatform.class) {
                if (instance == null) {
                    instance = new EaglePlatform();
                }
            }
        }
        return instance;
    }

    @Deprecated
    public void init(final Activity context, final EagleInitListener callback) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            Log.superError(Constants.TAG, "       init sdk must in the MainThread       ");
            EagleSDK.getInstance().runOnMainThread(new Runnable() {
                public void run() {
                    EaglePlatform.this.init(context, callback);
                }
            });
            return;
        }
        Log.superError(Constants.TAG, "该 init 方法已废弃，请不要再调用");
        if (callback == null) {
            Log.m598d(Constants.TAG, "EagleInitListener must be not null.");
            return;
        }
        try {
            EagleSDK.getInstance().register(com.eagle.mixsdk.support.event.GameEvent.create(callback));
            EagleSDK.getInstance().init(context);
        } catch (Exception e) {
            callback.onInitResult(2, e.getMessage());
            Log.m600e(Constants.TAG, "init failed.", e);
            e.printStackTrace();
        }
    }

    public void init(final Activity context, final EagleInitListener listener) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            Log.superError(Constants.TAG, "       init sdk must in the MainThread       ");
            EagleSDK.getInstance().runOnMainThread(new Runnable() {
                public void run() {
                    EaglePlatform.this.init(context, listener);
                }
            });
        } else if (context == null || listener == null) {
            Log.m598d(Constants.TAG, "context or EagleInitListener must be not null.");
        } else {
            Log.m602w(Constants.TAG, context.getClass().getSimpleName() + ":init");
            this.mGameEvent.setInitListener(listener);
            EagleSDK.getInstance().register(this.mGameEvent);
            EagleSDK.getInstance().init(context);
        }
    }

    public void login(Activity context, EagleLoginListener listener) {
        if (context == null) {
            Log.m598d(Constants.TAG, "context  must be not null.");
            return;
        }
        Log.m602w(Constants.TAG, context.getClass().getSimpleName() + ":login");
        if (listener != null) {
            this.mGameEvent.setLoginListener(listener);
        }
        EagleSDK.getInstance().setContext(context);
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (!EagleUser.getInstance().isTooFast()) {
                    EagleUser.getInstance().checkUpdate();
                }
            }
        });
    }

    public void pay(Activity context, final PayParams data, EaglePayListener listener) {
        if (context == null) {
            Log.m598d(Constants.TAG, "context  must be not null.");
            return;
        }
        if (listener != null) {
            this.mGameEvent.setPayListener(listener);
        }
        Log.m602w(Constants.TAG, context.getClass().getSimpleName() + ":pay");
        EagleSDK.getInstance().setContext(context);
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                EaglePay.getInstance().pay(data);
            }
        });
    }

    @Deprecated
    public void login(Activity context) {
        Log.superError(Constants.TAG, "该 login 方法已废弃，请不要再调用");
        login(context, (EagleLoginListener) null);
    }

    @Deprecated
    public void pay(Activity context, PayParams data) {
        Log.superError(Constants.TAG, "该 pay 方法已废弃，请不要再调用");
        pay(context, data, (EaglePayListener) null);
    }

    @Deprecated
    public void pay(Activity context, PayParams data, ISDKOrderIDListener listener) {
        Log.superError(Constants.TAG, "该 pay 方法已废弃，请不要再调用");
        pay(context, data, (EaglePayListener) null);
    }

    public void logout() {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (EagleUser.getInstance().isSupport("logout")) {
                    EagleUser.getInstance().logout();
                }
            }
        });
    }

    public void getPlayerAntiAddictionInfo(EagleAntiAddictionListener listener) {
        Log.m602w(Constants.TAG, getClass().getSimpleName() + ":getPlayerCertificationInfo");
        EagleUser.getInstance().getPlayerAntiAddictionInfo(listener);
    }

    public void showAccountCenter() {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (EagleUser.getInstance().isSupport("showAccountCenter")) {
                    EagleUser.getInstance().showAccountCenter();
                }
            }
        });
    }

    public void openBindMobilePage(final Activity activity, final String roleData, final EagleBindMobileListener listener) {
        if (isSupportMethod("bindMobile")) {
            EagleSDK.getInstance().runOnMainThread(new Runnable() {
                public void run() {
                    new BindMobilePageDialog(activity, roleData).addListener(listener).show();
                }
            });
        }
    }

    public void sendBMVerifyCode(final String phoneNum, final EagleBindMobileListener listener) {
        if (isSupportMethod("bindMobile")) {
            EagleSDK.getInstance().runOnMainThread(new Runnable() {
                public void run() {
                    BindMobileHelper.getInstance().sendVerifyCode(phoneNum, listener);
                }
            });
        }
    }

    public void invoke(Activity activity, String methodName, Map<String, Object> params) {
        invoke(activity, methodName, params, (EagleInvokeListener) null);
    }

    public void invoke(Activity activity, String methodName, Map<String, Object> params, EagleInvokeListener callback) {
        if (TextUtils.isEmpty(methodName)) {
            Log.m602w(Constants.TAG, "no the method params");
            return;
        }
        final Activity activity2 = activity;
        final String str = methodName;
        final EagleInvokeListener eagleInvokeListener = callback;
        final Map<String, Object> map = params;
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                EagleSDK.getInstance().setContext(activity2);
                EagleExtPlugin.getInstance().addInvokeListener(str, eagleInvokeListener);
                EagleExtPlugin.getInstance().invoke(str, map);
            }
        });
    }

    public boolean isExtSupport(String method) {
        return EagleExtPlugin.getInstance().isSupportMethod(method);
    }

    public boolean isBindMobile() {
        return EagleSDK.getInstance().getUToken() != null && EagleSDK.getInstance().getUToken().isBindMobile();
    }

    public void bindMobile(String captcha, String phoneNum, String roleData, EagleBindMobileListener listener) {
        if (isSupportMethod("bindMobile")) {
            BindMobileHelper.getInstance().bindingMobile(captcha, phoneNum, roleData, listener);
        }
    }

    public void submitExtraData(final UserExtraData data) {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                EagleUser.getInstance().submitExtraData(data);
            }
        });
    }

    public void switchLogin() {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (EagleUser.getInstance().isSupport("switchLogin")) {
                    EagleUser.getInstance().switchLogin();
                }
            }
        });
    }

    @Deprecated
    public void exitSDK(final EagleExitListener callback) {
        Log.superError(Constants.TAG, "该 exitSDK 方法已废弃，请不要再调用");
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (callback != null) {
                    callback.onGameExit();
                }
                if (EagleUser.getInstance().isSupport("exit")) {
                    EagleUser.getInstance().exit();
                } else {
                    EagleSDK.getInstance().exitDialog();
                }
            }
        });
    }

    public void exitSDK() {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (EagleUser.getInstance().isSupport("exit")) {
                    EagleUser.getInstance().exit();
                } else {
                    EagleSDK.getInstance().exitDialog();
                }
            }
        });
    }

    public boolean switchAppLanguage(Activity activity, String language) {
        return LanguageUtil.getInstance().switchAppLanguage(language, activity);
    }

    public Context attachBaseContext(Context context) {
        return LanguageUtil.getInstance().attachBaseContext(context);
    }

    public void restApp(Activity activity) {
        LanguageUtil.getInstance().restartApp(activity);
    }

    public void queryProducts(Activity context) {
        EagleSDK.getInstance().setContext(context);
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                EagleUser.getInstance().queryProducts();
            }
        });
    }

    public void openCustomerService(String serviceKey) {
        EagleServicePlugin.getInstance().openCustomerService(EagleSDK.getInstance().getAppID() + "");
    }

    public void reportEvent(Activity context, String action, Map<String, Object> map) {
        EagleIDot.getInstance().reportEvent(context, action, map);
    }

    public void reportGameEvent(Activity context, String action, Map<String, Object> map) {
        GameTrackReport.get(context).reportEvent(context, action, map);
    }

    public void shareWithImage(Activity context, String imgPath, ISDKShareListener listener) {
        EagleSDK.getInstance().setContext(context);
        if (TextUtils.isEmpty(imgPath)) {
            throw new NullPointerException("local image path is empty!!!!!");
        }
        ShareParams shareParams = new ShareParams();
        shareParams.setImgPath(imgPath);
        shareParams.setShareType(1);
        EagleShare.getInstance().share(shareParams, listener);
    }

    public void shareWithSdkData(Activity context, ISDKShareListener listener) {
        EagleSDK.getInstance().setContext(context);
        EagleShare.getInstance().share((ShareParams) null, listener);
    }

    public boolean isSupportMethod(String method) {
        char c = 65535;
        switch (method.hashCode()) {
            case -1867494543:
                if (method.equals(SupportMethod.PLAYER_ADULT_INFO_METHOD)) {
                    c = 3;
                    break;
                }
                break;
            case -926750473:
                if (method.equals("customerService")) {
                    c = 2;
                    break;
                }
                break;
            case 713998143:
                if (method.equals("bindMobile")) {
                    c = 0;
                    break;
                }
                break;
            case 1499637796:
                if (method.equals("EagleIDot")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (EagleSDK.getInstance().getUToken() == null || !EagleSDK.getInstance().getUToken().isOpenBindMobile()) {
                    return false;
                }
                return true;
            case 1:
                return EagleIDot.getInstance().isSupport("");
            case 2:
                return EagleServicePlugin.getInstance().isSupport("");
            case 3:
                EagleToken eagleToken = EagleSDK.getInstance().getUToken();
                if (eagleToken != null) {
                    return eagleToken.getAntiAddictionSwitch() == 1;
                }
                break;
        }
        return EagleUser.getInstance().isSupport(method);
    }

    public IPlugin getPlugin(int pluginType) {
        return PluginFactory.getInstance().getPlugin(pluginType);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.m602w(Constants.TAG, "----------onActivityResult-------------");
        EagleSDK.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    public void onStart() {
        Log.m602w(Constants.TAG, "----------onStart-------------");
        EagleSDK.getInstance().onStart();
    }

    public void onPause() {
        Log.m602w(Constants.TAG, "----------onPause-------------");
        EagleSDK.getInstance().onPause();
    }

    public void onResume() {
        Log.m602w(Constants.TAG, "----------onResume-------------");
        EagleSDK.getInstance().onResume();
    }

    public void onNewIntent(Intent newIntent) {
        Log.m602w(Constants.TAG, "----------onNewIntent-------------");
        EagleSDK.getInstance().onNewIntent(newIntent);
    }

    public void onStop() {
        Log.m602w(Constants.TAG, "----------onStop-------------");
        EagleSDK.getInstance().onStop();
    }

    public void onDestroy() {
        Log.m602w(Constants.TAG, "----------onDestroy-------------");
        EagleSDK.getInstance().onDestroy();
    }

    public void onRestart() {
        Log.m602w(Constants.TAG, "----------onRestart-------------");
        EagleSDK.getInstance().onRestart();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        EagleSDK.getInstance().onConfigurationChanged(newConfig);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.m602w(Constants.TAG, "----------onRequestPermissionsResult-------------");
        EagleSDK.getInstance().onRequestPermissionResult(requestCode, permissions, grantResults);
    }

    public void onSaveInstanceState(Bundle outState) {
        EagleSDK.getInstance().onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        EagleSDK.getInstance().onRestoreInstanceState(savedInstanceState);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        EagleSDK.getInstance().onWindowFocusChanged(hasFocus);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        EagleSDK.getInstance().onWindowAttributesChanged(params);
    }
}
