package com.facebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String APPLICATION_ID_KEY = "application_id";
    public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator() {
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }
    };
    private static final int CURRENT_JSON_FORMAT = 1;
    public static final String DATA_ACCESS_EXPIRATION_TIME = "data_access_expiration_time";
    private static final String DECLINED_PERMISSIONS_KEY = "declined_permissions";
    private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    private static final Date DEFAULT_EXPIRATION_TIME = MAX_DATE;
    private static final Date DEFAULT_LAST_REFRESH_TIME = new Date();
    private static final String EXPIRED_PERMISSIONS_KEY = "expired_permissions";
    private static final String EXPIRES_AT_KEY = "expires_at";
    public static final String EXPIRES_IN_KEY = "expires_in";
    private static final String LAST_REFRESH_KEY = "last_refresh";
    private static final Date MAX_DATE = new Date(Long.MAX_VALUE);
    private static final String PERMISSIONS_KEY = "permissions";
    private static final String SOURCE_KEY = "source";
    private static final String TOKEN_KEY = "token";
    public static final String USER_ID_KEY = "user_id";
    private static final String VERSION_KEY = "version";
    private final String applicationId;
    private final Date dataAccessExpirationTime;
    private final Set<String> declinedPermissions;
    private final Set<String> expiredPermissions;
    private final Date expires;
    private final Date lastRefresh;
    private final Set<String> permissions;
    private final AccessTokenSource source;
    private final String token;
    private final String userId;

    public interface AccessTokenCreationCallback {
        void onError(FacebookException facebookException);

        void onSuccess(AccessToken accessToken);
    }

    public interface AccessTokenRefreshCallback {
        void OnTokenRefreshFailed(FacebookException facebookException);

        void OnTokenRefreshed(AccessToken accessToken);
    }

    public AccessToken(String accessToken, String applicationId2, String userId2, @Nullable Collection<String> permissions2, @Nullable Collection<String> declinedPermissions2, @Nullable Collection<String> expiredPermissions2, @Nullable AccessTokenSource accessTokenSource, @Nullable Date expirationTime, @Nullable Date lastRefreshTime, @Nullable Date dataAccessExpirationTime2) {
        Validate.notNullOrEmpty(accessToken, "accessToken");
        Validate.notNullOrEmpty(applicationId2, "applicationId");
        Validate.notNullOrEmpty(userId2, "userId");
        this.expires = expirationTime == null ? DEFAULT_EXPIRATION_TIME : expirationTime;
        this.permissions = Collections.unmodifiableSet(permissions2 != null ? new HashSet(permissions2) : new HashSet());
        this.declinedPermissions = Collections.unmodifiableSet(declinedPermissions2 != null ? new HashSet(declinedPermissions2) : new HashSet());
        this.expiredPermissions = Collections.unmodifiableSet(expiredPermissions2 != null ? new HashSet(expiredPermissions2) : new HashSet());
        this.token = accessToken;
        this.source = accessTokenSource == null ? DEFAULT_ACCESS_TOKEN_SOURCE : accessTokenSource;
        this.lastRefresh = lastRefreshTime == null ? DEFAULT_LAST_REFRESH_TIME : lastRefreshTime;
        this.applicationId = applicationId2;
        this.userId = userId2;
        this.dataAccessExpirationTime = (dataAccessExpirationTime2 == null || dataAccessExpirationTime2.getTime() == 0) ? DEFAULT_EXPIRATION_TIME : dataAccessExpirationTime2;
    }

    public static AccessToken getCurrentAccessToken() {
        return AccessTokenManager.getInstance().getCurrentAccessToken();
    }

    public static boolean isCurrentAccessTokenActive() {
        AccessToken accessToken = AccessTokenManager.getInstance().getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

    public static boolean isDataAccessActive() {
        AccessToken accessToken = AccessTokenManager.getInstance().getCurrentAccessToken();
        return accessToken != null && !accessToken.isDataAccessExpired();
    }

    static void expireCurrentAccessToken() {
        AccessToken accessToken = AccessTokenManager.getInstance().getCurrentAccessToken();
        if (accessToken != null) {
            setCurrentAccessToken(createExpired(accessToken));
        }
    }

    public static void setCurrentAccessToken(AccessToken accessToken) {
        AccessTokenManager.getInstance().setCurrentAccessToken(accessToken);
    }

    public static void refreshCurrentAccessTokenAsync() {
        AccessTokenManager.getInstance().refreshCurrentAccessToken((AccessTokenRefreshCallback) null);
    }

    public static void refreshCurrentAccessTokenAsync(AccessTokenRefreshCallback callback) {
        AccessTokenManager.getInstance().refreshCurrentAccessToken(callback);
    }

    public String getToken() {
        return this.token;
    }

    public Date getExpires() {
        return this.expires;
    }

    public Date getDataAccessExpirationTime() {
        return this.dataAccessExpirationTime;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public Set<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }

    public Set<String> getExpiredPermissions() {
        return this.expiredPermissions;
    }

    public AccessTokenSource getSource() {
        return this.source;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getUserId() {
        return this.userId;
    }

    public static void createFromNativeLinkingIntent(Intent intent, final String applicationId2, final AccessTokenCreationCallback accessTokenCallback) {
        Validate.notNull(intent, "intent");
        if (intent.getExtras() == null) {
            accessTokenCallback.onError(new FacebookException("No extras found on intent"));
            return;
        }
        final Bundle extras = new Bundle(intent.getExtras());
        String accessToken = extras.getString("access_token");
        if (accessToken == null || accessToken.isEmpty()) {
            accessTokenCallback.onError(new FacebookException("No access token found on intent"));
            return;
        }
        String userId2 = extras.getString("user_id");
        if (userId2 == null || userId2.isEmpty()) {
            Utility.getGraphMeRequestWithCacheAsync(accessToken, new Utility.GraphMeRequestWithCacheCallback() {
                public void onSuccess(JSONObject userInfo) {
                    try {
                        extras.putString("user_id", userInfo.getString("id"));
                        accessTokenCallback.onSuccess(AccessToken.createFromBundle((List<String>) null, extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), applicationId2));
                    } catch (JSONException e) {
                        accessTokenCallback.onError(new FacebookException("Unable to generate access token due to missing user id"));
                    }
                }

                public void onFailure(FacebookException error) {
                    accessTokenCallback.onError(error);
                }
            });
        } else {
            accessTokenCallback.onSuccess(createFromBundle((List<String>) null, extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), applicationId2));
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{AccessToken");
        builder.append(" token:").append(tokenToString());
        appendPermissions(builder);
        builder.append("}");
        return builder.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccessToken)) {
            return false;
        }
        AccessToken o = (AccessToken) other;
        if (!this.expires.equals(o.expires) || !this.permissions.equals(o.permissions) || !this.declinedPermissions.equals(o.declinedPermissions) || !this.expiredPermissions.equals(o.expiredPermissions) || !this.token.equals(o.token) || this.source != o.source || !this.lastRefresh.equals(o.lastRefresh) || (this.applicationId != null ? !this.applicationId.equals(o.applicationId) : o.applicationId != null) || !this.userId.equals(o.userId) || !this.dataAccessExpirationTime.equals(o.dataAccessExpirationTime)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((this.expires.hashCode() + 527) * 31) + this.permissions.hashCode()) * 31) + this.declinedPermissions.hashCode()) * 31) + this.expiredPermissions.hashCode()) * 31) + this.token.hashCode()) * 31) + this.source.hashCode()) * 31) + this.lastRefresh.hashCode()) * 31) + (this.applicationId == null ? 0 : this.applicationId.hashCode())) * 31) + this.userId.hashCode()) * 31) + this.dataAccessExpirationTime.hashCode();
    }

    @SuppressLint({"FieldGetter"})
    static AccessToken createFromRefresh(AccessToken current, Bundle bundle) {
        if (current.source == AccessTokenSource.FACEBOOK_APPLICATION_WEB || current.source == AccessTokenSource.FACEBOOK_APPLICATION_NATIVE || current.source == AccessTokenSource.FACEBOOK_APPLICATION_SERVICE) {
            Date expires2 = Utility.getBundleLongAsDate(bundle, EXPIRES_IN_KEY, new Date(0));
            String token2 = bundle.getString("access_token");
            Date dataAccessExpirationTime2 = Utility.getBundleLongAsDate(bundle, DATA_ACCESS_EXPIRATION_TIME, new Date(0));
            if (Utility.isNullOrEmpty(token2)) {
                return null;
            }
            return new AccessToken(token2, current.applicationId, current.getUserId(), current.getPermissions(), current.getDeclinedPermissions(), current.getExpiredPermissions(), current.source, expires2, new Date(), dataAccessExpirationTime2);
        }
        throw new FacebookException("Invalid token source: " + current.source);
    }

    static AccessToken createExpired(AccessToken current) {
        return new AccessToken(current.token, current.applicationId, current.getUserId(), current.getPermissions(), current.getDeclinedPermissions(), current.getExpiredPermissions(), current.source, new Date(), new Date(), current.dataAccessExpirationTime);
    }

    static AccessToken createFromLegacyCache(Bundle bundle) {
        List<String> permissions2 = getPermissionsFromBundle(bundle, LegacyTokenHelper.PERMISSIONS_KEY);
        List<String> declinedPermissions2 = getPermissionsFromBundle(bundle, LegacyTokenHelper.DECLINED_PERMISSIONS_KEY);
        List<String> expiredPermissions2 = getPermissionsFromBundle(bundle, LegacyTokenHelper.EXPIRED_PERMISSIONS_KEY);
        String applicationId2 = LegacyTokenHelper.getApplicationId(bundle);
        if (Utility.isNullOrEmpty(applicationId2)) {
            applicationId2 = FacebookSdk.getApplicationId();
        }
        String tokenString = LegacyTokenHelper.getToken(bundle);
        try {
            return new AccessToken(tokenString, applicationId2, Utility.awaitGetGraphMeRequestWithCache(tokenString).getString("id"), permissions2, declinedPermissions2, expiredPermissions2, LegacyTokenHelper.getSource(bundle), LegacyTokenHelper.getDate(bundle, LegacyTokenHelper.EXPIRATION_DATE_KEY), LegacyTokenHelper.getDate(bundle, LegacyTokenHelper.LAST_REFRESH_DATE_KEY), (Date) null);
        } catch (JSONException e) {
            return null;
        }
    }

    static List<String> getPermissionsFromBundle(Bundle bundle, String key) {
        List<String> originalPermissions = bundle.getStringArrayList(key);
        if (originalPermissions == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(originalPermissions));
    }

    public boolean isExpired() {
        return new Date().after(this.expires);
    }

    public boolean isDataAccessExpired() {
        return new Date().after(this.dataAccessExpirationTime);
    }

    /* access modifiers changed from: package-private */
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", 1);
        jsonObject.put(TOKEN_KEY, this.token);
        jsonObject.put(EXPIRES_AT_KEY, this.expires.getTime());
        jsonObject.put("permissions", new JSONArray(this.permissions));
        jsonObject.put(DECLINED_PERMISSIONS_KEY, new JSONArray(this.declinedPermissions));
        jsonObject.put(EXPIRED_PERMISSIONS_KEY, new JSONArray(this.expiredPermissions));
        jsonObject.put(LAST_REFRESH_KEY, this.lastRefresh.getTime());
        jsonObject.put("source", this.source.name());
        jsonObject.put(APPLICATION_ID_KEY, this.applicationId);
        jsonObject.put("user_id", this.userId);
        jsonObject.put(DATA_ACCESS_EXPIRATION_TIME, this.dataAccessExpirationTime.getTime());
        return jsonObject;
    }

    static AccessToken createFromJSONObject(JSONObject jsonObject) throws JSONException {
        Collection jsonArrayToStringList;
        if (jsonObject.getInt("version") > 1) {
            throw new FacebookException("Unknown AccessToken serialization format.");
        }
        String token2 = jsonObject.getString(TOKEN_KEY);
        Date expiresAt = new Date(jsonObject.getLong(EXPIRES_AT_KEY));
        JSONArray permissionsArray = jsonObject.getJSONArray("permissions");
        JSONArray declinedPermissionsArray = jsonObject.getJSONArray(DECLINED_PERMISSIONS_KEY);
        JSONArray expiredPermissionsArray = jsonObject.optJSONArray(EXPIRED_PERMISSIONS_KEY);
        Date lastRefresh2 = new Date(jsonObject.getLong(LAST_REFRESH_KEY));
        AccessTokenSource source2 = AccessTokenSource.valueOf(jsonObject.getString("source"));
        String applicationId2 = jsonObject.getString(APPLICATION_ID_KEY);
        String userId2 = jsonObject.getString("user_id");
        Date dataAccessExpirationTime2 = new Date(jsonObject.optLong(DATA_ACCESS_EXPIRATION_TIME, 0));
        List<String> jsonArrayToStringList2 = Utility.jsonArrayToStringList(permissionsArray);
        List<String> jsonArrayToStringList3 = Utility.jsonArrayToStringList(declinedPermissionsArray);
        if (expiredPermissionsArray == null) {
            jsonArrayToStringList = new ArrayList();
        } else {
            jsonArrayToStringList = Utility.jsonArrayToStringList(expiredPermissionsArray);
        }
        return new AccessToken(token2, applicationId2, userId2, jsonArrayToStringList2, jsonArrayToStringList3, jsonArrayToStringList, source2, expiresAt, lastRefresh2, dataAccessExpirationTime2);
    }

    /* access modifiers changed from: private */
    public static AccessToken createFromBundle(List<String> requestedPermissions, Bundle bundle, AccessTokenSource source2, Date expirationBase, String applicationId2) {
        String token2 = bundle.getString("access_token");
        Date expires2 = Utility.getBundleLongAsDate(bundle, EXPIRES_IN_KEY, expirationBase);
        String userId2 = bundle.getString("user_id");
        Date dataAccessExpirationTime2 = Utility.getBundleLongAsDate(bundle, DATA_ACCESS_EXPIRATION_TIME, new Date(0));
        if (Utility.isNullOrEmpty(token2) || expires2 == null) {
            return null;
        }
        return new AccessToken(token2, applicationId2, userId2, requestedPermissions, (Collection<String>) null, (Collection<String>) null, source2, expires2, new Date(), dataAccessExpirationTime2);
    }

    private String tokenToString() {
        if (this.token == null) {
            return "null";
        }
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.token;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    private void appendPermissions(StringBuilder builder) {
        builder.append(" permissions:");
        if (this.permissions == null) {
            builder.append("null");
            return;
        }
        builder.append("[");
        builder.append(TextUtils.join(", ", this.permissions));
        builder.append("]");
    }

    AccessToken(Parcel parcel) {
        this.expires = new Date(parcel.readLong());
        ArrayList<String> permissionsList = new ArrayList<>();
        parcel.readStringList(permissionsList);
        this.permissions = Collections.unmodifiableSet(new HashSet(permissionsList));
        permissionsList.clear();
        parcel.readStringList(permissionsList);
        this.declinedPermissions = Collections.unmodifiableSet(new HashSet(permissionsList));
        permissionsList.clear();
        parcel.readStringList(permissionsList);
        this.expiredPermissions = Collections.unmodifiableSet(new HashSet(permissionsList));
        this.token = parcel.readString();
        this.source = AccessTokenSource.valueOf(parcel.readString());
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        this.userId = parcel.readString();
        this.dataAccessExpirationTime = new Date(parcel.readLong());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.expires.getTime());
        dest.writeStringList(new ArrayList(this.permissions));
        dest.writeStringList(new ArrayList(this.declinedPermissions));
        dest.writeStringList(new ArrayList(this.expiredPermissions));
        dest.writeString(this.token);
        dest.writeString(this.source.name());
        dest.writeLong(this.lastRefresh.getTime());
        dest.writeString(this.applicationId);
        dest.writeString(this.userId);
        dest.writeLong(this.dataAccessExpirationTime.getTime());
    }
}
