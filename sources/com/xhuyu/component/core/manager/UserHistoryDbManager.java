package com.xhuyu.component.core.manager;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.config.Constant;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.p035db.DBExecutor;
import com.xhuyu.component.core.p035db.UserHistoryDbHelper;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.io.File;
import java.util.List;

public class UserHistoryDbManager {
    private static final String DATABASE_DIR = "history";
    private static final String DATABASE_NAME = "hy_user.cache";
    private static UserHistoryDbManager mInstance;
    private boolean isInit;
    private Context mContext;
    private UserHistoryDbHelper mHistoryDbHelper;
    private DBExecutor mPhoneTable;
    private DBExecutor mUserTable;

    private UserHistoryDbManager() {
    }

    public static UserHistoryDbManager getInstance() {
        if (mInstance == null) {
            synchronized (TrackEventManager.class) {
                if (mInstance == null) {
                    mInstance = new UserHistoryDbManager();
                }
            }
        }
        return mInstance;
    }

    public void initDatabase(Context context) {
        this.mContext = context;
        if (!this.isInit) {
            this.mHistoryDbHelper = new UserHistoryDbHelper(this.mContext, doGetDatabasePath());
            this.mUserTable = this.mHistoryDbHelper.getUserTable();
            this.mPhoneTable = this.mHistoryDbHelper.getPhoneTable();
            this.mHistoryDbHelper.clearOutDataUserInfo();
            mergeDatabase();
            this.isInit = true;
        }
    }

    private void mergeDatabase() {
        try {
            File defaultDbFile = new File(getDefaultCachePath(), DATABASE_NAME);
            if (defaultDbFile.exists()) {
                if (PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE)) {
                    UserHistoryDbHelper defaultDbHelper = new UserHistoryDbHelper(this.mContext, defaultDbFile.getAbsolutePath());
                    DBExecutor defaultUserTable = defaultDbHelper.getUserTable();
                    DBExecutor defaultPhoneTable = defaultDbHelper.getPhoneTable();
                    List<HuYuUser> allUser = defaultUserTable.getAllUser();
                    List<HuYuUser> allTableUser = defaultPhoneTable.getAllUser();
                    defaultDbHelper.close();
                    defaultDbHelper.deleteDatabase();
                    this.mUserTable.appendUserList(allUser);
                    this.mPhoneTable.appendUserList(allTableUser);
                    return;
                }
            }
            SDKLoggerUtil.getLogger().mo19502e("default db is not exists or need storage permission", new Object[0]);
        } catch (Exception e) {
            SDKLoggerUtil.getLogger().mo19502e("Database merge error:" + e.toString(), new Object[0]);
        }
    }

    private String doGetDatabasePath() {
        return new File(doGetDatabaseDir(), DATABASE_NAME).getAbsolutePath();
    }

    private String doGetDatabaseDir() {
        String defaultPath = getDefaultCachePath();
        if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE)) {
            return defaultPath;
        }
        if (Build.VERSION.SDK_INT < 29) {
            defaultPath = new File(Environment.getExternalStorageDirectory(), getChannelName()).getAbsolutePath();
        } else if (Environment.isExternalStorageLegacy()) {
            defaultPath = new File(Environment.getExternalStorageDirectory(), getChannelName()).getAbsolutePath();
        }
        File history = new File(defaultPath, DATABASE_DIR);
        if (!history.exists()) {
            history.mkdirs();
        }
        return history.getAbsolutePath();
    }

    private String getDefaultCachePath() {
        File externalFilesDir;
        String cachePath = new File(this.mContext.getCacheDir(), getChannelName()).getPath();
        if ((!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) || (externalFilesDir = this.mContext.getExternalFilesDir(getChannelName())) == null) {
            return cachePath;
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        return externalFilesDir.getAbsolutePath();
    }

    private String getChannelName() {
        String channelName = SDKConfig.getInstance().getConfigValue(Constant.HuYuConfigKey.CHANNEL_NAME_KEY);
        return CheckUtils.isNullOrEmpty(channelName) ? "HaiLiangHuYu" : channelName;
    }

    public DBExecutor getUserTable() {
        return this.mUserTable;
    }

    public DBExecutor getPhoneTable() {
        return this.mPhoneTable;
    }

    public void release() {
        try {
            if (this.mHistoryDbHelper != null) {
                this.mHistoryDbHelper.close();
            }
        } catch (Exception e) {
        }
    }
}
