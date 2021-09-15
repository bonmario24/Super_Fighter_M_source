package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzer extends SQLiteOpenHelper {
    private final /* synthetic */ zzeo zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzer(zzeo zzeo, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zza = zzeo;
    }

    @WorkerThread
    public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteDatabaseLockedException e) {
            throw e;
        } catch (SQLiteException e2) {
            this.zza.zzr().zzf().zza("Opening the local database failed, dropping and recreating it");
            if (!this.zza.zzn().getDatabasePath("google_app_measurement_local.db").delete()) {
                this.zza.zzr().zzf().zza("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e3) {
                this.zza.zzr().zzf().zza("Failed to open local database. Events will bypass local storage", e3);
                return null;
            }
        }
    }

    @WorkerThread
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        zzah.zza(this.zza.zzr(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", (String[]) null);
    }

    @WorkerThread
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzah.zza(this.zza.zzr(), sQLiteDatabase);
    }

    @WorkerThread
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @WorkerThread
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
