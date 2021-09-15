package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbk;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
abstract class zzv {
    String zza;
    int zzb;
    Boolean zzc;
    Boolean zzd;
    Long zze;
    Long zzf;

    zzv(String str, int i) {
        this.zza = str;
        this.zzb = i;
    }

    /* access modifiers changed from: package-private */
    public abstract int zza();

    /* access modifiers changed from: package-private */
    public abstract boolean zzb();

    /* access modifiers changed from: package-private */
    public abstract boolean zzc();

    @VisibleForTesting
    static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    @VisibleForTesting
    static Boolean zza(String str, zzbk.zzf zzf2, zzes zzes) {
        String str2;
        List<String> zzg;
        String str3 = null;
        Preconditions.checkNotNull(zzf2);
        if (str == null || !zzf2.zza() || zzf2.zzb() == zzbk.zzf.zza.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        if (zzf2.zzb() == zzbk.zzf.zza.IN_LIST) {
            if (zzf2.zzh() == 0) {
                return null;
            }
        } else if (!zzf2.zzc()) {
            return null;
        }
        zzbk.zzf.zza zzb2 = zzf2.zzb();
        boolean zzf3 = zzf2.zzf();
        if (zzf3 || zzb2 == zzbk.zzf.zza.REGEXP || zzb2 == zzbk.zzf.zza.IN_LIST) {
            str2 = zzf2.zzd();
        } else {
            str2 = zzf2.zzd().toUpperCase(Locale.ENGLISH);
        }
        if (zzf2.zzh() == 0) {
            zzg = null;
        } else {
            zzg = zzf2.zzg();
            if (!zzf3) {
                ArrayList arrayList = new ArrayList(zzg.size());
                for (String upperCase : zzg) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                zzg = Collections.unmodifiableList(arrayList);
            }
        }
        if (zzb2 == zzbk.zzf.zza.REGEXP) {
            str3 = str2;
        }
        return zza(str, zzb2, zzf3, str2, zzg, str3, zzes);
    }

    private static Boolean zza(String str, zzbk.zzf.zza zza2, boolean z, String str2, List<String> list, String str3, zzes zzes) {
        if (str == null) {
            return null;
        }
        if (zza2 == zzbk.zzf.zza.IN_LIST) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && zza2 != zzbk.zzf.zza.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (zza2) {
            case REGEXP:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    if (zzes != null) {
                        zzes.zzi().zza("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case BEGINS_WITH:
                return Boolean.valueOf(str.startsWith(str2));
            case ENDS_WITH:
                return Boolean.valueOf(str.endsWith(str2));
            case PARTIAL:
                return Boolean.valueOf(str.contains(str2));
            case EXACT:
                return Boolean.valueOf(str.equals(str2));
            case IN_LIST:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    static Boolean zza(long j, zzbk.zzd zzd2) {
        try {
            return zza(new BigDecimal(j), zzd2, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zza(double d, zzbk.zzd zzd2) {
        try {
            return zza(new BigDecimal(d), zzd2, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zza(String str, zzbk.zzd zzd2) {
        if (!zzki.zza(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzd2, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(BigDecimal bigDecimal, zzbk.zzd zzd2, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        boolean z;
        boolean z2 = true;
        Preconditions.checkNotNull(zzd2);
        if (!zzd2.zza() || zzd2.zzb() == zzbk.zzd.zzb.UNKNOWN_COMPARISON_TYPE) {
            return null;
        }
        if (zzd2.zzb() == zzbk.zzd.zzb.BETWEEN) {
            if (!zzd2.zzg() || !zzd2.zzi()) {
                return null;
            }
        } else if (!zzd2.zze()) {
            return null;
        }
        zzbk.zzd.zzb zzb2 = zzd2.zzb();
        if (zzd2.zzb() == zzbk.zzd.zzb.BETWEEN) {
            if (!zzki.zza(zzd2.zzh()) || !zzki.zza(zzd2.zzj())) {
                return null;
            }
            try {
                bigDecimal4 = new BigDecimal(zzd2.zzh());
                bigDecimal3 = new BigDecimal(zzd2.zzj());
                bigDecimal2 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (!zzki.zza(zzd2.zzf())) {
            return null;
        } else {
            try {
                bigDecimal2 = new BigDecimal(zzd2.zzf());
                bigDecimal3 = null;
                bigDecimal4 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        if (zzb2 == zzbk.zzd.zzb.BETWEEN) {
            if (bigDecimal4 == null) {
                return null;
            }
        } else if (bigDecimal2 == null) {
            return null;
        }
        switch (zzb2) {
            case LESS_THAN:
                if (bigDecimal.compareTo(bigDecimal2) == -1) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            case GREATER_THAN:
                if (bigDecimal.compareTo(bigDecimal2) != 1) {
                    z2 = false;
                }
                return Boolean.valueOf(z2);
            case EQUAL:
                if (d != 0.0d) {
                    if (!(bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1)) {
                        z2 = false;
                    }
                    return Boolean.valueOf(z2);
                }
                if (bigDecimal.compareTo(bigDecimal2) != 0) {
                    z2 = false;
                }
                return Boolean.valueOf(z2);
            case BETWEEN:
                if (bigDecimal.compareTo(bigDecimal4) == -1 || bigDecimal.compareTo(bigDecimal3) == 1) {
                    z2 = false;
                }
                return Boolean.valueOf(z2);
            default:
                return null;
        }
    }
}
