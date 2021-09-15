package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzkk;
import com.google.android.gms.internal.measurement.zzlb;
import com.google.android.gms.internal.measurement.zzx;
import com.lzy.okgo.model.HttpHeaders;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
public class zzka implements zzgt {
    private static volatile zzka zza;
    private zzfq zzb;
    private zzez zzc;
    private zzad zzd;
    private zzfc zze;
    private zzjw zzf;
    private zzo zzg;
    private final zzki zzh;
    private zzif zzi;
    private final zzfw zzj;
    private boolean zzk;
    private boolean zzl;
    @VisibleForTesting
    private long zzm;
    private List<Runnable> zzn;
    private int zzo;
    private int zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private FileLock zzt;
    private FileChannel zzu;
    private List<Long> zzv;
    private List<Long> zzw;
    private long zzx;

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
    class zza implements zzaf {
        zzbs.zzg zza;
        List<Long> zzb;
        List<zzbs.zzc> zzc;
        private long zzd;

        private zza() {
        }

        public final void zza(zzbs.zzg zzg) {
            Preconditions.checkNotNull(zzg);
            this.zza = zzg;
        }

        public final boolean zza(long j, zzbs.zzc zzc2) {
            Preconditions.checkNotNull(zzc2);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (this.zzc.size() > 0 && zza(this.zzc.get(0)) != zza(zzc2)) {
                return false;
            }
            long zzbm = this.zzd + ((long) zzc2.zzbm());
            if (zzbm >= ((long) Math.max(0, zzaq.zzh.zza(null).intValue()))) {
                return false;
            }
            this.zzd = zzbm;
            this.zzc.add(zzc2);
            this.zzb.add(Long.valueOf(j));
            if (this.zzc.size() >= Math.max(1, zzaq.zzi.zza(null).intValue())) {
                return false;
            }
            return true;
        }

