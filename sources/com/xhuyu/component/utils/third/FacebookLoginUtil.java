package com.xhuyu.component.utils.third;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.config.SuperTool;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FacebookLoginUtil {
    private static FacebookLoginUtil instance = null;
    private LoginManager loginManager;
    /* access modifiers changed from: private */
    public Activity mActivity;
    private String mApplicationID;
    private CallbackManager mCallbackManager;
    private LoginBehavior mFbLoginType;
    /* access modifiers changed from: private */
    public FacebookLoginListener mLoginListener;

    public interface FacebookLoginListener {
        void onCancel();

        void onError(String str);

        void onSuccess(LoginResult loginResult);
    }

    private FacebookLoginUtil() {
        try {
            this.mCallbackManager = CallbackManager.Factory.create();
        } catch (Exception e) {
        }
    }

    public static FacebookLoginUtil getInstance() {
        if (instance == null) {
            synchronized (FacebookLoginUtil.class) {
                if (instance == null) {
                    instance = new FacebookLoginUtil();
                }
            }
        }
        return instance;
    }

    public void initFacebookLogin(Activity activity, FacebookLoginListener listener) {
        this.mActivity = activity;
        this.mLoginListener = listener;
        SDKLoggerUtil.getLogger().mo19504i("beginFacebookLogin init...", new Object[0]);
        registerFacebookCallback();
    }

    public void doFacebookLogin() {
        if (this.mActivity == null) {
            SDKLoggerUtil.getLogger().mo19502e("not set activity", new Object[0]);
            return;
        }
        SDKLoggerUtil.getLogger().mo19502e("loginWith FaceBook", new Object[0]);
        SuperTool.getInstance().setActivityResultType(1);
        List<String> permissions = Arrays.asList(new String[]{"public_profile"});
        SDKLoggerUtil.getLogger().mo19502e("LoginFaceBook", new Object[0]);
        if (this.mFbLoginType != null && this.mFbLoginType.ordinal() > 0) {
            LoginManager.getInstance().setLoginBehavior(this.mFbLoginType);
        }
        if (!CheckUtils.isNullOrEmpty(this.mApplicationID)) {
            FacebookSdk.setApplicationId(this.mApplicationID);
        }
        LoginManager.getInstance().logInWithReadPermissions(this.mActivity, (Collection<String>) permissions);
    }

    private void registerFacebookCallback() {
        getLoginManager().registerCallback(this.mCallbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                SDKLoggerUtil.getLogger().mo19501d("--->fb token:", loginResult.getAccessToken().getToken());
                FacebookLoginUtil.this.mLoginListener.onSuccess(loginResult);
            }

            public void onCancel() {
                FacebookLoginUtil.this.mLoginListener.onCancel();
                FacebookLoginUtil.this.postLoginResultEvent(2, ReflectResource.getInstance(FacebookLoginUtil.this.mActivity).getString("login_cancel"));
            }

            public void onError(FacebookException error) {
                error.printStackTrace();
                FacebookLoginUtil.this.mLoginListener.onError(error.toString());
                String login_fail = ReflectResource.getInstance(FacebookLoginUtil.this.mActivity).getString("login_fail");
                Toast.makeText(FacebookLoginUtil.this.mActivity, login_fail, 0).show();
                FacebookLoginUtil.this.postLoginResultEvent(0, login_fail);
                FacebookLoginUtil.this.logout();
            }
        });
    }

    /* access modifiers changed from: private */
    public void postLoginResultEvent(int resultCode, String dataInfo) {
        SDKEventPost.post(3, new com.xhuyu.component.mvp.model.LoginResult(11, resultCode, dataInfo));
    }

    private LoginManager getLoginManager() {
        if (this.loginManager == null) {
            this.loginManager = LoginManager.getInstance();
        }
        return this.loginManager;
    }

    public void logout() {
        try {
            if (FacebookSdk.isInitialized()) {
                SDKLoggerUtil.getLogger().mo19502e("logout facebook", new Object[0]);
                getLoginManager().logOut();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CallbackManager getFacebookCallbackManager() {
        return this.mCallbackManager;
    }

    public void setFbLoginType(LoginBehavior fbLoginType) {
        this.mFbLoginType = fbLoginType;
    }

    public void setApplicationID(String applicationID) {
        this.mApplicationID = applicationID;
    }

    public String getApplicationID() {
        return this.mApplicationID;
    }

    public void recoverApplicationID(String applicationID) {
        if (!CheckUtils.isNullOrEmpty(applicationID)) {
            FacebookSdk.setApplicationId(applicationID);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mCallbackManager != null) {
            this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
