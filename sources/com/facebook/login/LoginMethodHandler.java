package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

abstract class LoginMethodHandler implements Parcelable {
    protected LoginClient loginClient;
    Map<String, String> methodLoggingExtras;

    /* access modifiers changed from: package-private */
    public abstract String getNameForLogging();

    /* access modifiers changed from: package-private */
    public abstract boolean tryAuthorize(LoginClient.Request request);

    LoginMethodHandler(LoginClient loginClient2) {
        this.loginClient = loginClient2;
    }

    LoginMethodHandler(Parcel source) {
        this.methodLoggingExtras = Utility.readStringMapFromParcel(source);
    }

    /* access modifiers changed from: package-private */
    public void setLoginClient(LoginClient loginClient2) {
        if (this.loginClient != null) {
            throw new FacebookException("Can't set LoginClient if it is already set.");
        }
        this.loginClient = loginClient2;
    }

    /* access modifiers changed from: package-private */
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean needsInternetPermission() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
    }

    /* access modifiers changed from: package-private */
    public void putChallengeParam(JSONObject param) throws JSONException {
    }

    /* access modifiers changed from: protected */
    public String getClientState(String authId) {
        JSONObject param = new JSONObject();
        try {
            param.put("0_auth_logger_id", authId);
            param.put("3_method", getNameForLogging());
            putChallengeParam(param);
        } catch (JSONException e) {
            Log.w("LoginMethodHandler", "Error creating client state json: " + e.getMessage());
        }
        return param.toString();
    }

    /* access modifiers changed from: protected */
    public void addLoggingExtra(String key, Object value) {
        if (this.methodLoggingExtras == null) {
            this.methodLoggingExtras = new HashMap();
        }
        this.methodLoggingExtras.put(key, value == null ? null : value.toString());
    }

    /* access modifiers changed from: protected */
    public void logWebLoginCompleted(String e2e) {
        String applicationId = this.loginClient.getPendingRequest().getApplicationId();
        InternalAppEventsLogger logger = new InternalAppEventsLogger(this.loginClient.getActivity(), applicationId);
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_WEB_LOGIN_E2E, e2e);
        parameters.putLong(AnalyticsEvents.PARAMETER_WEB_LOGIN_SWITCHBACK_TIME, System.currentTimeMillis());
        parameters.putString("app_id", applicationId);
        logger.logEventImplicitly(AnalyticsEvents.EVENT_WEB_LOGIN_COMPLETE, (Double) null, parameters);
    }

    static AccessToken createAccessTokenFromNativeLogin(Bundle bundle, AccessTokenSource source, String applicationId) {
        Date expires = Utility.getBundleLongAsDate(bundle, NativeProtocol.EXTRA_EXPIRES_SECONDS_SINCE_EPOCH, new Date(0));
        ArrayList<String> permissions = bundle.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
        String token = bundle.getString(NativeProtocol.EXTRA_ACCESS_TOKEN);
        Date dataAccessExpirationTime = Utility.getBundleLongAsDate(bundle, NativeProtocol.EXTRA_DATA_ACCESS_EXPIRATION_TIME, new Date(0));
        if (Utility.isNullOrEmpty(token)) {
            return null;
        }
        return new AccessToken(token, applicationId, bundle.getString(NativeProtocol.EXTRA_USER_ID), permissions, (Collection<String>) null, (Collection<String>) null, source, expires, new Date(), dataAccessExpirationTime);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r20v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.AccessToken createAccessTokenFromWebBundle(java.util.Collection<java.lang.String> r20, android.os.Bundle r21, com.facebook.AccessTokenSource r22, java.lang.String r23) throws com.facebook.FacebookException {
        /*
            java.lang.String r2 = "expires_in"
            java.util.Date r4 = new java.util.Date
            r4.<init>()
            r0 = r21
            java.util.Date r10 = com.facebook.internal.Utility.getBundleLongAsDate(r0, r2, r4)
            java.lang.String r2 = "access_token"
            r0 = r21
            java.lang.String r3 = r0.getString(r2)
            java.lang.String r2 = "data_access_expiration_time"
            java.util.Date r4 = new java.util.Date
            r18 = 0
            r0 = r18
            r4.<init>(r0)
            r0 = r21
            java.util.Date r12 = com.facebook.internal.Utility.getBundleLongAsDate(r0, r2, r4)
            java.lang.String r2 = "granted_scopes"
            r0 = r21
            java.lang.String r15 = r0.getString(r2)
            boolean r2 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r15)
            if (r2 != 0) goto L_0x0045
            java.util.ArrayList r20 = new java.util.ArrayList
            java.lang.String r2 = ","
            java.lang.String[] r2 = r15.split(r2)
            java.util.List r2 = java.util.Arrays.asList(r2)
            r0 = r20
            r0.<init>(r2)
        L_0x0045:
            java.lang.String r2 = "denied_scopes"
            r0 = r21
            java.lang.String r13 = r0.getString(r2)
            r7 = 0
            boolean r2 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r13)
            if (r2 != 0) goto L_0x0063
            java.util.ArrayList r7 = new java.util.ArrayList
            java.lang.String r2 = ","
            java.lang.String[] r2 = r13.split(r2)
            java.util.List r2 = java.util.Arrays.asList(r2)
            r7.<init>(r2)
        L_0x0063:
            java.lang.String r2 = "expired_scopes"
            r0 = r21
            java.lang.String r14 = r0.getString(r2)
            r8 = 0
            boolean r2 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r14)
            if (r2 != 0) goto L_0x0081
            java.util.ArrayList r8 = new java.util.ArrayList
            java.lang.String r2 = ","
            java.lang.String[] r2 = r14.split(r2)
            java.util.List r2 = java.util.Arrays.asList(r2)
            r8.<init>(r2)
        L_0x0081:
            boolean r2 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r3)
            if (r2 == 0) goto L_0x0089
            r2 = 0
        L_0x0088:
            return r2
        L_0x0089:
            java.lang.String r2 = "signed_request"
            r0 = r21
            java.lang.String r16 = r0.getString(r2)
            java.lang.String r5 = getUserIDFromSignedRequest(r16)
            com.facebook.AccessToken r2 = new com.facebook.AccessToken
            java.util.Date r11 = new java.util.Date
            r11.<init>()
            r4 = r23
            r6 = r20
            r9 = r22
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            goto L_0x0088
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.LoginMethodHandler.createAccessTokenFromWebBundle(java.util.Collection, android.os.Bundle, com.facebook.AccessTokenSource, java.lang.String):com.facebook.AccessToken");
    }

    static String getUserIDFromSignedRequest(String signedRequest) throws FacebookException {
        if (signedRequest == null || signedRequest.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            String[] signatureAndPayload = signedRequest.split("\\.");
            if (signatureAndPayload.length == 2) {
                return new JSONObject(new String(Base64.decode(signatureAndPayload[1], 0), "UTF-8")).getString("user_id");
            }
        } catch (UnsupportedEncodingException | JSONException e) {
        }
        throw new FacebookException("Failed to retrieve user_id from signed_request");
    }

    public void writeToParcel(Parcel dest, int flags) {
        Utility.writeStringMapToParcel(dest, this.methodLoggingExtras);
    }
}
