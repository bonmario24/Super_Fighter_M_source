package com.google.android.gms.internal.clearcut;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.clearcut.zzgw;
import com.google.android.gms.phenotype.Phenotype;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class zzp implements ClearcutLogger.zza {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final zzao zzaq = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:samplingrules_").zzd("LogSamplingRules__");
    private static final zzao zzar = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:sampling_").zzd("LogSampling__");
    private static final ConcurrentHashMap<String, zzae<zzgw.zza>> zzas = new ConcurrentHashMap<>();
    private static final HashMap<String, zzae<String>> zzat = new HashMap<>();
    @VisibleForTesting
    private static Boolean zzau = null;
    @VisibleForTesting
    private static Long zzav = null;
    @VisibleForTesting
    private static final zzae<Boolean> zzaw = zzaq.zzc("enable_log_sampling_rules", false);
    private final Context zzh;

    public zzp(Context context) {
        this.zzh = context;
        if (this.zzh != null) {
            zzae.maybeInit(this.zzh);
        }
    }

    @VisibleForTesting
    private static long zza(String str, long j) {
        if (str == null || str.isEmpty()) {
            return zzk.zza(ByteBuffer.allocate(8).putLong(j).array());
        }
        byte[] bytes = str.getBytes(UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
        allocate.put(bytes);
        allocate.putLong(j);
        return zzk.zza(allocate.array());
    }

    @VisibleForTesting
    private static zzgw.zza.zzb zza(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            String valueOf = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf.length() != 0 ? "Failed to parse the rule: ".concat(valueOf) : new String("Failed to parse the rule: "));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return (zzgw.zza.zzb) zzgw.zza.zzb.zzfz().zzn(str2).zzr(parseLong).zzs(parseLong2).zzbh();
            }
            Log.e("LogSamplerImpl", new StringBuilder(72).append("negative values not supported: ").append(parseLong).append("/").append(parseLong2).toString());
            return null;
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            String valueOf2 = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf2.length() != 0 ? "parseLong() failed while parsing: ".concat(valueOf2) : new String("parseLong() failed while parsing: "), numberFormatException);
            return null;
        }
    }

    @VisibleForTesting
    private static boolean zzb(long j, long j2, long j3) {
        if (j2 >= 0 && j3 > 0) {
            return ((j > 0 ? 1 : (j == 0 ? 0 : -1)) >= 0 ? j % j3 : (((Long.MAX_VALUE % j3) + 1) + ((j & Long.MAX_VALUE) % j3)) % j3) < j2;
        }
    }

    private static boolean zzc(Context context) {
        if (zzau == null) {
            zzau = Boolean.valueOf(Wrappers.packageManager(context).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return zzau.booleanValue();
    }

    @VisibleForTesting
    private static long zzd(Context context) {
        if (zzav == null) {
            if (context == null) {
                return 0;
            }
            if (zzc(context)) {
                zzav = Long.valueOf(zzy.getLong(context.getContentResolver(), "android_id", 0));
            } else {
                zzav = 0L;
            }
        }
        return zzav.longValue();
    }

    public final boolean zza(zze zze) {
        List<zzgw.zza.zzb> zzfs;
        zzae zza;
        String str;
        String str2 = zze.zzag.zzj;
        int i = zze.zzag.zzk;
        int i2 = zze.zzaa != null ? zze.zzaa.zzbji : 0;
        if (!zzaw.get().booleanValue()) {
            if (str2 == null || str2.isEmpty()) {
                str2 = i >= 0 ? String.valueOf(i) : null;
            }
            if (str2 != null) {
                if (this.zzh == null || !zzc(this.zzh)) {
                    str = null;
                } else {
                    zzae<String> zzae = zzat.get(str2);
                    if (zzae == null) {
                        zzae = zzar.zza(str2, (String) null);
                        zzat.put(str2, zzae);
                    }
                    str = zzae.get();
                }
                zzgw.zza.zzb zza2 = zza(str);
                if (zza2 != null) {
                    return zzb(zza(zza2.zzfw(), zzd(this.zzh)), zza2.zzfx(), zza2.zzfy());
                }
            }
        } else {
            if (str2 == null || str2.isEmpty()) {
                str2 = i >= 0 ? String.valueOf(i) : null;
            }
            if (str2 != null) {
                if (this.zzh == null) {
                    zzfs = Collections.emptyList();
                } else {
                    zzae zzae2 = zzas.get(str2);
                    if (zzae2 == null && (zzae2 = zzas.putIfAbsent(str2, zza)) == null) {
                        zzae2 = zzaq.zza(str2, zzgw.zza.zzft(), zzq.zzax);
                    }
                    zzfs = ((zzgw.zza) zzae2.get()).zzfs();
                }
                for (zzgw.zza.zzb next : zzfs) {
                    if ((!next.zzfv() || next.getEventCode() == 0 || next.getEventCode() == i2) && !zzb(zza(next.zzfw(), zzd(this.zzh)), next.zzfx(), next.zzfy())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
