package com.google.firebase.auth;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzbk;
import com.google.android.gms.internal.firebase_auth.zzbn;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class ActionCodeUrl {
    private static final zzbk<String, Integer> zzg = new zzbn().zza("recoverEmail", 2).zza("resetPassword", 0).zza("signIn", 4).zza("verifyEmail", 1).zza("verifyBeforeChangeEmail", 5).zza("revertSecondFactorAddition", 6).zza();
    private final String zza;
    private final String zzb;
    private final String zzc;
    @Nullable
    private final String zzd;
    @Nullable
    private final String zze;
    @Nullable
    private final String zzf;

    private ActionCodeUrl(String str) {
        this.zza = zza(str, "apiKey");
        this.zzb = zza(str, "oobCode");
        this.zzc = zza(str, "mode");
        if (this.zza == null || this.zzb == null || this.zzc == null) {
            throw new IllegalArgumentException(String.format("%s, %s and %s are required in a valid action code URL", new Object[]{"apiKey", "oobCode", "mode"}));
        }
        this.zzd = zza(str, "continueUrl");
        this.zze = zza(str, "languageCode");
        this.zzf = zza(str, "tenantId");
    }

    @Nullable
    public static ActionCodeUrl parseLink(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        try {
            return new ActionCodeUrl(str);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @NonNull
    public String getApiKey() {
        return this.zza;
    }

    public int getOperation() {
        return zzg.getOrDefault(this.zzc, 3).intValue();
    }

    @NonNull
    public String getCode() {
        return this.zzb;
    }

    @Nullable
    public String getContinueUrl() {
        return this.zzd;
    }

    @Nullable
    public String getLanguageCode() {
        return this.zze;
    }

    @Nullable
    public final String zza() {
        return this.zzf;
    }

    private static String zza(String str, String str2) {
        Uri parse = Uri.parse(str);
        try {
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            if (queryParameterNames.contains(str2)) {
                return parse.getQueryParameter(str2);
            }
            if (queryParameterNames.contains("link")) {
                return Uri.parse(parse.getQueryParameter("link")).getQueryParameter(str2);
            }
            return null;
        } catch (UnsupportedOperationException e) {
        }
    }
}
