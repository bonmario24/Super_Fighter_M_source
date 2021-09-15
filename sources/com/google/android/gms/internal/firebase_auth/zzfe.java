package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.api.internal.zzgb;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfe implements zzgb<zzp.zzh> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private ActionCodeSettings zze;
    @Nullable
    private String zzf;

    public zzfe(zzgm zzgm) {
        this.zza = zza(zzgm);
    }

    private zzfe(zzgm zzgm, ActionCodeSettings actionCodeSettings, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.zza = zza((zzgm) Preconditions.checkNotNull(zzgm));
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        this.zzb = null;
        this.zzc = str2;
        this.zzd = str3;
        this.zzf = null;
    }

    public static zzfe zza(ActionCodeSettings actionCodeSettings, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        return new zzfe(zzgm.VERIFY_AND_CHANGE_EMAIL, actionCodeSettings, (String) null, str2, str, (String) null);
    }

    public final zzfe zza(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfe zzb(String str) {
        this.zzd = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfe zza(ActionCodeSettings actionCodeSettings) {
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        return this;
    }

    public final zzfe zzc(@Nullable String str) {
        this.zzf = str;
        return this;
    }

    public final ActionCodeSettings zzb() {
        return this.zze;
    }

    private static String zza(zzgm zzgm) {
        switch (zzfd.zza[zzgm.ordinal()]) {
            case 1:
                return "PASSWORD_RESET";
            case 2:
                return "VERIFY_EMAIL";
            case 3:
                return "EMAIL_SIGNIN";
            case 4:
                return "VERIFY_BEFORE_UPDATE_EMAIL";
            default:
                return "REQUEST_TYPE_UNSET_ENUM_VALUE";
        }
    }

    public final /* synthetic */ zzjr zza() {
        zzgm zzgm;
        zzp.zzh.zza zza2 = zzp.zzh.zza();
        String str = this.zza;
        char c = 65535;
        switch (str.hashCode()) {
            case -1452371317:
                if (str.equals("PASSWORD_RESET")) {
                    c = 0;
                    break;
                }
                break;
            case -1341836234:
                if (str.equals("VERIFY_EMAIL")) {
                    c = 1;
                    break;
                }
                break;
            case -1288726400:
                if (str.equals("VERIFY_BEFORE_UPDATE_EMAIL")) {
                    c = 3;
                    break;
                }
                break;
            case 870738373:
                if (str.equals("EMAIL_SIGNIN")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                zzgm = zzgm.PASSWORD_RESET;
                break;
            case 1:
                zzgm = zzgm.VERIFY_EMAIL;
                break;
            case 2:
                zzgm = zzgm.EMAIL_SIGNIN;
                break;
            case 3:
                zzgm = zzgm.VERIFY_AND_CHANGE_EMAIL;
                break;
            default:
                zzgm = zzgm.OOB_REQ_TYPE_UNSPECIFIED;
                break;
        }
        zzp.zzh.zza zza3 = zza2.zza(zzgm);
        if (this.zzb != null) {
            zza3.zza(this.zzb);
        }
        if (this.zzc != null) {
            zza3.zzb(this.zzc);
        }
        if (this.zzd != null) {
            zza3.zzc(this.zzd);
        }
        if (this.zze != null) {
            zza3.zza(this.zze.getAndroidInstallApp()).zzb(this.zze.canHandleCodeInApp());
            if (this.zze.getUrl() != null) {
                zza3.zzd(this.zze.getUrl());
            }
            if (this.zze.getIOSBundle() != null) {
                zza3.zze(this.zze.getIOSBundle());
            }
            if (this.zze.zzb() != null) {
                zza3.zzf(this.zze.zzb());
            }
            if (this.zze.getAndroidPackageName() != null) {
                zza3.zzg(this.zze.getAndroidPackageName());
            }
            if (this.zze.getAndroidMinimumVersion() != null) {
                zza3.zzh(this.zze.getAndroidMinimumVersion());
            }
            if (this.zze.zze() != null) {
                zza3.zzj(this.zze.zze());
            }
        }
        if (this.zzf != null) {
            zza3.zzi(this.zzf);
        }
        return (zzp.zzh) ((zzig) zza3.zzf());
    }
}
