package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class AuthCredential extends AbstractSafeParcelable {
    AuthCredential() {
    }

    @NonNull
    public abstract String getProvider();

    @NonNull
    public abstract String getSignInMethod();

    public abstract AuthCredential zza();
}
