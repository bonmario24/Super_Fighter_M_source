package com.google.firebase.auth;

import androidx.annotation.NonNull;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class FirebaseAuthMultiFactorException extends FirebaseAuthException {
    private MultiFactorResolver zza;

    public FirebaseAuthMultiFactorException(String str, String str2, MultiFactorResolver multiFactorResolver) {
        super(str, str2);
        this.zza = multiFactorResolver;
    }

    @NonNull
    public MultiFactorResolver getResolver() {
        return this.zza;
    }
}
