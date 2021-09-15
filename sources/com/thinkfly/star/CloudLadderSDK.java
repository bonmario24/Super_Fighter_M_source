package com.thinkfly.star;

import android.content.Context;
import android.text.TextUtils;
import com.doraemon.p027eg.CheckUtils;
import com.lzy.okgo.OkGo;
import com.thinkfly.cloudlader.CombDataFactory;
import com.thinkfly.cloudlader.interceptor.DidInterceptor;
import com.thinkfly.plugins.coludladder.CloudLadder;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.coludladder.manager.CloudLadderConnectivityMonitor;
import com.thinkfly.plugins.coludladder.manager.ConnectivityListener;
import com.thinkfly.plugins.coludladder.manager.ProcessMonitor;
import com.thinkfly.plugins.coludladder.utils.FixTimeUtils;
import com.thinkfly.plugins.coludladder.utils.HostUtils;
import com.thinkfly.star.builder.Builder;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.InitBuilder;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import com.thinkfly.star.event.CloudLadderHeartbeatEvent;
import com.thinkfly.star.event.ExtendHeartbeatEvent;
import com.thinkfly.star.event.IHeartbeatEvent;
import java.util.concurrent.ScheduledFuture;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudLadderSDK {
    public static long FIRST_TIME = 1000;
    public static long HEARTBEAT_TIME = OkGo.DEFAULT_MILLISECONDS;
    public static String UID_KEY = "uidKey";
    private static boolean isInited = false;
    /* access modifiers changed from: private */
    public IHeartbeatEvent iHeartbeatEvent;
    private String mAppKey;
    private String mAppSec;
    /* access modifiers changed from: private */
    public CloudLadder mCloudLadder = null;
    /* access modifiers changed from: private */
    public CombDataFactory mCombDataFactory = null;
    /* access modifiers changed from: private */
    public Context mContext = null;
    private GlobalParams mGlobalParams;
    private ScheduledFuture<?> mHeartBeatTask;
    private Builder mHeartbeatBuilder = null;
    private String mHost;
    private InitBuilder mInitBuilder;

    public CloudLadderSDK(Context context, String appKey, String host, String appSec, InitBuilder initBuilder, boolean debug, GlobalParams globalParams) {
        this.mGlobalParams = globalParams;
        init(context, appKey, host, appSec, initBuilder, debug);
    }

    public CloudLadderSDK(Context context, String appKey, String host, String appSec, InitBuilder initBuilder, boolean debug) {
        init(context, appKey, host, appSec, initBuilder, debug);
    }

    private void init(Context context, String appKey, String host, String appSec, InitBuilder initBuilder, boolean debug) {
        if (context == null) {
            throw new RuntimeException("the Context must not be null");
        } else if (TextUtils.isEmpty(appKey)) {
            throw new RuntimeException("the appKey must not be null");
        } else if (TextUtils.isEmpty(host)) {
            throw new RuntimeException("the host must not be null");
        } else if (TextUtils.isEmpty(appSec)) {
            throw new RuntimeException("the appSec must not be null");
        } else if (initBuilder == null) {
            throw new RuntimeException("the salt must not be null");
        } else if (TextUtils.isEmpty(initBuilder.getAppv())) {
            throw new RuntimeException("the appv must not be null");
        } else if (TextUtils.isEmpty(initBuilder.getC())) {
            throw new RuntimeException("the c must not be null");
        } else {
            Log.init(debug);
            String root = HostUtils.getRoot(host);
            Log.m663e(Log.TAG, "url is " + host);
            this.mHost = root + "/app";
            Log.m663e(Log.TAG, "new url is " + this.mHost);
            this.mAppSec = appSec;
            this.mAppKey = appKey;
            this.mContext = context;
            this.mCloudLadder = new CloudLadder.Builder().setAppKey(this.mAppKey).setAppSec(this.mAppSec).setHost(host).setDebug(debug).setInterceptor(new DidInterceptor()).builder(context);
            FixTimeUtils.getInstance().obtainServerTime(host, new FixTimeUtils.IObtainServerTimeCallback() {
                public void onObtainServerTime(long serverTime) {
                    CloudLadderSDK.this.refreshReport(1);
                }
            });
            new ProcessMonitor().register(new ProcessMonitor.ProcessLifeCycleListener() {
                public void onProcessChanged(boolean isBackground) {
                    if (isBackground) {
                        CloudLadderSDK.this.refreshReport(1);
                    } else {
                        CloudLadderSDK.this.refreshReport(100);
                    }
                }
            });
            CloudLadderConnectivityMonitor.with(context).register(new ConnectivityListener() {
                public void onConnectivityChanged(boolean isConnected) {
                    Log.m662d(Log.TAG, "onConnectivityChanged " + isConnected);
                    if (isConnected) {
                        CloudLadderSDK.this.refreshReport(1);
                    }
                }
            });
            this.mCombDataFactory = new CombDataFactory.Configure().setAppKey(appKey).setC(initBuilder.getC()).setFc(initBuilder.getC()).setSc(initBuilder.getSc()).setExtc(initBuilder.getExtc()).setAppv(initBuilder.getAppv()).setGlobalParams(getGlobalParams()).config();
            isInited = true;
            this.mInitBuilder = initBuilder;
            if (initBuilder.isOpenHeartbeat()) {
                reportHeartbeat(0);
            }
        }
    }

    @Deprecated
    public CloudLadder getCloudLadder() {
        return this.mCloudLadder;
    }

    public void refreshReport() {
        refreshReport(50);
    }

    /* access modifiers changed from: private */
    public void refreshReport(int priority) {
        if (this.mCloudLadder != null) {
            this.mCloudLadder.scheduleTask(priority);
        }
    }

    public long reportStartup(Context context, StartupBuilder builder, int priority) {
        if (!isInited) {
            Log.m663e(Log.TAG, "startSDK init failure...");
            return -1;
        } else if (context == null) {
            throw new RuntimeException("the Context must not be null");
        } else {
            this.mContext = context;
            try {
                JSONObject startupJson = this.mCombDataFactory.createStartupJson(context, builder);
                if (this.iHeartbeatEvent != null) {
                    this.iHeartbeatEvent.updateEvent(builder);
                }
                return this.mCloudLadder.report(context, startupJson.toString(), 50);
            } catch (Exception e) {
                e.printStackTrace();
                Log.m663e(Log.TAG, e.getLocalizedMessage());
                return -1;
            }
        }
    }

    public void reportExtendHeartbeat(EventBuilder builder) {
        if (!isInited) {
            Log.m663e(Log.TAG, "startSDK init failure...");
        } else if (builder != null) {
            this.iHeartbeatEvent = new ExtendHeartbeatEvent(builder);
            if (this.mHeartBeatTask == null) {
                addHeartbeatTask();
            }
        }
    }

    public long reportHeartbeat(int priority) {
        if (!isInited) {
            Log.m663e(Log.TAG, "startSDK init failure...");
            return 0;
        }
        this.iHeartbeatEvent = new CloudLadderHeartbeatEvent(this.mInitBuilder);
        if (this.mHeartBeatTask == null) {
            addHeartbeatTask();
        }
        return -1;
    }

    private Runnable getDefaultRunnable() {
        return new Runnable() {
            public void run() {
                if (CloudLadderSDK.this.mContext == null || CloudLadderSDK.this.iHeartbeatEvent == null) {
                    Log.m662d(Log.TAG, "mContext or iHeartbeatEvent is null");
                } else if (!CloudLadderApplication.isForgroundSate) {
                    Log.m662d(Log.TAG, "暂未开启心跳上报功能");
                } else {
                    CloudLadderSDK.this.mCloudLadder.report(CloudLadderSDK.this.mContext, CloudLadderSDK.this.iHeartbeatEvent.createHeartbeatJson(CloudLadderSDK.this.mContext, CloudLadderSDK.this.mCombDataFactory).toString());
                }
            }
        };
    }

    public long reportLogin(Context context, LoginBuilder builder, int priority) {
        if (!isInited) {
            Log.m663e(Log.TAG, "startSDK init failure...");
            return -1;
        } else if (context == null) {
            throw new RuntimeException("the Context must not be null");
        } else {
            this.mContext = context;
            try {
                if (this.iHeartbeatEvent != null) {
                    this.iHeartbeatEvent.setUid(builder.getUid());
                }
                builder.setIsNew(LoginBuilder.OldUser);
                return this.mCloudLadder.report(context, this.mCombDataFactory.createLoginJson(context, builder).toString(), 50);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.m663e(Log.TAG, e.getLocalizedMessage());
                return -1;
            }
        }
    }

    public long reportRegister(Context context, RegisterBuilder builder, int priority) {
        if (!isInited) {
            Log.m663e(Log.TAG, "startSDK init failure...");
            return -1;
        } else if (context == null) {
            throw new RuntimeException("the Context must not be null");
        } else {
            this.mContext = context;
            try {
                if (this.iHeartbeatEvent != null) {
                    this.iHeartbeatEvent.setUid(builder.getUid());
                }
                builder.setIsNew(LoginBuilder.NewUser);
                return this.mCloudLadder.report(context, this.mCombDataFactory.createRegisterJson(context, builder).toString(), 50);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.m663e(Log.TAG, e.getLocalizedMessage());
                return -1;
            }
        }
    }

    public long reportLogout(Context context, LogoutBuilder builder, int priority) {
        if (!isInited) {
            Log.m663e(Log.TAG, "startSDK init failure...");
            return -1;
        } else if (context == null) {
            throw new RuntimeException("the Context must not be null");
        } else {
            this.mContext = context;
            try {
                if (this.iHeartbeatEvent != null) {
                    this.iHeartbeatEvent.setUid("");
                }
                return this.mCloudLadder.report(context, this.mCombDataFactory.createLogoutJson(context, builder).toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.m663e(Log.TAG, e.getLocalizedMessage());
                return -1;
            }
        }
    }

    public long reportEvent(Context context, EventBuilder builder, int priority) {
        if (!isInited) {
            Log.m663e(Log.TAG, "startSDK init failure...");
            return -1;
        } else if (builder == null || CheckUtils.isNullOrEmpty(builder.getAction())) {
            throw new RuntimeException("the action must not be null");
        } else if (context == null) {
            throw new RuntimeException("the Context must not be null");
        } else {
            this.mContext = context;
            try {
                if (this.iHeartbeatEvent != null) {
                    this.iHeartbeatEvent.setUid(builder.getUid());
                }
                return this.mCloudLadder.report(context, this.mCombDataFactory.createEventJson(context, builder).toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.m663e(Log.TAG, e.getLocalizedMessage());
                return -1;
            }
        }
    }

    private void addHeartbeatTask() {
        if (this.mCloudLadder != null) {
            this.mHeartBeatTask = this.mCloudLadder.startHeartbeatTask(getDefaultRunnable());
        }
    }

    public void stopHeartbeat() {
        if (this.mHeartBeatTask != null) {
            this.mHeartBeatTask.cancel(true);
            this.mHeartBeatTask = null;
        }
    }

    public CloudLadderSDK setSalt(String appSec) {
        if (!TextUtils.isEmpty(appSec)) {
            this.mAppKey = appSec;
            this.mCloudLadder.setAppSec(appSec);
        }
        return this;
    }

    public CloudLadderSDK setHost(String host) {
        if (!TextUtils.isEmpty(host)) {
            this.mHost = host;
            this.mCloudLadder.setHost(host);
        }
        return this;
    }

    @Deprecated
    public CloudLadderSDK setWho(String who) {
        this.mAppKey = who;
        return this;
    }

    public void setAppKey(String mAppKey2) {
        this.mAppKey = mAppKey2;
    }

    public CloudLadderSDK setInitBuilder(InitBuilder mInitBuilder2) {
        this.mInitBuilder = mInitBuilder2;
        return this;
    }

    public CloudLadderSDK addGlobalParams(GlobalParams mGlobalParams2) {
        this.mGlobalParams = mGlobalParams2;
        if (this.mCombDataFactory != null) {
            this.mCombDataFactory.setGlobalParams(mGlobalParams2);
        }
        return this;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public InitBuilder getInitBuilder() {
        return this.mInitBuilder;
    }

    public void onDestory(Context context) {
        if (context == null) {
            throw new RuntimeException("the Context must not be null");
        }
        this.mContext = context;
        this.mCloudLadder.onDestroy(context);
        stopHeartbeat();
        isInited = false;
    }

    public GlobalParams getGlobalParams() {
        if (this.mGlobalParams == null) {
            this.mGlobalParams = new GlobalParams();
        }
        return this.mGlobalParams;
    }
}
