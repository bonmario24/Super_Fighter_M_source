package com.google.firebase.auth;

import androidx.annotation.NonNull;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class ActionCodeMultiFactorInfo extends ActionCodeInfo {
    @NonNull
    public abstract MultiFactorInfo getMultiFactorInfo();
}
