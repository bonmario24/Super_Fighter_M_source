package com.xhuyu.component.core.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.star.libtrack.core.TrackCore;
import com.xhuyu.component.BuildConfig;
import com.xhuyu.component.core.config.Constant;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.config.SuperTool;
import com.xhuyu.component.core.config.TrackEventKey;
import com.xhuyu.component.core.manager.AppsFlyerManager;
import com.xhuyu.component.core.manager.FacebookShareManager;
import com.xhuyu.component.core.manager.FloatWindowManager;
import com.xhuyu.component.core.manager.NetWorkManager;
import com.xhuyu.component.core.manager.NoticeManager;
import com.xhuyu.component.core.manager.TrackEventManager;
import com.xhuyu.component.core.manager.UserHistoryDbManager;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.presenter.login.LoginPresenterControl;
import com.xhuyu.component.p036ui.activity.LoginActivity;
import com.xhuyu.component.p036ui.activity.MarketingActivity;
import com.xhuyu.component.p036ui.activity.PaymentActivity;
import com.xhuyu.component.p036ui.activity.UserCenterActivity;
import com.xhuyu.component.utils.ActivityUtil;
import com.xhuyu.component.utils.appsflyer.AppsFlyerTrackUtil;
import com.xhuyu.component.utils.third.FacebookLoginUtil;
import com.xhuyu.component.utils.third.GoogleLoginUtil;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.FastClickUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.HashMap;
import java.util.Map;

public class HuYuApi {
    private static HuYuApi mInstance;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    private HuYuApi() {
    }

    public static HuYuApi getInstance() {
        if (mInstance == null) {
            synchronized (HuYuApi.class) {
                if (mInstance == null) {
                    mInstance = new HuYuApi();
                }
            }
        }
        return mInstance;
    }

    /* access modifiers changed from: package-private */
    public void initToApplication(Application application) {
        TrackEventManager.getInstance().init();
        ContextUtil.getInstance().setApplication(application);
        UserHistoryDbManager.getInstance().initDatabase(application);
        SDKConfig.getInstance().initEnvironment(application);
        doInit(application);
    }

    private void doInit(Application application) {
        SDKConfig.getInstance().addConfig(Constant.HuYuConfigKey.CURRENT_LANGUAGE_KEY, ReflectResource.getInstance(application).getString("xf_lan"));
        NetWorkManager.getInstance().init(application.getApplicationContext());
        initTrack(application);
        AppsFlyerManager.getInstance().init(application.getApplicationContext(), SDKConfig.getInstance().getConfigValue(Constant.HuYuConfigKey.APPSFLYER_DEV_KEY));
    }

    private void initTrack(Context context) {
        String ladderHost = SDKConfig.getInstance().getConfigValue(Constant.LadderConfigKey.Ladder_Host_KEY);
        if (CheckUtil.isEmpty(ladderHost)) {
            SDKLoggerUtil.getLogger().mo19502e("ladder host is null", new Object[0]);
            return;
        }
        String channel = SDKConfig.getInstance().getConfigValue(Constant.HuYuConfigKey.CHANNEL_KEY);
        String ladderWho = SDKConfig.getInstance().getConfigValue(Constant.LadderConfigKey.Ladder_Who_KEY);
        String ladderSalt = SDKConfig.getInstance().getConfigValue(Constant.LadderConfigKey.Ladder_Salt_KEY);
        String gameID = SDKConfig.getInstance().getConfigValue(Constant.HuYuConfigKey.GAME_ID_KEY);
        if (CheckUtil.checkTextEmpty(channel, ladderWho, ladderSalt, gameID)) {
            SDKLoggerUtil.getLogger().mo19502e("%s config error", SDKConfig.SDK_CONFIG_FILE_NAME);
            return;
        }
        SDKLoggerUtil.getLogger().mo19501d(ladderHost.contains("applog.test") ? "Ladder：Testing Environment " : "Ladder： Production Environment ", new Object[0]);
        TrackCore.getInstance().init(context, BuildConfig.VERSION_NAME, channel, gameID, SDKConfig.getInstance().isDebugEnvironment(), ladderWho, ladderHost, ladderSalt);
        SDKEventPost.postTrack(TrackEventKey.ON_STARTUP_APP, new Object[0]);
    }

