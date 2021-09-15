package com.eagle.mixsdk.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.p027eg.ResManager;
import com.doraemon.util.ToastUtils;
import com.doraemon.util.language.LanguageUtil;
import com.eagle.mixsdk.aspectj.TrackAspect;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.IActivityCallback;
import com.eagle.mixsdk.sdk.base.IEagleSDKListener;
import com.eagle.mixsdk.sdk.dialog.ExitDialog;
import com.eagle.mixsdk.sdk.dialog.GameNoticeDialog;
import com.eagle.mixsdk.sdk.impl.listeners.ISDKOrderIDListener;
import com.eagle.mixsdk.sdk.listeners.EagleConfigResultListener;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.manger.EagleLoopDomainManager;
import com.eagle.mixsdk.sdk.okhttp.model.DomainBean;
import com.eagle.mixsdk.sdk.plugin.EagleBugly;
import com.eagle.mixsdk.sdk.plugin.EagleESLiangPlugin;
import com.eagle.mixsdk.sdk.plugin.EagleIDot;
import com.eagle.mixsdk.sdk.plugin.EaglePay;
import com.eagle.mixsdk.sdk.plugin.EaglePush;
import com.eagle.mixsdk.sdk.plugin.EagleUpdate;
import com.eagle.mixsdk.sdk.plugin.EagleUser;
import com.eagle.mixsdk.sdk.utils.ActivityManager;
import com.eagle.mixsdk.sdk.utils.MetaInfUtils;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import com.eagle.mixsdk.sdk.utils.domain.DomainsCacheUtil;
import com.eagle.mixsdk.sdk.verify.EagleInitConfig;
import com.eagle.mixsdk.sdk.verify.EagleProxy;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.eagle.mixsdk.support.event.EagleEvent;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.reflect.Factory;