        private static long zza(zzbs.zzc zzc2) {
            return ((zzc2.zze() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzka zzka, zzkd zzkd) {
            this();
        }
    }

    public static zzka zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzka.class) {
                if (zza == null) {
                    zza = new zzka(new zzkg(context));
                }
            }
        }
        return zza;
    }

    private zzka(zzkg zzkg) {
        this(zzkg, (zzfw) null);
    }

    private zzka(zzkg zzkg, zzfw zzfw) {
        this.zzk = false;
        Preconditions.checkNotNull(zzkg);
        this.zzj = zzfw.zza(zzkg.zza, (zzx) null, (Long) null);
        this.zzx = -1;
        zzki zzki = new zzki(this);
        zzki.zzal();
        this.zzh = zzki;
        zzez zzez = new zzez(this);
        zzez.zzal();
        this.zzc = zzez;
        zzfq zzfq = new zzfq(this);
        zzfq.zzal();
        this.zzb = zzfq;
        this.zzj.zzq().zza((Runnable) new zzkd(this, zzkg));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzkg zzkg) {
        this.zzj.zzq().zzd();
        zzad zzad = new zzad(this);
        zzad.zzal();
        this.zzd = zzad;
        this.zzj.zzb().zza((zzaa) this.zzb);
        zzo zzo2 = new zzo(this);
        zzo2.zzal();
        this.zzg = zzo2;
        zzif zzif = new zzif(this);
        zzif.zzal();
        this.zzi = zzif;
        zzjw zzjw = new zzjw(this);
        zzjw.zzal();
        this.zzf = zzjw;
        this.zze = new zzfc(this);
        if (this.zzo != this.zzp) {
            this.zzj.zzr().zzf().zza("Not all upload components initialized", Integer.valueOf(this.zzo), Integer.valueOf(this.zzp));
        }
        this.zzk = true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza() {
        this.zzj.zzq().zzd();
        zze().zzv();
        if (this.zzj.zzc().zzc.zza() == 0) {
            this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
        }
        zzz();
    }

    public final zzx zzu() {
        return this.zzj.zzu();
    }

    public final zzy zzb() {
        return this.zzj.zzb();
    }

    public final zzes zzr() {
        return this.zzj.zzr();
    }

    public final zzft zzq() {
        return this.zzj.zzq();
    }

    public final zzfq zzc() {
        zzb((zzkb) this.zzb);
        return this.zzb;
    }

    public final zzez zzd() {
        zzb((zzkb) this.zzc);
        return this.zzc;
    }

    public final zzad zze() {
        zzb((zzkb) this.zzd);
        return this.zzd;
    }

    private final zzfc zzt() {
        if (this.zze != null) {
            return this.zze;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjw zzv() {
        zzb((zzkb) this.zzf);
        return this.zzf;
    }

    public final zzo zzf() {
        zzb((zzkb) this.zzg);
        return this.zzg;
    }

    public final zzif zzg() {
        zzb((zzkb) this.zzi);
        return this.zzi;
    }

    public final zzki zzh() {
        zzb((zzkb) this.zzh);
        return this.zzh;
    }

    public final zzeq zzi() {
        return this.zzj.zzj();
    }

    public final Context zzn() {
        return this.zzj.zzn();
    }

    public final Clock zzm() {
        return this.zzj.zzm();
    }

    public final zzkm zzj() {
        return this.zzj.zzi();
    }

    @WorkerThread
    private final void zzw() {
        this.zzj.zzq().zzd();
    }

    /* access modifiers changed from: package-private */
    public final void zzk() {
        if (!this.zzk) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zzb(zzkb zzkb) {
        if (zzkb == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzkb.zzaj()) {
            String valueOf = String.valueOf(zzkb.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    private final long zzx() {
        long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
        zzfe zzc2 = this.zzj.zzc();
        zzc2.zzaa();
        zzc2.zzd();
        long zza2 = zzc2.zzg.zza();
        if (zza2 == 0) {
            zza2 = 1 + ((long) zzc2.zzp().zzh().nextInt(86400000));
            zzc2.zzg.zza(zza2);
        }
        return ((((zza2 + currentTimeMillis) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(zzao zzao, String str) {
        String str2;
        zzf zzb2 = zze().zzb(str);
        if (zzb2 == null || TextUtils.isEmpty(zzb2.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping event", str);
            return;
        }
        Boolean zzb3 = zzb(zzb2);
        if (zzb3 == null) {
            if (!"_ui".equals(zzao.zza)) {
                this.zzj.zzr().zzi().zza("Could not find package. appId", zzes.zza(str));
            }
        } else if (!zzb3.booleanValue()) {
            this.zzj.zzr().zzf().zza("App version does not match; dropping event. appId", zzes.zza(str));
            return;
        }
        String zze2 = zzb2.zze();
        String zzl2 = zzb2.zzl();
        long zzm2 = zzb2.zzm();
        String zzn2 = zzb2.zzn();
        long zzo2 = zzb2.zzo();
        long zzp2 = zzb2.zzp();
        boolean zzr2 = zzb2.zzr();
        String zzi2 = zzb2.zzi();
        long zzae = zzb2.zzae();
        boolean zzaf = zzb2.zzaf();
        boolean zzag = zzb2.zzag();
        String zzf2 = zzb2.zzf();
        Boolean zzah = zzb2.zzah();
        long zzq2 = zzb2.zzq();
        List<String> zzai = zzb2.zzai();
        if (zzlb.zzb()) {
            if (this.zzj.zzb().zze(zzb2.zzc(), zzaq.zzbo)) {
                str2 = zzb2.zzg();
                zza(zzao, new zzn(str, zze2, zzl2, zzm2, zzn2, zzo2, zzp2, (String) null, zzr2, false, zzi2, zzae, 0, 0, zzaf, zzag, false, zzf2, zzah, zzq2, zzai, str2));
            }
        }
        str2 = null;
        zza(zzao, new zzn(str, zze2, zzl2, zzm2, zzn2, zzo2, zzp2, (String) null, zzr2, false, zzi2, zzae, 0, 0, zzaf, zzag, false, zzf2, zzah, zzq2, zzai, str2));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(zzao zzao, zzn zzn2) {
        List<zzw> zza2;
        List<zzw> zza3;
        List<zzw> zza4;
        Preconditions.checkNotNull(zzn2);
        Preconditions.checkNotEmpty(zzn2.zza);
        zzw();
        zzk();
        String str = zzn2.zza;
        long j = zzao.zzd;
        zzh();
        if (zzki.zza(zzao, zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
                return;
            }
            if (this.zzj.zzb().zze(str, zzaq.zzbc) && zzn2.zzu != null) {
                if (zzn2.zzu.contains(zzao.zza)) {
                    Bundle zzb2 = zzao.zzb.zzb();
                    zzb2.putLong("ga_safelisted", 1);
                    zzao = new zzao(zzao.zza, new zzan(zzb2), zzao.zzc, zzao.zzd);
                } else {
                    this.zzj.zzr().zzw().zza("Dropping non-safelisted event. appId, event name, origin", str, zzao.zza, zzao.zzc);
                    return;
                }
            }
            zze().zzf();
            try {
                zzad zze2 = zze();
                Preconditions.checkNotEmpty(str);
                zze2.zzd();
                zze2.zzak();
                if (j < 0) {
                    zze2.zzr().zzi().zza("Invalid time querying timed out conditional properties", zzes.zza(str), Long.valueOf(j));
                    zza2 = Collections.emptyList();
                } else {
                    zza2 = zze2.zza("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzw next : zza2) {
                    if (next != null) {
                        this.zzj.zzr().zzx().zza("User property timed out", next.zza, this.zzj.zzj().zzc(next.zzc.zza), next.zzc.zza());
                        if (next.zzg != null) {
                            zzb(new zzao(next.zzg, j), zzn2);
                        }
                        zze().zze(str, next.zzc.zza);
                    }
                }
                zzad zze3 = zze();
                Preconditions.checkNotEmpty(str);
                zze3.zzd();
                zze3.zzak();
                if (j < 0) {
                    zze3.zzr().zzi().zza("Invalid time querying expired conditional properties", zzes.zza(str), Long.valueOf(j));
                    zza3 = Collections.emptyList();
                } else {
                    zza3 = zze3.zza("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(zza3.size());
                for (zzw next2 : zza3) {
                    if (next2 != null) {
                        this.zzj.zzr().zzx().zza("User property expired", next2.zza, this.zzj.zzj().zzc(next2.zzc.zza), next2.zzc.zza());
                        zze().zzb(str, next2.zzc.zza);
                        if (next2.zzk != null) {
                            arrayList.add(next2.zzk);
                        }
                        zze().zze(str, next2.zzc.zza);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzb(new zzao((zzao) obj, j), zzn2);
                }
                zzad zze4 = zze();
                String str2 = zzao.zza;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zze4.zzd();
                zze4.zzak();
                if (j < 0) {
                    zze4.zzr().zzi().zza("Invalid time querying triggered conditional properties", zzes.zza(str), zze4.zzo().zza(str2), Long.valueOf(j));
                    zza4 = Collections.emptyList();
                } else {
                    zza4 = zze4.zza("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(zza4.size());
                for (zzw next3 : zza4) {
                    if (next3 != null) {
                        zzkh zzkh = next3.zzc;
                        zzkj zzkj = new zzkj(next3.zza, next3.zzb, zzkh.zza, j, zzkh.zza());
                        if (zze().zza(zzkj)) {
                            this.zzj.zzr().zzx().zza("User property triggered", next3.zza, this.zzj.zzj().zzc(zzkj.zzc), zzkj.zze);
                        } else {
                            this.zzj.zzr().zzf().zza("Too many active user properties, ignoring", zzes.zza(next3.zza), this.zzj.zzj().zzc(zzkj.zzc), zzkj.zze);
                        }
                        if (next3.zzi != null) {
                            arrayList3.add(next3.zzi);
                        }
                        next3.zzc = new zzkh(zzkj);
                        next3.zze = true;
                        zze().zza(next3);
                    }
                }
                zzb(zzao, zzn2);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj2 = arrayList4.get(i2);
                    i2++;
                    zzb(new zzao((zzao) obj2, j), zzn2);
                }
                zze().mo24237b_();
            } finally {
                zze().zzh();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:97:0x0386 A[Catch:{ SQLiteException -> 0x03df, all -> 0x03d6 }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(com.google.android.gms.measurement.internal.zzao r33, com.google.android.gms.measurement.internal.zzn r34) {
        /*
            r32 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r34)
            r0 = r34
            java.lang.String r2 = r0.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)
            long r30 = java.lang.System.nanoTime()
            r32.zzw()
            r32.zzk()
            r0 = r34
            java.lang.String r3 = r0.zza
            r32.zzh()
            boolean r2 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.measurement.internal.zzao) r33, (com.google.android.gms.measurement.internal.zzn) r34)
            if (r2 != 0) goto L_0x0022
        L_0x0021:
            return
        L_0x0022:
            r0 = r34
            boolean r2 = r0.zzh
            if (r2 != 0) goto L_0x0030
            r0 = r32
            r1 = r34
            r0.zzc(r1)
            goto L_0x0021
        L_0x0030:
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()
            r0 = r33
            java.lang.String r4 = r0.zza
            boolean r2 = r2.zzb(r3, r4)
            if (r2 == 0) goto L_0x00f1
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()
            java.lang.String r4 = "Dropping blacklisted event. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r6 = r0.zzj
            com.google.android.gms.measurement.internal.zzeq r6 = r6.zzj()
            r0 = r33
            java.lang.String r7 = r0.zza
            java.lang.String r6 = r6.zza((java.lang.String) r7)
            r2.zza(r4, r5, r6)
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()
            boolean r2 = r2.zzg(r3)
            if (r2 != 0) goto L_0x0077
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()
            boolean r2 = r2.zzh(r3)
            if (r2 == 0) goto L_0x00ee
        L_0x0077:
            r2 = 1
            r8 = r2
        L_0x0079:
            if (r8 != 0) goto L_0x009b
            java.lang.String r2 = "_err"
            r0 = r33
            java.lang.String r4 = r0.zza
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x009b
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()
            r4 = 11
            java.lang.String r5 = "_ev"
            r0 = r33
            java.lang.String r6 = r0.zza
            r7 = 0
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)
        L_0x009b:
            if (r8 == 0) goto L_0x0021
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            com.google.android.gms.measurement.internal.zzf r3 = r2.zzb(r3)
            if (r3 == 0) goto L_0x0021
            long r4 = r3.zzu()
            long r6 = r3.zzt()
            long r4 = java.lang.Math.max(r4, r6)
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.common.util.Clock r2 = r2.zzm()
            long r6 = r2.currentTimeMillis()
            long r4 = r6 - r4
            long r4 = java.lang.Math.abs(r4)
            com.google.android.gms.measurement.internal.zzel<java.lang.Long> r2 = com.google.android.gms.measurement.internal.zzaq.zzy
            r6 = 0
            java.lang.Object r2 = r2.zza(r6)
            java.lang.Long r2 = (java.lang.Long) r2
            long r6 = r2.longValue()
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0021
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzw()
            java.lang.String r4 = "Fetching config for blacklisted app"
            r2.zza(r4)
            r0 = r32
            r0.zza((com.google.android.gms.measurement.internal.zzf) r3)
            goto L_0x0021
        L_0x00ee:
            r2 = 0
            r8 = r2
            goto L_0x0079
        L_0x00f1:
            boolean r2 = com.google.android.gms.internal.measurement.zzjg.zzb()
            if (r2 == 0) goto L_0x01c2
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzck
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r4)
            if (r2 == 0) goto L_0x01c2
            com.google.android.gms.measurement.internal.zzew r4 = new com.google.android.gms.measurement.internal.zzew
            r0 = r33
            java.lang.String r5 = r0.zza
            r0 = r33
            java.lang.String r6 = r0.zzc
            r0 = r33
            com.google.android.gms.measurement.internal.zzan r2 = r0.zzb
            android.os.Bundle r7 = r2.zzb()
            r0 = r33
            long r8 = r0.zzd
            r4.<init>(r5, r6, r7, r8)
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzkm r6 = r2.zzi()
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()
            com.google.android.gms.measurement.internal.zzel<java.lang.Integer> r5 = com.google.android.gms.measurement.internal.zzaq.zzah
            r7 = 25
            r8 = 100
            int r7 = r2.zza(r3, r5, r7, r8)
            r2 = 0
            java.util.TreeSet r5 = new java.util.TreeSet
            android.os.Bundle r8 = r4.zzd
            java.util.Set r8 = r8.keySet()
            r5.<init>(r8)
            java.util.Iterator r8 = r5.iterator()
            r5 = r2
        L_0x014b:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x01a9
            java.lang.Object r2 = r8.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r9 = com.google.android.gms.measurement.internal.zzkm.zza((java.lang.String) r2)
            if (r9 == 0) goto L_0x01a6
            int r5 = r5 + 1
            if (r5 <= r7) goto L_0x01a6
            r9 = 48
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r9)
            java.lang.String r9 = "Event can't contain more than "
            java.lang.StringBuilder r9 = r10.append(r9)
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r10 = " params"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            com.google.android.gms.measurement.internal.zzes r10 = r6.zzr()
            com.google.android.gms.measurement.internal.zzeu r10 = r10.zzh()
            com.google.android.gms.measurement.internal.zzeq r11 = r6.zzo()
            java.lang.String r12 = r4.zza
            java.lang.String r11 = r11.zza((java.lang.String) r12)
            com.google.android.gms.measurement.internal.zzeq r12 = r6.zzo()
            android.os.Bundle r13 = r4.zzd
            java.lang.String r12 = r12.zza((android.os.Bundle) r13)
            r10.zza(r9, r11, r12)
            android.os.Bundle r9 = r4.zzd
            r10 = 5
            com.google.android.gms.measurement.internal.zzkm.zzb((android.os.Bundle) r9, (int) r10)
            android.os.Bundle r9 = r4.zzd
            r9.remove(r2)
        L_0x01a6:
            r2 = r5
            r5 = r2
            goto L_0x014b
        L_0x01a9:
            com.google.android.gms.measurement.internal.zzao r33 = new com.google.android.gms.measurement.internal.zzao
            java.lang.String r5 = r4.zza
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan
            android.os.Bundle r2 = new android.os.Bundle
            android.os.Bundle r7 = r4.zzd
            r2.<init>(r7)
            r6.<init>(r2)
            java.lang.String r7 = r4.zzb
            long r8 = r4.zzc
            r4 = r33
            r4.<init>(r5, r6, r7, r8)
        L_0x01c2:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()
            r4 = 2
            boolean r2 = r2.zza((int) r4)
            if (r2 == 0) goto L_0x01f0
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzx()
            java.lang.String r4 = "Logging event"
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r5 = r0.zzj
            com.google.android.gms.measurement.internal.zzeq r5 = r5.zzj()
            r0 = r33
            java.lang.String r5 = r5.zza((com.google.android.gms.measurement.internal.zzao) r0)
            r2.zza(r4, r5)
        L_0x01f0:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzf()
            r0 = r32
            r1 = r34
            r0.zzc(r1)     // Catch:{ all -> 0x03d6 }
            boolean r2 = com.google.android.gms.internal.measurement.zzjm.zzb()     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0396
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzcj     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r4)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0396
            r2 = 1
        L_0x0215:
            java.lang.String r4 = "ecommerce_purchase"
            r0 = r33
            java.lang.String r5 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x03d6 }
            if (r4 != 0) goto L_0x023b
            if (r2 == 0) goto L_0x0399
            java.lang.String r2 = "purchase"
            r0 = r33
            java.lang.String r4 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x023b
            java.lang.String r2 = "refund"
            r0 = r33
            java.lang.String r4 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0399
        L_0x023b:
            r2 = 1
            r4 = r2
        L_0x023d:
            java.lang.String r2 = "_iap"
            r0 = r33
            java.lang.String r5 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x024b
            if (r4 == 0) goto L_0x039d
        L_0x024b:
            r2 = 1
        L_0x024c:
            if (r2 == 0) goto L_0x0417
            r0 = r33
            com.google.android.gms.measurement.internal.zzan r2 = r0.zzb     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "currency"
            java.lang.String r2 = r2.zzd(r5)     // Catch:{ all -> 0x03d6 }
            if (r4 == 0) goto L_0x03be
            r0 = r33
            com.google.android.gms.measurement.internal.zzan r4 = r0.zzb     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "value"
            java.lang.Double r4 = r4.zzc(r5)     // Catch:{ all -> 0x03d6 }
            double r4 = r4.doubleValue()     // Catch:{ all -> 0x03d6 }
            r6 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r4 = r4 * r6
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 != 0) goto L_0x0289
            r0 = r33
            com.google.android.gms.measurement.internal.zzan r4 = r0.zzb     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "value"
            java.lang.Long r4 = r4.zzb(r5)     // Catch:{ all -> 0x03d6 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x03d6 }
            double r4 = (double) r4     // Catch:{ all -> 0x03d6 }
            r6 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r4 = r4 * r6
        L_0x0289:
            r6 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 > 0) goto L_0x03a0
            r6 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x03a0
            long r4 = java.lang.Math.round(r4)     // Catch:{ all -> 0x03d6 }
            boolean r6 = com.google.android.gms.internal.measurement.zzjm.zzb()     // Catch:{ all -> 0x03d6 }
            if (r6 == 0) goto L_0x03bb
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r6 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zzb()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzaq.zzcj     // Catch:{ all -> 0x03d6 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r7)     // Catch:{ all -> 0x03d6 }
            if (r6 == 0) goto L_0x03bb
            java.lang.String r6 = "refund"
            r0 = r33
            java.lang.String r7 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x03d6 }
            if (r6 == 0) goto L_0x03bb
            long r4 = -r4
            r8 = r4
        L_0x02bd:
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r4 != 0) goto L_0x0383
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = r2.toUpperCase(r4)     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = "[A-Z]{3}"
            boolean r4 = r2.matches(r4)     // Catch:{ all -> 0x03d6 }
            if (r4 == 0) goto L_0x0383
            java.lang.String r4 = "_ltv_"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x03d6 }
            int r5 = r2.length()     // Catch:{ all -> 0x03d6 }
            if (r5 == 0) goto L_0x03cf
            java.lang.String r5 = r4.concat(r2)     // Catch:{ all -> 0x03d6 }
        L_0x02e5:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkj r2 = r2.zzc(r3, r5)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x02f5
            java.lang.Object r4 = r2.zze     // Catch:{ all -> 0x03d6 }
            boolean r4 = r4 instanceof java.lang.Long     // Catch:{ all -> 0x03d6 }
            if (r4 != 0) goto L_0x03f3
        L_0x02f5:
            com.google.android.gms.measurement.internal.zzad r4 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzaq.zzad     // Catch:{ all -> 0x03d6 }
            int r2 = r2.zzb(r3, r6)     // Catch:{ all -> 0x03d6 }
            int r2 = r2 + -1
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x03d6 }
            r4.zzd()     // Catch:{ all -> 0x03d6 }
            r4.zzak()     // Catch:{ all -> 0x03d6 }
            android.database.sqlite.SQLiteDatabase r6 = r4.mo24238c_()     // Catch:{ SQLiteException -> 0x03df }
            java.lang.String r7 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            r10 = 3
            java.lang.String[] r10 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x03df }
            r11 = 0
            r10[r11] = r3     // Catch:{ SQLiteException -> 0x03df }
            r11 = 1
            r10[r11] = r3     // Catch:{ SQLiteException -> 0x03df }
            r11 = 2
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ SQLiteException -> 0x03df }
            r10[r11] = r2     // Catch:{ SQLiteException -> 0x03df }
            r6.execSQL(r7, r10)     // Catch:{ SQLiteException -> 0x03df }
        L_0x032b:
            com.google.android.gms.measurement.internal.zzkj r2 = new com.google.android.gms.measurement.internal.zzkj     // Catch:{ all -> 0x03d6 }
            r0 = r33
            java.lang.String r4 = r0.zzc     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r6 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.common.util.Clock r6 = r6.zzm()     // Catch:{ all -> 0x03d6 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x03d6 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x03d6 }
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x03d6 }
        L_0x0344:
            com.google.android.gms.measurement.internal.zzad r4 = r32.zze()     // Catch:{ all -> 0x03d6 }
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzkj) r2)     // Catch:{ all -> 0x03d6 }
            if (r4 != 0) goto L_0x0383
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r4 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r4 = r4.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r7 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeq r7 = r7.zzj()     // Catch:{ all -> 0x03d6 }
            java.lang.String r8 = r2.zzc     // Catch:{ all -> 0x03d6 }
            java.lang.String r7 = r7.zzc(r8)     // Catch:{ all -> 0x03d6 }
            java.lang.Object r2 = r2.zze     // Catch:{ all -> 0x03d6 }
            r4.zza(r5, r6, r7, r2)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            r4 = 9
            r5 = 0
            r6 = 0
            r7 = 0
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)     // Catch:{ all -> 0x03d6 }
        L_0x0383:
            r2 = 1
        L_0x0384:
            if (r2 != 0) goto L_0x0417
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r2.mo24237b_()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            goto L_0x0021
        L_0x0396:
            r2 = 0
            goto L_0x0215
        L_0x0399:
            r2 = 0
            r4 = r2
            goto L_0x023d
        L_0x039d:
            r2 = 0
            goto L_0x024c
        L_0x03a0:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r6 = "Data lost. Currency value is too big. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ all -> 0x03d6 }
            r2.zza(r6, r7, r4)     // Catch:{ all -> 0x03d6 }
            r2 = 0
            goto L_0x0384
        L_0x03bb:
            r8 = r4
            goto L_0x02bd
        L_0x03be:
            r0 = r33
            com.google.android.gms.measurement.internal.zzan r4 = r0.zzb     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "value"
            java.lang.Long r4 = r4.zzb(r5)     // Catch:{ all -> 0x03d6 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x03d6 }
            r8 = r4
            goto L_0x02bd
        L_0x03cf:
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x03d6 }
            r5.<init>(r4)     // Catch:{ all -> 0x03d6 }
            goto L_0x02e5
        L_0x03d6:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()
            r3.zzh()
            throw r2
        L_0x03df:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzes r4 = r4.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ all -> 0x03d6 }
            java.lang.String r6 = "Error pruning currencies. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r4.zza(r6, r7, r2)     // Catch:{ all -> 0x03d6 }
            goto L_0x032b
        L_0x03f3:
            java.lang.Object r2 = r2.zze     // Catch:{ all -> 0x03d6 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x03d6 }
            long r10 = r2.longValue()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkj r2 = new com.google.android.gms.measurement.internal.zzkj     // Catch:{ all -> 0x03d6 }
            r0 = r33
            java.lang.String r4 = r0.zzc     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r6 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.common.util.Clock r6 = r6.zzm()     // Catch:{ all -> 0x03d6 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x03d6 }
            long r8 = r8 + r10
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x03d6 }
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x03d6 }
            goto L_0x0344
        L_0x0417:
            r0 = r33
            java.lang.String r2 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r11 = com.google.android.gms.measurement.internal.zzkm.zza((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = "_err"
            r0 = r33
            java.lang.String r4 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r13 = r2.equals(r4)     // Catch:{ all -> 0x03d6 }
            r8 = 1
            boolean r2 = com.google.android.gms.internal.measurement.zzjm.zzb()     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0458
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r4 = r0.zza     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzcf     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zze(r4, r5)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0458
            r4 = 1
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            r2.zzi()     // Catch:{ all -> 0x03d6 }
            r0 = r33
            com.google.android.gms.measurement.internal.zzan r2 = r0.zzb     // Catch:{ all -> 0x03d6 }
            long r6 = com.google.android.gms.measurement.internal.zzkm.zza((com.google.android.gms.measurement.internal.zzan) r2)     // Catch:{ all -> 0x03d6 }
            long r8 = r4 + r6
        L_0x0458:
            com.google.android.gms.measurement.internal.zzad r4 = r32.zze()     // Catch:{ all -> 0x03d6 }
            long r5 = r32.zzx()     // Catch:{ all -> 0x03d6 }
            r10 = 1
            r12 = 0
            r14 = 0
            r7 = r3
            com.google.android.gms.measurement.internal.zzac r4 = r4.zza(r5, r7, r8, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x03d6 }
            long r6 = r4.zzb     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Integer> r2 = com.google.android.gms.measurement.internal.zzaq.zzj     // Catch:{ all -> 0x03d6 }
            r5 = 0
            java.lang.Object r2 = r2.zza(r5)     // Catch:{ all -> 0x03d6 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ all -> 0x03d6 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x03d6 }
            long r8 = (long) r2     // Catch:{ all -> 0x03d6 }
            long r6 = r6 - r8
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x04b3
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 % r8
            r8 = 1
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x04a3
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            long r6 = r4.zzb     // Catch:{ all -> 0x03d6 }
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x03d6 }
            r2.zza(r5, r3, r4)     // Catch:{ all -> 0x03d6 }
        L_0x04a3:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r2.mo24237b_()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            goto L_0x0021
        L_0x04b3:
            if (r11 == 0) goto L_0x0514
            long r6 = r4.zza     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Integer> r2 = com.google.android.gms.measurement.internal.zzaq.zzl     // Catch:{ all -> 0x03d6 }
            r5 = 0
            java.lang.Object r2 = r2.zza(r5)     // Catch:{ all -> 0x03d6 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ all -> 0x03d6 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x03d6 }
            long r8 = (long) r2     // Catch:{ all -> 0x03d6 }
            long r6 = r6 - r8
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x0514
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 % r8
            r8 = 1
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x04f0
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            long r8 = r4.zza     // Catch:{ all -> 0x03d6 }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x03d6 }
            r2.zza(r5, r6, r4)     // Catch:{ all -> 0x03d6 }
        L_0x04f0:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            r4 = 16
            java.lang.String r5 = "_ev"
            r0 = r33
            java.lang.String r6 = r0.zza     // Catch:{ all -> 0x03d6 }
            r7 = 0
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r2.mo24237b_()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            goto L_0x0021
        L_0x0514:
            if (r13 == 0) goto L_0x056f
            long r6 = r4.zzd     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r5 = r0.zza     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Integer> r8 = com.google.android.gms.measurement.internal.zzaq.zzk     // Catch:{ all -> 0x03d6 }
            int r2 = r2.zzb(r5, r8)     // Catch:{ all -> 0x03d6 }
            r5 = 1000000(0xf4240, float:1.401298E-39)
            int r2 = java.lang.Math.min(r5, r2)     // Catch:{ all -> 0x03d6 }
            r5 = 0
            int r2 = java.lang.Math.max(r5, r2)     // Catch:{ all -> 0x03d6 }
            long r8 = (long) r2     // Catch:{ all -> 0x03d6 }
            long r6 = r6 - r8
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x056f
            r8 = 1
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x055f
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "Too many error events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            long r6 = r4.zzd     // Catch:{ all -> 0x03d6 }
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x03d6 }
            r2.zza(r5, r3, r4)     // Catch:{ all -> 0x03d6 }
        L_0x055f:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r2.mo24237b_()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            goto L_0x0021
        L_0x056f:
            r0 = r33
            com.google.android.gms.measurement.internal.zzan r2 = r0.zzb     // Catch:{ all -> 0x03d6 }
            android.os.Bundle r22 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = "_o"
            r0 = r33
            java.lang.String r5 = r0.zzc     // Catch:{ all -> 0x03d6 }
            r0 = r22
            r2.zza((android.os.Bundle) r0, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zzf(r3)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x05c2
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = "_dbg"
            r6 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x03d6 }
            r0 = r22
            r2.zza((android.os.Bundle) r0, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = "_r"
            r6 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x03d6 }
            r0 = r22
            r2.zza((android.os.Bundle) r0, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x03d6 }
        L_0x05c2:
            java.lang.String r2 = "_s"
            r0 = r33
            java.lang.String r4 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x05f5
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r4 = r0.zza     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "_sno"
            com.google.android.gms.measurement.internal.zzkj r2 = r2.zzc(r4, r5)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x05f5
            java.lang.Object r4 = r2.zze     // Catch:{ all -> 0x03d6 }
            boolean r4 = r4 instanceof java.lang.Long     // Catch:{ all -> 0x03d6 }
            if (r4 == 0) goto L_0x05f5
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r4 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r4 = r4.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "_sno"
            java.lang.Object r2 = r2.zze     // Catch:{ all -> 0x03d6 }
            r0 = r22
            r4.zza((android.os.Bundle) r0, (java.lang.String) r5, (java.lang.Object) r2)     // Catch:{ all -> 0x03d6 }
        L_0x05f5:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            long r4 = r2.zzc(r3)     // Catch:{ all -> 0x03d6 }
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x061c
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r6 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x03d6 }
            r2.zza(r6, r7, r4)     // Catch:{ all -> 0x03d6 }
        L_0x061c:
            com.google.android.gms.measurement.internal.zzal r13 = new com.google.android.gms.measurement.internal.zzal     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r14 = r0.zzj     // Catch:{ all -> 0x03d6 }
            r0 = r33
            java.lang.String r15 = r0.zzc     // Catch:{ all -> 0x03d6 }
            r0 = r33
            java.lang.String r0 = r0.zza     // Catch:{ all -> 0x03d6 }
            r17 = r0
            r0 = r33
            long r0 = r0.zzd     // Catch:{ all -> 0x03d6 }
            r18 = r0
            r20 = 0
            r16 = r3
            r13.<init>((com.google.android.gms.measurement.internal.zzfw) r14, (java.lang.String) r15, (java.lang.String) r16, (java.lang.String) r17, (long) r18, (long) r20, (android.os.Bundle) r22)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = r13.zzb     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzak r2 = r2.zza((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x09a8
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            long r4 = r2.zzh(r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            int r2 = r2.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            long r6 = (long) r2     // Catch:{ all -> 0x03d6 }
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L_0x06ac
            if (r11 == 0) goto L_0x06ac
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r6 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeq r6 = r6.zzj()     // Catch:{ all -> 0x03d6 }
            java.lang.String r7 = r13.zzb     // Catch:{ all -> 0x03d6 }
            java.lang.String r6 = r6.zza((java.lang.String) r7)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r7 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r7 = r7.zzb()     // Catch:{ all -> 0x03d6 }
            int r7 = r7.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x03d6 }
            r2.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            r4 = 8
            r5 = 0
            r6 = 0
            r7 = 0
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            goto L_0x0021
        L_0x06ac:
            com.google.android.gms.measurement.internal.zzak r15 = new com.google.android.gms.measurement.internal.zzak     // Catch:{ all -> 0x03d6 }
            java.lang.String r0 = r13.zzb     // Catch:{ all -> 0x03d6 }
            r17 = r0
            r18 = 0
            r20 = 0
            long r0 = r13.zzc     // Catch:{ all -> 0x03d6 }
            r22 = r0
            r24 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r16 = r3
            r15.<init>(r16, r17, r18, r20, r22, r24, r26, r27, r28, r29)     // Catch:{ all -> 0x03d6 }
        L_0x06c9:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r2.zza((com.google.android.gms.measurement.internal.zzak) r15)     // Catch:{ all -> 0x03d6 }
            r32.zzw()     // Catch:{ all -> 0x03d6 }
            r32.zzk()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r34)     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = r13.zza     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = r13.zza     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = com.google.android.gms.internal.measurement.zzbs.zzg.zzbf()     // Catch:{ all -> 0x03d6 }
            r3 = 1
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zza((int) r3)     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = "android"
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = r2.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r2 = r0.zza     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x070e
            r0 = r34
            java.lang.String r2 = r0.zza     // Catch:{ all -> 0x03d6 }
            r4.zzf((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
        L_0x070e:
            r0 = r34
            java.lang.String r2 = r0.zzd     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x071f
            r0 = r34
            java.lang.String r2 = r0.zzd     // Catch:{ all -> 0x03d6 }
            r4.zze((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
        L_0x071f:
            r0 = r34
            java.lang.String r2 = r0.zzc     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x0730
            r0 = r34
            java.lang.String r2 = r0.zzc     // Catch:{ all -> 0x03d6 }
            r4.zzg((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
        L_0x0730:
            r0 = r34
            long r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x0743
            r0 = r34
            long r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            int r2 = (int) r2     // Catch:{ all -> 0x03d6 }
            r4.zzh((int) r2)     // Catch:{ all -> 0x03d6 }
        L_0x0743:
            r0 = r34
            long r2 = r0.zze     // Catch:{ all -> 0x03d6 }
            r4.zzf((long) r2)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r2 = r0.zzb     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x075b
            r0 = r34
            java.lang.String r2 = r0.zzb     // Catch:{ all -> 0x03d6 }
            r4.zzk((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
        L_0x075b:
            boolean r2 = com.google.android.gms.internal.measurement.zzlb.zzb()     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x09ba
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zza     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzbo     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zze(r3, r5)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x09ba
            java.lang.String r2 = r4.zzl()     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0790
            r0 = r34
            java.lang.String r2 = r0.zzv     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x0790
            r0 = r34
            java.lang.String r2 = r0.zzv     // Catch:{ all -> 0x03d6 }
            r4.zzp(r2)     // Catch:{ all -> 0x03d6 }
        L_0x0790:
            java.lang.String r2 = r4.zzl()     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x07b5
            java.lang.String r2 = r4.zzo()     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x07b5
            r0 = r34
            java.lang.String r2 = r0.zzr     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x07b5
            r0 = r34
            java.lang.String r2 = r0.zzr     // Catch:{ all -> 0x03d6 }
            r4.zzo(r2)     // Catch:{ all -> 0x03d6 }
        L_0x07b5:
            r0 = r34
            long r2 = r0.zzf     // Catch:{ all -> 0x03d6 }
            r6 = 0
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x07c6
            r0 = r34
            long r2 = r0.zzf     // Catch:{ all -> 0x03d6 }
            r4.zzh((long) r2)     // Catch:{ all -> 0x03d6 }
        L_0x07c6:
            r0 = r34
            long r2 = r0.zzt     // Catch:{ all -> 0x03d6 }
            r4.zzk((long) r2)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zza     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzaw     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zze(r3, r5)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x07ee
            com.google.android.gms.measurement.internal.zzki r2 = r32.zzh()     // Catch:{ all -> 0x03d6 }
            java.util.List r2 = r2.zzf()     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x07ee
            r4.zzd((java.lang.Iterable<? extends java.lang.Integer>) r2)     // Catch:{ all -> 0x03d6 }
        L_0x07ee:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzfe r2 = r2.zzc()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zza     // Catch:{ all -> 0x03d6 }
            android.util.Pair r3 = r2.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            if (r3 == 0) goto L_0x09d7
            java.lang.Object r2 = r3.first     // Catch:{ all -> 0x03d6 }
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x09d7
            r0 = r34
            boolean r2 = r0.zzo     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0826
            java.lang.Object r2 = r3.first     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x03d6 }
            r4.zzh((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
            java.lang.Object r2 = r3.second     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0826
            java.lang.Object r2 = r3.second     // Catch:{ all -> 0x03d6 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x03d6 }
            r4.zza((boolean) r2)     // Catch:{ all -> 0x03d6 }
        L_0x0826:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzai r2 = r2.zzx()     // Catch:{ all -> 0x03d6 }
            r2.zzaa()     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = android.os.Build.MODEL     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r4.zzc((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzai r3 = r3.zzx()     // Catch:{ all -> 0x03d6 }
            r3.zzaa()     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zzb((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzai r3 = r3.zzx()     // Catch:{ all -> 0x03d6 }
            long r6 = r3.zzf()     // Catch:{ all -> 0x03d6 }
            int r3 = (int) r6     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zzf((int) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzai r3 = r3.zzx()     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = r3.zzg()     // Catch:{ all -> 0x03d6 }
            r2.zzd((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzcm     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r3)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x087f
            r0 = r34
            long r2 = r0.zzl     // Catch:{ all -> 0x03d6 }
            r4.zzj((long) r2)     // Catch:{ all -> 0x03d6 }
        L_0x087f:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zzab()     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0897
            r4.zzj()     // Catch:{ all -> 0x03d6 }
            r2 = 0
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x0897
            r2 = 0
            r4.zzn(r2)     // Catch:{ all -> 0x03d6 }
        L_0x0897:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zza     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzf r2 = r2.zzb(r3)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x0940
            com.google.android.gms.measurement.internal.zzf r2 = new com.google.android.gms.measurement.internal.zzf     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r5 = r0.zza     // Catch:{ all -> 0x03d6 }
            r2.<init>(r3, r5)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkm r3 = r3.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = r3.zzk()     // Catch:{ all -> 0x03d6 }
            r2.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zzk     // Catch:{ all -> 0x03d6 }
            r2.zzf((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zzb     // Catch:{ all -> 0x03d6 }
            r2.zzb((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzfe r3 = r3.zzc()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r5 = r0.zza     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = r3.zzb((java.lang.String) r5)     // Catch:{ all -> 0x03d6 }
            r2.zze((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r6 = 0
            r2.zzg((long) r6)     // Catch:{ all -> 0x03d6 }
            r6 = 0
            r2.zza((long) r6)     // Catch:{ all -> 0x03d6 }
            r6 = 0
            r2.zzb((long) r6)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zzc     // Catch:{ all -> 0x03d6 }
            r2.zzg((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            long r6 = r0.zzj     // Catch:{ all -> 0x03d6 }
            r2.zzc((long) r6)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zzd     // Catch:{ all -> 0x03d6 }
            r2.zzh((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            long r6 = r0.zze     // Catch:{ all -> 0x03d6 }
            r2.zzd((long) r6)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            long r6 = r0.zzf     // Catch:{ all -> 0x03d6 }
            r2.zze((long) r6)     // Catch:{ all -> 0x03d6 }
            r0 = r34
            boolean r3 = r0.zzh     // Catch:{ all -> 0x03d6 }
            r2.zza((boolean) r3)     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zzb()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzcm     // Catch:{ all -> 0x03d6 }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r5)     // Catch:{ all -> 0x03d6 }
            if (r3 != 0) goto L_0x0932
            r0 = r34
            long r6 = r0.zzl     // Catch:{ all -> 0x03d6 }
            r2.zzp(r6)     // Catch:{ all -> 0x03d6 }
        L_0x0932:
            r0 = r34
            long r6 = r0.zzt     // Catch:{ all -> 0x03d6 }
            r2.zzf((long) r6)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r3.zza((com.google.android.gms.measurement.internal.zzf) r2)     // Catch:{ all -> 0x03d6 }
        L_0x0940:
            java.lang.String r3 = r2.zzd()     // Catch:{ all -> 0x03d6 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x03d6 }
            if (r3 != 0) goto L_0x0951
            java.lang.String r3 = r2.zzd()     // Catch:{ all -> 0x03d6 }
            r4.zzi((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
        L_0x0951:
            java.lang.String r3 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x03d6 }
            if (r3 != 0) goto L_0x0962
            java.lang.String r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            r4.zzl((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
        L_0x0962:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r0 = r34
            java.lang.String r3 = r0.zza     // Catch:{ all -> 0x03d6 }
            java.util.List r5 = r2.zza((java.lang.String) r3)     // Catch:{ all -> 0x03d6 }
            r2 = 0
            r3 = r2
        L_0x0970:
            int r2 = r5.size()     // Catch:{ all -> 0x03d6 }
            if (r3 >= r2) goto L_0x0a47
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r6 = com.google.android.gms.internal.measurement.zzbs.zzk.zzj()     // Catch:{ all -> 0x03d6 }
            java.lang.Object r2 = r5.get(r3)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkj r2 = (com.google.android.gms.measurement.internal.zzkj) r2     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = r2.zzc     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r6 = r6.zza((java.lang.String) r2)     // Catch:{ all -> 0x03d6 }
            java.lang.Object r2 = r5.get(r3)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkj r2 = (com.google.android.gms.measurement.internal.zzkj) r2     // Catch:{ all -> 0x03d6 }
            long r8 = r2.zzd     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r6 = r6.zza((long) r8)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzki r7 = r32.zzh()     // Catch:{ all -> 0x03d6 }
            java.lang.Object r2 = r5.get(r3)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzkj r2 = (com.google.android.gms.measurement.internal.zzkj) r2     // Catch:{ all -> 0x03d6 }
            java.lang.Object r2 = r2.zze     // Catch:{ all -> 0x03d6 }
            r7.zza((com.google.android.gms.internal.measurement.zzbs.zzk.zza) r6, (java.lang.Object) r2)     // Catch:{ all -> 0x03d6 }
            r4.zza((com.google.android.gms.internal.measurement.zzbs.zzk.zza) r6)     // Catch:{ all -> 0x03d6 }
            int r2 = r3 + 1
            r3 = r2
            goto L_0x0970
        L_0x09a8:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            long r4 = r2.zzf     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzal r13 = r13.zza(r3, r4)     // Catch:{ all -> 0x03d6 }
            long r4 = r13.zzc     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzak r15 = r2.zza(r4)     // Catch:{ all -> 0x03d6 }
            goto L_0x06c9
        L_0x09ba:
            java.lang.String r2 = r4.zzl()     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x07b5
            r0 = r34
            java.lang.String r2 = r0.zzr     // Catch:{ all -> 0x03d6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x07b5
            r0 = r34
            java.lang.String r2 = r0.zzr     // Catch:{ all -> 0x03d6 }
            r4.zzo(r2)     // Catch:{ all -> 0x03d6 }
            goto L_0x07b5
        L_0x09d7:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzai r2 = r2.zzx()     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            android.content.Context r3 = r3.zzn()     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zza(r3)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x0826
            r0 = r34
            boolean r2 = r0.zzp     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0826
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            android.content.Context r2 = r2.zzn()     // Catch:{ all -> 0x03d6 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = "android_id"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r3)     // Catch:{ all -> 0x03d6 }
            if (r2 != 0) goto L_0x0a27
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = "null secure ID. appId"
            java.lang.String r5 = r4.zzj()     // Catch:{ all -> 0x03d6 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r5)     // Catch:{ all -> 0x03d6 }
            r2.zza(r3, r5)     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = "null"
        L_0x0a22:
            r4.zzm(r2)     // Catch:{ all -> 0x03d6 }
            goto L_0x0826
        L_0x0a27:
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x03d6 }
            if (r3 == 0) goto L_0x0a22
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r3 = r3.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzi()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "empty secure ID. appId"
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x03d6 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r6)     // Catch:{ all -> 0x03d6 }
            r3.zza(r5, r6)     // Catch:{ all -> 0x03d6 }
            goto L_0x0a22
        L_0x0a47:
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ IOException -> 0x0abc }
            com.google.android.gms.internal.measurement.zzgm r2 = r4.zzv()     // Catch:{ IOException -> 0x0abc }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ IOException -> 0x0abc }
            com.google.android.gms.internal.measurement.zzbs$zzg r2 = (com.google.android.gms.internal.measurement.zzbs.zzg) r2     // Catch:{ IOException -> 0x0abc }
            long r14 = r3.zza((com.google.android.gms.internal.measurement.zzbs.zzg) r2)     // Catch:{ IOException -> 0x0abc }
            com.google.android.gms.measurement.internal.zzad r12 = r32.zze()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzan r2 = r13.zze     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0b10
            com.google.android.gms.measurement.internal.zzan r2 = r13.zze     // Catch:{ all -> 0x03d6 }
            java.util.Iterator r3 = r2.iterator()     // Catch:{ all -> 0x03d6 }
        L_0x0a65:
            boolean r2 = r3.hasNext()     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0ad7
            java.lang.Object r2 = r3.next()     // Catch:{ all -> 0x03d6 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = "_r"
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0a65
            r2 = 1
        L_0x0a7a:
            boolean r2 = r12.zza((com.google.android.gms.measurement.internal.zzal) r13, (long) r14, (boolean) r2)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0a86
            r2 = 0
            r0 = r32
            r0.zzm = r2     // Catch:{ all -> 0x03d6 }
        L_0x0a86:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x03d6 }
            r2.mo24237b_()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            r32.zzz()
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzx()
            java.lang.String r3 = "Background event processing time, ms"
            long r4 = java.lang.System.nanoTime()
            long r4 = r4 - r30
            r6 = 500000(0x7a120, double:2.47033E-318)
            long r4 = r4 + r6
            r6 = 1000000(0xf4240, double:4.940656E-318)
            long r4 = r4 / r6
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r2.zza(r3, r4)
            goto L_0x0021
        L_0x0abc:
            r2 = move-exception
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzes r3 = r3.zzr()     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r4 = r4.zzj()     // Catch:{ all -> 0x03d6 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x03d6 }
            r3.zza(r5, r4, r2)     // Catch:{ all -> 0x03d6 }
            goto L_0x0a86
        L_0x0ad7:
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()     // Catch:{ all -> 0x03d6 }
            java.lang.String r3 = r13.zza     // Catch:{ all -> 0x03d6 }
            java.lang.String r4 = r13.zzb     // Catch:{ all -> 0x03d6 }
            boolean r2 = r2.zzc(r3, r4)     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ all -> 0x03d6 }
            long r4 = r32.zzx()     // Catch:{ all -> 0x03d6 }
            java.lang.String r6 = r13.zza     // Catch:{ all -> 0x03d6 }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r4, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x03d6 }
            if (r2 == 0) goto L_0x0b10
            long r2 = r3.zze     // Catch:{ all -> 0x03d6 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r4 = r0.zzj     // Catch:{ all -> 0x03d6 }
            com.google.android.gms.measurement.internal.zzy r4 = r4.zzb()     // Catch:{ all -> 0x03d6 }
            java.lang.String r5 = r13.zza     // Catch:{ all -> 0x03d6 }
            int r4 = r4.zzb(r5)     // Catch:{ all -> 0x03d6 }
            long r4 = (long) r4
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0b10
            r2 = 1
            goto L_0x0a7a
        L_0x0b10:
            r2 = 0
            goto L_0x0a7a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzka.zzb(com.google.android.gms.measurement.internal.zzao, com.google.android.gms.measurement.internal.zzn):void");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzl() {
        String d_;
        zzf zzb2;
        String str;
        List<Pair<zzbs.zzg, Long>> list;
        String str2;
        String zza2;
        zzw();
        zzk();
        this.zzs = true;
        try {
            this.zzj.zzu();
            Boolean zzag = this.zzj.zzw().zzag();
            if (zzag == null) {
                this.zzj.zzr().zzi().zza("Upload data called on the client side before use of service was decided");
                this.zzs = false;
                zzaa();
            } else if (zzag.booleanValue()) {
                this.zzj.zzr().zzf().zza("Upload called in the client side when service should be used");
                this.zzs = false;
                zzaa();
            } else if (this.zzm > 0) {
                zzz();
                this.zzs = false;
                zzaa();
            } else {
                zzw();
                if (this.zzv != null) {
                    this.zzj.zzr().zzx().zza("Uploading requested multiple times");
                    this.zzs = false;
                    zzaa();
                } else if (!zzd().zzf()) {
                    this.zzj.zzr().zzx().zza("Network not connected, ignoring upload request");
                    zzz();
                    this.zzs = false;
                    zzaa();
                } else {
                    long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
                    int zzb3 = this.zzj.zzb().zzb((String) null, zzaq.zzap);
                    long zzv2 = currentTimeMillis - zzy.zzv();
                    for (int i = 0; i < zzb3 && zza((String) null, zzv2); i++) {
                    }
                    long zza3 = this.zzj.zzc().zzc.zza();
                    if (zza3 != 0) {
                        this.zzj.zzr().zzw().zza("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - zza3)));
                    }
                    d_ = zze().mo24239d_();
                    if (!TextUtils.isEmpty(d_)) {
                        if (this.zzx == -1) {
                            this.zzx = zze().zzaa();
                        }
                        List<Pair<zzbs.zzg, Long>> zza4 = zze().zza(d_, this.zzj.zzb().zzb(d_, zzaq.zzf), Math.max(0, this.zzj.zzb().zzb(d_, zzaq.zzg)));
                        if (!zza4.isEmpty()) {
                            Iterator<Pair<zzbs.zzg, Long>> it = zza4.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    str = null;
                                    break;
                                }
                                zzbs.zzg zzg2 = (zzbs.zzg) it.next().first;
                                if (!TextUtils.isEmpty(zzg2.zzad())) {
                                    str = zzg2.zzad();
                                    break;
                                }
                            }
                            if (str != null) {
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= zza4.size()) {
                                        break;
                                    }
                                    zzbs.zzg zzg3 = (zzbs.zzg) zza4.get(i2).first;
                                    if (!TextUtils.isEmpty(zzg3.zzad()) && !zzg3.zzad().equals(str)) {
                                        list = zza4.subList(0, i2);
                                        break;
                                    }
                                    i2++;
                                }
                            }
                            list = zza4;
                            zzbs.zzf.zza zzb4 = zzbs.zzf.zzb();
                            int size = list.size();
                            ArrayList arrayList = new ArrayList(list.size());
                            boolean zzf2 = this.zzj.zzb().zzf(d_);
                            for (int i3 = 0; i3 < size; i3++) {
                                zzbs.zzg.zza zza5 = (zzbs.zzg.zza) ((zzbs.zzg) list.get(i3).first).zzbl();
                                arrayList.add((Long) list.get(i3).second);
                                zzbs.zzg.zza zza6 = zza5.zzg(this.zzj.zzb().zzf()).zza(currentTimeMillis);
                                this.zzj.zzu();
                                zza6.zzb(false);
                                if (!zzf2) {
                                    zza5.zzn();
                                }
                                if (this.zzj.zzb().zze(d_, zzaq.zzay)) {
                                    zza5.zzl(zzh().zza(((zzbs.zzg) ((zzfe) zza5.zzv())).zzbi()));
                                }
                                zzb4.zza(zza5);
                            }
                            if (this.zzj.zzr().zza(2)) {
                                str2 = zzh().zza((zzbs.zzf) ((zzfe) zzb4.zzv()));
                            } else {
                                str2 = null;
                            }
                            zzh();
                            byte[] zzbi = ((zzbs.zzf) ((zzfe) zzb4.zzv())).zzbi();
                            zza2 = zzaq.zzp.zza(null);
                            URL url = new URL(zza2);
                            Preconditions.checkArgument(!arrayList.isEmpty());
                            if (this.zzv != null) {
                                this.zzj.zzr().zzf().zza("Set uploading progress before finishing the previous upload");
                            } else {
                                this.zzv = new ArrayList(arrayList);
                            }
                            this.zzj.zzc().zzd.zza(currentTimeMillis);
                            String str3 = "?";
                            if (size > 0) {
                                str3 = zzb4.zza(0).zzx();
                            }
                            this.zzj.zzr().zzx().zza("Uploading data. app, uncompressed size, data", str3, Integer.valueOf(zzbi.length), str2);
                            this.zzr = true;
                            zzez zzd2 = zzd();
                            zzkc zzkc = new zzkc(this, d_);
                            zzd2.zzd();
                            zzd2.zzak();
                            Preconditions.checkNotNull(url);
                            Preconditions.checkNotNull(zzbi);
                            Preconditions.checkNotNull(zzkc);
                            zzd2.zzq().zzb((Runnable) new zzfd(zzd2, d_, url, zzbi, (Map<String, String>) null, zzkc));
                        }
                    } else {
                        this.zzx = -1;
                        String zza7 = zze().zza(currentTimeMillis - zzy.zzv());
                        if (!TextUtils.isEmpty(zza7) && (zzb2 = zze().zzb(zza7)) != null) {
                            zza(zzb2);
                        }
                    }
                    this.zzs = false;
                    zzaa();
                }
            }
        } catch (MalformedURLException e) {
            this.zzj.zzr().zzf().zza("Failed to parse upload URL. Not uploading. appId", zzes.zza(d_), zza2);
        } catch (Throwable th) {
            this.zzs = false;
            zzaa();
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r2v133, types: [com.google.android.gms.internal.measurement.zzfe$zza] */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03cd, code lost:
        if (r2 != false) goto L_0x03cf;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0397 A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x039b A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x05a2 A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0677 A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x06d6 A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0093 A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0096 A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:501:0x1013 A[Catch:{ IOException -> 0x02f9, all -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:518:0x105c  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r33, long r34) {
        /*
            r32 = this;
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzf()
            com.google.android.gms.measurement.internal.zzka$zza r23 = new com.google.android.gms.measurement.internal.zzka$zza     // Catch:{ all -> 0x01f8 }
            r2 = 0
            r0 = r23
            r1 = r32
            r0.<init>(r1, r2)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzad r14 = r32.zze()     // Catch:{ all -> 0x01f8 }
            r4 = 0
            r0 = r32
            long r0 = r0.zzx     // Catch:{ all -> 0x01f8 }
            r16 = r0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r23)     // Catch:{ all -> 0x01f8 }
            r14.zzd()     // Catch:{ all -> 0x01f8 }
            r14.zzak()     // Catch:{ all -> 0x01f8 }
            r3 = 0
            android.database.sqlite.SQLiteDatabase r2 = r14.mo24238c_()     // Catch:{ SQLiteException -> 0x1027 }
            r5 = 0
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteException -> 0x1027 }
            if (r5 == 0) goto L_0x0201
            r6 = -1
            int r5 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x019a
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x1027 }
            r6 = 0
            java.lang.String r7 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x1027 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x1027 }
            r6 = 1
            java.lang.String r7 = java.lang.String.valueOf(r34)     // Catch:{ SQLiteException -> 0x1027 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x1027 }
            r6 = r5
        L_0x0049:
            r8 = -1
            int r5 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x01a7
            java.lang.String r5 = "rowid <= ? and "
        L_0x0051:
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x1027 }
            int r7 = r7.length()     // Catch:{ SQLiteException -> 0x1027 }
            int r7 = r7 + 148
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x1027 }
            r8.<init>(r7)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.String r7 = "select app_id, metadata_fingerprint from raw_events where "
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.StringBuilder r5 = r7.append(r5)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.String r7 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x1027 }
            android.database.Cursor r3 = r2.rawQuery(r5, r6)     // Catch:{ SQLiteException -> 0x1027 }
            boolean r5 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x1027 }
            if (r5 != 0) goto L_0x01ab
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01f8 }
        L_0x0083:
            r0 = r23
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r2 = r0.zzc     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0093
            r0 = r23
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r2 = r0.zzc     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x039b
        L_0x0093:
            r2 = 1
        L_0x0094:
            if (r2 != 0) goto L_0x1013
            r20 = 0
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r2 = r0.zza     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = r2.zzbl()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zzg.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r24 = r2.zzc()     // Catch:{ all -> 0x01f8 }
            r17 = 0
            r18 = 0
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzau     // Catch:{ all -> 0x01f8 }
            boolean r22 = r2.zze(r3, r4)     // Catch:{ all -> 0x01f8 }
            r16 = 0
            r15 = -1
            r14 = 0
            r13 = -1
            r2 = 0
            r21 = r2
        L_0x00ca:
            r0 = r23
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r2 = r0.zzc     // Catch:{ all -> 0x01f8 }
            int r2 = r2.size()     // Catch:{ all -> 0x01f8 }
            r0 = r21
            if (r0 >= r2) goto L_0x0907
            r0 = r23
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r2 = r0.zzc     // Catch:{ all -> 0x01f8 }
            r0 = r21
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = r2.zzbl()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            r0 = r2
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r0 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r0     // Catch:{ all -> 0x01f8 }
            r12 = r0
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zzb(r3, r4)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x03a1
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Dropping blacklisted raw event. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r5 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeq r5 = r5.zzj()     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r5.zza((java.lang.String) r6)     // Catch:{ all -> 0x01f8 }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zzg(r3)     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x0153
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zzh(r3)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x039e
        L_0x0153:
            r2 = 1
        L_0x0154:
            if (r2 != 0) goto L_0x104f
            java.lang.String r2 = "_err"
            java.lang.String r3 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x104f
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            r4 = 11
            java.lang.String r5 = "_ev"
            java.lang.String r6 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            r7 = 0
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)     // Catch:{ all -> 0x01f8 }
            r2 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r8 = r18
            r7 = r17
            r10 = r20
        L_0x0189:
            int r3 = r21 + 1
            r21 = r3
            r13 = r2
            r14 = r4
            r15 = r5
            r16 = r6
            r18 = r8
            r17 = r7
            r20 = r10
            goto L_0x00ca
        L_0x019a:
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x1027 }
            r6 = 0
            java.lang.String r7 = java.lang.String.valueOf(r34)     // Catch:{ SQLiteException -> 0x1027 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x1027 }
            r6 = r5
            goto L_0x0049
        L_0x01a7:
            java.lang.String r5 = ""
            goto L_0x0051
        L_0x01ab:
            r5 = 0
            java.lang.String r4 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x1027 }
            r5 = 1
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x1027 }
            r3.close()     // Catch:{ SQLiteException -> 0x1027 }
            r13 = r5
            r11 = r3
            r12 = r4
        L_0x01bb:
            java.lang.String r3 = "raw_events_metadata"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r5 = 0
            java.lang.String r6 = "metadata"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 0
            r6[r7] = r12     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 1
            r6[r7] = r13     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "2"
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            boolean r3 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            if (r3 != 0) goto L_0x026b
            com.google.android.gms.measurement.internal.zzes r2 = r14.zzr()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            java.lang.String r3 = "Raw event metadata record is missing. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r2.zza(r3, r4)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            if (r11 == 0) goto L_0x0083
            r11.close()     // Catch:{ all -> 0x01f8 }
            goto L_0x0083
        L_0x01f8:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()
            r3.zzh()
            throw r2
        L_0x0201:
            r6 = -1
            int r5 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x0252
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x1027 }
            r6 = 0
            r7 = 0
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x1027 }
            r6 = 1
            java.lang.String r7 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x1027 }
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x1027 }
            r6 = r5
        L_0x0216:
            r8 = -1
            int r5 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x025b
            java.lang.String r5 = " and rowid <= ?"
        L_0x021e:
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x1027 }
            int r7 = r7.length()     // Catch:{ SQLiteException -> 0x1027 }
            int r7 = r7 + 84
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x1027 }
            r8.<init>(r7)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.String r7 = "select metadata_fingerprint from raw_events where app_id = ?"
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.StringBuilder r5 = r7.append(r5)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.String r7 = " order by rowid limit 1;"
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ SQLiteException -> 0x1027 }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x1027 }
            android.database.Cursor r3 = r2.rawQuery(r5, r6)     // Catch:{ SQLiteException -> 0x1027 }
            boolean r5 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x1027 }
            if (r5 != 0) goto L_0x025e
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01f8 }
            goto L_0x0083
        L_0x0252:
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x1027 }
            r6 = 0
            r7 = 0
            r5[r6] = r7     // Catch:{ SQLiteException -> 0x1027 }
            r6 = r5
            goto L_0x0216
        L_0x025b:
            java.lang.String r5 = ""
            goto L_0x021e
        L_0x025e:
            r5 = 0
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x1027 }
            r3.close()     // Catch:{ SQLiteException -> 0x1027 }
            r13 = r5
            r11 = r3
            r12 = r4
            goto L_0x01bb
        L_0x026b:
            r3 = 0
            byte[] r3 = r11.getBlob(r3)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = com.google.android.gms.internal.measurement.zzbs.zzg.zzbf()     // Catch:{ IOException -> 0x02f9 }
            com.google.android.gms.internal.measurement.zzgp r3 = com.google.android.gms.measurement.internal.zzki.zza(r4, (byte[]) r3)     // Catch:{ IOException -> 0x02f9 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r3 = (com.google.android.gms.internal.measurement.zzbs.zzg.zza) r3     // Catch:{ IOException -> 0x02f9 }
            com.google.android.gms.internal.measurement.zzgm r3 = r3.zzv()     // Catch:{ IOException -> 0x02f9 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ IOException -> 0x02f9 }
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = (com.google.android.gms.internal.measurement.zzbs.zzg) r3     // Catch:{ IOException -> 0x02f9 }
            boolean r4 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            if (r4 == 0) goto L_0x0299
            com.google.android.gms.measurement.internal.zzes r4 = r14.zzr()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzi()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            java.lang.String r5 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r4.zza(r5, r6)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
        L_0x0299:
            r11.close()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r0 = r23
            r0.zza(r3)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r4 = -1
            int r3 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x0312
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r3 = 3
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r3 = 0
            r6[r3] = r12     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r3 = 1
            r6[r3] = r13     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r3 = 2
            java.lang.String r4 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r6[r3] = r4     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
        L_0x02b9:
            java.lang.String r3 = "raw_events"
            r4 = 4
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 0
            java.lang.String r8 = "rowid"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 1
            java.lang.String r8 = "name"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 2
            java.lang.String r8 = "timestamp"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 3
            java.lang.String r8 = "data"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            r10 = 0
            android.database.Cursor r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x102a }
            if (r2 != 0) goto L_0x0339
            com.google.android.gms.measurement.internal.zzes r2 = r14.zzr()     // Catch:{ SQLiteException -> 0x102a }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ SQLiteException -> 0x102a }
            java.lang.String r4 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x102a }
            r2.zza(r4, r5)     // Catch:{ SQLiteException -> 0x102a }
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01f8 }
            goto L_0x0083
        L_0x02f9:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzes r3 = r14.zzr()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            java.lang.String r4 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r3.zza(r4, r5, r2)     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            if (r11 == 0) goto L_0x0083
            r11.close()     // Catch:{ all -> 0x01f8 }
            goto L_0x0083
        L_0x0312:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r3 = 2
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r3 = 0
            r6[r3] = r12     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            r3 = 1
            r6[r3] = r13     // Catch:{ SQLiteException -> 0x031e, all -> 0x1023 }
            goto L_0x02b9
        L_0x031e:
            r2 = move-exception
            r3 = r11
            r4 = r12
        L_0x0321:
            com.google.android.gms.measurement.internal.zzes r5 = r14.zzr()     // Catch:{ all -> 0x0394 }
            com.google.android.gms.measurement.internal.zzeu r5 = r5.zzf()     // Catch:{ all -> 0x0394 }
            java.lang.String r6 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x0394 }
            r5.zza(r6, r4, r2)     // Catch:{ all -> 0x0394 }
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01f8 }
            goto L_0x0083
        L_0x0339:
            r2 = 0
            long r4 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x102a }
            r2 = 3
            byte[] r2 = r3.getBlob(r2)     // Catch:{ SQLiteException -> 0x102a }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r6 = com.google.android.gms.internal.measurement.zzbs.zzc.zzj()     // Catch:{ IOException -> 0x0375 }
            com.google.android.gms.internal.measurement.zzgp r2 = com.google.android.gms.measurement.internal.zzki.zza(r6, (byte[]) r2)     // Catch:{ IOException -> 0x0375 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2     // Catch:{ IOException -> 0x0375 }
            r6 = 1
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x102a }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r6 = r2.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x102a }
            r7 = 2
            long r8 = r3.getLong(r7)     // Catch:{ SQLiteException -> 0x102a }
            r6.zza((long) r8)     // Catch:{ SQLiteException -> 0x102a }
            com.google.android.gms.internal.measurement.zzgm r2 = r2.zzv()     // Catch:{ SQLiteException -> 0x102a }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ SQLiteException -> 0x102a }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ SQLiteException -> 0x102a }
            r0 = r23
            boolean r2 = r0.zza(r4, r2)     // Catch:{ SQLiteException -> 0x102a }
            if (r2 != 0) goto L_0x0387
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01f8 }
            goto L_0x0083
        L_0x0375:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzes r4 = r14.zzr()     // Catch:{ SQLiteException -> 0x102a }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ SQLiteException -> 0x102a }
            java.lang.String r5 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x102a }
            r4.zza(r5, r6, r2)     // Catch:{ SQLiteException -> 0x102a }
        L_0x0387:
            boolean r2 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x102a }
            if (r2 != 0) goto L_0x0339
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ all -> 0x01f8 }
            goto L_0x0083
        L_0x0394:
            r2 = move-exception
        L_0x0395:
            if (r3 == 0) goto L_0x039a
            r3.close()     // Catch:{ all -> 0x01f8 }
        L_0x039a:
            throw r2     // Catch:{ all -> 0x01f8 }
        L_0x039b:
            r2 = 0
            goto L_0x0094
        L_0x039e:
            r2 = 0
            goto L_0x0154
        L_0x03a1:
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r25 = r2.zzc(r3, r4)     // Catch:{ all -> 0x01f8 }
            if (r25 != 0) goto L_0x03cf
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x01f8 }
            r2 = -1
            int r4 = r3.hashCode()     // Catch:{ all -> 0x01f8 }
            switch(r4) {
                case 94660: goto L_0x040c;
                case 95025: goto L_0x0420;
                case 95027: goto L_0x0416;
                default: goto L_0x03c9;
            }     // Catch:{ all -> 0x01f8 }
        L_0x03c9:
            switch(r2) {
                case 0: goto L_0x042a;
                case 1: goto L_0x042a;
                case 2: goto L_0x042a;
                default: goto L_0x03cc;
            }     // Catch:{ all -> 0x01f8 }
        L_0x03cc:
            r2 = 0
        L_0x03cd:
            if (r2 == 0) goto L_0x060d
        L_0x03cf:
            r4 = 0
            r3 = 0
            r2 = 0
            r5 = r2
        L_0x03d3:
            int r2 = r12.zzb()     // Catch:{ all -> 0x01f8 }
            if (r5 >= r2) goto L_0x045c
            java.lang.String r2 = "_c"
            com.google.android.gms.internal.measurement.zzbs$zze r6 = r12.zza((int) r5)     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r6.zzb()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x042c
            com.google.android.gms.internal.measurement.zzbs$zze r2 = r12.zza((int) r5)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = r2.zzbl()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r2     // Catch:{ all -> 0x01f8 }
            r6 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((long) r6)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            r4 = 1
            r12.zza((int) r5, (com.google.android.gms.internal.measurement.zzbs.zze) r2)     // Catch:{ all -> 0x01f8 }
            r2 = r3
        L_0x0408:
            int r5 = r5 + 1
            r3 = r2
            goto L_0x03d3
        L_0x040c:
            java.lang.String r4 = "_in"
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x03c9
            r2 = 0
            goto L_0x03c9
        L_0x0416:
            java.lang.String r4 = "_ui"
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x03c9
            r2 = 1
            goto L_0x03c9
        L_0x0420:
            java.lang.String r4 = "_ug"
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x03c9
            r2 = 2
            goto L_0x03c9
        L_0x042a:
            r2 = 1
            goto L_0x03cd
        L_0x042c:
            java.lang.String r2 = "_r"
            com.google.android.gms.internal.measurement.zzbs$zze r6 = r12.zza((int) r5)     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r6.zzb()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x045a
            com.google.android.gms.internal.measurement.zzbs$zze r2 = r12.zza((int) r5)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = r2.zzbl()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r2     // Catch:{ all -> 0x01f8 }
            r6 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((long) r6)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            r3 = 1
            r12.zza((int) r5, (com.google.android.gms.internal.measurement.zzbs.zze) r2)     // Catch:{ all -> 0x01f8 }
        L_0x045a:
            r2 = r3
            goto L_0x0408
        L_0x045c:
            if (r4 != 0) goto L_0x0494
            if (r25 == 0) goto L_0x0494
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "Marking event as conversion"
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r5 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeq r5 = r5.zzj()     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r5.zza((java.lang.String) r6)     // Catch:{ all -> 0x01f8 }
            r2.zza(r4, r5)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = com.google.android.gms.internal.measurement.zzbs.zze.zzk()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "_c"
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r4 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((long) r4)     // Catch:{ all -> 0x01f8 }
            r12.zza((com.google.android.gms.internal.measurement.zzbs.zze.zza) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0494:
            if (r3 != 0) goto L_0x04ca
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Marking event as real-time"
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r4 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeq r4 = r4.zzj()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zza((java.lang.String) r5)     // Catch:{ all -> 0x01f8 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = com.google.android.gms.internal.measurement.zzbs.zze.zzk()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "_r"
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
            r4 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((long) r4)     // Catch:{ all -> 0x01f8 }
            r12.zza((com.google.android.gms.internal.measurement.zzbs.zze.zza) r2)     // Catch:{ all -> 0x01f8 }
        L_0x04ca:
            r2 = 1
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ all -> 0x01f8 }
            long r4 = r32.zzx()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x01f8 }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 1
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r4, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x01f8 }
            long r4 = r3.zze     // Catch:{ all -> 0x01f8 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x01f8 }
            int r3 = r3.zzb(r6)     // Catch:{ all -> 0x01f8 }
            long r6 = (long) r3     // Catch:{ all -> 0x01f8 }
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x104b
            java.lang.String r2 = "_r"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (java.lang.String) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0504:
            java.lang.String r2 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r2 = com.google.android.gms.measurement.internal.zzkm.zza((java.lang.String) r2)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x060d
            if (r25 == 0) goto L_0x060d
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ all -> 0x01f8 }
            long r4 = r32.zzx()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r2 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r2.zzx()     // Catch:{ all -> 0x01f8 }
            r7 = 0
            r8 = 0
            r9 = 1
            r10 = 0
            r11 = 0
            com.google.android.gms.measurement.internal.zzac r2 = r3.zza(r4, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x01f8 }
            long r2 = r2.zzc     // Catch:{ all -> 0x01f8 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r4 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r4 = r4.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r5.zzx()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzaq.zzm     // Catch:{ all -> 0x01f8 }
            int r4 = r4.zzb(r5, r6)     // Catch:{ all -> 0x01f8 }
            long r4 = (long) r4     // Catch:{ all -> 0x01f8 }
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x060d
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Too many conversions. Not logging as conversion. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x01f8 }
            r5 = 0
            r2 = 0
            r3 = -1
            r4 = 0
        L_0x0567:
            int r6 = r12.zzb()     // Catch:{ all -> 0x01f8 }
            if (r4 >= r6) goto L_0x0597
            com.google.android.gms.internal.measurement.zzbs$zze r6 = r12.zza((int) r4)     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "_c"
            java.lang.String r8 = r6.zzb()     // Catch:{ all -> 0x01f8 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x01f8 }
            if (r7 == 0) goto L_0x0589
            com.google.android.gms.internal.measurement.zzfe$zza r2 = r6.zzbl()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r2     // Catch:{ all -> 0x01f8 }
            r3 = r4
        L_0x0586:
            int r4 = r4 + 1
            goto L_0x0567
        L_0x0589:
            java.lang.String r7 = "_err"
            java.lang.String r6 = r6.zzb()     // Catch:{ all -> 0x01f8 }
            boolean r6 = r7.equals(r6)     // Catch:{ all -> 0x01f8 }
            if (r6 == 0) goto L_0x0586
            r5 = 1
            goto L_0x0586
        L_0x0597:
            if (r5 == 0) goto L_0x05cc
            if (r2 == 0) goto L_0x05cc
            r12.zzb((int) r3)     // Catch:{ all -> 0x01f8 }
            r10 = r20
        L_0x05a0:
            if (r25 == 0) goto L_0x065f
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x01f8 }
            java.util.List r2 = r12.zza()     // Catch:{ all -> 0x01f8 }
            r6.<init>(r2)     // Catch:{ all -> 0x01f8 }
            r5 = -1
            r3 = -1
            r4 = 0
        L_0x05ae:
            int r2 = r6.size()     // Catch:{ all -> 0x01f8 }
            if (r4 >= r2) goto L_0x0624
            java.lang.String r7 = "value"
            java.lang.Object r2 = r6.get(r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r7.equals(r2)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0610
            r2 = r3
            r5 = r4
        L_0x05c8:
            int r4 = r4 + 1
            r3 = r2
            goto L_0x05ae
        L_0x05cc:
            if (r2 == 0) goto L_0x05f0
            java.lang.Object r2 = r2.clone()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "_err"
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r4 = 10
            com.google.android.gms.internal.measurement.zzbs$zze$zza r2 = r2.zza((long) r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            r12.zza((int) r3, (com.google.android.gms.internal.measurement.zzbs.zze) r2)     // Catch:{ all -> 0x01f8 }
            r10 = r20
            goto L_0x05a0
        L_0x05f0:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Did not find conversion parameter. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x01f8 }
        L_0x060d:
            r10 = r20
            goto L_0x05a0
        L_0x0610:
            java.lang.String r7 = "currency"
            java.lang.Object r2 = r6.get(r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r7.equals(r2)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x1048
            r2 = r4
            goto L_0x05c8
        L_0x0624:
            r2 = -1
            if (r5 == r2) goto L_0x065f
            java.lang.Object r2 = r6.get(r5)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zze()     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x0716
            java.lang.Object r2 = r6.get(r5)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zzg()     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x0716
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzk()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Value must be specified with a numeric type."
            r2.zza(r3)     // Catch:{ all -> 0x01f8 }
            r12.zzb((int) r5)     // Catch:{ all -> 0x01f8 }
            java.lang.String r2 = "_c"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (java.lang.String) r2)     // Catch:{ all -> 0x01f8 }
            r2 = 18
            java.lang.String r3 = "value"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (int) r2, (java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
        L_0x065f:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzat     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zze(r3, r4)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x103e
            java.lang.String r2 = "_e"
            java.lang.String r3 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0779
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r12.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "_fr"
            com.google.android.gms.internal.measurement.zzbs$zze r2 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r2, (java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x103e
            if (r14 == 0) goto L_0x0772
            long r2 = r14.zzf()     // Catch:{ all -> 0x01f8 }
            long r4 = r12.zzf()     // Catch:{ all -> 0x01f8 }
            long r2 = r2 - r4
            long r2 = java.lang.Math.abs(r2)     // Catch:{ all -> 0x01f8 }
            r4 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x0772
            java.lang.Object r2 = r14.clone()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2     // Catch:{ all -> 0x01f8 }
            r0 = r32
            boolean r3 = r0.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x076b
            r16 = 0
            r14 = 0
            r0 = r24
            r0.zza((int) r13, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
        L_0x06c8:
            if (r22 != 0) goto L_0x105c
            java.lang.String r2 = "_e"
            java.lang.String r7 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.equals(r7)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x105c
            int r2 = r12.zzb()     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x08c6
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "Engagement event does not contain any parameters. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r8 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r8 = r8.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)     // Catch:{ all -> 0x01f8 }
            r2.zza(r7, r8)     // Catch:{ all -> 0x01f8 }
            r8 = r18
        L_0x06fb:
            r0 = r23
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r7 = r0.zzc     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r12.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ all -> 0x01f8 }
            r0 = r21
            r7.set(r0, r2)     // Catch:{ all -> 0x01f8 }
            int r7 = r17 + 1
            r0 = r24
            r0.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12)     // Catch:{ all -> 0x01f8 }
            r2 = r3
            goto L_0x0189
        L_0x0716:
            r4 = 0
            r2 = -1
            if (r3 != r2) goto L_0x073f
            r2 = 1
        L_0x071b:
            if (r2 == 0) goto L_0x065f
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzk()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r2.zza(r3)     // Catch:{ all -> 0x01f8 }
            r12.zzb((int) r5)     // Catch:{ all -> 0x01f8 }
            java.lang.String r2 = "_c"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (java.lang.String) r2)     // Catch:{ all -> 0x01f8 }
            r2 = 19
            java.lang.String r3 = "currency"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (int) r2, (java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
            goto L_0x065f
        L_0x073f:
            java.lang.Object r2 = r6.get(r3)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r2 = (com.google.android.gms.internal.measurement.zzbs.zze) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            int r2 = r3.length()     // Catch:{ all -> 0x01f8 }
            r6 = 3
            if (r2 == r6) goto L_0x0752
            r2 = 1
            goto L_0x071b
        L_0x0752:
            r2 = 0
        L_0x0753:
            int r6 = r3.length()     // Catch:{ all -> 0x01f8 }
            if (r2 >= r6) goto L_0x1045
            int r6 = r3.codePointAt(r2)     // Catch:{ all -> 0x01f8 }
            boolean r7 = java.lang.Character.isLetter(r6)     // Catch:{ all -> 0x01f8 }
            if (r7 != 0) goto L_0x0765
            r2 = 1
            goto L_0x071b
        L_0x0765:
            int r6 = java.lang.Character.charCount(r6)     // Catch:{ all -> 0x01f8 }
            int r2 = r2 + r6
            goto L_0x0753
        L_0x076b:
            r3 = r13
            r4 = r14
            r5 = r17
            r6 = r12
            goto L_0x06c8
        L_0x0772:
            r3 = r13
            r4 = r14
            r5 = r17
            r6 = r12
            goto L_0x06c8
        L_0x0779:
            java.lang.String r2 = "_vs"
            java.lang.String r3 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x07dc
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r12.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "_et"
            com.google.android.gms.internal.measurement.zzbs$zze r2 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r2, (java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x103e
            if (r16 == 0) goto L_0x07d4
            long r2 = r16.zzf()     // Catch:{ all -> 0x01f8 }
            long r4 = r12.zzf()     // Catch:{ all -> 0x01f8 }
            long r2 = r2 - r4
            long r2 = java.lang.Math.abs(r2)     // Catch:{ all -> 0x01f8 }
            r4 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x07d4
            java.lang.Object r2 = r16.clone()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2     // Catch:{ all -> 0x01f8 }
            r0 = r32
            boolean r3 = r0.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x07cc
            r16 = 0
            r14 = 0
            r0 = r24
            r0.zza((int) r15, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            goto L_0x06c8
        L_0x07cc:
            r3 = r17
            r4 = r12
            r5 = r15
            r6 = r16
            goto L_0x06c8
        L_0x07d4:
            r3 = r17
            r4 = r12
            r5 = r15
            r6 = r16
            goto L_0x06c8
        L_0x07dc:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzbs     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zze(r3, r4)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x103e
            java.lang.String r2 = "_ab"
            java.lang.String r3 = r12.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x103e
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r12.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "_et"
            com.google.android.gms.internal.measurement.zzbs$zze r2 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r2, (java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x103e
            if (r16 == 0) goto L_0x103e
            long r2 = r16.zzf()     // Catch:{ all -> 0x01f8 }
            long r4 = r12.zzf()     // Catch:{ all -> 0x01f8 }
            long r2 = r2 - r4
            long r2 = java.lang.Math.abs(r2)     // Catch:{ all -> 0x01f8 }
            r4 = 4000(0xfa0, double:1.9763E-320)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x103e
            java.lang.Object r2 = r16.clone()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2     // Catch:{ all -> 0x01f8 }
            r0 = r32
            r0.zzb((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12)     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "_e"
            java.lang.String r4 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r3)     // Catch:{ all -> 0x01f8 }
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "_sn"
            com.google.android.gms.internal.measurement.zzbs$zze r4 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r3, (java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = "_sc"
            com.google.android.gms.internal.measurement.zzbs$zze r5 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r3, (java.lang.String) r5)     // Catch:{ all -> 0x01f8 }
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = "_si"
            com.google.android.gms.internal.measurement.zzbs$zze r6 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r3, (java.lang.String) r6)     // Catch:{ all -> 0x01f8 }
            if (r4 == 0) goto L_0x08c0
            java.lang.String r3 = r4.zzd()     // Catch:{ all -> 0x01f8 }
        L_0x087b:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x01f8 }
            if (r4 != 0) goto L_0x088a
            com.google.android.gms.measurement.internal.zzki r4 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "_sn"
            r4.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (java.lang.String) r7, (java.lang.Object) r3)     // Catch:{ all -> 0x01f8 }
        L_0x088a:
            if (r5 == 0) goto L_0x08c3
            java.lang.String r3 = r5.zzd()     // Catch:{ all -> 0x01f8 }
        L_0x0890:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x01f8 }
            if (r4 != 0) goto L_0x089f
            com.google.android.gms.measurement.internal.zzki r4 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = "_sc"
            r4.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (java.lang.String) r5, (java.lang.Object) r3)     // Catch:{ all -> 0x01f8 }
        L_0x089f:
            if (r6 == 0) goto L_0x08b2
            com.google.android.gms.measurement.internal.zzki r3 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "_si"
            long r6 = r6.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01f8 }
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r12, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x01f8 }
        L_0x08b2:
            r16 = 0
            r0 = r24
            r0.zza((int) r15, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            goto L_0x06c8
        L_0x08c0:
            java.lang.String r3 = ""
            goto L_0x087b
        L_0x08c3:
            java.lang.String r3 = ""
            goto L_0x0890
        L_0x08c6:
            com.google.android.gms.measurement.internal.zzki r7 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r12.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r8 = "_et"
            java.lang.Object r2 = r7.zzb(r2, r8)     // Catch:{ all -> 0x01f8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x08fd
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "Engagement event does not include duration. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r8 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r8 = r8.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)     // Catch:{ all -> 0x01f8 }
            r2.zza(r7, r8)     // Catch:{ all -> 0x01f8 }
            r8 = r18
            goto L_0x06fb
        L_0x08fd:
            long r8 = r2.longValue()     // Catch:{ all -> 0x01f8 }
            long r18 = r18 + r8
            r8 = r18
            goto L_0x06fb
        L_0x0907:
            if (r22 == 0) goto L_0x0966
            r2 = 0
            r4 = r18
            r3 = r17
        L_0x090e:
            if (r2 >= r3) goto L_0x0968
            r0 = r24
            com.google.android.gms.internal.measurement.zzbs$zzc r6 = r0.zzb((int) r2)     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "_e"
            java.lang.String r8 = r6.zzc()     // Catch:{ all -> 0x01f8 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x01f8 }
            if (r7 == 0) goto L_0x0939
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "_fr"
            com.google.android.gms.internal.measurement.zzbs$zze r7 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r6, (java.lang.String) r7)     // Catch:{ all -> 0x01f8 }
            if (r7 == 0) goto L_0x0939
            r0 = r24
            r0.zzc((int) r2)     // Catch:{ all -> 0x01f8 }
            int r3 = r3 + -1
            int r2 = r2 + -1
        L_0x0936:
            int r2 = r2 + 1
            goto L_0x090e
        L_0x0939:
            r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "_et"
            com.google.android.gms.internal.measurement.zzbs$zze r6 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzc) r6, (java.lang.String) r7)     // Catch:{ all -> 0x01f8 }
            if (r6 == 0) goto L_0x0936
            boolean r7 = r6.zze()     // Catch:{ all -> 0x01f8 }
            if (r7 == 0) goto L_0x0964
            long r6 = r6.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01f8 }
        L_0x0952:
            if (r6 == 0) goto L_0x0936
            long r8 = r6.longValue()     // Catch:{ all -> 0x01f8 }
            r10 = 0
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x0936
            long r6 = r6.longValue()     // Catch:{ all -> 0x01f8 }
            long r4 = r4 + r6
            goto L_0x0936
        L_0x0964:
            r6 = 0
            goto L_0x0952
        L_0x0966:
            r4 = r18
        L_0x0968:
            r2 = 0
            r0 = r32
            r1 = r24
            r0.zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r1, (long) r4, (boolean) r2)     // Catch:{ all -> 0x01f8 }
            r3 = 0
            java.util.List r2 = r24.zza()     // Catch:{ all -> 0x01f8 }
            java.util.Iterator r6 = r2.iterator()     // Catch:{ all -> 0x01f8 }
        L_0x0979:
            boolean r2 = r6.hasNext()     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x103b
            java.lang.Object r2 = r6.next()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r7 = "_s"
            java.lang.String r2 = r2.zzc()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r7.equals(r2)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0979
            r2 = 1
        L_0x0992:
            if (r2 == 0) goto L_0x09a1
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r24.zzj()     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = "_se"
            r2.zzb((java.lang.String) r3, (java.lang.String) r6)     // Catch:{ all -> 0x01f8 }
        L_0x09a1:
            java.lang.String r2 = "_sid"
            r0 = r24
            int r2 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r0, (java.lang.String) r2)     // Catch:{ all -> 0x01f8 }
            if (r2 < 0) goto L_0x0b63
            r2 = 1
        L_0x09ac:
            if (r2 == 0) goto L_0x0b66
            r2 = 1
            r0 = r32
            r1 = r24
            r0.zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r1, (long) r4, (boolean) r2)     // Catch:{ all -> 0x01f8 }
        L_0x09b6:
            com.google.android.gms.measurement.internal.zzki r2 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r3 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "Checking account type status for ad personalization signals"
            r3.zza(r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzfq r3 = r2.zzj()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r24.zzj()     // Catch:{ all -> 0x01f8 }
            boolean r3 = r3.zze(r4)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0a4b
            com.google.android.gms.measurement.internal.zzad r3 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r24.zzj()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzf r3 = r3.zzb(r4)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0a4b
            boolean r3 = r3.zzaf()     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0a4b
            com.google.android.gms.measurement.internal.zzai r3 = r2.zzl()     // Catch:{ all -> 0x01f8 }
            boolean r3 = r3.zzj()     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0a4b
            com.google.android.gms.measurement.internal.zzes r3 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzw()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "Turning off ad personalization due to account type"
            r3.zza(r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r3 = com.google.android.gms.internal.measurement.zzbs.zzk.zzj()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "_npa"
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r3 = r3.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzai r2 = r2.zzl()     // Catch:{ all -> 0x01f8 }
            long r4 = r2.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r2 = r3.zza((long) r4)     // Catch:{ all -> 0x01f8 }
            r4 = 1
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r2 = r2.zzb((long) r4)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzk r2 = (com.google.android.gms.internal.measurement.zzbs.zzk) r2     // Catch:{ all -> 0x01f8 }
            r3 = 0
            r4 = 0
        L_0x0a26:
            int r5 = r24.zze()     // Catch:{ all -> 0x01f8 }
            if (r4 >= r5) goto L_0x0a44
            java.lang.String r5 = "_npa"
            r0 = r24
            com.google.android.gms.internal.measurement.zzbs$zzk r6 = r0.zzd((int) r4)     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r6.zzc()     // Catch:{ all -> 0x01f8 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x01f8 }
            if (r5 == 0) goto L_0x0b94
            r0 = r24
            r0.zza((int) r4, (com.google.android.gms.internal.measurement.zzbs.zzk) r2)     // Catch:{ all -> 0x01f8 }
            r3 = 1
        L_0x0a44:
            if (r3 != 0) goto L_0x0a4b
            r0 = r24
            r0.zza((com.google.android.gms.internal.measurement.zzbs.zzk) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0a4b:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r24.zzj()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzbn     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zze(r3, r4)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0a62
            zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r24)     // Catch:{ all -> 0x01f8 }
        L_0x0a62:
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r8 = r24.zzm()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzo r2 = r32.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r24.zzj()     // Catch:{ all -> 0x01f8 }
            java.util.List r4 = r24.zza()     // Catch:{ all -> 0x01f8 }
            java.util.List r5 = r24.zzd()     // Catch:{ all -> 0x01f8 }
            long r6 = r24.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01f8 }
            long r10 = r24.zzg()     // Catch:{ all -> 0x01f8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x01f8 }
            java.util.List r2 = r2.zza(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x01f8 }
            r8.zzc((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzbs.zza>) r2)     // Catch:{ all -> 0x01f8 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zzg(r3)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0e4f
            java.util.HashMap r25 = new java.util.HashMap     // Catch:{ all -> 0x01f8 }
            r25.<init>()     // Catch:{ all -> 0x01f8 }
            java.util.ArrayList r26 = new java.util.ArrayList     // Catch:{ all -> 0x01f8 }
            r26.<init>()     // Catch:{ all -> 0x01f8 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzkm r2 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            java.security.SecureRandom r27 = r2.zzh()     // Catch:{ all -> 0x01f8 }
            r2 = 0
            r22 = r2
        L_0x0abc:
            int r2 = r24.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r22
            if (r0 >= r2) goto L_0x0e1a
            r0 = r24
            r1 = r22
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = r0.zzb((int) r1)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = r2.zzbl()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "_ep"
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0b98
            com.google.android.gms.measurement.internal.zzki r4 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = "_en"
            java.lang.Object r3 = r4.zzb(r3, r5)     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x01f8 }
            r0 = r25
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzak r4 = (com.google.android.gms.measurement.internal.zzak) r4     // Catch:{ all -> 0x01f8 }
            if (r4 != 0) goto L_0x0b13
            com.google.android.gms.measurement.internal.zzad r4 = r32.zze()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r5.zzx()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzak r4 = r4.zza((java.lang.String) r5, (java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
            r0 = r25
            r0.put(r3, r4)     // Catch:{ all -> 0x01f8 }
        L_0x0b13:
            java.lang.Long r3 = r4.zzi     // Catch:{ all -> 0x01f8 }
            if (r3 != 0) goto L_0x0b56
            java.lang.Long r3 = r4.zzj     // Catch:{ all -> 0x01f8 }
            long r6 = r3.longValue()     // Catch:{ all -> 0x01f8 }
            r8 = 1
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 <= 0) goto L_0x0b2e
            com.google.android.gms.measurement.internal.zzki r3 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = "_sr"
            java.lang.Long r6 = r4.zzj     // Catch:{ all -> 0x01f8 }
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2, (java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x01f8 }
        L_0x0b2e:
            java.lang.Boolean r3 = r4.zzk     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0b49
            java.lang.Boolean r3 = r4.zzk     // Catch:{ all -> 0x01f8 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0b49
            com.google.android.gms.measurement.internal.zzki r3 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "_efs"
            r6 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01f8 }
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x01f8 }
        L_0x0b49:
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            r0 = r26
            r0.add(r3)     // Catch:{ all -> 0x01f8 }
        L_0x0b56:
            r0 = r24
            r1 = r22
            r0.zza((int) r1, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0b5d:
            int r2 = r22 + 1
            r22 = r2
            goto L_0x0abc
        L_0x0b63:
            r2 = 0
            goto L_0x09ac
        L_0x0b66:
            java.lang.String r2 = "_se"
            r0 = r24
            int r2 = com.google.android.gms.measurement.internal.zzki.zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r0, (java.lang.String) r2)     // Catch:{ all -> 0x01f8 }
            if (r2 < 0) goto L_0x09b6
            r0 = r24
            r0.zze((int) r2)     // Catch:{ all -> 0x01f8 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Session engagement user property is in the bundle without session ID. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x01f8 }
            goto L_0x09b6
        L_0x0b94:
            int r4 = r4 + 1
            goto L_0x0a26
        L_0x0b98:
            com.google.android.gms.measurement.internal.zzfq r3 = r32.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            long r28 = r3.zzf(r4)     // Catch:{ all -> 0x01f8 }
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x01f8 }
            r3.zzi()     // Catch:{ all -> 0x01f8 }
            long r4 = r2.zzf()     // Catch:{ all -> 0x01f8 }
            r0 = r28
            long r30 = com.google.android.gms.measurement.internal.zzkm.zza((long) r4, (long) r0)     // Catch:{ all -> 0x01f8 }
            r4 = 1
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = "_dbg"
            r6 = 1
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01f8 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x01f8 }
            if (r7 != 0) goto L_0x0bd2
            if (r6 != 0) goto L_0x0c1c
        L_0x0bd2:
            r3 = 0
        L_0x0bd3:
            if (r3 != 0) goto L_0x1037
            com.google.android.gms.measurement.internal.zzfq r3 = r32.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            int r3 = r3.zzd(r4, r5)     // Catch:{ all -> 0x01f8 }
            r21 = r3
        L_0x0beb:
            if (r21 > 0) goto L_0x0c75
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r3 = r3.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "Sample rate must be positive. event, rate"
            java.lang.String r5 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r21)     // Catch:{ all -> 0x01f8 }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            r0 = r26
            r0.add(r3)     // Catch:{ all -> 0x01f8 }
            r0 = r24
            r1 = r22
            r0.zza((int) r1, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
            goto L_0x0b5d
        L_0x0c1c:
            java.util.List r3 = r3.zza()     // Catch:{ all -> 0x01f8 }
            java.util.Iterator r7 = r3.iterator()     // Catch:{ all -> 0x01f8 }
        L_0x0c24:
            boolean r3 = r7.hasNext()     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0c72
            java.lang.Object r3 = r7.next()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zze r3 = (com.google.android.gms.internal.measurement.zzbs.zze) r3     // Catch:{ all -> 0x01f8 }
            java.lang.String r8 = r3.zzb()     // Catch:{ all -> 0x01f8 }
            boolean r8 = r5.equals(r8)     // Catch:{ all -> 0x01f8 }
            if (r8 == 0) goto L_0x0c24
            boolean r5 = r6 instanceof java.lang.Long     // Catch:{ all -> 0x01f8 }
            if (r5 == 0) goto L_0x0c4c
            long r8 = r3.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01f8 }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x01f8 }
            if (r5 != 0) goto L_0x0c6c
        L_0x0c4c:
            boolean r5 = r6 instanceof java.lang.String     // Catch:{ all -> 0x01f8 }
            if (r5 == 0) goto L_0x0c5a
            java.lang.String r5 = r3.zzd()     // Catch:{ all -> 0x01f8 }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x01f8 }
            if (r5 != 0) goto L_0x0c6c
        L_0x0c5a:
            boolean r5 = r6 instanceof java.lang.Double     // Catch:{ all -> 0x01f8 }
            if (r5 == 0) goto L_0x0c6f
            double r8 = r3.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.Double r3 = java.lang.Double.valueOf(r8)     // Catch:{ all -> 0x01f8 }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0c6f
        L_0x0c6c:
            r3 = 1
            goto L_0x0bd3
        L_0x0c6f:
            r3 = 0
            goto L_0x0bd3
        L_0x0c72:
            r3 = 0
            goto L_0x0bd3
        L_0x0c75:
            java.lang.String r3 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            r0 = r25
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzak r3 = (com.google.android.gms.measurement.internal.zzak) r3     // Catch:{ all -> 0x01f8 }
            if (r3 != 0) goto L_0x1034
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzak r3 = r3.zza((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ all -> 0x01f8 }
            if (r3 != 0) goto L_0x1034
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r3 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r3 = r3.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "Event being bundled has no eventAggregate. appId, eventName"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r5.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzak r3 = new com.google.android.gms.measurement.internal.zzak     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.String r5 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            r6 = 1
            r8 = 1
            r10 = 1
            long r12 = r2.zzf()     // Catch:{ all -> 0x01f8 }
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r3.<init>(r4, r5, r6, r8, r10, r12, r14, r16, r17, r18, r19)     // Catch:{ all -> 0x01f8 }
            r4 = r3
        L_0x0cdc:
            com.google.android.gms.measurement.internal.zzki r5 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = "_eid"
            java.lang.Object r3 = r5.zzb(r3, r6)     // Catch:{ all -> 0x01f8 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0d34
            r5 = 1
        L_0x0cf3:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x01f8 }
            r6 = 1
            r0 = r21
            if (r0 != r6) goto L_0x0d36
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            r0 = r26
            r0.add(r3)     // Catch:{ all -> 0x01f8 }
            boolean r3 = r5.booleanValue()     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0d2b
            java.lang.Long r3 = r4.zzi     // Catch:{ all -> 0x01f8 }
            if (r3 != 0) goto L_0x0d1b
            java.lang.Long r3 = r4.zzj     // Catch:{ all -> 0x01f8 }
            if (r3 != 0) goto L_0x0d1b
            java.lang.Boolean r3 = r4.zzk     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0d2b
        L_0x0d1b:
            r3 = 0
            r5 = 0
            r6 = 0
            com.google.android.gms.measurement.internal.zzak r3 = r4.zza(r3, r5, r6)     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            r0 = r25
            r0.put(r4, r3)     // Catch:{ all -> 0x01f8 }
        L_0x0d2b:
            r0 = r24
            r1 = r22
            r0.zza((int) r1, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
            goto L_0x0b5d
        L_0x0d34:
            r5 = 0
            goto L_0x0cf3
        L_0x0d36:
            r0 = r27
            r1 = r21
            int r6 = r0.nextInt(r1)     // Catch:{ all -> 0x01f8 }
            if (r6 != 0) goto L_0x0d8c
            com.google.android.gms.measurement.internal.zzki r3 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = "_sr"
            r0 = r21
            long r8 = (long) r0     // Catch:{ all -> 0x01f8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01f8 }
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            r0 = r26
            r0.add(r3)     // Catch:{ all -> 0x01f8 }
            boolean r3 = r5.booleanValue()     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x0d70
            r3 = 0
            r0 = r21
            long r6 = (long) r0     // Catch:{ all -> 0x01f8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01f8 }
            r6 = 0
            com.google.android.gms.measurement.internal.zzak r4 = r4.zza(r3, r5, r6)     // Catch:{ all -> 0x01f8 }
        L_0x0d70:
            java.lang.String r3 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            long r6 = r2.zzf()     // Catch:{ all -> 0x01f8 }
            r0 = r30
            com.google.android.gms.measurement.internal.zzak r4 = r4.zza(r6, r0)     // Catch:{ all -> 0x01f8 }
            r0 = r25
            r0.put(r3, r4)     // Catch:{ all -> 0x01f8 }
        L_0x0d83:
            r0 = r24
            r1 = r22
            r0.zza((int) r1, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x01f8 }
            goto L_0x0b5d
        L_0x0d8c:
            java.lang.Long r6 = r4.zzh     // Catch:{ all -> 0x01f8 }
            if (r6 == 0) goto L_0x0df1
            java.lang.Long r6 = r4.zzh     // Catch:{ all -> 0x01f8 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x01f8 }
        L_0x0d96:
            int r6 = (r6 > r30 ? 1 : (r6 == r30 ? 0 : -1))
            if (r6 == 0) goto L_0x0e03
            com.google.android.gms.measurement.internal.zzki r3 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = "_efs"
            r8 = 1
            java.lang.Long r7 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01f8 }
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzki r3 = r32.zzh()     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = "_sr"
            r0 = r21
            long r8 = (long) r0     // Catch:{ all -> 0x01f8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01f8 }
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r3 = r2.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzc r3 = (com.google.android.gms.internal.measurement.zzbs.zzc) r3     // Catch:{ all -> 0x01f8 }
            r0 = r26
            r0.add(r3)     // Catch:{ all -> 0x01f8 }
            boolean r3 = r5.booleanValue()     // Catch:{ all -> 0x01f8 }
            if (r3 == 0) goto L_0x1031
            r3 = 0
            r0 = r21
            long r6 = (long) r0     // Catch:{ all -> 0x01f8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01f8 }
            r6 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzak r3 = r4.zza(r3, r5, r6)     // Catch:{ all -> 0x01f8 }
        L_0x0ddd:
            java.lang.String r4 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            long r6 = r2.zzf()     // Catch:{ all -> 0x01f8 }
            r0 = r30
            com.google.android.gms.measurement.internal.zzak r3 = r3.zza(r6, r0)     // Catch:{ all -> 0x01f8 }
            r0 = r25
            r0.put(r4, r3)     // Catch:{ all -> 0x01f8 }
            goto L_0x0d83
        L_0x0df1:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r6 = r0.zzj     // Catch:{ all -> 0x01f8 }
            r6.zzi()     // Catch:{ all -> 0x01f8 }
            long r6 = r2.zzg()     // Catch:{ all -> 0x01f8 }
            r0 = r28
            long r6 = com.google.android.gms.measurement.internal.zzkm.zza((long) r6, (long) r0)     // Catch:{ all -> 0x01f8 }
            goto L_0x0d96
        L_0x0e03:
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x01f8 }
            if (r5 == 0) goto L_0x0d83
            java.lang.String r5 = r2.zzd()     // Catch:{ all -> 0x01f8 }
            r6 = 0
            r7 = 0
            com.google.android.gms.measurement.internal.zzak r3 = r4.zza(r3, r6, r7)     // Catch:{ all -> 0x01f8 }
            r0 = r25
            r0.put(r5, r3)     // Catch:{ all -> 0x01f8 }
            goto L_0x0d83
        L_0x0e1a:
            int r2 = r26.size()     // Catch:{ all -> 0x01f8 }
            int r3 = r24.zzb()     // Catch:{ all -> 0x01f8 }
            if (r2 >= r3) goto L_0x0e2d
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r24.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r26
            r2.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzbs.zzc>) r0)     // Catch:{ all -> 0x01f8 }
        L_0x0e2d:
            java.util.Set r2 = r25.entrySet()     // Catch:{ all -> 0x01f8 }
            java.util.Iterator r3 = r2.iterator()     // Catch:{ all -> 0x01f8 }
        L_0x0e35:
            boolean r2 = r3.hasNext()     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0e4f
            java.lang.Object r2 = r3.next()     // Catch:{ all -> 0x01f8 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzad r4 = r32.zze()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzak r2 = (com.google.android.gms.measurement.internal.zzak) r2     // Catch:{ all -> 0x01f8 }
            r4.zza((com.google.android.gms.measurement.internal.zzak) r2)     // Catch:{ all -> 0x01f8 }
            goto L_0x0e35
        L_0x0e4f:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r24.zzj()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzbn     // Catch:{ all -> 0x01f8 }
            boolean r2 = r2.zze(r3, r4)     // Catch:{ all -> 0x01f8 }
            if (r2 != 0) goto L_0x0e66
            zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r24)     // Catch:{ all -> 0x01f8 }
        L_0x0e66:
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r2 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r2.zzx()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzf r7 = r2.zzb(r6)     // Catch:{ all -> 0x01f8 }
            if (r7 != 0) goto L_0x0f18
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Bundling raw events w/o app info. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x01f8 }
        L_0x0e95:
            int r2 = r24.zzb()     // Catch:{ all -> 0x01f8 }
            if (r2 <= 0) goto L_0x0ee0
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            r2.zzu()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzfq r2 = r32.zzc()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbq$zzb r2 = r2.zza((java.lang.String) r3)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0eba
            boolean r3 = r2.zza()     // Catch:{ all -> 0x01f8 }
            if (r3 != 0) goto L_0x0f9c
        L_0x0eba:
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r2 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r2 = r2.zzam()     // Catch:{ all -> 0x01f8 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0f7d
            r2 = -1
            r0 = r24
            r0.zzi((long) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0ecf:
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzgm r2 = r24.zzv()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.internal.measurement.zzbs$zzg r2 = (com.google.android.gms.internal.measurement.zzbs.zzg) r2     // Catch:{ all -> 0x01f8 }
            r0 = r20
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzg) r2, (boolean) r0)     // Catch:{ all -> 0x01f8 }
        L_0x0ee0:
            com.google.android.gms.measurement.internal.zzad r4 = r32.zze()     // Catch:{ all -> 0x01f8 }
            r0 = r23
            java.util.List<java.lang.Long> r5 = r0.zzb     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x01f8 }
            r4.zzd()     // Catch:{ all -> 0x01f8 }
            r4.zzak()     // Catch:{ all -> 0x01f8 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01f8 }
            java.lang.String r2 = "rowid in ("
            r7.<init>(r2)     // Catch:{ all -> 0x01f8 }
            r2 = 0
            r3 = r2
        L_0x0efa:
            int r2 = r5.size()     // Catch:{ all -> 0x01f8 }
            if (r3 >= r2) goto L_0x0fa7
            if (r3 == 0) goto L_0x0f07
            java.lang.String r2 = ","
            r7.append(r2)     // Catch:{ all -> 0x01f8 }
        L_0x0f07:
            java.lang.Object r2 = r5.get(r3)     // Catch:{ all -> 0x01f8 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x01f8 }
            long r8 = r2.longValue()     // Catch:{ all -> 0x01f8 }
            r7.append(r8)     // Catch:{ all -> 0x01f8 }
            int r2 = r3 + 1
            r3 = r2
            goto L_0x0efa
        L_0x0f18:
            int r2 = r24.zzb()     // Catch:{ all -> 0x01f8 }
            if (r2 <= 0) goto L_0x0e95
            long r2 = r7.zzk()     // Catch:{ all -> 0x01f8 }
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0f71
            r0 = r24
            r0.zze((long) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0f2d:
            long r4 = r7.zzj()     // Catch:{ all -> 0x01f8 }
            r8 = 0
            int r8 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x102e
        L_0x0f37:
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0f75
            r0 = r24
            r0.zzd((long) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0f42:
            r7.zzv()     // Catch:{ all -> 0x01f8 }
            long r2 = r7.zzs()     // Catch:{ all -> 0x01f8 }
            int r2 = (int) r2     // Catch:{ all -> 0x01f8 }
            r0 = r24
            r0.zzg((int) r2)     // Catch:{ all -> 0x01f8 }
            long r2 = r24.zzf()     // Catch:{ all -> 0x01f8 }
            r7.zza((long) r2)     // Catch:{ all -> 0x01f8 }
            long r2 = r24.zzg()     // Catch:{ all -> 0x01f8 }
            r7.zzb((long) r2)     // Catch:{ all -> 0x01f8 }
            java.lang.String r2 = r7.zzad()     // Catch:{ all -> 0x01f8 }
            if (r2 == 0) goto L_0x0f79
            r0 = r24
            r0.zzj((java.lang.String) r2)     // Catch:{ all -> 0x01f8 }
        L_0x0f68:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x01f8 }
            r2.zza((com.google.android.gms.measurement.internal.zzf) r7)     // Catch:{ all -> 0x01f8 }
            goto L_0x0e95
        L_0x0f71:
            r24.zzi()     // Catch:{ all -> 0x01f8 }
            goto L_0x0f2d
        L_0x0f75:
            r24.zzh()     // Catch:{ all -> 0x01f8 }
            goto L_0x0f42
        L_0x0f79:
            r24.zzk()     // Catch:{ all -> 0x01f8 }
            goto L_0x0f68
        L_0x0f7d:
            r0 = r32
            com.google.android.gms.measurement.internal.zzfw r2 = r0.zzj     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "Did not find measurement config or missing version info. appId"
            r0 = r23
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r0.zza     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x01f8 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x01f8 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x01f8 }
            goto L_0x0ecf
        L_0x0f9c:
            long r2 = r2.zzb()     // Catch:{ all -> 0x01f8 }
            r0 = r24
            r0.zzi((long) r2)     // Catch:{ all -> 0x01f8 }
            goto L_0x0ecf
        L_0x0fa7:
            java.lang.String r2 = ")"
            r7.append(r2)     // Catch:{ all -> 0x01f8 }
            android.database.sqlite.SQLiteDatabase r2 = r4.mo24238c_()     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = "raw_events"
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01f8 }
            r8 = 0
            int r2 = r2.delete(r3, r7, r8)     // Catch:{ all -> 0x01f8 }
            int r3 = r5.size()     // Catch:{ all -> 0x01f8 }
            if (r2 == r3) goto L_0x0fda
            com.google.android.gms.measurement.internal.zzes r3 = r4.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x01f8 }
            int r5 = r5.size()     // Catch:{ all -> 0x01f8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x01f8 }
            r3.zza(r4, r2, r5)     // Catch:{ all -> 0x01f8 }
        L_0x0fda:
            com.google.android.gms.measurement.internal.zzad r3 = r32.zze()     // Catch:{ all -> 0x01f8 }
            android.database.sqlite.SQLiteDatabase r2 = r3.mo24238c_()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x1000 }
            r7 = 0
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x1000 }
            r7 = 1
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x1000 }
            r2.execSQL(r4, r5)     // Catch:{ SQLiteException -> 0x1000 }
        L_0x0ff0:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x01f8 }
            r2.mo24237b_()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            r2 = 1
        L_0x0fff:
            return r2
        L_0x1000:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzes r3 = r3.zzr()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x01f8 }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r6)     // Catch:{ all -> 0x01f8 }
            r3.zza(r4, r5, r2)     // Catch:{ all -> 0x01f8 }
            goto L_0x0ff0
        L_0x1013:
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()     // Catch:{ all -> 0x01f8 }
            r2.mo24237b_()     // Catch:{ all -> 0x01f8 }
            com.google.android.gms.measurement.internal.zzad r2 = r32.zze()
            r2.zzh()
            r2 = 0
            goto L_0x0fff
        L_0x1023:
            r2 = move-exception
            r3 = r11
            goto L_0x0395
        L_0x1027:
            r2 = move-exception
            goto L_0x0321
        L_0x102a:
            r2 = move-exception
            r4 = r12
            goto L_0x0321
        L_0x102e:
            r2 = r4
            goto L_0x0f37
        L_0x1031:
            r3 = r4
            goto L_0x0ddd
        L_0x1034:
            r4 = r3
            goto L_0x0cdc
        L_0x1037:
            r21 = r4
            goto L_0x0beb
        L_0x103b:
            r2 = r3
            goto L_0x0992
        L_0x103e:
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            goto L_0x06c8
        L_0x1045:
            r2 = r4
            goto L_0x071b
        L_0x1048:
            r2 = r3
            goto L_0x05c8
        L_0x104b:
            r20 = r2
            goto L_0x0504
        L_0x104f:
            r2 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r8 = r18
            r7 = r17
            r10 = r20
            goto L_0x0189
        L_0x105c:
            r8 = r18
            goto L_0x06fb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzka.zza(java.lang.String, long):boolean");
    }

    private static void zza(zzbs.zzg.zza zza2) {
        zza2.zzb(Long.MAX_VALUE).zzc(Long.MIN_VALUE);
        for (int i = 0; i < zza2.zzb(); i++) {
            zzbs.zzc zzb2 = zza2.zzb(i);
            if (zzb2.zze() < zza2.zzf()) {
                zza2.zzb(zzb2.zze());
            }
            if (zzb2.zze() > zza2.zzg()) {
                zza2.zzc(zzb2.zze());
            }
        }
    }

    @VisibleForTesting
    private final void zza(zzbs.zzg.zza zza2, long j, boolean z) {
        zzkj zzkj;
        String str = "_lte";
        if (z) {
            str = "_se";
        }
        zzkj zzc2 = zze().zzc(zza2.zzj(), str);
        if (zzc2 == null || zzc2.zze == null) {
            zzkj = new zzkj(zza2.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzkj = new zzkj(zza2.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(((Long) zzc2.zze).longValue() + j));
        }
        zzbs.zzk zzk2 = (zzbs.zzk) ((zzfe) zzbs.zzk.zzj().zza(str).zza(this.zzj.zzm().currentTimeMillis()).zzb(((Long) zzkj.zze).longValue()).zzv());
        boolean z2 = false;
        int zza3 = zzki.zza(zza2, str);
        if (zza3 >= 0) {
            zza2.zza(zza3, zzk2);
            z2 = true;
        }
        if (!z2) {
            zza2.zza(zzk2);
        }
        if (j > 0) {
            zze().zza(zzkj);
            String str2 = "lifetime";
            if (z) {
                str2 = "session-scoped";
            }
            this.zzj.zzr().zzx().zza("Updated engagement user property. scope, value", str2, zzkj.zze);
        }
    }

    private final boolean zza(zzbs.zzc.zza zza2, zzbs.zzc.zza zza3) {
        String str = null;
        Preconditions.checkArgument("_e".equals(zza2.zzd()));
        zzh();
        zzbs.zze zza4 = zzki.zza((zzbs.zzc) ((zzfe) zza2.zzv()), "_sc");
        String zzd2 = zza4 == null ? null : zza4.zzd();
        zzh();
        zzbs.zze zza5 = zzki.zza((zzbs.zzc) ((zzfe) zza3.zzv()), "_pc");
        if (zza5 != null) {
            str = zza5.zzd();
        }
        if (str == null || !str.equals(zzd2)) {
            return false;
        }
        zzb(zza2, zza3);
        return true;
    }

    private final void zzb(zzbs.zzc.zza zza2, zzbs.zzc.zza zza3) {
        long j;
        Preconditions.checkArgument("_e".equals(zza2.zzd()));
        zzh();
        zzbs.zze zza4 = zzki.zza((zzbs.zzc) ((zzfe) zza2.zzv()), "_et");
        if (zza4.zze() && zza4.zzf() > 0) {
            long zzf2 = zza4.zzf();
            zzh();
            zzbs.zze zza5 = zzki.zza((zzbs.zzc) ((zzfe) zza3.zzv()), "_et");
            if (zza5 == null || zza5.zzf() <= 0) {
                j = zzf2;
            } else {
                j = zza5.zzf() + zzf2;
            }
            zzh().zza(zza3, "_et", (Object) Long.valueOf(j));
            zzh().zza(zza2, "_fr", (Object) 1L);
        }
    }

    @VisibleForTesting
    private static void zza(zzbs.zzc.zza zza2, @NonNull String str) {
        List<zzbs.zze> zza3 = zza2.zza();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= zza3.size()) {
                return;
            }
            if (str.equals(zza3.get(i2).zzb())) {
                zza2.zzb(i2);
                return;
            }
            i = i2 + 1;
        }
    }

    @VisibleForTesting
    private static void zza(zzbs.zzc.zza zza2, int i, String str) {
        List<zzbs.zze> zza3 = zza2.zza();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= zza3.size()) {
                zza2.zza((zzbs.zze) ((zzfe) zzbs.zze.zzk().zza("_err").zza(Long.valueOf((long) i).longValue()).zzv())).zza((zzbs.zze) ((zzfe) zzbs.zze.zzk().zza("_ev").zzb(str).zzv()));
                return;
            } else if (!"_err".equals(zza3.get(i3).zzb())) {
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzad zze2;
        zzw();
        zzk();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzr = false;
                zzaa();
                throw th2;
            }
        }
        List<Long> list = this.zzv;
        this.zzv = null;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
                this.zzj.zzc().zzd.zza(0);
                zzz();
                this.zzj.zzr().zzx().zza("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zze().zzf();
                try {
                    for (Long next : list) {
                        try {
                            zze2 = zze();
                            long longValue = next.longValue();
                            zze2.zzd();
                            zze2.zzak();
                            if (zze2.mo24238c_().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zze2.zzr().zzf().zza("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzw == null || !this.zzw.contains(next)) {
                                throw e2;
                            }
                        }
                    }
                    zze().mo24237b_();
                    zze().zzh();
                    this.zzw = null;
                    if (!zzd().zzf() || !zzy()) {
                        this.zzx = -1;
                        zzz();
                    } else {
                        zzl();
                    }
                    this.zzm = 0;
                } catch (Throwable th3) {
                    zze().zzh();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzj.zzr().zzf().zza("Database error while trying to delete uploaded bundles", e3);
                this.zzm = this.zzj.zzm().elapsedRealtime();
                this.zzj.zzr().zzx().zza("Disable upload, time", Long.valueOf(this.zzm));
            }
        } else {
            this.zzj.zzr().zzx().zza("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzj.zzc().zzd.zza(this.zzj.zzm().currentTimeMillis());
            if (i == 503 || i == 429) {
                this.zzj.zzc().zze.zza(this.zzj.zzm().currentTimeMillis());
            }
            zze().zza(list);
            zzz();
        }
        this.zzr = false;
        zzaa();
    }

    private final boolean zzy() {
        zzw();
        zzk();
        return zze().zzy() || !TextUtils.isEmpty(zze().mo24239d_());
    }

    @WorkerThread
    private final void zza(zzf zzf2) {
        ArrayMap arrayMap;
        zzw();
        if (!zzlb.zzb() || !this.zzj.zzb().zze(zzf2.zzc(), zzaq.zzbo)) {
            if (TextUtils.isEmpty(zzf2.zze()) && TextUtils.isEmpty(zzf2.zzf())) {
                zza(zzf2.zzc(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
                return;
            }
        } else if (TextUtils.isEmpty(zzf2.zze()) && TextUtils.isEmpty(zzf2.zzg()) && TextUtils.isEmpty(zzf2.zzf())) {
            zza(zzf2.zzc(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
            return;
        }
        String zza2 = this.zzj.zzb().zza(zzf2);
        try {
            URL url = new URL(zza2);
            this.zzj.zzr().zzx().zza("Fetching remote configuration", zzf2.zzc());
            zzbq.zzb zza3 = zzc().zza(zzf2.zzc());
            String zzb2 = zzc().zzb(zzf2.zzc());
            if (zza3 == null || TextUtils.isEmpty(zzb2)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put(HttpHeaders.HEAD_KEY_IF_MODIFIED_SINCE, zzb2);
                arrayMap = arrayMap2;
            }
            this.zzq = true;
            zzez zzd2 = zzd();
            String zzc2 = zzf2.zzc();
            zzkf zzkf = new zzkf(this);
            zzd2.zzd();
            zzd2.zzak();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkf);
            zzd2.zzq().zzb((Runnable) new zzfd(zzd2, zzc2, url, (byte[]) null, arrayMap, zzkf));
        } catch (MalformedURLException e) {
            this.zzj.zzr().zzf().zza("Failed to parse config URL. Not fetching. appId", zzes.zza(zzf2.zzc()), zza2);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        String str2;
        boolean z = true;
        zzw();
        zzk();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzq = false;
                zzaa();
                throw th2;
            }
        }
        this.zzj.zzr().zzx().zza("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zze().zzf();
        zzf zzb2 = zze().zzb(str);
        boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
        if (zzb2 == null) {
            this.zzj.zzr().zzi().zza("App does not exist in onConfigFetched. appId", zzes.zza(str));
        } else if (z2 || i == 404) {
            List list = map != null ? map.get(HttpHeaders.HEAD_KEY_LAST_MODIFIED) : null;
            if (list == null || list.size() <= 0) {
                str2 = null;
            } else {
                str2 = (String) list.get(0);
            }
            if (i == 404 || i == 304) {
                if (zzc().zza(str) == null && !zzc().zza(str, (byte[]) null, (String) null)) {
                    zze().zzh();
                    this.zzq = false;
                    zzaa();
                    return;
                }
            } else if (!zzc().zza(str, bArr, str2)) {
                zze().zzh();
                this.zzq = false;
                zzaa();
                return;
            }
            zzb2.zzh(this.zzj.zzm().currentTimeMillis());
            zze().zza(zzb2);
            if (i == 404) {
                this.zzj.zzr().zzk().zza("Config not found. Using empty config. appId", str);
            } else {
                this.zzj.zzr().zzx().zza("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            }
            if (!zzd().zzf() || !zzy()) {
                zzz();
            } else {
                zzl();
            }
        } else {
            zzb2.zzi(this.zzj.zzm().currentTimeMillis());
            zze().zza(zzb2);
            this.zzj.zzr().zzx().zza("Fetching config failed. code, error", Integer.valueOf(i), th);
            zzc().zzc(str);
            this.zzj.zzc().zzd.zza(this.zzj.zzm().currentTimeMillis());
            if (!(i == 503 || i == 429)) {
                z = false;
            }
            if (z) {
                this.zzj.zzc().zze.zza(this.zzj.zzm().currentTimeMillis());
            }
            zzz();
        }
        zze().mo24237b_();
        zze().zzh();
        this.zzq = false;
        zzaa();
    }

    @WorkerThread
    private final void zzz() {
        long max;
        long j;
        long j2;
        zzw();
        zzk();
        if (this.zzm > 0) {
            long abs = 3600000 - Math.abs(this.zzj.zzm().elapsedRealtime() - this.zzm);
            if (abs > 0) {
                this.zzj.zzr().zzx().zza("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                zzt().zzb();
                zzv().zzf();
                return;
            }
            this.zzm = 0;
        }
        if (!this.zzj.zzag() || !zzy()) {
            this.zzj.zzr().zzx().zza("Nothing to upload or uploading impossible");
            zzt().zzb();
            zzv().zzf();
            return;
        }
        long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
        long max2 = Math.max(0, zzaq.zzz.zza(null).longValue());
        boolean z = zze().zzz() || zze().zzk();
        if (z) {
            String zzw2 = this.zzj.zzb().zzw();
            if (TextUtils.isEmpty(zzw2) || ".none.".equals(zzw2)) {
                max = Math.max(0, zzaq.zzt.zza(null).longValue());
            } else {
                max = Math.max(0, zzaq.zzu.zza(null).longValue());
            }
        } else {
            max = Math.max(0, zzaq.zzs.zza(null).longValue());
        }
        long zza2 = this.zzj.zzc().zzc.zza();
        long zza3 = this.zzj.zzc().zzd.zza();
        long max3 = Math.max(zze().zzw(), zze().zzx());
        if (max3 == 0) {
            j = 0;
        } else {
            long abs2 = currentTimeMillis - Math.abs(max3 - currentTimeMillis);
            long abs3 = currentTimeMillis - Math.abs(zza3 - currentTimeMillis);
            long max4 = Math.max(currentTimeMillis - Math.abs(zza2 - currentTimeMillis), abs3);
            j = abs2 + max2;
            if (z && max4 > 0) {
                j = Math.min(abs2, max4) + max;
            }
            if (!zzh().zza(max4, max)) {
                j = max4 + max;
            }
            if (abs3 != 0 && abs3 >= abs2) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= Math.min(20, Math.max(0, zzaq.zzab.zza(null).intValue()))) {
                        j = 0;
                        break;
                    }
                    j += (1 << i2) * Math.max(0, zzaq.zzaa.zza(null).longValue());
                    if (j > abs3) {
                        break;
                    }
                    i = i2 + 1;
                }
            }
        }
        if (j == 0) {
            this.zzj.zzr().zzx().zza("Next upload time is 0");
            zzt().zzb();
            zzv().zzf();
        } else if (!zzd().zzf()) {
            this.zzj.zzr().zzx().zza("No network");
            zzt().zza();
            zzv().zzf();
        } else {
            long zza4 = this.zzj.zzc().zze.zza();
            long max5 = Math.max(0, zzaq.zzq.zza(null).longValue());
            if (!zzh().zza(zza4, max5)) {
                j2 = Math.max(j, max5 + zza4);
            } else {
                j2 = j;
            }
            zzt().zzb();
            long currentTimeMillis2 = j2 - this.zzj.zzm().currentTimeMillis();
            if (currentTimeMillis2 <= 0) {
                currentTimeMillis2 = Math.max(0, zzaq.zzv.zza(null).longValue());
                this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
            }
            this.zzj.zzr().zzx().zza("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
            zzv().zza(currentTimeMillis2);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(Runnable runnable) {
        zzw();
        if (this.zzn == null) {
            this.zzn = new ArrayList();
        }
        this.zzn.add(runnable);
    }

    @WorkerThread
    private final void zzaa() {
        zzw();
        if (this.zzq || this.zzr || this.zzs) {
            this.zzj.zzr().zzx().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzq), Boolean.valueOf(this.zzr), Boolean.valueOf(this.zzs));
            return;
        }
        this.zzj.zzr().zzx().zza("Stopping uploading service(s)");
        if (this.zzn != null) {
            for (Runnable run : this.zzn) {
                run.run();
            }
            this.zzn.clear();
        }
    }

    @WorkerThread
    private final Boolean zzb(zzf zzf2) {
        try {
            if (zzf2.zzm() != -2147483648L) {
                if (zzf2.zzm() == ((long) Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzf2.zzc(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzf2.zzc(), 0).versionName;
                if (zzf2.zzl() != null && zzf2.zzl().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zzo() {
        zzw();
        zzk();
        if (!this.zzl) {
            this.zzl = true;
            if (zzab()) {
                int zza2 = zza(this.zzu);
                int zzaf = this.zzj.zzy().zzaf();
                zzw();
                if (zza2 > zzaf) {
                    this.zzj.zzr().zzf().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                } else if (zza2 >= zzaf) {
                } else {
                    if (zza(zzaf, this.zzu)) {
                        this.zzj.zzr().zzx().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                    } else {
                        this.zzj.zzr().zzf().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                    }
                }
            }
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzab() {
        zzw();
        if (!this.zzj.zzb().zza(zzaq.zzbm) || this.zzt == null || !this.zzt.isValid()) {
            try {
                this.zzu = new RandomAccessFile(new File(this.zzj.zzn().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                this.zzt = this.zzu.tryLock();
                if (this.zzt != null) {
                    this.zzj.zzr().zzx().zza("Storage concurrent access okay");
                    return true;
                }
                this.zzj.zzr().zzf().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                this.zzj.zzr().zzf().zza("Failed to acquire storage lock", e);
            } catch (IOException e2) {
                this.zzj.zzr().zzf().zza("Failed to access storage lock file", e2);
            } catch (OverlappingFileLockException e3) {
                this.zzj.zzr().zzi().zza("Storage lock already acquired", e3);
            }
        } else {
            this.zzj.zzr().zzx().zza("Storage concurrent access okay");
            return true;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzw();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read == 4) {
                allocate.flip();
                return allocate.getInt();
            } else if (read == -1) {
                return 0;
            } else {
                this.zzj.zzr().zzi().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                return 0;
            }
        } catch (IOException e) {
            this.zzj.zzr().zzf().zza("Failed to read from channel", e);
            return 0;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzw();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            if (this.zzj.zzb().zza(zzaq.zzbz) && Build.VERSION.SDK_INT <= 19) {
                fileChannel.position(0);
            }
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            this.zzj.zzr().zzf().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            this.zzj.zzr().zzf().zza("Failed to write to channel", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzn zzn2) {
        if (this.zzv != null) {
            this.zzw = new ArrayList();
            this.zzw.addAll(this.zzv);
        }
        zzad zze2 = zze();
        String str = zzn2.zza;
        Preconditions.checkNotEmpty(str);
        zze2.zzd();
        zze2.zzak();
        try {
            SQLiteDatabase c_ = zze2.mo24238c_();
            String[] strArr = {str};
            int delete = c_.delete("main_event_params", "app_id=?", strArr) + c_.delete("apps", "app_id=?", strArr) + 0 + c_.delete("events", "app_id=?", strArr) + c_.delete("user_attributes", "app_id=?", strArr) + c_.delete("conditional_properties", "app_id=?", strArr) + c_.delete("raw_events", "app_id=?", strArr) + c_.delete("raw_events_metadata", "app_id=?", strArr) + c_.delete("queue", "app_id=?", strArr) + c_.delete("audience_filter_values", "app_id=?", strArr);
            if (delete > 0) {
                zze2.zzr().zzx().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zze2.zzr().zzf().zza("Error resetting analytics data. appId, error", zzes.zza(str), e);
        }
        if (zzn2.zzh) {
            zzb(zzn2);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(zzkh zzkh, zzn zzn2) {
        int i = 0;
        zzw();
        zzk();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
                return;
            }
            int zzc2 = this.zzj.zzi().zzc(zzkh.zza);
            if (zzc2 != 0) {
                this.zzj.zzi();
                String zza2 = zzkm.zza(zzkh.zza, 24, true);
                if (zzkh.zza != null) {
                    i = zzkh.zza.length();
                }
                this.zzj.zzi().zza(zzn2.zza, zzc2, "_ev", zza2, i);
                return;
            }
            int zzb2 = this.zzj.zzi().zzb(zzkh.zza, zzkh.zza());
            if (zzb2 != 0) {
                this.zzj.zzi();
                String zza3 = zzkm.zza(zzkh.zza, 24, true);
                Object zza4 = zzkh.zza();
                if (zza4 != null && ((zza4 instanceof String) || (zza4 instanceof CharSequence))) {
                    i = String.valueOf(zza4).length();
                }
                this.zzj.zzi().zza(zzn2.zza, zzb2, "_ev", zza3, i);
                return;
            }
            Object zzc3 = this.zzj.zzi().zzc(zzkh.zza, zzkh.zza());
            if (zzc3 != null) {
                if ("_sid".equals(zzkh.zza)) {
                    long j = zzkh.zzb;
                    String str = zzkh.zze;
                    long j2 = 0;
                    zzkj zzc4 = zze().zzc(zzn2.zza, "_sno");
                    if (zzc4 == null || !(zzc4.zze instanceof Long)) {
                        if (zzc4 != null) {
                            this.zzj.zzr().zzi().zza("Retrieved last session number from database does not contain a valid (long) value", zzc4.zze);
                        }
                        zzak zza5 = zze().zza(zzn2.zza, "_s");
                        if (zza5 != null) {
                            j2 = zza5.zzc;
                            this.zzj.zzr().zzx().zza("Backfill the session number. Last used session number", Long.valueOf(j2));
                        }
                    } else {
                        j2 = ((Long) zzc4.zze).longValue();
                    }
                    zza(new zzkh("_sno", j, Long.valueOf(1 + j2), str), zzn2);
                }
                zzkj zzkj = new zzkj(zzn2.zza, zzkh.zze, zzkh.zza, zzkh.zzb, zzc3);
                this.zzj.zzr().zzx().zza("Setting user property", this.zzj.zzj().zzc(zzkj.zzc), zzc3);
                zze().zzf();
                try {
                    zzc(zzn2);
                    boolean zza6 = zze().zza(zzkj);
                    zze().mo24237b_();
                    if (!zza6) {
                        this.zzj.zzr().zzf().zza("Too many unique user properties are set. Ignoring user property", this.zzj.zzj().zzc(zzkj.zzc), zzkj.zze);
                        this.zzj.zzi().zza(zzn2.zza, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zze().zzh();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzkh zzkh, zzn zzn2) {
        zzw();
        zzk();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
            } else if (!"_npa".equals(zzkh.zza) || zzn2.zzs == null) {
                this.zzj.zzr().zzw().zza("Removing user property", this.zzj.zzj().zzc(zzkh.zza));
                zze().zzf();
                try {
                    zzc(zzn2);
                    zze().zzb(zzn2.zza, zzkh.zza);
                    zze().mo24237b_();
                    this.zzj.zzr().zzw().zza("User property removed", this.zzj.zzj().zzc(zzkh.zza));
                } finally {
                    zze().zzh();
                }
            } else {
                this.zzj.zzr().zzw().zza("Falling back to manifest metadata value for ad personalization");
                zza(new zzkh("_npa", this.zzj.zzm().currentTimeMillis(), Long.valueOf(zzn2.zzs.booleanValue() ? 1 : 0), "auto"), zzn2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzkb zzkb) {
        this.zzo++;
    }

    /* access modifiers changed from: package-private */
    public final void zzp() {
        this.zzp++;
    }

    /* access modifiers changed from: package-private */
    public final zzfw zzs() {
        return this.zzj;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0374 A[Catch:{ SQLiteException -> 0x0359, all -> 0x0350 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x04ac A[Catch:{ SQLiteException -> 0x0359, all -> 0x0350 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0194 A[SYNTHETIC, Splitter:B:47:0x0194] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01f2 A[Catch:{ SQLiteException -> 0x0359, all -> 0x0350 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0200 A[Catch:{ SQLiteException -> 0x0359, all -> 0x0350 }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.measurement.internal.zzn r13) {
        /*
            r12 = this;
            r12.zzw()
            r12.zzk()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            java.lang.String r0 = r13.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            boolean r0 = r12.zze(r13)
            if (r0 != 0) goto L_0x0015
        L_0x0014:
            return
        L_0x0015:
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()
            java.lang.String r1 = r13.zza
            com.google.android.gms.measurement.internal.zzf r0 = r0.zzb(r1)
            if (r0 == 0) goto L_0x0048
            java.lang.String r1 = r0.zze()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0048
            java.lang.String r1 = r13.zzb
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0048
            r2 = 0
            r0.zzh((long) r2)
            com.google.android.gms.measurement.internal.zzad r1 = r12.zze()
            r1.zza((com.google.android.gms.measurement.internal.zzf) r0)
            com.google.android.gms.measurement.internal.zzfq r0 = r12.zzc()
            java.lang.String r1 = r13.zza
            r0.zzd(r1)
        L_0x0048:
            boolean r0 = r13.zzh
            if (r0 != 0) goto L_0x0050
            r12.zzc(r13)
            goto L_0x0014
        L_0x0050:
            long r2 = r13.zzm
            r0 = 0
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0062
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj
            com.google.android.gms.common.util.Clock r0 = r0.zzm()
            long r2 = r0.currentTimeMillis()
        L_0x0062:
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj
            com.google.android.gms.measurement.internal.zzai r0 = r0.zzx()
            r0.zzi()
            int r0 = r13.zzn
            if (r0 == 0) goto L_0x04cf
            r1 = 1
            if (r0 == r1) goto L_0x04cf
            com.google.android.gms.measurement.internal.zzfw r1 = r12.zzj
            com.google.android.gms.measurement.internal.zzes r1 = r1.zzr()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzi()
            java.lang.String r4 = "Incorrect app type, assuming installed app. appId, appType"
            java.lang.String r5 = r13.zza
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r5)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.zza(r4, r5, r0)
            r0 = 0
            r10 = r0
        L_0x008d:
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()
            r0.zzf()
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = "_npa"
            com.google.android.gms.measurement.internal.zzkj r6 = r0.zzc(r1, r4)     // Catch:{ all -> 0x0350 }
            if (r6 == 0) goto L_0x00ac
            java.lang.String r0 = "auto"
            java.lang.String r1 = r6.zzb     // Catch:{ all -> 0x0350 }
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x00d6
        L_0x00ac:
            java.lang.Boolean r0 = r13.zzs     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x033f
            com.google.android.gms.measurement.internal.zzkh r0 = new com.google.android.gms.measurement.internal.zzkh     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_npa"
            java.lang.Boolean r4 = r13.zzs     // Catch:{ all -> 0x0350 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0350 }
            if (r4 == 0) goto L_0x033b
            r4 = 1
        L_0x00be:
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "auto"
            r0.<init>(r1, r2, r4, r5)     // Catch:{ all -> 0x0350 }
            if (r6 == 0) goto L_0x00d3
            java.lang.Object r1 = r6.zze     // Catch:{ all -> 0x0350 }
            java.lang.Long r4 = r0.zzc     // Catch:{ all -> 0x0350 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0350 }
            if (r1 != 0) goto L_0x00d6
        L_0x00d3:
            r12.zza((com.google.android.gms.measurement.internal.zzkh) r0, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
        L_0x00d6:
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzf r0 = r0.zzb(r1)     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x04cc
            com.google.android.gms.measurement.internal.zzfw r1 = r12.zzj     // Catch:{ all -> 0x0350 }
            r1.zzi()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zzb     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = r0.zze()     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = r13.zzr     // Catch:{ all -> 0x0350 }
            java.lang.String r6 = r0.zzf()     // Catch:{ all -> 0x0350 }
            boolean r1 = com.google.android.gms.measurement.internal.zzkm.zza((java.lang.String) r1, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ all -> 0x0350 }
            if (r1 == 0) goto L_0x04cc
            com.google.android.gms.measurement.internal.zzfw r1 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzes r1 = r1.zzr()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzi()     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r5 = r0.zzc()     // Catch:{ all -> 0x0350 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r5)     // Catch:{ all -> 0x0350 }
            r1.zza(r4, r5)     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzad r1 = r12.zze()     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = r0.zzc()     // Catch:{ all -> 0x0350 }
            r1.zzak()     // Catch:{ all -> 0x0350 }
            r1.zzd()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x0350 }
            android.database.sqlite.SQLiteDatabase r0 = r1.mo24238c_()     // Catch:{ SQLiteException -> 0x0359 }
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0359 }
            r6 = 0
            r5[r6] = r4     // Catch:{ SQLiteException -> 0x0359 }
            java.lang.String r6 = "events"
            java.lang.String r7 = "app_id=?"
            int r6 = r0.delete(r6, r7, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + 0
            java.lang.String r7 = "user_attributes"
            java.lang.String r8 = "app_id=?"
            int r7 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + r7
            java.lang.String r7 = "conditional_properties"
            java.lang.String r8 = "app_id=?"
            int r7 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + r7
            java.lang.String r7 = "apps"
            java.lang.String r8 = "app_id=?"
            int r7 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + r7
            java.lang.String r7 = "raw_events"
            java.lang.String r8 = "app_id=?"
            int r7 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + r7
            java.lang.String r7 = "raw_events_metadata"
            java.lang.String r8 = "app_id=?"
            int r7 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + r7
            java.lang.String r7 = "event_filters"
            java.lang.String r8 = "app_id=?"
            int r7 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + r7
            java.lang.String r7 = "property_filters"
            java.lang.String r8 = "app_id=?"
            int r7 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r6 = r6 + r7
            java.lang.String r7 = "audience_filter_values"
            java.lang.String r8 = "app_id=?"
            int r0 = r0.delete(r7, r8, r5)     // Catch:{ SQLiteException -> 0x0359 }
            int r0 = r0 + r6
            if (r0 <= 0) goto L_0x0190
            com.google.android.gms.measurement.internal.zzes r5 = r1.zzr()     // Catch:{ SQLiteException -> 0x0359 }
            com.google.android.gms.measurement.internal.zzeu r5 = r5.zzx()     // Catch:{ SQLiteException -> 0x0359 }
            java.lang.String r6 = "Deleted application data. app, records"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ SQLiteException -> 0x0359 }
            r5.zza(r6, r4, r0)     // Catch:{ SQLiteException -> 0x0359 }
        L_0x0190:
            r0 = 0
            r4 = r0
        L_0x0192:
            if (r4 == 0) goto L_0x01ec
            long r0 = r4.zzm()     // Catch:{ all -> 0x0350 }
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x036d
            long r0 = r4.zzm()     // Catch:{ all -> 0x0350 }
            long r6 = r13.zzj     // Catch:{ all -> 0x0350 }
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x036d
            r0 = 1
            r1 = r0
        L_0x01ab:
            long r6 = r4.zzm()     // Catch:{ all -> 0x0350 }
            r8 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 != 0) goto L_0x0371
            java.lang.String r0 = r4.zzl()     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x0371
            java.lang.String r0 = r4.zzl()     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = r13.zzc     // Catch:{ all -> 0x0350 }
            boolean r0 = r0.equals(r5)     // Catch:{ all -> 0x0350 }
            if (r0 != 0) goto L_0x0371
            r0 = 1
        L_0x01c9:
            r0 = r0 | r1
            if (r0 == 0) goto L_0x01ec
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0350 }
            r0.<init>()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_pv"
            java.lang.String r4 = r4.zzl()     // Catch:{ all -> 0x0350 }
            r0.putString(r1, r4)     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzao r4 = new com.google.android.gms.measurement.internal.zzao     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "_au"
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0350 }
            r6.<init>(r0)     // Catch:{ all -> 0x0350 }
            java.lang.String r7 = "auto"
            r8 = r2
            r4.<init>(r5, r6, r7, r8)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzao) r4, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
        L_0x01ec:
            r12.zzc(r13)     // Catch:{ all -> 0x0350 }
            r0 = 0
            if (r10 != 0) goto L_0x0374
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzak r0 = r0.zza((java.lang.String) r1, (java.lang.String) r4)     // Catch:{ all -> 0x0350 }
        L_0x01fe:
            if (r0 != 0) goto L_0x04ac
            r0 = 1
            r4 = 3600000(0x36ee80, double:1.7786363E-317)
            long r4 = r2 / r4
            long r0 = r0 + r4
            r4 = 3600000(0x36ee80, double:1.7786363E-317)
            long r4 = r4 * r0
            if (r10 != 0) goto L_0x044a
            com.google.android.gms.measurement.internal.zzkh r0 = new com.google.android.gms.measurement.internal.zzkh     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_fot"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "auto"
            r0.<init>(r1, r2, r4, r5)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzkh) r0, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzy r0 = r0.zzb()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zzb     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzar     // Catch:{ all -> 0x0350 }
            boolean r0 = r0.zze(r1, r4)     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x023c
            r12.zzw()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzfn r0 = r0.zzf()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            r0.zza(r1)     // Catch:{ all -> 0x0350 }
        L_0x023c:
            r12.zzw()     // Catch:{ all -> 0x0350 }
            r12.zzk()     // Catch:{ all -> 0x0350 }
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ all -> 0x0350 }
            r7.<init>()     // Catch:{ all -> 0x0350 }
            java.lang.String r0 = "_c"
            r4 = 1
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r0 = "_r"
            r4 = 1
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r0 = "_uwa"
            r4 = 0
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r0 = "_pfo"
            r4 = 0
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r0 = "_sys"
            r4 = 0
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r0 = "_sysu"
            r4 = 0
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzy r0 = r0.zzb()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzat     // Catch:{ all -> 0x0350 }
            boolean r0 = r0.zze(r1, r4)     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x0288
            java.lang.String r0 = "_et"
            r4 = 1
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
        L_0x0288:
            boolean r0 = r13.zzq     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x0293
            java.lang.String r0 = "_dac"
            r4 = 1
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
        L_0x0293:
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1)     // Catch:{ all -> 0x0350 }
            r0.zzd()     // Catch:{ all -> 0x0350 }
            r0.zzak()     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = "first_open_count"
            long r8 = r0.zzh(r1, r4)     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ all -> 0x0350 }
            android.content.Context r0 = r0.zzn()     // Catch:{ all -> 0x0350 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x0350 }
            if (r0 != 0) goto L_0x0385
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzes r0 = r0.zzr()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzf()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r4 = r13.zza     // Catch:{ all -> 0x0350 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x0350 }
            r0.zza(r1, r4)     // Catch:{ all -> 0x0350 }
        L_0x02c9:
            r0 = 0
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x02d4
            java.lang.String r0 = "_pfo"
            r7.putLong(r0, r8)     // Catch:{ all -> 0x0350 }
        L_0x02d4:
            com.google.android.gms.measurement.internal.zzao r4 = new com.google.android.gms.measurement.internal.zzao     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "_f"
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0350 }
            r6.<init>(r7)     // Catch:{ all -> 0x0350 }
            java.lang.String r7 = "auto"
            r8 = r2
            r4.<init>(r5, r6, r7, r8)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzao) r4, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
        L_0x02e6:
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzy r0 = r0.zzb()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzau     // Catch:{ all -> 0x0350 }
            boolean r0 = r0.zze(r1, r4)     // Catch:{ all -> 0x0350 }
            if (r0 != 0) goto L_0x032b
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0350 }
            r0.<init>()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_et"
            r4 = 1
            r0.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzfw r1 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzy r1 = r1.zzb()     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = r13.zza     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzat     // Catch:{ all -> 0x0350 }
            boolean r1 = r1.zze(r4, r5)     // Catch:{ all -> 0x0350 }
            if (r1 == 0) goto L_0x0319
            java.lang.String r1 = "_fr"
            r4 = 1
            r0.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
        L_0x0319:
            com.google.android.gms.measurement.internal.zzao r4 = new com.google.android.gms.measurement.internal.zzao     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "_e"
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0350 }
            r6.<init>(r0)     // Catch:{ all -> 0x0350 }
            java.lang.String r7 = "auto"
            r8 = r2
            r4.<init>(r5, r6, r7, r8)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzao) r4, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
        L_0x032b:
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()     // Catch:{ all -> 0x0350 }
            r0.mo24237b_()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()
            r0.zzh()
            goto L_0x0014
        L_0x033b:
            r4 = 0
            goto L_0x00be
        L_0x033f:
            if (r6 == 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzkh r0 = new com.google.android.gms.measurement.internal.zzkh     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_npa"
            r4 = 0
            java.lang.String r5 = "auto"
            r0.<init>(r1, r2, r4, r5)     // Catch:{ all -> 0x0350 }
            r12.zzb((com.google.android.gms.measurement.internal.zzkh) r0, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
            goto L_0x00d6
        L_0x0350:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzad r1 = r12.zze()
            r1.zzh()
            throw r0
        L_0x0359:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r1 = r1.zzr()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "Error deleting application data. appId, error"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)     // Catch:{ all -> 0x0350 }
            r1.zza(r5, r4, r0)     // Catch:{ all -> 0x0350 }
            goto L_0x0190
        L_0x036d:
            r0 = 0
            r1 = r0
            goto L_0x01ab
        L_0x0371:
            r0 = 0
            goto L_0x01c9
        L_0x0374:
            r1 = 1
            if (r10 != r1) goto L_0x01fe
            com.google.android.gms.measurement.internal.zzad r0 = r12.zze()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = r13.zza     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = "_v"
            com.google.android.gms.measurement.internal.zzak r0 = r0.zza((java.lang.String) r1, (java.lang.String) r4)     // Catch:{ all -> 0x0350 }
            goto L_0x01fe
        L_0x0385:
            r1 = 0
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ NameNotFoundException -> 0x040b }
            android.content.Context r0 = r0.zzn()     // Catch:{ NameNotFoundException -> 0x040b }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x040b }
            java.lang.String r4 = r13.zza     // Catch:{ NameNotFoundException -> 0x040b }
            r5 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r4, r5)     // Catch:{ NameNotFoundException -> 0x040b }
            r1 = r0
        L_0x0398:
            if (r1 == 0) goto L_0x03db
            long r4 = r1.firstInstallTime     // Catch:{ all -> 0x0350 }
            r10 = 0
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x03db
            r0 = 0
            long r4 = r1.firstInstallTime     // Catch:{ all -> 0x0350 }
            long r10 = r1.lastUpdateTime     // Catch:{ all -> 0x0350 }
            int r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r1 == 0) goto L_0x042c
            com.google.android.gms.measurement.internal.zzfw r1 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzy r1 = r1.zzb()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzaq.zzbt     // Catch:{ all -> 0x0350 }
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r4)     // Catch:{ all -> 0x0350 }
            if (r1 == 0) goto L_0x0423
            r4 = 0
            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r1 != 0) goto L_0x04c9
            java.lang.String r1 = "_uwa"
            r4 = 1
            r7.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
            r4 = r0
        L_0x03c7:
            com.google.android.gms.measurement.internal.zzkh r0 = new com.google.android.gms.measurement.internal.zzkh     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_fi"
            if (r4 == 0) goto L_0x042f
            r4 = 1
        L_0x03cf:
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "auto"
            r0.<init>(r1, r2, r4, r5)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzkh) r0, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
        L_0x03db:
            r1 = 0
            com.google.android.gms.measurement.internal.zzfw r0 = r12.zzj     // Catch:{ NameNotFoundException -> 0x0432 }
            android.content.Context r0 = r0.zzn()     // Catch:{ NameNotFoundException -> 0x0432 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0432 }
            java.lang.String r4 = r13.zza     // Catch:{ NameNotFoundException -> 0x0432 }
            r5 = 0
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r4, r5)     // Catch:{ NameNotFoundException -> 0x0432 }
        L_0x03ed:
            if (r0 == 0) goto L_0x02c9
            int r1 = r0.flags     // Catch:{ all -> 0x0350 }
            r1 = r1 & 1
            if (r1 == 0) goto L_0x03fc
            java.lang.String r1 = "_sys"
            r4 = 1
            r7.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
        L_0x03fc:
            int r0 = r0.flags     // Catch:{ all -> 0x0350 }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x02c9
            java.lang.String r0 = "_sysu"
            r4 = 1
            r7.putLong(r0, r4)     // Catch:{ all -> 0x0350 }
            goto L_0x02c9
        L_0x040b:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfw r4 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzes r4 = r4.zzr()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r6 = r13.zza     // Catch:{ all -> 0x0350 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r6)     // Catch:{ all -> 0x0350 }
            r4.zza(r5, r6, r0)     // Catch:{ all -> 0x0350 }
            goto L_0x0398
        L_0x0423:
            java.lang.String r1 = "_uwa"
            r4 = 1
            r7.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
            r4 = r0
            goto L_0x03c7
        L_0x042c:
            r0 = 1
            r4 = r0
            goto L_0x03c7
        L_0x042f:
            r4 = 0
            goto L_0x03cf
        L_0x0432:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfw r4 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzes r4 = r4.zzr()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r6 = r13.zza     // Catch:{ all -> 0x0350 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r6)     // Catch:{ all -> 0x0350 }
            r4.zza(r5, r6, r0)     // Catch:{ all -> 0x0350 }
            r0 = r1
            goto L_0x03ed
        L_0x044a:
            r0 = 1
            if (r10 != r0) goto L_0x02e6
            com.google.android.gms.measurement.internal.zzkh r0 = new com.google.android.gms.measurement.internal.zzkh     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_fvt"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "auto"
            r0.<init>(r1, r2, r4, r5)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzkh) r0, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
            r12.zzw()     // Catch:{ all -> 0x0350 }
            r12.zzk()     // Catch:{ all -> 0x0350 }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0350 }
            r0.<init>()     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_c"
            r4 = 1
            r0.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
            java.lang.String r1 = "_r"
            r4 = 1
            r0.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzfw r1 = r12.zzj     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzy r1 = r1.zzb()     // Catch:{ all -> 0x0350 }
            java.lang.String r4 = r13.zza     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzat     // Catch:{ all -> 0x0350 }
            boolean r1 = r1.zze(r4, r5)     // Catch:{ all -> 0x0350 }
            if (r1 == 0) goto L_0x048d
            java.lang.String r1 = "_et"
            r4 = 1
            r0.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
        L_0x048d:
            boolean r1 = r13.zzq     // Catch:{ all -> 0x0350 }
            if (r1 == 0) goto L_0x0498
            java.lang.String r1 = "_dac"
            r4 = 1
            r0.putLong(r1, r4)     // Catch:{ all -> 0x0350 }
        L_0x0498:
            com.google.android.gms.measurement.internal.zzao r4 = new com.google.android.gms.measurement.internal.zzao     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "_v"
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0350 }
            r6.<init>(r0)     // Catch:{ all -> 0x0350 }
            java.lang.String r7 = "auto"
            r8 = r2
            r4.<init>(r5, r6, r7, r8)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzao) r4, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
            goto L_0x02e6
        L_0x04ac:
            boolean r0 = r13.zzi     // Catch:{ all -> 0x0350 }
            if (r0 == 0) goto L_0x032b
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0350 }
            r0.<init>()     // Catch:{ all -> 0x0350 }
            com.google.android.gms.measurement.internal.zzao r4 = new com.google.android.gms.measurement.internal.zzao     // Catch:{ all -> 0x0350 }
            java.lang.String r5 = "_cd"
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x0350 }
            r6.<init>(r0)     // Catch:{ all -> 0x0350 }
            java.lang.String r7 = "auto"
            r8 = r2
            r4.<init>(r5, r6, r7, r8)     // Catch:{ all -> 0x0350 }
            r12.zza((com.google.android.gms.measurement.internal.zzao) r4, (com.google.android.gms.measurement.internal.zzn) r13)     // Catch:{ all -> 0x0350 }
            goto L_0x032b
        L_0x04c9:
            r4 = r0
            goto L_0x03c7
        L_0x04cc:
            r4 = r0
            goto L_0x0192
        L_0x04cf:
            r10 = r0
            goto L_0x008d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzka.zzb(com.google.android.gms.measurement.internal.zzn):void");
    }

    @WorkerThread
    private final zzn zza(String str) {
        String str2;
        zzf zzb2 = zze().zzb(str);
        if (zzb2 == null || TextUtils.isEmpty(zzb2.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping", str);
            return null;
        }
        Boolean zzb3 = zzb(zzb2);
        if (zzb3 == null || zzb3.booleanValue()) {
            String zze2 = zzb2.zze();
            String zzl2 = zzb2.zzl();
            long zzm2 = zzb2.zzm();
            String zzn2 = zzb2.zzn();
            long zzo2 = zzb2.zzo();
            long zzp2 = zzb2.zzp();
            boolean zzr2 = zzb2.zzr();
            String zzi2 = zzb2.zzi();
            long zzae = zzb2.zzae();
            boolean zzaf = zzb2.zzaf();
            boolean zzag = zzb2.zzag();
            String zzf2 = zzb2.zzf();
            Boolean zzah = zzb2.zzah();
            long zzq2 = zzb2.zzq();
            List<String> zzai = zzb2.zzai();
            if (!zzlb.zzb() || !this.zzj.zzb().zze(str, zzaq.zzbo)) {
                str2 = null;
            } else {
                str2 = zzb2.zzg();
            }
            return new zzn(str, zze2, zzl2, zzm2, zzn2, zzo2, zzp2, (String) null, zzr2, false, zzi2, zzae, 0, 0, zzaf, zzag, false, zzf2, zzah, zzq2, zzai, str2);
        }
        this.zzj.zzr().zzf().zza("App version does not match; dropping. appId", zzes.zza(str));
        return null;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(zzw zzw2) {
        zzn zza2 = zza(zzw2.zza);
        if (zza2 != null) {
            zza(zzw2, zza2);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(zzw zzw2, zzn zzn2) {
        boolean z = true;
        Preconditions.checkNotNull(zzw2);
        Preconditions.checkNotEmpty(zzw2.zza);
        Preconditions.checkNotNull(zzw2.zzb);
        Preconditions.checkNotNull(zzw2.zzc);
        Preconditions.checkNotEmpty(zzw2.zzc.zza);
        zzw();
        zzk();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
                return;
            }
            zzw zzw3 = new zzw(zzw2);
            zzw3.zze = false;
            zze().zzf();
            try {
                zzw zzd2 = zze().zzd(zzw3.zza, zzw3.zzc.zza);
                if (zzd2 != null && !zzd2.zzb.equals(zzw3.zzb)) {
                    this.zzj.zzr().zzi().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzj.zzj().zzc(zzw3.zzc.zza), zzw3.zzb, zzd2.zzb);
                }
                if (zzd2 != null && zzd2.zze) {
                    zzw3.zzb = zzd2.zzb;
                    zzw3.zzd = zzd2.zzd;
                    zzw3.zzh = zzd2.zzh;
                    zzw3.zzf = zzd2.zzf;
                    zzw3.zzi = zzd2.zzi;
                    zzw3.zze = zzd2.zze;
                    zzw3.zzc = new zzkh(zzw3.zzc.zza, zzd2.zzc.zzb, zzw3.zzc.zza(), zzd2.zzc.zze);
                    z = false;
                } else if (TextUtils.isEmpty(zzw3.zzf)) {
                    zzw3.zzc = new zzkh(zzw3.zzc.zza, zzw3.zzd, zzw3.zzc.zza(), zzw3.zzc.zze);
                    zzw3.zze = true;
                } else {
                    z = false;
                }
                if (zzw3.zze) {
                    zzkh zzkh = zzw3.zzc;
                    zzkj zzkj = new zzkj(zzw3.zza, zzw3.zzb, zzkh.zza, zzkh.zzb, zzkh.zza());
                    if (zze().zza(zzkj)) {
                        this.zzj.zzr().zzw().zza("User property updated immediately", zzw3.zza, this.zzj.zzj().zzc(zzkj.zzc), zzkj.zze);
                    } else {
                        this.zzj.zzr().zzf().zza("(2)Too many active user properties, ignoring", zzes.zza(zzw3.zza), this.zzj.zzj().zzc(zzkj.zzc), zzkj.zze);
                    }
                    if (z && zzw3.zzi != null) {
                        zzb(new zzao(zzw3.zzi, zzw3.zzd), zzn2);
                    }
                }
                if (zze().zza(zzw3)) {
                    this.zzj.zzr().zzw().zza("Conditional property added", zzw3.zza, this.zzj.zzj().zzc(zzw3.zzc.zza), zzw3.zzc.zza());
                } else {
                    this.zzj.zzr().zzf().zza("Too many conditional properties, ignoring", zzes.zza(zzw3.zza), this.zzj.zzj().zzc(zzw3.zzc.zza), zzw3.zzc.zza());
                }
                zze().mo24237b_();
            } finally {
                zze().zzh();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzw zzw2) {
        zzn zza2 = zza(zzw2.zza);
        if (zza2 != null) {
            zzb(zzw2, zza2);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzw zzw2, zzn zzn2) {
        Preconditions.checkNotNull(zzw2);
        Preconditions.checkNotEmpty(zzw2.zza);
        Preconditions.checkNotNull(zzw2.zzc);
        Preconditions.checkNotEmpty(zzw2.zzc.zza);
        zzw();
        zzk();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
                return;
            }
            zze().zzf();
            try {
                zzc(zzn2);
                zzw zzd2 = zze().zzd(zzw2.zza, zzw2.zzc.zza);
                if (zzd2 != null) {
                    this.zzj.zzr().zzw().zza("Removing conditional user property", zzw2.zza, this.zzj.zzj().zzc(zzw2.zzc.zza));
                    zze().zze(zzw2.zza, zzw2.zzc.zza);
                    if (zzd2.zze) {
                        zze().zzb(zzw2.zza, zzw2.zzc.zza);
                    }
                    if (zzw2.zzk != null) {
                        Bundle bundle = null;
                        if (zzw2.zzk.zzb != null) {
                            bundle = zzw2.zzk.zzb.zzb();
                        }
                        zzb(this.zzj.zzi().zza(zzw2.zza, zzw2.zzk.zza, bundle, zzd2.zzb, zzw2.zzk.zzd, true, false), zzn2);
                    }
                } else {
                    this.zzj.zzr().zzi().zza("Conditional user property doesn't exist", zzes.zza(zzw2.zza), this.zzj.zzj().zzc(zzw2.zzc.zza));
                }
                zze().mo24237b_();
            } finally {
                zze().zzh();
            }
        }
    }

    private final zzf zza(zzn zzn2, zzf zzf2, String str) {
        boolean z = false;
        if (zzf2 == null) {
            zzf2 = new zzf(this.zzj, zzn2.zza);
            zzf2.zza(this.zzj.zzi().zzk());
            zzf2.zze(str);
            z = true;
        } else if (!str.equals(zzf2.zzh())) {
            zzf2.zze(str);
            zzf2.zza(this.zzj.zzi().zzk());
            z = true;
        }
        if (!TextUtils.equals(zzn2.zzb, zzf2.zze())) {
            zzf2.zzb(zzn2.zzb);
            z = true;
        }
        if (!TextUtils.equals(zzn2.zzr, zzf2.zzf())) {
            zzf2.zzc(zzn2.zzr);
            z = true;
        }
        if (zzlb.zzb() && this.zzj.zzb().zze(zzf2.zzc(), zzaq.zzbo) && !TextUtils.equals(zzn2.zzv, zzf2.zzg())) {
            zzf2.zzd(zzn2.zzv);
            z = true;
        }
        if (!TextUtils.isEmpty(zzn2.zzk) && !zzn2.zzk.equals(zzf2.zzi())) {
            zzf2.zzf(zzn2.zzk);
            z = true;
        }
        if (!(zzn2.zze == 0 || zzn2.zze == zzf2.zzo())) {
            zzf2.zzd(zzn2.zze);
            z = true;
        }
        if (!TextUtils.isEmpty(zzn2.zzc) && !zzn2.zzc.equals(zzf2.zzl())) {
            zzf2.zzg(zzn2.zzc);
            z = true;
        }
        if (zzn2.zzj != zzf2.zzm()) {
            zzf2.zzc(zzn2.zzj);
            z = true;
        }
        if (zzn2.zzd != null && !zzn2.zzd.equals(zzf2.zzn())) {
            zzf2.zzh(zzn2.zzd);
            z = true;
        }
        if (zzn2.zzf != zzf2.zzp()) {
            zzf2.zze(zzn2.zzf);
            z = true;
        }
        if (zzn2.zzh != zzf2.zzr()) {
            zzf2.zza(zzn2.zzh);
            z = true;
        }
        if (!TextUtils.isEmpty(zzn2.zzg) && !zzn2.zzg.equals(zzf2.zzac())) {
            zzf2.zzi(zzn2.zzg);
            z = true;
        }
        if (!this.zzj.zzb().zza(zzaq.zzcm) && zzn2.zzl != zzf2.zzae()) {
            zzf2.zzp(zzn2.zzl);
            z = true;
        }
        if (zzn2.zzo != zzf2.zzaf()) {
            zzf2.zzb(zzn2.zzo);
            z = true;
        }
        if (zzn2.zzp != zzf2.zzag()) {
            zzf2.zzc(zzn2.zzp);
            z = true;
        }
        if (zzn2.zzs != zzf2.zzah()) {
            zzf2.zza(zzn2.zzs);
            z = true;
        }
        if (!(zzn2.zzt == 0 || zzn2.zzt == zzf2.zzq())) {
            zzf2.zzf(zzn2.zzt);
            z = true;
        }
        if (z) {
            zze().zza(zzf2);
        }
        return zzf2;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzf zzc(zzn zzn2) {
        zzw();
        zzk();
        Preconditions.checkNotNull(zzn2);
        Preconditions.checkNotEmpty(zzn2.zza);
        zzf zzb2 = zze().zzb(zzn2.zza);
        String zzb3 = this.zzj.zzc().zzb(zzn2.zza);
        if (!zzkk.zzb() || !this.zzj.zzb().zza(zzaq.zzbu)) {
            return zza(zzn2, zzb2, zzb3);
        }
        if (zzb2 == null) {
            zzb2 = new zzf(this.zzj, zzn2.zza);
            zzb2.zza(this.zzj.zzi().zzk());
            zzb2.zze(zzb3);
        } else if (!zzb3.equals(zzb2.zzh())) {
            zzb2.zze(zzb3);
            zzb2.zza(this.zzj.zzi().zzk());
        }
        zzb2.zzb(zzn2.zzb);
        zzb2.zzc(zzn2.zzr);
        if (zzlb.zzb() && this.zzj.zzb().zze(zzb2.zzc(), zzaq.zzbo)) {
            zzb2.zzd(zzn2.zzv);
        }
        if (!TextUtils.isEmpty(zzn2.zzk)) {
            zzb2.zzf(zzn2.zzk);
        }
        if (zzn2.zze != 0) {
            zzb2.zzd(zzn2.zze);
        }
        if (!TextUtils.isEmpty(zzn2.zzc)) {
            zzb2.zzg(zzn2.zzc);
        }
        zzb2.zzc(zzn2.zzj);
        if (zzn2.zzd != null) {
            zzb2.zzh(zzn2.zzd);
        }
        zzb2.zze(zzn2.zzf);
        zzb2.zza(zzn2.zzh);
        if (!TextUtils.isEmpty(zzn2.zzg)) {
            zzb2.zzi(zzn2.zzg);
        }
        if (!this.zzj.zzb().zza(zzaq.zzcm)) {
            zzb2.zzp(zzn2.zzl);
        }
        zzb2.zzb(zzn2.zzo);
        zzb2.zzc(zzn2.zzp);
        zzb2.zza(zzn2.zzs);
        zzb2.zzf(zzn2.zzt);
        if (!zzb2.zza()) {
            return zzb2;
        }
        zze().zza(zzb2);
        return zzb2;
    }

    /* access modifiers changed from: package-private */
    public final String zzd(zzn zzn2) {
        try {
            return (String) this.zzj.zzq().zza(new zzke(this, zzn2)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzj.zzr().zzf().zza("Failed to get app instance id. appId", zzes.zza(zzn2.zza), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzz();
    }

    private final boolean zze(zzn zzn2) {
        if (!zzlb.zzb() || !this.zzj.zzb().zze(zzn2.zza, zzaq.zzbo)) {
            if (!TextUtils.isEmpty(zzn2.zzb) || !TextUtils.isEmpty(zzn2.zzr)) {
                return true;
            }
            return false;
        } else if (!TextUtils.isEmpty(zzn2.zzb) || !TextUtils.isEmpty(zzn2.zzv) || !TextUtils.isEmpty(zzn2.zzr)) {
            return true;
        } else {
            return false;
        }
    }
}
