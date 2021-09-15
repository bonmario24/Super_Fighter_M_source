package com.xhuyu.component.mvp.presenter.login;

import android.text.TextUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.model.LoginResult;
import com.xhuyu.component.network.NetLoginUtil;
import org.json.JSONObject;

public class LoginPresenterControl {
    public static void visitorLogin(boolean isNeedTrack, final GameSDKListener listener) {
        String account = "";
        String password = "";
        HuYuUser visitorUser = UserManager.getInstance().getVisitorUser();
        if (visitorUser != null && !TextUtils.isEmpty(visitorUser.getUsername()) && !TextUtils.isEmpty(visitorUser.getPassword())) {
            account = visitorUser.getUsername();
            password = visitorUser.getPassword();
        }
        if (isNeedTrack) {
            SDKEventPost.postTrack(8194, 7);
        }
        NetLoginUtil.postVisitorLogin(account, password, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                LoginPresenterControl.postLoginResult(7, 1, dataJson.toString());
                if (listener != null) {
                    listener.onSuccess(dataJson, message, args);
                }
            }

            public void onFail(String message, int code) {
                LoginPresenterControl.postLoginResult(7, code, message);
                if (listener != null) {
                    listener.onFail(message, code);
                }
            }
        });
    }

    public static void postLoginResult(int loginType, int responseCode, String message) {
        SDKEventPost.post(3, new LoginResult(loginType, responseCode, message));
    }
}
