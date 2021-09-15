package com.facebook.login;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.facebook.login.LoginClient;
import java.util.Collection;

public class DeviceLoginManager extends LoginManager {
    private static volatile DeviceLoginManager instance;
    @Nullable
    private String deviceAuthTargetUserId;
    private Uri deviceRedirectUri;

    public static DeviceLoginManager getInstance() {
        if (instance == null) {
            synchronized (DeviceLoginManager.class) {
                if (instance == null) {
                    instance = new DeviceLoginManager();
                }
            }
        }
        return instance;
    }

    public void setDeviceRedirectUri(Uri uri) {
        this.deviceRedirectUri = uri;
    }

    public Uri getDeviceRedirectUri() {
        return this.deviceRedirectUri;
    }

    public void setDeviceAuthTargetUserId(@Nullable String targetUserId) {
        this.deviceAuthTargetUserId = targetUserId;
    }

    @Nullable
    public String getDeviceAuthTargetUserId() {
        return this.deviceAuthTargetUserId;
    }

    /* access modifiers changed from: protected */
    public LoginClient.Request createLoginRequest(Collection<String> permissions) {
        LoginClient.Request request = super.createLoginRequest(permissions);
        Uri redirectUri = getDeviceRedirectUri();
        if (redirectUri != null) {
            request.setDeviceRedirectUriString(redirectUri.toString());
        }
        String deviceTargetUserId = getDeviceAuthTargetUserId();
        if (deviceTargetUserId != null) {
            request.setDeviceAuthTargetUserId(deviceTargetUserId);
        }
        return request;
    }
}
