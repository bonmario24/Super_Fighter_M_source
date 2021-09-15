package com.google.firebase.auth;

import androidx.annotation.NonNull;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class ActionCodeEmailInfo extends ActionCodeInfo {
    @NonNull
    public abstract String getPreviousEmail();

    @NonNull
    public String getEmail() {
        return super.getEmail();
    }
}
