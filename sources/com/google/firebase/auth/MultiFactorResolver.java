package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class MultiFactorResolver extends AbstractSafeParcelable {
    @NonNull
    public abstract FirebaseAuth getFirebaseAuth();

    @NonNull
    public abstract List<MultiFactorInfo> getHints();

    @NonNull
    public abstract MultiFactorSession getSession();

    @NonNull
    public abstract Task<AuthResult> resolveSignIn(@NonNull MultiFactorAssertion multiFactorAssertion);
}
