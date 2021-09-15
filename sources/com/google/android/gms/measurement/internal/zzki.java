package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import bolts.MeasurementEvent;
import com.doraemon.util.ShellAdbUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzer;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzgp;
import com.google.android.gms.internal.measurement.zzjm;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
public final class zzki extends zzkb {
    zzki(zzka zzka) {
        super(zzka);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbs.zzk.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zza().zzb().zzc();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zzb(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzr().zzf().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbs.zze.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zza().zzb().zzc().zze();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else if (!zzjm.zzb() || !zzt().zza(zzaq.zzcg) || !(obj instanceof Bundle[])) {
            zzr().zzf().zza("Ignoring invalid (type) event param value", obj);
        } else {
            zza.zza((Iterable<? extends zzbs.zze>) zza((Bundle[]) obj));
        }
    }

    static zzbs.zze zza(zzbs.zzc zzc, String str) {
        for (zzbs.zze next : zzc.zza()) {
            if (next.zzb().equals(str)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Object zzb(zzbs.zzc zzc, String str) {
        zzbs.zze zza = zza(zzc, str);
        if (zza != null) {
            if (zza.zzc()) {
                return zza.zzd();
            }
            if (zza.zze()) {
                return Long.valueOf(zza.zzf());
            }
            if (zza.zzg()) {
                return Double.valueOf(zza.zzh());
            }
            if (zzjm.zzb() && zzt().zza(zzaq.zzcg) && zza.zzj() > 0) {
                List<zzbs.zze> zzi = zza.zzi();
                ArrayList arrayList = new ArrayList();
                for (zzbs.zze next : zzi) {
                    if (next != null) {
                        Bundle bundle = new Bundle();
                        for (zzbs.zze next2 : next.zzi()) {
                            if (next2.zzc()) {
                                bundle.putString(next2.zzb(), next2.zzd());
                            } else if (next2.zze()) {
                                bundle.putLong(next2.zzb(), next2.zzf());
                            } else if (next2.zzg()) {
                                bundle.putDouble(next2.zzb(), next2.zzh());
                            }
                        }
                        if (!bundle.isEmpty()) {
                            arrayList.add(bundle);
                        }
                    }
                }
                return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbs.zzc.zza zza, String str, Object obj) {
        int i;
        List<zzbs.zze> zza2 = zza.zza();
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= zza2.size()) {
                i = -1;
                break;
            } else if (str.equals(zza2.get(i).zzb())) {
                break;
            } else {
                i2 = i + 1;
            }
        }
        zzbs.zze.zza zza3 = zzbs.zze.zzk().zza(str);
        if (obj instanceof Long) {
            zza3.zza(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zza3.zzb((String) obj);
        } else if (obj instanceof Double) {
            zza3.zza(((Double) obj).doubleValue());
        } else if (zzjm.zzb() && zzt().zza(zzaq.zzcg) && (obj instanceof Bundle[])) {
            zza3.zza((Iterable<? extends zzbs.zze>) zza((Bundle[]) obj));
        }
        if (i >= 0) {
            zza.zza(i, zza3);
        } else {
            zza.zza(zza3);
        }
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzbs.zzf zzf) {
        Long l;
        Double d;
        if (zzf == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (zzbs.zzg next : zzf.zza()) {
            if (next != null) {
                zza(sb, 1);
                sb.append("bundle {\n");
                if (next.zza()) {
                    zza(sb, 1, "protocol_version", (Object) Integer.valueOf(next.zzb()));
                }
                zza(sb, 1, "platform", (Object) next.zzq());
                if (next.zzz()) {
                    zza(sb, 1, "gmp_version", (Object) Long.valueOf(next.zzaa()));
                }
                if (next.zzab()) {
                    zza(sb, 1, "uploading_gmp_version", (Object) Long.valueOf(next.zzac()));
                }
                if (next.zzbc()) {
                    zza(sb, 1, "dynamite_version", (Object) Long.valueOf(next.zzbd()));
                }
                if (next.zzau()) {
                    zza(sb, 1, "config_version", (Object) Long.valueOf(next.zzav()));
                }
                zza(sb, 1, "gmp_app_id", (Object) next.zzam());
                zza(sb, 1, "admob_app_id", (Object) next.zzbb());
                zza(sb, 1, "app_id", (Object) next.zzx());
                zza(sb, 1, "app_version", (Object) next.zzy());
                if (next.zzar()) {
                    zza(sb, 1, "app_version_major", (Object) Integer.valueOf(next.zzas()));
                }
                zza(sb, 1, "firebase_instance_id", (Object) next.zzaq());
                if (next.zzah()) {
                    zza(sb, 1, "dev_cert_hash", (Object) Long.valueOf(next.zzai()));
                }
                zza(sb, 1, "app_store", (Object) next.zzw());
                if (next.zzg()) {
                    zza(sb, 1, "upload_timestamp_millis", (Object) Long.valueOf(next.zzh()));
                }
                if (next.zzi()) {
                    zza(sb, 1, "start_timestamp_millis", (Object) Long.valueOf(next.zzj()));
                }
                if (next.zzk()) {
                    zza(sb, 1, "end_timestamp_millis", (Object) Long.valueOf(next.zzl()));
                }
                if (next.zzm()) {
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) Long.valueOf(next.zzn()));
                }
                if (next.zzo()) {
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) Long.valueOf(next.zzp()));
                }
                zza(sb, 1, "app_instance_id", (Object) next.zzag());
                zza(sb, 1, "resettable_device_id", (Object) next.zzad());
                zza(sb, 1, "device_id", (Object) next.zzat());
                zza(sb, 1, "ds_id", (Object) next.zzay());
                if (next.zzae()) {
                    zza(sb, 1, "limited_ad_tracking", (Object) Boolean.valueOf(next.zzaf()));
                }
                zza(sb, 1, "os_version", (Object) next.zzr());
                zza(sb, 1, "device_model", (Object) next.zzs());
                zza(sb, 1, "user_default_language", (Object) next.zzt());
                if (next.zzu()) {
                    zza(sb, 1, "time_zone_offset_minutes", (Object) Integer.valueOf(next.zzv()));
                }
                if (next.zzaj()) {
                    zza(sb, 1, "bundle_sequential_index", (Object) Integer.valueOf(next.zzak()));
                }
                if (next.zzan()) {
                    zza(sb, 1, "service_upload", (Object) Boolean.valueOf(next.zzao()));
                }
                zza(sb, 1, "health_monitor", (Object) next.zzal());
                if (!zzt().zza(zzaq.zzcm) && next.zzaw() && next.zzax() != 0) {
                    zza(sb, 1, "android_id", (Object) Long.valueOf(next.zzax()));
                }
                if (next.zzaz()) {
                    zza(sb, 1, "retry_counter", (Object) Integer.valueOf(next.zzba()));
                }
                List<zzbs.zzk> zze = next.zze();
                if (zze != null) {
                    for (zzbs.zzk next2 : zze) {
                        if (next2 != null) {
                            zza(sb, 2);
                            sb.append("user_property {\n");
                            zza(sb, 2, "set_timestamp_millis", (Object) next2.zza() ? Long.valueOf(next2.zzb()) : null);
                            zza(sb, 2, "name", (Object) zzo().zzc(next2.zzc()));
                            zza(sb, 2, "string_value", (Object) next2.zze());
                            if (next2.zzf()) {
                                l = Long.valueOf(next2.zzg());
                            } else {
                                l = null;
                            }
                            zza(sb, 2, "int_value", (Object) l);
                            if (next2.zzh()) {
                                d = Double.valueOf(next2.zzi());
                            } else {
                                d = null;
                            }
                            zza(sb, 2, "double_value", (Object) d);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzbs.zza> zzap = next.zzap();
                String zzx = next.zzx();
                if (zzap != null) {
                    for (zzbs.zza next3 : zzap) {
                        if (next3 != null) {
                            zza(sb, 2);
                            sb.append("audience_membership {\n");
                            if (next3.zza()) {
                                zza(sb, 2, "audience_id", (Object) Integer.valueOf(next3.zzb()));
                            }
                            if (next3.zzf()) {
                                zza(sb, 2, "new_audience", (Object) Boolean.valueOf(next3.zzg()));
                            }
                            zza(sb, 2, "current_data", next3.zzc(), zzx);
                            if (next3.zzd()) {
                                zza(sb, 2, "previous_data", next3.zze(), zzx);
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzbs.zzc> zzc = next.zzc();
                if (zzc != null) {
                    for (zzbs.zzc next4 : zzc) {
                        if (next4 != null) {
                            zza(sb, 2);
                            sb.append("event {\n");
                            zza(sb, 2, "name", (Object) zzo().zza(next4.zzc()));
                            if (next4.zzd()) {
                                zza(sb, 2, "timestamp_millis", (Object) Long.valueOf(next4.zze()));
                            }
                            if (next4.zzf()) {
                                zza(sb, 2, "previous_timestamp_millis", (Object) Long.valueOf(next4.zzg()));
                            }
                            if (next4.zzh()) {
                                zza(sb, 2, "count", (Object) Integer.valueOf(next4.zzi()));
                            }
                            if (next4.zzb() != 0) {
                                zza(sb, 2, next4.zza());
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zza(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzbk.zzb zzb) {
        if (zzb == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzb.zza()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zzb.zzb()));
        }
        zza(sb, 0, MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, (Object) zzo().zza(zzb.zzc()));
        String zza = zza(zzb.zzh(), zzb.zzi(), zzb.zzk());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        if (zzb.zzf()) {
            zza(sb, 1, "event_count_filter", zzb.zzg());
        }
        if (zzb.zze() > 0) {
            sb.append("  filters {\n");
            for (zzbk.zzc zza2 : zzb.zzd()) {
                zza(sb, 2, zza2);
            }
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzbk.zze zze) {
        if (zze == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zze.zza()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zze.zzb()));
        }
        zza(sb, 0, "property_name", (Object) zzo().zzc(zze.zzc()));
        String zza = zza(zze.zze(), zze.zzf(), zze.zzh());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        zza(sb, 1, zze.zzd());
        sb.append("}\n");
        return sb.toString();
    }

    private static String zza(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private final void zza(StringBuilder sb, int i, List<zzbs.zze> list) {
        Long l;
        Double d;
        String str;
        Long l2;
        Double d2;
        if (list != null) {
            int i2 = i + 1;
            for (zzbs.zze next : list) {
                if (next != null) {
                    zza(sb, i2);
                    sb.append("param {\n");
                    if (!zzjm.zzb() || !zzt().zza(zzaq.zzce)) {
                        zza(sb, i2, "name", (Object) zzo().zzb(next.zzb()));
                        zza(sb, i2, "string_value", (Object) next.zzd());
                        if (next.zze()) {
                            l = Long.valueOf(next.zzf());
                        } else {
                            l = null;
                        }
                        zza(sb, i2, "int_value", (Object) l);
                        if (next.zzg()) {
                            d = Double.valueOf(next.zzh());
                        } else {
                            d = null;
                        }
                        zza(sb, i2, "double_value", (Object) d);
                    } else {
                        zza(sb, i2, "name", (Object) next.zza() ? zzo().zzb(next.zzb()) : null);
                        if (next.zzc()) {
                            str = next.zzd();
                        } else {
                            str = null;
                        }
                        zza(sb, i2, "string_value", (Object) str);
                        if (next.zze()) {
                            l2 = Long.valueOf(next.zzf());
                        } else {
                            l2 = null;
                        }
                        zza(sb, i2, "int_value", (Object) l2);
                        if (next.zzg()) {
                            d2 = Double.valueOf(next.zzh());
                        } else {
                            d2 = null;
                        }
                        zza(sb, i2, "double_value", (Object) d2);
                        if (next.zzj() > 0) {
                            zza(sb, i2, next.zzi());
                        }
                    }
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzbs.zzi zzi, String str2) {
        Integer num;
        Long l;
        if (zzi != null) {
            int i2 = i + 1;
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzi.zzd() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i3 = 0;
                for (Long next : zzi.zzc()) {
                    int i4 = i3 + 1;
                    if (i3 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next);
                    i3 = i4;
                }
                sb.append(10);
            }
            if (zzi.zzb() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i5 = 0;
                for (Long next2 : zzi.zza()) {
                    int i6 = i5 + 1;
                    if (i5 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next2);
                    i5 = i6;
                }
                sb.append(10);
            }
            if (zzi.zzf() != 0) {
                zza(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i7 = 0;
                for (zzbs.zzb next3 : zzi.zze()) {
                    int i8 = i7 + 1;
                    if (i7 != 0) {
                        sb.append(", ");
                    }
                    if (next3.zza()) {
                        num = Integer.valueOf(next3.zzb());
                    } else {
                        num = null;
                    }
                    StringBuilder append = sb.append(num).append(":");
                    if (next3.zzc()) {
                        l = Long.valueOf(next3.zzd());
                    } else {
                        l = null;
                    }
                    append.append(l);
                    i7 = i8;
                }
                sb.append("}\n");
            }
            if (zzi.zzh() != 0) {
                zza(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i9 = 0;
                for (zzbs.zzj next4 : zzi.zzg()) {
                    int i10 = i9 + 1;
                    if (i9 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next4.zza() ? Integer.valueOf(next4.zzb()) : null).append(": [");
                    int i11 = 0;
                    for (Long longValue : next4.zzc()) {
                        long longValue2 = longValue.longValue();
                        int i12 = i11 + 1;
                        if (i11 != 0) {
                            sb.append(", ");
                        }
                        sb.append(longValue2);
                        i11 = i12;
                    }
                    sb.append("]");
                    i9 = i10;
                }
                sb.append("}\n");
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzbk.zzd zzd) {
        if (zzd != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zza()) {
                zza(sb, i, "comparison_type", (Object) zzd.zzb().name());
            }
            if (zzd.zzc()) {
                zza(sb, i, "match_as_float", (Object) Boolean.valueOf(zzd.zzd()));
            }
            if (zzd.zze()) {
                zza(sb, i, "comparison_value", (Object) zzd.zzf());
            }
            if (zzd.zzg()) {
                zza(sb, i, "min_comparison_value", (Object) zzd.zzh());
            }
            if (zzd.zzi()) {
                zza(sb, i, "max_comparison_value", (Object) zzd.zzj());
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, zzbk.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append("filter {\n");
            if (zzc.zze()) {
                zza(sb, i, "complement", (Object) Boolean.valueOf(zzc.zzf()));
            }
            if (zzc.zzg()) {
                zza(sb, i, "param_name", (Object) zzo().zzb(zzc.zzh()));
            }
            if (zzc.zza()) {
                int i2 = i + 1;
                zzbk.zzf zzb = zzc.zzb();
                if (zzb != null) {
                    zza(sb, i2);
                    sb.append("string_filter");
                    sb.append(" {\n");
                    if (zzb.zza()) {
                        zza(sb, i2, "match_type", (Object) zzb.zzb().name());
                    }
                    if (zzb.zzc()) {
                        zza(sb, i2, "expression", (Object) zzb.zzd());
                    }
                    if (zzb.zze()) {
                        zza(sb, i2, "case_sensitive", (Object) Boolean.valueOf(zzb.zzf()));
                    }
                    if (zzb.zzh() > 0) {
                        zza(sb, i2 + 1);
                        sb.append("expression_list {\n");
                        for (String append : zzb.zzg()) {
                            zza(sb, i2 + 2);
                            sb.append(append);
                            sb.append(ShellAdbUtil.COMMAND_LINE_END);
                        }
                        sb.append("}\n");
                    }
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
            if (zzc.zzc()) {
                zza(sb, i + 1, "number_filter", zzc.zzd());
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final <T extends Parcelable> T zza(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            T t = (Parcelable) creator.createFromParcel(obtain);
            obtain.recycle();
            return t;
        } catch (SafeParcelReader.ParseException e) {
            zzr().zzf().zza("Failed to load parcelable from buffer");
            obtain.recycle();
            return null;
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    @WorkerThread
    static boolean zza(zzao zzao, zzn zzn) {
        Preconditions.checkNotNull(zzao);
        Preconditions.checkNotNull(zzn);
        if (!TextUtils.isEmpty(zzn.zzb) || !TextUtils.isEmpty(zzn.zzr)) {
            return true;
        }
        return false;
    }

    static boolean zza(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zza(List<Long> list, int i) {
        return i < (list.size() << 6) && (list.get(i / 64).longValue() & (1 << (i % 64))) != 0;
    }

    static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            long j = 0;
            int i2 = 0;
            while (i2 < 64 && (i << 6) + i2 < bitSet.length()) {
                if (bitSet.get((i << 6) + i2)) {
                    j |= 1 << i2;
                }
                i2++;
            }
            arrayList.add(Long.valueOf(j));
            i++;
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        ArrayList arrayList = new ArrayList(list);
        for (Integer next : list2) {
            if (next.intValue() < 0) {
                zzr().zzi().zza("Ignoring negative bit index to be cleared", next);
            } else {
                int intValue = next.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzr().zzi().zza("Ignoring bit index greater than bitSet size", next, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((1 << (next.intValue() % 64)) ^ -1) & ((Long) arrayList.get(intValue)).longValue()));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (size2 >= 0 && ((Long) arrayList.get(size2)).longValue() == 0) {
            size = size2;
            size2--;
        }
        return arrayList.subList(0, size);
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzm().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzp().zzd();
        MessageDigest zzi = zzkm.zzi();
        if (zzi != null) {
            return zzkm.zza(zzi.digest(bArr));
        }
        zzr().zzf().zza("Failed to get MD5");
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzr().zzf().zza("Failed to ungzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzc(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzr().zzf().zza("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final List<Integer> zzf() {
        Map<String, String> zza = zzaq.zza(this.zza.zzn());
        if (zza == null || zza.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = zzaq.zzao.zza(null).intValue();
        Iterator<Map.Entry<String, String>> it = zza.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            if (((String) next.getKey()).startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt((String) next.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzr().zzi().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzr().zzi().zza("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    static <Builder extends zzgp> Builder zza(Builder builder, byte[] bArr) throws zzfm {
        zzer zzb = zzer.zzb();
        if (zzb != null) {
            return builder.zza(bArr, zzb);
        }
        return builder.zza(bArr);
    }

    static int zza(zzbs.zzg.zza zza, String str) {
        if (zza == null) {
            return -1;
        }
        for (int i = 0; i < zza.zze(); i++) {
            if (str.equals(zza.zzd(i).zzc())) {
                return i;
            }
        }
        return -1;
    }

    private static List<zzbs.zze> zza(Bundle[] bundleArr) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : bundleArr) {
            if (bundle != null) {
                zzbs.zze.zza zzk = zzbs.zze.zzk();
                for (String str : bundle.keySet()) {
                    zzbs.zze.zza zza = zzbs.zze.zzk().zza(str);
                    Object obj = bundle.get(str);
                    if (obj instanceof Long) {
                        zza.zza(((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        zza.zzb((String) obj);
                    } else if (obj instanceof Double) {
                        zza.zza(((Double) obj).doubleValue());
                    }
                    zzk.zza(zza);
                }
                if (zzk.zzd() > 0) {
                    arrayList.add((zzbs.zze) ((zzfe) zzk.zzv()));
                }
            }
        }
        return arrayList;
    }

    public final /* bridge */ /* synthetic */ zzki zzg() {
        return super.zzg();
    }

    /* renamed from: e_ */
    public final /* bridge */ /* synthetic */ zzo mo24394e_() {
        return super.mo24394e_();
    }

    public final /* bridge */ /* synthetic */ zzad zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzfq zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzeq zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzkm zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzft zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzes zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzfe zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
