package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzjm;
import com.google.android.gms.internal.measurement.zzjx;
import com.google.android.gms.internal.measurement.zzke;
import com.google.android.gms.internal.measurement.zzkp;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzhb extends zzg {
    @VisibleForTesting
    protected zzhw zza;
    final zzp zzb;
    @VisibleForTesting
    protected boolean zzc = true;
    private zzgw zzd;
    private final Set<zzgz> zze = new CopyOnWriteArraySet();
    private boolean zzf;
    private final AtomicReference<String> zzg = new AtomicReference<>();

    protected zzhb(zzfw zzfw) {
        super(zzfw);
        this.zzb = new zzp(zzfw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    public final void zzab() {
        if (zzn().getApplicationContext() instanceof Application) {
            ((Application) zzn().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    public final Boolean zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzq().zza(atomicReference, 15000, "boolean test flag value", new zzhc(this, atomicReference));
    }

    public final String zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzq().zza(atomicReference, 15000, "String test flag value", new zzhm(this, atomicReference));
    }

    public final Long zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzq().zza(atomicReference, 15000, "long test flag value", new zzhr(this, atomicReference));
    }

    public final Integer zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzq().zza(atomicReference, 15000, "int test flag value", new zzhq(this, atomicReference));
    }

    public final Double zzag() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzq().zza(atomicReference, 15000, "double test flag value", new zzht(this, atomicReference));
    }

    public final void zza(boolean z) {
        zzw();
        zzb();
        zzq().zza((Runnable) new zzhs(this, z));
    }

    public final void zzb(boolean z) {
        zzw();
        zzb();
        zzq().zza((Runnable) new zzhv(this, z));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzd(boolean z) {
        zzd();
        zzb();
        zzw();
        zzr().zzw().zza("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzs().zzb(z);
        zzam();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzam() {
        long j;
        zzd();
        String zza2 = zzs().zzn.zza();
        if (zza2 != null) {
            if ("unset".equals(zza2)) {
                zza("app", "_npa", (Object) null, zzm().currentTimeMillis());
            } else {
                if ("true".equals(zza2)) {
                    j = 1;
                } else {
                    j = 0;
                }
                zza("app", "_npa", (Object) Long.valueOf(j), zzm().currentTimeMillis());
            }
        }
        if (!this.zzz.zzab() || !this.zzc) {
            zzr().zzw().zza("Updating Scion state (FE)");
            zzh().zzac();
            return;
        }
        zzr().zzw().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzai();
        if (zzkp.zzb() && zzt().zza(zzaq.zzbw)) {
            zzk().zza.zza();
        }
        if (zzke.zzb() && zzt().zza(zzaq.zzcb)) {
            if (!(this.zzz.zzf().zza.zzc().zzi.zza() > 0)) {
                zzfn zzf2 = this.zzz.zzf();
                zzf2.zza.zzad();
                zzf2.zza(zzf2.zza.zzn().getPackageName());
            }
        }
        if (zzt().zza(zzaq.zzcp)) {
            zzq().zza((Runnable) new zzhu(this));
        }
    }

    public final void zza(long j) {
        zzb();
        zzq().zza((Runnable) new zzhx(this, j));
    }

    public final void zzb(long j) {
        zzb();
        zzq().zza((Runnable) new zzhf(this, j));
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zza(str, str2, bundle, false, true, zzm().currentTimeMillis());
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzm().currentTimeMillis());
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(String str, String str2, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, zzm().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, long j, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, j, bundle, true, this.zzd == null || zzkm.zze(str2), false, (String) null);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzih zzih;
        zzih zzih2;
        String str4;
        Bundle bundle2;
        int i;
        String trim;
        boolean z4;
        int i2;
        Class<?> cls;
        List<String> zzah;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzd();
        zzw();
        if (!this.zzz.zzab()) {
            zzr().zzw().zza("Event not sent since app measurement is disabled");
        } else if (!zzt().zza(zzaq.zzbc) || (zzah = zzg().zzah()) == null || zzah.contains(str2)) {
            if (!this.zzf) {
                this.zzf = true;
                try {
                    if (!this.zzz.zzt()) {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, zzn().getClassLoader());
                    } else {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                    }
                    try {
                        cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{zzn()});
                    } catch (Exception e) {
                        zzr().zzi().zza("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException e2) {
                    zzr().zzv().zza("Tag Manager is not found and thus will not be used");
                }
            }
            if (zzt().zza(zzaq.zzbi) && "_cmp".equals(str2) && bundle.containsKey("gclid")) {
                zza("auto", "_lgclid", (Object) bundle.getString("gclid"), zzm().currentTimeMillis());
            }
            if (zzlc.zzb() && zzt().zza(zzaq.zzcn)) {
                zzu();
                if (z && zzkm.zzg(str2)) {
                    zzkm zzp = zzp();
                    Bundle zza2 = zzs().zzy.zza();
                    if (zza2 != null) {
                        for (String str5 : zza2.keySet()) {
                            if (!bundle.containsKey(str5)) {
                                zzp.zzp().zza(bundle, str5, zza2.get(str5));
                            }
                        }
                    }
                }
            }
            if (z3) {
                zzu();
                if (!"_iap".equals(str2)) {
                    zzkm zzi = this.zzz.zzi();
                    if (!zzi.zza("event", str2)) {
                        i2 = 2;
                    } else if (!zzi.zza("event", zzgv.zza, str2)) {
                        i2 = 13;
                    } else if (!zzi.zza("event", 40, str2)) {
                        i2 = 2;
                    } else {
                        i2 = 0;
                    }
                    if (i2 != 0) {
                        zzr().zzh().zza("Invalid public event name. Event will not be logged (FE)", zzo().zza(str2));
                        this.zzz.zzi();
                        this.zzz.zzi().zza(i2, "_ev", zzkm.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                        return;
                    }
                }
            }
            zzu();
            zzih zzab = zzi().zzab();
            if (zzab != null && !bundle.containsKey("_sc")) {
                zzab.zzd = true;
            }
            zzig.zza(zzab, bundle, z && z3);
            boolean equals = "am".equals(str);
            boolean zze2 = zzkm.zze(str2);
            if (z && this.zzd != null && !zze2 && !equals) {
                zzr().zzw().zza("Passing event to registered event handler (FE)", zzo().zza(str2), zzo().zza(bundle));
                this.zzd.interceptEvent(str, str2, bundle, j);
            } else if (this.zzz.zzag()) {
                int zzb2 = zzp().zzb(str2);
                if (zzb2 != 0) {
                    zzr().zzh().zza("Invalid event name. Event will not be logged (FE)", zzo().zza(str2));
                    zzp();
                    this.zzz.zzi().zza(str3, zzb2, "_ev", zzkm.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
                List listOf = CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"});
                Bundle zza3 = zzp().zza(str3, str2, bundle, (List<String>) listOf, z3, true);
                if (zza3 == null || !zza3.containsKey("_sc") || !zza3.containsKey("_si")) {
                    zzih = null;
                } else {
                    zzih = new zzih(zza3.getString("_sn"), zza3.getString("_sc"), Long.valueOf(zza3.getLong("_si")).longValue());
                }
                if (zzih == null) {
                    zzih2 = zzab;
                } else {
                    zzih2 = zzih;
                }
                if (zzt().zza(zzaq.zzat)) {
                    zzu();
                    if (zzi().zzab() != null && "_ae".equals(str2)) {
                        long zzb3 = zzk().zzb.zzb();
                        if (zzb3 > 0) {
                            zzp().zza(zza3, zzb3);
                        }
                    }
                }
                if (zzjx.zzb() && zzt().zza(zzaq.zzbv)) {
                    if (!"auto".equals(str) && "_ssr".equals(str2)) {
                        zzkm zzp2 = zzp();
                        String string = zza3.getString("_ffr");
                        if (Strings.isEmptyOrWhitespace(string)) {
                            trim = null;
                        } else {
                            trim = string.trim();
                        }
                        if (zzkm.zzc(trim, zzp2.zzs().zzv.zza())) {
                            zzp2.zzr().zzw().zza("Not logging duplicate session_start_with_rollout event");
                            z4 = false;
                        } else {
                            zzp2.zzs().zzv.zza(trim);
                            z4 = true;
                        }
                        if (!z4) {
                            return;
                        }
                    } else if ("_ae".equals(str2)) {
                        String zza4 = zzp().zzs().zzv.zza();
                        if (!TextUtils.isEmpty(zza4)) {
                            zza3.putString("_ffr", zza4);
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza3);
                long nextLong = zzp().zzh().nextLong();
                if (zzs().zzq.zza() > 0 && zzs().zza(j) && zzs().zzs.zza()) {
                    zzr().zzx().zza("Current session is expired, remove the session number, ID, and engagement time");
                    zza("auto", "_sid", (Object) null, zzm().currentTimeMillis());
                    zza("auto", "_sno", (Object) null, zzm().currentTimeMillis());
                    zza("auto", "_se", (Object) null, zzm().currentTimeMillis());
                }
                if (zza3.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, 0) == 1) {
                    zzr().zzx().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    this.zzz.zze().zza.zza(j, true);
                }
                String[] strArr = (String[]) zza3.keySet().toArray(new String[zza3.size()]);
                Arrays.sort(strArr);
                if (!zzjm.zzb() || !zzt().zza(zzaq.zzch) || !zzt().zza(zzaq.zzcg)) {
                    int i3 = 0;
                    int length = strArr.length;
                    int i4 = 0;
                    while (i4 < length) {
                        String str6 = strArr[i4];
                        Object obj = zza3.get(str6);
                        zzp();
                        Bundle[] zzb4 = zzkm.zzb(obj);
                        if (zzb4 != null) {
                            zza3.putInt(str6, zzb4.length);
                            int i5 = 0;
                            while (true) {
                                int i6 = i5;
                                if (i6 >= zzb4.length) {
                                    break;
                                }
                                Bundle bundle3 = zzb4[i6];
                                zzig.zza(zzih2, bundle3, true);
                                Bundle zza5 = zzp().zza(str3, "_ep", bundle3, (List<String>) listOf, z3, false);
                                zza5.putString("_en", str2);
                                zza5.putLong("_eid", nextLong);
                                zza5.putString("_gn", str6);
                                zza5.putInt("_ll", zzb4.length);
                                zza5.putInt("_i", i6);
                                arrayList.add(zza5);
                                i5 = i6 + 1;
                            }
                            i = zzb4.length + i3;
                        } else {
                            i = i3;
                        }
                        i4++;
                        i3 = i;
                    }
                    if (i3 != 0) {
                        zza3.putLong("_eid", nextLong);
                        zza3.putInt("_epc", i3);
                    }
                } else {
                    for (String str7 : strArr) {
                        zzp();
                        Bundle[] zzb5 = zzkm.zzb(zza3.get(str7));
                        if (zzb5 != null) {
                            zza3.putParcelableArray(str7, zzb5);
                        }
                    }
                }
                int i7 = 0;
                while (true) {
                    int i8 = i7;
                    if (i8 >= arrayList.size()) {
                        break;
                    }
                    Bundle bundle4 = (Bundle) arrayList.get(i8);
                    if (i8 != 0) {
                        str4 = "_ep";
                    } else {
                        str4 = str2;
                    }
                    bundle4.putString("_o", str);
                    if (z2) {
                        bundle2 = zzp().zza(bundle4);
                    } else {
                        bundle2 = bundle4;
                    }
                    zzh().zza(new zzao(str4, new zzan(bundle2), str, j), str3);
                    if (!equals) {
                        for (zzgz onEvent : this.zze) {
                            onEvent.onEvent(str, str2, new Bundle(bundle2), j);
                        }
                    }
                    i7 = i8 + 1;
                }
                zzu();
                if (zzi().zzab() != null && "_ae".equals(str2)) {
                    zzk().zza(true, true, zzm().elapsedRealtime());
                }
            }
        } else {
            zzr().zzw().zza("Dropping non-safelisted event. event name, origin", str2, str);
        }
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        String str3;
        Bundle bundle2;
        zzb();
        if (str == null) {
            str3 = "app";
        } else {
            str3 = str;
        }
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = bundle;
        }
        zzb(str3, str2, j, bundle2, z2, !z2 || this.zzd == null || zzkm.zze(str2), !z, (String) null);
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzq().zza((Runnable) new zzhe(this, str, str2, j, zzkm.zzb(bundle), z, z2, z3, str3));
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzm().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        String str3;
        int i = 6;
        int i2 = 0;
        if (str == null) {
            str3 = "app";
        } else {
            str3 = str;
        }
        if (z) {
            i = zzp().zzc(str2);
        } else {
            zzkm zzp = zzp();
            if (zzp.zza("user property", str2)) {
                if (!zzp.zza("user property", zzgx.zza, str2)) {
                    i = 15;
                } else if (zzp.zza("user property", 24, str2)) {
                    i = 0;
                }
            }
        }
        if (i != 0) {
            zzp();
            String zza2 = zzkm.zza(str2, 24, true);
            if (str2 != null) {
                i2 = str2.length();
            }
            this.zzz.zzi().zza(i, "_ev", zza2, i2);
        } else if (obj != null) {
            int zzb2 = zzp().zzb(str2, obj);
            if (zzb2 != 0) {
                zzp();
                String zza3 = zzkm.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i2 = String.valueOf(obj).length();
                }
                this.zzz.zzi().zza(zzb2, "_ev", zza3, i2);
                return;
            }
            Object zzc2 = zzp().zzc(str2, obj);
            if (zzc2 != null) {
                zza(str3, str2, j, zzc2);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzq().zza((Runnable) new zzhh(this, str, str2, obj, j));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0083  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r9, java.lang.String r10, java.lang.Object r11, long r12) {
        /*
            r8 = this;
            r4 = 1
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r8.zzd()
            r8.zzb()
            r8.zzw()
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x0080
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L_0x0071
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0071
            java.lang.String r0 = "false"
            java.lang.String r11 = (java.lang.String) r11
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r1 = r11.toLowerCase(r1)
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x006b
            r0 = r4
        L_0x0037:
            java.lang.Long r2 = java.lang.Long.valueOf(r0)
            java.lang.String r10 = "_npa"
            com.google.android.gms.measurement.internal.zzfe r0 = r8.zzs()
            com.google.android.gms.measurement.internal.zzfk r1 = r0.zzn
            r0 = r2
            java.lang.Long r0 = (java.lang.Long) r0
            long r6 = r0.longValue()
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x006e
            java.lang.String r0 = "true"
        L_0x0050:
            r1.zza(r0)
            r4 = r2
            r1 = r10
        L_0x0055:
            com.google.android.gms.measurement.internal.zzfw r0 = r8.zzz
            boolean r0 = r0.zzab()
            if (r0 != 0) goto L_0x0083
            com.google.android.gms.measurement.internal.zzes r0 = r8.zzr()
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzx()
            java.lang.String r1 = "User property not set since app measurement is disabled"
            r0.zza(r1)
        L_0x006a:
            return
        L_0x006b:
            r0 = 0
            goto L_0x0037
        L_0x006e:
            java.lang.String r0 = "false"
            goto L_0x0050
        L_0x0071:
            if (r11 != 0) goto L_0x0080
            java.lang.String r10 = "_npa"
            com.google.android.gms.measurement.internal.zzfe r0 = r8.zzs()
            com.google.android.gms.measurement.internal.zzfk r0 = r0.zzn
            java.lang.String r1 = "unset"
            r0.zza(r1)
        L_0x0080:
            r4 = r11
            r1 = r10
            goto L_0x0055
        L_0x0083:
            com.google.android.gms.measurement.internal.zzfw r0 = r8.zzz
            boolean r0 = r0.zzag()
            if (r0 == 0) goto L_0x006a
            com.google.android.gms.measurement.internal.zzkh r0 = new com.google.android.gms.measurement.internal.zzkh
            r2 = r12
            r5 = r9
            r0.<init>(r1, r2, r4, r5)
            com.google.android.gms.measurement.internal.zzil r1 = r8.zzh()
            r1.zza((com.google.android.gms.measurement.internal.zzkh) r0)
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhb.zza(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public final List<zzkh> zzc(boolean z) {
        zzb();
        zzw();
        zzr().zzx().zza("Getting user properties (FE)");
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzx.zza()) {
            zzr().zzf().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzz.zzq().zza(atomicReference, 5000, "get user properties", new zzhg(this, atomicReference, z));
            List<zzkh> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzr().zzf().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyList();
        }
    }

    @Nullable
    public final String zzah() {
        zzb();
        return this.zzg.get();
    }

    @Nullable
    public final String zzc(long j) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot retrieve app instance id from analytics worker thread");
            return null;
        } else if (zzx.zza()) {
            zzr().zzf().zza("Cannot retrieve app instance id from main thread");
            return null;
        } else {
            long elapsedRealtime = zzm().elapsedRealtime();
            String zze2 = zze(120000);
            long elapsedRealtime2 = zzm().elapsedRealtime() - elapsedRealtime;
            if (zze2 != null || elapsedRealtime2 >= 120000) {
                return zze2;
            }
            return zze(120000 - elapsedRealtime2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(@Nullable String str) {
        this.zzg.set(str);
    }

    @Nullable
    private final String zze(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzq().zza((Runnable) new zzhj(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException e) {
                zzr().zzi().zza("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final void zzd(long j) {
        zza((String) null);
        zzq().zza((Runnable) new zzhi(this, j));
    }

    @WorkerThread
    public final void zzai() {
        zzd();
        zzb();
        zzw();
        if (this.zzz.zzag()) {
            if (zzt().zza(zzaq.zzbh)) {
                zzy zzt = zzt();
                zzt.zzu();
                Boolean zzd2 = zzt.zzd("google_analytics_deferred_deep_link_enabled");
                if (zzd2 != null && zzd2.booleanValue()) {
                    zzr().zzw().zza("Deferred Deep Link feature enabled.");
                    zzq().zza((Runnable) new zzhd(this));
                }
            }
            zzh().zzae();
            this.zzc = false;
            String zzw = zzs().zzw();
            if (!TextUtils.isEmpty(zzw)) {
                zzl().zzaa();
                if (!zzw.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzw);
                    zza("auto", "_ou", bundle);
                }
            }
        }
    }

    @WorkerThread
    public final void zza(zzgw zzgw) {
        zzd();
        zzb();
        zzw();
        if (!(zzgw == null || zzgw == this.zzd)) {
            Preconditions.checkState(this.zzd == null, "EventInterceptor already set.");
        }
        this.zzd = zzgw;
    }

    public final void zza(zzgz zzgz) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzgz);
        if (!this.zze.add(zzgz)) {
            zzr().zzi().zza("OnEventListener already registered");
        }
    }

    public final void zzb(zzgz zzgz) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzgz);
        if (!this.zze.remove(zzgz)) {
            zzr().zzi().zza("OnEventListener had not been registered");
        }
    }

    public final void zza(Bundle bundle) {
        zza(bundle, zzm().currentTimeMillis());
    }

    public final void zza(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzb();
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzr().zzi().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        zzb(bundle2, j);
    }

    public final void zzb(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("app_id"));
        zza();
        zzb(new Bundle(bundle), zzm().currentTimeMillis());
    }

    private final void zzb(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzgs.zza(bundle, "app_id", String.class, null);
        zzgs.zza(bundle, "origin", String.class, null);
        zzgs.zza(bundle, "name", String.class, null);
        zzgs.zza(bundle, "value", Object.class, null);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgs.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle.getString("name"));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle.getString("name");
        Object obj = bundle.get("value");
        if (zzp().zzc(string) != 0) {
            zzr().zzf().zza("Invalid conditional user property name", zzo().zzc(string));
        } else if (zzp().zzb(string, obj) != 0) {
            zzr().zzf().zza("Invalid conditional user property value", zzo().zzc(string), obj);
        } else {
            Object zzc2 = zzp().zzc(string, obj);
            if (zzc2 == null) {
                zzr().zzf().zza("Unable to normalize conditional user property value", zzo().zzc(string), obj);
                return;
            }
            zzgs.zza(bundle, zzc2);
            long j2 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (TextUtils.isEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) || (j2 <= 15552000000L && j2 >= 1)) {
                long j3 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                if (j3 > 15552000000L || j3 < 1) {
                    zzr().zzf().zza("Invalid conditional user property time to live", zzo().zzc(string), Long.valueOf(j3));
                } else {
                    zzq().zza((Runnable) new zzhk(this, bundle));
                }
            } else {
                zzr().zzf().zza("Invalid conditional user property timeout", zzo().zzc(string), Long.valueOf(j2));
            }
        }
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        zzb();
        zzb((String) null, str, str2, bundle);
    }

    public final void zza(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zza();
        zzb(str, str2, str3, bundle);
    }

    private final void zzb(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzm().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        Bundle bundle2 = new Bundle();
        if (str != null) {
            bundle2.putString("app_id", str);
        }
        bundle2.putString("name", str2);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str3 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str3);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzq().zza((Runnable) new zzhn(this, bundle2));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzc(Bundle bundle) {
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("name"));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        if (!this.zzz.zzab()) {
            zzr().zzx().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzkh zzkh = new zzkh(bundle.getString("name"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), bundle.getString("origin"));
        try {
            zzao zza2 = zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), bundle.getString("origin"), 0, true, false);
            zzh().zza(new zzw(bundle.getString("app_id"), bundle.getString("origin"), zzkh, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), bundle.getString("origin"), 0, true, false), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zza2, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), 0, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzd(Bundle bundle) {
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("name"));
        if (!this.zzz.zzab()) {
            zzr().zzx().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzkh zzkh = new zzkh(bundle.getString("name"), 0, (Object) null, (String) null);
        try {
            zzh().zza(new zzw(bundle.getString("app_id"), bundle.getString("origin"), zzkh, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), (zzao) null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), (zzao) null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        zzb();
        return zzb((String) null, str, str2);
    }

    public final ArrayList<Bundle> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3);
    }

    @VisibleForTesting
    private final ArrayList<Bundle> zzb(String str, String str2, String str3) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzx.zza()) {
            zzr().zzf().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzz.zzq().zza(atomicReference, 5000, "get conditional user properties", new zzhp(this, atomicReference, str, str2, str3));
            List list = (List) atomicReference.get();
            if (list != null) {
                return zzkm.zzb((List<zzw>) list);
            }
            zzr().zzf().zza("Timed out waiting for get conditional user properties", str);
            return new ArrayList<>();
        }
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzb();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> zza(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3, z);
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzx.zza()) {
            zzr().zzf().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzz.zzq().zza(atomicReference, 5000, "get user properties", new zzho(this, atomicReference, str, str2, str3, z));
            List<zzkh> list = (List) atomicReference.get();
            if (list == null) {
                zzr().zzf().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzkh zzkh : list) {
                arrayMap.put(zzkh.zza, zzkh.zza());
            }
            return arrayMap;
        }
    }

    @Nullable
    public final String zzaj() {
        zzih zzac = this.zzz.zzv().zzac();
        if (zzac != null) {
            return zzac.zza;
        }
        return null;
    }

    @Nullable
    public final String zzak() {
        zzih zzac = this.zzz.zzv().zzac();
        if (zzac != null) {
            return zzac.zzb;
        }
        return null;
    }

    @Nullable
    public final String zzal() {
        if (this.zzz.zzo() != null) {
            return this.zzz.zzo();
        }
        try {
            return new StringResourceValueReader(zzn()).getString("google_app_id");
        } catch (IllegalStateException e) {
            this.zzz.zzr().zzf().zza("getGoogleAppId failed with exception", e);
            return null;
        }
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

    public final /* bridge */ /* synthetic */ zza zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzhb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzep zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzil zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzig zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzeo zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzjm zzk() {
        return super.zzk();
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
