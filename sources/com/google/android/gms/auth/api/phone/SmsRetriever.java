package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.p037authapiphone.zzi;

/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.1.0 */
public final class SmsRetriever {
    public static final String EXTRA_CONSENT_INTENT = "com.google.android.gms.auth.api.phone.EXTRA_CONSENT_INTENT";
    public static final String EXTRA_SIM_SUBSCRIPTION_ID = "com.google.android.gms.auth.api.phone.EXTRA_SIM_SUBSCRIPTION_ID";
    public static final String EXTRA_SMS_MESSAGE = "com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE";
    public static final String EXTRA_STATUS = "com.google.android.gms.auth.api.phone.EXTRA_STATUS";
    public static final String SMS_RETRIEVED_ACTION = "com.google.android.gms.auth.api.phone.SMS_RETRIEVED";

    public static SmsRetrieverClient getClient(@NonNull Context context) {
        return new zzi(context);
    }

    public static SmsRetrieverClient getClient(@NonNull Activity activity) {
        return new zzi(activity);
    }

    private SmsRetriever() {
    }
}