    public void initSDK(Activity activity) {
        initSDK(activity, false);
    }

    public void initSDK(Activity activity, boolean isSilentLogin) {
        ContextUtil.getInstance().setGameMainActivity(activity);
        TrackCore.getInstance().initPageTrack(activity);
        SDKEventPost.postTrack(8192, new Object[0]);
        NoticeManager.getInstance().initNotice(activity);
        ActivityUtil.init(activity);
        if (!isSilentLogin) {
            FloatWindowManager.getInstance().init(activity);
        }
    }

    public void doExitGame(Activity activity) {
        if (!isFastClick("doExitGame")) {
            ContextUtil.getInstance().setGameMainActivity(activity);
            SDKEventPost.post(9, new Object[0]);
        }
    }

    public void doPayment(Activity activity, HashMap<String, Object> payInfo) {
        if (!isFastClick("doPayment")) {
            PaymentActivity.start(activity, payInfo);
        }
    }

    public void doSilentLogin() {
        if (!isFastClick("doSilentLogin")) {
            LoginPresenterControl.visitorLogin(true, (GameSDKListener) null);
        }
    }

    public void deleteVisitorUser() {
        UserManager.getInstance().deleteVisitorUser();
    }

    public void doLoginDialog(Activity activity) {
        if (!isFastClick("doLoginDialog")) {
            LoginActivity.start(activity);
        }
    }

    public void doMarketingView(Activity activity) {
        if (!isFastClick("doMarketingView")) {
            MarketingActivity.start(activity);
        }
    }

    public void doShare(Activity activity) {
        if (!isFastClick("doShare")) {
            FacebookShareManager.getInstance().share(activity);
        }
    }

    public void doOpenUserCenter(Activity activity) {
        if (isFastClick("doOpenUserCenter")) {
            return;
        }
        if (UserManager.getInstance().getLoginUser() != null) {
            UserCenterActivity.start(activity);
            return;
        }
        FastClickUtil.reset();
        doLoginDialog(activity);
    }

    public void doLogout() {
        if (!isFastClick("doLogout")) {
            UserManager.getInstance().setLoginStatus(false);
            FacebookLoginUtil.getInstance().logout();
            GoogleLoginUtil.getInstance().signOut();
            FastClickUtil.reset();
            SDKEventPost.post(6, new Object[0]);
            TrackCore.getInstance().setUid("");
        }
    }

    public void doPostTrackGame() {
        SDKEventPost.postTrack(TrackEventKey.ON_TRACK_GAME_INFO, new Object[0]);
    }

    private boolean isFastClick(String tag) {
        boolean fastClick = FastClickUtil.isFastClick();
        if (fastClick) {
            SDKLoggerUtil.getLogger().mo19502e("click to fast....", new Object[0]);
        } else if (!CheckUtil.isEmpty(tag)) {
            SDKLoggerUtil.getLogger().mo19504i("begin of %s ....", tag);
        }
        return fastClick;
    }

    public void trackEvent(Context context, String eventName, Map<String, Object> eventValue) {
        AppsFlyerTrackUtil.trackEvent(context, eventName, eventValue);
    }

    public void onDestroy() {
        NetWorkManager.getInstance().cancelAllRequest();
        UserHistoryDbManager.getInstance().release();
        FloatWindowManager.getInstance().release();
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (SuperTool.getInstance().getActivityResultType()) {
            case 3:
                FacebookShareManager.getInstance().onActivityResult(requestCode, resultCode, data);
                return;
            case 4:
                NoticeManager.getInstance().onActivityResult(requestCode, resultCode, data);
                return;
            default:
                return;
        }
    }

    public void onResume() {
        if (UserManager.getInstance().getLoginUser() != null) {
            FloatWindowManager.getInstance().show();
        }
    }

    public void onPause() {
        FacebookShareManager.getInstance().onPause();
        FloatWindowManager.getInstance().destroy();
    }
}
