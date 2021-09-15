package com.google.firebase.auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public interface AuthResult extends SafeParcelable {
    @Nullable
    AdditionalUserInfo getAdditionalUserInfo();

    @Nullable
    AuthCredential getCredential();

    @Nullable
    FirebaseUser getUser();
}
