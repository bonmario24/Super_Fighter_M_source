package com.google.android.gms.internal.p037authapiphone;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzi */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.1.0 */
public final class zzi extends SmsRetrieverClient {
    public zzi(Context context) {
        super(context);
    }

    public zzi(Activity activity) {
        super(activity);
    }

    public final Task<Void> startSmsRetriever() {
        return doWrite(new zzk(this));
    }

    public final Task<Void> startSmsUserConsent(@Nullable String str) {
        return doWrite(TaskApiCall.builder().run(new zzl(this, str)).setFeatures(zzo.zza).build());
    }
}