public class EagleSDK extends DispatchEagleEvent {
    private static final String APP_GAME_NAME = "Eagle_Game_Application";
    private static final String APP_PROXY_NAME = "Eagle_APPLICATION_PROXY_NAME";
    private static final String DEFAULT_PKG_NAME = "com.eagle.mixsdk.sdk";
    public static final String USERID_KEY = "userIdKey";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static volatile EagleSDK instance;
    private List<IActivityCallback> activityCallbacks = new ArrayList(1);
    private Application application;
    /* access modifiers changed from: private */
    public Activity context;
    private SDKParams developInfo;
    private List<DomainBean> domainsList = new ArrayList();
    private String idotUrl = "";
    private PayParams mPayParams = null;
    private Bundle metaData;
    private List<ISDKOrderIDListener> orderListener = new ArrayList();
    private List<IApplicationListener> proxyApplicationListeners = new ArrayList(2);

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("EagleSDK.java", EagleSDK.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig("2", "onProxyCreate", "com.eagle.mixsdk.sdk.EagleSDK", "", "", "", "void"), 417);
    }

    private EagleSDK() {
    }

    public static EagleSDK getInstance() {
        if (instance == null) {
            synchronized (EagleSDK.class) {
                if (instance == null) {
                    instance = new EagleSDK();
                }
            }
        }
        return instance;
    }

    public SDKParams getSDKParams() {
        return this.developInfo;
    }

    public Bundle getMetaData() {
        return this.metaData;
    }

    public String getSDKVersion() {
        return Constants.VERSIONNAME;
    }

    public PayParams getPayParams() {
        return this.mPayParams;
    }

    public void setPayParams(PayParams data) {
        this.mPayParams = data;
    }

    private boolean isDevelopInfoInvalid(String key) {
        return this.developInfo == null || !this.developInfo.contains(key);
    }

    public Application getApplication() {
        return this.application;
    }

    public EagleToken getUToken() {
        return EagleUser.getInstance().getEagleToken();
    }

    public String getEagleUid() {
        EagleToken uToken = EagleUser.getInstance().getEagleToken();
        return uToken != null ? "" + uToken.getUserID() : "";
    }

    public String getChannelUid() {
        EagleToken uToken = EagleUser.getInstance().getEagleToken();
        return uToken != null ? "" + uToken.getSdkUserID() : "";
    }

    public int getCurrChannel() {
        if (isDevelopInfoInvalid("Eagle_Channel")) {
            return 0;
        }
        return this.developInfo.getInt("Eagle_Channel");
    }

    public int getSubChannel() {
        return MetaInfUtils.getInstance().getSubChannelID();
    }

    public String getExtChannel() {
        return MetaInfUtils.getInstance().getExtChannel();
    }

    public boolean isNeedToUpdate() {
        if (isDevelopInfoInvalid("Eagle_Update_Switch")) {
            return false;
        }
        return this.developInfo.getBoolean("Eagle_Update_Switch").booleanValue();
    }

    public String getActiveUrl() {
        if (isDevelopInfoInvalid("Eagle_ACTIVE_URL")) {
            return "";
        }
        return this.developInfo.getString("Eagle_ACTIVE_URL");
    }

    public int getAppID() {
        if (isDevelopInfoInvalid("Eagle_APPID")) {
            return 0;
        }
        return this.developInfo.getInt("Eagle_APPID");
    }

    public String getAppKey() {
        if (isDevelopInfoInvalid("Eagle_APPKEY")) {
            return "";
        }
        return this.developInfo.getString("Eagle_APPKEY");
    }

    public String getPayPrivateKey() {
        if (isDevelopInfoInvalid("Eagle_PAY_PRIVATEKEY")) {
            return "";
        }
        return this.developInfo.getString("Eagle_PAY_PRIVATEKEY");
    }

    public String getIDotUrl() {
        if (isDevelopInfoInvalid("Eagle_IDot_Url")) {
            return "";
        }
        return this.developInfo.getString("Eagle_IDot_Url");
    }

    public String getEagleServerURL() {
        if (isDevelopInfoInvalid("Eagle_SERVER_URL")) {
            return null;
        }
        String url = this.developInfo.getString("Eagle_SERVER_URL");
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }

    public void loadConfigToEagleBackUpServerURL() {
        getDomainsList();
        String currentDomainHostname = DomainsCacheUtil.getCurrentCacheDomainHostname();
        boolean isExist = false;
        if (!CheckUtils.isNullOrEmpty(currentDomainHostname)) {
            DomainBean tempDomainBean = null;
            int i = 0;
            while (true) {
                if (i >= this.domainsList.size()) {
                    break;
                }
                DomainBean domainBean = this.domainsList.get(i);
                if (currentDomainHostname.equals(domainBean.getDomainUrl())) {
                    if (i != 0) {
                        tempDomainBean = domainBean;
                        this.domainsList.remove(domainBean);
                    }
                    isExist = true;
                } else {
                    i++;
                }
            }
            if (tempDomainBean != null) {
                this.domainsList.add(0, tempDomainBean);
            }
        }
        if (!isExist) {
            System.out.println("============【[没有可用域名]或者[域名集合信息中不包含]当前的地址，需要更新缓存中的域名信息】============");
            DomainsCacheUtil.saveNewDomain(this.domainsList.get(0).getDomainUrl());
        }
    }

    public List<DomainBean> getDomainsList() {
        if (this.domainsList.size() == 0) {
            for (String hostname : getConfigFileDomainsList()) {
                this.domainsList.add(new DomainBean(true, hostname));
            }
        }
        return this.domainsList;
    }

    private List<String> getConfigFileDomainsList() {
        List<String> urls;
        List<String> listDomain = new ArrayList<>();
        if (!isDevelopInfoInvalid("EAGLE_BACK_UP_SERVER_URL") && (urls = DomainsCacheUtil.disposeServerUrl(this.developInfo.getString("EAGLE_BACK_UP_SERVER_URL"))) != null) {
            listDomain.addAll(urls);
        }
        String eagleServerURL = getEagleServerURL();
        if (!TextUtils.isEmpty(eagleServerURL) && eagleServerURL.startsWith("http")) {
            listDomain.add(0, eagleServerURL);
        }
        return listDomain;
    }

    public boolean isSDKShowSplash() {
        if (isDevelopInfoInvalid("Eagle_SDK_SHOW_SPLASH")) {
            return false;
        }
        return "true".equalsIgnoreCase(this.developInfo.getString("Eagle_SDK_SHOW_SPLASH"));
    }

    public String getSDKVersionCode() {
        if (isDevelopInfoInvalid("Eagle_SDK_VERSION_CODE")) {
            return "";
        }
        return this.developInfo.getString("Eagle_SDK_VERSION_CODE");
    }

    @Deprecated
    public void setSDKListener(IEagleSDKListener listener) {
        register(EagleEvent.create(listener));
    }

    public void setSDKOderIDListener(ISDKOrderIDListener listener) {
        if (!this.orderListener.contains(listener) && listener != null) {
            this.orderListener.add(listener);
        }
    }

    public void setActivityCallback(IActivityCallback callback) {
        if (!this.activityCallbacks.contains(callback) && callback != null) {
            this.activityCallbacks.add(callback);
        }
    }

    public List<IApplicationListener> getProxyApplicationListeners() {
        return this.proxyApplicationListeners;
    }

    public void onAppCreate(Application application2) {
        if (CommonUtil.isMainProcess(application2)) {
            this.application = application2;
            LanguageUtil.getInstance().setLanguage2PropertiesFile(application2, "eagle_developer_config.properties");
            LanguageUtil.getInstance().setLanguageChange(application2);
            ResManager.getInstance().init(application2, BuildConfig.RES_APK_NAME, BuildConfig.RES_PACKAGE_NAME);
            ActivityManager.getInstance().registerActivityLifecycleCallbacks(application2);
            onProxyCreate();
        }
    }

    private void onProxyCreate() {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this);
        try {
            for (IApplicationListener lis : this.proxyApplicationListeners) {
                lis.onProxyCreate();
            }
        } finally {
            TrackAspect.aspectOf().onApplicationCreate(makeJP);
        }
    }

    public void loadLanguageConfig(Context context2) {
        LanguageUtil.getInstance().loadConfig(context2, "eagle_developer_config.properties");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00ae, code lost:
        r0 = r11.metaData.getString(APP_GAME_NAME);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAppAttachBaseContext(android.app.Application r12, android.content.Context r13) {
        /*
            r11 = this;
            boolean r6 = com.doraemon.p027eg.CommonUtil.isMainProcess(r12)
            if (r6 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            r11.application = r12
            com.doraemon.util.Utils.init((android.app.Application) r12)
            android.support.multidex.MultiDex.install(r13)
            com.eagle.mixsdk.sdk.log.Log.init(r13)
            com.eagle.mixsdk.sdk.utils.MetaInfUtils r6 = com.eagle.mixsdk.sdk.utils.MetaInfUtils.getInstance()
            r6.initChannelContent(r13)
            java.util.List<com.eagle.mixsdk.sdk.IApplicationListener> r6 = r11.proxyApplicationListeners
            r6.clear()
            com.eagle.mixsdk.sdk.base.PluginFactory r6 = com.eagle.mixsdk.sdk.base.PluginFactory.getInstance()
            r6.loadPluginInfo(r13)
            com.eagle.mixsdk.sdk.base.PluginFactory r6 = com.eagle.mixsdk.sdk.base.PluginFactory.getInstance()
            com.eagle.mixsdk.sdk.SDKParams r6 = r6.getSDKParams(r13)
            r11.developInfo = r6
            r11.loadConfigToEagleBackUpServerURL()
            com.eagle.mixsdk.sdk.base.PluginFactory r6 = com.eagle.mixsdk.sdk.base.PluginFactory.getInstance()
            android.os.Bundle r6 = r6.getMetaData(r13)
            r11.metaData = r6
            android.os.Bundle r6 = r11.metaData
            java.lang.String r7 = "Eagle_APPLICATION_PROXY_NAME"
            boolean r6 = r6.containsKey(r7)
            if (r6 == 0) goto L_0x00a4
            android.os.Bundle r6 = r11.metaData
            java.lang.String r7 = "Eagle_APPLICATION_PROXY_NAME"
            java.lang.String r4 = r6.getString(r7)
            java.lang.String r6 = "EagleSDK"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "proxyAppNames:"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r4)
            java.lang.String r7 = r7.toString()
            com.eagle.mixsdk.sdk.log.Log.m598d(r6, r7)
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 != 0) goto L_0x00a4
            java.lang.String r6 = ","
            java.lang.String[] r5 = r4.split(r6)
            int r7 = r5.length
            r6 = 0
        L_0x0074:
            if (r6 >= r7) goto L_0x00a4
            r3 = r5[r6]
            boolean r8 = android.text.TextUtils.isEmpty(r3)
            if (r8 != 0) goto L_0x00a1
            java.lang.String r8 = "EagleSDK"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "add a new application listener:"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r3)
            java.lang.String r9 = r9.toString()
            com.eagle.mixsdk.sdk.log.Log.m598d(r8, r9)
            com.eagle.mixsdk.sdk.IApplicationListener r2 = r11.newApplicationInstance(r12, r3)
            if (r2 == 0) goto L_0x00a1
            java.util.List<com.eagle.mixsdk.sdk.IApplicationListener> r8 = r11.proxyApplicationListeners
            r8.add(r2)
        L_0x00a1:
            int r6 = r6 + 1
            goto L_0x0074
        L_0x00a4:
            android.os.Bundle r6 = r11.metaData
            java.lang.String r7 = "Eagle_Game_Application"
            boolean r6 = r6.containsKey(r7)
            if (r6 == 0) goto L_0x00d9
            android.os.Bundle r6 = r11.metaData
            java.lang.String r7 = "Eagle_Game_Application"
            java.lang.String r0 = r6.getString(r7)
            com.eagle.mixsdk.sdk.IApplicationListener r2 = r11.newApplicationInstance(r12, r0)
            if (r2 == 0) goto L_0x00d9
            java.lang.String r6 = "EagleSDK"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "add a game application listener:"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            com.eagle.mixsdk.sdk.log.Log.m599e(r6, r7)
            java.util.List<com.eagle.mixsdk.sdk.IApplicationListener> r6 = r11.proxyApplicationListeners
            r6.add(r2)
        L_0x00d9:
            java.util.List<com.eagle.mixsdk.sdk.IApplicationListener> r6 = r11.proxyApplicationListeners
            java.util.Iterator r6 = r6.iterator()
        L_0x00df:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0006
            java.lang.Object r1 = r6.next()
            com.eagle.mixsdk.sdk.IApplicationListener r1 = (com.eagle.mixsdk.sdk.IApplicationListener) r1
            r1.onProxyAttachBaseContext(r13)
            goto L_0x00df
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.sdk.EagleSDK.onAppAttachBaseContext(android.app.Application, android.content.Context):void");
    }

    public void onAppConfigurationChanged(Application application2, Configuration newConfig) {
        if (CommonUtil.isMainProcess(application2)) {
            LanguageUtil.getInstance().setLanguageChange(application2);
            for (IApplicationListener lis : this.proxyApplicationListeners) {
                lis.onProxyConfigurationChanged(newConfig);
            }
        }
    }

    public void onTerminate() {
        if (CommonUtil.isMainProcess(this.application)) {
            for (IApplicationListener lis : this.proxyApplicationListeners) {
                lis.onProxyTerminate();
            }
            Log.destory();
        }
    }

    private IApplicationListener newApplicationInstance(Application application2, String proxyAppName) {
        if (proxyAppName == null || SDKTools.isNullOrEmpty(proxyAppName)) {
            return null;
        }
        if (proxyAppName.startsWith(".")) {
            proxyAppName = "com.eagle.mixsdk.sdk" + proxyAppName;
        }
        try {
            return (IApplicationListener) Class.forName(proxyAppName).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public void init(Activity context2) {
        this.context = context2;
        EagleUser.getInstance().init();
        onCreate();
        initPlugin();
    }

    public void onInitSuccess() {
        super.onInitSuccess();
        EagleProxy.initConfig(new EagleConfigResultListener() {
            public void onResult(int code, EagleInitConfig initConfig) {
                if (code != 1) {
                    EagleSDK.getInstance().onEagleInitFail(initConfig.getErrorMsg());
                    return;
                }
                if (initConfig != null) {
                    if (initConfig.isDebugEnvironment()) {
                        ToastUtils.showShort((CharSequence) ResPluginUtil.getStringByName("xeagle_test_environment"));
                    }
                    if (initConfig.isForbidEnterGame()) {
                        EagleSDK.this.showForbidTipsDialog(initConfig.getBanMsg());
                        Log.m599e(Constants.TAG, "渠道归属错误");
                        EagleSDK.getInstance().trackEagleInitFail(initConfig.getErrorMsg());
                        return;
                    } else if (initConfig.isVmc()) {
                        if (CommonUtil.isEmulator(EagleSDK.this.context == null ? EagleSDK.this.getApplication() : EagleSDK.this.context)) {
                            EagleSDK.this.showForbidTipsDialog(initConfig.getVmcMsg());
                            EagleSDK.getInstance().trackEagleInitFail(initConfig.getErrorMsg());
                            return;
                        }
                    }
                }
                EagleSDK.getInstance().onEagleInitSuc();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showForbidTipsDialog(String tips) {
        if (!TextUtils.isEmpty(tips)) {
            GameNoticeDialog dialog = new GameNoticeDialog(this.context == null ? getApplication() : this.context);
            dialog.setNoticeContent(tips);
            dialog.setPositiveListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EagleSDK.this.exitGame();
                }
            });
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    private void initPlugin() {
        try {
            EagleESLiangPlugin.getInstance().init();
            EagleBugly.getInstance();
            EaglePay.getInstance().init();
            if (isNeedToUpdate()) {
                EagleUpdate.getInstance().init(this.context);
            }
            EaglePush.getInstance().init();
            EagleIDot.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContext(Activity context2) {
        this.context = context2;
    }

    public Activity getContext() {
        return this.context;
    }

    @Deprecated
    public List<IEagleSDKListener> getEagleSDKListener() {
        return new ArrayList();
    }

    @Deprecated
    public void onSwitchAccount() {
    }

    public void onSwitchAccount(String result) {
        EagleUser.getInstance().startAuthTask(result, true);
    }

    public void onLoginResult(String result) {
        EagleUser.getInstance().startAuthTask(result, false);
    }

    public void onLogout() {
        super.onLogout();
        clearUserInfo();
    }

    public void clearUserInfo() {
        EagleUser.getInstance().clearEagleToken();
    }

    public void onLoginFail(String msg) {
        super.onLoginFail(msg);
    }

    public void onLoginSuccess(EagleToken token) {
        super.onLoginSuccess(token);
    }

    public void onSwitchAccount(EagleToken token) {
        super.onSwitchAccount(token);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void onBackPressed() {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onBackPressed();
            }
        }
    }

    public void onCreate() {
        Log.m602w(Constants.TAG, "----------onCreate-------------");
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onCreate();
            }
        }
    }

    public void onStart() {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onStart();
            }
        }
    }

    public void onPause() {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onPause();
            }
            EagleLoopDomainManager.getInstance().dismissLoadingDialog();
        }
    }

    public void onResume() {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onResume();
            }
        }
    }

    public void onNewIntent(Intent newIntent) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onNewIntent(newIntent);
            }
        }
    }

    public void onStop() {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onStop();
            }
        }
    }

    public void onDestroy() {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onDestroy();
            }
        }
    }

    public void onRestart() {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onRestart();
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onConfigurationChanged(newConfig);
            }
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onRequestPermissionResult(requestCode, permissions, grantResults);
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onSaveInstanceState(outState);
            }
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onRestoreInstanceState(savedInstanceState);
            }
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onWindowFocusChanged(hasFocus);
            }
        }
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        if (this.activityCallbacks != null) {
            for (IActivityCallback callback : this.activityCallbacks) {
                callback.onWindowAttributesChanged(params);
            }
        }
    }

    public void exitDialog() {
        exitDialog(new ExitDialog.OnClickListener() {
            public void onClick(int which) {
                if (which == -1) {
                    EagleSDK.this.exitGame();
                }
            }
        });
    }

    public void exitDialog(ExitDialog.OnClickListener listener) {
        if (this.context == null) {
            System.out.println("u must init first!!");
        } else {
            new ExitDialog(this.context, listener).show();
        }
    }

    public void exitGame() {
        ActivityManager.getInstance().exitGame();
    }
}
