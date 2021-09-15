package com.google.firebase.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class OAuthCredential extends AuthCredential {
    @NonNull
    public abstract String getAccessToken();

    @NonNull
    public abstract String getIdToken();

    @Nullable
    public abstract String getSecret();
}
