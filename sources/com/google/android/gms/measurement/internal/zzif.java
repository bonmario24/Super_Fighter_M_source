package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzlb;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzif extends zzkb {
    public zzif(zzka zzka) {
        super(zzka);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    @WorkerThread
    public final byte[] zza(@NonNull zzao zzao, @Size(min = 1) String str) {
        zzkj zzkj;
        long j;
        zzak zza;
        zzd();
        this.zzz.zzae();
        Preconditions.checkNotNull(zzao);
        Preconditions.checkNotEmpty(str);
        if (!zzt().zze(str, zzaq.zzax)) {
            zzr().zzw().zza("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        } else if ("_iap".equals(zzao.zza) || "_iapx".equals(zzao.zza)) {
            zzbs.zzf.zza zzb = zzbs.zzf.zzb();
            zzi().zzf();
            try {
                zzf zzb2 = zzi().zzb(str);
                if (zzb2 == null) {
                    zzr().zzw().zza("Log and bundle not available. package_name", str);
                    return new byte[0];
                } else if (!zzb2.zzr()) {
                    zzr().zzw().zza("Log and bundle disabled. package_name", str);
                    byte[] bArr = new byte[0];
                    zzi().zzh();
                    return bArr;
                } else {
                    zzbs.zzg.zza zza2 = zzbs.zzg.zzbf().zza(1).zza("android");
                    if (!TextUtils.isEmpty(zzb2.zzc())) {
                        zza2.zzf(zzb2.zzc());
                    }
                    if (!TextUtils.isEmpty(zzb2.zzn())) {
                        zza2.zze(zzb2.zzn());
                    }
                    if (!TextUtils.isEmpty(zzb2.zzl())) {
                        zza2.zzg(zzb2.zzl());
                    }
                    if (zzb2.zzm() != -2147483648L) {
                        zza2.zzh((int) zzb2.zzm());
                    }
                    zza2.zzf(zzb2.zzo()).zzk(zzb2.zzq());
                    if (!zzlb.zzb() || !zzt().zze(zzb2.zzc(), zzaq.zzbo)) {
                        if (!TextUtils.isEmpty(zzb2.zze())) {
                            zza2.zzk(zzb2.zze());
                        } else if (!TextUtils.isEmpty(zzb2.zzf())) {
                            zza2.zzo(zzb2.zzf());
                        }
                    } else if (!TextUtils.isEmpty(zzb2.zze())) {
                        zza2.zzk(zzb2.zze());
                    } else if (!TextUtils.isEmpty(zzb2.zzg())) {
                        zza2.zzp(zzb2.zzg());
                    } else if (!TextUtils.isEmpty(zzb2.zzf())) {
                        zza2.zzo(zzb2.zzf());
                    }
                    zza2.zzh(zzb2.zzp());
                    if (this.zzz.zzab() && zzt().zzf(zza2.zzj())) {
                        zza2.zzj();
                        if (!TextUtils.isEmpty((CharSequence) null)) {
                            zza2.zzn((String) null);
                        }
                    }
                    Pair<String, Boolean> zza3 = zzs().zza(zzb2.zzc());
                    if (zzb2.zzaf() && zza3 != null && !TextUtils.isEmpty((CharSequence) zza3.first)) {
                        zza2.zzh(zza((String) zza3.first, Long.toString(zzao.zzd)));
                        if (zza3.second != null) {
                            zza2.zza(((Boolean) zza3.second).booleanValue());
                        }
                    }
                    zzl().zzaa();
                    zzbs.zzg.zza zzc = zza2.zzc(Build.MODEL);
                    zzl().zzaa();
                    zzc.zzb(Build.VERSION.RELEASE).zzf((int) zzl().zzf()).zzd(zzl().zzg());
                    try {
                        zza2.zzi(zza(zzb2.zzd(), Long.toString(zzao.zzd)));
                        if (!TextUtils.isEmpty(zzb2.zzi())) {
                            zza2.zzl(zzb2.zzi());
                        }
                        String zzc2 = zzb2.zzc();
                        List<zzkj> zza4 = zzi().zza(zzc2);
                        Iterator<zzkj> it = zza4.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                zzkj = null;
                                break;
                            }
                            zzkj = it.next();
                            if ("_lte".equals(zzkj.zzc)) {
                                break;
                            }
                        }
                        if (zzkj == null || zzkj.zze == null) {
                            zzkj zzkj2 = new zzkj(zzc2, "auto", "_lte", zzm().currentTimeMillis(), 0L);
                            zza4.add(zzkj2);
                            zzi().zza(zzkj2);
                        }
                        zzki zzg = zzg();
                        zzg.zzr().zzx().zza("Checking account type status for ad personalization signals");
                        if (zzg.zzl().zzj()) {
                            String zzc3 = zzb2.zzc();
                            if (zzb2.zzaf() && zzg.zzj().zze(zzc3)) {
                                zzg.zzr().zzw().zza("Turning off ad personalization due to account type");
                                Iterator<zzkj> it2 = zza4.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        if ("_npa".equals(it2.next().zzc)) {
                                            it2.remove();
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                zza4.add(new zzkj(zzc3, "auto", "_npa", zzg.zzm().currentTimeMillis(), 1L));
                            }
                        }
                        zzbs.zzk[] zzkArr = new zzbs.zzk[zza4.size()];
                        for (int i = 0; i < zza4.size(); i++) {
                            zzbs.zzk.zza zza5 = zzbs.zzk.zzj().zza(zza4.get(i).zzc).zza(zza4.get(i).zzd);
                            zzg().zza(zza5, zza4.get(i).zze);
                            zzkArr[i] = (zzbs.zzk) ((zzfe) zza5.zzv());
                        }
                        zza2.zzb((Iterable<? extends zzbs.zzk>) Arrays.asList(zzkArr));
                        Bundle zzb3 = zzao.zzb.zzb();
                        zzb3.putLong("_c", 1);
                        zzr().zzw().zza("Marking in-app purchase as real-time");
                        zzb3.putLong("_r", 1);
                        zzb3.putString("_o", zzao.zzc);
                        if (zzp().zzf(zza2.zzj())) {
                            zzp().zza(zzb3, "_dbg", (Object) 1L);
                            zzp().zza(zzb3, "_r", (Object) 1L);
                        }
                        zzak zza6 = zzi().zza(str, zzao.zza);
                        if (zza6 == null) {
                            zza = new zzak(str, zzao.zza, 0, 0, zzao.zzd, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                            j = 0;
                        } else {
                            j = zza6.zzf;
                            zza = zza6.zza(zzao.zzd);
                        }
                        zzi().zza(zza);
                        zzal zzal = new zzal(this.zzz, zzao.zzc, str, zzao.zza, zzao.zzd, j, zzb3);
                        zzbs.zzc.zza zzb4 = zzbs.zzc.zzj().zza(zzal.zzc).zza(zzal.zzb).zzb(zzal.zzd);
                        Iterator<String> it3 = zzal.zze.iterator();
                        while (it3.hasNext()) {
                            String next = it3.next();
                            zzbs.zze.zza zza7 = zzbs.zze.zzk().zza(next);
                            zzg().zza(zza7, zzal.zze.zza(next));
                            zzb4.zza(zza7);
                        }
                        zza2.zza(zzb4).zza(zzbs.zzh.zza().zza(zzbs.zzd.zza().zza(zza.zzc).zza(zzao.zza)));
                        zza2.zzc((Iterable<? extends zzbs.zza>) mo24394e_().zza(zzb2.zzc(), Collections.emptyList(), zza2.zzd(), Long.valueOf(zzb4.zzf()), Long.valueOf(zzb4.zzf())));
                        if (zzb4.zze()) {
                            zza2.zzb(zzb4.zzf()).zzc(zzb4.zzf());
                        }
                        long zzk = zzb2.zzk();
                        if (zzk != 0) {
                            zza2.zze(zzk);
                        }
                        long zzj = zzb2.zzj();
                        if (zzj != 0) {
                            zza2.zzd(zzj);
                        } else if (zzk != 0) {
                            zza2.zzd(zzk);
                        }
                        zzb2.zzv();
                        zza2.zzg((int) zzb2.zzs()).zzg(zzt().zzf()).zza(zzm().currentTimeMillis()).zzb(Boolean.TRUE.booleanValue());
                        zzb.zza(zza2);
                        zzb2.zza(zza2.zzf());
                        zzb2.zzb(zza2.zzg());
                        zzi().zza(zzb2);
                        zzi().mo24237b_();
                        zzi().zzh();
                        try {
                            return zzg().zzc(((zzbs.zzf) ((zzfe) zzb.zzv())).zzbi());
                        } catch (IOException e) {
                            zzr().zzf().zza("Data loss. Failed to bundle and serialize. appId", zzes.zza(str), e);
                            return null;
                        }
                    } catch (SecurityException e2) {
                        zzr().zzw().zza("app instance id encryption failed", e2.getMessage());
                        byte[] bArr2 = new byte[0];
                        zzi().zzh();
                        return bArr2;
                    }
                }
            } catch (SecurityException e3) {
                zzr().zzw().zza("Resettable device id encryption failed", e3.getMessage());
                return new byte[0];
            } finally {
                zzi().zzh();
            }
        } else {
            zzr().zzw().zza("Generating a payload for this event is not available. package_name, event_name", str, zzao.zza);
            return null;
        }
    }

    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }
}
