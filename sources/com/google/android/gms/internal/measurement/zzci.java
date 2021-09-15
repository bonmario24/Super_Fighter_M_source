package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzci {
    public static zzcw<zzcj> zza(Context context) {
        boolean z = false;
        String str = Build.TYPE;
        String str2 = Build.TAGS;
        String str3 = Build.HARDWARE;
        if ((str.equals("eng") || str.equals("userdebug")) && ((str3.equals("goldfish") || str3.equals("ranchu") || str3.equals("robolectric")) && (str2.contains("dev-keys") || str2.contains("test-keys")))) {
            z = true;
        }
        if (!z) {
            return zzcw.zzc();
        }
        if (zzbw.zza() && !context.isDeviceProtectedStorage()) {
            context = context.createDeviceProtectedStorageContext();
        }
        zzcw<File> zzb = zzb(context);
        if (zzb.zza()) {
            return zzcw.zza(zza(zzb.zzb()));
        }
        return zzcw.zzc();
    }

    private static zzcw<File> zzb(Context context) {
        zzcw<File> zzc;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
            zzc = file.exists() ? zzcw.zza(file) : zzcw.zzc();
        } catch (RuntimeException e) {
            Log.e("HermeticFileOverrides", "no data dir", e);
            zzc = zzcw.zzc();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
        return zzc;
    }

    private static zzcj zza(File file) {
        BufferedReader bufferedReader;
        String str;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            HashMap hashMap = new HashMap();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(" ", 3);
                    if (split.length != 3) {
                        String valueOf = String.valueOf(readLine);
                        if (valueOf.length() != 0) {
                            str = "Invalid: ".concat(valueOf);
                        } else {
                            str = new String("Invalid: ");
                        }
                        Log.e("HermeticFileOverrides", str);
                    } else {
                        String str2 = split[0];
                        String decode = Uri.decode(split[1]);
                        String decode2 = Uri.decode(split[2]);
                        if (!hashMap.containsKey(str2)) {
                            hashMap.put(str2, new HashMap());
                        }
                        ((Map) hashMap.get(str2)).put(decode, decode2);
                    }
                } else {
                    String valueOf2 = String.valueOf(file);
                    Log.i("HermeticFileOverrides", new StringBuilder(String.valueOf(valueOf2).length() + 7).append("Parsed ").append(valueOf2).toString());
                    zzcj zzcj = new zzcj(hashMap);
                    bufferedReader.close();
                    return zzcj;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            zzde.zza(th, th);
        }
        throw th;
    }
}
