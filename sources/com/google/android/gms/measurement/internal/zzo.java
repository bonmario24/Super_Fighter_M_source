package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzo extends zzkb {
    private String zzb;
    private Set<Integer> zzc;
    private Map<Integer, zzq> zzd;
    private Long zze;
    private Long zzf;

    zzo(zzka zzka) {
        super(zzka);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0623, code lost:
        if (r3.zza() == false) goto L_0x0646;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0625, code lost:
        r3 = java.lang.Integer.valueOf(r3.zzb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x062d, code lost:
        r5.zza("Invalid property filter ID. appId, id", r11, java.lang.String.valueOf(r3));
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0646, code lost:
        r3 = null;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzbs.zza> zza(java.lang.String r25, java.util.List<com.google.android.gms.internal.measurement.zzbs.zzc> r26, java.util.List<com.google.android.gms.internal.measurement.zzbs.zzk> r27, java.lang.Long r28, java.lang.Long r29) {
        /*
            r24 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r25)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r27)
            r0 = r25
            r1 = r24
            r1.zzb = r0
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r0 = r24
            r0.zzc = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap
            r2.<init>()
            r0 = r24
            r0.zzd = r2
            r0 = r28
            r1 = r24
            r1.zze = r0
            r0 = r29
            r1 = r24
            r1.zzf = r0
            java.util.Iterator r3 = r26.iterator()
        L_0x0031:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x01bc
            java.lang.Object r2 = r3.next()
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2
            java.lang.String r4 = "_s"
            java.lang.String r2 = r2.zzc()
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0031
            r2 = 1
        L_0x004a:
            boolean r3 = com.google.android.gms.internal.measurement.zzjy.zzb()
            if (r3 == 0) goto L_0x01bf
            com.google.android.gms.measurement.internal.zzy r3 = r24.zzt()
            r0 = r24
            java.lang.String r4 = r0.zzb
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzbg
            boolean r3 = r3.zzd(r4, r5)
            if (r3 == 0) goto L_0x01bf
            r3 = 1
            r11 = r3
        L_0x0062:
            boolean r3 = com.google.android.gms.internal.measurement.zzjy.zzb()
            if (r3 == 0) goto L_0x01c3
            com.google.android.gms.measurement.internal.zzy r3 = r24.zzt()
            r0 = r24
            java.lang.String r4 = r0.zzb
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzaq.zzbf
            boolean r3 = r3.zzd(r4, r5)
            if (r3 == 0) goto L_0x01c3
            r3 = 1
            r15 = r3
        L_0x007a:
            if (r2 == 0) goto L_0x00ad
            com.google.android.gms.measurement.internal.zzad r4 = r24.zzi()
            r0 = r24
            java.lang.String r5 = r0.zzb
            r4.zzak()
            r4.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r6 = "current_session_count"
            r7 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.put(r6, r7)
            android.database.sqlite.SQLiteDatabase r6 = r4.mo24238c_()     // Catch:{ SQLiteException -> 0x01c7 }
            java.lang.String r7 = "events"
            java.lang.String r8 = "app_id = ?"
            r9 = 1
            java.lang.String[] r9 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x01c7 }
            r10 = 0
            r9[r10] = r5     // Catch:{ SQLiteException -> 0x01c7 }
            r6.update(r7, r3, r8, r9)     // Catch:{ SQLiteException -> 0x01c7 }
        L_0x00ad:
            java.util.Map r3 = java.util.Collections.emptyMap()
            if (r15 == 0) goto L_0x074c
            if (r11 == 0) goto L_0x074c
            com.google.android.gms.measurement.internal.zzad r3 = r24.zzi()
            r0 = r24
            java.lang.String r4 = r0.zzb
            java.util.Map r3 = r3.zze(r4)
            r12 = r3
        L_0x00c2:
            com.google.android.gms.measurement.internal.zzad r3 = r24.zzi()
            r0 = r24
            java.lang.String r4 = r0.zzb
            java.util.Map r14 = r3.zzg(r4)
            if (r14 == 0) goto L_0x00d6
            boolean r3 = r14.isEmpty()
            if (r3 == 0) goto L_0x01db
        L_0x00d6:
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x0522
            com.google.android.gms.measurement.internal.zzt r20 = new com.google.android.gms.measurement.internal.zzt
            r2 = 0
            r0 = r20
            r1 = r24
            r0.<init>(r1, r2)
            androidx.collection.ArrayMap r21 = new androidx.collection.ArrayMap
            r21.<init>()
            java.util.Iterator r22 = r26.iterator()
        L_0x00ef:
            boolean r2 = r22.hasNext()
            if (r2 == 0) goto L_0x0522
            java.lang.Object r2 = r22.next()
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2
            r0 = r24
            java.lang.String r3 = r0.zzb
            r0 = r20
            com.google.android.gms.internal.measurement.zzbs$zzc r23 = r0.zza(r3, r2)
            if (r23 == 0) goto L_0x00ef
            com.google.android.gms.measurement.internal.zzad r3 = r24.zzi()
            r0 = r24
            java.lang.String r4 = r0.zzb
            java.lang.String r5 = r23.zzc()
            java.lang.String r6 = r2.zzc()
            com.google.android.gms.measurement.internal.zzak r19 = r3.zza((java.lang.String) r4, (java.lang.String) r6)
            if (r19 != 0) goto L_0x0466
            com.google.android.gms.measurement.internal.zzes r6 = r3.zzr()
            com.google.android.gms.measurement.internal.zzeu r6 = r6.zzi()
            java.lang.String r7 = "Event aggregate wasn't created during raw event logging. appId, event"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r4)
            com.google.android.gms.measurement.internal.zzeq r3 = r3.zzo()
            java.lang.String r3 = r3.zza((java.lang.String) r5)
            r6.zza(r7, r8, r3)
            com.google.android.gms.measurement.internal.zzak r3 = new com.google.android.gms.measurement.internal.zzak
            java.lang.String r5 = r2.zzc()
            r6 = 1
            r8 = 1
            r10 = 1
            long r12 = r2.zze()
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r3.<init>(r4, r5, r6, r8, r10, r12, r14, r16, r17, r18, r19)
            r8 = r3
        L_0x0154:
            com.google.android.gms.measurement.internal.zzad r2 = r24.zzi()
            r2.zza((com.google.android.gms.measurement.internal.zzak) r8)
            long r6 = r8.zzc
            java.lang.String r3 = r23.zzc()
            r0 = r21
            java.lang.Object r2 = r0.get(r3)
            java.util.Map r2 = (java.util.Map) r2
            if (r2 != 0) goto L_0x0742
            com.google.android.gms.measurement.internal.zzad r2 = r24.zzi()
            r0 = r24
            java.lang.String r4 = r0.zzb
            java.util.Map r2 = r2.zzf(r4, r3)
            if (r2 != 0) goto L_0x017e
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap
            r2.<init>()
        L_0x017e:
            r0 = r21
            r0.put(r3, r2)
            r10 = r2
        L_0x0184:
            java.util.Set r2 = r10.keySet()
            java.util.Iterator r11 = r2.iterator()
        L_0x018c:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto L_0x00ef
            java.lang.Object r2 = r11.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r12 = r2.intValue()
            r0 = r24
            java.util.Set<java.lang.Integer> r2 = r0.zzc
            java.lang.Integer r3 = java.lang.Integer.valueOf(r12)
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x04ab
            com.google.android.gms.measurement.internal.zzes r2 = r24.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzx()
            java.lang.String r3 = "Skipping failed audience ID"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)
            r2.zza(r3, r4)
            goto L_0x018c
        L_0x01bc:
            r2 = 0
            goto L_0x004a
        L_0x01bf:
            r3 = 0
            r11 = r3
            goto L_0x0062
        L_0x01c3:
            r3 = 0
            r15 = r3
            goto L_0x007a
        L_0x01c7:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzes r4 = r4.zzr()
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()
            java.lang.String r6 = "Error resetting session-scoped event counts. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r5)
            r4.zza(r6, r5, r3)
            goto L_0x00ad
        L_0x01db:
            java.util.HashSet r6 = new java.util.HashSet
            java.util.Set r3 = r14.keySet()
            r6.<init>(r3)
            if (r2 == 0) goto L_0x0749
            r0 = r24
            java.lang.String r2 = r0.zzb
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            androidx.collection.ArrayMap r5 = new androidx.collection.ArrayMap
            r5.<init>()
            boolean r3 = r14.isEmpty()
            if (r3 != 0) goto L_0x02c3
            com.google.android.gms.measurement.internal.zzad r3 = r24.zzi()
            java.util.Map r7 = r3.zzf(r2)
            java.util.Set r2 = r14.keySet()
            java.util.Iterator r8 = r2.iterator()
        L_0x020b:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x02c3
            java.lang.Object r2 = r8.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r9 = r2.intValue()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r9)
            java.lang.Object r2 = r14.get(r2)
            com.google.android.gms.internal.measurement.zzbs$zzi r2 = (com.google.android.gms.internal.measurement.zzbs.zzi) r2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
            java.lang.Object r3 = r7.get(r3)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L_0x0237
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x023f
        L_0x0237:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
            r5.put(r3, r2)
            goto L_0x020b
        L_0x023f:
            com.google.android.gms.measurement.internal.zzki r4 = r24.zzg()
            java.util.List r10 = r2.zzc()
            java.util.List r10 = r4.zza((java.util.List<java.lang.Long>) r10, (java.util.List<java.lang.Integer>) r3)
            boolean r4 = r10.isEmpty()
            if (r4 != 0) goto L_0x020b
            com.google.android.gms.internal.measurement.zzfe$zza r4 = r2.zzbl()
            com.google.android.gms.internal.measurement.zzfe$zza r4 = (com.google.android.gms.internal.measurement.zzfe.zza) r4
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r4 = (com.google.android.gms.internal.measurement.zzbs.zzi.zza) r4
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r4 = r4.zzb()
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r10 = r4.zzb((java.lang.Iterable<? extends java.lang.Long>) r10)
            com.google.android.gms.measurement.internal.zzki r4 = r24.zzg()
            java.util.List r13 = r2.zza()
            java.util.List r4 = r4.zza((java.util.List<java.lang.Long>) r13, (java.util.List<java.lang.Integer>) r3)
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r13 = r10.zza()
            r13.zza((java.lang.Iterable<? extends java.lang.Long>) r4)
            r4 = 0
        L_0x0275:
            int r13 = r2.zzf()
            if (r4 >= r13) goto L_0x0293
            com.google.android.gms.internal.measurement.zzbs$zzb r13 = r2.zza((int) r4)
            int r13 = r13.zzb()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            boolean r13 = r3.contains(r13)
            if (r13 == 0) goto L_0x0290
            r10.zza((int) r4)
        L_0x0290:
            int r4 = r4 + 1
            goto L_0x0275
        L_0x0293:
            r4 = 0
        L_0x0294:
            int r13 = r2.zzh()
            if (r4 >= r13) goto L_0x02b2
            com.google.android.gms.internal.measurement.zzbs$zzj r13 = r2.zzb((int) r4)
            int r13 = r13.zzb()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            boolean r13 = r3.contains(r13)
            if (r13 == 0) goto L_0x02af
            r10.zzb((int) r4)
        L_0x02af:
            int r4 = r4 + 1
            goto L_0x0294
        L_0x02b2:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
            com.google.android.gms.internal.measurement.zzgm r2 = r10.zzv()
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2
            com.google.android.gms.internal.measurement.zzbs$zzi r2 = (com.google.android.gms.internal.measurement.zzbs.zzi) r2
            r5.put(r3, r2)
            goto L_0x020b
        L_0x02c3:
            r13 = r5
        L_0x02c4:
            java.util.Iterator r18 = r6.iterator()
        L_0x02c8:
            boolean r2 = r18.hasNext()
            if (r2 == 0) goto L_0x00d6
            java.lang.Object r2 = r18.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r19 = r2.intValue()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r19)
            java.lang.Object r2 = r13.get(r2)
            com.google.android.gms.internal.measurement.zzbs$zzi r2 = (com.google.android.gms.internal.measurement.zzbs.zzi) r2
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.util.BitSet r7 = new java.util.BitSet
            r7.<init>()
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            if (r2 == 0) goto L_0x02f9
            int r3 = r2.zzf()
            if (r3 != 0) goto L_0x0352
        L_0x02f9:
            androidx.collection.ArrayMap r9 = new androidx.collection.ArrayMap
            r9.<init>()
            if (r2 == 0) goto L_0x0306
            int r3 = r2.zzh()
            if (r3 != 0) goto L_0x0388
        L_0x0306:
            if (r2 == 0) goto L_0x03c2
            r3 = 0
        L_0x0309:
            int r4 = r2.zzb()
            int r4 = r4 << 6
            if (r3 >= r4) goto L_0x03c2
            r4 = 0
            java.util.List r5 = r2.zza()
            boolean r5 = com.google.android.gms.measurement.internal.zzki.zza((java.util.List<java.lang.Long>) r5, (int) r3)
            if (r5 == 0) goto L_0x0346
            com.google.android.gms.measurement.internal.zzes r5 = r24.zzr()
            com.google.android.gms.measurement.internal.zzeu r5 = r5.zzx()
            java.lang.String r10 = "Filter already evaluated. audience ID, filter ID"
            java.lang.Integer r16 = java.lang.Integer.valueOf(r19)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r3)
            r0 = r16
            r1 = r17
            r5.zza(r10, r0, r1)
            r7.set(r3)
            java.util.List r5 = r2.zzc()
            boolean r5 = com.google.android.gms.measurement.internal.zzki.zza((java.util.List<java.lang.Long>) r5, (int) r3)
            if (r5 == 0) goto L_0x0346
            r6.set(r3)
            r4 = 1
        L_0x0346:
            if (r4 != 0) goto L_0x034f
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r8.remove(r4)
        L_0x034f:
            int r3 = r3 + 1
            goto L_0x0309
        L_0x0352:
            java.util.List r3 = r2.zze()
            java.util.Iterator r4 = r3.iterator()
        L_0x035a:
            boolean r3 = r4.hasNext()
            if (r3 == 0) goto L_0x02f9
            java.lang.Object r3 = r4.next()
            com.google.android.gms.internal.measurement.zzbs$zzb r3 = (com.google.android.gms.internal.measurement.zzbs.zzb) r3
            boolean r5 = r3.zza()
            if (r5 == 0) goto L_0x035a
            int r5 = r3.zzb()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            boolean r9 = r3.zzc()
            if (r9 == 0) goto L_0x0386
            long r16 = r3.zzd()
            java.lang.Long r3 = java.lang.Long.valueOf(r16)
        L_0x0382:
            r8.put(r5, r3)
            goto L_0x035a
        L_0x0386:
            r3 = 0
            goto L_0x0382
        L_0x0388:
            java.util.List r3 = r2.zzg()
            java.util.Iterator r4 = r3.iterator()
        L_0x0390:
            boolean r3 = r4.hasNext()
            if (r3 == 0) goto L_0x0306
            java.lang.Object r3 = r4.next()
            com.google.android.gms.internal.measurement.zzbs$zzj r3 = (com.google.android.gms.internal.measurement.zzbs.zzj) r3
            boolean r5 = r3.zza()
            if (r5 == 0) goto L_0x0390
            int r5 = r3.zzd()
            if (r5 <= 0) goto L_0x0390
            int r5 = r3.zzb()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            int r10 = r3.zzd()
            int r10 = r10 + -1
            long r16 = r3.zza((int) r10)
            java.lang.Long r3 = java.lang.Long.valueOf(r16)
            r9.put(r5, r3)
            goto L_0x0390
        L_0x03c2:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r19)
            java.lang.Object r5 = r14.get(r2)
            com.google.android.gms.internal.measurement.zzbs$zzi r5 = (com.google.android.gms.internal.measurement.zzbs.zzi) r5
            if (r15 == 0) goto L_0x03e8
            if (r11 == 0) goto L_0x03e8
            java.lang.Integer r2 = java.lang.Integer.valueOf(r19)
            java.lang.Object r2 = r12.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x03e8
            r0 = r24
            java.lang.Long r3 = r0.zzf
            if (r3 == 0) goto L_0x03e8
            r0 = r24
            java.lang.Long r3 = r0.zze
            if (r3 != 0) goto L_0x0401
        L_0x03e8:
            com.google.android.gms.measurement.internal.zzq r2 = new com.google.android.gms.measurement.internal.zzq
            r0 = r24
            java.lang.String r4 = r0.zzb
            r10 = 0
            r3 = r24
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            r0 = r24
            java.util.Map<java.lang.Integer, com.google.android.gms.measurement.internal.zzq> r3 = r0.zzd
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            r3.put(r4, r2)
            goto L_0x02c8
        L_0x0401:
            java.util.Iterator r4 = r2.iterator()
        L_0x0405:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x03e8
            java.lang.Object r2 = r4.next()
            com.google.android.gms.internal.measurement.zzbk$zzb r2 = (com.google.android.gms.internal.measurement.zzbk.zzb) r2
            int r10 = r2.zzb()
            r0 = r24
            java.lang.Long r3 = r0.zzf
            long r16 = r3.longValue()
            r20 = 1000(0x3e8, double:4.94E-321)
            long r16 = r16 / r20
            boolean r2 = r2.zzi()
            if (r2 == 0) goto L_0x0745
            r0 = r24
            java.lang.Long r2 = r0.zze
            long r2 = r2.longValue()
            r16 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r16
        L_0x0433:
            java.lang.Integer r16 = java.lang.Integer.valueOf(r10)
            r0 = r16
            boolean r16 = r8.containsKey(r0)
            if (r16 == 0) goto L_0x044e
            java.lang.Integer r16 = java.lang.Integer.valueOf(r10)
            java.lang.Long r17 = java.lang.Long.valueOf(r2)
            r0 = r16
            r1 = r17
            r8.put(r0, r1)
        L_0x044e:
            java.lang.Integer r16 = java.lang.Integer.valueOf(r10)
            r0 = r16
            boolean r16 = r9.containsKey(r0)
            if (r16 == 0) goto L_0x0405
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r9.put(r10, r2)
            goto L_0x0405
        L_0x0466:
            com.google.android.gms.measurement.internal.zzak r3 = new com.google.android.gms.measurement.internal.zzak
            r0 = r19
            java.lang.String r4 = r0.zza
            r0 = r19
            java.lang.String r5 = r0.zzb
            r0 = r19
            long r6 = r0.zzc
            r8 = 1
            long r6 = r6 + r8
            r0 = r19
            long r8 = r0.zzd
            r10 = 1
            long r8 = r8 + r10
            r0 = r19
            long r10 = r0.zze
            r12 = 1
            long r10 = r10 + r12
            r0 = r19
            long r12 = r0.zzf
            r0 = r19
            long r14 = r0.zzg
            r0 = r19
            java.lang.Long r0 = r0.zzh
            r16 = r0
            r0 = r19
            java.lang.Long r0 = r0.zzi
            r17 = r0
            r0 = r19
            java.lang.Long r0 = r0.zzj
            r18 = r0
            r0 = r19
            java.lang.Boolean r0 = r0.zzk
            r19 = r0
            r3.<init>(r4, r5, r6, r8, r10, r12, r14, r16, r17, r18, r19)
            r8 = r3
            goto L_0x0154
        L_0x04ab:
            r3 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r12)
            java.lang.Object r2 = r10.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r13 = r2.iterator()
            r2 = r3
        L_0x04bb:
            boolean r3 = r13.hasNext()
            if (r3 == 0) goto L_0x073f
            java.lang.Object r2 = r13.next()
            r5 = r2
            com.google.android.gms.internal.measurement.zzbk$zzb r5 = (com.google.android.gms.internal.measurement.zzbk.zzb) r5
            com.google.android.gms.measurement.internal.zzs r2 = new com.google.android.gms.measurement.internal.zzs
            r0 = r24
            java.lang.String r3 = r0.zzb
            r0 = r24
            r2.<init>(r0, r3, r12, r5)
            r0 = r24
            java.lang.Long r3 = r0.zze
            r0 = r24
            java.lang.Long r4 = r0.zzf
            int r5 = r5.zzb()
            r0 = r24
            boolean r9 = r0.zza(r12, r5)
            r5 = r23
            boolean r3 = r2.zza(r3, r4, r5, r6, r8, r9)
            com.google.android.gms.measurement.internal.zzy r4 = r24.zzt()
            r0 = r24
            java.lang.String r5 = r0.zzb
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzaq.zzbd
            boolean r4 = r4.zzd(r5, r9)
            if (r4 == 0) goto L_0x0517
            if (r3 != 0) goto L_0x0517
            r0 = r24
            java.util.Set<java.lang.Integer> r2 = r0.zzc
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)
            r2.add(r4)
        L_0x0508:
            if (r3 != 0) goto L_0x018c
            r0 = r24
            java.util.Set<java.lang.Integer> r2 = r0.zzc
            java.lang.Integer r3 = java.lang.Integer.valueOf(r12)
            r2.add(r3)
            goto L_0x018c
        L_0x0517:
            r0 = r24
            com.google.android.gms.measurement.internal.zzq r4 = r0.zza(r12)
            r4.zza((com.google.android.gms.measurement.internal.zzv) r2)
            r2 = r3
            goto L_0x04bb
        L_0x0522:
            boolean r2 = r27.isEmpty()
            if (r2 != 0) goto L_0x0692
            androidx.collection.ArrayMap r6 = new androidx.collection.ArrayMap
            r6.<init>()
            java.util.Iterator r7 = r27.iterator()
        L_0x0531:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x0692
            java.lang.Object r2 = r7.next()
            com.google.android.gms.internal.measurement.zzbs$zzk r2 = (com.google.android.gms.internal.measurement.zzbs.zzk) r2
            java.lang.String r4 = r2.zzc()
            java.lang.Object r3 = r6.get(r4)
            java.util.Map r3 = (java.util.Map) r3
            if (r3 != 0) goto L_0x073c
            com.google.android.gms.measurement.internal.zzad r3 = r24.zzi()
            r0 = r24
            java.lang.String r5 = r0.zzb
            java.util.Map r3 = r3.zzg(r5, r4)
            if (r3 != 0) goto L_0x055c
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
        L_0x055c:
            r6.put(r4, r3)
            r4 = r3
        L_0x0560:
            java.util.Set r3 = r4.keySet()
            java.util.Iterator r8 = r3.iterator()
        L_0x0568:
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto L_0x0531
            java.lang.Object r3 = r8.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r9 = r3.intValue()
            r0 = r24
            java.util.Set<java.lang.Integer> r3 = r0.zzc
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
            boolean r3 = r3.contains(r5)
            if (r3 == 0) goto L_0x0598
            com.google.android.gms.measurement.internal.zzes r2 = r24.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzx()
            java.lang.String r3 = "Skipping failed audience ID"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)
            r2.zza(r3, r4)
            goto L_0x0531
        L_0x0598:
            r5 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
            java.lang.Object r3 = r4.get(r3)
            java.util.List r3 = (java.util.List) r3
            java.util.Iterator r10 = r3.iterator()
            r3 = r5
        L_0x05a8:
            boolean r5 = r10.hasNext()
            if (r5 == 0) goto L_0x0635
            java.lang.Object r3 = r10.next()
            com.google.android.gms.internal.measurement.zzbk$zze r3 = (com.google.android.gms.internal.measurement.zzbk.zze) r3
            com.google.android.gms.measurement.internal.zzes r5 = r24.zzr()
            r11 = 2
            boolean r5 = r5.zza((int) r11)
            if (r5 == 0) goto L_0x05ff
            com.google.android.gms.measurement.internal.zzes r5 = r24.zzr()
            com.google.android.gms.measurement.internal.zzeu r11 = r5.zzx()
            java.lang.String r12 = "Evaluating filter. audience, filter, property"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r9)
            boolean r5 = r3.zza()
            if (r5 == 0) goto L_0x0644
            int r5 = r3.zzb()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
        L_0x05db:
            com.google.android.gms.measurement.internal.zzeq r14 = r24.zzo()
            java.lang.String r15 = r3.zzc()
            java.lang.String r14 = r14.zzc(r15)
            r11.zza(r12, r13, r5, r14)
            com.google.android.gms.measurement.internal.zzes r5 = r24.zzr()
            com.google.android.gms.measurement.internal.zzeu r5 = r5.zzx()
            java.lang.String r11 = "Filter definition"
            com.google.android.gms.measurement.internal.zzki r12 = r24.zzg()
            java.lang.String r12 = r12.zza((com.google.android.gms.internal.measurement.zzbk.zze) r3)
            r5.zza(r11, r12)
        L_0x05ff:
            boolean r5 = r3.zza()
            if (r5 == 0) goto L_0x060d
            int r5 = r3.zzb()
            r11 = 256(0x100, float:3.59E-43)
            if (r5 <= r11) goto L_0x0648
        L_0x060d:
            com.google.android.gms.measurement.internal.zzes r5 = r24.zzr()
            com.google.android.gms.measurement.internal.zzeu r5 = r5.zzi()
            java.lang.String r10 = "Invalid property filter ID. appId, id"
            r0 = r24
            java.lang.String r11 = r0.zzb
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r11)
            boolean r12 = r3.zza()
            if (r12 == 0) goto L_0x0646
            int r3 = r3.zzb()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x062d:
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r5.zza(r10, r11, r3)
            r3 = 0
        L_0x0635:
            if (r3 != 0) goto L_0x0568
            r0 = r24
            java.util.Set<java.lang.Integer> r3 = r0.zzc
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
            r3.add(r5)
            goto L_0x0568
        L_0x0644:
            r5 = 0
            goto L_0x05db
        L_0x0646:
            r3 = 0
            goto L_0x062d
        L_0x0648:
            com.google.android.gms.measurement.internal.zzu r5 = new com.google.android.gms.measurement.internal.zzu
            r0 = r24
            java.lang.String r11 = r0.zzb
            r0 = r24
            r5.<init>(r0, r11, r9, r3)
            r0 = r24
            java.lang.Long r11 = r0.zze
            r0 = r24
            java.lang.Long r12 = r0.zzf
            int r3 = r3.zzb()
            r0 = r24
            boolean r3 = r0.zza(r9, r3)
            boolean r3 = r5.zza(r11, r12, r2, r3)
            com.google.android.gms.measurement.internal.zzy r11 = r24.zzt()
            r0 = r24
            java.lang.String r12 = r0.zzb
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r13 = com.google.android.gms.measurement.internal.zzaq.zzbd
            boolean r11 = r11.zzd(r12, r13)
            if (r11 == 0) goto L_0x0687
            if (r3 != 0) goto L_0x0687
            r0 = r24
            java.util.Set<java.lang.Integer> r5 = r0.zzc
            java.lang.Integer r10 = java.lang.Integer.valueOf(r9)
            r5.add(r10)
            goto L_0x0635
        L_0x0687:
            r0 = r24
            com.google.android.gms.measurement.internal.zzq r11 = r0.zza(r9)
            r11.zza((com.google.android.gms.measurement.internal.zzv) r5)
            goto L_0x05a8
        L_0x0692:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r0 = r24
            java.util.Map<java.lang.Integer, com.google.android.gms.measurement.internal.zzq> r2 = r0.zzd
            java.util.Set r2 = r2.keySet()
            r0 = r24
            java.util.Set<java.lang.Integer> r4 = r0.zzc
            r2.removeAll(r4)
            java.util.Iterator r4 = r2.iterator()
        L_0x06aa:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x073b
            java.lang.Object r2 = r4.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r5 = r2.intValue()
            r0 = r24
            java.util.Map<java.lang.Integer, com.google.android.gms.measurement.internal.zzq> r2 = r0.zzd
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            java.lang.Object r2 = r2.get(r6)
            com.google.android.gms.measurement.internal.zzq r2 = (com.google.android.gms.measurement.internal.zzq) r2
            com.google.android.gms.internal.measurement.zzbs$zza r2 = r2.zza((int) r5)
            r3.add(r2)
            com.google.android.gms.measurement.internal.zzad r6 = r24.zzi()
            r0 = r24
            java.lang.String r7 = r0.zzb
            com.google.android.gms.internal.measurement.zzbs$zzi r2 = r2.zzc()
            r6.zzak()
            r6.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)
            byte[] r2 = r2.zzbi()
            android.content.ContentValues r8 = new android.content.ContentValues
            r8.<init>()
            java.lang.String r9 = "app_id"
            r8.put(r9, r7)
            java.lang.String r9 = "audience_id"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r8.put(r9, r5)
            java.lang.String r5 = "current_results"
            r8.put(r5, r2)
            android.database.sqlite.SQLiteDatabase r2 = r6.mo24238c_()     // Catch:{ SQLiteException -> 0x0727 }
            java.lang.String r5 = "audience_filter_values"
            r9 = 0
            r10 = 5
            long r8 = r2.insertWithOnConflict(r5, r9, r8, r10)     // Catch:{ SQLiteException -> 0x0727 }
            r10 = -1
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x06aa
            com.google.android.gms.measurement.internal.zzes r2 = r6.zzr()     // Catch:{ SQLiteException -> 0x0727 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x0727 }
            java.lang.String r5 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x0727 }
            r2.zza(r5, r8)     // Catch:{ SQLiteException -> 0x0727 }
            goto L_0x06aa
        L_0x0727:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzes r5 = r6.zzr()
            com.google.android.gms.measurement.internal.zzeu r5 = r5.zzf()
            java.lang.String r6 = "Error storing filter results. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r7)
            r5.zza(r6, r7, r2)
            goto L_0x06aa
        L_0x073b:
            return r3
        L_0x073c:
            r4 = r3
            goto L_0x0560
        L_0x073f:
            r3 = r2
            goto L_0x0508
        L_0x0742:
            r10 = r2
            goto L_0x0184
        L_0x0745:
            r2 = r16
            goto L_0x0433
        L_0x0749:
            r13 = r14
            goto L_0x02c4
        L_0x074c:
            r12 = r3
            goto L_0x00c2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzo.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    private final zzq zza(int i) {
        if (this.zzd.containsKey(Integer.valueOf(i))) {
            return this.zzd.get(Integer.valueOf(i));
        }
        zzq zzq = new zzq(this, this.zzb, (zzr) null);
        this.zzd.put(Integer.valueOf(i), zzq);
        return zzq;
    }

    private final boolean zza(int i, int i2) {
        if (this.zzd.get(Integer.valueOf(i)) == null) {
            return false;
        }
        return this.zzd.get(Integer.valueOf(i)).zzd.get(i2);
    }
}
