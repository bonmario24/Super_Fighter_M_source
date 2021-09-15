package com.eagle.mixsdk.sdk.plugin;

import android.content.Context;
import android.support.annotation.NonNull;
import com.eagle.mixsdk.aspectj.LoginTrack;
import com.eagle.mixsdk.aspectj.SDKTrack;
import com.eagle.mixsdk.aspectj.TrackAspect;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IUser;
import com.eagle.mixsdk.sdk.UserExtraData;
import com.eagle.mixsdk.sdk.active.AndroidJSObject;
import com.eagle.mixsdk.sdk.active.CustomAlertDialog;
import com.eagle.mixsdk.sdk.active.CustomWebView;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.dialog.GameNoticeDialog;
import com.eagle.mixsdk.sdk.impl.AntiAddictionProxyListener;
import com.eagle.mixsdk.sdk.impl.DefaultUser;
import com.eagle.mixsdk.sdk.impl.listeners.IEagleUpdateListener;
import com.eagle.mixsdk.sdk.impl.listeners.ILoginListener;
import com.eagle.mixsdk.sdk.listeners.EagleAntiAddictionListener;
import com.eagle.mixsdk.sdk.listeners.EagleGetUserInfoListener;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.platform.EagleBindMobileListener;
import com.eagle.mixsdk.sdk.verify.EagleProxy;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.annotation.Annotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONObject;

