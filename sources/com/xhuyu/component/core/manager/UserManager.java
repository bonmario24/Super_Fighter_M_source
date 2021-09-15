package com.xhuyu.component.core.manager;

import android.text.TextUtils;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.utils.CacheUtils;
import com.xhuyu.component.utils.JsonUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class UserManager {
    private static String KEY_USER = "UserInfo";
    private static String KEY_VISITOR = "VISITOR";
    private static String SP_NAME = "UserData";
    private static UserManager mInstance;
    private boolean isLogin = true;
    private HuYuUser mUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (mInstance == null) {
            synchronized (UserManager.class) {
                if (mInstance == null) {
                    mInstance = new UserManager();
                }
            }
        }
        return mInstance;
    }

    public void saveUser(HuYuUser user) {
        if (user != null) {
            user.setTime(System.currentTimeMillis() + "");
            try {
                CacheUtils.putCacheString(SP_NAME, KEY_USER, JsonUtil.gson.toJson((Object) user));
                this.mUser = user;
            } catch (Exception e) {
                SDKLoggerUtil.getLogger().mo19502e("save user info error!", new Object[0]);
            }
        }
    }

    public void deleteUser() {
        this.mUser = null;
        CacheUtils.putCacheString(SP_NAME, KEY_USER, "");
    }

    public HuYuUser getUser() {
        if (this.mUser == null) {
            this.mUser = getUserCache();
        }
        return this.mUser;
    }

    public void saveUserHistory(HuYuUser user) {
        boolean addUser = UserHistoryDbManager.getInstance().getUserTable().addUser(user);
    }

    public boolean hasUserHistory() {
        return UserHistoryDbManager.getInstance().getUserTable().hasUser();
    }

    public HuYuUser getLoginUser() {
        if (this.isLogin) {
            return getUser();
        }
        return null;
    }

    public void setLoginStatus(boolean status) {
        this.isLogin = status;
    }

    public void changePassword(String md5) {
        HuYuUser user = getUser();
        user.setPassword(md5);
        if (user != null) {
            CacheUtils.putCacheString(SP_NAME, KEY_USER, "");
            UserHistoryDbManager.getInstance().getUserTable().addUser(user);
        }
    }

    public HuYuUser getUserCache() {
        String userJson = CacheUtils.getCacheString(SP_NAME, KEY_USER);
        if (TextUtils.isEmpty(userJson)) {
            return null;
        }
        return (HuYuUser) JsonUtil.parseObject(userJson, HuYuUser.class);
    }

    public HuYuUser getVisitorUser() {
        return (HuYuUser) JsonUtil.parseObject(CacheUtils.getCacheString(SP_NAME, KEY_VISITOR), HuYuUser.class);
    }

    public void saveVisitorUser(HuYuUser user) {
        if (user != null) {
            user.setPassword(user.getRSAPassword());
            CacheUtils.putCacheString(SP_NAME, KEY_VISITOR, JsonUtil.toJson(user));
        }
    }

    public void deleteVisitorUser() {
        CacheUtils.putCacheString(SP_NAME, KEY_VISITOR, "");
    }

    public void exitGame() {
        this.mUser = null;
    }

    public String getStarToken() {
        HuYuUser user = getInstance().getUser();
        if (user != null) {
            return user.getStar_token();
        }
        return "";
    }
}
