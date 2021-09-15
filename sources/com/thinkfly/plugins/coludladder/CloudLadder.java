package com.thinkfly.plugins.coludladder;

import android.content.Context;
import android.text.TextUtils;
import com.doraemon.p027eg.CheckUtils;
import com.thinkfly.cloudlader.Interceptor;
import com.thinkfly.cloudlader.interceptor.DidInterceptor;
import com.thinkfly.plugins.coludladder.config.Config;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.coludladder.p034dk.SharedPreferencesUtils;
import com.thinkfly.plugins.coludladder.proxy.CloudLadderProxy;
import com.thinkfly.plugins.coludladder.proxy.PeriodTaskProxy;
import com.thinkfly.plugins.coludladder.task.TimerTask;
import com.thinkfly.plugins.coludladder.utils.FixTimeUtils;
import java.util.concurrent.ScheduledFuture;

public class CloudLadder {
    private String host;
    private String mAppKey;
    private String mAppSec;
    private ScheduledFuture<?> mCloudLadderTimerTask;
    private Context mContext;
    private TimerTask mTimerTask;
    private CloudLadderProxy proxy;

    public static class Configure {
        public String getHost() {
            return Config.URL;
        }

        public String getSalt() {
            return Config.Salt.SALT;
        }

        public void setDBName(String str) {
            if (!TextUtils.isEmpty(str)) {
                Config.DBConfig.DATA_NAME = str;
            }
        }

        public void setHost(String str) {
            Config.URL = str;
        }

        public void setMaxRepeatNum(int i) {
            Config.RepeatConfig.MAXNUMBER = i;
        }

        public void setRepeatThreadSleepTime(long j) {
            Config.RepeatConfig.MAX_SLEEP_TIME = j;
        }

        public void setSalt(String str) {
            Config.Salt.SALT = str;
        }
    }

    public interface IInitResult {
        void onFailure(String str);

        void onSuccess();
    }

    private CloudLadder(Context context, Builder builder) {
        this.mContext = null;
        this.proxy = null;
        this.host = "";
        this.mAppKey = "";
        this.mAppSec = "";
        this.mContext = context;
        this.host = builder.getHost();
        this.mAppKey = builder.getAppKey();
        this.mAppSec = builder.getAppSec();
        this.proxy = new CloudLadderProxy(this, builder.getDbName()).setInterceptor(builder.getInterceptor());
        Log.init(builder.isDebug());
    }

    private TimerTask getTimerTask() {
        if (this.mTimerTask == null) {
            this.mTimerTask = new TimerTask(this.mAppKey);
        }
        return this.mTimerTask;
    }

    private void startReportTask() {
        if (this.mCloudLadderTimerTask == null) {
            this.mCloudLadderTimerTask = getTimerTask().scheduleAtFixedRate(new PeriodTaskProxy(this.proxy), Config.HEARTBEAT_TIME, Config.HEARTBEAT_TIME);
        }
    }

    public void scheduleTask(int priority) {
        if (priority >= 1 && this.proxy != null && !this.proxy.isNullData()) {
            Log.m662d(Log.TAG, "scheduleTask " + priority);
            if (priority > 100) {
                priority = 100;
            }
            getTimerTask().schedule((Runnable) this.proxy, (long) ((100 - priority) * 10));
        }
    }

    public boolean isExistData() {
        return !this.proxy.isNullData();
    }

    public void tryReport() {
        if (!this.proxy.isNullData()) {
            scheduleTask(1);
        }
    }

    public ScheduledFuture<?> startHeartbeatTask(Runnable command) {
        return getTimerTask().scheduleAtFixedRate(new PeriodTaskProxy(command), Config.FIRST_TIME * 10, Config.HEARTBEAT_TIME);
    }

    public long report(Context context, String sendData) {
        return report(context, sendData, 0);
    }

    public long report(Context context, String sendData, int priority) {
        if (CheckUtils.isNullOrEmpty(sendData)) {
            throw new RuntimeException("需要发送的数据不能为null或者空字符串");
        }
        this.mContext = context;
        long rid = this.proxy.addRecordToDatabase(sendData, priority);
        if (rid > 0) {
            startReportTask();
        }
        Log.m662d(Log.TAG, "--->addRecordToDatabase:" + priority + "\n--->timestamp:" + FixTimeUtils.getInstance().getIntervalTime() + "\n--->The data :" + sendData + "\n--->" + (rid > 0 ? "success" : "failure") + " " + rid);
        if (this.proxy.isReadSend()) {
            scheduleTask(100);
        } else if (priority > 0) {
            scheduleTask(priority);
        }
        return rid;
    }

    public void onDestroy(Context context) {
        this.mContext = context;
        this.proxy.onDestroy();
        if (this.mCloudLadderTimerTask != null) {
            this.mCloudLadderTimerTask.cancel(true);
        }
    }

    @Deprecated
    public void onDestory(Context context) {
        onDestroy(context);
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setHost(String host2) {
        this.host = host2;
    }

    public void setAppSec(String salt) {
        this.mAppKey = salt;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public String getAppSec() {
        return this.mAppSec;
    }

    public String getHost() {
        return this.host;
    }

    public SharedPreferencesUtils getSharedPreferencesUtils() {
        return SharedPreferencesUtils.getInstance();
    }

    public static long fixTimeStamp(long time) {
        return FixTimeUtils.getInstance().fixTimeStamp(time);
    }

    public static class Builder {
        String appKey = "";
        String appSec = "";
        String dbName = "";
        String host = "";
        Interceptor interceptor;
        boolean isDebug = false;

        public String getHost() {
            return this.host;
        }

        public Builder setHost(String host2) {
            this.host = host2;
            return this;
        }

        public String getAppKey() {
            return this.appKey;
        }

        public Builder setAppKey(String appKey2) {
            this.appKey = appKey2;
            return this;
        }

        public String getAppSec() {
            return this.appSec;
        }

        public Builder setAppSec(String appSec2) {
            this.appSec = appSec2;
            return this;
        }

        public boolean isDebug() {
            return this.isDebug;
        }

        public Builder setDebug(boolean debug) {
            this.isDebug = debug;
            return this;
        }

        public String getDbName() {
            if (CheckUtils.isNullOrEmpty(this.dbName)) {
                return this.appKey;
            }
            return this.dbName;
        }

        public Builder setDbName(String dbName2) {
            this.dbName = dbName2;
            return this;
        }

        public Builder setInterceptor(Interceptor interceptor2) {
            this.interceptor = interceptor2;
            return this;
        }

        public Interceptor getInterceptor() {
            if (this.interceptor == null) {
                return new DidInterceptor();
            }
            return this.interceptor;
        }

        public CloudLadder builder(Context context) {
            return new CloudLadder(context, this);
        }
    }
}