public class EagleUser {
    private static Annotation ajc$anno$0;
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static EagleUser instance;
    private long lastTime = 0;
    /* access modifiers changed from: private */
    public EagleToken mEagleToken;
    private UserExtraData mUserExtraData = null;
    private IUser userPlugin;

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("EagleUser.java", EagleUser.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "init", "com.eagle.mixsdk.sdk.plugin.EagleUser", "", "", "", "void"), 45);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "checkUpdate", "com.eagle.mixsdk.sdk.plugin.EagleUser", "", "", "", "void"), 84);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "login", "com.eagle.mixsdk.sdk.plugin.EagleUser", "", "", "", "void"), 112);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "login", "com.eagle.mixsdk.sdk.plugin.EagleUser", "java.lang.String", "customData", "", "void"), 121);
        ajc$tjp_4 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "switchLogin", "com.eagle.mixsdk.sdk.plugin.EagleUser", "", "", "", "void"), 130);
        ajc$tjp_5 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "getPlayerAntiAddictionInfo", "com.eagle.mixsdk.sdk.plugin.EagleUser", "com.eagle.mixsdk.sdk.listeners.EagleAntiAddictionListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "void"), 143);
        ajc$tjp_6 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "submitExtraData", "com.eagle.mixsdk.sdk.plugin.EagleUser", "com.eagle.mixsdk.sdk.UserExtraData", "extraData", "", "void"), 189);
        ajc$tjp_7 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "startAuthTask", "com.eagle.mixsdk.sdk.plugin.EagleUser", "java.lang.String:boolean", "result:isSwitch", "", "void"), 242);
    }

    private EagleUser() {
    }

    public void init() {
        TrackAspect.aspectOf().onStartInit(Factory.makeJP(ajc$tjp_0, this, this));
        this.userPlugin = (IUser) PluginFactory.getInstance().initPlugin(1);
        if (this.userPlugin == null) {
            this.userPlugin = new DefaultUser();
        }
    }

    public static EagleUser getInstance() {
        if (instance == null) {
            instance = new EagleUser();
        }
        return instance;
    }

    public boolean isSupport(String method) {
        return this.userPlugin != null && this.userPlugin.isSupportMethod(method);
    }

    public UserExtraData getUserExtraData() {
        return this.mUserExtraData;
    }

    @LoginTrack.StartLogin
    public void checkUpdate() {
        TrackAspect.aspectOf().onStartLogin(Factory.makeJP(ajc$tjp_1, this, this));
        EagleUpdate.getInstance().isNeedToUpdate(EagleSDK.getInstance().getContext(), new IEagleUpdateListener() {
            public void onNeedToUpdate() {
            }

            public void onNotToUpdate() {
                EagleUser.this.login();
            }
        });
    }

    public boolean isTooFast() {
        long l = System.currentTimeMillis();
        boolean isTooFast = false;
        if (this.lastTime > 0 && l - this.lastTime < 1000) {
            Log.m599e(Constants.TAG, "login too fast " + (l - this.lastTime));
            isTooFast = true;
        }
        this.lastTime = System.currentTimeMillis();
        return isTooFast;
    }

    @LoginTrack.ShowLoginPage
    public void login() {
        TrackAspect.aspectOf().onShowLogin(Factory.makeJP(ajc$tjp_2, this, this));
        if (this.userPlugin != null) {
            this.userPlugin.login();
        }
    }

    @LoginTrack.ShowLoginPage
    public void login(String customData) {
        TrackAspect.aspectOf().onShowLogin(Factory.makeJP(ajc$tjp_3, (Object) this, (Object) this, (Object) customData));
        if (this.userPlugin != null) {
            this.userPlugin.loginCustom(customData);
        }
    }

    @LoginTrack.ShowLoginPage
    public void switchLogin() {
        TrackAspect.aspectOf().onShowLogin(Factory.makeJP(ajc$tjp_4, this, this));
        if (this.userPlugin != null) {
            this.userPlugin.switchLogin();
        }
    }

    @LoginTrack.StartPlayerAntiAddictionInfo
    public void getPlayerAntiAddictionInfo(@NonNull EagleAntiAddictionListener listener) {
        TrackAspect.aspectOf().onStartPlayerAntiAddictionInfo(Factory.makeJP(ajc$tjp_5, (Object) this, (Object) this, (Object) listener));
        AntiAddictionProxyListener proxyListener = new AntiAddictionProxyListener(listener);
        if (getEagleToken() == null || getEagleToken().getAntiAddictionSwitch() != 1 || this.userPlugin == null) {
            proxyListener.createDefaultAntiAddictionInfoResult();
        } else {
            this.userPlugin.getPlayerCertificationInfo(proxyListener);
        }
    }

    public void getUserInfo(String extInfo, EagleGetUserInfoListener userInfoListener) {
        if (this.userPlugin == null) {
            userInfoListener.onResult((JSONObject) null);
        } else {
            this.userPlugin.getUserInfo(extInfo, userInfoListener);
        }
    }

    public void showAccountCenter() {
        if (this.userPlugin != null) {
            this.userPlugin.showAccountCenter();
        }
    }

    public void logout() {
        if (this.userPlugin != null) {
            this.userPlugin.logout();
        }
    }

    @SDKTrack.GameEvent
    public void submitExtraData(UserExtraData extraData) {
        TrackAspect.aspectOf().onGameEvent(Factory.makeJP(ajc$tjp_6, (Object) this, (Object) this, (Object) extraData));
        if (this.userPlugin != null) {
            this.mUserExtraData = extraData;
            this.userPlugin.submitExtraData(extraData);
        }
    }

    public void exit() {
        if (this.userPlugin != null) {
            this.userPlugin.exit();
        }
    }

    public void postGiftCode(String code) {
        if (this.userPlugin != null) {
            this.userPlugin.postGiftCode(code);
        }
    }

    public void queryProducts() {
        if (this.userPlugin != null) {
            this.userPlugin.queryProducts();
        }
    }

    public IUser getIUser() {
        return this.userPlugin;
    }

    public void bindMobile(EagleBindMobileListener listener) {
        if (this.userPlugin != null) {
            this.userPlugin.bindMobile(listener);
        }
    }

    @LoginTrack.SDKLogin(result = 1)
    public void startAuthTask(String result, final boolean isSwitch) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_7, this, this, result, Conversions.booleanObject(isSwitch));
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$0;
        if (annotation == null) {
            annotation = EagleUser.class.getDeclaredMethod("startAuthTask", new Class[]{String.class, Boolean.TYPE}).getAnnotation(LoginTrack.SDKLogin.class);
            ajc$anno$0 = annotation;
        }
        aspectOf.onSDKLogin(makeJP, (LoginTrack.SDKLogin) annotation);
        Log.m599e(Constants.TAG, "begin to auth..." + result);
        this.mEagleToken = null;
        EagleProxy.getToken(result, new ILoginListener() {
            public void onLoginSuccess(EagleToken token) {
                if (token.isShowGameNotice()) {
                    new GameNoticeDialog(EagleUser.this.getContext()).setNoticeContent(token.getGameNoticeContent()).setCancelable(true).show();
                }
                if (token.isSuc()) {
                    EagleToken unused = EagleUser.this.mEagleToken = token;
                    if (token.getIsActivated() == 0) {
                        CustomWebView mWebView = new CustomWebView(EagleUser.this.getContext());
                        mWebView.toLoadUrl(EagleSDK.getInstance().getActiveUrl() + "?" + AndroidJSObject.getActiveWebViewParams());
                        mWebView.installJSInterface(new AndroidJSObject(), "control");
                        CustomAlertDialog.getInstance().createDialog(EagleSDK.getInstance().getContext(), mWebView);
                    } else if (!token.isOpenServer()) {
                        EagleUser.this.loginFail("关服中，游戏暂不开放");
                    } else if (isSwitch) {
                        EagleSDK.getInstance().onSwitchAccount(token);
                    } else {
                        EagleSDK.getInstance().onLoginSuccess(token);
                    }
                } else {
                    EagleUser.this.loginFail(token.getMsg());
                }
            }

            public void onLoginFail(String msg) {
                EagleUser.this.loginFail(msg);
            }
        });
    }

    /* access modifiers changed from: private */
    public Context getContext() {
        return EagleSDK.getInstance().getContext();
    }

    public void loginFail(String msg) {
        EagleSDK.getInstance().onEagleLoginFail(msg);
    }

    public EagleToken getEagleToken() {
        return this.mEagleToken;
    }

    public void clearEagleToken() {
        if (this.mEagleToken != null) {
            this.mEagleToken = null;
        }
    }
}
