package com.appsflyer.internal.referrer;

import android.content.Context;
import android.support.annotation.NonNull;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.appsflyer.AFLogger;
import com.appsflyer.AndroidUtils;
import com.appsflyer.AppsFlyerLibCore;
import java.util.HashMap;
import java.util.Map;

public class GoogleReferrer {
    public final Map<String, Object> newMap = new HashMap();
    public final Map<String, Object> oldMap = new HashMap();

    public static boolean allow(@NonNull AppsFlyerLibCore appsFlyerLibCore, @NonNull Context context) {
        if (appsFlyerLibCore.getLaunchCounter(AppsFlyerLibCore.getSharedPreferences(context), false) > 2) {
            AFLogger.afRDLog("Install referrer will not load, the counter > 2, ");
            return false;
        }
        try {
            Class.forName("com.android.installreferrer.api.InstallReferrerClient");
            if (AndroidUtils.isPermissionAvailable(context, "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE")) {
                AFLogger.afDebugLog("Install referrer is allowed");
                return true;
            }
            AFLogger.afDebugLog("Install referrer is not allowed");
            return false;
        } catch (ClassNotFoundException e) {
            AFLogger.afRDLog(new StringBuilder("Class ").append("com.android.installreferrer.api.InstallReferrerClient").append(" not found").toString());
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest : ".concat(String.valueOf("com.android.installreferrer.api.InstallReferrerClient")), th);
            return false;
        }
    }

    public void start(Context context, @NonNull final Runnable runnable) {
        try {
            final InstallReferrerClient build = InstallReferrerClient.newBuilder(context).build();
            AFLogger.afDebugLog("Connecting to Install Referrer Library...");
            build.startConnection(new InstallReferrerStateListener() {
                public final void onInstallReferrerSetupFinished(int i) {
                    GoogleReferrer.this.oldMap.put("code", String.valueOf(i));
                    GoogleReferrer.this.newMap.put("source", Payload.SOURCE_GOOGLE);
                    GoogleReferrer.this.newMap.putAll(new MandatoryFields());
                    switch (i) {
                        case -1:
                            AFLogger.afWarnLog("InstallReferrer SERVICE_DISCONNECTED");
                            GoogleReferrer.this.newMap.put(Payload.RESPONSE, Payload.RESPONSE_SERVICE_DISCONNECTED);
                            break;
                        case 0:
                            GoogleReferrer.this.newMap.put(Payload.RESPONSE, Payload.RESPONSE_OK);
                            try {
                                AFLogger.afDebugLog("InstallReferrer connected");
                                if (!build.isReady()) {
                                    AFLogger.afWarnLog("ReferrerClient: InstallReferrer is not ready");
                                    GoogleReferrer.this.oldMap.put("err", "ReferrerClient: InstallReferrer is not ready");
                                    break;
                                } else {
                                    ReferrerDetails installReferrer = build.getInstallReferrer();
                                    String installReferrer2 = installReferrer.getInstallReferrer();
                                    if (installReferrer2 != null) {
                                        GoogleReferrer.this.oldMap.put("val", installReferrer2);
                                        GoogleReferrer.this.newMap.put(Payload.REFERRER, installReferrer2);
                                    }
                                    long referrerClickTimestampSeconds = installReferrer.getReferrerClickTimestampSeconds();
                                    GoogleReferrer.this.oldMap.put("clk", Long.toString(referrerClickTimestampSeconds));
                                    GoogleReferrer.this.newMap.put(Payload.CLICK_TS, Long.valueOf(referrerClickTimestampSeconds));
                                    long installBeginTimestampSeconds = installReferrer.getInstallBeginTimestampSeconds();
                                    GoogleReferrer.this.oldMap.put("install", Long.toString(installBeginTimestampSeconds));
                                    GoogleReferrer.this.newMap.put(Payload.INSTALL_BEGIN_TS, Long.valueOf(installBeginTimestampSeconds));
                                    try {
                                        GoogleReferrer.this.oldMap.put(Payload.INSTANT, Boolean.valueOf(installReferrer.getGooglePlayInstantParam()));
                                        break;
                                    } catch (NoSuchMethodError e) {
                                        break;
                                    }
                                }
                            } catch (Throwable th) {
                                AFLogger.afWarnLog(new StringBuilder("Failed to get install referrer: ").append(th.getMessage()).toString());
                                GoogleReferrer.this.oldMap.put("err", th.getMessage());
                                break;
                            }
                        case 1:
                            GoogleReferrer.this.newMap.put(Payload.RESPONSE, Payload.RESPONSE_SERVICE_UNAVAILABLE);
                            AFLogger.afWarnLog("InstallReferrer not supported");
                            break;
                        case 2:
                            AFLogger.afWarnLog("InstallReferrer FEATURE_NOT_SUPPORTED");
                            GoogleReferrer.this.newMap.put(Payload.RESPONSE, Payload.RESPONSE_FEATURE_NOT_SUPPORTED);
                            break;
                        case 3:
                            AFLogger.afWarnLog("InstallReferrer DEVELOPER_ERROR");
                            GoogleReferrer.this.newMap.put(Payload.RESPONSE, Payload.RESPONSE_DEVELOPER_ERROR);
                            break;
                        default:
                            AFLogger.afWarnLog("responseCode not found.");
                            break;
                    }
                    AFLogger.afDebugLog("Install Referrer collected locally");
                    runnable.run();
                    build.endConnection();
                }

                public final void onInstallReferrerServiceDisconnected() {
                    AFLogger.afDebugLog("Install Referrer service disconnected");
                }
            });
        } catch (Throwable th) {
            AFLogger.afErrorLog("referrerClient -> startConnection", th);
        }
    }
}
