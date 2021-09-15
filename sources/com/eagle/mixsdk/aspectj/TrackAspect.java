package com.eagle.mixsdk.aspectj;

import android.util.Log;
import com.eagle.mixsdk.aspectj.LoginTrack;
import com.eagle.mixsdk.aspectj.PayTrack;
import com.eagle.mixsdk.aspectj.SDKTrack;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.track.EagleTrackEvent;
import com.eagle.mixsdk.track.EagleTrackManager;
import com.eagle.mixsdk.track.EagleTrackReport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TrackAspect {
    private static final String TAG = "EagleSDK-Aspect";
    private static Throwable ajc$initFailureCause;
    public static final TrackAspect ajc$perSingletonInstance = null;

    static {
        try {
            ajc$postClinit();
        } catch (Throwable th) {
            ajc$initFailureCause = th;
        }
    }

    private static void ajc$postClinit() {
        ajc$perSingletonInstance = new TrackAspect();
    }

    public static TrackAspect aspectOf() {
        if (ajc$perSingletonInstance != null) {
            return ajc$perSingletonInstance;
        }
        throw new NoAspectBoundException("com.eagle.mixsdk.aspectj.TrackAspect", ajc$initFailureCause);
    }

    public static boolean hasAspect() {
        return ajc$perSingletonInstance != null;
    }

    @After("execution(* com.eagle.mixsdk.sdk.EagleSDK.onProxyCreate(..))")
    public void onApplicationCreate(JoinPoint joinPoint) {
        try {
            Log.d(TAG, "onApplicationCreate " + joinPoint.getSignature().toString());
            new Thread(new Runnable() {
                public void run() {
                    EagleTrackReport.getInstance().init(EagleSDK.getInstance().getApplication());
                    EagleTrackManager.getInstance().addTracks(EagleTrackEvent.getInstance());
                    EagleTrackEvent.getInstance().obtainDid();
                    EagleTrackEvent.getInstance().onEmulatorEvent(EagleSDK.getInstance().getApplication());
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before("execution(* com.eagle.mixsdk.sdk.plugin.EagleUser.init(..))")
    public void onStartInit(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "onStartInit " + joinPoint.getSignature().toString());
        EagleTrackEvent.getInstance().onTrackDeviceInfo();
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.SDKTrack.InitSDK * *..*.*(..))&& @annotation(result)")
    public void onInitSDK(JoinPoint joinPoint, SDKTrack.InitSDK result) throws Throwable {
        String failMsg = "";
        if (!(result.result() != 0 || joinPoint.getArgs() == null || joinPoint.getArgs().length == 0)) {
            failMsg = (String) joinPoint.getArgs()[0];
        }
        EagleTrackManager.getInstance().onInitSDK(result.result(), failMsg);
        Log.d(TAG, "onInitSDK: " + joinPoint.getSignature().toString());
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.SDKTrack.InitEagle * *..*.*(..))&& @annotation(result)")
    public void onInitEagle(JoinPoint joinPoint, SDKTrack.InitEagle result) throws Throwable {
        String failMsg = "";
        if (!(result.result() != 0 || joinPoint.getArgs() == null || joinPoint.getArgs().length == 0)) {
            failMsg = (String) joinPoint.getArgs()[0];
        }
        EagleTrackManager.getInstance().onInitEagle(result.result(), failMsg);
        Log.d(TAG, "onInitEagle: " + joinPoint.getSignature().toString());
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.LoginTrack.StartLogin * *..*.*(..))")
    public void onStartLogin(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "onStartLogin: " + joinPoint.getSignature().toString());
        EagleTrackManager.getInstance().onStartLogin();
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.LoginTrack.ShowLoginPage * *..*.*(..))")
    public void onShowLogin(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "onShowLogin: " + joinPoint.getSignature().toString());
        EagleTrackManager.getInstance().onShowLoginPage();
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.LoginTrack.EagleLogin * *..*.*(..))&& @annotation(result)")
    public void onEagleLogin(JoinPoint joinPoint, LoginTrack.EagleLogin result) throws Throwable {
        String failMsg = "";
        if (!(result.result() != 0 || joinPoint.getArgs() == null || joinPoint.getArgs().length == 0)) {
            failMsg = (String) joinPoint.getArgs()[0];
        }
        EagleTrackManager.getInstance().onEagleLogin(result.result(), failMsg);
        Log.d(TAG, "onEagleLogin: " + joinPoint.getSignature().toString());
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.LoginTrack.SDKLogin * *..*.*(..))&& @annotation(result)")
    public void onSDKLogin(JoinPoint joinPoint, LoginTrack.SDKLogin result) throws Throwable {
        String failMsg = "";
        if (!(result.result() != 0 || joinPoint.getArgs() == null || joinPoint.getArgs().length == 0)) {
            failMsg = (String) joinPoint.getArgs()[0];
        }
        EagleTrackManager.getInstance().onSDKLogin(result.result(), failMsg);
        Log.d(TAG, "onSDKLogin: " + joinPoint.getSignature().toString());
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.LoginTrack.NotifyGameLoginResult * *..*.*(..))")
    public void onNotifyGameLoginResult(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "onNotifyGameLoginResult: " + joinPoint.getSignature().toString());
        EagleTrackManager.getInstance().onNotifyLogin();
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.LoginTrack.Logout * *..*.*(..))")
    public void onLogout(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "onLogout: " + joinPoint.getSignature().toString());
        EagleTrackManager.getInstance().onLogout();
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.LoginTrack.StartPlayerAntiAddictionInfo * *..*.*(..))")
    public void onStartPlayerAntiAddictionInfo(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "StartPlayerAntiAddictionInfo: " + joinPoint.getSignature().toString());
        EagleTrackManager.getInstance().onStartPlayerAntiAddictionInfo();
    }

    @After("execution(@com.eagle.mixsdk.aspectj.LoginTrack.PlayerAntiAddictionInfoResult * *..*.*(..))")
    public void onPlayerAntiAddictionInfoResult(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "onPlayerAntiAddictionInfoResult: " + joinPoint.getSignature().toString());
        EagleTrackManager.getInstance().onPlayerAntiAddictionInfoResult();
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.SDKTrack.GameEvent * *..*.*(..))")
    public void onGameEvent(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
            Log.d(TAG, "onGameEvent getArgs is null");
            return;
        }
        EagleTrackManager.getInstance().onGameEvent(joinPoint.getArgs()[0]);
        Log.d(TAG, "onGameEvent: " + joinPoint.getSignature().toString());
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.PayTrack.StarOrder * *..*.*(..))")
    public void onStartOrder(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
            Log.d(TAG, "onStartOrder getArgs is null");
            return;
        }
        EagleTrackManager.getInstance().onStartOrder(joinPoint.getArgs()[0]);
        Log.d(TAG, "onStartOrder: " + joinPoint.getSignature().toString());
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.PayTrack.OrderResult * *..*.*(..))&& @annotation(result)")
    public void onOrderResult(JoinPoint joinPoint, PayTrack.OrderResult result) throws Throwable {
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
            Log.d(TAG, "onOrderResult getArgs is null");
            return;
        }
        EagleTrackManager.getInstance().onOrderResult(result.result(), joinPoint.getArgs()[0]);
        Log.d(TAG, "onOrderResult: " + joinPoint.getSignature().toString());
    }

    @After("execution(@com.eagle.mixsdk.aspectj.PayTrack.StarPay * *..*.*(..))")
    public void onStarPay(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
            Log.d(TAG, "onStarPay getArgs is null");
            return;
        }
        EagleTrackManager.getInstance().onStartPay(joinPoint.getArgs()[0]);
        Log.d(TAG, "onStarPay: " + joinPoint.getSignature().toString());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r4v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.aspectj.lang.annotation.Before("execution(@com.eagle.mixsdk.aspectj.PayTrack.PayResult * *..*.*(..))&& @annotation(result)")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPayResult(org.aspectj.lang.JoinPoint r8, com.eagle.mixsdk.aspectj.PayTrack.PayResult r9) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r3 = ""
            java.lang.Object[] r4 = r8.getArgs()     // Catch:{ Exception -> 0x0046 }
            if (r4 == 0) goto L_0x001a
            java.lang.Object[] r4 = r8.getArgs()     // Catch:{ Exception -> 0x0046 }
            int r4 = r4.length     // Catch:{ Exception -> 0x0046 }
            if (r4 == 0) goto L_0x001a
            java.lang.Object[] r4 = r8.getArgs()     // Catch:{ Exception -> 0x0046 }
            r5 = 0
            r4 = r4[r5]     // Catch:{ Exception -> 0x0046 }
            r0 = r4
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0046 }
            r3 = r0
        L_0x001a:
            com.eagle.mixsdk.track.EagleTrackManager r4 = com.eagle.mixsdk.track.EagleTrackManager.getInstance()
            int r5 = r9.result()
            r4.onPayResult(r5, r3)
            org.aspectj.lang.Signature r4 = r8.getSignature()
            java.lang.String r2 = r4.toString()
            java.lang.String r4 = "EagleSDK-Aspect"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "onPayResult: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r2)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r4, r5)
            return
        L_0x0046:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.aspectj.TrackAspect.onPayResult(org.aspectj.lang.JoinPoint, com.eagle.mixsdk.aspectj.PayTrack$PayResult):void");
    }

    @After("execution(@com.eagle.mixsdk.aspectj.PayTrack.StarPayVerify * *..*.*(..))")
    public void onStarPayVerify(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
            Log.d(TAG, "onStartPayVerify getArgs is null");
            return;
        }
        EagleTrackManager.getInstance().onStartPayVerify(joinPoint.getArgs()[0]);
        Log.d(TAG, "onStartPayVerify: " + joinPoint.getSignature().toString());
    }

    @Before("execution(@com.eagle.mixsdk.aspectj.PayTrack.PayVerifyResult * *..*.*(..))&& @annotation(result)")
    public void onPayVerifyResult(JoinPoint joinPoint, PayTrack.PayVerifyResult result) throws Throwable {
        int code = result.result();
        Object object = null;
        try {
            if (!(joinPoint.getArgs() == null || joinPoint.getArgs().length == 0)) {
                object = code == 1 ? joinPoint.getArgs()[0] : joinPoint.getArgs();
            }
            Log.d(TAG, "onPayVerifyResult: " + joinPoint.getSignature().toString());
            EagleTrackManager.getInstance().onPayVerifyResult(code, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
