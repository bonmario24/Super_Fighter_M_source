package com.eagle.mixsdk.sdk.other;

import com.eagle.mixsdk.aspectj.LoginTrack;
import com.eagle.mixsdk.aspectj.TrackAspect;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.listeners.EagleInitListener;
import com.eagle.mixsdk.sdk.listeners.EagleLoginListener;
import com.eagle.mixsdk.sdk.listeners.EaglePayListener;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.facebook.appevents.AppEventsConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.reflect.Factory;

public class GameEvent extends EagleEvent {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private EagleInitListener mInitListener;
    private EagleLoginListener mLoginListener;
    private EaglePayListener mPayListener;

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("GameEvent.java", GameEvent.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onLoginSuccess", "com.eagle.mixsdk.sdk.other.GameEvent", "com.eagle.mixsdk.sdk.verify.EagleToken", "token", "", "void"), 75);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onLoginFail", "com.eagle.mixsdk.sdk.other.GameEvent", "java.lang.String", "msg", "", "void"), 86);
    }

    public void setLoginListener(EagleLoginListener listener) {
        this.mLoginListener = listener;
    }

    public void setInitListener(EagleInitListener listener) {
        this.mInitListener = listener;
    }

    public void setPayListener(EaglePayListener listener) {
        this.mPayListener = listener;
    }

    private void writeLog(String msg) {
        Log.m602w(Constants.TAG, msg);
    }

    public void onResult(int code, String msg) {
    }

    public void onInitSuccess() {
        if (this.mInitListener != null) {
            this.mInitListener.onSuccess();
        } else {
            writeLog("no EagleInitListener");
        }
    }

    public void onInitFail(String msg) {
        if (this.mInitListener != null) {
            this.mInitListener.onFailed(msg);
        } else {
            writeLog("no EagleInitListener");
        }
    }

    public void onEagleInitSuc() {
        super.onEagleInitSuc();
    }

    public void onEagleInitFail(String msg) {
        super.onEagleInitFail(msg);
    }

    @LoginTrack.NotifyGameLoginResult
    public void onLoginSuccess(EagleToken token) {
        TrackAspect.aspectOf().onNotifyGameLoginResult(Factory.makeJP(ajc$tjp_0, (Object) this, (Object) this, (Object) token));
        if (this.mLoginListener != null) {
            this.mLoginListener.onLoginSuccess(token);
        } else {
            writeLog("no EagleLoginListener");
        }
    }

    @LoginTrack.NotifyGameLoginResult
    public void onLoginFail(String msg) {
        TrackAspect.aspectOf().onNotifyGameLoginResult(Factory.makeJP(ajc$tjp_1, (Object) this, (Object) this, (Object) msg));
        if (this.mLoginListener != null) {
            this.mLoginListener.onLoginFail(msg);
        } else {
            writeLog("no EagleLoginListener");
        }
    }

    public void onLogout() {
        if (this.mLoginListener != null) {
            this.mLoginListener.onLogout();
        } else {
            writeLog("no EagleLoginListener");
        }
    }

    public void onSwitchAccount(EagleToken token) {
        if (this.mLoginListener != null) {
            this.mLoginListener.onSwitchAccount(token);
        } else {
            writeLog("no EagleLoginListener");
        }
    }

    public void onPaySuccess() {
        if (this.mPayListener != null) {
            this.mPayListener.onSuccess();
        } else {
            writeLog("no EaglePayListener");
        }
    }

    public void onPayFail(String msg) {
        if (this.mPayListener != null) {
            this.mPayListener.onFailed(msg);
        } else {
            writeLog("no EaglePayListener");
        }
    }

    public void onPayCancel() {
        if (this.mPayListener != null) {
            this.mPayListener.onCancel();
        } else {
            writeLog("no EaglePayListener");
        }
    }

    public void onPayUnknown() {
        if (this.mPayListener != null) {
            this.mPayListener.onUnknown();
        } else {
            writeLog("no EaglePayListener");
        }
    }
}
