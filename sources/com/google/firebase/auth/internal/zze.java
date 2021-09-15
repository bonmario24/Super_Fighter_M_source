package com.google.firebase.auth.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfq;
import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zze implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final ActionCodeInfo zzd;

    public zze(zzfq zzfq) {
        int i;
        ActionCodeInfo zzc2;
        this.zzb = zzfq.zzg() ? zzfq.zzc() : zzfq.zzb();
        this.zzc = zzfq.zzb();
        if (!zzfq.zzh()) {
            this.zza = 3;
            this.zzd = null;
            return;
        }
        String zzd2 = zzfq.zzd();
        char c = 65535;
        switch (zzd2.hashCode()) {
            case -1874510116:
                if (zzd2.equals("REVERT_SECOND_FACTOR_ADDITION")) {
                    c = 5;
                    break;
                }
                break;
            case -1452371317:
                if (zzd2.equals("PASSWORD_RESET")) {
                    c = 0;
                    break;
                }
                break;
            case -1341836234:
                if (zzd2.equals("VERIFY_EMAIL")) {
                    c = 1;
                    break;
                }
                break;
            case -1288726400:
                if (zzd2.equals("VERIFY_BEFORE_UPDATE_EMAIL")) {
                    c = 3;
                    break;
                }
                break;
            case 870738373:
                if (zzd2.equals("EMAIL_SIGNIN")) {
                    c = 2;
                    break;
                }
                break;
            case 970484929:
                if (zzd2.equals("RECOVER_EMAIL")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                i = 0;
                break;
            case 1:
                i = 1;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            case 4:
                i = 2;
                break;
            case 5:
                i = 6;
                break;
            default:
                i = 3;
                break;
        }
        this.zza = i;
        if (this.zza == 4 || this.zza == 3) {
            this.zzd = null;
            return;
        }
        if (zzfq.zzi()) {
            zzc2 = new zzf(zzfq.zzb(), zzar.zza(zzfq.zze()));
        } else if (zzfq.zzg()) {
            zzc2 = new zzd(zzfq.zzc(), zzfq.zzb());
        } else {
            zzc2 = zzfq.zzf() ? new zzc(zzfq.zzb()) : null;
        }
        this.zzd = zzc2;
    }

    public final int getOperation() {
        return this.zza;
    }

    @Nullable
    public final ActionCodeInfo getInfo() {
        return this.zzd;
    }

    @Nullable
    public final String getData(int i) {
        if (this.zza == 4) {
            return null;
        }
        switch (i) {
            case 0:
                return this.zzb;
            case 1:
                return this.zzc;
            default:
                return null;
        }
    }
}
