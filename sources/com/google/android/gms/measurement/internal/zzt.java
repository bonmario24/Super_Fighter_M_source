package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzfe;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzt {
    private zzbs.zzc zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzo zzd;

    private zzt(zzo zzo) {
        this.zzd = zzo;
    }

    /* access modifiers changed from: package-private */
    public final zzbs.zzc zza(String str, zzbs.zzc zzc2) {
        ArrayList arrayList;
        String str2;
        boolean z = true;
        String zzc3 = zzc2.zzc();
        List<zzbs.zze> zza2 = zzc2.zza();
        Long l = (Long) this.zzd.zzg().zzb(zzc2, "_eid");
        boolean z2 = l != null;
        if (!z2 || !zzc3.equals("_ep")) {
            z = false;
        }
        if (z) {
            String str3 = (String) this.zzd.zzg().zzb(zzc2, "_en");
            if (TextUtils.isEmpty(str3)) {
                this.zzd.zzr().zzg().zza("Extra parameter without an event name. eventId", l);
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair<zzbs.zzc, Long> zza3 = this.zzd.zzi().zza(str, l);
                if (zza3 == null || zza3.first == null) {
                    this.zzd.zzr().zzg().zza("Extra parameter without existing main event. eventName, eventId", str3, l);
                    return null;
                }
                this.zza = (zzbs.zzc) zza3.first;
                this.zzc = ((Long) zza3.second).longValue();
                this.zzb = (Long) this.zzd.zzg().zzb(this.zza, "_eid");
            }
            this.zzc--;
            if (this.zzc <= 0) {
                zzad zzi = this.zzd.zzi();
                zzi.zzd();
                zzi.zzr().zzx().zza("Clearing complex main event info. appId", str);
                try {
                    zzi.mo24238c_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e) {
                    zzi.zzr().zzf().zza("Error clearing complex main event", e);
                }
            } else {
                this.zzd.zzi().zza(str, l, this.zzc, this.zza);
            }
            ArrayList arrayList2 = new ArrayList();
            for (zzbs.zze next : this.zza.zza()) {
                this.zzd.zzg();
                if (zzki.zza(zzc2, next.zzb()) == null) {
                    arrayList2.add(next);
                }
            }
            if (!arrayList2.isEmpty()) {
                arrayList2.addAll(zza2);
                arrayList = arrayList2;
                str2 = str3;
            } else {
                this.zzd.zzr().zzg().zza("No unique parameters in main event. eventName", str3);
                arrayList = zza2;
                str2 = str3;
            }
        } else {
            if (z2) {
                this.zzb = l;
                this.zza = zzc2;
                long j = 0L;
                Object zzb2 = this.zzd.zzg().zzb(zzc2, "_epc");
                if (zzb2 != null) {
                    j = zzb2;
                }
                this.zzc = ((Long) j).longValue();
                if (this.zzc <= 0) {
                    this.zzd.zzr().zzg().zza("Complex event with zero extra param count. eventName", zzc3);
                    arrayList = zza2;
                    str2 = zzc3;
                } else {
                    this.zzd.zzi().zza(str, l, this.zzc, zzc2);
                }
            }
            arrayList = zza2;
            str2 = zzc3;
        }
        return (zzbs.zzc) ((zzfe) ((zzbs.zzc.zza) zzc2.zzbl()).zza(str2).zzc().zza((Iterable<? extends zzbs.zze>) arrayList).zzv());
    }

    /* synthetic */ zzt(zzo zzo, zzr zzr) {
        this(zzo);
    }
}
