package com.google.firebase.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class MultiFactor {
    @NonNull
    public abstract Task<Void> enroll(@NonNull MultiFactorAssertion multiFactorAssertion, @Nullable String str);

    @NonNull
    public abstract List<MultiFactorInfo> getEnrolledFactors();

    @NonNull
    public abstract Task<MultiFactorSession> getSession();

    @NonNull
    public abstract Task<Void> unenroll(@NonNull MultiFactorInfo multiFactorInfo);

    @NonNull
    public abstract Task<Void> unenroll(@NonNull String str);
}
