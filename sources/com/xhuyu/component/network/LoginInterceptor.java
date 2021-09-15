package com.xhuyu.component.network;

import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.manager.UserHistoryDbManager;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.core.p035db.DBExecutor;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.network.bean.BaseResponse;
import com.xhuyu.component.utils.JsonUtil;
import com.xsdk.doraemon.utils.CheckUtil;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoginInterceptor implements Interceptor {
    private String[] loginUrls = {Urls.AUTO_LOGIN, Urls.URL_QUICK_LOGIN, Urls.FACEBOOK_LOGIN, Urls.GOOGLE_LOGIN, Urls.VISITOR_LOGIN};

    public Response intercept(Interceptor.Chain chain) throws IOException {
        BaseResponse result;
        Request request = chain.request();
        boolean isLogin = isLoginRequest(request);
        String password = request.header("password");
        Request request2 = request.newBuilder().removeHeader("password").build();
        Response proceed = chain.proceed(request2);
        if (isLogin && (result = (BaseResponse) JsonUtil.parseObject(proceed.peekBody(Long.MAX_VALUE).string(), BaseResponse.class)) != null && result.isSuccess()) {
            String url = request2.url().url().toString();
            if (url.contains(Urls.AUTO_LOGIN)) {
                handAutoLogin(result);
            } else if (url.contains(Urls.URL_QUICK_LOGIN)) {
                handNormalLogin(result, password);
            } else if (url.contains(Urls.VISITOR_LOGIN)) {
                handVisitorLogin(result);
            } else {
                handOtherLogin(result);
            }
        }
        return proceed;
    }

    private void handNormalLogin(BaseResponse result, String password) {
        HuYuUser user = (HuYuUser) JsonUtil.parseObject(result.getData().toJSONString(), HuYuUser.class);
        if (user != null) {
            user.setPassword(password);
            saveUserInfo(user, true);
        }
        SDKConfig.getInstance().saveLastLoginType(0);
    }

    private void handVisitorLogin(BaseResponse result) {
        HuYuUser cacheVisitorUser;
        HuYuUser user = (HuYuUser) result.getData().toJavaObject(HuYuUser.class);
        if (user != null) {
            saveUserInfo(user, false);
            if (CheckUtil.checkTextEmpty(user.getPassword()) && (cacheVisitorUser = UserManager.getInstance().getVisitorUser()) != null && cacheVisitorUser.getUsername().equals(user.getUsername()) && !CheckUtil.isEmpty(cacheVisitorUser.getPassword())) {
                user.setPassword(cacheVisitorUser.getPassword());
            }
            UserManager.getInstance().saveVisitorUser(user);
        }
    }

    private void handAutoLogin(BaseResponse result) {
        HuYuUser user;
        HuYuUser newUser = (HuYuUser) result.getData().toJavaObject(HuYuUser.class);
        if (newUser != null) {
            HuYuUser user2 = UserManager.getInstance().getUser();
            saveUserInfo(newUser, false);
            if (!newUser.getUsername().equals(user2.getUsername()) && (user = UserHistoryDbManager.getInstance().getUserTable().getUser(user2.getUsername())) != null) {
                DBExecutor userTable = UserHistoryDbManager.getInstance().getUserTable();
                userTable.deleteUser(user.getUsername());
                user.setUsername(newUser.getUsername());
                userTable.addUser(user);
            }
        }
    }

    private void handOtherLogin(BaseResponse result) {
        HuYuUser user = (HuYuUser) JsonUtil.parseObject(result.getData().toJSONString(), HuYuUser.class);
        if (user != null) {
            saveUserInfo(user, false);
        }
    }

    private void saveUserInfo(HuYuUser user, boolean saveDb) {
        UserManager.getInstance().saveUser(user);
        UserManager.getInstance().setLoginStatus(true);
        if (saveDb) {
            UserManager.getInstance().saveUserHistory(user);
        }
    }

    private boolean isLoginRequest(Request request) {
        String url = request.url().toString();
        for (String contains : this.loginUrls) {
            if (url.contains(contains)) {
                return true;
            }
        }
        return false;
    }
}
