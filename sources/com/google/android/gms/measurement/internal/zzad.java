package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzlb;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzad extends zzkb {
    /* access modifiers changed from: private */
    public static final String[] zzb = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzc = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzd = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zze = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzf = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzae zzj = new zzae(this, zzn(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzjx zzk = new zzjx(zzm());

    zzad(zzka zzka) {
        super(zzka);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    @WorkerThread
    public final void zzf() {
        zzak();
        mo24238c_().beginTransaction();
    }

    @WorkerThread
    /* renamed from: b_ */
    public final void mo24237b_() {
        zzak();
        mo24238c_().setTransactionSuccessful();
    }

    @WorkerThread
    public final void zzh() {
        zzak();
        mo24238c_().endTransaction();
    }

    @WorkerThread
    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = mo24238c_().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzr().zzf().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = mo24238c_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } else if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    /* renamed from: c_ */
    public final SQLiteDatabase mo24238c_() {
        zzd();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            zzr().zzi().zza("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0168  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzak zza(java.lang.String r22, java.lang.String r23) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r22)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            r21.zzd()
            r21.zzak()
            java.util.ArrayList r4 = new java.util.ArrayList
            r2 = 9
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 0
            java.lang.String r5 = "lifetime_count"
            r2[r3] = r5
            r3 = 1
            java.lang.String r5 = "current_bundle_count"
            r2[r3] = r5
            r3 = 2
            java.lang.String r5 = "last_fire_timestamp"
            r2[r3] = r5
            r3 = 3
            java.lang.String r5 = "last_bundled_timestamp"
            r2[r3] = r5
            r3 = 4
            java.lang.String r5 = "last_bundled_day"
            r2[r3] = r5
            r3 = 5
            java.lang.String r5 = "last_sampled_complex_event_id"
            r2[r3] = r5
            r3 = 6
            java.lang.String r5 = "last_sampling_rate"
            r2[r3] = r5
            r3 = 7
            java.lang.String r5 = "last_exempt_from_sampling"
            r2[r3] = r5
            r3 = 8
            java.lang.String r5 = "current_session_count"
            r2[r3] = r5
            java.util.List r2 = java.util.Arrays.asList(r2)
            r4.<init>(r2)
            r10 = 0
            android.database.sqlite.SQLiteDatabase r2 = r21.mo24238c_()     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            java.lang.String r3 = "events"
            r5 = 0
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            java.lang.Object[] r4 = r4.toArray(r5)     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            java.lang.String r5 = "app_id=? and name=?"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            r7 = 0
            r6[r7] = r22     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            r7 = 1
            r6[r7] = r23     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r20 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x013e, all -> 0x0163 }
            boolean r2 = r20.moveToFirst()     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 != 0) goto L_0x0076
            if (r20 == 0) goto L_0x0074
            r20.close()
        L_0x0074:
            r3 = 0
        L_0x0075:
            return r3
        L_0x0076:
            r2 = 0
            r0 = r20
            long r6 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            r2 = 1
            r0 = r20
            long r8 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            r2 = 2
            r0 = r20
            long r12 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            r2 = 3
            r0 = r20
            boolean r2 = r0.isNull(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 == 0) goto L_0x0107
            r14 = 0
        L_0x0096:
            r2 = 4
            r0 = r20
            boolean r2 = r0.isNull(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 == 0) goto L_0x010f
            r16 = 0
        L_0x00a1:
            r2 = 5
            r0 = r20
            boolean r2 = r0.isNull(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 == 0) goto L_0x011b
            r17 = 0
        L_0x00ac:
            r2 = 6
            r0 = r20
            boolean r2 = r0.isNull(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 == 0) goto L_0x0127
            r18 = 0
        L_0x00b7:
            r19 = 0
            r2 = 7
            r0 = r20
            boolean r2 = r0.isNull(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 != 0) goto L_0x00d4
            r2 = 7
            r0 = r20
            long r2 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            r4 = 1
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0133
            r2 = 1
        L_0x00d0:
            java.lang.Boolean r19 = java.lang.Boolean.valueOf(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
        L_0x00d4:
            r2 = 8
            r0 = r20
            boolean r2 = r0.isNull(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 == 0) goto L_0x0135
            r10 = 0
        L_0x00e0:
            com.google.android.gms.measurement.internal.zzak r3 = new com.google.android.gms.measurement.internal.zzak     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            r4 = r22
            r5 = r23
            r3.<init>(r4, r5, r6, r8, r10, r12, r14, r16, r17, r18, r19)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            boolean r2 = r20.moveToNext()     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            if (r2 == 0) goto L_0x0100
            com.google.android.gms.measurement.internal.zzes r2 = r21.zzr()     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            java.lang.String r4 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r22)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            r2.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
        L_0x0100:
            if (r20 == 0) goto L_0x0075
            r20.close()
            goto L_0x0075
        L_0x0107:
            r2 = 3
            r0 = r20
            long r14 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            goto L_0x0096
        L_0x010f:
            r2 = 4
            r0 = r20
            long r2 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            java.lang.Long r16 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            goto L_0x00a1
        L_0x011b:
            r2 = 5
            r0 = r20
            long r2 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            java.lang.Long r17 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            goto L_0x00ac
        L_0x0127:
            r2 = 6
            r0 = r20
            long r2 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            java.lang.Long r18 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            goto L_0x00b7
        L_0x0133:
            r2 = 0
            goto L_0x00d0
        L_0x0135:
            r2 = 8
            r0 = r20
            long r10 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0172, all -> 0x016c }
            goto L_0x00e0
        L_0x013e:
            r2 = move-exception
            r3 = r10
        L_0x0140:
            com.google.android.gms.measurement.internal.zzes r4 = r21.zzr()     // Catch:{ all -> 0x016e }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ all -> 0x016e }
            java.lang.String r5 = "Error querying events. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r22)     // Catch:{ all -> 0x016e }
            com.google.android.gms.measurement.internal.zzeq r7 = r21.zzo()     // Catch:{ all -> 0x016e }
            r0 = r23
            java.lang.String r7 = r7.zza((java.lang.String) r0)     // Catch:{ all -> 0x016e }
            r4.zza(r5, r6, r7, r2)     // Catch:{ all -> 0x016e }
            if (r3 == 0) goto L_0x0160
            r3.close()
        L_0x0160:
            r3 = 0
            goto L_0x0075
        L_0x0163:
            r2 = move-exception
            r20 = r10
        L_0x0166:
            if (r20 == 0) goto L_0x016b
            r20.close()
        L_0x016b:
            throw r2
        L_0x016c:
            r2 = move-exception
            goto L_0x0166
        L_0x016e:
            r2 = move-exception
            r20 = r3
            goto L_0x0166
        L_0x0172:
            r2 = move-exception
            r3 = r20
            goto L_0x0140
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzak");
    }

    @WorkerThread
    public final void zza(zzak zzak) {
        long j = null;
        Preconditions.checkNotNull(zzak);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzak.zza);
        contentValues.put("name", zzak.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzak.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzak.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzak.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzak.zzg));
        contentValues.put("last_bundled_day", zzak.zzh);
        contentValues.put("last_sampled_complex_event_id", zzak.zzi);
        contentValues.put("last_sampling_rate", zzak.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzak.zze));
        if (zzak.zzk != null && zzak.zzk.booleanValue()) {
            j = 1L;
        }
        contentValues.put("last_exempt_from_sampling", j);
        try {
            if (mo24238c_().insertWithOnConflict("events", (String) null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update event aggregates (got -1). appId", zzes.zza(zzak.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing event aggregates. appId", zzes.zza(zzak.zza), e);
        }
    }

    @WorkerThread
    public final void zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            mo24238c_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting user property. appId", zzes.zza(str), zzo().zzc(str2), e);
        }
    }

    @WorkerThread
    public final boolean zza(zzkj zzkj) {
        Preconditions.checkNotNull(zzkj);
        zzd();
        zzak();
        if (zzc(zzkj.zza, zzkj.zzc) == null) {
            if (zzkm.zza(zzkj.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkj.zza}) >= ((long) zzt().zzc(zzkj.zza))) {
                    return false;
                }
            } else if (!"_npa".equals(zzkj.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkj.zza, zzkj.zzb}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkj.zza);
        contentValues.put("origin", zzkj.zzb);
        contentValues.put("name", zzkj.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzkj.zzd));
        zza(contentValues, "value", zzkj.zze);
        try {
            if (mo24238c_().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update user property (got -1). appId", zzes.zza(zzkj.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing user property. appId", zzes.zza(zzkj.zza), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x009c  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzkj zzc(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            r8 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r9.zzd()
            r9.zzak()
            android.database.sqlite.SQLiteDatabase r0 = r9.mo24238c_()     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            java.lang.String r1 = "user_attributes"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            r3 = 0
            java.lang.String r4 = "set_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            r3 = 1
            java.lang.String r4 = "value"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            r3 = 2
            java.lang.String r4 = "origin"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            java.lang.String r3 = "app_id=? and name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            r5 = 0
            r4[r5] = r10     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            r5 = 1
            r4[r5] = r11     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0099 }
            boolean r0 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            if (r0 != 0) goto L_0x0044
            if (r7 == 0) goto L_0x0042
            r7.close()
        L_0x0042:
            r0 = r8
        L_0x0043:
            return r0
        L_0x0044:
            r0 = 0
            long r4 = r7.getLong(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            r0 = 1
            java.lang.Object r6 = r9.zza((android.database.Cursor) r7, (int) r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            r0 = 2
            java.lang.String r2 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            com.google.android.gms.measurement.internal.zzkj r0 = new com.google.android.gms.measurement.internal.zzkj     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            r1 = r10
            r3 = r11
            r0.<init>(r1, r2, r3, r4, r6)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            boolean r1 = r7.moveToNext()     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            if (r1 == 0) goto L_0x0071
            com.google.android.gms.measurement.internal.zzes r1 = r9.zzr()     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r10)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
            r1.zza(r2, r3)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a0 }
        L_0x0071:
            if (r7 == 0) goto L_0x0043
            r7.close()
            goto L_0x0043
        L_0x0077:
            r0 = move-exception
            r1 = r8
        L_0x0079:
            com.google.android.gms.measurement.internal.zzes r2 = r9.zzr()     // Catch:{ all -> 0x00a3 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r10)     // Catch:{ all -> 0x00a3 }
            com.google.android.gms.measurement.internal.zzeq r5 = r9.zzo()     // Catch:{ all -> 0x00a3 }
            java.lang.String r5 = r5.zzc(r11)     // Catch:{ all -> 0x00a3 }
            r2.zza(r3, r4, r5, r0)     // Catch:{ all -> 0x00a3 }
            if (r1 == 0) goto L_0x0097
            r1.close()
        L_0x0097:
            r0 = r8
            goto L_0x0043
        L_0x0099:
            r0 = move-exception
        L_0x009a:
            if (r8 == 0) goto L_0x009f
            r8.close()
        L_0x009f:
            throw r0
        L_0x00a0:
            r0 = move-exception
            r8 = r7
            goto L_0x009a
        L_0x00a3:
            r0 = move-exception
            r8 = r1
            goto L_0x009a
        L_0x00a6:
            r0 = move-exception
            r1 = r7
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzkj");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00aa  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzkj> zza(java.lang.String r12) {
        /*
            r11 = this;
            r10 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.zzd()
            r11.zzak()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r11.mo24238c_()     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            java.lang.String r1 = "user_attributes"
            r2 = 4
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            r3 = 0
            java.lang.String r4 = "name"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            r3 = 1
            java.lang.String r4 = "origin"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            r3 = 2
            java.lang.String r4 = "set_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            r3 = 3
            java.lang.String r4 = "value"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            r5 = 0
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid"
            java.lang.String r8 = "1000"
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00b4, all -> 0x00a7 }
            boolean r0 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            if (r0 != 0) goto L_0x004b
            if (r7 == 0) goto L_0x0049
            r7.close()
        L_0x0049:
            r0 = r9
        L_0x004a:
            return r0
        L_0x004b:
            r0 = 0
            java.lang.String r3 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            r0 = 1
            java.lang.String r2 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            if (r2 != 0) goto L_0x0059
            java.lang.String r2 = ""
        L_0x0059:
            r0 = 2
            long r4 = r7.getLong(r0)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            r0 = 3
            java.lang.Object r6 = r11.zza((android.database.Cursor) r7, (int) r0)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            if (r6 != 0) goto L_0x0083
            com.google.android.gms.measurement.internal.zzes r0 = r11.zzr()     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzf()     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            java.lang.String r1 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
        L_0x0076:
            boolean r0 = r7.moveToNext()     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            if (r0 != 0) goto L_0x004b
            if (r7 == 0) goto L_0x0081
            r7.close()
        L_0x0081:
            r0 = r9
            goto L_0x004a
        L_0x0083:
            com.google.android.gms.measurement.internal.zzkj r0 = new com.google.android.gms.measurement.internal.zzkj     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r6)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x008d, all -> 0x00ae }
            goto L_0x0076
        L_0x008d:
            r0 = move-exception
            r1 = r7
        L_0x008f:
            com.google.android.gms.measurement.internal.zzes r2 = r11.zzr()     // Catch:{ all -> 0x00b1 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x00b1 }
            java.lang.String r3 = "Error querying user properties. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ all -> 0x00b1 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x00b1 }
            if (r1 == 0) goto L_0x00a5
            r1.close()
        L_0x00a5:
            r0 = r10
            goto L_0x004a
        L_0x00a7:
            r0 = move-exception
        L_0x00a8:
            if (r10 == 0) goto L_0x00ad
            r10.close()
        L_0x00ad:
            throw r0
        L_0x00ae:
            r0 = move-exception
            r10 = r7
            goto L_0x00a8
        L_0x00b1:
            r0 = move-exception
            r10 = r1
            goto L_0x00a8
        L_0x00b4:
            r0 = move-exception
            r1 = r10
            goto L_0x008f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0102, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0106, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0107, code lost:
        r10 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0110, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0106 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x007c] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzkj> zza(java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r11 = this;
            r10 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.zzd()
            r11.zzak()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r1 = 3
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r0.add(r12)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String r1 = "app_id=?"
            r3.<init>(r1)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            boolean r1 = android.text.TextUtils.isEmpty(r13)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            if (r1 != 0) goto L_0x002d
            r0.add(r13)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String r1 = " and origin=?"
            r3.append(r1)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
        L_0x002d:
            boolean r1 = android.text.TextUtils.isEmpty(r14)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            if (r1 != 0) goto L_0x0045
            java.lang.String r1 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String r2 = "*"
            java.lang.String r1 = r1.concat(r2)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r0.add(r1)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String r1 = " and name glob ?"
            r3.append(r1)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
        L_0x0045:
            int r1 = r0.size()     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.Object[] r4 = r0.toArray(r1)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            android.database.sqlite.SQLiteDatabase r0 = r11.mo24238c_()     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String r1 = "user_attributes"
            r2 = 4
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r5 = 0
            java.lang.String r6 = "name"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r5 = 1
            java.lang.String r6 = "set_timestamp"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r5 = 2
            java.lang.String r6 = "value"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r5 = 3
            java.lang.String r6 = "origin"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid"
            java.lang.String r8 = "1001"
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x010c, all -> 0x00ff }
            boolean r0 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            if (r0 != 0) goto L_0x008a
            if (r7 == 0) goto L_0x0087
            r7.close()
        L_0x0087:
            r0 = r9
        L_0x0088:
            return r0
        L_0x0089:
            r13 = r2
        L_0x008a:
            int r0 = r9.size()     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            r1 = 1000(0x3e8, float:1.401E-42)
            if (r0 < r1) goto L_0x00ac
            com.google.android.gms.measurement.internal.zzes r0 = r11.zzr()     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzf()     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            java.lang.String r1 = "Read more than the max allowed user properties, ignoring excess"
            r2 = 1000(0x3e8, float:1.401E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
        L_0x00a5:
            if (r7 == 0) goto L_0x00aa
            r7.close()
        L_0x00aa:
            r0 = r9
            goto L_0x0088
        L_0x00ac:
            r0 = 0
            java.lang.String r3 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            r0 = 1
            long r4 = r7.getLong(r0)     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            r0 = 2
            java.lang.Object r6 = r11.zza((android.database.Cursor) r7, (int) r0)     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            r0 = 3
            java.lang.String r2 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x010f, all -> 0x0106 }
            if (r6 != 0) goto L_0x00da
            com.google.android.gms.measurement.internal.zzes r0 = r11.zzr()     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzf()     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
            java.lang.String r1 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
            r0.zza(r1, r3, r2, r14)     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
        L_0x00d3:
            boolean r0 = r7.moveToNext()     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
            if (r0 != 0) goto L_0x0089
            goto L_0x00a5
        L_0x00da:
            com.google.android.gms.measurement.internal.zzkj r0 = new com.google.android.gms.measurement.internal.zzkj     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r6)     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x00e4, all -> 0x0106 }
            goto L_0x00d3
        L_0x00e4:
            r0 = move-exception
            r1 = r7
            r13 = r2
        L_0x00e7:
            com.google.android.gms.measurement.internal.zzes r2 = r11.zzr()     // Catch:{ all -> 0x0109 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x0109 }
            java.lang.String r3 = "(2)Error querying user properties"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ all -> 0x0109 }
            r2.zza(r3, r4, r13, r0)     // Catch:{ all -> 0x0109 }
            if (r1 == 0) goto L_0x00fd
            r1.close()
        L_0x00fd:
            r0 = r10
            goto L_0x0088
        L_0x00ff:
            r0 = move-exception
        L_0x0100:
            if (r10 == 0) goto L_0x0105
            r10.close()
        L_0x0105:
            throw r0
        L_0x0106:
            r0 = move-exception
            r10 = r7
            goto L_0x0100
        L_0x0109:
            r0 = move-exception
            r10 = r1
            goto L_0x0100
        L_0x010c:
            r0 = move-exception
            r1 = r10
            goto L_0x00e7
        L_0x010f:
            r0 = move-exception
            r1 = r7
            goto L_0x00e7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public final boolean zza(zzw zzw) {
        Preconditions.checkNotNull(zzw);
        zzd();
        zzak();
        if (zzc(zzw.zza, zzw.zzc.zza) == null) {
            if (zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzw.zza}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzw.zza);
        contentValues.put("origin", zzw.zzb);
        contentValues.put("name", zzw.zzc.zza);
        zza(contentValues, "value", zzw.zzc.zza());
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzw.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzw.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzw.zzh));
        zzp();
        contentValues.put("timed_out_event", zzkm.zza((Parcelable) zzw.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzw.zzd));
        zzp();
        contentValues.put("triggered_event", zzkm.zza((Parcelable) zzw.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzw.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzw.zzj));
        zzp();
        contentValues.put("expired_event", zzkm.zza((Parcelable) zzw.zzk));
        try {
            if (mo24238c_().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update conditional user property (got -1)", zzes.zza(zzw.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing conditional user property", zzes.zza(zzw.zza), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x014d  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzw zzd(java.lang.String r22, java.lang.String r23) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r22)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            r21.zzd()
            r21.zzak()
            r10 = 0
            android.database.sqlite.SQLiteDatabase r2 = r21.mo24238c_()     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            java.lang.String r3 = "conditional_properties"
            r4 = 11
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 0
            java.lang.String r6 = "origin"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 1
            java.lang.String r6 = "value"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 2
            java.lang.String r6 = "active"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 3
            java.lang.String r6 = "trigger_event_name"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 4
            java.lang.String r6 = "trigger_timeout"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 5
            java.lang.String r6 = "timed_out_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 6
            java.lang.String r6 = "creation_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 7
            java.lang.String r6 = "triggered_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 8
            java.lang.String r6 = "triggered_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 9
            java.lang.String r6 = "time_to_live"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r5 = 10
            java.lang.String r6 = "expired_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            java.lang.String r5 = "app_id=? and name=?"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r7 = 0
            r6[r7] = r22     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r7 = 1
            r6[r7] = r23     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r20 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0123, all -> 0x0148 }
            boolean r2 = r20.moveToFirst()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            if (r2 != 0) goto L_0x0070
            if (r20 == 0) goto L_0x006e
            r20.close()
        L_0x006e:
            r5 = 0
        L_0x006f:
            return r5
        L_0x0070:
            r2 = 0
            r0 = r20
            java.lang.String r7 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r2 = 1
            r0 = r21
            r1 = r20
            java.lang.Object r6 = r0.zza((android.database.Cursor) r1, (int) r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r2 = 2
            r0 = r20
            int r2 = r0.getInt(r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            if (r2 == 0) goto L_0x0120
            r11 = 1
        L_0x008a:
            r2 = 3
            r0 = r20
            java.lang.String r12 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r2 = 4
            r0 = r20
            long r14 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzki r2 = r21.zzg()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r3 = 5
            r0 = r20
            byte[] r3 = r0.getBlob(r3)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzao> r4 = com.google.android.gms.measurement.internal.zzao.CREATOR     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            android.os.Parcelable r13 = r2.zza((byte[]) r3, r4)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzao r13 = (com.google.android.gms.measurement.internal.zzao) r13     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r2 = 6
            r0 = r20
            long r9 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzki r2 = r21.zzg()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r3 = 7
            r0 = r20
            byte[] r3 = r0.getBlob(r3)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzao> r4 = com.google.android.gms.measurement.internal.zzao.CREATOR     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            android.os.Parcelable r16 = r2.zza((byte[]) r3, r4)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzao r16 = (com.google.android.gms.measurement.internal.zzao) r16     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r2 = 8
            r0 = r20
            long r4 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r2 = 9
            r0 = r20
            long r17 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzki r2 = r21.zzg()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r3 = 10
            r0 = r20
            byte[] r3 = r0.getBlob(r3)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzao> r8 = com.google.android.gms.measurement.internal.zzao.CREATOR     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            android.os.Parcelable r19 = r2.zza((byte[]) r3, r8)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzao r19 = (com.google.android.gms.measurement.internal.zzao) r19     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzkh r2 = new com.google.android.gms.measurement.internal.zzkh     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r3 = r23
            r2.<init>(r3, r4, r6, r7)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzw r5 = new com.google.android.gms.measurement.internal.zzw     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r6 = r22
            r8 = r2
            r5.<init>(r6, r7, r8, r9, r11, r12, r13, r14, r16, r17, r19)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            boolean r2 = r20.moveToNext()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            if (r2 == 0) goto L_0x0119
            com.google.android.gms.measurement.internal.zzes r2 = r21.zzr()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            java.lang.String r3 = "Got multiple records for conditional property, expected one"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r22)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            com.google.android.gms.measurement.internal.zzeq r6 = r21.zzo()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r0 = r23
            java.lang.String r6 = r6.zzc(r0)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
            r2.zza(r3, r4, r6)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0151 }
        L_0x0119:
            if (r20 == 0) goto L_0x006f
            r20.close()
            goto L_0x006f
        L_0x0120:
            r11 = 0
            goto L_0x008a
        L_0x0123:
            r2 = move-exception
            r3 = r10
        L_0x0125:
            com.google.android.gms.measurement.internal.zzes r4 = r21.zzr()     // Catch:{ all -> 0x0153 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ all -> 0x0153 }
            java.lang.String r5 = "Error querying conditional property"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r22)     // Catch:{ all -> 0x0153 }
            com.google.android.gms.measurement.internal.zzeq r7 = r21.zzo()     // Catch:{ all -> 0x0153 }
            r0 = r23
            java.lang.String r7 = r7.zzc(r0)     // Catch:{ all -> 0x0153 }
            r4.zza(r5, r6, r7, r2)     // Catch:{ all -> 0x0153 }
            if (r3 == 0) goto L_0x0145
            r3.close()
        L_0x0145:
            r5 = 0
            goto L_0x006f
        L_0x0148:
            r2 = move-exception
            r20 = r10
        L_0x014b:
            if (r20 == 0) goto L_0x0150
            r20.close()
        L_0x0150:
            throw r2
        L_0x0151:
            r2 = move-exception
            goto L_0x014b
        L_0x0153:
            r2 = move-exception
            r20 = r3
            goto L_0x014b
        L_0x0157:
            r2 = move-exception
            r3 = r20
            goto L_0x0125
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzd(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzw");
    }

    @WorkerThread
    public final int zze(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            return mo24238c_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting conditional property", zzes.zza(str), zzo().zzc(str2), e);
            return 0;
        }
    }

    @WorkerThread
    public final List<zzw> zzb(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0165  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzw> zza(java.lang.String r24, java.lang.String[] r25) {
        /*
            r23 = this;
            r23.zzd()
            r23.zzak()
            java.util.ArrayList r20 = new java.util.ArrayList
            r20.<init>()
            r11 = 0
            android.database.sqlite.SQLiteDatabase r2 = r23.mo24238c_()     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            java.lang.String r3 = "conditional_properties"
            r4 = 13
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 0
            java.lang.String r6 = "app_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 1
            java.lang.String r6 = "origin"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 2
            java.lang.String r6 = "name"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 3
            java.lang.String r6 = "value"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 4
            java.lang.String r6 = "active"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 5
            java.lang.String r6 = "trigger_event_name"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 6
            java.lang.String r6 = "trigger_timeout"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 7
            java.lang.String r6 = "timed_out_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 8
            java.lang.String r6 = "creation_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 9
            java.lang.String r6 = "triggered_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 10
            java.lang.String r6 = "triggered_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 11
            java.lang.String r6 = "time_to_live"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r5 = 12
            java.lang.String r6 = "expired_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1001"
            r5 = r24
            r6 = r25
            android.database.Cursor r21 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0146, all -> 0x0160 }
            boolean r2 = r21.moveToFirst()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            if (r2 != 0) goto L_0x0078
            if (r21 == 0) goto L_0x0075
            r21.close()
        L_0x0075:
            r2 = r20
        L_0x0077:
            return r2
        L_0x0078:
            int r2 = r20.size()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 < r3) goto L_0x009b
            com.google.android.gms.measurement.internal.zzes r2 = r23.zzr()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            java.lang.String r3 = "Read more than the max allowed conditional properties, ignoring extra"
            r4 = 1000(0x3e8, float:1.401E-42)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2.zza(r3, r4)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
        L_0x0093:
            if (r21 == 0) goto L_0x0098
            r21.close()
        L_0x0098:
            r2 = r20
            goto L_0x0077
        L_0x009b:
            r2 = 0
            r0 = r21
            java.lang.String r8 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 1
            r0 = r21
            java.lang.String r7 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 2
            r0 = r21
            java.lang.String r3 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 3
            r0 = r23
            r1 = r21
            java.lang.Object r6 = r0.zza((android.database.Cursor) r1, (int) r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 4
            r0 = r21
            int r2 = r0.getInt(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            if (r2 == 0) goto L_0x0143
            r11 = 1
        L_0x00c3:
            r2 = 5
            r0 = r21
            java.lang.String r12 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 6
            r0 = r21
            long r14 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzki r2 = r23.zzg()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r4 = 7
            r0 = r21
            byte[] r4 = r0.getBlob(r4)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzao> r5 = com.google.android.gms.measurement.internal.zzao.CREATOR     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            android.os.Parcelable r13 = r2.zza((byte[]) r4, r5)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzao r13 = (com.google.android.gms.measurement.internal.zzao) r13     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 8
            r0 = r21
            long r9 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzki r2 = r23.zzg()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r4 = 9
            r0 = r21
            byte[] r4 = r0.getBlob(r4)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzao> r5 = com.google.android.gms.measurement.internal.zzao.CREATOR     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            android.os.Parcelable r16 = r2.zza((byte[]) r4, r5)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzao r16 = (com.google.android.gms.measurement.internal.zzao) r16     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 10
            r0 = r21
            long r4 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2 = 11
            r0 = r21
            long r17 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzki r2 = r23.zzg()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r19 = 12
            r0 = r21
            r1 = r19
            byte[] r19 = r0.getBlob(r1)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzao> r22 = com.google.android.gms.measurement.internal.zzao.CREATOR     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r0 = r19
            r1 = r22
            android.os.Parcelable r19 = r2.zza((byte[]) r0, r1)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzao r19 = (com.google.android.gms.measurement.internal.zzao) r19     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzkh r2 = new com.google.android.gms.measurement.internal.zzkh     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r2.<init>(r3, r4, r6, r7)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            com.google.android.gms.measurement.internal.zzw r5 = new com.google.android.gms.measurement.internal.zzw     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r6 = r8
            r8 = r2
            r5.<init>(r6, r7, r8, r9, r11, r12, r13, r14, r16, r17, r19)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            r0 = r20
            r0.add(r5)     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            boolean r2 = r21.moveToNext()     // Catch:{ SQLiteException -> 0x016f, all -> 0x0169 }
            if (r2 != 0) goto L_0x0078
            goto L_0x0093
        L_0x0143:
            r11 = 0
            goto L_0x00c3
        L_0x0146:
            r2 = move-exception
            r3 = r11
        L_0x0148:
            com.google.android.gms.measurement.internal.zzes r4 = r23.zzr()     // Catch:{ all -> 0x016b }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ all -> 0x016b }
            java.lang.String r5 = "Error querying conditional user property value"
            r4.zza(r5, r2)     // Catch:{ all -> 0x016b }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x016b }
            if (r3 == 0) goto L_0x0077
            r3.close()
            goto L_0x0077
        L_0x0160:
            r2 = move-exception
            r21 = r11
        L_0x0163:
            if (r21 == 0) goto L_0x0168
            r21.close()
        L_0x0168:
            throw r2
        L_0x0169:
            r2 = move-exception
            goto L_0x0163
        L_0x016b:
            r2 = move-exception
            r21 = r3
            goto L_0x0163
        L_0x016f:
            r2 = move-exception
            r3 = r21
            goto L_0x0148
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x02a0  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzf zzb(java.lang.String r12) {
        /*
            r11 = this;
            r8 = 0
            r10 = 1
            r9 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.zzd()
            r11.zzak()
            android.database.sqlite.SQLiteDatabase r0 = r11.mo24238c_()     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            java.lang.String r1 = "apps"
            r2 = 29
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 0
            java.lang.String r4 = "app_instance_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 1
            java.lang.String r4 = "gmp_app_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 2
            java.lang.String r4 = "resettable_device_id_hash"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 3
            java.lang.String r4 = "last_bundle_index"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 4
            java.lang.String r4 = "last_bundle_start_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 5
            java.lang.String r4 = "last_bundle_end_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 6
            java.lang.String r4 = "app_version"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 7
            java.lang.String r4 = "app_store"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 8
            java.lang.String r4 = "gmp_version"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 9
            java.lang.String r4 = "dev_cert_hash"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 10
            java.lang.String r4 = "measurement_enabled"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 11
            java.lang.String r4 = "day"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 12
            java.lang.String r4 = "daily_public_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 13
            java.lang.String r4 = "daily_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 14
            java.lang.String r4 = "daily_conversions_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 15
            java.lang.String r4 = "config_fetched_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 16
            java.lang.String r4 = "failed_config_fetch_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 17
            java.lang.String r4 = "app_version_int"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 18
            java.lang.String r4 = "firebase_instance_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 19
            java.lang.String r4 = "daily_error_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 20
            java.lang.String r4 = "daily_realtime_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 21
            java.lang.String r4 = "health_monitor_sample"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 22
            java.lang.String r4 = "android_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 23
            java.lang.String r4 = "adid_reporting_enabled"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 24
            java.lang.String r4 = "ssaid_reporting_enabled"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 25
            java.lang.String r4 = "admob_app_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 26
            java.lang.String r4 = "dynamite_version"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 27
            java.lang.String r4 = "safelisted_events"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r3 = 28
            java.lang.String r4 = "ga_app_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r5 = 0
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0281, all -> 0x029c }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x02a6 }
            if (r0 != 0) goto L_0x00d8
            if (r1 == 0) goto L_0x00d6
            r1.close()
        L_0x00d6:
            r0 = r8
        L_0x00d7:
            return r0
        L_0x00d8:
            com.google.android.gms.measurement.internal.zzf r0 = new com.google.android.gms.measurement.internal.zzf     // Catch:{ SQLiteException -> 0x02a6 }
            com.google.android.gms.measurement.internal.zzka r2 = r11.zza     // Catch:{ SQLiteException -> 0x02a6 }
            com.google.android.gms.measurement.internal.zzfw r2 = r2.zzs()     // Catch:{ SQLiteException -> 0x02a6 }
            r0.<init>(r2, r12)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 0
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zza((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 1
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 2
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zze((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 3
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzg((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 4
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zza((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 5
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzb((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 6
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzg((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 7
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzh((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 8
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzd((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 9
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zze((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 10
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 != 0) goto L_0x0145
            r2 = 10
            int r2 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x0260
        L_0x0145:
            r2 = r10
        L_0x0146:
            r0.zza((boolean) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 11
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzj(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 12
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzk(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 13
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzl(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 14
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzm(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 15
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzh((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 16
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzi((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 17
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x0263
            r2 = -2147483648(0xffffffff80000000, double:NaN)
        L_0x018a:
            r0.zzc((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 18
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzf((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 19
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzo(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 20
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzn(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 21
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzi((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            com.google.android.gms.measurement.internal.zzy r2 = r11.zzt()     // Catch:{ SQLiteException -> 0x02a6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzcm     // Catch:{ SQLiteException -> 0x02a6 }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r3)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 != 0) goto L_0x01ca
            r2 = 22
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x026c
            r2 = 0
        L_0x01c7:
            r0.zzp(r2)     // Catch:{ SQLiteException -> 0x02a6 }
        L_0x01ca:
            r2 = 23
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 != 0) goto L_0x01da
            r2 = 23
            int r2 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x0274
        L_0x01da:
            r2 = r10
        L_0x01db:
            r0.zzb((boolean) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 24
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 != 0) goto L_0x01ee
            r2 = 24
            int r2 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x0277
        L_0x01ee:
            r2 = r10
        L_0x01ef:
            r0.zzc((boolean) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 25
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzc((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 26
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x027a
            r2 = 0
        L_0x0205:
            r0.zzf((long) r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r2 = 27
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 != 0) goto L_0x0224
            r2 = 27
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            java.lang.String r3 = ","
            r4 = -1
            java.lang.String[] r2 = r2.split(r3, r4)     // Catch:{ SQLiteException -> 0x02a6 }
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zza((java.util.List<java.lang.String>) r2)     // Catch:{ SQLiteException -> 0x02a6 }
        L_0x0224:
            boolean r2 = com.google.android.gms.internal.measurement.zzlb.zzb()     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x023f
            com.google.android.gms.measurement.internal.zzy r2 = r11.zzt()     // Catch:{ SQLiteException -> 0x02a6 }
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzbo     // Catch:{ SQLiteException -> 0x02a6 }
            boolean r2 = r2.zze(r12, r3)     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x023f
            r2 = 28
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzd((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02a6 }
        L_0x023f:
            r0.zzb()     // Catch:{ SQLiteException -> 0x02a6 }
            boolean r2 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x02a6 }
            if (r2 == 0) goto L_0x0259
            com.google.android.gms.measurement.internal.zzes r2 = r11.zzr()     // Catch:{ SQLiteException -> 0x02a6 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x02a6 }
            java.lang.String r3 = "Got multiple records for app, expected one. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x02a6 }
            r2.zza(r3, r4)     // Catch:{ SQLiteException -> 0x02a6 }
        L_0x0259:
            if (r1 == 0) goto L_0x00d7
            r1.close()
            goto L_0x00d7
        L_0x0260:
            r2 = r9
            goto L_0x0146
        L_0x0263:
            r2 = 17
            int r2 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            long r2 = (long) r2     // Catch:{ SQLiteException -> 0x02a6 }
            goto L_0x018a
        L_0x026c:
            r2 = 22
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            goto L_0x01c7
        L_0x0274:
            r2 = r9
            goto L_0x01db
        L_0x0277:
            r2 = r9
            goto L_0x01ef
        L_0x027a:
            r2 = 26
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            goto L_0x0205
        L_0x0281:
            r0 = move-exception
            r1 = r8
        L_0x0283:
            com.google.android.gms.measurement.internal.zzes r2 = r11.zzr()     // Catch:{ all -> 0x02a4 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x02a4 }
            java.lang.String r3 = "Error querying app. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ all -> 0x02a4 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x02a4 }
            if (r1 == 0) goto L_0x0299
            r1.close()
        L_0x0299:
            r0 = r8
            goto L_0x00d7
        L_0x029c:
            r0 = move-exception
            r1 = r8
        L_0x029e:
            if (r1 == 0) goto L_0x02a3
            r1.close()
        L_0x02a3:
            throw r0
        L_0x02a4:
            r0 = move-exception
            goto L_0x029e
        L_0x02a6:
            r0 = move-exception
            goto L_0x0283
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzb(java.lang.String):com.google.android.gms.measurement.internal.zzf");
    }

    @WorkerThread
    public final void zza(zzf zzf2) {
        Preconditions.checkNotNull(zzf2);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzf2.zzc());
        contentValues.put("app_instance_id", zzf2.zzd());
        contentValues.put("gmp_app_id", zzf2.zze());
        contentValues.put("resettable_device_id_hash", zzf2.zzh());
        contentValues.put("last_bundle_index", Long.valueOf(zzf2.zzs()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzf2.zzj()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzf2.zzk()));
        contentValues.put("app_version", zzf2.zzl());
        contentValues.put("app_store", zzf2.zzn());
        contentValues.put("gmp_version", Long.valueOf(zzf2.zzo()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzf2.zzp()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzf2.zzr()));
        contentValues.put("day", Long.valueOf(zzf2.zzw()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzf2.zzx()));
        contentValues.put("daily_events_count", Long.valueOf(zzf2.zzy()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzf2.zzz()));
        contentValues.put("config_fetched_time", Long.valueOf(zzf2.zzt()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzf2.zzu()));
        contentValues.put("app_version_int", Long.valueOf(zzf2.zzm()));
        contentValues.put("firebase_instance_id", zzf2.zzi());
        contentValues.put("daily_error_events_count", Long.valueOf(zzf2.zzab()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzf2.zzaa()));
        contentValues.put("health_monitor_sample", zzf2.zzac());
        contentValues.put("android_id", Long.valueOf(zzf2.zzae()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzf2.zzaf()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzf2.zzag()));
        contentValues.put("admob_app_id", zzf2.zzf());
        contentValues.put("dynamite_version", Long.valueOf(zzf2.zzq()));
        if (zzf2.zzai() != null) {
            if (zzf2.zzai().size() == 0) {
                zzr().zzi().zza("Safelisted events should not be an empty list. appId", zzf2.zzc());
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzf2.zzai()));
            }
        }
        if (zzlb.zzb() && zzt().zze(zzf2.zzc(), zzaq.zzbo)) {
            contentValues.put("ga_app_id", zzf2.zzg());
        }
        try {
            SQLiteDatabase c_ = mo24238c_();
            if (((long) c_.update("apps", contentValues, "app_id = ?", new String[]{zzf2.zzc()})) == 0 && c_.insertWithOnConflict("apps", (String) null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update app (got -1). appId", zzes.zza(zzf2.zzc()));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing app. appId", zzes.zza(zzf2.zzc()), e);
        }
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        try {
            return (long) mo24238c_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzt().zzb(str, zzaq.zzo))))});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting over the limit events. appId", zzes.zza(str), e);
            return 0;
        }
    }

    @WorkerThread
    public final zzac zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zza(j, str, 1, false, false, z3, false, z5);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0130  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzac zza(long r13, java.lang.String r15, long r16, boolean r18, boolean r19, boolean r20, boolean r21, boolean r22) {
        /*
            r12 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)
            r12.zzd()
            r12.zzak()
            r0 = 1
            java.lang.String[] r10 = new java.lang.String[r0]
            r0 = 0
            r10[r0] = r15
            com.google.android.gms.measurement.internal.zzac r8 = new com.google.android.gms.measurement.internal.zzac
            r8.<init>()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r0 = r12.mo24238c_()     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            java.lang.String r1 = "apps"
            r2 = 6
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r3 = 0
            java.lang.String r4 = "day"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r3 = 1
            java.lang.String r4 = "daily_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r3 = 2
            java.lang.String r4 = "daily_public_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r3 = 3
            java.lang.String r4 = "daily_conversions_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r3 = 4
            java.lang.String r4 = "daily_error_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r3 = 5
            java.lang.String r4 = "daily_realtime_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r5 = 0
            r4[r5] = r15     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0111, all -> 0x012c }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0136 }
            if (r2 != 0) goto L_0x0069
            com.google.android.gms.measurement.internal.zzes r0 = r12.zzr()     // Catch:{ SQLiteException -> 0x0136 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzi()     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r2 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r15)     // Catch:{ SQLiteException -> 0x0136 }
            r0.zza(r2, r3)     // Catch:{ SQLiteException -> 0x0136 }
            if (r1 == 0) goto L_0x0067
            r1.close()
        L_0x0067:
            r0 = r8
        L_0x0068:
            return r0
        L_0x0069:
            r2 = 0
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0136 }
            int r2 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x0095
            r2 = 1
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0136 }
            r8.zzb = r2     // Catch:{ SQLiteException -> 0x0136 }
            r2 = 2
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0136 }
            r8.zza = r2     // Catch:{ SQLiteException -> 0x0136 }
            r2 = 3
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0136 }
            r8.zzc = r2     // Catch:{ SQLiteException -> 0x0136 }
            r2 = 4
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0136 }
            r8.zzd = r2     // Catch:{ SQLiteException -> 0x0136 }
            r2 = 5
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0136 }
            r8.zze = r2     // Catch:{ SQLiteException -> 0x0136 }
        L_0x0095:
            if (r18 == 0) goto L_0x009d
            long r2 = r8.zzb     // Catch:{ SQLiteException -> 0x0136 }
            long r2 = r2 + r16
            r8.zzb = r2     // Catch:{ SQLiteException -> 0x0136 }
        L_0x009d:
            if (r19 == 0) goto L_0x00a5
            long r2 = r8.zza     // Catch:{ SQLiteException -> 0x0136 }
            long r2 = r2 + r16
            r8.zza = r2     // Catch:{ SQLiteException -> 0x0136 }
        L_0x00a5:
            if (r20 == 0) goto L_0x00ad
            long r2 = r8.zzc     // Catch:{ SQLiteException -> 0x0136 }
            long r2 = r2 + r16
            r8.zzc = r2     // Catch:{ SQLiteException -> 0x0136 }
        L_0x00ad:
            if (r21 == 0) goto L_0x00b5
            long r2 = r8.zzd     // Catch:{ SQLiteException -> 0x0136 }
            long r2 = r2 + r16
            r8.zzd = r2     // Catch:{ SQLiteException -> 0x0136 }
        L_0x00b5:
            if (r22 == 0) goto L_0x00bd
            long r2 = r8.zze     // Catch:{ SQLiteException -> 0x0136 }
            long r2 = r2 + r16
            r8.zze = r2     // Catch:{ SQLiteException -> 0x0136 }
        L_0x00bd:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x0136 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r3 = "day"
            java.lang.Long r4 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteException -> 0x0136 }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r3 = "daily_public_events_count"
            long r4 = r8.zza     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x0136 }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r3 = "daily_events_count"
            long r4 = r8.zzb     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x0136 }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r3 = "daily_conversions_count"
            long r4 = r8.zzc     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x0136 }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r3 = "daily_error_events_count"
            long r4 = r8.zzd     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x0136 }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r3 = "daily_realtime_events_count"
            long r4 = r8.zze     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x0136 }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r3 = "apps"
            java.lang.String r4 = "app_id=?"
            r0.update(r3, r2, r4, r10)     // Catch:{ SQLiteException -> 0x0136 }
            if (r1 == 0) goto L_0x010e
            r1.close()
        L_0x010e:
            r0 = r8
            goto L_0x0068
        L_0x0111:
            r0 = move-exception
            r1 = r9
        L_0x0113:
            com.google.android.gms.measurement.internal.zzes r2 = r12.zzr()     // Catch:{ all -> 0x0134 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x0134 }
            java.lang.String r3 = "Error updating daily counts. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r15)     // Catch:{ all -> 0x0134 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x0134 }
            if (r1 == 0) goto L_0x0129
            r1.close()
        L_0x0129:
            r0 = r8
            goto L_0x0068
        L_0x012c:
            r0 = move-exception
            r1 = r9
        L_0x012e:
            if (r1 == 0) goto L_0x0133
            r1.close()
        L_0x0133:
            throw r0
        L_0x0134:
            r0 = move-exception
            goto L_0x012e
        L_0x0136:
            r0 = move-exception
            goto L_0x0113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(long, java.lang.String, long, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zzac");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0074  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzd(java.lang.String r10) {
        /*
            r9 = this;
            r8 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r9.zzd()
            r9.zzak()
            android.database.sqlite.SQLiteDatabase r0 = r9.mo24238c_()     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            java.lang.String r1 = "apps"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            r3 = 0
            java.lang.String r4 = "remote_config"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            r5 = 0
            r4[r5] = r10     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x007a }
            if (r0 != 0) goto L_0x0034
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            r0 = r8
        L_0x0033:
            return r0
        L_0x0034:
            r0 = 0
            byte[] r0 = r1.getBlob(r0)     // Catch:{ SQLiteException -> 0x007a }
            boolean r2 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x007a }
            if (r2 == 0) goto L_0x0050
            com.google.android.gms.measurement.internal.zzes r2 = r9.zzr()     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ SQLiteException -> 0x007a }
            java.lang.String r3 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r10)     // Catch:{ SQLiteException -> 0x007a }
            r2.zza(r3, r4)     // Catch:{ SQLiteException -> 0x007a }
        L_0x0050:
            if (r1 == 0) goto L_0x0033
            r1.close()
            goto L_0x0033
        L_0x0056:
            r0 = move-exception
            r1 = r8
        L_0x0058:
            com.google.android.gms.measurement.internal.zzes r2 = r9.zzr()     // Catch:{ all -> 0x0078 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x0078 }
            java.lang.String r3 = "Error querying remote config. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r10)     // Catch:{ all -> 0x0078 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x0078 }
            if (r1 == 0) goto L_0x006e
            r1.close()
        L_0x006e:
            r0 = r8
            goto L_0x0033
        L_0x0070:
            r0 = move-exception
            r1 = r8
        L_0x0072:
            if (r1 == 0) goto L_0x0077
            r1.close()
        L_0x0077:
            throw r0
        L_0x0078:
            r0 = move-exception
            goto L_0x0072
        L_0x007a:
            r0 = move-exception
            goto L_0x0058
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzd(java.lang.String):byte[]");
    }

    @WorkerThread
    public final boolean zza(zzbs.zzg zzg2, boolean z) {
        int i;
        zzd();
        zzak();
        Preconditions.checkNotNull(zzg2);
        Preconditions.checkNotEmpty(zzg2.zzx());
        Preconditions.checkState(zzg2.zzk());
        zzv();
        long currentTimeMillis = zzm().currentTimeMillis();
        if (zzg2.zzl() < currentTimeMillis - zzy.zzk() || zzg2.zzl() > zzy.zzk() + currentTimeMillis) {
            zzr().zzi().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzes.zza(zzg2.zzx()), Long.valueOf(currentTimeMillis), Long.valueOf(zzg2.zzl()));
        }
        try {
            byte[] zzc2 = zzg().zzc(zzg2.zzbi());
            zzr().zzx().zza("Saving bundle, size", Integer.valueOf(zzc2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzg2.zzx());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzg2.zzl()));
            contentValues.put("data", zzc2);
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            contentValues.put("has_realtime", Integer.valueOf(i));
            if (zzg2.zzaz()) {
                contentValues.put("retry_count", Integer.valueOf(zzg2.zzba()));
            }
            try {
                if (mo24238c_().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzr().zzf().zza("Failed to insert bundle (got -1). appId", zzes.zza(zzg2.zzx()));
                return false;
            } catch (SQLiteException e) {
                zzr().zzf().zza("Error storing bundle. appId", zzes.zza(zzg2.zzx()), e);
                return false;
            }
        } catch (IOException e2) {
            zzr().zzf().zza("Data loss. Failed to serialize bundle. appId", zzes.zza(zzg2.zzx()), e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c  */
    @androidx.annotation.WorkerThread
    /* renamed from: d_ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String mo24239d_() {
        /*
            r5 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.mo24238c_()
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            r3 = 0
            android.database.Cursor r2 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0023, all -> 0x0038 }
            boolean r1 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0043 }
            if (r1 == 0) goto L_0x001d
            r1 = 0
            java.lang.String r0 = r2.getString(r1)     // Catch:{ SQLiteException -> 0x0043 }
            if (r2 == 0) goto L_0x001c
            r2.close()
        L_0x001c:
            return r0
        L_0x001d:
            if (r2 == 0) goto L_0x001c
            r2.close()
            goto L_0x001c
        L_0x0023:
            r1 = move-exception
            r2 = r0
        L_0x0025:
            com.google.android.gms.measurement.internal.zzes r3 = r5.zzr()     // Catch:{ all -> 0x0040 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x0040 }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zza(r4, r1)     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x001c
            r2.close()
            goto L_0x001c
        L_0x0038:
            r1 = move-exception
            r2 = r0
        L_0x003a:
            if (r2 == 0) goto L_0x003f
            r2.close()
        L_0x003f:
            throw r1
        L_0x0040:
            r0 = move-exception
            r1 = r0
            goto L_0x003a
        L_0x0043:
            r1 = move-exception
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.mo24239d_():java.lang.String");
    }

    public final boolean zzk() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0101  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzbs.zzg, java.lang.Long>> zza(java.lang.String r12, int r13, int r14) {
        /*
            r11 = this;
            r10 = 0
            r1 = 1
            r9 = 0
            r11.zzd()
            r11.zzak()
            if (r13 <= 0) goto L_0x0053
            r0 = r1
        L_0x000c:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0)
            if (r14 <= 0) goto L_0x0055
        L_0x0011:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r1)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.mo24238c_()     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            java.lang.String r1 = "queue"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            r3 = 0
            java.lang.String r4 = "rowid"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            r3 = 2
            java.lang.String r4 = "retry_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            r5 = 0
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid"
            java.lang.String r8 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00df, all -> 0x00fd }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            if (r0 != 0) goto L_0x0057
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            if (r2 == 0) goto L_0x0052
            r2.close()
        L_0x0052:
            return r0
        L_0x0053:
            r0 = r9
            goto L_0x000c
        L_0x0055:
            r1 = r9
            goto L_0x0011
        L_0x0057:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r3 = r9
        L_0x005d:
            r0 = 0
            long r4 = r2.getLong(r0)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r0 = 1
            byte[] r0 = r2.getBlob(r0)     // Catch:{ IOException -> 0x00b7 }
            com.google.android.gms.measurement.internal.zzki r6 = r11.zzg()     // Catch:{ IOException -> 0x00b7 }
            byte[] r6 = r6.zzb(r0)     // Catch:{ IOException -> 0x00b7 }
            boolean r0 = r1.isEmpty()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            if (r0 != 0) goto L_0x0079
            int r0 = r6.length     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            int r0 = r0 + r3
            if (r0 > r14) goto L_0x00b0
        L_0x0079:
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r0 = com.google.android.gms.internal.measurement.zzbs.zzg.zzbf()     // Catch:{ IOException -> 0x00cb }
            com.google.android.gms.internal.measurement.zzgp r0 = com.google.android.gms.measurement.internal.zzki.zza(r0, (byte[]) r6)     // Catch:{ IOException -> 0x00cb }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r0 = (com.google.android.gms.internal.measurement.zzbs.zzg.zza) r0     // Catch:{ IOException -> 0x00cb }
            r7 = 2
            boolean r7 = r2.isNull(r7)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            if (r7 != 0) goto L_0x0092
            r7 = 2
            int r7 = r2.getInt(r7)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r0.zzi((int) r7)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
        L_0x0092:
            int r6 = r6.length     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            int r3 = r3 + r6
            com.google.android.gms.internal.measurement.zzgm r0 = r0.zzv()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            com.google.android.gms.internal.measurement.zzbs$zzg r0 = (com.google.android.gms.internal.measurement.zzbs.zzg) r0     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            android.util.Pair r0 = android.util.Pair.create(r0, r4)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r1.add(r0)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r0 = r3
        L_0x00a8:
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            if (r3 == 0) goto L_0x00b0
            if (r0 <= r14) goto L_0x010d
        L_0x00b0:
            if (r2 == 0) goto L_0x00b5
            r2.close()
        L_0x00b5:
            r0 = r1
            goto L_0x0052
        L_0x00b7:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r4 = r11.zzr()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            java.lang.String r5 = "Failed to unzip queued bundle. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r4.zza(r5, r6, r0)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r0 = r3
            goto L_0x00a8
        L_0x00cb:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r4 = r11.zzr()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            java.lang.String r5 = "Failed to merge queued bundle. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r4.zza(r5, r6, r0)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0105 }
            r0 = r3
            goto L_0x00a8
        L_0x00df:
            r0 = move-exception
            r1 = r10
        L_0x00e1:
            com.google.android.gms.measurement.internal.zzes r2 = r11.zzr()     // Catch:{ all -> 0x0107 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x0107 }
            java.lang.String r3 = "Error querying bundles. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ all -> 0x0107 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x0107 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0107 }
            if (r1 == 0) goto L_0x0052
            r1.close()
            goto L_0x0052
        L_0x00fd:
            r0 = move-exception
            r2 = r10
        L_0x00ff:
            if (r2 == 0) goto L_0x0104
            r2.close()
        L_0x0104:
            throw r0
        L_0x0105:
            r0 = move-exception
            goto L_0x00ff
        L_0x0107:
            r0 = move-exception
            r2 = r1
            goto L_0x00ff
        L_0x010a:
            r0 = move-exception
            r1 = r2
            goto L_0x00e1
        L_0x010d:
            r3 = r0
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, int, int):java.util.List");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzv() {
        int delete;
        zzd();
        zzak();
        if (zzam()) {
            long zza = zzs().zzf.zza();
            long elapsedRealtime = zzm().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza) > zzaq.zzx.zza(null).longValue()) {
                zzs().zzf.zza(elapsedRealtime);
                zzd();
                zzak();
                if (zzam() && (delete = mo24238c_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzm().currentTimeMillis()), String.valueOf(zzy.zzk())})) > 0) {
                    zzr().zzx().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(List<Long> list) {
        zzd();
        zzak();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzam()) {
            String join = TextUtils.join(",", list);
            String sb = new StringBuilder(String.valueOf(join).length() + 2).append("(").append(join).append(")").toString();
            if (zzb(new StringBuilder(String.valueOf(sb).length() + 80).append("SELECT COUNT(1) FROM queue WHERE rowid IN ").append(sb).append(" AND retry_count =  2147483647 LIMIT 1").toString(), (String[]) null) > 0) {
                zzr().zzi().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                mo24238c_().execSQL(new StringBuilder(String.valueOf(sb).length() + 127).append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ").append(sb).append(" AND (retry_count IS NULL OR retry_count < 2147483647)").toString());
            } catch (SQLiteException e) {
                zzr().zzf().zza("Error incrementing retry count. error", e);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v17, types: [java.lang.Integer] */
    /* JADX WARNING: Multi-variable type inference failed */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r8, int r9, com.google.android.gms.internal.measurement.zzbk.zzb r10) {
        /*
            r7 = this;
            r2 = 0
            r0 = 0
            r7.zzak()
            r7.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            java.lang.String r1 = r10.zzc()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0041
            com.google.android.gms.measurement.internal.zzes r1 = r7.zzr()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzi()
            java.lang.String r3 = "Event filter had no event name. Audience definition ignored. appId, audienceId, filterId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
            boolean r6 = r10.zza()
            if (r6 == 0) goto L_0x0038
            int r0 = r10.zzb()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x0038:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.zza(r3, r4, r5, r0)
            r0 = r2
        L_0x0040:
            return r0
        L_0x0041:
            byte[] r3 = r10.zzbi()
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            java.lang.String r1 = "app_id"
            r4.put(r1, r8)
            java.lang.String r1 = "audience_id"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
            r4.put(r1, r5)
            java.lang.String r5 = "filter_id"
            boolean r1 = r10.zza()
            if (r1 == 0) goto L_0x00b1
            int r1 = r10.zzb()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x0068:
            r4.put(r5, r1)
            java.lang.String r1 = "event_name"
            java.lang.String r5 = r10.zzc()
            r4.put(r1, r5)
            java.lang.String r1 = "session_scoped"
            boolean r5 = r10.zzj()
            if (r5 == 0) goto L_0x0084
            boolean r0 = r10.zzk()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
        L_0x0084:
            r4.put(r1, r0)
            java.lang.String r0 = "data"
            r4.put(r0, r3)
            android.database.sqlite.SQLiteDatabase r0 = r7.mo24238c_()     // Catch:{ SQLiteException -> 0x00b3 }
            java.lang.String r1 = "event_filters"
            r3 = 0
            r5 = 5
            long r0 = r0.insertWithOnConflict(r1, r3, r4, r5)     // Catch:{ SQLiteException -> 0x00b3 }
            r4 = -1
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x00af
            com.google.android.gms.measurement.internal.zzes r0 = r7.zzr()     // Catch:{ SQLiteException -> 0x00b3 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzf()     // Catch:{ SQLiteException -> 0x00b3 }
            java.lang.String r1 = "Failed to insert event filter (got -1). appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)     // Catch:{ SQLiteException -> 0x00b3 }
            r0.zza(r1, r3)     // Catch:{ SQLiteException -> 0x00b3 }
        L_0x00af:
            r0 = 1
            goto L_0x0040
        L_0x00b1:
            r1 = r0
            goto L_0x0068
        L_0x00b3:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r1 = r7.zzr()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()
            java.lang.String r3 = "Error storing event filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)
            r1.zza(r3, r4, r0)
            r0 = r2
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, int, com.google.android.gms.internal.measurement.zzbk$zzb):boolean");
    }

    /* JADX WARNING: type inference failed for: r0v18, types: [java.lang.Integer] */
    /* JADX WARNING: Multi-variable type inference failed */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r8, int r9, com.google.android.gms.internal.measurement.zzbk.zze r10) {
        /*
            r7 = this;
            r2 = 0
            r0 = 0
            r7.zzak()
            r7.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            java.lang.String r1 = r10.zzc()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0041
            com.google.android.gms.measurement.internal.zzes r1 = r7.zzr()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzi()
            java.lang.String r3 = "Property filter had no property name. Audience definition ignored. appId, audienceId, filterId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
            boolean r6 = r10.zza()
            if (r6 == 0) goto L_0x0038
            int r0 = r10.zzb()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x0038:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.zza(r3, r4, r5, r0)
            r0 = r2
        L_0x0040:
            return r0
        L_0x0041:
            byte[] r3 = r10.zzbi()
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            java.lang.String r1 = "app_id"
            r4.put(r1, r8)
            java.lang.String r1 = "audience_id"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
            r4.put(r1, r5)
            java.lang.String r5 = "filter_id"
            boolean r1 = r10.zza()
            if (r1 == 0) goto L_0x00b1
            int r1 = r10.zzb()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x0068:
            r4.put(r5, r1)
            java.lang.String r1 = "property_name"
            java.lang.String r5 = r10.zzc()
            r4.put(r1, r5)
            java.lang.String r1 = "session_scoped"
            boolean r5 = r10.zzg()
            if (r5 == 0) goto L_0x0084
            boolean r0 = r10.zzh()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
        L_0x0084:
            r4.put(r1, r0)
            java.lang.String r0 = "data"
            r4.put(r0, r3)
            android.database.sqlite.SQLiteDatabase r0 = r7.mo24238c_()     // Catch:{ SQLiteException -> 0x00b3 }
            java.lang.String r1 = "property_filters"
            r3 = 0
            r5 = 5
            long r0 = r0.insertWithOnConflict(r1, r3, r4, r5)     // Catch:{ SQLiteException -> 0x00b3 }
            r4 = -1
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x00c8
            com.google.android.gms.measurement.internal.zzes r0 = r7.zzr()     // Catch:{ SQLiteException -> 0x00b3 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzf()     // Catch:{ SQLiteException -> 0x00b3 }
            java.lang.String r1 = "Failed to insert property filter (got -1). appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)     // Catch:{ SQLiteException -> 0x00b3 }
            r0.zza(r1, r3)     // Catch:{ SQLiteException -> 0x00b3 }
            r0 = r2
            goto L_0x0040
        L_0x00b1:
            r1 = r0
            goto L_0x0068
        L_0x00b3:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r1 = r7.zzr()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()
            java.lang.String r3 = "Error storing property filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r8)
            r1.zza(r3, r4, r0)
            r0 = r2
            goto L_0x0040
        L_0x00c8:
            r0 = 1
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, int, com.google.android.gms.internal.measurement.zzbk$zze):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbk.zzb>> zzf(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            r9 = 0
            r10.zzak()
            r10.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.mo24238c_()
            java.lang.String r1 = "event_filters"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            java.lang.String r3 = "app_id=? AND event_name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r5 = 0
            r4[r5] = r11     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r5 = 1
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r0 != 0) goto L_0x0047
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r2 == 0) goto L_0x0046
            r2.close()
        L_0x0046:
            return r0
        L_0x0047:
            r0 = 1
            byte[] r0 = r2.getBlob(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r1 = com.google.android.gms.internal.measurement.zzbk.zzb.zzl()     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzgp r0 = com.google.android.gms.measurement.internal.zzki.zza(r1, (byte[]) r0)     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zzb.zza) r0     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzgm r0 = r0.zzv()     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzbk$zzb r0 = (com.google.android.gms.internal.measurement.zzbk.zzb) r0     // Catch:{ IOException -> 0x008b }
            r1 = 0
            int r3 = r2.getInt(r1)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.Object r1 = r8.get(r1)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r1 != 0) goto L_0x007b
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            r8.put(r3, r1)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
        L_0x007b:
            r1.add(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
        L_0x007e:
            boolean r0 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r0 != 0) goto L_0x0047
            if (r2 == 0) goto L_0x0089
            r2.close()
        L_0x0089:
            r0 = r8
            goto L_0x0046
        L_0x008b:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r1 = r10.zzr()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            r1.zza(r3, r4, r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            goto L_0x007e
        L_0x009e:
            r0 = move-exception
            r1 = r2
        L_0x00a0:
            com.google.android.gms.measurement.internal.zzes r2 = r10.zzr()     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r11)     // Catch:{ all -> 0x00c2 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x00c2 }
            if (r1 == 0) goto L_0x00b6
            r1.close()
        L_0x00b6:
            r0 = r9
            goto L_0x0046
        L_0x00b8:
            r0 = move-exception
            r2 = r9
        L_0x00ba:
            if (r2 == 0) goto L_0x00bf
            r2.close()
        L_0x00bf:
            throw r0
        L_0x00c0:
            r0 = move-exception
            goto L_0x00ba
        L_0x00c2:
            r0 = move-exception
            r2 = r1
            goto L_0x00ba
        L_0x00c5:
            r0 = move-exception
            r1 = r9
            goto L_0x00a0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzf(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbk.zzb>> zze(java.lang.String r11) {
        /*
            r10 = this;
            r9 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.mo24238c_()
            java.lang.String r1 = "event_filters"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00b5 }
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00b5 }
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00b5 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00b5 }
            r5 = 0
            r4[r5] = r11     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00b5 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x00c2, all -> 0x00b5 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            if (r0 != 0) goto L_0x003b
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            if (r2 == 0) goto L_0x003a
            r2.close()
        L_0x003a:
            return r0
        L_0x003b:
            r0 = 1
            byte[] r0 = r2.getBlob(r0)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r1 = com.google.android.gms.internal.measurement.zzbk.zzb.zzl()     // Catch:{ IOException -> 0x0085 }
            com.google.android.gms.internal.measurement.zzgp r0 = com.google.android.gms.measurement.internal.zzki.zza(r1, (byte[]) r0)     // Catch:{ IOException -> 0x0085 }
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zzb.zza) r0     // Catch:{ IOException -> 0x0085 }
            com.google.android.gms.internal.measurement.zzgm r0 = r0.zzv()     // Catch:{ IOException -> 0x0085 }
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0     // Catch:{ IOException -> 0x0085 }
            com.google.android.gms.internal.measurement.zzbk$zzb r0 = (com.google.android.gms.internal.measurement.zzbk.zzb) r0     // Catch:{ IOException -> 0x0085 }
            boolean r1 = r0.zzf()     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            if (r1 == 0) goto L_0x0078
            r1 = 0
            int r3 = r2.getInt(r1)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            java.lang.Object r1 = r8.get(r1)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            java.util.List r1 = (java.util.List) r1     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            if (r1 != 0) goto L_0x0075
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            r8.put(r3, r1)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
        L_0x0075:
            r1.add(r0)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
        L_0x0078:
            boolean r0 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            if (r0 != 0) goto L_0x003b
            if (r2 == 0) goto L_0x0083
            r2.close()
        L_0x0083:
            r0 = r8
            goto L_0x003a
        L_0x0085:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r1 = r10.zzr()     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            r1.zza(r3, r4, r0)     // Catch:{ SQLiteException -> 0x0098, all -> 0x00bd }
            goto L_0x0078
        L_0x0098:
            r0 = move-exception
            r1 = r2
        L_0x009a:
            com.google.android.gms.measurement.internal.zzes r2 = r10.zzr()     // Catch:{ all -> 0x00bf }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r11)     // Catch:{ all -> 0x00bf }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x00bf }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x003a
            r1.close()
            goto L_0x003a
        L_0x00b5:
            r0 = move-exception
            r2 = r9
        L_0x00b7:
            if (r2 == 0) goto L_0x00bc
            r2.close()
        L_0x00bc:
            throw r0
        L_0x00bd:
            r0 = move-exception
            goto L_0x00b7
        L_0x00bf:
            r0 = move-exception
            r2 = r1
            goto L_0x00b7
        L_0x00c2:
            r0 = move-exception
            r1 = r9
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zze(java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbk.zze>> zzg(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            r9 = 0
            r10.zzak()
            r10.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.mo24238c_()
            java.lang.String r1 = "property_filters"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            java.lang.String r3 = "app_id=? AND property_name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r5 = 0
            r4[r5] = r11     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r5 = 1
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x00c5, all -> 0x00b8 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r0 != 0) goto L_0x0047
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r2 == 0) goto L_0x0046
            r2.close()
        L_0x0046:
            return r0
        L_0x0047:
            r0 = 1
            byte[] r0 = r2.getBlob(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            com.google.android.gms.internal.measurement.zzbk$zze$zza r1 = com.google.android.gms.internal.measurement.zzbk.zze.zzi()     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzgp r0 = com.google.android.gms.measurement.internal.zzki.zza(r1, (byte[]) r0)     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzbk$zze$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zze.zza) r0     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzgm r0 = r0.zzv()     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0     // Catch:{ IOException -> 0x008b }
            com.google.android.gms.internal.measurement.zzbk$zze r0 = (com.google.android.gms.internal.measurement.zzbk.zze) r0     // Catch:{ IOException -> 0x008b }
            r1 = 0
            int r3 = r2.getInt(r1)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.Object r1 = r8.get(r1)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r1 != 0) goto L_0x007b
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            r8.put(r3, r1)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
        L_0x007b:
            r1.add(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
        L_0x007e:
            boolean r0 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            if (r0 != 0) goto L_0x0047
            if (r2 == 0) goto L_0x0089
            r2.close()
        L_0x0089:
            r0 = r8
            goto L_0x0046
        L_0x008b:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r1 = r10.zzr()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            r1.zza(r3, r4, r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x00c0 }
            goto L_0x007e
        L_0x009e:
            r0 = move-exception
            r1 = r2
        L_0x00a0:
            com.google.android.gms.measurement.internal.zzes r2 = r10.zzr()     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r11)     // Catch:{ all -> 0x00c2 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x00c2 }
            if (r1 == 0) goto L_0x00b6
            r1.close()
        L_0x00b6:
            r0 = r9
            goto L_0x0046
        L_0x00b8:
            r0 = move-exception
            r2 = r9
        L_0x00ba:
            if (r2 == 0) goto L_0x00bf
            r2.close()
        L_0x00bf:
            throw r0
        L_0x00c0:
            r0 = move-exception
            goto L_0x00ba
        L_0x00c2:
            r0 = move-exception
            r2 = r1
            goto L_0x00ba
        L_0x00c5:
            r0 = move-exception
            r1 = r9
            goto L_0x00a0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzg(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<java.lang.Integer>> zzf(java.lang.String r7) {
        /*
            r6 = this;
            r2 = 0
            r6.zzak()
            r6.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r6.mo24238c_()
            java.lang.String r3 = "select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0068, all -> 0x0082 }
            r5 = 0
            r4[r5] = r7     // Catch:{ SQLiteException -> 0x0068, all -> 0x0082 }
            r5 = 1
            r4[r5] = r7     // Catch:{ SQLiteException -> 0x0068, all -> 0x0082 }
            android.database.Cursor r3 = r0.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x0068, all -> 0x0082 }
            boolean r0 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            if (r0 != 0) goto L_0x0032
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            if (r3 == 0) goto L_0x0031
            r3.close()
        L_0x0031:
            return r0
        L_0x0032:
            r0 = 0
            int r4 = r3.getInt(r0)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            java.lang.Object r0 = r1.get(r0)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            java.util.List r0 = (java.util.List) r0     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            if (r0 != 0) goto L_0x004f
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            r0.<init>()     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            r1.put(r4, r0)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
        L_0x004f:
            r4 = 1
            int r4 = r3.getInt(r4)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            r0.add(r4)     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            boolean r0 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x008f, all -> 0x008a }
            if (r0 != 0) goto L_0x0032
            if (r3 == 0) goto L_0x0066
            r3.close()
        L_0x0066:
            r0 = r1
            goto L_0x0031
        L_0x0068:
            r0 = move-exception
            r1 = r2
        L_0x006a:
            com.google.android.gms.measurement.internal.zzes r3 = r6.zzr()     // Catch:{ all -> 0x008c }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x008c }
            java.lang.String r4 = "Database error querying scoped filters. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r7)     // Catch:{ all -> 0x008c }
            r3.zza(r4, r5, r0)     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x0080
            r1.close()
        L_0x0080:
            r0 = r2
            goto L_0x0031
        L_0x0082:
            r0 = move-exception
            r3 = r2
        L_0x0084:
            if (r3 == 0) goto L_0x0089
            r3.close()
        L_0x0089:
            throw r0
        L_0x008a:
            r0 = move-exception
            goto L_0x0084
        L_0x008c:
            r0 = move-exception
            r3 = r1
            goto L_0x0084
        L_0x008f:
            r0 = move-exception
            r1 = r3
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzf(java.lang.String):java.util.Map");
    }

    private final boolean zzb(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzak();
        zzd();
        SQLiteDatabase c_ = mo24238c_();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE, zzt().zzb(str, zzaq.zzae)));
            if (zzb2 <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            String sb = new StringBuilder(String.valueOf(join).length() + 2).append("(").append(join).append(")").toString();
            if (c_.delete("audience_filter_values", new StringBuilder(String.valueOf(sb).length() + 140).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(sb).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(max)}) > 0) {
                return true;
            }
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Database error querying filters. appId", zzes.zza(str), e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzbs.zzi> zzg(java.lang.String r10) {
        /*
            r9 = this;
            r8 = 0
            r9.zzak()
            r9.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            android.database.sqlite.SQLiteDatabase r0 = r9.mo24238c_()
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
            r3 = 1
            java.lang.String r4 = "current_results"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
            r5 = 0
            r4[r5] = r10     // Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            if (r0 != 0) goto L_0x0039
            if (r2 == 0) goto L_0x0037
            r2.close()
        L_0x0037:
            r0 = r8
        L_0x0038:
            return r0
        L_0x0039:
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
        L_0x003e:
            r0 = 0
            int r3 = r2.getInt(r0)     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            r0 = 1
            byte[] r0 = r2.getBlob(r0)     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r4 = com.google.android.gms.internal.measurement.zzbs.zzi.zzi()     // Catch:{ IOException -> 0x006e }
            com.google.android.gms.internal.measurement.zzgp r0 = com.google.android.gms.measurement.internal.zzki.zza(r4, (byte[]) r0)     // Catch:{ IOException -> 0x006e }
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r0 = (com.google.android.gms.internal.measurement.zzbs.zzi.zza) r0     // Catch:{ IOException -> 0x006e }
            com.google.android.gms.internal.measurement.zzgm r0 = r0.zzv()     // Catch:{ IOException -> 0x006e }
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0     // Catch:{ IOException -> 0x006e }
            com.google.android.gms.internal.measurement.zzbs$zzi r0 = (com.google.android.gms.internal.measurement.zzbs.zzi) r0     // Catch:{ IOException -> 0x006e }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            r1.put(r3, r0)     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
        L_0x0061:
            boolean r0 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            if (r0 != 0) goto L_0x003e
            if (r2 == 0) goto L_0x006c
            r2.close()
        L_0x006c:
            r0 = r1
            goto L_0x0038
        L_0x006e:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r4 = r9.zzr()     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzf()     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r10)     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            r4.zza(r5, r6, r3, r0)     // Catch:{ SQLiteException -> 0x0085, all -> 0x00a7 }
            goto L_0x0061
        L_0x0085:
            r0 = move-exception
            r1 = r2
        L_0x0087:
            com.google.android.gms.measurement.internal.zzes r2 = r9.zzr()     // Catch:{ all -> 0x00a9 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x00a9 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r10)     // Catch:{ all -> 0x00a9 }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x00a9 }
            if (r1 == 0) goto L_0x009d
            r1.close()
        L_0x009d:
            r0 = r8
            goto L_0x0038
        L_0x009f:
            r0 = move-exception
            r2 = r8
        L_0x00a1:
            if (r2 == 0) goto L_0x00a6
            r2.close()
        L_0x00a6:
            throw r0
        L_0x00a7:
            r0 = move-exception
            goto L_0x00a1
        L_0x00a9:
            r0 = move-exception
            r2 = r1
            goto L_0x00a1
        L_0x00ac:
            r0 = move-exception
            r1 = r8
            goto L_0x0087
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzg(java.lang.String):java.util.Map");
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzr().zzf().zza("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzr().zzf().zza("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzr().zzf().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    public final long zzw() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final long zzh(String str, String str2) {
        long j;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        SQLiteDatabase c_ = mo24238c_();
        c_.beginTransaction();
        try {
            j = zza(new StringBuilder(String.valueOf(str2).length() + 32).append("select ").append(str2).append(" from app2 where app_id=?").toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (c_.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    zzr().zzf().zza("Failed to insert column (got -1). appId", zzes.zza(str), str2);
                    c_.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) c_.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzr().zzf().zza("Failed to update column (got 0). appId", zzes.zza(str), str2);
                    c_.endTransaction();
                    return -1;
                }
                c_.setTransactionSuccessful();
                c_.endTransaction();
                return j;
            } catch (SQLiteException e) {
                e = e;
                try {
                    zzr().zzf().zza("Error inserting column. appId", zzes.zza(str), str2, e);
                    return j;
                } finally {
                    c_.endTransaction();
                }
            }
        } catch (SQLiteException e2) {
            e = e2;
            j = 0;
            zzr().zzf().zza("Error inserting column. appId", zzes.zza(str), str2, e);
            return j;
        }
    }

    @WorkerThread
    public final long zzx() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zza(zzbs.zzg zzg2) throws IOException {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzg2);
        Preconditions.checkNotEmpty(zzg2.zzx());
        byte[] zzbi = zzg2.zzbi();
        long zza = zzg().zza(zzbi);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzg2.zzx());
        contentValues.put("metadata_fingerprint", Long.valueOf(zza));
        contentValues.put("metadata", zzbi);
        try {
            mo24238c_().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
            return zza;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing raw event metadata. appId", zzes.zza(zzg2.zzx()), e);
            throw e;
        }
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzh(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(long r8) {
        /*
            r7 = this;
            r0 = 0
            r7.zzd()
            r7.zzak()
            android.database.sqlite.SQLiteDatabase r1 = r7.mo24238c_()     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            r4 = 0
            java.lang.String r5 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            r3[r4] = r5     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            android.database.Cursor r2 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            boolean r1 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x005f }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.measurement.internal.zzes r1 = r7.zzr()     // Catch:{ SQLiteException -> 0x005f }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzx()     // Catch:{ SQLiteException -> 0x005f }
            java.lang.String r3 = "No expired configs for apps with pending events"
            r1.zza(r3)     // Catch:{ SQLiteException -> 0x005f }
            if (r2 == 0) goto L_0x0033
            r2.close()
        L_0x0033:
            return r0
        L_0x0034:
            r1 = 0
            java.lang.String r0 = r2.getString(r1)     // Catch:{ SQLiteException -> 0x005f }
            if (r2 == 0) goto L_0x0033
            r2.close()
            goto L_0x0033
        L_0x003f:
            r1 = move-exception
            r2 = r0
        L_0x0041:
            com.google.android.gms.measurement.internal.zzes r3 = r7.zzr()     // Catch:{ all -> 0x005c }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x005c }
            java.lang.String r4 = "Error selecting expired configs"
            r3.zza(r4, r1)     // Catch:{ all -> 0x005c }
            if (r2 == 0) goto L_0x0033
            r2.close()
            goto L_0x0033
        L_0x0054:
            r1 = move-exception
            r2 = r0
        L_0x0056:
            if (r2 == 0) goto L_0x005b
            r2.close()
        L_0x005b:
            throw r1
        L_0x005c:
            r0 = move-exception
            r1 = r0
            goto L_0x0056
        L_0x005f:
            r1 = move-exception
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(long):java.lang.String");
    }

    public final long zzaa() {
        long j = -1;
        Cursor cursor = null;
        try {
            cursor = mo24238c_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", (String[]) null);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzbs.zzc, java.lang.Long> zza(java.lang.String r7, java.lang.Long r8) {
        /*
            r6 = this;
            r1 = 0
            r6.zzd()
            r6.zzak()
            android.database.sqlite.SQLiteDatabase r0 = r6.mo24238c_()     // Catch:{ SQLiteException -> 0x007b, all -> 0x0091 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x007b, all -> 0x0091 }
            r4 = 0
            r3[r4] = r7     // Catch:{ SQLiteException -> 0x007b, all -> 0x0091 }
            r4 = 1
            java.lang.String r5 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x007b, all -> 0x0091 }
            r3[r4] = r5     // Catch:{ SQLiteException -> 0x007b, all -> 0x0091 }
            android.database.Cursor r2 = r0.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x007b, all -> 0x0091 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x009b }
            if (r0 != 0) goto L_0x0038
            com.google.android.gms.measurement.internal.zzes r0 = r6.zzr()     // Catch:{ SQLiteException -> 0x009b }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzx()     // Catch:{ SQLiteException -> 0x009b }
            java.lang.String r3 = "Main event not found"
            r0.zza(r3)     // Catch:{ SQLiteException -> 0x009b }
            if (r2 == 0) goto L_0x0036
            r2.close()
        L_0x0036:
            r0 = r1
        L_0x0037:
            return r0
        L_0x0038:
            r0 = 0
            byte[] r0 = r2.getBlob(r0)     // Catch:{ SQLiteException -> 0x009b }
            r3 = 1
            long r4 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x009b }
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x009b }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r4 = com.google.android.gms.internal.measurement.zzbs.zzc.zzj()     // Catch:{ IOException -> 0x0062 }
            com.google.android.gms.internal.measurement.zzgp r0 = com.google.android.gms.measurement.internal.zzki.zza(r4, (byte[]) r0)     // Catch:{ IOException -> 0x0062 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r0 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r0     // Catch:{ IOException -> 0x0062 }
            com.google.android.gms.internal.measurement.zzgm r0 = r0.zzv()     // Catch:{ IOException -> 0x0062 }
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0     // Catch:{ IOException -> 0x0062 }
            com.google.android.gms.internal.measurement.zzbs$zzc r0 = (com.google.android.gms.internal.measurement.zzbs.zzc) r0     // Catch:{ IOException -> 0x0062 }
            android.util.Pair r0 = android.util.Pair.create(r0, r3)     // Catch:{ SQLiteException -> 0x009b }
            if (r2 == 0) goto L_0x0037
            r2.close()
            goto L_0x0037
        L_0x0062:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzes r3 = r6.zzr()     // Catch:{ SQLiteException -> 0x009b }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ SQLiteException -> 0x009b }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x009b }
            r3.zza(r4, r5, r8, r0)     // Catch:{ SQLiteException -> 0x009b }
            if (r2 == 0) goto L_0x0079
            r2.close()
        L_0x0079:
            r0 = r1
            goto L_0x0037
        L_0x007b:
            r0 = move-exception
            r2 = r1
        L_0x007d:
            com.google.android.gms.measurement.internal.zzes r3 = r6.zzr()     // Catch:{ all -> 0x0099 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x0099 }
            java.lang.String r4 = "Error selecting main event"
            r3.zza(r4, r0)     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x008f
            r2.close()
        L_0x008f:
            r0 = r1
            goto L_0x0037
        L_0x0091:
            r0 = move-exception
            r2 = r1
        L_0x0093:
            if (r2 == 0) goto L_0x0098
            r2.close()
        L_0x0098:
            throw r0
        L_0x0099:
            r0 = move-exception
            goto L_0x0093
        L_0x009b:
            r0 = move-exception
            goto L_0x007d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final boolean zza(String str, Long l, long j, zzbs.zzc zzc2) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzc2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbi = zzc2.zzbi();
        zzr().zzx().zza("Saving complex main event, appId, data size", zzo().zza(str), Integer.valueOf(zzbi.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbi);
        try {
            if (mo24238c_().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert complex main event (got -1). appId", zzes.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing complex main event. appId", zzes.zza(str), e);
            return false;
        }
    }

    public final boolean zza(zzal zzal, long j, boolean z) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzal);
        Preconditions.checkNotEmpty(zzal.zza);
        zzbs.zzc.zza zzb2 = zzbs.zzc.zzj().zzb(zzal.zzd);
        Iterator<String> it = zzal.zze.iterator();
        while (it.hasNext()) {
            String next = it.next();
            zzbs.zze.zza zza = zzbs.zze.zzk().zza(next);
            zzg().zza(zza, zzal.zze.zza(next));
            zzb2.zza(zza);
        }
        byte[] zzbi = ((zzbs.zzc) ((zzfe) zzb2.zzv())).zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzal.zza);
        contentValues.put("name", zzal.zzb);
        contentValues.put("timestamp", Long.valueOf(zzal.zzc));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", zzbi);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (mo24238c_().insert("raw_events", (String) null, contentValues) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert raw event (got -1). appId", zzes.zza(zzal.zza));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing raw event. appId", zzes.zza(zzal.zza), e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0110 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01e4 A[Catch:{ all -> 0x0140 }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r12, java.util.List<com.google.android.gms.internal.measurement.zzbk.zza> r13) {
        /*
            r11 = this;
            r7 = 1
            r4 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            r3 = r4
        L_0x0006:
            int r0 = r13.size()
            if (r3 >= r0) goto L_0x00d2
            java.lang.Object r0 = r13.get(r3)
            com.google.android.gms.internal.measurement.zzbk$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zza) r0
            com.google.android.gms.internal.measurement.zzfe$zza r0 = r0.zzbl()
            com.google.android.gms.internal.measurement.zzfe$zza r0 = (com.google.android.gms.internal.measurement.zzfe.zza) r0
            com.google.android.gms.internal.measurement.zzbk$zza$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zza.C1798zza) r0
            int r1 = r0.zzb()
            if (r1 == 0) goto L_0x0092
            r5 = r4
            r6 = r0
        L_0x0022:
            int r0 = r6.zzb()
            if (r5 >= r0) goto L_0x0093
            com.google.android.gms.internal.measurement.zzbk$zzb r0 = r6.zzb(r5)
            com.google.android.gms.internal.measurement.zzfe$zza r0 = r0.zzbl()
            com.google.android.gms.internal.measurement.zzfe$zza r0 = (com.google.android.gms.internal.measurement.zzfe.zza) r0
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zzb.zza) r0
            java.lang.Object r1 = r0.clone()
            com.google.android.gms.internal.measurement.zzfe$zza r1 = (com.google.android.gms.internal.measurement.zzfe.zza) r1
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r1 = (com.google.android.gms.internal.measurement.zzbk.zzb.zza) r1
            java.lang.String r2 = r0.zza()
            java.lang.String r2 = com.google.android.gms.measurement.internal.zzgv.zzb(r2)
            if (r2 == 0) goto L_0x0256
            r1.zza((java.lang.String) r2)
            r2 = r7
        L_0x004a:
            r8 = r4
            r9 = r2
        L_0x004c:
            int r2 = r0.zzb()
            if (r8 >= r2) goto L_0x007c
            com.google.android.gms.internal.measurement.zzbk$zzc r2 = r0.zza((int) r8)
            java.lang.String r10 = r2.zzh()
            java.lang.String r10 = com.google.android.gms.measurement.internal.zzgu.zza(r10)
            if (r10 == 0) goto L_0x0078
            com.google.android.gms.internal.measurement.zzfe$zza r2 = r2.zzbl()
            com.google.android.gms.internal.measurement.zzfe$zza r2 = (com.google.android.gms.internal.measurement.zzfe.zza) r2
            com.google.android.gms.internal.measurement.zzbk$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbk.zzc.zza) r2
            com.google.android.gms.internal.measurement.zzbk$zzc$zza r2 = r2.zza(r10)
            com.google.android.gms.internal.measurement.zzgm r2 = r2.zzv()
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2
            com.google.android.gms.internal.measurement.zzbk$zzc r2 = (com.google.android.gms.internal.measurement.zzbk.zzc) r2
            r1.zza(r8, r2)
            r9 = r7
        L_0x0078:
            int r2 = r8 + 1
            r8 = r2
            goto L_0x004c
        L_0x007c:
            if (r9 == 0) goto L_0x0253
            com.google.android.gms.internal.measurement.zzbk$zza$zza r1 = r6.zza((int) r5, (com.google.android.gms.internal.measurement.zzbk.zzb.zza) r1)
            com.google.android.gms.internal.measurement.zzgm r0 = r1.zzv()
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0
            com.google.android.gms.internal.measurement.zzbk$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zza) r0
            r13.set(r3, r0)
        L_0x008d:
            int r0 = r5 + 1
            r5 = r0
            r6 = r1
            goto L_0x0022
        L_0x0092:
            r6 = r0
        L_0x0093:
            int r0 = r6.zza()
            if (r0 == 0) goto L_0x00cd
            r1 = r4
        L_0x009a:
            int r0 = r6.zza()
            if (r1 >= r0) goto L_0x00cd
            com.google.android.gms.internal.measurement.zzbk$zze r0 = r6.zza(r1)
            java.lang.String r2 = r0.zzc()
            java.lang.String r2 = com.google.android.gms.measurement.internal.zzgx.zza(r2)
            if (r2 == 0) goto L_0x00c9
            com.google.android.gms.internal.measurement.zzfe$zza r0 = r0.zzbl()
            com.google.android.gms.internal.measurement.zzfe$zza r0 = (com.google.android.gms.internal.measurement.zzfe.zza) r0
            com.google.android.gms.internal.measurement.zzbk$zze$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zze.zza) r0
            com.google.android.gms.internal.measurement.zzbk$zze$zza r0 = r0.zza(r2)
            com.google.android.gms.internal.measurement.zzbk$zza$zza r6 = r6.zza((int) r1, (com.google.android.gms.internal.measurement.zzbk.zze.zza) r0)
            com.google.android.gms.internal.measurement.zzgm r0 = r6.zzv()
            com.google.android.gms.internal.measurement.zzfe r0 = (com.google.android.gms.internal.measurement.zzfe) r0
            com.google.android.gms.internal.measurement.zzbk$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zza) r0
            r13.set(r3, r0)
        L_0x00c9:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x009a
        L_0x00cd:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x0006
        L_0x00d2:
            r11.zzak()
            r11.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            android.database.sqlite.SQLiteDatabase r2 = r11.mo24238c_()
            r2.beginTransaction()
            r11.zzak()     // Catch:{ all -> 0x0140 }
            r11.zzd()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)     // Catch:{ all -> 0x0140 }
            android.database.sqlite.SQLiteDatabase r0 = r11.mo24238c_()     // Catch:{ all -> 0x0140 }
            java.lang.String r1 = "property_filters"
            java.lang.String r3 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ all -> 0x0140 }
            r6 = 0
            r5[r6] = r12     // Catch:{ all -> 0x0140 }
            r0.delete(r1, r3, r5)     // Catch:{ all -> 0x0140 }
            java.lang.String r1 = "event_filters"
            java.lang.String r3 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ all -> 0x0140 }
            r6 = 0
            r5[r6] = r12     // Catch:{ all -> 0x0140 }
            r0.delete(r1, r3, r5)     // Catch:{ all -> 0x0140 }
            java.util.Iterator r3 = r13.iterator()     // Catch:{ all -> 0x0140 }
        L_0x0110:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x0140 }
            if (r0 == 0) goto L_0x021b
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.internal.measurement.zzbk$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zza) r0     // Catch:{ all -> 0x0140 }
            r11.zzak()     // Catch:{ all -> 0x0140 }
            r11.zzd()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)     // Catch:{ all -> 0x0140 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x0140 }
            boolean r1 = r0.zza()     // Catch:{ all -> 0x0140 }
            if (r1 != 0) goto L_0x0145
            com.google.android.gms.measurement.internal.zzes r0 = r11.zzr()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzi()     // Catch:{ all -> 0x0140 }
            java.lang.String r1 = "Audience with no ID. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ all -> 0x0140 }
            r0.zza(r1, r5)     // Catch:{ all -> 0x0140 }
            goto L_0x0110
        L_0x0140:
            r0 = move-exception
            r2.endTransaction()
            throw r0
        L_0x0145:
            int r5 = r0.zzb()     // Catch:{ all -> 0x0140 }
            java.util.List r1 = r0.zze()     // Catch:{ all -> 0x0140 }
            java.util.Iterator r6 = r1.iterator()     // Catch:{ all -> 0x0140 }
        L_0x0151:
            boolean r1 = r6.hasNext()     // Catch:{ all -> 0x0140 }
            if (r1 == 0) goto L_0x0179
            java.lang.Object r1 = r6.next()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.internal.measurement.zzbk$zzb r1 = (com.google.android.gms.internal.measurement.zzbk.zzb) r1     // Catch:{ all -> 0x0140 }
            boolean r1 = r1.zza()     // Catch:{ all -> 0x0140 }
            if (r1 != 0) goto L_0x0151
            com.google.android.gms.measurement.internal.zzes r0 = r11.zzr()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzi()     // Catch:{ all -> 0x0140 }
            java.lang.String r1 = "Event filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ all -> 0x0140 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0140 }
            r0.zza(r1, r6, r5)     // Catch:{ all -> 0x0140 }
            goto L_0x0110
        L_0x0179:
            java.util.List r1 = r0.zzc()     // Catch:{ all -> 0x0140 }
            java.util.Iterator r6 = r1.iterator()     // Catch:{ all -> 0x0140 }
        L_0x0181:
            boolean r1 = r6.hasNext()     // Catch:{ all -> 0x0140 }
            if (r1 == 0) goto L_0x01aa
            java.lang.Object r1 = r6.next()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.internal.measurement.zzbk$zze r1 = (com.google.android.gms.internal.measurement.zzbk.zze) r1     // Catch:{ all -> 0x0140 }
            boolean r1 = r1.zza()     // Catch:{ all -> 0x0140 }
            if (r1 != 0) goto L_0x0181
            com.google.android.gms.measurement.internal.zzes r0 = r11.zzr()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzi()     // Catch:{ all -> 0x0140 }
            java.lang.String r1 = "Property filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzes.zza((java.lang.String) r12)     // Catch:{ all -> 0x0140 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0140 }
            r0.zza(r1, r6, r5)     // Catch:{ all -> 0x0140 }
            goto L_0x0110
        L_0x01aa:
            java.util.List r1 = r0.zze()     // Catch:{ all -> 0x0140 }
            java.util.Iterator r6 = r1.iterator()     // Catch:{ all -> 0x0140 }
        L_0x01b2:
            boolean r1 = r6.hasNext()     // Catch:{ all -> 0x0140 }
            if (r1 == 0) goto L_0x0250
            java.lang.Object r1 = r6.next()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.internal.measurement.zzbk$zzb r1 = (com.google.android.gms.internal.measurement.zzbk.zzb) r1     // Catch:{ all -> 0x0140 }
            boolean r1 = r11.zza((java.lang.String) r12, (int) r5, (com.google.android.gms.internal.measurement.zzbk.zzb) r1)     // Catch:{ all -> 0x0140 }
            if (r1 != 0) goto L_0x01b2
            r1 = r4
        L_0x01c5:
            if (r1 == 0) goto L_0x024e
            java.util.List r0 = r0.zzc()     // Catch:{ all -> 0x0140 }
            java.util.Iterator r6 = r0.iterator()     // Catch:{ all -> 0x0140 }
        L_0x01cf:
            boolean r0 = r6.hasNext()     // Catch:{ all -> 0x0140 }
            if (r0 == 0) goto L_0x024e
            java.lang.Object r0 = r6.next()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.internal.measurement.zzbk$zze r0 = (com.google.android.gms.internal.measurement.zzbk.zze) r0     // Catch:{ all -> 0x0140 }
            boolean r0 = r11.zza((java.lang.String) r12, (int) r5, (com.google.android.gms.internal.measurement.zzbk.zze) r0)     // Catch:{ all -> 0x0140 }
            if (r0 != 0) goto L_0x01cf
            r0 = r4
        L_0x01e2:
            if (r0 != 0) goto L_0x0110
            r11.zzak()     // Catch:{ all -> 0x0140 }
            r11.zzd()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)     // Catch:{ all -> 0x0140 }
            android.database.sqlite.SQLiteDatabase r0 = r11.mo24238c_()     // Catch:{ all -> 0x0140 }
            java.lang.String r1 = "property_filters"
            java.lang.String r6 = "app_id=? and audience_id=?"
            r8 = 2
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ all -> 0x0140 }
            r9 = 0
            r8[r9] = r12     // Catch:{ all -> 0x0140 }
            r9 = 1
            java.lang.String r10 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0140 }
            r8[r9] = r10     // Catch:{ all -> 0x0140 }
            r0.delete(r1, r6, r8)     // Catch:{ all -> 0x0140 }
            java.lang.String r1 = "event_filters"
            java.lang.String r6 = "app_id=? and audience_id=?"
            r8 = 2
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ all -> 0x0140 }
            r9 = 0
            r8[r9] = r12     // Catch:{ all -> 0x0140 }
            r9 = 1
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0140 }
            r8[r9] = r5     // Catch:{ all -> 0x0140 }
            r0.delete(r1, r6, r8)     // Catch:{ all -> 0x0140 }
            goto L_0x0110
        L_0x021b:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0140 }
            r1.<init>()     // Catch:{ all -> 0x0140 }
            java.util.Iterator r3 = r13.iterator()     // Catch:{ all -> 0x0140 }
        L_0x0224:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x0140 }
            if (r0 == 0) goto L_0x0244
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x0140 }
            com.google.android.gms.internal.measurement.zzbk$zza r0 = (com.google.android.gms.internal.measurement.zzbk.zza) r0     // Catch:{ all -> 0x0140 }
            boolean r4 = r0.zza()     // Catch:{ all -> 0x0140 }
            if (r4 == 0) goto L_0x0242
            int r0 = r0.zzb()     // Catch:{ all -> 0x0140 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0140 }
        L_0x023e:
            r1.add(r0)     // Catch:{ all -> 0x0140 }
            goto L_0x0224
        L_0x0242:
            r0 = 0
            goto L_0x023e
        L_0x0244:
            r11.zzb((java.lang.String) r12, (java.util.List<java.lang.Integer>) r1)     // Catch:{ all -> 0x0140 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0140 }
            r2.endTransaction()
            return
        L_0x024e:
            r0 = r1
            goto L_0x01e2
        L_0x0250:
            r1 = r7
            goto L_0x01c5
        L_0x0253:
            r1 = r6
            goto L_0x008d
        L_0x0256:
            r2 = r4
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.util.List):void");
    }

    @VisibleForTesting
    private final boolean zzam() {
        return zzn().getDatabasePath("google_app_measurement.db").exists();
    }
}
