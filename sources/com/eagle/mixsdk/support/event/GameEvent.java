package com.eagle.mixsdk.support.event;

import com.eagle.mixsdk.aspectj.LoginTrack;
import com.eagle.mixsdk.aspectj.TrackAspect;
import com.eagle.mixsdk.sdk.other.EagleEvent;
import com.eagle.mixsdk.sdk.platform.EagleInitListener;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.facebook.appevents.AppEventsConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.reflect.Factory;

public class GameEvent extends EagleEvent {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static GameEvent instance;
    private EagleInitListener mListener;

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("GameEvent.java", GameEvent.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onLoginSuccess", "com.eagle.mixsdk.support.event.GameEvent", "com.eagle.mixsdk.sdk.verify.EagleToken", "token", "", "void"), 56);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onLoginFail", "com.eagle.mixsdk.support.event.GameEvent", "java.lang.String", "msg", "", "void"), 64);
    }

    private GameEvent() {
    }

    public static GameEvent create(EagleInitListener listener) {
        if (instance == null) {
            instance = new GameEvent();
        }
        instance.setInitListener(listener);
        return instance;
    }

    private void setInitListener(EagleInitListener listener) {
        this.mListener = listener;
    }

    public void onInitSuccess() {
        if (this.mListener != null) {
            this.mListener.onInitResult(1, "init success");
        }
    }

    public void onInitFail(String msg) {
        if (this.mListener != null) {
            this.mListener.onInitResult(2, msg);
        }
    }

    @LoginTrack.NotifyGameLoginResult
    public void onLoginSuccess(EagleToken token) {
        TrackAspect.aspectOf().onNotifyGameLoginResult(Factory.makeJP(ajc$tjp_0, (Object) this, (Object) this, (Object) token));
        if (this.mListener != null) {
            this.mListener.onLoginResult(4, token);
        }
    }

    @LoginTrack.NotifyGameLoginResult
    public void onLoginFail(String msg) {
        TrackAspect.aspectOf().onNotifyGameLoginResult(Factory.makeJP(ajc$tjp_1, (Object) this, (Object) this, (Object) msg));
        if (this.mListener != null) {
            this.mListener.onLoginResult(5, (EagleToken) null);
            this.mListener.onLoginFail(5, msg);
        }
    }

    public void onLogout() {
        if (this.mListener != null) {
            this.mListener.onLogout();
        }
    }

    public void onSwitchAccount(EagleToken token) {
        if (this.mListener != null) {
            this.mListener.onSwitchAccount(token);
        }
    }

    public void onPaySuccess() {
        if (this.mListener != null) {
            this.mListener.onPayResult(10, "pay success");
        }
    }

    public void onPayFail(String msg) {
        if (this.mListener != null) {
            this.mListener.onPayResult(11, msg);
        }
    }

    public void onPayCancel() {
        if (this.mListener != null) {
            this.mListener.onPayResult(33, "pay cancel");
        }
    }

    public void onPayUnknown() {
        if (this.mListener != null) {
            this.mListener.onPayResult(34, "pay unknown");
        }
    }
}
