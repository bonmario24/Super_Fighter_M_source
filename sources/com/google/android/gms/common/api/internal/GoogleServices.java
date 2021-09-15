package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.C1775R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
@Deprecated
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class GoogleServices {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static GoogleServices zzbk;
    private final String zzbl;
    private final Status zzbm;
    private final boolean zzbn;
    private final boolean zzbo;

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(C1775R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            this.zzbo = z2 ? false : z;
            z = z2;
        } else {
            this.zzbo = false;
        }
        this.zzbn = z;
        String zzc = zzp.zzc(context);
        zzc = zzc == null ? new StringResourceValueReader(context).getString("google_app_id") : zzc;
        if (TextUtils.isEmpty(zzc)) {
            this.zzbm = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzbl = null;
            return;
        }
        this.zzbl = zzc;
        this.zzbm = Status.RESULT_SUCCESS;
    }

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(String str, boolean z) {
        this.zzbl = str;
        this.zzbm = Status.RESULT_SUCCESS;
        this.zzbn = z;
        this.zzbo = !z;
    }

    @KeepForSdk
    public static Status initialize(Context context, String str, boolean z) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (sLock) {
            if (zzbk != null) {
                status = zzbk.checkGoogleAppId(str);
            } else {
                GoogleServices googleServices = new GoogleServices(str, z);
                zzbk = googleServices;
                status = googleServices.zzbm;
            }
        }
        return status;
    }

    /* access modifiers changed from: package-private */
    @KeepForSdk
    @VisibleForTesting
    public final Status checkGoogleAppId(String str) {
        if (this.zzbl == null || this.zzbl.equals(str)) {
            return Status.RESULT_SUCCESS;
        }
        String str2 = this.zzbl;
        return new Status(10, new StringBuilder(String.valueOf(str2).length() + 97).append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '").append(str2).append("'.").toString());
    }

    @KeepForSdk
    public static Status initialize(Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (sLock) {
            if (zzbk == null) {
                zzbk = new GoogleServices(context);
            }
            status = zzbk.zzbm;
        }
        return status;
    }

    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzbl;
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices checkInitialized = checkInitialized("isMeasurementEnabled");
        return checkInitialized.zzbm.isSuccess() && checkInitialized.zzbn;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzbo;
    }

    @KeepForSdk
    @VisibleForTesting
    static void clearInstanceForTest() {
        synchronized (sLock) {
            zzbk = null;
        }
    }

    @KeepForSdk
    private static GoogleServices checkInitialized(String str) {
        GoogleServices googleServices;
        synchronized (sLock) {
            if (zzbk == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            googleServices = zzbk;
        }
        return googleServices;
    }
}
