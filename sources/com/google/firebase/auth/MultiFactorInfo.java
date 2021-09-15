package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class MultiFactorInfo extends AbstractSafeParcelable {
    public static final String FACTOR_ID_KEY = "factorIdKey";

    @Nullable
    public abstract String getDisplayName();

    public abstract long getEnrollmentTimestamp();

    @NonNull
    public abstract String getFactorId();

    @NonNull
    public abstract String getUid();

    @Nullable
    public abstract JSONObject toJson();
}
