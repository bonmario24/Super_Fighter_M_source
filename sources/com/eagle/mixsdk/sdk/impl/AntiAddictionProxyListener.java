package com.eagle.mixsdk.sdk.impl;

import android.text.TextUtils;
import android.view.View;
import com.doraemon.p027eg.CheckUtils;
import com.eagle.mixsdk.aspectj.LoginTrack;
import com.eagle.mixsdk.aspectj.TrackAspect;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.dialog.GameNoticeDialog;
import com.eagle.mixsdk.sdk.did.utils.DIDMD5Util;
import com.eagle.mixsdk.sdk.listeners.EagleAntiAddictionListener;
import com.eagle.mixsdk.sdk.listeners.EagleCertificationInfoListener;
import com.eagle.mixsdk.sdk.plugin.EagleUser;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import com.eagle.mixsdk.sdk.verify.EagleAntiAddictionInfo;
import com.eagle.mixsdk.sdk.verify.EagleCertificationInfo;
import com.eagle.mixsdk.sdk.verify.EagleProxy;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.facebook.appevents.AppEventsConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.reflect.Factory;

public class AntiAddictionProxyListener implements EagleCertificationInfoListener {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    /* access modifiers changed from: private */
    public boolean isShowGameTips = false;
    /* access modifiers changed from: private */
    public EagleAntiAddictionListener mGameAntiAddictionListener;

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AntiAddictionProxyListener.java", AntiAddictionProxyListener.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, (Signature) factory.makeMethodSig(AppEventsConstants.EVENT_PARAM_VALUE_YES, "onAntiAddictionInfoResult", "com.eagle.mixsdk.sdk.impl.AntiAddictionProxyListener", "com.eagle.mixsdk.sdk.verify.EagleAntiAddictionInfo", "antiAddictionInfo", "", "void"), 49);
    }

    public AntiAddictionProxyListener(EagleAntiAddictionListener listener) {
        this.mGameAntiAddictionListener = listener;
    }

    private void sendGameAntiAddictionInfoResult(final EagleAntiAddictionInfo antiAddictionInfo) {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (AntiAddictionProxyListener.this.mGameAntiAddictionListener != null) {
                    AntiAddictionProxyListener.this.mGameAntiAddictionListener.onResult(antiAddictionInfo);
                }
            }
        });
    }

    @LoginTrack.PlayerAntiAddictionInfoResult
    public void onAntiAddictionInfoResult(EagleAntiAddictionInfo antiAddictionInfo) {
        JoinPoint makeJP = Factory.makeJP(ajc$tjp_0, (Object) this, (Object) this, (Object) antiAddictionInfo);
        try {
            EagleToken eagleToken = EagleUser.getInstance().getEagleToken();
            if (eagleToken != null) {
                eagleToken.setAntiAddictionInfo(antiAddictionInfo);
                if (antiAddictionInfo.getAntiAddictionState() != 1 || eagleToken.getAgeLimit() <= 0 || antiAddictionInfo.getPlayerAge() >= eagleToken.getAgeLimit()) {
                    sendGameAntiAddictionInfoResult(antiAddictionInfo);
                } else if (!this.isShowGameTips) {
                    this.isShowGameTips = true;
                    new GameNoticeDialog(EagleSDK.getInstance().getContext()).setNoticeContent(getAgeLimitTips(eagleToken.getAgeLimit())).setCancelable(false).setPositiveListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EagleSDK.getInstance().exitGame();
                            boolean unused = AntiAddictionProxyListener.this.isShowGameTips = false;
                        }
                    }).show();
                }
            }
        } finally {
            TrackAspect.aspectOf().onPlayerAntiAddictionInfoResult(makeJP);
        }
    }

    private String getAgeLimitTips(int ageLimit) {
        String tips = ResPluginUtil.getStringByName("xeagle_age_limit");
        if (TextUtils.isEmpty(tips)) {
            return tips;
        }
        return String.format(tips, new Object[]{Integer.valueOf(ageLimit)});
    }

    public void onResult(EagleCertificationInfo certificationInfo) {
        EagleToken eagleToken = EagleUser.getInstance().getEagleToken();
        if (eagleToken == null || eagleToken.getAntiAddictionSwitch() != 1) {
            if (eagleToken != null) {
                eagleToken.setCertificationInfo(certificationInfo);
            }
            createDefaultAntiAddictionInfoResult();
            return;
        }
        int antiAddictionState = EagleProxy.calculateCertificationInfo(certificationInfo.getCertificationStat(), certificationInfo.getPlayerAge());
        int playerAge = -1;
        String identity = "";
        String extInfo = "";
        if (antiAddictionState == 1) {
            if (CheckUtils.isNullOrEmpty(certificationInfo.getIdentity())) {
                System.out.println("antiAddictionState = 1 but identity is empty create new identity");
                certificationInfo.setIdentity(DIDMD5Util.MD5(eagleToken.getUserID() + "").toLowerCase());
            }
            playerAge = certificationInfo.getPlayerAge();
            identity = certificationInfo.getIdentity();
            extInfo = certificationInfo.getExtInfo();
        }
        eagleToken.setCertificationInfo(certificationInfo);
        onAntiAddictionInfoResult(new EagleAntiAddictionInfo(antiAddictionState, playerAge, identity, extInfo));
    }

    public void createDefaultAntiAddictionInfoResult() {
        sendGameAntiAddictionInfoResult(new EagleAntiAddictionInfo());
    }
}
