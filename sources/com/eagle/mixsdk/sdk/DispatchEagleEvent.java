package com.eagle.mixsdk.sdk;

import android.os.Handler;
import android.os.Looper;
import com.eagle.mixsdk.aspectj.LoginTrack;
import com.eagle.mixsdk.aspectj.PayTrack;
import com.eagle.mixsdk.aspectj.SDKTrack;
import com.eagle.mixsdk.aspectj.TrackAspect;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.impl.listeners.IInnerEvent;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.other.EagleEvent;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.facebook.appevents.AppEventsConstants;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.reflect.Factory;

public class DispatchEagleEvent implements IInnerEvent {
    private static Annotation ajc$anno$0;
    private static Annotation ajc$anno$1;
    private static Annotation ajc$anno$10;
    private static Annotation ajc$anno$11;
    private static Annotation ajc$anno$12;
    private static Annotation ajc$anno$2;
    private static Annotation ajc$anno$3;
    private static Annotation ajc$anno$4;
    private static Annotation ajc$anno$5;
    private static Annotation ajc$anno$6;
    private static Annotation ajc$anno$7;
    private static Annotation ajc$anno$8;
    private static Annotation ajc$anno$9;
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    /* access modifiers changed from: private */
    public List<EagleEvent> events = new ArrayList();
    private AtomicInteger initState;
    /* access modifiers changed from: private */
    public List<EagleEvent> interceptEvents = new ArrayList();
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("DispatchEagleEvent.java", DispatchEagleEvent.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onInitSuccess", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "", "", "", "void"), 111);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onInitFail", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "java.lang.String", "msg", "", "void"), 117);
        ajc$tjp_10 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "trackEagleInitFail", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "java.lang.String", "msg", "", "void"), 278);
        ajc$tjp_11 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onEagleLoginFail", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "java.lang.String", "msg", "", "void"), 308);
        ajc$tjp_12 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onLoginSuccess", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "com.eagle.mixsdk.sdk.verify.EagleToken", "token", "", "void"), 329);
        ajc$tjp_13 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onEagleOrderFail", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "java.lang.String", "msg", "", "void"), 351);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onLoginFail", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "java.lang.String", "msg", "", "void"), 140);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onLogout", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "", "", "", "void"), 157);
        ajc$tjp_4 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onSwitchAccount", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "com.eagle.mixsdk.sdk.verify.EagleToken", "token", "", "void"), 174);
        ajc$tjp_5 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onPaySuccess", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "", "", "", "void"), 191);
        ajc$tjp_6 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onPayFail", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "java.lang.String", "msg", "", "void"), 208);
        ajc$tjp_7 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onPayCancel", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "", "", "", "void"), 225);
        ajc$tjp_8 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onPayUnknown", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "", "", "", "void"), 242);
        ajc$tjp_9 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onEagleInitSuc", "com.eagle.mixsdk.sdk.DispatchEagleEvent", "", "", "", "void"), 261);
    }

    public void resetState() {
        this.initState.set(0);
    }

    public void register(EagleEvent event) {
        if (event != null && !this.events.contains(event)) {
            if (this.interceptEvents.contains(event)) {
                this.interceptEvents.remove(event);
            }
            this.events.add(event);
        }
    }

    public void unregister(EagleEvent event) {
        if (event != null && this.events.contains(event)) {
            this.interceptEvents.add(event);
        }
    }

    public void onResult(final int code, final String msg) {
        Log.m599e(Constants.TAG, "onResult code :" + code + " msg:" + msg);
        switch (code) {
            case 1:
                onInitSuccess();
                return;
            case 2:
                onInitFail(msg);
                return;
            case 4:
                return;
            case 5:
                onLoginFail(msg);
                return;
            case 8:
                onLogout();
                return;
            case 10:
                onPaySuccess();
                return;
            case 11:
                onPayFail(msg);
                return;
            case 33:
                onPayCancel();
                return;
            case 34:
                onPayUnknown();
                return;
            default:
                runOnMainThread(new Runnable() {
                    public void run() {
                        for (EagleEvent event : DispatchEagleEvent.this.events) {
                            if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                                event.onResult(code, msg);
                            }
                        }
                    }
                });
                return;
        }
    }

    @SDKTrack.InitSDK(result = 1)
    public void onInitSuccess() {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, this, this);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$0;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onInitSuccess", new Class[0]).getAnnotation(SDKTrack.InitSDK.class);
            ajc$anno$0 = annotation;
        }
        aspectOf.onInitSDK(makeJP, (SDKTrack.InitSDK) annotation);
        Log.m602w(Constants.TAG, "onInitSuccess:");
    }

    @SDKTrack.InitSDK(result = 0)
    public void onInitFail(final String msg) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_1, (Object) this, (Object) this, (Object) msg);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$1;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onInitFail", new Class[]{String.class}).getAnnotation(SDKTrack.InitSDK.class);
            ajc$anno$1 = annotation;
        }
        aspectOf.onInitSDK(makeJP, (SDKTrack.InitSDK) annotation);
        Log.m602w(Constants.TAG, "onInitFail:" + msg);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onInitFail(msg);
                    }
                }
            }
        });
    }

    @LoginTrack.SDKLogin(result = 0)
    public void onLoginFail(final String msg) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_2, (Object) this, (Object) this, (Object) msg);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$2;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onLoginFail", new Class[]{String.class}).getAnnotation(LoginTrack.SDKLogin.class);
            ajc$anno$2 = annotation;
        }
        aspectOf.onSDKLogin(makeJP, (LoginTrack.SDKLogin) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onLoginFail(msg);
                    }
                }
            }
        });
    }

    @LoginTrack.Logout
    public void onLogout() {
        TrackAspect.aspectOf().onLogout(Factory.makeJP(ajc$tjp_3, this, this));
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onLogout();
                    }
                }
            }
        });
    }

    @LoginTrack.EagleLogin(result = 1)
    public void onSwitchAccount(final EagleToken token) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_4, (Object) this, (Object) this, (Object) token);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$3;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onSwitchAccount", new Class[]{EagleToken.class}).getAnnotation(LoginTrack.EagleLogin.class);
            ajc$anno$3 = annotation;
        }
        aspectOf.onEagleLogin(makeJP, (LoginTrack.EagleLogin) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onSwitchAccount(token);
                    }
                }
            }
        });
    }

    @PayTrack.PayResult(result = 1)
    public void onPaySuccess() {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_5, this, this);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$4;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onPaySuccess", new Class[0]).getAnnotation(PayTrack.PayResult.class);
            ajc$anno$4 = annotation;
        }
        aspectOf.onPayResult(makeJP, (PayTrack.PayResult) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onPaySuccess();
                    }
                }
            }
        });
    }

    @PayTrack.PayResult(result = 0)
    public void onPayFail(final String msg) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_6, (Object) this, (Object) this, (Object) msg);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$5;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onPayFail", new Class[]{String.class}).getAnnotation(PayTrack.PayResult.class);
            ajc$anno$5 = annotation;
        }
        aspectOf.onPayResult(makeJP, (PayTrack.PayResult) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onPayFail(msg);
                    }
                }
            }
        });
    }

    @PayTrack.PayResult(result = 2)
    public void onPayCancel() {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_7, this, this);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$6;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onPayCancel", new Class[0]).getAnnotation(PayTrack.PayResult.class);
            ajc$anno$6 = annotation;
        }
        aspectOf.onPayResult(makeJP, (PayTrack.PayResult) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onPayCancel();
                    }
                }
            }
        });
    }

    @PayTrack.PayResult(result = 3)
    public void onPayUnknown() {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_8, this, this);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$7;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onPayUnknown", new Class[0]).getAnnotation(PayTrack.PayResult.class);
            ajc$anno$7 = annotation;
        }
        aspectOf.onPayResult(makeJP, (PayTrack.PayResult) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onPayUnknown();
                    }
                }
            }
        });
    }

    @SDKTrack.InitEagle(result = 1)
    public void onEagleInitSuc() {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_9, this, this);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$8;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onEagleInitSuc", new Class[0]).getAnnotation(SDKTrack.InitEagle.class);
            ajc$anno$8 = annotation;
        }
        aspectOf.onInitEagle(makeJP, (SDKTrack.InitEagle) annotation);
        Log.m602w(Constants.TAG, "onEagleInitSuc");
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onEagleInitSuc();
                        event.onInitSuccess();
                    }
                }
            }
        });
    }

    @SDKTrack.InitEagle(result = 0)
    public void trackEagleInitFail(String msg) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_10, (Object) this, (Object) this, (Object) msg);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$9;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("trackEagleInitFail", new Class[]{String.class}).getAnnotation(SDKTrack.InitEagle.class);
            ajc$anno$9 = annotation;
        }
        aspectOf.onInitEagle(makeJP, (SDKTrack.InitEagle) annotation);
        Log.m602w(Constants.TAG, "onEagleInitFail:" + msg);
    }

    public void onEagleInitFail(String msg) {
        trackEagleInitFail(msg);
        final String result = ResPluginUtil.getStringByName("xeagle_init_fail");
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onEagleInitFail(result);
                        event.onInitFail(result);
                    }
                }
            }
        });
    }

    @LoginTrack.EagleLogin(result = 0)
    public void onEagleLoginFail(final String msg) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_11, (Object) this, (Object) this, (Object) msg);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$10;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onEagleLoginFail", new Class[]{String.class}).getAnnotation(LoginTrack.EagleLogin.class);
            ajc$anno$10 = annotation;
        }
        aspectOf.onEagleLogin(makeJP, (LoginTrack.EagleLogin) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onLoginFail(msg);
                    }
                }
            }
        });
    }

    @LoginTrack.EagleLogin(result = 1)
    public void onLoginSuccess(final EagleToken token) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_12, (Object) this, (Object) this, (Object) token);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$11;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onLoginSuccess", new Class[]{EagleToken.class}).getAnnotation(LoginTrack.EagleLogin.class);
            ajc$anno$11 = annotation;
        }
        aspectOf.onEagleLogin(makeJP, (LoginTrack.EagleLogin) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onLoginSuccess(token);
                    }
                }
            }
        });
    }

    @PayTrack.OrderResult(result = 0)
    public void onEagleOrderFail(final String msg) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_13, (Object) this, (Object) this, (Object) msg);
        TrackAspect aspectOf = TrackAspect.aspectOf();
        Annotation annotation = ajc$anno$12;
        if (annotation == null) {
            annotation = DispatchEagleEvent.class.getDeclaredMethod("onEagleOrderFail", new Class[]{String.class}).getAnnotation(PayTrack.OrderResult.class);
            ajc$anno$12 = annotation;
        }
        aspectOf.onOrderResult(makeJP, (PayTrack.OrderResult) annotation);
        runOnMainThread(new Runnable() {
            public void run() {
                for (EagleEvent event : DispatchEagleEvent.this.events) {
                    if (!DispatchEagleEvent.this.interceptEvents.contains(event)) {
                        event.onPayFail(msg);
                    }
                }
            }
        });
    }

    public void runOnMainThread(Runnable runnable) {
        if (this.mainThreadHandler != null) {
            this.mainThreadHandler.post(runnable);
        }
    }
}
