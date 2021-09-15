package com.eagle.mixsdk.sdk;

import com.eagle.mixsdk.sdk.lifecycle.ActivityLifecycleAdapter;
import com.eagle.mixsdk.sdk.listeners.EagleInvokeListener;
import com.eagle.mixsdk.sdk.plugin.EagleExtPlugin;
import com.eagle.mixsdk.sdk.presenter.EagleExtPluginImpl;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xhuyu.component.core.api.HuYuApi;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.LoginResult;
import com.xhuyu.component.mvp.model.PaymentResult;
import com.xhuyu.component.mvp.model.ShareResult;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class HuYuSDK {
    private static HuYuSDK instance = new HuYuSDK();
    private boolean hasLogin;
    private boolean isInit;

    private HuYuSDK() {
        EagleSDK.getInstance().setActivityCallback(new ActivityLifecycleAdapter());
        SDKEventBus.getDefault().register(this);
        EagleExtPlugin.getInstance().registerExtPlugins(new EagleExtPluginImpl(), EagleExtPluginImpl.EXT_PLUGIN_FACEBOOK_SHARE, EagleExtPluginImpl.EXT_PLUGIN_OPEN_MARKETING);
    }

    public static HuYuSDK getInstance() {
        return instance;
    }

    public void initSDK() {
        if (!this.isInit) {
            this.isInit = true;
            HuYuApi.getInstance().initSDK(EagleSDK.getInstance().getContext());
            EagleSDK.getInstance().onResult(1, "init success");
            SDKLoggerUtil.getLogger().mo19501d("初始化成功", new Object[0]);
        }
    }

    @Subscribe(event = {7})
    public void onExitApp() {
        SDKLoggerUtil.getLogger().mo19501d("退出应用", new Object[0]);
        EagleSDK.getInstance().exitGame();
    }

    @Subscribe(event = {6})
    public void onLogoutResult() {
        SDKLoggerUtil.getLogger().mo19501d("注销成功", new Object[0]);
        this.hasLogin = false;
        EagleSDK.getInstance().onLogout();
    }

    @Subscribe(event = {3})
    public void onLoginResult(LoginResult result) {
        String msg = result.getData();
        switch (result.getResultCode()) {
            case 1:
                SDKLoggerUtil.getLogger().mo19501d("登录%s类型-> 登录成功 msg:" + result.getData(), Integer.valueOf(result.getLoginType()));
                try {
                    encodeLoginResult(new JSONObject(result.getData()));
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            default:
                SDKLoggerUtil.getLogger().mo19502e("登录%s类型-> 登录失败 msg:" + result.getData(), Integer.valueOf(result.getLoginType()));
                EagleSDK.getInstance().onResult(5, msg);
                return;
        }
    }

    private void encodeLoginResult(JSONObject message) {
        try {
            JSONObject json = new JSONObject();
            json.put("access_token", message.optString("access_token"));
            json.put("uid", message.optString("uid"));
            EagleSDK.getInstance().onLoginResult(json.toString());
            this.hasLogin = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(event = {8})
    public void onShareResultResult(ShareResult shareResult) {
        switch (shareResult.getResultCode()) {
            case 1:
                SDKLoggerUtil.getLogger().mo19501d("分享成功 PostId:" + shareResult.getPostId(), new Object[0]);
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("shareResult", shareResult.getPostId());
                EagleExtPlugin.getInstance().onInvokeSuccess(EagleExtPluginImpl.EXT_PLUGIN_FACEBOOK_SHARE, resultMap, (EagleInvokeListener.CallbackListener) null);
                return;
            case 2:
                SDKLoggerUtil.getLogger().mo19502e("分享取消", new Object[0]);
                EagleExtPlugin.getInstance().onInvokeFailed(EagleExtPluginImpl.EXT_PLUGIN_FACEBOOK_SHARE, "cancel");
                return;
            default:
                SDKLoggerUtil.getLogger().mo19502e("分享失败", new Object[0]);
                EagleExtPlugin.getInstance().onInvokeFailed(EagleExtPluginImpl.EXT_PLUGIN_FACEBOOK_SHARE, shareResult.getFailureMsg());
                return;
        }
    }

    @Subscribe(event = {5})
    public void onPayMentResult(PaymentResult result) {
        String message = result.getMessage();
        switch (result.getResultCode()) {
            case 1:
                SDKLoggerUtil.getLogger().mo19501d("支付成功 msg:" + result.getMessage(), new Object[0]);
                EagleSDK.getInstance().onResult(10, "pay success");
                return;
            default:
                SDKLoggerUtil.getLogger().mo19502e("支付失败 msg:" + result.getMessage(), new Object[0]);
                EagleSDK.getInstance().onResult(11, "pay failed.");
                return;
        }
    }

    public void login() {
        Boolean isSilentLogin;
        SDKParams sdkParams = EagleSDK.getInstance().getSDKParams();
        if (sdkParams == null || (isSilentLogin = sdkParams.getBoolean("Silent_Login")) == null || !isSilentLogin.booleanValue()) {
            HuYuApi.getInstance().doLoginDialog(EagleSDK.getInstance().getContext());
        } else {
            HuYuApi.getInstance().doSilentLogin();
        }
    }

    public boolean isLogin() {
        return this.hasLogin;
    }

    public void submitExtraData(UserExtraData data) {
        if (data != null && 3 == data.getDataType()) {
            HuYuApi.getInstance().doPostTrackGame();
        }
    }

    public void logout() {
        SDKLoggerUtil.getLogger().mo19501d("登出", new Object[0]);
        HuYuApi.getInstance().doLogout();
    }

    public void exitSDK() {
        SDKLoggerUtil.getLogger().mo19501d("退出", new Object[0]);
        HuYuApi.getInstance().doExitGame(EagleSDK.getInstance().getContext());
    }

    public void pay(PayParams data) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("cp_order_id", data.getOrderID());
            hashMap.put("product_id", data.getProductId());
            hashMap.put("product_name", data.getProductName());
            hashMap.put("game_server_name", data.getServerName());
            hashMap.put("game_player_name", data.getRoleName());
            hashMap.put("pay_notify_url", data.getPayNotifyUrl());
            hashMap.put("game_server_id", data.getServerId());
            hashMap.put("game_player_id", data.getRoleId());
            hashMap.put("cp_extension", data.getExtension());
            hashMap.put(FirebaseAnalytics.Param.CURRENCY, data.getCurrency());
            double money = 0.0d;
            try {
                money = Double.parseDouble(data.getMoney());
            } catch (Exception e) {
                e.printStackTrace();
            }
            hashMap.put("product_price", Double.valueOf(money));
            hashMap.put("game_role_level", data.getRoleLevel() + "");
            HuYuApi.getInstance().doPayment(EagleSDK.getInstance().getContext(), hashMap);
        } catch (Exception e2) {
            SDKLoggerUtil.getLogger().mo19502e("支付失败", new Object[0]);
            EagleSDK.getInstance().onResult(11, "pay failed");
            e2.printStackTrace();
        }
    }

    public boolean showAccountCenter() {
        HuYuApi.getInstance().doOpenUserCenter(EagleSDK.getInstance().getContext());
        return UserManager.getInstance().getLoginUser() != null;
    }
}
