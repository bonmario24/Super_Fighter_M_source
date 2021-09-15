package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.CheckReturnValue;

@ShowFirstParty
@CheckReturnValue
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzat;
    private final Context mContext;
    private volatile String zzau;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zzat == null) {
                zzc.zza(context);
                zzat = new GoogleSignatureVerifier(context);
            }
        }
        return zzat;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i) {
        zzl zzl;
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i);
        if (packagesForUid != null && packagesForUid.length != 0) {
            zzl = null;
            for (String zza : packagesForUid) {
                zzl = zza(zza, i);
                if (zzl.zzap) {
                    break;
                }
            }
        } else {
            zzl = zzl.zzb("no pkgs");
        }
        zzl.zzf();
        return zzl.zzap;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) {
        zzl zzc = zzc(str);
        zzc.zzf();
        return zzc.zzap;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        zzd zza;
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (z) {
                zza = zza(packageInfo, zzi.zzaj);
            } else {
                zza = zza(packageInfo, zzi.zzaj[0]);
            }
            if (zza != null) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (!zza(packageInfo, true)) {
            return false;
        }
        if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
            return true;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return false;
    }

    private final zzl zza(String str, int i) {
        try {
            PackageInfo zza = Wrappers.packageManager(this.mContext).zza(str, 64, i);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (zza == null) {
                return zzl.zzb("null pkg");
            }
            if (zza.signatures == null || zza.signatures.length != 1) {
                return zzl.zzb("single cert required");
            }
            zzg zzg = new zzg(zza.signatures[0].toByteArray());
            String str2 = zza.packageName;
            zzl zza2 = zzc.zza(str2, zzg, honorsDebugCertificates, false);
            if (!zza2.zzap || zza.applicationInfo == null || (zza.applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzg, false, true).zzap) {
                return zza2;
            }
            return zzl.zzb("debuggable release cert app rejected");
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(str);
            return zzl.zzb(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    private final zzl zzc(String str) {
        zzl zzb;
        if (str == null) {
            return zzl.zzb("null pkg");
        }
        if (str.equals(this.zzau)) {
            return zzl.zze();
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(this.mContext).getPackageInfo(str, 64);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (packageInfo == null) {
                zzb = zzl.zzb("null pkg");
            } else if (packageInfo.signatures == null || packageInfo.signatures.length != 1) {
                zzb = zzl.zzb("single cert required");
            } else {
                zzg zzg = new zzg(packageInfo.signatures[0].toByteArray());
                String str2 = packageInfo.packageName;
                zzb = zzc.zza(str2, zzg, honorsDebugCertificates, false);
                if (zzb.zzap && packageInfo.applicationInfo != null && (packageInfo.applicationInfo.flags & 2) != 0 && zzc.zza(str2, zzg, false, true).zzap) {
                    zzb = zzl.zzb("debuggable release cert app rejected");
                }
            }
            if (!zzb.zzap) {
                return zzb;
            }
            this.zzau = str;
            return zzb;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(str);
            return zzl.zzb(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    private static zzd zza(PackageInfo packageInfo, zzd... zzdArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzg zzg = new zzg(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzdArr.length; i++) {
            if (zzdArr[i].equals(zzg)) {
                return zzdArr[i];
            }
        }
        return null;
    }
}
