package com.xhuyu.component.core.manager;

import com.star.libtrack.core.TrackCore;
import com.xhuyu.component.core.manager.track.GooglePayTrack;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.model.LoginResult;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import com.xsdk.doraemon.utils.CheckUtil;
import java.util.HashMap;
import java.util.Map;

public class TrackEventManager {
    private static TrackEventManager mInstance;

    private TrackEventManager() {
        SDKEventBus.getDefault().register(this);
        new GooglePayTrack().init();
    }

    public static TrackEventManager getInstance() {
        if (mInstance == null) {
            synchronized (TrackEventManager.class) {
                if (mInstance == null) {
                    mInstance = new TrackEventManager();
                }
            }
        }
        return mInstance;
    }

    public void init() {
    }

    @Subscribe(event = {8216})
    public static void trackOneClickRegistration() {
        Map<String, Object> ext = new HashMap<>();
        ext.put("page", "csdk_login");
        TrackCore.getInstance().submitCustomEvent("oneclick_registration", ext);
    }

    @Subscribe(event = {8193})
    public void trackStartupAppEvent() {
        HuYuUser userCache = UserManager.getInstance().getUserCache();
        if (userCache != null && !CheckUtil.isEmpty(userCache.getUid() + "")) {
            TrackCore.getInstance().setUid(userCache.getUid() + "");
        }
        TrackCore.getInstance().submitStartupEvent();
    }

    @Subscribe(event = {8192})
    public void trackInitializeEvent() {
        Map<String, Object> ext = new HashMap<>();
        ext.put("page", "csdk_initialize");
        TrackCore.getInstance().submitCustomEvent("initialize", ext);
    }

    @Subscribe(event = {8215})
    public void trackDuplicateResultEvent(int enterType) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("enter_type", Integer.valueOf(enterType));
        ext.put("page", "csdk_initialize");
        TrackCore.getInstance().submitCustomEvent("duplicate", ext);
    }

    @Subscribe(event = {8194})
    public void trackStartLoginEvent(int loginType) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("login_type", Integer.valueOf(loginType));
        ext.put("page", "csdk_login");
        TrackCore.getInstance().submitCustomEvent("start_login", ext);
    }

    @Subscribe(event = {3})
    public void trackLoginResult(LoginResult result) {
        String action;
        if (result != null) {
            Map<String, Object> ext = new HashMap<>();
            ext.put("page", "csdk_login");
            ext.put("login_type", Integer.valueOf(result.getLoginType()));
            if (result.getResultCode() == 2) {
                action = "login_cancle";
            } else {
                ext.put("result", Integer.valueOf(convertResultCode(result.getResultCode())));
                ext.put("fail_detail", convertFailedMsg(result.getResultCode(), result.getData()));
                action = "sdk_login";
            }
            TrackCore.getInstance().submitCustomEvent(action, ext);
        }
    }

    @Subscribe(event = {6})
    public void trackLogoutResult() {
        Map<String, Object> ext = new HashMap<>();
        ext.put("page", "csdk_login");
        TrackCore.getInstance().submitCustomEvent("sdk_logout", ext);
    }

    public void trackPayment(int payType, long price, String orderID) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("page", "csdk_pay");
        ext.put("payment", Integer.valueOf(payType));
        ext.put("pay_amount", Long.valueOf(price));
        ext.put("pay_scene", 1);
        ext.put("selfsdk_order_id", orderID);
        TrackCore.getInstance().submitCustomEvent("pay", ext);
    }

    @Subscribe(event = {8195})
    public void trackStartOrders(String roleID, String serverID, String productID) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("page", "csdk_pay");
        ext.put("role_id", roleID);
        ext.put("server_id", serverID);
        ext.put("pay_scene", 1);
        ext.put("product_id", productID);
        TrackCore.getInstance().submitCustomEvent("start_order", ext);
    }

    @Subscribe(event = {8209})
    public void trackOrdersResult(int state, String sdkOrderID) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("page", "csdk_pay");
        ext.put("result", Integer.valueOf(state));
        ext.put("selfsdk_order_id", sdkOrderID);
        TrackCore.getInstance().submitCustomEvent("order_to_c", ext);
    }

    @Subscribe(event = {8197})
    public void trackGameInfo() {
        Map<String, Object> ext = new HashMap<>();
        ext.put("page", "csdk_login");
        TrackCore.getInstance().submitCustomEvent("enter_game", ext);
    }

    private int convertResultCode(int srcResultCode) {
        if (srcResultCode == 1) {
            return srcResultCode;
        }
        return 0;
    }

    private String convertFailedMsg(int srcResultCode, String msg) {
        return srcResultCode == 0 ? msg : "";
    }
}
