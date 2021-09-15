package com.eagle.mixsdk.track;

import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerProperties;
import com.doraemon.devices.AdvertisingIdHelper;
import com.doraemon.devices.Identifier;
import com.doraemon.devices.IdentifierObserver;
import com.doraemon.devices.MsaSdkHelper;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.p027eg.DateUtils;
import com.doraemon.p027eg.SPUtils;
import com.doraemon.util.AppUtils;
import com.doraemon.util.DeviceEmuCheckUtil;
import com.eagle.mixsdk.aspectj.LoginType;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.PayParams;
import com.eagle.mixsdk.sdk.UserExtraData;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import com.eagle.mixsdk.sdk.did.listener.IDIDObtainListener;
import com.eagle.mixsdk.sdk.did.utils.DeviceInfoUtil;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.utils.StoreUtils;
import com.eagle.mixsdk.sdk.verify.EagleAntiAddictionInfo;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.thinkfly.plugins.coludladder.utils.FixTimeUtils;
import com.thinkfly.star.builder.EventBuilder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class EagleTrackEvent extends TrackEvent {
    private static EagleTrackEvent instance;
    /* access modifiers changed from: private */
    public String TAG = "TrackEvent";
    private boolean isHeartbeatStarted = false;
    private String mProductId = "";
    private String mRoleId = "";
    private String mServerId = "";
    private UserExtraData mUserData;

    private EagleTrackEvent() {
    }

    public static EagleTrackEvent getInstance() {
        if (instance == null) {
            instance = new EagleTrackEvent();
        }
        return instance;
    }

    public void onInitEagle(int code, String msg) {
        Log.m602w(this.TAG, "onInitEagle code " + code + " msg " + msg);
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("eagle_init_result").put("result", Integer.valueOf(code)).put("page", "login_steps").put("fail_detail", msg).put("simulator", Integer.valueOf(CommonUtil.isEmulator(EagleSDK.getInstance().getApplication()) ? 1 : 0)).build());
        EagleTrackReport.getInstance().refresh();
    }

    public void onTrackDeviceInfo() {
        DeviceInfoUtil.getInstance().doGetDeviceInfo(EagleSDK.getInstance().getContext(), FixTimeUtils.getInstance().geServerTimeMillis(), new DeviceInfoUtil.IDeviceInfoListener() {
            public void onResult(Map<String, Object> result) {
                Log.m602w(EagleTrackEvent.this.TAG, "onTrackDeviceInfo");
                EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setExtMap(result).setAction("android_ext").put(AppsFlyerProperties.APP_ID, EagleSDK.getInstance().getAppID() + "").build());
                EagleTrackReport.getInstance().refresh();
            }
        });
    }

    public void onInitSDK(int code, String msg) {
        Log.m602w(this.TAG, "onInitSDK code " + code + " msg " + msg);
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("channel_init_result").put("result", Integer.valueOf(code)).put("page", "login_steps").put("fail_detail", msg).build());
        if (code != 1) {
            EagleTrackReport.getInstance().refresh();
        }
    }

    public void onEagleLogin(int code, String msg) {
        Log.m602w(this.TAG, "onEagleLoginResult code " + code + " msg " + msg);
        tryStopHeartbeat();
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("eagle_login_result").put("result", Integer.valueOf(code)).putString("page", "login_steps").putString("fail_detail", msg).build());
        EagleTrackReport.getInstance().reportLogin(EagleSDK.getInstance().getApplication());
    }

    private void tryStopHeartbeat() {
        if (this.isHeartbeatStarted) {
            EagleTrackReport.getInstance().stopHeartbeat();
            this.isHeartbeatStarted = false;
        }
    }

    public void onSDKLogin(int code, String msg) {
        Log.m602w(this.TAG, "onChannelLoginResult code " + code + " msg " + msg);
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("channel_login_result").put("result", Integer.valueOf(code)).putString("page", "login_steps").putString("fail_detail", msg).build());
        if (code != 1) {
            EagleTrackReport.getInstance().refresh();
        }
    }

    public void onStartLogin() {
        Log.m602w(this.TAG, "onStartLogin ");
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("game_login").putString("page", "login_steps").build());
    }

    public void onShowLoginPage() {
        Log.m602w(this.TAG, "onShowLoginPage ");
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction(LoginType.CHANNEL).putString("page", "login_steps").build());
    }

    public void onNotifyLogin() {
        Log.m602w(this.TAG, "onNotifyLogin ");
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("login_callback_result").putString("page", "login_steps").build());
    }

    public void onLogout() {
        EagleTrackReport.getInstance().reportLogout(EagleSDK.getInstance().getApplication());
        tryStopHeartbeat();
    }

    public void onGameEvent(Object object) {
        Log.m602w(this.TAG, "onGameEvent ");
        if (object != null && (object instanceof UserExtraData)) {
            UserExtraData data = (UserExtraData) object;
            switch (data.getDataType()) {
                case 2:
                case 4:
                case 5:
                    break;
                case 3:
                    this.isHeartbeatStarted = true;
                    break;
                default:
                    return;
            }
            this.mUserData = data;
            EventBuilder gameEvent = new EventBuilder.Configure().setAction("event").put("event", Integer.valueOf(data.getDataType() - 1)).putString("server_id", "" + data.getServerID()).putString("server_name", data.getServerName()).putString("role_id", data.getRoleID()).putString("role_name", data.getRoleName()).putString("role_level", data.getRoleLevel()).build();
            gameEvent.setUid(StoreUtils.getString(EagleSDK.USERID_KEY));
            EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), gameEvent);
            if (this.isHeartbeatStarted) {
                EagleTrackReport.getInstance().reportHeartbeatEvent(gameEvent);
            }
        }
    }

    public void reportPayment(Object object) {
        if (object != null && (object instanceof PayParams)) {
            PayParams data = (PayParams) object;
            EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("payment").putString("order_id", data.getOrderID()).put("money", Long.valueOf(Long.parseLong(data.getMoney()))).putString("product_id", data.getProductId()).putString("product_name", data.getProductId()).putString("server_id", data.getServerId()).putString("server_name", data.getServerName()).putString("role_id", data.getRoleId()).putString("role_name", data.getRoleName()).putString("role_level", "" + data.getRoleLevel()).build());
        }
    }

    public void onStartOrder(Object object) {
        Log.m602w(this.TAG, "onStartOrder ");
        if (object != null && (object instanceof PayParams)) {
            PayParams params = (PayParams) object;
            this.mRoleId = params.getRoleId();
            this.mProductId = params.getProductId();
            this.mServerId = params.getServerId();
        }
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("start_order").put("page", "pay_steps").put("role_id", this.mRoleId).put("server_id", this.mServerId).put("product_id", this.mProductId).build());
    }

    public void onOrderResult(int code, Object object) {
        Log.m602w(this.TAG, "onOrderResult code " + code);
        String fail_detail = "";
        String eagleOrderId = "";
        if (code == 1) {
            reportPayment(object);
            if (object != null && (object instanceof PayParams)) {
                PayParams params = (PayParams) object;
                this.mRoleId = params.getRoleId();
                this.mProductId = params.getProductId();
                this.mServerId = params.getServerId();
                eagleOrderId = params.getOrderID();
            }
        } else if (object != null && (object instanceof String)) {
            fail_detail = (String) object;
        }
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("order_result").put("page", "pay_steps").put("result", Integer.valueOf(code)).put("role_id", this.mRoleId).put("server_id", this.mServerId).put("product_id", this.mProductId).put("fail_detail", fail_detail).put("eagle_order_id", eagleOrderId).build());
    }

    public void onStartPay(Object object) {
        Log.m602w(this.TAG, "onStartPay ");
        String orderId = "";
        if (object != null && (object instanceof PayParams)) {
            PayParams params = (PayParams) object;
            this.mRoleId = params.getRoleId();
            this.mProductId = params.getProductId();
            this.mServerId = params.getServerId();
            orderId = params.getOrderID();
        }
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("start_pay").put("page", "pay_steps").put("role_id", this.mRoleId).put("server_id", this.mServerId).put("product_id", this.mProductId).put("eagle_order_id", orderId).build());
    }

    public void onPayResult(int code, Object object) {
        Log.m602w(this.TAG, "onPayResult code " + code);
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("pay_result").put("page", "pay_steps").put("result", Integer.valueOf(code)).put("role_id", this.mRoleId).put("server_id", this.mServerId).build());
    }

    public void onStartPayVerify(Object object) {
        JSONObject json = new JSONObject();
        if (object != null && (object instanceof JSONObject)) {
            json = (JSONObject) object;
            EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("start_pay_verify").put("page", "pay_steps").put("eagle_order_id", json.optString("orderID", "")).put("channel_order_id", json.optString("channelOrderID", "")).put("pay_environment", json.optString("environment", "")).build());
        }
        Log.m602w(Constants.TAG, "onStartPayVerify" + json.toString());
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPayVerifyResult(int r11, java.lang.Object r12) {
        /*
            r10 = this;
            r6 = 1
            if (r12 != 0) goto L_0x0004
        L_0x0003:
            return
        L_0x0004:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x009d }
            r3.<init>()     // Catch:{ Exception -> 0x009d }
            java.lang.String r4 = ""
            if (r11 != r6) goto L_0x00a3
            boolean r6 = r12 instanceof org.json.JSONObject     // Catch:{ Exception -> 0x009d }
            if (r6 == 0) goto L_0x0015
            r0 = r12
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ Exception -> 0x009d }
            r3 = r0
        L_0x0015:
            com.thinkfly.star.builder.EventBuilder$Configure r6 = new com.thinkfly.star.builder.EventBuilder$Configure     // Catch:{ Exception -> 0x009d }
            r6.<init>()     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = "pay_verify_result"
            com.thinkfly.star.builder.EventBuilder$Configure r6 = r6.setAction(r7)     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = "page"
            java.lang.String r8 = "pay_steps"
            com.thinkfly.star.builder.EventBuilder$Configure r6 = r6.put(r7, r8)     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = "result"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r11)     // Catch:{ Exception -> 0x009d }
            com.thinkfly.star.builder.EventBuilder$Configure r6 = r6.put(r7, r8)     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = "eagle_order_id"
            java.lang.String r8 = "orderID"
            java.lang.String r9 = ""
            java.lang.String r8 = r3.optString(r8, r9)     // Catch:{ Exception -> 0x009d }
            com.thinkfly.star.builder.EventBuilder$Configure r6 = r6.put(r7, r8)     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = "channel_order_id"
            java.lang.String r8 = "channelOrderID"
            java.lang.String r9 = ""
            java.lang.String r8 = r3.optString(r8, r9)     // Catch:{ Exception -> 0x009d }
            com.thinkfly.star.builder.EventBuilder$Configure r6 = r6.put(r7, r8)     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = "pay_environment"
            java.lang.String r8 = "environment"
            java.lang.String r9 = ""
            java.lang.String r8 = r3.optString(r8, r9)     // Catch:{ Exception -> 0x009d }
            com.thinkfly.star.builder.EventBuilder$Configure r6 = r6.put(r7, r8)     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = "fail_detail"
            com.thinkfly.star.builder.EventBuilder$Configure r6 = r6.put(r7, r4)     // Catch:{ Exception -> 0x009d }
            com.thinkfly.star.builder.EventBuilder r1 = r6.build()     // Catch:{ Exception -> 0x009d }
            com.eagle.mixsdk.track.EagleTrackReport r6 = com.eagle.mixsdk.track.EagleTrackReport.getInstance()     // Catch:{ Exception -> 0x009d }
            com.eagle.mixsdk.sdk.EagleSDK r7 = com.eagle.mixsdk.sdk.EagleSDK.getInstance()     // Catch:{ Exception -> 0x009d }
            android.app.Application r7 = r7.getApplication()     // Catch:{ Exception -> 0x009d }
            r6.reportEvent(r7, r1)     // Catch:{ Exception -> 0x009d }
            java.lang.String r6 = "EagleSDK"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009d }
            r7.<init>()     // Catch:{ Exception -> 0x009d }
            java.lang.String r8 = "onPayVerifyResult code "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x009d }
            java.lang.StringBuilder r7 = r7.append(r11)     // Catch:{ Exception -> 0x009d }
            java.lang.String r8 = "-- "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x009d }
            java.lang.String r8 = r3.toString()     // Catch:{ Exception -> 0x009d }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x009d }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x009d }
            com.eagle.mixsdk.sdk.log.Log.m602w(r6, r7)     // Catch:{ Exception -> 0x009d }
            goto L_0x0003
        L_0x009d:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0003
        L_0x00a3:
            boolean r6 = r12 instanceof java.lang.Object[]     // Catch:{ Exception -> 0x009d }
            if (r6 == 0) goto L_0x0015
            java.lang.Object[] r12 = (java.lang.Object[]) r12     // Catch:{ Exception -> 0x009d }
            r0 = r12
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch:{ Exception -> 0x009d }
            r5 = r0
            r6 = 0
            r4 = r5[r6]     // Catch:{ Exception -> 0x009d }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x009d }
            r6 = 1
            r3 = r5[r6]     // Catch:{ Exception -> 0x009d }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x009d }
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.track.EagleTrackEvent.onPayVerifyResult(int, java.lang.Object):void");
    }

    public void onStartPlayerAntiAddictionInfo() {
        Log.m602w(this.TAG, "TrackEvent->onStartPlayerAntiAddictionInfo");
        EagleToken uToken = EagleSDK.getInstance().getUToken();
        if (uToken != null) {
            EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("channel_anti_addiction_request").put("page", "anti_addiction").put("anti_addiction_switch", Integer.valueOf(uToken.getAntiAddictionSwitch())).build());
        }
    }

    public void onPlayerAntiAddictionInfoResult() {
        EagleAntiAddictionInfo adultInfo;
        Log.m602w(this.TAG, "TrackEvent->onPlayerAntiAddictionInfoResult");
        EagleToken uToken = EagleSDK.getInstance().getUToken();
        if (uToken != null && (adultInfo = uToken.getAntiAddictionInfo()) != null) {
            EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("channel_anti_addiction_callback").put("page", "anti_addiction").put("anti_addiction_switch", Integer.valueOf(uToken.getAntiAddictionSwitch())).put("anti_addiction_state", Integer.valueOf(adultInfo.getAntiAddictionState())).put("identity", adultInfo.getIdentityPlate()).put("age", Integer.valueOf(adultInfo.getPlayerAge())).build());
        }
    }

    public void onEmulatorEvent(Context context) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("simulator", Integer.valueOf(DeviceEmuCheckUtil.mayOnEmulator(context) ? 1 : 0));
        if (DeviceEmuCheckUtil.isEmulatorFromQemuFeatures()) {
            i = 1;
        } else {
            i = 0;
        }
        map.put("simulator_features_qemu", Integer.valueOf(i));
        if (DeviceEmuCheckUtil.notHasLightSensorManager(context).booleanValue()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        map.put("simulator_sensors", Integer.valueOf(i2));
        if (DeviceEmuCheckUtil.isEmulatorFromCpu()) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        map.put("simulator_cpu", Integer.valueOf(i3));
        if (DeviceEmuCheckUtil.isRunningInEmualtor()) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        map.put("simulator_process_qemu", Integer.valueOf(i4));
        if (!DeviceEmuCheckUtil.hasEth0Interface()) {
            i5 = 0;
        }
        map.put("simulator_eth0", Integer.valueOf(i5));
        EagleTrackReport.getInstance().reportEmulatorEvent(map);
    }

    public void getAppsInfo(long serverTime) {
        long lastTime = SPUtils.getLong(Constants.KEY_APPS_LAST_UPLOAD_TIME, 0);
        if (DateUtils.isSameDay(lastTime, serverTime)) {
            Log.m602w(this.TAG, "getAppsInfo in the same day;ignore");
            return;
        }
        Log.m602w(this.TAG, "getAppsInfo lastTime->" + lastTime);
        StringBuilder pkgs = new StringBuilder();
        StringBuilder names = new StringBuilder();
        try {
            for (AppUtils.AppInfo appInfo : AppUtils.getAppsInfo()) {
                if (appInfo != null && !appInfo.isSystem() && !TextUtils.isEmpty(appInfo.getPackageName()) && !TextUtils.isEmpty(appInfo.getName())) {
                    if (pkgs.length() > 0) {
                        pkgs.append("|");
                    }
                    pkgs.append(appInfo.getPackageName());
                    if (names.length() > 0) {
                        names.append("|");
                    }
                    names.append(appInfo.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(pkgs) && !TextUtils.isEmpty(names)) {
            EagleTrackReport.getInstance().reportAppsInfo(pkgs.toString(), names.toString());
            SPUtils.put(Constants.KEY_APPS_LAST_UPLOAD_TIME, serverTime);
        }
    }

    public void obtainDid() {
        FixTimeUtils.getInstance().obtainServerTime(EagleSDK.getInstance().getIDotUrl(), new FixTimeUtils.IObtainServerTimeCallback() {
            public void onObtainServerTime(long serverTime) {
                Log.m602w(EagleTrackEvent.this.TAG, "serverTime->" + serverTime);
                EagleTrackEvent.this.doDidEvent(serverTime);
                EagleTrackEvent.this.getAppsInfo(serverTime);
            }
        });
    }

    private void doOAidEvent(final long serverTime) {
        if (DateUtils.isSameDay(SPUtils.getLong(Constants.KEY_OAID_LAST_UPLOAD_TIME, 0), serverTime)) {
            Log.m602w(this.TAG, "doOAidEvent in the same day;ignore");
            NoCloudLadderSDKReport.isNeedReportOAID = false;
            return;
        }
        Log.m602w(this.TAG, "doOAidEvent");
        MsaSdkHelper.getInstance().obtainMsnIdentifier(new IdentifierObserver() {
            public void onChange(Identifier identifier) {
                if (identifier != null) {
                    NoCloudLadderSDKReport.get().reportDidEvent();
                    SPUtils.put(Constants.KEY_OAID_LAST_UPLOAD_TIME, serverTime);
                    NoCloudLadderSDKReport.isNeedReportOAID = false;
                }
            }
        });
    }

    private void doGAidEvent(final long serverTime) {
        if (DateUtils.isSameDay(SPUtils.getLong(Constants.KEY_GAID_LAST_UPLOAD_TIME, 0), serverTime)) {
            Log.m602w(this.TAG, "doGAidEvent in the same day;ignore");
            NoCloudLadderSDKReport.isNeedReportGAID = false;
            return;
        }
        Log.m602w(this.TAG, "doGAidEvent");
        AdvertisingIdHelper.getInstance().getAdvertisingId(new IdentifierObserver() {
            public void onChange(Identifier identifier) {
                if (identifier != null) {
                    NoCloudLadderSDKReport.get().reportDidEvent();
                    SPUtils.put(Constants.KEY_GAID_LAST_UPLOAD_TIME, serverTime);
                    NoCloudLadderSDKReport.isNeedReportGAID = false;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void doDidEvent(final long serverTime) {
        if (DateUtils.isSameDay(SPUtils.getLong(Constants.KEY_DID_LAST_UPLOAD_TIME, 0), serverTime)) {
            Log.m602w(this.TAG, "obtainDid in the same day;ignore");
            NoCloudLadderSDKReport.isNeedReportDid = false;
            return;
        }
        DIDV4Proxy.getInstance().obtainUUID(EagleSDK.getInstance().getApplication(), new IDIDObtainListener() {
            public void onResult(String did, int from) {
                EagleTrackReport.getInstance().reportDidEvent();
                SPUtils.put(Constants.KEY_DID_LAST_UPLOAD_TIME, serverTime);
                NoCloudLadderSDKReport.isNeedReportDid = false;
            }
        });
    }
}
