package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzeo extends zzg {
    private final zzer zza = new zzer(this, zzn(), "google_app_measurement_local.db");
    private boolean zzb;

    zzeo(zzfw zzfw) {
        super(zzfw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    @WorkerThread
    public final void zzab() {
        zzb();
        zzd();
        try {
            int delete = zzae().delete("messages", (String) null, (String[]) null) + 0;
            if (delete > 0) {
                zzr().zzx().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error resetting local analytics data. error", e);
        }
    }

    @WorkerThread
    private final boolean zza(int i, byte[] bArr) {
        zzb();
        zzd();
        if (this.zzb) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 5;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < 5) {
                SQLiteDatabase sQLiteDatabase = null;
                Cursor cursor = null;
                try {
                    sQLiteDatabase = zzae();
                    if (sQLiteDatabase == null) {
                        this.zzb = true;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return false;
                    }
                    sQLiteDatabase.beginTransaction();
                    long j = 0;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("select count(1) from messages", (String[]) null);
                    if (rawQuery != null && rawQuery.moveToFirst()) {
                        j = rawQuery.getLong(0);
                    }
                    if (j >= 100000) {
                        zzr().zzf().zza("Data loss, local db full");
                        long j2 = (100000 - j) + 1;
                        long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j2)});
                        if (delete != j2) {
                            zzr().zzf().zza("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(delete), Long.valueOf(j2 - delete));
                        }
                    }
                    sQLiteDatabase.insertOrThrow("messages", (String) null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return true;
                } catch (SQLiteFullException e) {
                    zzr().zzf().zza("Error writing entry; local database full", e);
                    this.zzb = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteDatabaseLockedException e2) {
                    SystemClock.sleep((long) i2);
                    i2 += 20;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteException e3) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    zzr().zzf().zza("Error writing entry to local database", e3);
                    this.zzb = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            } else {
                zzr().zzx().zza("Failed to write entry to local database");
                return false;
            }
            i3 = i4 + 1;
        }
    }

    public final boolean zza(zzao zzao) {
        Parcel obtain = Parcel.obtain();
        zzao.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzr().zzg().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzkh zzkh) {
        Parcel obtain = Parcel.obtain();
        zzkh.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzr().zzg().zza("User property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zza(zzw zzw) {
        zzp();
        byte[] zza2 = zzkm.zza((Parcelable) zzw);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzr().zzg().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x00c8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00fc A[SYNTHETIC, Splitter:B:69:0x00fc] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x014f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r19) {
        /*
            r18 = this;
            r18.zzd()
            r18.zzb()
            r0 = r18
            boolean r2 = r0.zzb
            if (r2 == 0) goto L_0x000e
            r2 = 0
        L_0x000d:
            return r2
        L_0x000e:
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            boolean r2 = r18.zzaf()
            if (r2 != 0) goto L_0x001b
            r2 = r12
            goto L_0x000d
        L_0x001b:
            r11 = 5
            r2 = 0
            r14 = r2
        L_0x001e:
            r2 = 5
            if (r14 >= r2) goto L_0x01ff
            r5 = 0
            r13 = 0
            android.database.sqlite.SQLiteDatabase r2 = r18.zzae()     // Catch:{ SQLiteFullException -> 0x0230, SQLiteDatabaseLockedException -> 0x0227, SQLiteException -> 0x021d, all -> 0x020f }
            if (r2 != 0) goto L_0x0035
            r3 = 1
            r0 = r18
            r0.zzb = r3     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            if (r2 == 0) goto L_0x0033
            r2.close()
        L_0x0033:
            r2 = 0
            goto L_0x000d
        L_0x0035:
            r2.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            long r8 = zza((android.database.sqlite.SQLiteDatabase) r2)     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r5 = 0
            r6 = 0
            r16 = -1
            int r3 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r3 == 0) goto L_0x0050
            java.lang.String r5 = "rowid<?"
            r3 = 1
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r3 = 0
            java.lang.String r4 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r6[r3] = r4     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
        L_0x0050:
            java.lang.String r3 = "messages"
            r4 = 3
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r7 = 0
            java.lang.String r8 = "rowid"
            r4[r7] = r8     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r7 = 1
            java.lang.String r8 = "type"
            r4[r7] = r8     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r7 = 2
            java.lang.String r8 = "entry"
            r4[r7] = r8     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid asc"
            r10 = 100
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            android.database.Cursor r4 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteFullException -> 0x0235, SQLiteDatabaseLockedException -> 0x022b, SQLiteException -> 0x0222, all -> 0x0214 }
            r6 = -1
        L_0x0074:
            boolean r3 = r4.moveToNext()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            if (r3 == 0) goto L_0x01c7
            r3 = 0
            long r6 = r4.getLong(r3)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r3 = 1
            int r3 = r4.getInt(r3)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r5 = 2
            byte[] r8 = r4.getBlob(r5)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            if (r3 != 0) goto L_0x0123
            android.os.Parcel r5 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r3 = 0
            int r9 = r8.length     // Catch:{ ParseException -> 0x00ce }
            r5.unmarshall(r8, r3, r9)     // Catch:{ ParseException -> 0x00ce }
            r3 = 0
            r5.setDataPosition(r3)     // Catch:{ ParseException -> 0x00ce }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzao> r3 = com.google.android.gms.measurement.internal.zzao.CREATOR     // Catch:{ ParseException -> 0x00ce }
            java.lang.Object r3 = r3.createFromParcel(r5)     // Catch:{ ParseException -> 0x00ce }
            com.google.android.gms.measurement.internal.zzao r3 = (com.google.android.gms.measurement.internal.zzao) r3     // Catch:{ ParseException -> 0x00ce }
            r5.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            if (r3 == 0) goto L_0x0074
            r12.add(r3)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            goto L_0x0074
        L_0x00a9:
            r3 = move-exception
            r5 = r2
        L_0x00ab:
            com.google.android.gms.measurement.internal.zzes r2 = r18.zzr()     // Catch:{ all -> 0x0219 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x0219 }
            java.lang.String r6 = "Error reading entries from local database"
            r2.zza(r6, r3)     // Catch:{ all -> 0x0219 }
            r2 = 1
            r0 = r18
            r0.zzb = r2     // Catch:{ all -> 0x0219 }
            if (r4 == 0) goto L_0x00c2
            r4.close()
        L_0x00c2:
            if (r5 == 0) goto L_0x023a
            r5.close()
            r2 = r11
        L_0x00c8:
            int r3 = r14 + 1
            r14 = r3
            r11 = r2
            goto L_0x001e
        L_0x00ce:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzes r3 = r18.zzr()     // Catch:{ all -> 0x00f3 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x00f3 }
            java.lang.String r8 = "Failed to load event from local database"
            r3.zza(r8)     // Catch:{ all -> 0x00f3 }
            r5.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            goto L_0x0074
        L_0x00e0:
            r3 = move-exception
            r5 = r2
        L_0x00e2:
            long r2 = (long) r11
            android.os.SystemClock.sleep(r2)     // Catch:{ all -> 0x0219 }
            int r2 = r11 + 20
            if (r4 == 0) goto L_0x00ed
            r4.close()
        L_0x00ed:
            if (r5 == 0) goto L_0x00c8
            r5.close()
            goto L_0x00c8
        L_0x00f3:
            r3 = move-exception
            r5.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            throw r3     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
        L_0x00f8:
            r3 = move-exception
            r5 = r2
        L_0x00fa:
            if (r5 == 0) goto L_0x0105
            boolean r2 = r5.inTransaction()     // Catch:{ all -> 0x0219 }
            if (r2 == 0) goto L_0x0105
            r5.endTransaction()     // Catch:{ all -> 0x0219 }
        L_0x0105:
            com.google.android.gms.measurement.internal.zzes r2 = r18.zzr()     // Catch:{ all -> 0x0219 }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ all -> 0x0219 }
            java.lang.String r6 = "Error reading entries from local database"
            r2.zza(r6, r3)     // Catch:{ all -> 0x0219 }
            r2 = 1
            r0 = r18
            r0.zzb = r2     // Catch:{ all -> 0x0219 }
            if (r4 == 0) goto L_0x011c
            r4.close()
        L_0x011c:
            if (r5 == 0) goto L_0x023a
            r5.close()
            r2 = r11
            goto L_0x00c8
        L_0x0123:
            r5 = 1
            if (r3 != r5) goto L_0x016b
            android.os.Parcel r9 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r5 = 0
            r3 = 0
            int r10 = r8.length     // Catch:{ ParseException -> 0x0153 }
            r9.unmarshall(r8, r3, r10)     // Catch:{ ParseException -> 0x0153 }
            r3 = 0
            r9.setDataPosition(r3)     // Catch:{ ParseException -> 0x0153 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzkh> r3 = com.google.android.gms.measurement.internal.zzkh.CREATOR     // Catch:{ ParseException -> 0x0153 }
            java.lang.Object r3 = r3.createFromParcel(r9)     // Catch:{ ParseException -> 0x0153 }
            com.google.android.gms.measurement.internal.zzkh r3 = (com.google.android.gms.measurement.internal.zzkh) r3     // Catch:{ ParseException -> 0x0153 }
            r9.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
        L_0x013f:
            if (r3 == 0) goto L_0x0074
            r12.add(r3)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            goto L_0x0074
        L_0x0146:
            r3 = move-exception
            r5 = r2
        L_0x0148:
            if (r4 == 0) goto L_0x014d
            r4.close()
        L_0x014d:
            if (r5 == 0) goto L_0x0152
            r5.close()
        L_0x0152:
            throw r3
        L_0x0153:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzes r3 = r18.zzr()     // Catch:{ all -> 0x0166 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x0166 }
            java.lang.String r8 = "Failed to load user property from local database"
            r3.zza(r8)     // Catch:{ all -> 0x0166 }
            r9.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r3 = r5
            goto L_0x013f
        L_0x0166:
            r3 = move-exception
            r9.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            throw r3     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
        L_0x016b:
            r5 = 2
            if (r3 != r5) goto L_0x01a6
            android.os.Parcel r9 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r5 = 0
            r3 = 0
            int r10 = r8.length     // Catch:{ ParseException -> 0x018e }
            r9.unmarshall(r8, r3, r10)     // Catch:{ ParseException -> 0x018e }
            r3 = 0
            r9.setDataPosition(r3)     // Catch:{ ParseException -> 0x018e }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzw> r3 = com.google.android.gms.measurement.internal.zzw.CREATOR     // Catch:{ ParseException -> 0x018e }
            java.lang.Object r3 = r3.createFromParcel(r9)     // Catch:{ ParseException -> 0x018e }
            com.google.android.gms.measurement.internal.zzw r3 = (com.google.android.gms.measurement.internal.zzw) r3     // Catch:{ ParseException -> 0x018e }
            r9.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
        L_0x0187:
            if (r3 == 0) goto L_0x0074
            r12.add(r3)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            goto L_0x0074
        L_0x018e:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzes r3 = r18.zzr()     // Catch:{ all -> 0x01a1 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ all -> 0x01a1 }
            java.lang.String r8 = "Failed to load conditional user property from local database"
            r3.zza(r8)     // Catch:{ all -> 0x01a1 }
            r9.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r3 = r5
            goto L_0x0187
        L_0x01a1:
            r3 = move-exception
            r9.recycle()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            throw r3     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
        L_0x01a6:
            r5 = 3
            if (r3 != r5) goto L_0x01b8
            com.google.android.gms.measurement.internal.zzes r3 = r18.zzr()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzi()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            java.lang.String r5 = "Skipping app launch break"
            r3.zza(r5)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            goto L_0x0074
        L_0x01b8:
            com.google.android.gms.measurement.internal.zzes r3 = r18.zzr()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            java.lang.String r5 = "Unknown record type in local database"
            r3.zza(r5)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            goto L_0x0074
        L_0x01c7:
            java.lang.String r3 = "messages"
            java.lang.String r5 = "rowid <= ?"
            r8 = 1
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r9 = 0
            java.lang.String r6 = java.lang.Long.toString(r6)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r8[r9] = r6     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            int r3 = r2.delete(r3, r5, r8)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            int r5 = r12.size()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            if (r3 >= r5) goto L_0x01ec
            com.google.android.gms.measurement.internal.zzes r3 = r18.zzr()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzf()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            java.lang.String r5 = "Fewer entries removed from local database than expected"
            r3.zza(r5)     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
        L_0x01ec:
            r2.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            r2.endTransaction()     // Catch:{ SQLiteFullException -> 0x00a9, SQLiteDatabaseLockedException -> 0x00e0, SQLiteException -> 0x00f8, all -> 0x0146 }
            if (r4 == 0) goto L_0x01f7
            r4.close()
        L_0x01f7:
            if (r2 == 0) goto L_0x01fc
            r2.close()
        L_0x01fc:
            r2 = r12
            goto L_0x000d
        L_0x01ff:
            com.google.android.gms.measurement.internal.zzes r2 = r18.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzi()
            java.lang.String r3 = "Failed to read events from database in reasonable time"
            r2.zza(r3)
            r2 = 0
            goto L_0x000d
        L_0x020f:
            r2 = move-exception
            r3 = r2
            r4 = r13
            goto L_0x0148
        L_0x0214:
            r3 = move-exception
            r4 = r13
            r5 = r2
            goto L_0x0148
        L_0x0219:
            r2 = move-exception
            r3 = r2
            goto L_0x0148
        L_0x021d:
            r2 = move-exception
            r3 = r2
            r4 = r13
            goto L_0x00fa
        L_0x0222:
            r3 = move-exception
            r4 = r13
            r5 = r2
            goto L_0x00fa
        L_0x0227:
            r2 = move-exception
            r4 = r13
            goto L_0x00e2
        L_0x022b:
            r3 = move-exception
            r4 = r13
            r5 = r2
            goto L_0x00e2
        L_0x0230:
            r2 = move-exception
            r3 = r2
            r4 = r13
            goto L_0x00ab
        L_0x0235:
            r3 = move-exception
            r4 = r13
            r5 = r2
            goto L_0x00ab
        L_0x023a:
            r2 = r11
            goto L_0x00c8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeo.zza(int):java.util.List");
    }

    @WorkerThread
    public final boolean zzac() {
        return zza(3, new byte[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        r5 = r5 + 1;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzad() {
        /*
            r11 = this;
            r6 = 5
            r2 = 1
            r1 = 0
            r11.zzd()
            r11.zzb()
            boolean r0 = r11.zzb
            if (r0 == 0) goto L_0x000f
            r0 = r1
        L_0x000e:
            return r0
        L_0x000f:
            boolean r0 = r11.zzaf()
            if (r0 != 0) goto L_0x0017
            r0 = r1
            goto L_0x000e
        L_0x0017:
            r5 = r1
            r0 = r6
        L_0x0019:
            if (r5 >= r6) goto L_0x009e
            r4 = 0
            android.database.sqlite.SQLiteDatabase r4 = r11.zzae()     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            if (r4 != 0) goto L_0x002c
            r3 = 1
            r11.zzb = r3     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            if (r4 == 0) goto L_0x002a
            r4.close()
        L_0x002a:
            r0 = r1
            goto L_0x000e
        L_0x002c:
            r4.beginTransaction()     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            java.lang.String r3 = "messages"
            java.lang.String r7 = "type == ?"
            r8 = 1
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            r9 = 0
            r10 = 3
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            r8[r9] = r10     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            r4.delete(r3, r7, r8)     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            r4.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            r4.endTransaction()     // Catch:{ SQLiteFullException -> 0x004e, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0075 }
            if (r4 == 0) goto L_0x004c
            r4.close()
        L_0x004c:
            r0 = r2
            goto L_0x000e
        L_0x004e:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzes r7 = r11.zzr()     // Catch:{ all -> 0x0097 }
            com.google.android.gms.measurement.internal.zzeu r7 = r7.zzf()     // Catch:{ all -> 0x0097 }
            java.lang.String r8 = "Error deleting app launch break from local database"
            r7.zza(r8, r3)     // Catch:{ all -> 0x0097 }
            r3 = 1
            r11.zzb = r3     // Catch:{ all -> 0x0097 }
            if (r4 == 0) goto L_0x0064
            r4.close()
        L_0x0064:
            int r3 = r5 + 1
            r5 = r3
            goto L_0x0019
        L_0x0068:
            r3 = move-exception
            long r8 = (long) r0
            android.os.SystemClock.sleep(r8)     // Catch:{ all -> 0x0097 }
            int r0 = r0 + 20
            if (r4 == 0) goto L_0x0064
            r4.close()
            goto L_0x0064
        L_0x0075:
            r3 = move-exception
            if (r4 == 0) goto L_0x0081
            boolean r7 = r4.inTransaction()     // Catch:{ all -> 0x0097 }
            if (r7 == 0) goto L_0x0081
            r4.endTransaction()     // Catch:{ all -> 0x0097 }
        L_0x0081:
            com.google.android.gms.measurement.internal.zzes r7 = r11.zzr()     // Catch:{ all -> 0x0097 }
            com.google.android.gms.measurement.internal.zzeu r7 = r7.zzf()     // Catch:{ all -> 0x0097 }
            java.lang.String r8 = "Error deleting app launch break from local database"
            r7.zza(r8, r3)     // Catch:{ all -> 0x0097 }
            r3 = 1
            r11.zzb = r3     // Catch:{ all -> 0x0097 }
            if (r4 == 0) goto L_0x0064
            r4.close()
            goto L_0x0064
        L_0x0097:
            r0 = move-exception
            if (r4 == 0) goto L_0x009d
            r4.close()
        L_0x009d:
            throw r0
        L_0x009e:
            com.google.android.gms.measurement.internal.zzes r0 = r11.zzr()
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzi()
            java.lang.String r2 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r2)
            r0 = r1
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeo.zzad():boolean");
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        try {
            Cursor query = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, (String) null, (String) null, "rowid desc", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            try {
                if (query.moveToFirst()) {
                    long j = query.getLong(0);
                    if (query == null) {
                        return j;
                    }
                    query.close();
                    return j;
                }
                if (query != null) {
                    query.close();
                }
                return -1;
            } catch (Throwable th) {
                th = th;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase zzae() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    @VisibleForTesting
    private final boolean zzaf() {
        return zzn().getDatabasePath("google_app_measurement_local.db").exists();
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
