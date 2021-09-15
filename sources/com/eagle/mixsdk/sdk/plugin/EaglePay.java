package com.eagle.mixsdk.sdk.plugin;

import android.app.Activity;
import android.text.TextUtils;
import com.eagle.mixsdk.aspectj.PayTrack;
import com.eagle.mixsdk.aspectj.TrackAspect;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IPay;
import com.eagle.mixsdk.sdk.PayParams;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.dialog.LoadingDialog;
import com.eagle.mixsdk.sdk.impl.DefaultPay;
import com.eagle.mixsdk.sdk.impl.listeners.IPayVerifyListener;
import com.eagle.mixsdk.sdk.impl.listeners.ISDKOrderIDListener;
import com.eagle.mixsdk.sdk.listeners.EaglePayVerifyListener;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.verify.EagleOrder;
import com.eagle.mixsdk.sdk.verify.EagleProxy;
import com.facebook.appevents.AppEventsConstants;
import java.lang.annotation.Annotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONObject;

public class EaglePay {
    private static Annotation ajc$anno$0;
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static EaglePay instance;
    private boolean canShowLoading = true;
    private boolean isShowLoading = false;
    private long lastTime = 0;
    private IPay mPay;
    private LoadingDialog processTip;

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("EaglePay.java", EaglePay.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig("2", "startGetOrderFormClient", "com.eagle.mixsdk.sdk.plugin.EaglePay", "com.eagle.mixsdk.sdk.PayParams", "data", "", "void"), 101);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig("2", "starPay", "com.eagle.mixsdk.sdk.plugin.EaglePay", "com.eagle.mixsdk.sdk.PayParams", "data", "", "void"), 147);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "payVerify", "com.eagle.mixsdk.sdk.plugin.EaglePay", "org.json.JSONObject:com.eagle.mixsdk.sdk.listeners.EaglePayVerifyListener", "data:listener", "", "void"), 155);
    }

    private EaglePay() {
        String payLoading = EagleSDK.getInstance().getSDKParams().getString("Pay_Loading");
        if (!TextUtils.isEmpty(payLoading)) {
            this.canShowLoading = "true".equals(payLoading);
        }
    }

    public static EaglePay getInstance() {
        if (instance == null) {
            instance = new EaglePay();
        }
        return instance;
    }

    public void init() {
        this.mPay = (IPay) PluginFactory.getInstance().initPlugin(2);
        if (this.mPay == null) {
            this.mPay = new DefaultPay();
        }
    }

    public boolean isSupport(String method) {
        return this.mPay != null && this.mPay.isSupportMethod(method);
    }

    public void pay(PayParams data) {
        if (this.mPay != null) {
            long l = System.currentTimeMillis();
            if (this.lastTime <= 0 || l - this.lastTime >= 1000) {
                this.lastTime = System.currentTimeMillis();
                Log.m598d(Constants.TAG, "****PayParams Print Begin****");
                Log.m598d(Constants.TAG, "productId=" + data.getProductId());
                Log.m598d(Constants.TAG, "productName=" + data.getProductName());
                Log.m598d(Constants.TAG, "productDesc=" + data.getProductDesc());
                Log.m598d(Constants.TAG, "price=" + data.getPrice());
                Log.m598d(Constants.TAG, "coinNum=" + data.getCoinNum());
                Log.m598d(Constants.TAG, "serverId=" + data.getServerId());
                Log.m598d(Constants.TAG, "serverName=" + data.getServerName());
                Log.m598d(Constants.TAG, "gameName=" + data.getGameName());
                Log.m598d(Constants.TAG, "roleId=" + data.getRoleId());
                Log.m598d(Constants.TAG, "roleName=" + data.getRoleName());
                Log.m598d(Constants.TAG, "roleLevel=" + data.getRoleLevel());
                Log.m598d(Constants.TAG, "vip=" + data.getVip());
                Log.m598d(Constants.TAG, "orderID=" + data.getOrderID());
                Log.m598d(Constants.TAG, "cpOrderID=" + data.getGameOrderID());
                Log.m598d(Constants.TAG, "extension=" + data.getExtension());
                Log.m598d(Constants.TAG, "****PayParams Print End****");
                startGetOrderFormClient(data);
                return;
            }
            Log.m599e(Constants.TAG, "login too fast " + (l - this.lastTime));
        }
    }

    @PayTrack.StarOrder
    private void startGetOrderFormClient(final PayParams data) {
        TrackAspect.aspectOf().onStartOrder(Factory.makeJP(ajc$tjp_0, (Object) this, (Object) this, (Object) data));
        EagleSDK.getInstance().setPayParams(data);
        if (this.canShowLoading) {
            Activity activity = EagleSDK.getInstance().getContext();
            if (this.processTip == null) {
                this.processTip = new LoadingDialog(activity);
                this.processTip.setCancelable(false);
                this.processTip.setCanceledOnTouchOutside(false);
            }
            if (!this.isShowLoading && !activity.isFinishing()) {
                this.processTip.show();
                this.isShowLoading = true;
            }
        }
        EagleProxy.getOrder(data, new ISDKOrderIDListener() {
            public void onSuccess(EagleOrder order) {
                EaglePay.this.dismissLoading();
                data.setOrderID(order.getOrder());
                data.setEagleExtension(order.getExtension());
                data.setPayNotifyUrl(order.getNotifyUrl());
                EagleSDK.getInstance().setPayParams(data);
                EaglePay.this.starPay(data);
            }

            public void onFailed(int code, String msg) {
                EaglePay.this.dismissLoading();
                EagleSDK.getInstance().onEagleOrderFail(msg);
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissLoading() {
        if (this.processTip != null) {
            this.processTip.dismiss();
        }
        this.isShowLoading = false;
    }

    /* access modifiers changed from: private */
    @PayTrack.StarPay
    @PayTrack.OrderResult(result = 1)
    public void starPay(PayParams data) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_1, (Object) this, (Object) this, (Object) data);
        try {
            TrackAspect aspectOf = TrackAspect.aspectOf();
            Annotation annotation = ajc$anno$0;
            if (annotation == null) {
                annotation = EaglePay.class.getDeclaredMethod("starPay", new Class[]{PayParams.class}).getAnnotation(PayTrack.OrderResult.class);
                ajc$anno$0 = annotation;
            }
            aspectOf.onOrderResult(makeJP, (PayTrack.OrderResult) annotation);
            if (this.mPay != null) {
                this.mPay.pay(data);
            }
        } finally {
            TrackAspect.aspectOf().onStarPay(makeJP);
        }
    }

    @PayTrack.StarPayVerify
    public void payVerify(JSONObject data, final EaglePayVerifyListener listener) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_2, this, this, data, listener);
        if (data != null) {
            try {
                EagleProxy.payVerify(data, new IPayVerifyListener() {
                    private static Annotation ajc$anno$0;
                    private static Annotation ajc$anno$1;
                    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
                    private static final JoinPoint.StaticPart ajc$tjp_1 = null;

                    static {
                        ajc$preClinit();
                    }

                    private static void ajc$preClinit() {
                        Factory factory = new Factory("EaglePay.java", C11532.class);
                        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onVerifySuccess", "com.eagle.mixsdk.sdk.plugin.EaglePay$2", "java.lang.Object", "obj", "", "void"), 163);
                        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onVerifyFail", "com.eagle.mixsdk.sdk.plugin.EaglePay$2", "java.lang.String:java.lang.Object", "msg:obj", "", "void"), 175);
                    }

                    @PayTrack.PayVerifyResult(result = 1)
                    public void onVerifySuccess(Object obj) {
                        JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, (Object) this, (Object) this, obj);
                        TrackAspect aspectOf = TrackAspect.aspectOf();
                        Annotation annotation = ajc$anno$0;
                        if (annotation == null) {
                            annotation = C11532.class.getDeclaredMethod("onVerifySuccess", new Class[]{Object.class}).getAnnotation(PayTrack.PayVerifyResult.class);
                            ajc$anno$0 = annotation;
                        }
                        aspectOf.onPayVerifyResult(makeJP, (PayTrack.PayVerifyResult) annotation);
                        if (listener == null) {
                            return;
                        }
                        if (obj == null || !(obj instanceof JSONObject)) {
                            listener.onVerifySuccess((JSONObject) null);
                        } else {
                            listener.onVerifySuccess((JSONObject) obj);
                        }
                    }

                    @PayTrack.PayVerifyResult(result = 0)
                    public void onVerifyFail(String msg, Object obj) {
                        JoinPoint makeJP = Factory.makeJP(ajc$tjp_1, this, this, msg, obj);
                        TrackAspect aspectOf = TrackAspect.aspectOf();
                        Annotation annotation = ajc$anno$1;
                        if (annotation == null) {
                            annotation = C11532.class.getDeclaredMethod("onVerifyFail", new Class[]{String.class, Object.class}).getAnnotation(PayTrack.PayVerifyResult.class);
                            ajc$anno$1 = annotation;
                        }
                        aspectOf.onPayVerifyResult(makeJP, (PayTrack.PayVerifyResult) annotation);
                        if (listener != null) {
                            listener.onVerifyFail(msg);
                        }
                    }
                });
            } catch (Throwable th) {
                TrackAspect.aspectOf().onStarPayVerify(makeJP);
                throw th;
            }
        }
        TrackAspect.aspectOf().onStarPayVerify(makeJP);
    }
}
