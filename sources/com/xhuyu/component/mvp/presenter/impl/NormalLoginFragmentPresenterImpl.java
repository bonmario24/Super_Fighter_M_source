package com.xhuyu.component.mvp.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.firebase.auth.FirebaseUser;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.config.TrackEventKey;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.presenter.NormalLoginFragmentPresenter;
import com.xhuyu.component.mvp.presenter.login.LoginPresenterControl;
import com.xhuyu.component.mvp.view.NormalLoginFragmentView;
import com.xhuyu.component.network.NetLoginUtil;
import com.xhuyu.component.utils.PhoneInfoUtils;
import com.xhuyu.component.utils.RSAUtil;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.utils.third.FacebookLoginUtil;
import com.xhuyu.component.utils.third.GoogleLoginUtil;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.ScreenshotUtil;
import com.xsdk.doraemon.utils.UiCalculateUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class NormalLoginFragmentPresenterImpl implements NormalLoginFragmentPresenter {
    NormalLoginFragmentView mView;
    /* access modifiers changed from: private */
    public String randAccount;
    /* access modifiers changed from: private */
    public String randPassword;

    public NormalLoginFragmentPresenterImpl(NormalLoginFragmentView view) {
        this.mView = view;
    }

    public void calculateTheLayout(Context context, int containsHeight) {
        int height = -2;
        if (UiCalculateUtil.isLandscape(context) && containsHeight < (height = UiCalculateUtil.calculateTheLayoutHeight(context, 0.9f, 0.45f))) {
            height = containsHeight;
        }
        this.mView.autoInflaterUI(UiCalculateUtil.calculateTheLayoutWidth(context), height);
    }

    public void initThreadSDK(Activity activity) {
        intGoogleLogin(activity);
        initFacebookLogin(activity);
    }

    public void loadHistoryUser() {
        HuYuUser xUser;
        if (!UserManager.getInstance().hasUserHistory()) {
            SDKLoggerUtil.getLogger().mo19501d("---将自动生成一个账号---", new Object[0]);
            requestRandAccount();
        } else if (SDKConfig.getInstance().getLastLoginType() == 0 && (xUser = UserManager.getInstance().getUser()) != null) {
            this.mView.showAccountInfo(false, xUser.getUsername(), xUser.getPassword());
        }
    }

    public void requestAccountLogin(String account, String password) {
        int loginType;
        if (account.length() < 6) {
            this.mView.showToastMessage(true, "xf_account_rule");
        } else if (password.length() < 6) {
            this.mView.showToastMessage(true, "xf_password_rule");
        } else {
            boolean change = true;
            if (password.length() < 32) {
                if (!TextUtils.isEmpty(this.randPassword) && !TextUtils.isEmpty(this.randAccount)) {
                    change = !this.randPassword.equals(password) && !this.randAccount.equals(account);
                }
                password = RSAUtil.publicEncrypt(password);
            }
            String account2 = account.toLowerCase();
            if (PhoneInfoUtils.isPhoneNumber(account2)) {
                loginType = 4;
            } else {
                loginType = 5;
            }
            doAccountLogin(loginType, account2, password, change);
        }
    }

    public void reset(String pwd, String account) {
        if (!CheckUtils.isNullOrEmpty(pwd) && !pwd.equals(this.randPassword)) {
            this.randPassword = "";
        }
        if (!CheckUtils.isNullOrEmpty(account) && !account.equals(this.randAccount)) {
            this.randAccount = "";
        }
    }

    private void doAccountLogin(final int loginType, String account, String password, boolean change) {
        this.mView.showDialog();
        SDKEventPost.postTrack(8194, Integer.valueOf(loginType));
        NetLoginUtil.postQuickLogin(account, password, change, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                NormalLoginFragmentPresenterImpl.this.mView.closeLoadingDialog();
                HuYuUser user = UserManager.getInstance().getUser();
                int lType = loginType;
                if (user.getRegister_flag() == 1) {
                    lType = 14;
                    NormalLoginFragmentPresenterImpl.this.mView.changePwdEditViewType(145);
                    if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE)) {
                        try {
                            Toast.makeText(NormalLoginFragmentPresenterImpl.this.mView.getViewActivity(), ResourceUtil.getString("xf_save_img_no_per"), 0).show();
                        } catch (Exception e) {
                        }
                    } else {
                        NormalLoginFragmentPresenterImpl.this.saveScreenshot();
                    }
                }
                SDKLoggerUtil.getLogger().mo19504i("login type:" + lType, new Object[0]);
                NormalLoginFragmentPresenterImpl.this.postLoginResult(lType, 1, dataJson.toString());
                NormalLoginFragmentPresenterImpl.this.mView.onLoginSuccess();
            }

            public void onFail(String message, int code) {
                NormalLoginFragmentPresenterImpl.this.mView.closeLoadingDialog();
                NormalLoginFragmentPresenterImpl.this.postLoginResult(loginType, 0, message);
                NormalLoginFragmentPresenterImpl.this.mView.onLoginFailed(message, code);
            }
        });
    }

    /* access modifiers changed from: private */
    public void saveScreenshot() {
        final View contentView = this.mView.getContentView();
        if (contentView != null) {
            contentView.post(new Runnable() {
                public void run() {
                    try {
                        Bitmap bitmap = Bitmap.createBitmap(contentView.getMeasuredWidth(), contentView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                        contentView.draw(new Canvas(bitmap));
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bao);
                        InputStream inputStream = new ByteArrayInputStream(bao.toByteArray());
                        ScreenshotUtil.saveScreenshot(NormalLoginFragmentPresenterImpl.this.mView.getViewActivity(), "Account", "hy_img" + System.currentTimeMillis() + ".png", inputStream);
                        bitmap.recycle();
                        Toast.makeText(NormalLoginFragmentPresenterImpl.this.mView.getViewActivity(), ResourceUtil.getString("xf_save_img_tip"), 1).show();
                        NormalLoginFragmentPresenterImpl.this.mView.changePwdEditViewType(129);
                    } catch (Exception e) {
                        SDKLoggerUtil.getLogger().mo19502e("save screenshot error...", new Object[0]);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void requestRandAccount() {
        this.mView.showDialog();
        NetLoginUtil.postGetRandAccount(new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                try {
                    String username = dataJson.getString("account");
                    String password = dataJson.getString("password");
                    SDKEventPost.postTrack(TrackEventKey.ON_ONE_CLICK_REGISTRATION, new Object[0]);
                    String unused = NormalLoginFragmentPresenterImpl.this.randPassword = password;
                    String unused2 = NormalLoginFragmentPresenterImpl.this.randAccount = username;
                    NormalLoginFragmentPresenterImpl.this.mView.showAccountInfo(true, username, password);
                    NormalLoginFragmentPresenterImpl.this.mView.closeLoadingDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFail(String message, int code) {
                NormalLoginFragmentPresenterImpl.this.mView.showToastMessage(false, message);
                NormalLoginFragmentPresenterImpl.this.mView.closeLoadingDialog();
            }
        });
    }

    private void intGoogleLogin(Activity activity) {
        GoogleLoginUtil.getInstance().initGoogleSignInClient(activity, new GoogleLoginUtil.GoogleLoginListener() {
            public void onSuccessWithAuthFirebase(String idToken, FirebaseUser firebaseUser) {
                if (CheckUtil.isEmpty(idToken) || firebaseUser == null) {
                    postGoogleLoginResultEvent(0, "sign in account fail id token is empty");
                    return;
                }
                NormalLoginFragmentPresenterImpl.this.mView.showDialog();
                NetLoginUtil.postGoogleLogin(idToken, new GameSDKListener() {
                    public void onSuccess(JSONObject dataJson, String message, Object... args) {
                        NormalLoginFragmentPresenterImpl.this.mView.closeLoadingDialog();
                        C15804.this.postGoogleLoginResultEvent(1, dataJson.toString());
                    }

                    public void onFail(String message, int code) {
                        NormalLoginFragmentPresenterImpl.this.mView.closeLoadingDialog();
                        GoogleLoginUtil.getInstance().signOut();
                        C15804.this.postGoogleLoginResultEvent(0, message);
                    }
                });
            }

            public void onError(int statusCode, String failMessage) {
                switch (statusCode) {
                    case GoogleSignInStatusCodes.SIGN_IN_CANCELLED /*12501*/:
                        postGoogleLoginResultEvent(2, failMessage);
                        return;
                    case GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS /*12502*/:
                        postGoogleLoginResultEvent(3, failMessage);
                        return;
                    default:
                        GoogleLoginUtil.getInstance().signOut();
                        postGoogleLoginResultEvent(0, failMessage);
                        return;
                }
            }

            /* access modifiers changed from: private */
            public void postGoogleLoginResultEvent(int code, String failMessage) {
                NormalLoginFragmentPresenterImpl.this.postLoginResult(12, code, failMessage);
                if (code == 1) {
                    NormalLoginFragmentPresenterImpl.this.mView.onLoginSuccess();
                } else {
                    NormalLoginFragmentPresenterImpl.this.mView.onLoginFailed(failMessage, code);
                }
            }
        });
    }

    public void doGoogleLogin(Activity activity) {
        if (!CheckUtil.checkPackageApp(activity, "com.google.android.gms")) {
            this.mView.showToastMessage(true, "huyu_please_install_google_app");
            postLoginResult(12, 0, "login failed");
            return;
        }
        try {
            GoogleLoginUtil.getInstance().doGoogleLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doVisitorLogin() {
        this.mView.showDialog();
        LoginPresenterControl.visitorLogin(false, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                NormalLoginFragmentPresenterImpl.this.mView.onLoginSuccess();
            }

            public void onFail(String message, int code) {
                NormalLoginFragmentPresenterImpl.this.mView.onLoginFailed(message, code);
            }
        });
    }

    private void initFacebookLogin(Activity activity) {
        changeFacebookLoginConfig();
        FacebookLoginUtil.getInstance().initFacebookLogin(activity, new FacebookLoginUtil.FacebookLoginListener() {
            public void onSuccess(LoginResult loginResult) {
                NormalLoginFragmentPresenterImpl.this.mView.showDialog();
                NetLoginUtil.postFacebookLogin(loginResult.getAccessToken().getToken(), new GameSDKListener() {
                    public void onSuccess(JSONObject dataJson, String message, Object... args) {
                        NormalLoginFragmentPresenterImpl.this.postLoginResult(11, 1, dataJson.toString());
                        NormalLoginFragmentPresenterImpl.this.mView.onLoginSuccess();
                    }

                    public void onFail(String message, int code) {
                        NormalLoginFragmentPresenterImpl.this.postLoginResult(11, code, message);
                        NormalLoginFragmentPresenterImpl.this.mView.onLoginFailed(message, code);
                    }
                });
                recover();
            }

            public void onError(String errorMsg) {
                recover();
            }

            public void onCancel() {
                recover();
            }

            private void recover() {
                String fbLoginAID = FacebookLoginUtil.getInstance().getApplicationID();
                String analyzeFacebookAppId = SDKConfig.getInstance().getAnalyzeFacebookAppId();
                if (notInvalidAppId(fbLoginAID) && notInvalidAppId(analyzeFacebookAppId) && !fbLoginAID.equals(analyzeFacebookAppId)) {
                    FacebookLoginUtil.getInstance().recoverApplicationID(analyzeFacebookAppId);
                }
            }

            private boolean notInvalidAppId(String appId) {
                return !com.thinkfly.star.utils.CheckUtils.isNullOrEmpty(appId) && !appId.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
        });
    }

    private void changeFacebookLoginConfig() {
        FacebookLoginUtil.getInstance().setFbLoginType(SDKConfig.getInstance().getFbLoginType());
        FacebookLoginUtil.getInstance().setApplicationID(SDKConfig.getInstance().getFbApplicationId());
    }

    public void postLoginResult(int loginType, int responseCode, String message) {
        this.mView.closeLoadingDialog();
        LoginPresenterControl.postLoginResult(loginType, responseCode, message);
    }
}
