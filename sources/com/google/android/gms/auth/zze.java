package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzay;
import com.google.android.gms.internal.auth.zzf;
import java.io.IOException;

final class zze implements zzj<TokenData> {
    private final /* synthetic */ Bundle val$options;
    private final /* synthetic */ Account zzo;
    private final /* synthetic */ String zzp;

    zze(Account account, String str, Bundle bundle) {
        this.zzo = account;
        this.zzp = str;
        this.val$options = bundle;
    }

    public final /* synthetic */ Object zzb(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        boolean z;
        Bundle bundle = (Bundle) zzd.zza(zzf.zza(iBinder).zza(this.zzo, this.zzp, this.val$options));
        TokenData zza = TokenData.zza(bundle, "tokenDetails");
        if (zza != null) {
            return zza;
        }
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzay zzc = zzay.zzc(string);
        if (zzay.zza(zzc)) {
            Logger zza2 = zzd.zzn;
            String valueOf = String.valueOf(zzc);
            zza2.mo21231w("GoogleAuthUtil", new StringBuilder(String.valueOf(valueOf).length() + 31).append("isUserRecoverableError status: ").append(valueOf).toString());
            throw new UserRecoverableAuthException(string, intent);
        }
        if (zzay.NETWORK_ERROR.equals(zzc) || zzay.zzcp.equals(zzc) || zzay.INTNERNAL_ERROR.equals(zzc)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            throw new IOException(string);
        }
        throw new GoogleAuthException(string);
    }
}
